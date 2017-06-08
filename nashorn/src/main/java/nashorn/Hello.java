package nashorn;

@FunctionalInterface
public interface Hello {
    @SuppressWarnings("SameParameterValue")
    String composeMessage(String s);
}
