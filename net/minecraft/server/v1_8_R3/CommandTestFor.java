package net.minecraft.server;

import java.util.List;

public class CommandTestFor extends CommandAbstract {

    public CommandTestFor() {}

    public String getCommand() {
        return "testfor";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.testfor.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 1) {
            throw new ExceptionUsage("commands.testfor.usage", new Object[0]);
        } else {
            Entity entity = b(icommandlistener, astring[0]);
            NBTTagCompound nbttagcompound = null;

            if (astring.length >= 2) {
                try {
                    nbttagcompound = MojangsonParser.parse(a(astring, 1));
                } catch (MojangsonParseException mojangsonparseexception) {
                    throw new CommandException("commands.testfor.tagError", new Object[] { mojangsonparseexception.getMessage()});
                }
            }

            if (nbttagcompound != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                entity.e(nbttagcompound1);
                if (!GameProfileSerializer.a(nbttagcompound, nbttagcompound1, true)) {
                    throw new CommandException("commands.testfor.failure", new Object[] { entity.getName()});
                }
            }

            a(icommandlistener, this, "commands.testfor.success", new Object[] { entity.getName()});
        }
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 0;
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }
}
