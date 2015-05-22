package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CommandClone extends CommandAbstract {

    public CommandClone() {}

    public String getCommand() {
        return "clone";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.clone.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 9) {
            throw new ExceptionUsage("commands.clone.usage", new Object[0]);
        } else {
            icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, 0);
            BlockPosition blockposition = a(icommandlistener, astring, 0, false);
            BlockPosition blockposition1 = a(icommandlistener, astring, 3, false);
            BlockPosition blockposition2 = a(icommandlistener, astring, 6, false);
            StructureBoundingBox structureboundingbox = new StructureBoundingBox(blockposition, blockposition1);
            StructureBoundingBox structureboundingbox1 = new StructureBoundingBox(blockposition2, blockposition2.a(structureboundingbox.b()));
            int i = structureboundingbox.c() * structureboundingbox.d() * structureboundingbox.e();

            if (i > '\u8000') {
                throw new CommandException("commands.clone.tooManyBlocks", new Object[] { Integer.valueOf(i), Integer.valueOf('\u8000')});
            } else {
                boolean flag = false;
                Block block = null;
                int j = -1;

                if ((astring.length < 11 || !astring[10].equals("force") && !astring[10].equals("move")) && structureboundingbox.a(structureboundingbox1)) {
                    throw new CommandException("commands.clone.noOverlap", new Object[0]);
                } else {
                    if (astring.length >= 11 && astring[10].equals("move")) {
                        flag = true;
                    }

                    if (structureboundingbox.b >= 0 && structureboundingbox.e < 256 && structureboundingbox1.b >= 0 && structureboundingbox1.e < 256) {
                        World world = icommandlistener.getWorld();

                        if (world.a(structureboundingbox) && world.a(structureboundingbox1)) {
                            boolean flag1 = false;

                            if (astring.length >= 10) {
                                if (astring[9].equals("masked")) {
                                    flag1 = true;
                                } else if (astring[9].equals("filtered")) {
                                    if (astring.length < 12) {
                                        throw new ExceptionUsage("commands.clone.usage", new Object[0]);
                                    }

                                    block = g(icommandlistener, astring[11]);
                                    if (astring.length >= 13) {
                                        j = a(astring[12], 0, 15);
                                    }
                                }
                            }

                            ArrayList arraylist = Lists.newArrayList();
                            ArrayList arraylist1 = Lists.newArrayList();
                            ArrayList arraylist2 = Lists.newArrayList();
                            LinkedList linkedlist = Lists.newLinkedList();
                            BlockPosition blockposition3 = new BlockPosition(structureboundingbox1.a - structureboundingbox.a, structureboundingbox1.b - structureboundingbox.b, structureboundingbox1.c - structureboundingbox.c);

                            for (int k = structureboundingbox.c; k <= structureboundingbox.f; ++k) {
                                for (int l = structureboundingbox.b; l <= structureboundingbox.e; ++l) {
                                    for (int i1 = structureboundingbox.a; i1 <= structureboundingbox.d; ++i1) {
                                        BlockPosition blockposition4 = new BlockPosition(i1, l, k);
                                        BlockPosition blockposition5 = blockposition4.a(blockposition3);
                                        IBlockData iblockdata = world.getType(blockposition4);

                                        if ((!flag1 || iblockdata.getBlock() != Blocks.AIR) && (block == null || iblockdata.getBlock() == block && (j < 0 || iblockdata.getBlock().toLegacyData(iblockdata) == j))) {
                                            TileEntity tileentity = world.getTileEntity(blockposition4);

                                            if (tileentity != null) {
                                                NBTTagCompound nbttagcompound = new NBTTagCompound();

                                                tileentity.b(nbttagcompound);
                                                arraylist1.add(new CommandClone.CommandCloneStoredTileEntity(blockposition5, iblockdata, nbttagcompound));
                                                linkedlist.addLast(blockposition4);
                                            } else if (!iblockdata.getBlock().o() && !iblockdata.getBlock().d()) {
                                                arraylist2.add(new CommandClone.CommandCloneStoredTileEntity(blockposition5, iblockdata, (NBTTagCompound) null));
                                                linkedlist.addFirst(blockposition4);
                                            } else {
                                                arraylist.add(new CommandClone.CommandCloneStoredTileEntity(blockposition5, iblockdata, (NBTTagCompound) null));
                                                linkedlist.addLast(blockposition4);
                                            }
                                        }
                                    }
                                }
                            }

                            if (flag) {
                                Iterator iterator;
                                BlockPosition blockposition6;

                                for (iterator = linkedlist.iterator(); iterator.hasNext(); world.setTypeAndData(blockposition6, Blocks.BARRIER.getBlockData(), 2)) {
                                    blockposition6 = (BlockPosition) iterator.next();
                                    TileEntity tileentity1 = world.getTileEntity(blockposition6);

                                    if (tileentity1 instanceof IInventory) {
                                        ((IInventory) tileentity1).l();
                                    }
                                }

                                iterator = linkedlist.iterator();

                                while (iterator.hasNext()) {
                                    blockposition6 = (BlockPosition) iterator.next();
                                    world.setTypeAndData(blockposition6, Blocks.AIR.getBlockData(), 3);
                                }
                            }

                            ArrayList arraylist3 = Lists.newArrayList();

                            arraylist3.addAll(arraylist);
                            arraylist3.addAll(arraylist1);
                            arraylist3.addAll(arraylist2);
                            List list = Lists.reverse(arraylist3);

                            Iterator iterator1;
                            CommandClone.CommandCloneStoredTileEntity commandclone_commandclonestoredtileentity;
                            TileEntity tileentity2;

                            for (iterator1 = list.iterator(); iterator1.hasNext(); world.setTypeAndData(commandclone_commandclonestoredtileentity.a, Blocks.BARRIER.getBlockData(), 2)) {
                                commandclone_commandclonestoredtileentity = (CommandClone.CommandCloneStoredTileEntity) iterator1.next();
                                tileentity2 = world.getTileEntity(commandclone_commandclonestoredtileentity.a);
                                if (tileentity2 instanceof IInventory) {
                                    ((IInventory) tileentity2).l();
                                }
                            }

                            i = 0;
                            iterator1 = arraylist3.iterator();

                            while (iterator1.hasNext()) {
                                commandclone_commandclonestoredtileentity = (CommandClone.CommandCloneStoredTileEntity) iterator1.next();
                                if (world.setTypeAndData(commandclone_commandclonestoredtileentity.a, commandclone_commandclonestoredtileentity.b, 2)) {
                                    ++i;
                                }
                            }

                            for (iterator1 = arraylist1.iterator(); iterator1.hasNext(); world.setTypeAndData(commandclone_commandclonestoredtileentity.a, commandclone_commandclonestoredtileentity.b, 2)) {
                                commandclone_commandclonestoredtileentity = (CommandClone.CommandCloneStoredTileEntity) iterator1.next();
                                tileentity2 = world.getTileEntity(commandclone_commandclonestoredtileentity.a);
                                if (commandclone_commandclonestoredtileentity.c != null && tileentity2 != null) {
                                    commandclone_commandclonestoredtileentity.c.setInt("x", commandclone_commandclonestoredtileentity.a.getX());
                                    commandclone_commandclonestoredtileentity.c.setInt("y", commandclone_commandclonestoredtileentity.a.getY());
                                    commandclone_commandclonestoredtileentity.c.setInt("z", commandclone_commandclonestoredtileentity.a.getZ());
                                    tileentity2.a(commandclone_commandclonestoredtileentity.c);
                                    tileentity2.update();
                                }
                            }

                            iterator1 = list.iterator();

                            while (iterator1.hasNext()) {
                                commandclone_commandclonestoredtileentity = (CommandClone.CommandCloneStoredTileEntity) iterator1.next();
                                world.update(commandclone_commandclonestoredtileentity.a, commandclone_commandclonestoredtileentity.b.getBlock());
                            }

                            List list1 = world.a(structureboundingbox, false);

                            if (list1 != null) {
                                Iterator iterator2 = list1.iterator();

                                while (iterator2.hasNext()) {
                                    NextTickListEntry nextticklistentry = (NextTickListEntry) iterator2.next();

                                    if (structureboundingbox.b((BaseBlockPosition) nextticklistentry.a)) {
                                        BlockPosition blockposition7 = nextticklistentry.a.a(blockposition3);

                                        world.b(blockposition7, nextticklistentry.a(), (int) (nextticklistentry.b - world.getWorldData().getTime()), nextticklistentry.c);
                                    }
                                }
                            }

                            if (i <= 0) {
                                throw new CommandException("commands.clone.failed", new Object[0]);
                            } else {
                                icommandlistener.a(CommandObjectiveExecutor.EnumCommandResult.AFFECTED_BLOCKS, i);
                                a(icommandlistener, this, "commands.clone.success", new Object[] { Integer.valueOf(i)});
                            }
                        } else {
                            throw new CommandException("commands.clone.outOfWorld", new Object[0]);
                        }
                    } else {
                        throw new CommandException("commands.clone.outOfWorld", new Object[0]);
                    }
                }
            }
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length > 0 && astring.length <= 3 ? a(astring, 0, blockposition) : (astring.length > 3 && astring.length <= 6 ? a(astring, 3, blockposition) : (astring.length > 6 && astring.length <= 9 ? a(astring, 6, blockposition) : (astring.length == 10 ? a(astring, new String[] { "replace", "masked", "filtered"}) : (astring.length == 11 ? a(astring, new String[] { "normal", "force", "move"}) : (astring.length == 12 && "filtered".equals(astring[9]) ? a(astring, (Collection) Block.REGISTRY.keySet()) : null)))));
    }

    static class CommandCloneStoredTileEntity {

        public final BlockPosition a;
        public final IBlockData b;
        public final NBTTagCompound c;

        public CommandCloneStoredTileEntity(BlockPosition blockposition, IBlockData iblockdata, NBTTagCompound nbttagcompound) {
            this.a = blockposition;
            this.b = iblockdata;
            this.c = nbttagcompound;
        }
    }
}
