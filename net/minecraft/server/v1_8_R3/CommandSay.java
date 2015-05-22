package net.minecraft.server;

import java.util.List;

public class CommandSay extends CommandAbstract {

    public CommandSay() {}

    public String getCommand() {
        return "say";
    }

    public int a() {
        return 1;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.say.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length > 0 && astring[0].length() > 0) {
            IChatBaseComponent ichatbasecomponent = b(icommandlistener, astring, 0, true);

            MinecraftServer.getServer().getPlayerList().sendMessage(new ChatMessage("chat.type.announcement", new Object[] { icommandlistener.getScoreboardDisplayName(), ichatbasecomponent}));
        } else {
            throw new ExceptionUsage("commands.say.usage", new Object[0]);
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length >= 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }
}
