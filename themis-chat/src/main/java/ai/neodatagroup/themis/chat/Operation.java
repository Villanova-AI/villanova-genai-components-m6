package ai.neodatagroup.themis.chat;

public sealed interface Operation permits Push, Pop, Peek, Noop {
    void execute(Bot bot);
}
