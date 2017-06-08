import lombok.SneakyThrows;
import nashorn.Hello;
import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class NashornHWTest {

    private static ScriptEngine nashorn = new ScriptEngineManager()
            .getEngineByName("nashorn");

    @Test
    @SneakyThrows
    void baseChecking() {
        Hello hello = (Hello) nashorn.eval("load('src/main/js/hello.js')");
        assertThat(hello.composeMessage("JDK 8"),
                is("Hello from Nashorn, JDK 8!"));
    }
}
