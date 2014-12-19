package net.minecraft.server;

import java.util.Collection;
import java.util.List;

public class CommandExecute extends CommandAbstract {

    public CommandExecute() {}

    public String getCommand() {
        return "execute";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.execute.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 5) {
            throw new ExceptionUsage("commands.execute.usage", new Object[0]);
        } else {
            Entity entity = a(icommandlistener, astring[0], Entity.class);
            double d0 = b(entity.locX, astring[1], false);
            double d1 = b(entity.locY, astring[2], false);
            double d2 = b(entity.locZ, astring[3], false);
            BlockPosition blockposition = new BlockPosition(d0, d1, d2);
            byte b0 = 4;

            if ("detect".equals(astring[4]) && astring.length > 10) {
                World world = icommandlistener.getWorld();
                double d3 = b(d0, astring[5], false);
                double d4 = b(d1, astring[6], false);
                double d5 = b(d2, astring[7], false);
                Block block = g(icommandlistener, astring[8]);
                int i = a(astring[9], -1, 15);
                BlockPosition blockposition1 = new BlockPosition(d3, d4, d5);
                IBlockData iblockdata = world.getType(blockposition1);

                if (iblockdata.getBlock() != block || i >= 0 && iblockdata.getBlock().toLegacyData(iblockdata) != i) {
                    throw new CommandException("commands.execute.failed", new Object[] { "detect", entity.getName()});
                }

                b0 = 10;
            }

            String s = a(astring, b0);
            CommandListenerEntity commandlistenerentity = new CommandListenerEntity(this, entity, icommandlistener, blockposition, d0, d1, d2);
            ICommandHandler icommandhandler = MinecraftServer.getServer().getCommandHandler();

            try {
                int j = icommandhandler.a(commandlistenerentity, s);

                if (j < 1) {
                    throw new CommandException("commands.execute.allInvocationsFailed", new Object[] { s});
                }
            } catch (Throwable throwable) {
                throw new CommandException("commands.execute.failed", new Object[] { s, entity.getName()});
            }
        }
    }

    public List tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : (astring.length > 1 && astring.length <= 4 ? a(astring, 1, blockposition) : (astring.length > 5 && astring.length <= 8 && "detect".equals(astring[4]) ? a(astring, 5, blockposition) : (astring.length == 9 && "detect".equals(astring[4]) ? a(astring, (Collection) Block.REGISTRY.keySet()) : null)));
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 0;
    }
}
