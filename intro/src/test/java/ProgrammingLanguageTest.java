import languages.ProgrammingLanguage;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProgrammingLanguageTest {

    @Test
    public void tooManyProgrammingLanguages() {
        List<ProgrammingLanguage> programmingLanguages =
                ProgrammingLanguage.getAll();
        assertThat(programmingLanguages.size(), is(716));
    }

}