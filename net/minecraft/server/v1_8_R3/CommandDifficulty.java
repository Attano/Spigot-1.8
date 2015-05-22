package net.minecraft.server;

import java.util.List;

public class CommandDifficulty extends CommandAbstract {

    public CommandDifficulty() {}

    public String getCommand() {
        return "difficulty";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.difficulty.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length <= 0) {
            throw new ExceptionUsage("commands.difficulty.usage", new Object[0]);
        } else {
            EnumDifficulty enumdifficulty = this.e(astring[0]);

            MinecraftServer.getServer().a(enumdifficulty);
            a(icommandlistener, this, "commands.difficulty.success", new Object[] { new ChatMessage(enumdifficulty.b(), new Object[0])});
        }
    }

    protected EnumDifficulty e(String s) throws ExceptionInvalidNumber {
        return !s.equalsIgnoreCase("peaceful") && !s.equalsIgnoreCase("p") ? (!s.equalsIgnoreCase("easy") && !s.equalsIgnoreCase("e") ? (!s.equalsIgnoreCase("normal") && !s.equalsIgnoreCase("n") ? (!s.equalsIgnoreCase("hard") && !s.equalsIgnoreCase("h") ? EnumDifficulty.getById(a(s, 0, 3)) : EnumDifficulty.HARD) : EnumDifficulty.NORMAL) : EnumDifficulty.EASY) : EnumDifficulty.PEACEFUL;
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, new String[] { "peaceful", "easy", "normal", "hard"}) : null;
    }
}
