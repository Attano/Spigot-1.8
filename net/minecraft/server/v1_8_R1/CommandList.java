package net.minecraft.server;

public class CommandList extends CommandAbstract {

    public CommandList() {}

    public String getCommand() {
        return "list";
    }

    public int a() {
        return 0;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.players.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        int i = MinecraftServer.getServer().G();

        icommandlistener.sendMessage(new ChatMessage("commands.players.list", new Object[] { Integer.valueOf(i), Integer.valueOf(MinecraftServer.getServer().H())}));
        icommandlistener.sendMessage(new ChatComponentText(MinecraftServer.getServer().getPlayerList().f()));
        icommandlistener.a(EnumCommandResult.QUERY_RESULT, i);
    }
}
