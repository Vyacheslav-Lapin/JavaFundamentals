import javaslang.control.Try;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckedExceptionsProblemSolverTest {

    private int bunchOfWork(int i) throws Exception {
        switch (i) {
            case 1:
                throw new SQLException();
            case 2:
                throw new IOException();
            case 3:
                throw new Exception();
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

        assertThat(Try.of(() -> bunchOfWork(0))
                .get(), is(0));
    }

    @SneakyThrows
    private void throwChecked2() {
        throw new SQLException();
    }

    @Test
    void sneakyThrowsTest() {
        assertThat(
                assertThrows(SQLException.class, this::throwChecked2),
                instanceOf(SQLException.class));
    }

    private void throwChecked() throws SQLException {
        throw new SQLException();
    }

    @Test
    @DisplayName("We can just wrap checked exception to unchecked")
    void weCanJustWrapCheckedExceptionToUnchecked() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            try {
                throwChecked();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        assertThat(runtimeException.getCause(), instanceOf(SQLException.class));
    }

    @Test
    @DisplayName("We can throw checked exception at runtime")
    void weCanThrowCheckedExceptionAtRuntime() {
        assertThrows(SQLException.class, () ->
                doThrow(new SQLException())
        );
    }

    private void doThrow(Exception e) {
        CheckedExceptionsProblemSolverTest.throwChecked(e);
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    private static <E extends Exception> void throwChecked(Exception e) throws E {
        throw (E) e;
    }
}
