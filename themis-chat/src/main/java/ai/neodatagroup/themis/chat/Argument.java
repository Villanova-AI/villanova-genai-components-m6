package ai.neodatagroup.themis.chat;

public class Argument {
    private final Class<?> type;

    private final boolean required;

    private final boolean repeated;

    private final Object defaultValue;

    public Argument(Class<?> type, boolean required, boolean repeated, Object defaultValue) {
        this.type = type;
        this.required = required;
        this.repeated = repeated;
        this.defaultValue = defaultValue;
    }
}
