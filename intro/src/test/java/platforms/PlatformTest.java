package platforms;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.fail;

public class PlatformTest {

    private static final int TOO_MANY = 1_000;

    @Test
    public void tooManyThings() {
        assertTooMany(TypedTask.getEqualInstances());
        assertTooMany(Protocol.getInstances());
        assertTooMany(DataFormat.getInstances());
        assertTooMany(Standard.getInstances());
        //...
    }

    private static void assertTooMany(Collection<?> things) {
        if (things.size() < TOO_MANY) {
            fail();
        }
    }

}