import io.vavr.control.Try;

public class Main {

    // = Success(result) or Failure(exception)
    static Try<Integer> divide(Integer dividend, Integer divisor) {
        return Try.of(() -> dividend / divisor);
    }
}
