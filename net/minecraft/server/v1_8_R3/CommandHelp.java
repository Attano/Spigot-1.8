package net.minecraft.server;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommandHelp extends CommandAbstract {

    public CommandHelp() {}

    public String getCommand() {
        return "help";
    }

    public int a() {
        return 0;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.help.usage";
    }

    public List<String> b() {
        return Arrays.asList(new String[] { "?"});
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        List list = this.d(icommandlistener);
        boolean flag = true;
        int i = (list.size() - 1) / 7;
        boolean flag1 = false;

        int j;

        try {
            j = astring.length == 0 ? 0 : a(astring[0], 1, i + 1) - 1;
        } catch (ExceptionInvalidNumber exceptioninvalidnumber) {
            Map map = this.d();
            ICommand icommand = (ICommand) map.get(astring[0]);

            if (icommand != null) {
                throw new ExceptionUsage(icommand.getUsage(icommandlistener), new Object[0]);
            }

            if (MathHelper.a(astring[0], -1) != -1) {
                throw exceptioninvalidnumber;
            }

            throw new ExceptionUnknownCommand();
        }

        int k = Math.min((j + 1) * 7, list.size());
        ChatMessage chatmessage = new ChatMessage("commands.help.header", new Object[] { Integer.valueOf(j + 1), Integer.valueOf(i + 1)});

        chatmessage.getChatModifier().setColor(EnumChatFormat.DARK_GREEN);
        icommandlistener.sendMessage(chatmessage);

        for (int l = j * 7; l < k; ++l) {
            ICommand icommand1 = (ICommand) list.get(l);
            ChatMessage chatmessage1 = new ChatMessage(icommand1.getUsage(icommandlistener), new Object[0]);

            chatmessage1.getChatModifier().setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.SUGGEST_COMMAND, "/" + icommand1.getCommand() + " "));
            icommandlistener.sendMessage(chatmessage1);
        }

        if (j == 0 && icommandlistener instanceof EntityHuman) {
            ChatMessage chatmessage2 = new ChatMessage("commands.help.footer", new Object[0]);

            chatmessage2.getChatModifier().setColor(EnumChatFormat.GREEN);
            icommandlistener.sendMessage(chatmessage2);
        }

    }

    protected List<ICommand> d(ICommandListener icommandlistener) {
        List list = MinecraftServer.getServer().getCommandHandler().a(icommandlistener);

        Collections.sort(list);
        return list;
    }

    protected Map<String, ICommand> d() {
        return MinecraftServer.getServer().getCommandHandler().getCommands();
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        if (astring.length == 1) {
            Set set = this.d().keySet();

            return a(astring, (String[]) set.toArray(new String[set.size()]));
        } else {
            return null;
        }
    }
}
