package platforms;

import java.util.Arrays;
import java.util.Collection;

@FunctionalInterface
public interface Platform {

    String getName();

    static Collection<Platform> getAll() {
        return Arrays.asList(
                new DaVinciMachine(),
                () -> ".NET Framework",
                () -> "Client-side JavaScript Engine",
                () -> "Android",
                () -> "IPhone",
                () -> "Haskell", //?
                () -> "Delphi" //?
        );
    }
}
