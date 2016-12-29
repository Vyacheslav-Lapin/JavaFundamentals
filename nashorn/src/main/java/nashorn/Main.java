package nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Main {

    private static ScriptEngine nashorn = new ScriptEngineManager()
            .getEngineByName("nashorn");

    public static void main(String[] args) throws ScriptException {

        Hello hello = (Hello) nashorn.eval("load('src/main/js/hello.js')");
        String message = hello.composeMessage("JDK 8");
        System.out.println(message);
    }
}
