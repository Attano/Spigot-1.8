package net.minecraft.server;

public class CommandKill extends CommandAbstract {

    public CommandKill() {}

    public String getCommand() {
        return "kill";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.kill.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) {
        if (astring.length == 0) {
            EntityPlayer entityplayer = b(icommandlistener);

            entityplayer.G();
            a(icommandlistener, this, "commands.kill.successful", new Object[] { entityplayer.getScoreboardDisplayName()});
        } else {
            Entity entity = b(icommandlistener, astring[0]);

            entity.G();
            a(icommandlistener, this, "commands.kill.successful", new Object[] { entity.getScoreboardDisplayName()});
        }
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 0;
    }
}
