package ai.neodatagroup.themis.chat;

import java.util.Collections;
import java.util.Map;

public abstract class Command {
    protected final Bot bot;

    private final String name;

    private final Argument[] args;

    private final Map<String, Argument> namedArgs;

    public Command(Bot bot, String name) {
        this(bot, name, Collections.emptyMap(), new Argument[0]);
    }

    public Command(Bot bot, String name, Argument... args) {
        this(bot, name, Collections.emptyMap(), args);
    }

    public Command(Bot bot, String name, Map<String, Argument> namedArgs, Argument... args) {
        this.bot = bot;
        this.name = name;
        this.args = args;
        this.namedArgs = namedArgs;
    }

    public String getName() {
        return name;
    }

    public abstract void execute();
}
