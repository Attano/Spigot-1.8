package net.minecraft.server;

import com.mojang.authlib.GameProfile;
import java.util.List;

public class CommandWhitelist extends CommandAbstract {

    public CommandWhitelist() {}

    public String getCommand() {
        return "whitelist";
    }

    public int a() {
        return 3;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.whitelist.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 1) {
            throw new ExceptionUsage("commands.whitelist.usage", new Object[0]);
        } else {
            MinecraftServer minecraftserver = MinecraftServer.getServer();

            if (astring[0].equals("on")) {
                minecraftserver.getPlayerList().setHasWhitelist(true);
                a(icommandlistener, this, "commands.whitelist.enabled", new Object[0]);
            } else if (astring[0].equals("off")) {
                minecraftserver.getPlayerList().setHasWhitelist(false);
                a(icommandlistener, this, "commands.whitelist.disabled", new Object[0]);
            } else if (astring[0].equals("list")) {
                icommandlistener.sendMessage(new ChatMessage("commands.whitelist.list", new Object[] { Integer.valueOf(minecraftserver.getPlayerList().getWhitelisted().length), Integer.valueOf(minecraftserver.getPlayerList().getSeenPlayers().length)}));
                String[] astring1 = minecraftserver.getPlayerList().getWhitelisted();

                icommandlistener.sendMessage(new ChatComponentText(a((Object[]) astring1)));
            } else {
                GameProfile gameprofile;

                if (astring[0].equals("add")) {
                    if (astring.length < 2) {
                        throw new ExceptionUsage("commands.whitelist.add.usage", new Object[0]);
                    }

                    gameprofile = minecraftserver.getUserCache().getProfile(astring[1]);
                    if (gameprofile == null) {
                        throw new CommandException("commands.whitelist.add.failed", new Object[] { astring[1]});
                    }

                    minecraftserver.getPlayerList().addWhitelist(gameprofile);
                    a(icommandlistener, this, "commands.whitelist.add.success", new Object[] { astring[1]});
                } else if (astring[0].equals("remove")) {
                    if (astring.length < 2) {
                        throw new ExceptionUsage("commands.whitelist.remove.usage", new Object[0]);
                    }

                    gameprofile = minecraftserver.getPlayerList().getWhitelist().a(astring[1]);
                    if (gameprofile == null) {
                        throw new CommandException("commands.whitelist.remove.failed", new Object[] { astring[1]});
                    }

                    minecraftserver.getPlayerList().removeWhitelist(gameprofile);
                    a(icommandlistener, this, "commands.whitelist.remove.success", new Object[] { astring[1]});
                } else if (astring[0].equals("reload")) {
                    minecraftserver.getPlayerList().reloadWhitelist();
                    a(icommandlistener, this, "commands.whitelist.reloaded", new Object[0]);
                }
            }

        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        if (astring.length == 1) {
            return a(astring, new String[] { "on", "off", "list", "add", "remove", "reload"});
        } else {
            if (astring.length == 2) {
                if (astring[0].equals("remove")) {
                    return a(astring, MinecraftServer.getServer().getPlayerList().getWhitelisted());
                }

                if (astring[0].equals("add")) {
                    return a(astring, MinecraftServer.getServer().getUserCache().a());
                }
            }

            return null;
        }
    }
}
