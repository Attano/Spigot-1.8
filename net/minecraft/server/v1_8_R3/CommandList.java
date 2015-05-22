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

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        int i = MinecraftServer.getServer().I();

        icommandlistener.sendMessage(new ChatMessage("commands.players.list", new Object[] { Integer.valueOf(i), Integer.valueOf(MinecraftServer.getServer().J())}));
        icommandlistener.sendMessage(new ChatComponentText(MinecraftServer.getServer().getPlayerList().b(astring.length > 0 && "uuids".equalsIgnoreCase(astring[0]))));
        icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, i);
    }
}
