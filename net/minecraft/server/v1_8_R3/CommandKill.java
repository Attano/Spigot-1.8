package net.minecraft.server;

import java.util.List;

public class CommandKill extends CommandAbstract {

    public CommandKill() {}

    public String getCommand() {
        return "kill";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.kill.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length == 0) {
            EntityPlayer entityplayer = b(icommandlistener);

            entityplayer.G();
            a(icommandlistener, this, "commands.kill.successful", new Object[] { entityplayer.getScoreboardDisplayName()});
        } else {
            Entity entity = b(icommandlistener, astring[0]);

            entity.G();
            a(icommandlistener, this, "commands.kill.successful", new Object[] { entity.getScoreboardDisplayName()});
        }
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 0;
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }
}
