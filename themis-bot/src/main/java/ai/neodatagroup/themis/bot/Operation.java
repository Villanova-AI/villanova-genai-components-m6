package ai.neodatagroup.themis.bot;

public sealed interface Operation permits Push, Pop, Peek, Noop {
    void execute(Bot bot);
}
