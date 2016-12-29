import languages.ProgrammingLanguage;
import org.junit.Test;
import platforms.DataFormat;
import platforms.Protocol;
import platforms.Standard;
import platforms.TypedTask;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ProgrammingLanguageTest {

    private static final int TOO_MANY = 1_000;

    @Test
    public void tooManyProgrammingLanguages() {
        List<ProgrammingLanguage> programmingLanguages =
                ProgrammingLanguage.getAll();
        assertThat(programmingLanguages.size(), is(716));
    }

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