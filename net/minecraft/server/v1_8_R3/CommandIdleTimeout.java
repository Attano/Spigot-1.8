package net.minecraft.server;

public class CommandIdleTimeout extends CommandAbstract {

    public CommandIdleTimeout() {}

    public String getCommand() {
        return "setidletimeout";
    }

    public int a() {
        return 3;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.setidletimeout.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length != 1) {
            throw new ExceptionUsage("commands.setidletimeout.usage", new Object[0]);
        } else {
            int i = a(astring[0], 0);

            MinecraftServer.getServer().setIdleTimeout(i);
            a(icommandlistener, this, "commands.setidletimeout.success", new Object[] { Integer.valueOf(i)});
        }
    }
}
