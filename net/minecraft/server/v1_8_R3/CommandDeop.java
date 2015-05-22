package net.minecraft.server;

import com.mojang.authlib.GameProfile;
import java.util.List;

public class CommandDeop extends CommandAbstract {

    public CommandDeop() {}

    public String getCommand() {
        return "deop";
    }

    public int a() {
        return 3;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.deop.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length == 1 && astring[0].length() > 0) {
            MinecraftServer minecraftserver = MinecraftServer.getServer();
            GameProfile gameprofile = minecraftserver.getPlayerList().getOPs().a(astring[0]);

            if (gameprofile == null) {
                throw new CommandException("commands.deop.failed", new Object[] { astring[0]});
            } else {
                minecraftserver.getPlayerList().removeOp(gameprofile);
                a(icommandlistener, this, "commands.deop.success", new Object[] { astring[0]});
            }
        } else {
            throw new ExceptionUsage("commands.deop.usage", new Object[0]);
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, MinecraftServer.getServer().getPlayerList().n()) : null;
    }
}
