package net.minecraft.server;

import java.util.List;

public class CommandTestForBlocks extends CommandAbstract {

    public CommandTestForBlocks() {}

    public String getCommand() {
        return "testforblocks";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.compare.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 9) {
            throw new ExceptionUsage("commands.compare.usage", new Object[0]);
        } else {
            icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 0);
            BlockPosition blockposition = a(icommandlistener, astring, 0, false);
            BlockPosition blockposition1 = a(icommandlistener, astring, 3, false);
            BlockPosition blockposition2 = a(icommandlistener, astring, 6, false);
            StructureBoundingBox structureboundingbox = new StructureBoundingBox(blockposition, blockposition1);
            StructureBoundingBox structureboundingbox1 = new StructureBoundingBox(blockposition2, blockposition2.a(structureboundingbox.b()));
            int i = structureboundingbox.c() * structureboundingbox.d() * structureboundingbox.e();

            if (i > 524288) {
                throw new CommandException("commands.compare.tooManyBlocks", new Object[] { Integer.valueOf(i), Integer.valueOf(524288)});
            } else if (structureboundingbox.b >= 0 && structureboundingbox.e < 256 && structureboundingbox1.b >= 0 && structureboundingbox1.e < 256) {
                World world = icommandlistener.getWorld();

                if (world.a(structureboundingbox) && world.a(structureboundingbox1)) {
                    boolean flag = false;

                    if (astring.length > 9 && astring[9].equals("masked")) {
                        flag = true;
                    }

                    i = 0;
                    BlockPosition blockposition3 = new BlockPosition(structureboundingbox1.a - structureboundingbox.a, structureboundingbox1.b - structureboundingbox.b, structureboundingbox1.c - structureboundingbox.c);
                    BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
                    BlockPosition.MutableBlockPosition blockposition_mutableblockposition1 = new BlockPosition.MutableBlockPosition();

                    for (int j = structureboundingbox.c; j <= structureboundingbox.f; ++j) {
                        for (int k = structureboundingbox.b; k <= structureboundingbox.e; ++k) {
                            for (int l = structureboundingbox.a; l <= structureboundingbox.d; ++l) {
                                blockposition_mutableblockposition.c(l, k, j);
                                blockposition_mutableblockposition1.c(l + blockposition3.getX(), k + blockposition3.getY(), j + blockposition3.getZ());
                                boolean flag1 = false;
                                IBlockData iblockdata = world.getType(blockposition_mutableblockposition);

                                if (!flag || iblockdata.getBlock() != Blocks.AIR) {
                                    if (iblockdata == world.getType(blockposition_mutableblockposition1)) {
                                        TileEntity tileentity = world.getTileEntity(blockposition_mutableblockposition);
                                        TileEntity tileentity1 = world.getTileEntity(blockposition_mutableblockposition1);

                                        if (tileentity != null && tileentity1 != null) {
                                            NBTTagCompound nbttagcompound = new NBTTagCompound();

                                            tileentity.b(nbttagcompound);
                                            nbttagcompound.remove("x");
                                            nbttagcompound.remove("y");
                                            nbttagcompound.remove("z");
                                            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                                            tileentity1.b(nbttagcompound1);
                                            nbttagcompound1.remove("x");
                                            nbttagcompound1.remove("y");
                                            nbttagcompound1.remove("z");
                                            if (!nbttagcompound.equals(nbttagcompound1)) {
                                                flag1 = true;
                                            }
                                        } else if (tileentity != null) {
                                            flag1 = true;
                                        }
                                    } else {
                                        flag1 = true;
                                    }

                                    ++i;
                                    if (flag1) {
                                        throw new CommandException("commands.compare.failed", new Object[0]);
                                    }
                                }
                            }
                        }
                    }

                    icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, i);
                    a(icommandlistener, this, "commands.compare.success", new Object[] { Integer.valueOf(i)});
                } else {
                    throw new CommandException("commands.compare.outOfWorld", new Object[0]);
                }
            } else {
                throw new CommandException("commands.compare.outOfWorld", new Object[0]);
            }
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length > 0 && astring.length <= 3 ? a(astring, 0, blockposition) : (astring.length > 3 && astring.length <= 6 ? a(astring, 3, blockposition) : (astring.length > 6 && astring.length <= 9 ? a(astring, 6, blockposition) : (astring.length == 10 ? a(astring, new String[] { "masked", "all"}) : null)));
    }
}
