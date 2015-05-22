package net.minecraft.server;

import com.google.gson.JsonParseException;
import java.util.List;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class CommandTellRaw extends CommandAbstract {

    public CommandTellRaw() {}

    public String getCommand() {
        return "tellraw";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.tellraw.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 2) {
            throw new ExceptionUsage("commands.tellraw.usage", new Object[0]);
        } else {
            EntityPlayer entityplayer = a(icommandlistener, astring[0]);
            String s = a(astring, 1);

            try {
                IChatBaseComponent ichatbasecomponent = IChatBaseComponent.ChatSerializer.a(s);

                entityplayer.sendMessage(ChatComponentUtils.filterForDisplay(icommandlistener, ichatbasecomponent, entityplayer));
            } catch (JsonParseException jsonparseexception) {
                Throwable throwable = ExceptionUtils.getRootCause(jsonparseexception);

                throw new ExceptionInvalidSyntax("commands.tellraw.jsonException", new Object[] { throwable == null ? "" : throwable.getMessage()});
            }
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 0;
    }
}
