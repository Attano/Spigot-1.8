package net.minecraft.server;

import java.util.Collection;
import java.util.Iterator;
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

    public void execute(ICommandListener icommandlistener, String[] astring) {
        if (astring.length < 4) {
            throw new ExceptionUsage("commands.testforblock.usage", new Object[0]);
        } else {
            icommandlistener.a(EnumCommandResult.AFFECTED_BLOCKS, 0);
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
                            if (!a(nbttagcompound, nbttagcompound1, true)) {
                                throw new CommandException("commands.testforblock.failed.nbt", new Object[] { Integer.valueOf(blockposition.getX()), Integer.valueOf(blockposition.getY()), Integer.valueOf(blockposition.getZ())});
                            }
                        }

                        icommandlistener.a(EnumCommandResult.AFFECTED_BLOCKS, 1);
                        a(icommandlistener, this, "commands.testforblock.success", new Object[] { Integer.valueOf(blockposition.getX()), Integer.valueOf(blockposition.getY()), Integer.valueOf(blockposition.getZ())});
                    }
                }
            }
        }
    }

    public static boolean a(NBTBase nbtbase, NBTBase nbtbase1, boolean flag) {
        if (nbtbase == nbtbase1) {
            return true;
        } else if (nbtbase == null) {
            return true;
        } else if (nbtbase1 == null) {
            return false;
        } else if (!nbtbase.getClass().equals(nbtbase1.getClass())) {
            return false;
        } else if (nbtbase instanceof NBTTagCompound) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbtbase;
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbtbase1;
            Iterator iterator = nbttagcompound.c().iterator();

            String s;
            NBTBase nbtbase2;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                s = (String) iterator.next();
                nbtbase2 = nbttagcompound.get(s);
            } while (a(nbtbase2, nbttagcompound1.get(s), flag));

            return false;
        } else if (nbtbase instanceof NBTTagList && flag) {
            NBTTagList nbttaglist = (NBTTagList) nbtbase;
            NBTTagList nbttaglist1 = (NBTTagList) nbtbase1;

            if (nbttaglist.size() == 0) {
                return nbttaglist1.size() == 0;
            } else {
                int i = 0;

                while (i < nbttaglist.size()) {
                    NBTBase nbtbase3 = nbttaglist.g(i);
                    boolean flag1 = false;
                    int j = 0;

                    while (true) {
                        if (j < nbttaglist1.size()) {
                            if (!a(nbtbase3, nbttaglist1.g(j), flag)) {
                                ++j;
                                continue;
                            }

                            flag1 = true;
                        }

                        if (!flag1) {
                            return false;
                        }

                        ++i;
                        break;
                    }
                }

                return true;
            }
        } else {
            return nbtbase.equals(nbtbase1);
        }
    }

    public List tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length > 0 && astring.length <= 3 ? a(astring, 0, blockposition) : (astring.length == 4 ? a(astring, (Collection) Block.REGISTRY.keySet()) : null);
    }
}
