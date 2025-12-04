package ai.villanova.themis.bot;

public sealed interface Operation permits Push, Pop, Peek, Noop {
    void execute(Bot bot);
}
