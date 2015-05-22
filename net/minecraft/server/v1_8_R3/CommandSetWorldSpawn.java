package net.minecraft.server;

import java.util.List;

public class CommandSetWorldSpawn extends CommandAbstract {

    public CommandSetWorldSpawn() {}

    public String getCommand() {
        return "setworldspawn";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.setworldspawn.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        BlockPosition blockposition;

        if (astring.length == 0) {
            blockposition = b(icommandlistener).getChunkCoordinates();
        } else {
            if (astring.length != 3 || icommandlistener.getWorld() == null) {
                throw new ExceptionUsage("commands.setworldspawn.usage", new Object[0]);
            }

            blockposition = a(icommandlistener, astring, 0, true);
        }

        icommandlistener.getWorld().B(blockposition);
        MinecraftServer.getServer().getPlayerList().sendAll(new PacketPlayOutSpawnPosition(blockposition));
        a(icommandlistener, this, "commands.setworldspawn.success", new Object[] { Integer.valueOf(blockposition.getX()), Integer.valueOf(blockposition.getY()), Integer.valueOf(blockposition.getZ())});
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length > 0 && astring.length <= 3 ? a(astring, 0, blockposition) : null;
    }
}
