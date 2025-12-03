package ai.neodatagroup.themis.bot;

public abstract class Command {
    private final String name;

    private final Argument[] args;

    public Command(String name, Argument... args) {
        this.name = name;
        this.args = args;
    }

    public String getName() {
        return name;
    }

    protected void validate(Object... values) {
        if (values.length != args.length) {
            throw new IllegalArgumentException("Expected " + args.length + " positional arguments, got " + values.length);
        }
        for (int i = 0; i < args.length; i++) {
            Argument spec = args[i];
            Object value = values[i];
            spec.validate(value);
        }
    }

    protected abstract Operation execute(Bot bot, Object... values);
}
