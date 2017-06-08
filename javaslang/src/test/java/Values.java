import io.vavr.Lazy;
import io.vavr.collection.CharSeq;
import io.vavr.collection.Seq;
import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import lombok.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;
import static io.vavr.control.Validation.invalid;
import static io.vavr.control.Validation.valid;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

/**
 * In a functional setting we see a value as a kind of
 * <a href="https://en.wikipedia.org/wiki/Normal_form_(abstract_rewriting)">normal form</a>, it cannot be further
 * evaluated. In Java we express this by making the state of an object final and call it
 * <a href="https://en.wikipedia.org/wiki/Immutable_object">immutable</a>.<br/>
 * Javaslang’s functional Value abstracts over immutable objects. Efficient write operations are added by sharing
 * immutable memory between instances. What we get is thread-safety for free!
 */
public class Values {

    @Nested
    @DisplayName("javaslang.control.Option")
    class OptionTest {
        @Test
        @DisplayName("java.util.Optional isn`t a monad")
        void optionalIsNotAMonad() {
            Optional<String> maybeFoo = Optional.of("foo");
            assertThat(maybeFoo.get(), is("foo"));

            // The resulting option becomes empty here
            Optional<String> maybeFooBar = maybeFoo
                    .map(s -> (String) null)
                    .map(s -> s.toUpperCase() + "bar");

            assertFalse(maybeFooBar.isPresent());
        }

        /**
         * This seems like Javaslang’s implementation is broken, but in fact it’s not - rather, it adheres to the
         * requirement of a monad to maintain computational context when calling .map. In terms of an Option, this means
         * that calling {@link Option#map} on a <code>Some</code> will result in a <code>Some</code>, and calling
         * {@link Option#map} on a <code>None</code> will result in a <code>None</code>. In the
         * {@link Optional Java Optional} example above, that context changed from a <code>Some</code> to a
         * <code>None</code>.
         */
        @Test
        @DisplayName("Option is a true monad")
        void optionIsATrueMonad() {
            Option<String> maybeFoo = Option.of("foo");
            assertThat(maybeFoo.get(), is("foo"));

            assertThrows(
                    NullPointerException.class,

                    // The resulting option is Some(null)
                    () -> maybeFoo.map(s -> (String) null)

                            // The call to s.toUpperCase() is invoked on a null
                            .map(s -> s.toUpperCase() + "bar"));
        }

        /**
         * This may seem to make {@link Option} useless, but it actually forces you to pay attention to possible
         * occurrences of <code>null</code> and deal with them accordingly instead of unknowingly accepting them. The
         * correct way to deal with occurrences of <code>null</code> is to use {@link Option#flatMap}.
         */
        @Test
        @DisplayName("Option required for discipline")
        void optionRequiredForDiscipline() {

            Option<String> maybeFoo = Option.of("foo");
            assertThat(maybeFoo.get(), is("foo"));

            // The resulting option is Some(null)
            Option<String> maybeFooBar = maybeFoo
                    .map(s -> (String) null)
                    .flatMap(s -> Option.of(s)
                            .map(t -> t.toUpperCase() + "bar"));

            assertTrue(maybeFooBar.isEmpty());
        }

        /**
         * Alternatively, we can move the {@link Option#flatMap .flatMap} to be co-located with the the possibly
         * <code>null</code> value.
         */
        @Test
        void correctFlatMapUsage() {
            Option<String> maybeFoo = Option.of("foo");
            assertThat(maybeFoo.get(), is("foo"));

            Option<String> maybeFooBar = maybeFoo
                    // The resulting option is None
                    .flatMap(s -> Option.of((String) null))

                    // This step never occurs because the option is a None
                    .map(s -> s.toUpperCase() + "bar");

            assertTrue(maybeFooBar.isEmpty());
        }
    }

    /**
     * Try is a monadic container type which represents a computation that may either result in an exception, or return
     * a successfully computed value. It’s similar to, but semantically different from {@link Either}. Instances of
     * <code>Try</code>, are either an instance of <code>Success</code> or <code>Failure</code>.
     */
    @Nested
    @DisplayName("Try")
    class TryTest {

        private int bunchOfWork(int i) throws Throwable {
            switch (i) {
                case 1:
                    throw new SQLException();
                case 2:
                    throw new IOException();
                case 3:
                    throw new Exception();
                case 4:
                    throw new Throwable();
                default:
                    return 0;
            }
        }

        @Test
        @DisplayName("Try simple usage")
        void simpleUsage() {
            // no need to handle exceptions
            assertThat(Try.of(() -> bunchOfWork(1))
                    .getOrElse(1), is(1));
        }

        @Test
        @DisplayName("Try with pattern matching")
        void patternMatchingWithTry() {
            assertThat(Try.of(() -> bunchOfWork(1))
                    .recover(x -> Match(x).of(
                            Case($(instanceOf(SQLException.class)), 1),
                            Case($(instanceOf(IOException.class)), 2),
                            Case($(instanceOf(Exception.class)), 3)))
                    .getOrElse(4), is(1));

            assertThat(Try.of(() -> bunchOfWork(2))
                    .recover(x -> Match(x).of(
                            Case($(instanceOf(SQLException.class)), 1),
                            Case($(instanceOf(IOException.class)), 2),
                            Case($(instanceOf(Exception.class)), 3)))
                    .getOrElse(4), is(2));

            assertThat(Try.of(() -> bunchOfWork(3))
                    .recover(x -> Match(x).of(
                            Case($(instanceOf(SQLException.class)), 1),
                            Case($(instanceOf(IOException.class)), 2),
                            Case($(instanceOf(Exception.class)), 3)))
                    .getOrElse(4), is(3));

            assertThat(Try.of(() -> bunchOfWork(0))
                    .recover(x -> Match(x).of(
                            Case($(instanceOf(SQLException.class)), 1),
                            Case($(instanceOf(IOException.class)), 2),
                            Case($(instanceOf(Exception.class)), 3)))
                    .getOrElse(4), is(0));

            assertThat(Try.of(() -> bunchOfWork(4))
                    .recover(x -> Match(x).of(
                            Case($(instanceOf(SQLException.class)), 1),
                            Case($(instanceOf(IOException.class)), 2),
                            Case($(instanceOf(Exception.class)), 3)))
                    .getOrElse(4), is(4));
        }


    }

    @Nested
    @DisplayName("Lazy")
    class LazyTest {

        /**
         * {@link Lazy} is a monadic container type which represents a lazy evaluated value. Compared to a
         * {@link Supplier}, Lazy is memoizing, i.e. it evaluates only once and therefore is referential transparent.
         */
        @Test
        @DisplayName("Lazy works correctly")
        void lazyWorksCorrectly() {
            Lazy<Double> lazy = Lazy.of(Math::random);
            assertFalse(lazy.isEvaluated());
            Double aDouble = lazy.get();
            assertTrue(lazy.isEvaluated());
            assertThat(lazy.get(), is(aDouble));
        }

        /**
         * you may also create a real lazy value (works only with interfaces):
         */
        @Test
        @DisplayName("Real Lazy value")
        void realLazyValue() {
            CharSequence chars = Lazy.val(() -> "Yay!", CharSequence.class);
            assertThat(chars.toString(), is("Yay!"));
        }
    }

    /**
     * {@link Either} represents a value of two possible types. An Either is either a Left or a Right. If the given
     * Either is a Right and projected to a Left, the Left operations have no effect on the Right value. If the given
     * Either is a Left and projected to a Right, the Right operations have no effect on the Left value. If a Left is
     * projected to a Left or a Right is projected to a Right, the operations have an effect.
     */
    @Nested
    @DisplayName("Either")
    class EitherTest {

        private Either<String, Integer> compute(boolean correctness) {
            return correctness ? Either.right(1) : Either.left("error");
        }

        /**
         * A compute() function, which results either in an Integer value (in the case of success) or in an error
         * message of type String (in the case of failure). By convention the success case is Right and the failure is
         * Left.
         */
        @Test
        @DisplayName("Either works correctly")
        void eitherWorksCorrectly() {
            Either<String, Integer> realValue = compute(true).right().map(i -> i * 2).toEither();
            assertTrue(realValue.isRight());
            assertFalse(realValue.isLeft());
            assertThat(realValue.get(), is(2));

            Either<String, Integer> errorValue = compute(false).right().map(i -> i * 2).toEither();
            assertTrue(errorValue.isLeft());
            assertThat(errorValue.getLeft(), is("error"));
        }
    }

    /**
     * A {@link Future} is a computation result that becomes available at some point. All operations provided are
     * non-blocking. The underlying {@link ExecutorService} is used to execute asynchronous handlers, e.g. via
     * onComplete(…​).<br/>
     * A Future has two states: pending and completed.
     * <dl>
     * <dt>Pending</dt>
     * <dd>The computation is ongoing. Only a pending future may be completed or cancelled.</dd>
     * <dt>Completed</dt>
     * <dd>The computation finished successfully with a result, failed with an exception or was cancelled.</dd>
     * </ul>
     * <p>
     * Callbacks may be registered on a Future at each point of time. These actions are performed as soon as the Future
     * is completed. An action which is registered on a completed Future is immediately performed. The action may run on
     * a separate Thread, depending on the underlying ExecutorService. Actions which are registered on a cancelled
     * Future are performed with the failed result.
     */
    @Nested
    @DisplayName("Future")
    class FutureTest {
        @Test
        @DisplayName("Future works correctly")
        void futureWorksCorrectly() {
            assertThat(Future.of(() -> 1).get(), is(1));
        }
    }

    /**
     * The Validation control is an applicative functor and facilitates accumulating errors. When trying to compose
     * Monads, the combination process will short circuit at the first encountered error. But 'Validation' will continue
     * processing the combining functions, accumulating all errors. This is especially useful when doing validation of
     * multiple fields, say a web form, and you want to know all errors encountered, instead of one at a time.
     */
    @Nested
    @DisplayName("Validation")
    class ValidationTest {

        private static final String VALID_NAME_CHARS = "[a-zA-Z ]";
        private static final int MIN_AGE = 0;

        @Value
        private class Person {
            private final String name;
            private final int age;
        }

        private Validation<Seq<String>, Person> validatePerson(String name, int age) {
            return Validation.combine(
                    validateName(name),
                    validateAge(age))
                    .ap(Person::new);
        }

        private Validation<String, String> validateName(String name) {
            return CharSeq.of(name)
                    .replaceAll(VALID_NAME_CHARS, "")
                    .transform(seq -> seq.isEmpty() ? valid(name) : invalid(
                            String.format("Name contains invalid characters: '%s'", seq.distinct().sorted())));
        }

        private Validation<String, Integer> validateAge(int age) {
            return age < MIN_AGE ? invalid("Age must be at least " + MIN_AGE) : valid(age);
        }

        @Test
        @DisplayName("Validation works correctly")
        void validationWorksCorrectly() {
            assertThat(validatePerson("Ruslan", 24).get(), is(new Person("Ruslan", 24)));
        }

    }
}
