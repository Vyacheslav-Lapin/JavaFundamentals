import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class MainTest {

    @Test
    void divide() {
        assertThat(Main.divide(2, 2), is(Try.success(1)));
    }


//        Function1<Integer, Integer> plusOne = a -> a + 1;
//        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
//
//        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);
//
//        (add1AndMultiplyBy2.apply(2)).isEqualTo(6);

}