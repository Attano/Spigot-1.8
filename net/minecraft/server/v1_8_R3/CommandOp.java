package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.List;

public class CommandOp extends CommandAbstract {

    public CommandOp() {}

    public String getCommand() {
        return "op";
    }

    public int a() {
        return 3;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.op.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length == 1 && astring[0].length() > 0) {
            MinecraftServer minecraftserver = MinecraftServer.getServer();
            GameProfile gameprofile = minecraftserver.getUserCache().getProfile(astring[0]);

            if (gameprofile == null) {
                throw new CommandException("commands.op.failed", new Object[] { astring[0]});
            } else {
                minecraftserver.getPlayerList().addOp(gameprofile);
                a(icommandlistener, this, "commands.op.success", new Object[] { astring[0]});
            }
        } else {
            throw new ExceptionUsage("commands.op.usage", new Object[0]);
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        if (astring.length == 1) {
            String s = astring[astring.length - 1];
            ArrayList arraylist = Lists.newArrayList();
            GameProfile[] agameprofile = MinecraftServer.getServer().L();
            int i = agameprofile.length;

            for (int j = 0; j < i; ++j) {
                GameProfile gameprofile = agameprofile[j];

                if (!MinecraftServer.getServer().getPlayerList().isOp(gameprofile) && a(s, gameprofile.getName())) {
                    arraylist.add(gameprofile.getName());
                }
            }

            return arraylist;
        } else {
            return null;
        }
    }
}
