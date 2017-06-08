import io.vavr.*;
import io.vavr.control.Option;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * <h1>Functions</h1>
 * Functional programming is all about values and transformation of values using functions. Java 8 just provides a
 * Function which accepts one parameter and a BiFunction which accepts two parameters. Javaslang provides functions up
 * to a limit of 8 parameters. The functional interfaces are of called {@link Function0}, {@link Function1},
 * {@link Function2}, {@link Function3} and so on. If you need a function which throws a checked exception you can use
 * {@link CheckedFunction1}, {@link CheckedFunction2} and so on.
 */
@DisplayName("Functions")
class FunctionTest {

    /**
     * The following lambda expression creates a function to sum two integers
     */
    private Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

    @Test
    @DisplayName("Function works correctly")
    void sumWorksCorrectly() {
        assertThat(sum.apply(1, 2), is(3));
        assertThat(sum.apply(1).apply(2), is(3));
    }

    @Test
    @SneakyThrows
    @DisplayName("CheckedFunction works correctly")
    void checkedFunction() {
        CheckedFunction2<Integer, Integer, Integer> divide = (i, j) -> i / j;
        assertThat(divide.apply(2).apply(2), is(1));
        assertThrows(ArithmeticException.class, () -> divide.apply(1, 0));
    }

    /**
     * You can compose functions. In mathematics, function composition is the application of one function to the result
     * of another to produce a third function. For instance, the functions f : X → Y and g : Y → Z can be composed to
     * yield a function h: g(f(x)) which maps X → Z.
     */
    @Nested
    class Composition {

        private Function1<Integer, Integer> plusOne = a -> a + 1;
        private Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

        /**
         * You can use either {@link Function1#andThen(Function) andThen}:
         */
        @Test
        @DisplayName("Composition by andThen method works correctly")
        void compositionWorksCorrectly() {
            Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);

            assertThat(add1AndMultiplyBy2.apply(2), is(6));
        }

        /**
         * , or {@link Function1#compose(Function) compose}</code>:
         */
        @Test
        @DisplayName("Composition by compose method works correctly")
        void name() {
            Function1<Integer, Integer> plusOne = a -> a + 1;
            Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

            Function1<Integer, Integer> add1AndMultiplyBy2 = multiplyByTwo.compose(plusOne);

            assertThat(add1AndMultiplyBy2.apply(2), is(6));
        }
    }

    /**
     * You can lift a partial function into a total function that returns an Option result. The term partial function
     * comes from mathematics. A partial function from X to Y is a function f: X′ → Y, for some subset X′ of X. It
     * generalizes the concept of a function f: X → Y by not forcing f to map every element of X to an element of Y.
     * That means a partial function works properly only for some input values. If the function is called with a
     * disallowed input value, it will typically throw an exception.
     */
    @Nested
    class Lifting {

        /**
         * The following method divide is a partial function that only accepts non-zero divisors.
         */
        private Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;

        /**
         * We use lift to turn divide into a total function that is defined for all inputs.
         */
        @Test
        @DisplayName("Simple lifting")
        void simpleLifting() {
            Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

            // A lifted function returns None instead of throwing an exception, if the function is invoked with
            // disallowed input values.
            assertThat(safeDivide.apply(1, 0), is(Option.none()));

            // 	A lifted function returns Some, if the function is invoked with allowed input values.
            assertThat(safeDivide.apply(4, 2), is(Option.of(2)));
        }

        /**
         * Partial function that only accepts positive input values.
         *
         * @throws IllegalArgumentException for negative input values.
         */
        private int sum(int first, int second) {
            if (first < 0 || second < 0)
                throw new IllegalArgumentException("Only positive integers are allowed");
            return first + second;
        }

        /**
         * We may lift the sum method by providing the methods reference.
         */
        @Test
        @DisplayName("Lift the sum method")
        void liftTheSumMethod() {
            Function2<Integer, Integer, Option<Integer>> sum = Function2.lift(this::sum);

            // The lifted function catches the IllegalArgumentException and maps it to None.
            assertThat(sum.apply(-1, 2), is(Option.none()));
        }
    }

    /**
     * Partial application allows you to derive a new function from an existing one by fixing some values. You can fix
     * one or more parameters, and the number of fixed parameters defines the arity of the new function such that new
     * arity = (original arity - fixed parameters). The parameters are bound from left to right.
     */
    @Nested
    @DisplayName("Partial application")
    class PartialApplication {

        private Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

        @Test
        @DisplayName("One of two param fixing")
        void oneOfTwoParamFixing() {
            // The first parameter a is fixed to the value 2.
            Function1<Integer, Integer> add2 = sum.apply(2);
            assertThat(add2.apply(4), is(6));
        }

        @Test
        @DisplayName("Three of five params fixing")
        void threeOfFiveParamsFixing() {
            Function5<Integer, Integer, Integer, Integer, Integer, Integer> sum = (a, b, c, d, e) -> a + b + c + d + e;

            // The a, b and c parameters are fixed to the values 2, 3 and 1 respectively.
            Function2<Integer, Integer, Integer> add6 = sum.apply(2, 3, 1);

            assertThat(add6.apply(4, 3), is(13));
        }
    }

    /**
     * Currying is a technique to partially apply a function by fixing a value for one of the parameters, resulting in a
     * {@link Function1} function that returns a {@link Function1}.<br/>
     * When a {@link Function2} is curried, the result is indistinguishable from the partial application of a
     * {@link Function2} because both result in a 1-arity function.
     */
    @Nested
    class Currying {
        private Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

        @Test
        void simpleCarry() {
            // The first parameter a is fixed to the value 2.
            Function1<Integer, Integer> add2 = sum.curried().apply( 2);

            assertThat(add2.apply(4), is(6));
        }

        /**
         * You might notice that, apart from the use of {@link Function2#curried .curried()}, this code is identical to
         * the 2-arity given example in Partial application. With higher-arity functions, the difference becomes clear.
         */
        @Test
        @DisplayName("Two params carry")
        void twoParamsCarry() {
            Function3<Integer, Integer, Integer, Integer> sum = (a, b, c) -> a + b + c;

            // Note the presence of additional functions in the parameters.
            Function1<Integer, Function1<Integer, Integer>> add2 = sum.curried().apply(2);

            // Further calls to apply returns another Function1, apart from the final call.
            assertThat(add2.apply(4).apply(3), is(9));
        }
    }

    /**
     * Memoization is a form of caching. A memoized function executes only once and then returns the result from a
     * cache.
     */
    @Nested
    class Memoization {

        /**
         * The following example calculates a random number on the first invocation and returns the cached number on the
         * second invocation.
         */
        @Test
        void memoizeRandom() {
            Function0<Double> hashCache = Function0.of(Math::random).memoized();

            double randomValue1 = hashCache.apply();

            assertThat(hashCache.apply(), is(randomValue1));
        }
    }
}
