package net.minecraft.server;

import java.util.List;

public class CommandMe extends CommandAbstract {

    public CommandMe() {}

    public String getCommand() {
        return "me";
    }

    public int a() {
        return 0;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.me.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length <= 0) {
            throw new ExceptionUsage("commands.me.usage", new Object[0]);
        } else {
            IChatBaseComponent ichatbasecomponent = b(icommandlistener, astring, 0, !(icommandlistener instanceof EntityHuman));

            MinecraftServer.getServer().getPlayerList().sendMessage(new ChatMessage("chat.type.emote", new Object[] { icommandlistener.getScoreboardDisplayName(), ichatbasecomponent}));
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return a(astring, MinecraftServer.getServer().getPlayers());
    }
}
