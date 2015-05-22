package net.minecraft.server;

import java.util.Collection;
import java.util.List;

public class CommandTestForBlock extends CommandAbstract {

    public CommandTestForBlock() {}

    public String getCommand() {
        return "testforblock";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.testforblock.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 4) {
            throw new ExceptionUsage("commands.testforblock.usage", new Object[0]);
        } else {
            icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 0);
            BlockPosition blockposition = a(icommandlistener, astring, 0, false);
            Block block = Block.getByName(astring[3]);

            if (block == null) {
                throw new ExceptionInvalidNumber("commands.setblock.notFound", new Object[] { astring[3]});
            } else {
                int i = -1;

                if (astring.length >= 5) {
                    i = a(astring[4], -1, 15);
                }

                World world = icommandlistener.getWorld();

                if (!world.isLoaded(blockposition)) {
                    throw new CommandException("commands.testforblock.outOfWorld", new Object[0]);
                } else {
                    NBTTagCompound nbttagcompound = new NBTTagCompound();
                    boolean flag = false;

                    if (astring.length >= 6 && block.isTileEntity()) {
                        String s = a(icommandlistener, astring, 5).c();

                        try {
                            nbttagcompound = MojangsonParser.parse(s);
                            flag = true;
                        } catch (MojangsonParseException mojangsonparseexception) {
                            throw new CommandException("commands.setblock.tagError", new Object[] { mojangsonparseexception.getMessage()});
                        }
                    }

                    IBlockData iblockdata = world.getType(blockposition);
                    Block block1 = iblockdata.getBlock();

                    if (block1 != block) {
                        throw new CommandException("commands.testforblock.failed.tile", new Object[] { Integer.valueOf(blockposition.getX()), Integer.valueOf(blockposition.getY()), Integer.valueOf(blockposition.getZ()), block1.getName(), block.getName()});
                    } else {
                        if (i > -1) {
                            int j = iblockdata.getBlock().toLegacyData(iblockdata);

                            if (j != i) {
                                throw new CommandException("commands.testforblock.failed.data", new Object[] { Integer.valueOf(blockposition.getX()), Integer.valueOf(blockposition.getY()), Integer.valueOf(blockposition.getZ()), Integer.valueOf(j), Integer.valueOf(i)});
                            }
                        }

                        if (flag) {
                            TileEntity tileentity = world.getTileEntity(blockposition);

                            if (tileentity == null) {
                                throw new CommandException("commands.testforblock.failed.tileEntity", new Object[] { Integer.valueOf(blockposition.getX()), Integer.valueOf(blockposition.getY()), Integer.valueOf(blockposition.getZ())});
                            }

                            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                            tileentity.b(nbttagcompound1);
                            if (!GameProfileSerializer.a(nbttagcompound, nbttagcompound1, true)) {
                                throw new CommandException("commands.testforblock.failed.nbt", new Object[] { Integer.valueOf(blockposition.getX()), Integer.valueOf(blockposition.getY()), Integer.valueOf(blockposition.getZ())});
                            }
                        }

                        icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 1);
                        a(icommandlistener, this, "commands.testforblock.success", new Object[] { Integer.valueOf(blockposition.getX()), Integer.valueOf(blockposition.getY()), Integer.valueOf(blockposition.getZ())});
                    }
                }
            }
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length > 0 && astring.length <= 3 ? a(astring, 0, blockposition) : (astring.length == 4 ? a(astring, (Collection) Block.REGISTRY.keySet()) : null);
    }
}
