package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CommandFill extends CommandAbstract {

    public CommandFill() {}

    public String getCommand() {
        return "fill";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.fill.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 7) {
            throw new ExceptionUsage("commands.fill.usage", new Object[0]);
        } else {
            icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 0);
            BlockPosition blockposition = a(icommandlistener, astring, 0, false);
            BlockPosition blockposition1 = a(icommandlistener, astring, 3, false);
            Block block = CommandAbstract.g(icommandlistener, astring[6]);
            int i = 0;

            if (astring.length >= 8) {
                i = a(astring[7], 0, 15);
            }

            BlockPosition blockposition2 = new BlockPosition(Math.min(blockposition.getX(), blockposition1.getX()), Math.min(blockposition.getY(), blockposition1.getY()), Math.min(blockposition.getZ(), blockposition1.getZ()));
            BlockPosition blockposition3 = new BlockPosition(Math.max(blockposition.getX(), blockposition1.getX()), Math.max(blockposition.getY(), blockposition1.getY()), Math.max(blockposition.getZ(), blockposition1.getZ()));
            int j = (blockposition3.getX() - blockposition2.getX() + 1) * (blockposition3.getY() - blockposition2.getY() + 1) * (blockposition3.getZ() - blockposition2.getZ() + 1);

            if (j > '\u8000') {
                throw new CommandException("commands.fill.tooManyBlocks", new Object[] { Integer.valueOf(j), Integer.valueOf('\u8000')});
            } else if (blockposition2.getY() >= 0 && blockposition3.getY() < 256) {
                World world = icommandlistener.getWorld();

                for (int k = blockposition2.getZ(); k < blockposition3.getZ() + 16; k += 16) {
                    for (int l = blockposition2.getX(); l < blockposition3.getX() + 16; l += 16) {
                        if (!world.isLoaded(new BlockPosition(l, blockposition3.getY() - blockposition2.getY(), k))) {
                            throw new CommandException("commands.fill.outOfWorld", new Object[0]);
                        }
                    }
                }

                NBTTagCompound nbttagcompound = new NBTTagCompound();
                boolean flag = false;

                if (astring.length >= 10 && block.isTileEntity()) {
                    String s = a(icommandlistener, astring, 9).c();

                    try {
                        nbttagcompound = MojangsonParser.parse(s);
                        flag = true;
                    } catch (MojangsonParseException mojangsonparseexception) {
                        throw new CommandException("commands.fill.tagError", new Object[] { mojangsonparseexception.getMessage()});
                    }
                }

                ArrayList arraylist = Lists.newArrayList();

                j = 0;

                for (int i1 = blockposition2.getZ(); i1 <= blockposition3.getZ(); ++i1) {
                    for (int j1 = blockposition2.getY(); j1 <= blockposition3.getY(); ++j1) {
                        for (int k1 = blockposition2.getX(); k1 <= blockposition3.getX(); ++k1) {
                            BlockPosition blockposition4 = new BlockPosition(k1, j1, i1);
                            IBlockData iblockdata;

                            if (astring.length >= 9) {
                                if (!astring[8].equals("outline") && !astring[8].equals("hollow")) {
                                    if (astring[8].equals("destroy")) {
                                        world.setAir(blockposition4, true);
                                    } else if (astring[8].equals("keep")) {
                                        if (!world.isEmpty(blockposition4)) {
                                            continue;
                                        }
                                    } else if (astring[8].equals("replace") && !block.isTileEntity()) {
                                        if (astring.length > 9) {
                                            Block block1 = CommandAbstract.g(icommandlistener, astring[9]);

                                            if (world.getType(blockposition4).getBlock() != block1) {
                                                continue;
                                            }
                                        }

                                        if (astring.length > 10) {
                                            int l1 = CommandAbstract.a(astring[10]);

                                            iblockdata = world.getType(blockposition4);
                                            if (iblockdata.getBlock().toLegacyData(iblockdata) != l1) {
                                                continue;
                                            }
                                        }
                                    }
                                } else if (k1 != blockposition2.getX() && k1 != blockposition3.getX() && j1 != blockposition2.getY() && j1 != blockposition3.getY() && i1 != blockposition2.getZ() && i1 != blockposition3.getZ()) {
                                    if (astring[8].equals("hollow")) {
                                        world.setTypeAndData(blockposition4, Blocks.AIR.getBlockData(), 2);
                                        arraylist.add(blockposition4);
                                    }
                                    continue;
                                }
                            }

                            TileEntity tileentity = world.getTileEntity(blockposition4);

                            if (tileentity != null) {
                                if (tileentity instanceof IInventory) {
                                    ((IInventory) tileentity).l();
                                }

                                world.setTypeAndData(blockposition4, Blocks.BARRIER.getBlockData(), block == Blocks.BARRIER ? 2 : 4);
                            }

                            iblockdata = block.fromLegacyData(i);
                            if (world.setTypeAndData(blockposition4, iblockdata, 2)) {
                                arraylist.add(blockposition4);
                                ++j;
                                if (flag) {
                                    TileEntity tileentity1 = world.getTileEntity(blockposition4);

                                    if (tileentity1 != null) {
                                        nbttagcompound.setInt("x", blockposition4.getX());
                                        nbttagcompound.setInt("y", blockposition4.getY());
                                        nbttagcompound.setInt("z", blockposition4.getZ());
                                        tileentity1.a(nbttagcompound);
                                    }
                                }
                            }
                        }
                    }
                }

                Iterator iterator = arraylist.iterator();

                while (iterator.hasNext()) {
                    BlockPosition blockposition5 = (BlockPosition) iterator.next();
                    Block block2 = world.getType(blockposition5).getBlock();

                    world.update(blockposition5, block2);
                }

                if (j <= 0) {
                    throw new CommandException("commands.fill.failed", new Object[0]);
                } else {
                    icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, j);
                    a(icommandlistener, this, "commands.fill.success", new Object[] { Integer.valueOf(j)});
                }
            } else {
                throw new CommandException("commands.fill.outOfWorld", new Object[0]);
            }
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length > 0 && astring.length <= 3 ? a(astring, 0, blockposition) : (astring.length > 3 && astring.length <= 6 ? a(astring, 3, blockposition) : (astring.length == 7 ? a(astring, (Collection) Block.REGISTRY.keySet()) : (astring.length == 9 ? a(astring, new String[] { "replace", "destroy", "keep", "hollow", "outline"}) : (astring.length == 10 && "replace".equals(astring[8]) ? a(astring, (Collection) Block.REGISTRY.keySet()) : null))));
    }
}
