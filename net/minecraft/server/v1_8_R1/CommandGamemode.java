package net.minecraft.server;

import java.util.List;

public class CommandGamemode extends CommandAbstract {

    public CommandGamemode() {}

    public String getCommand() {
        return "gamemode";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.gamemode.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        if (astring.length <= 0) {
            throw new ExceptionUsage("commands.gamemode.usage", new Object[0]);
        } else {
            EnumGamemode enumgamemode = this.h(icommandlistener, astring[0]);
            EntityPlayer entityplayer = astring.length >= 2 ? a(icommandlistener, astring[1]) : b(icommandlistener);

            entityplayer.a(enumgamemode);
            entityplayer.fallDistance = 0.0F;
            if (icommandlistener.getWorld().getGameRules().getBoolean("sendCommandFeedback")) {
                entityplayer.sendMessage(new ChatMessage("gameMode.changed", new Object[0]));
            }

            ChatMessage chatmessage = new ChatMessage("gameMode." + enumgamemode.b(), new Object[0]);

            if (entityplayer != icommandlistener) {
                a(icommandlistener, this, 1, "commands.gamemode.success.other", new Object[] { entityplayer.getName(), chatmessage});
            } else {
                a(icommandlistener, this, 1, "commands.gamemode.success.self", new Object[] { chatmessage});
            }

        }
    }

    protected EnumGamemode h(ICommandListener icommandlistener, String s) {
        return !s.equalsIgnoreCase(EnumGamemode.SURVIVAL.b()) && !s.equalsIgnoreCase("s") ? (!s.equalsIgnoreCase(EnumGamemode.CREATIVE.b()) && !s.equalsIgnoreCase("c") ? (!s.equalsIgnoreCase(EnumGamemode.ADVENTURE.b()) && !s.equalsIgnoreCase("a") ? (!s.equalsIgnoreCase(EnumGamemode.SPECTATOR.b()) && !s.equalsIgnoreCase("sp") ? WorldSettings.a(a(s, 0, EnumGamemode.values().length - 2)) : EnumGamemode.SPECTATOR) : EnumGamemode.ADVENTURE) : EnumGamemode.CREATIVE) : EnumGamemode.SURVIVAL;
    }

    public List tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, new String[] { "survival", "creative", "adventure", "spectator"}) : (astring.length == 2 ? a(astring, this.d()) : null);
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 1;
    }
}
