package net.minecraft.server;

import java.util.Collection;
import java.util.List;

public class CommandGive extends CommandAbstract {

    public CommandGive() {}

    public String getCommand() {
        return "give";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.give.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 2) {
            throw new ExceptionUsage("commands.give.usage", new Object[0]);
        } else {
            EntityPlayer entityplayer = a(icommandlistener, astring[0]);
            Item item = f(icommandlistener, astring[1]);
            int i = astring.length >= 3 ? a(astring[2], 1, 64) : 1;
            int j = astring.length >= 4 ? a(astring[3]) : 0;
            ItemStack itemstack = new ItemStack(item, i, j);

            if (astring.length >= 5) {
                String s = a(icommandlistener, astring, 4).c();

                try {
                    itemstack.setTag(MojangsonParser.parse(s));
                } catch (MojangsonParseException mojangsonparseexception) {
                    throw new CommandException("commands.give.tagError", new Object[] { mojangsonparseexception.getMessage()});
                }
            }

            boolean flag = entityplayer.inventory.pickup(itemstack);

            if (flag) {
                entityplayer.world.makeSound(entityplayer, "random.pop", 0.2F, ((entityplayer.bc().nextFloat() - entityplayer.bc().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                entityplayer.defaultContainer.b();
            }

            EntityItem entityitem;

            if (flag && itemstack.count <= 0) {
                itemstack.count = 1;
                icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ITEMS, i);
                entityitem = entityplayer.drop(itemstack, false);
                if (entityitem != null) {
                    entityitem.v();
                }
            } else {
                icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ITEMS, i - itemstack.count);
                entityitem = entityplayer.drop(itemstack, false);
                if (entityitem != null) {
                    entityitem.q();
                    entityitem.b(entityplayer.getName());
                }
            }

            a(icommandlistener, this, "commands.give.success", new Object[] { itemstack.C(), Integer.valueOf(i), entityplayer.getName()});
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, this.d()) : (astring.length == 2 ? a(astring, (Collection) Item.REGISTRY.keySet()) : null);
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 0;
    }
}
