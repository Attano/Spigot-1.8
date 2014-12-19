package net.minecraft.server;

import java.util.List;
import java.util.Map;

public interface ICommandHandler {

    int a(ICommandListener icommandlistener, String s);

    List a(ICommandListener icommandlistener, String s, BlockPosition blockposition);

    List a(ICommandListener icommandlistener);

    Map getCommands();
}
