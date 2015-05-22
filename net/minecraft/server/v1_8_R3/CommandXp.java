package net.minecraft.server;

import java.util.List;

public class CommandXp extends CommandAbstract {

    public CommandXp() {}

    public String getCommand() {
        return "xp";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.xp.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length <= 0) {
            throw new ExceptionUsage("commands.xp.usage", new Object[0]);
        } else {
            String s = astring[0];
            boolean flag = s.endsWith("l") || s.endsWith("L");

            if (flag && s.length() > 1) {
                s = s.substring(0, s.length() - 1);
            }

            int i = a(s);
            boolean flag1 = i < 0;

            if (flag1) {
                i *= -1;
            }

            EntityPlayer entityplayer = astring.length > 1 ? a(icommandlistener, astring[1]) : b(icommandlistener);

            if (flag) {
                icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, entityplayer.expLevel);
                if (flag1) {
                    entityplayer.levelDown(-i);
                    a(icommandlistener, this, "commands.xp.success.negative.levels", new Object[] { Integer.valueOf(i), entityplayer.getName()});
                } else {
                    entityplayer.levelDown(i);
                    a(icommandlistener, this, "commands.xp.success.levels", new Object[] { Integer.valueOf(i), entityplayer.getName()});
                }
            } else {
                icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.QUERY_RESULT, entityplayer.expTotal);
                if (flag1) {
                    throw new CommandException("commands.xp.failure.widthdrawXp", new Object[0]);
                }

                entityplayer.giveExp(i);
                a(icommandlistener, this, "commands.xp.success", new Object[] { Integer.valueOf(i), entityplayer.getName()});
            }

        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 2 ? a(astring, this.d()) : null;
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 1;
    }
}
