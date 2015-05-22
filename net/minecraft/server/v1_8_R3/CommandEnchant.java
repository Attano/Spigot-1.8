package net.minecraft.server;

import java.util.Collection;
import java.util.List;

public class CommandEnchant extends CommandAbstract {

    public CommandEnchant() {}

    public String getCommand() {
        return "enchant";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.enchant.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 2) {
            throw new ExceptionUsage("commands.enchant.usage", new Object[0]);
        } else {
            EntityPlayer entityplayer = a(icommandlistener, astring[0]);

            icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ITEMS, 0);

            int i;

            try {
                i = a(astring[1], 0);
            } catch (ExceptionInvalidNumber exceptioninvalidnumber) {
                Enchantment enchantment = Enchantment.getByName(astring[1]);

                if (enchantment == null) {
                    throw exceptioninvalidnumber;
                }

                i = enchantment.id;
            }

            int j = 1;
            ItemStack itemstack = entityplayer.bZ();

            if (itemstack == null) {
                throw new CommandException("commands.enchant.noItem", new Object[0]);
            } else {
                Enchantment enchantment1 = Enchantment.getById(i);

                if (enchantment1 == null) {
                    throw new ExceptionInvalidNumber("commands.enchant.notFound", new Object[] { Integer.valueOf(i)});
                } else if (!enchantment1.canEnchant(itemstack)) {
                    throw new CommandException("commands.enchant.cantEnchant", new Object[0]);
                } else {
                    if (astring.length >= 3) {
                        j = a(astring[2], enchantment1.getStartLevel(), enchantment1.getMaxLevel());
                    }

                    if (itemstack.hasTag()) {
                        NBTTagList nbttaglist = itemstack.getEnchantments();

                        if (nbttaglist != null) {
                            for (int k = 0; k < nbttaglist.size(); ++k) {
                                short short0 = nbttaglist.get(k).getShort("id");

                                if (Enchantment.getById(short0) != null) {
                                    Enchantment enchantment2 = Enchantment.getById(short0);

                                    if (!enchantment2.a(enchantment1)) {
                                        throw new CommandException("commands.enchant.cantCombine", new Object[] { enchantment1.d(j), enchantment2.d(nbttaglist.get(k).getShort("lvl"))});
                                    }
                                }
                            }
                        }
                    }

                    itemstack.addEnchantment(enchantment1, j);
                    a(icommandlistener, this, "commands.enchant.success", new Object[0]);
                    icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_ITEMS, 1);
                }
            }
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, this.d()) : (astring.length == 2 ? a(astring, (Collection) Enchantment.getEffects()) : null);
    }

    protected String[] d() {
        return MinecraftServer.getServer().getPlayers();
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 0;
    }
}
