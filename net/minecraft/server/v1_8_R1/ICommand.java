package net.minecraft.server;

import java.util.List;

public interface ICommand extends Comparable {

    String getCommand();

    String getUsage(ICommandListener icommandlistener);

    List b();

    void execute(ICommandListener icommandlistener, String[] astring);

    boolean canUse(ICommandListener icommandlistener);

    List tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition);

    boolean isListStart(String[] astring, int i);
}
