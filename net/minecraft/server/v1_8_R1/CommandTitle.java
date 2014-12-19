package net.minecraft.server;

import com.google.gson.JsonParseException;
import java.util.List;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandTitle extends CommandAbstract {

    private static final Logger a = LogManager.getLogger();

    public CommandTitle() {}

    public String getCommand() {
        return "title";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.title.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 2) {
            throw new ExceptionUsage("commands.title.usage", new Object[0]);
        } else {
            if (astring.length < 3) {
                if ("title".equals(astring[1]) || "subtitle".equals(astring[1])) {
                    throw new ExceptionUsage("commands.title.usage.title", new Object[0]);
                }

                if ("times".equals(astring[1])) {
                    throw new ExceptionUsage("commands.title.usage.times", new Object[0]);
                }
            }

            EntityPlayer entityplayer = a(icommandlistener, astring[0]);
            EnumTitleAction enumtitleaction = EnumTitleAction.a(astring[1]);

            if (enumtitleaction != EnumTitleAction.CLEAR && enumtitleaction != EnumTitleAction.RESET) {
                if (enumtitleaction == EnumTitleAction.TIMES) {
                    if (astring.length != 5) {
                        throw new ExceptionUsage("commands.title.usage", new Object[0]);
                    } else {
                        int i = a(astring[2]);
                        int j = a(astring[3]);
                        int k = a(astring[4]);
                        PacketPlayOutTitle packetplayouttitle = new PacketPlayOutTitle(i, j, k);

                        entityplayer.playerConnection.sendPacket(packetplayouttitle);
                        a(icommandlistener, this, "commands.title.success", new Object[0]);
                    }
                } else if (astring.length < 3) {
                    throw new ExceptionUsage("commands.title.usage", new Object[0]);
                } else {
                    String s = a(astring, 2);

                    IChatBaseComponent ichatbasecomponent;

                    try {
                        ichatbasecomponent = ChatSerializer.a(s);
                    } catch (JsonParseException jsonparseexception) {
                        Throwable throwable = ExceptionUtils.getRootCause(jsonparseexception);

                        throw new ExceptionInvalidSyntax("commands.tellraw.jsonException", new Object[] { throwable == null ? "" : throwable.getMessage()});
                    }

                    PacketPlayOutTitle packetplayouttitle1 = new PacketPlayOutTitle(enumtitleaction, ChatComponentUtils.filterForDisplay(icommandlistener, ichatbasecomponent, entityplayer));

                    entityplayer.playerConnection.sendPacket(packetplayouttitle1);
                    a(icommandlistener, this, "commands.title.success", new Object[0]);
                }
            } else if (astring.length != 2) {
                throw new ExceptionUsage("commands.title.usage", new Object[0]);
            } else {
                PacketPlayOutTitle packetplayouttitle2 = new PacketPlayOutTitle(enumtitleaction, (IChatBaseComponent) null);

                entityplayer.playerConnection.sendPacket(packetplayouttitle2);
                a(icommandlistener, this, "commands.title.success", new Object[0]);
            }
        }
    }

    public List tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : (astring.length == 2 ? a(astring, EnumTitleAction.a()) : null);
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 0;
    }
}
