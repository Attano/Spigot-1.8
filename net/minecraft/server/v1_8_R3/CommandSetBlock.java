package net.minecraft.server;

import java.util.Collection;
import java.util.List;

public class CommandSetBlock extends CommandAbstract {

    public CommandSetBlock() {}

    public String getCommand() {
        return "setblock";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.setblock.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 4) {
            throw new ExceptionUsage("commands.setblock.usage", new Object[0]);
        } else {
            icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 0);
            BlockPosition blockposition = a(icommandlistener, astring, 0, false);
            Block block = CommandAbstract.g(icommandlistener, astring[3]);
            int i = 0;

            if (astring.length >= 5) {
                i = a(astring[4], 0, 15);
            }

            World world = icommandlistener.getWorld();

            if (!world.isLoaded(blockposition)) {
                throw new CommandException("commands.setblock.outOfWorld", new Object[0]);
            } else {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                boolean flag = false;

                if (astring.length >= 7 && block.isTileEntity()) {
                    String s = a(icommandlistener, astring, 6).c();

                    try {
                        nbttagcompound = MojangsonParser.parse(s);
                        flag = true;
                    } catch (MojangsonParseException mojangsonparseexception) {
                        throw new CommandException("commands.setblock.tagError", new Object[] { mojangsonparseexception.getMessage()});
                    }
                }

                if (astring.length >= 6) {
                    if (astring[5].equals("destroy")) {
                        world.setAir(blockposition, true);
                        if (block == Blocks.AIR) {
                            a(icommandlistener, this, "commands.setblock.success", new Object[0]);
                            return;
                        }
                    } else if (astring[5].equals("keep") && !world.isEmpty(blockposition)) {
                        throw new CommandException("commands.setblock.noChange", new Object[0]);
                    }
                }

                TileEntity tileentity = world.getTileEntity(blockposition);

                if (tileentity != null) {
                    if (tileentity instanceof IInventory) {
                        ((IInventory) tileentity).l();
                    }

                    world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), block == Blocks.AIR ? 2 : 4);
                }

                IBlockData iblockdata = block.fromLegacyData(i);

                if (!world.setTypeAndData(blockposition, iblockdata, 2)) {
                    throw new CommandException("commands.setblock.noChange", new Object[0]);
                } else {
                    if (flag) {
                        TileEntity tileentity1 = world.getTileEntity(blockposition);

                        if (tileentity1 != null) {
                            nbttagcompound.setInt("x", blockposition.getX());
                            nbttagcompound.setInt("y", blockposition.getY());
                            nbttagcompound.setInt("z", blockposition.getZ());
                            tileentity1.a(nbttagcompound);
                        }
                    }

                    world.update(blockposition, iblockdata.getBlock());
                    icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 1);
                    a(icommandlistener, this, "commands.setblock.success", new Object[0]);
                }
            }
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length > 0 && astring.length <= 3 ? a(astring, 0, blockposition) : (astring.length == 4 ? a(astring, (Collection) Block.REGISTRY.keySet()) : (astring.length == 6 ? a(astring, new String[] { "replace", "destroy", "keep"}) : null));
    }
}
