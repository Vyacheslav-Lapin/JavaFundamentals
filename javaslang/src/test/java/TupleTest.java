import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h1>Tuples</h1>
 * Java is missing a general notion of tuples. A Tuple combines a fixed number of elements together so that they can be
 * passed around as a whole. Unlike an array or list, a tuple can hold objects with different types, but they are also
 * immutable.<br/>
 * Tuples are of type Tuple1, Tuple2, Tuple3 and so on. There currently is an upper limit of 8 elements. To access
 * elements of a tuple <code>t</code>, you can use method {@link Tuple2#_1 t._1} to access the first element,
 * {@link Tuple2#_2 t._2} to access the second, and so on.
 */
@DisplayName("TupleTest should work correctly")
class TupleTest {

    /**
     * <h1>Create a tuple</h1>
     * Here is an example of how to create a tuple holding a String and an Integer:
     * a tuple is created via the static factory method {@link Tuple#of Tuple.of()}
     */
    private Tuple2<String, Integer> java8 = Tuple.of("Java", 8);

    @Test
    @DisplayName("Tuple was created correctly")
    void tupleCreatedCorrectly() {
        // Get the 1st element of this tuple
        assertEquals("Java", java8._1);

        // Get the 2nd element of this tuple
        assertEquals(8, java8._2.intValue());
    }

    /**
     * <h1>Map a tuple component-wise</h1>
     * The component-wise map evaluates a function per element in the tuple, returning
     * another tuple.
     */
    @Test
    @DisplayName("Map a tuple component-wise")
    void mapATupleComponentWise() {
        Tuple2<String, Integer> tuple2 = java8.map(s -> s + "slang", i -> i / 4);
        assertThat(tuple2._1, is("Javaslang"));
        assertThat(tuple2._2, is(2));
    }

    /**
     * <h1>Transform a tuple</h1>
     * Transform creates a new type based on the tuple’s contents.
     */
    @Test
    @DisplayName("Transform a tuple")
    void transformTuple() {
        assertThat(java8.apply(
                (s, i) -> s + "slang " + i / 4
        ), is("Javaslang 2"));
    }

    /**
     * <h1>Map a tuple using one mapper</h1>
     * It is also possible to map a tuple using one mapping function.
     */
    @Test
    @DisplayName("Map a tuple using one mapper")
    void mapTupleUsingOneMapper() {
        Tuple2<String, Integer> tuple2 = java8.map(
                (s, i) -> Tuple.of(
                        s + "slang",
                        i / 4));

        assertThat(tuple2, is(Tuple.of("Javaslang",2)));
    }
}
