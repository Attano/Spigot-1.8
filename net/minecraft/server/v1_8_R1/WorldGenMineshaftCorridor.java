package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenMineshaftCorridor extends StructurePiece {

    private boolean a;
    private boolean b;
    private boolean c;
    private int d;

    public WorldGenMineshaftCorridor() {}

    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.setBoolean("hr", this.a);
        nbttagcompound.setBoolean("sc", this.b);
        nbttagcompound.setBoolean("hps", this.c);
        nbttagcompound.setInt("Num", this.d);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        this.a = nbttagcompound.getBoolean("hr");
        this.b = nbttagcompound.getBoolean("sc");
        this.c = nbttagcompound.getBoolean("hps");
        this.d = nbttagcompound.getInt("Num");
    }

    public WorldGenMineshaftCorridor(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(i);
        this.m = enumdirection;
        this.l = structureboundingbox;
        this.a = random.nextInt(3) == 0;
        this.b = !this.a && random.nextInt(23) == 0;
        if (this.m != EnumDirection.NORTH && this.m != EnumDirection.SOUTH) {
            this.d = structureboundingbox.c() / 5;
        } else {
            this.d = structureboundingbox.e() / 5;
        }

    }

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, EnumDirection enumdirection) {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j, k, i, j + 2, k);

        int l;

        for (l = random.nextInt(3) + 2; l > 0; --l) {
            int i1 = l * 5;

            switch (SwitchHelperDirection7.a[enumdirection.ordinal()]) {
            case 1:
                structureboundingbox.d = i + 2;
                structureboundingbox.c = k - (i1 - 1);
                break;

            case 2:
                structureboundingbox.d = i + 2;
                structureboundingbox.f = k + (i1 - 1);
                break;

            case 3:
                structureboundingbox.a = i - (i1 - 1);
                structureboundingbox.f = k + 2;
                break;

            case 4:
                structureboundingbox.d = i + (i1 - 1);
                structureboundingbox.f = k + 2;
            }

            if (StructurePiece.a(list, structureboundingbox) == null) {
                break;
            }
        }

        return l > 0 ? structureboundingbox : null;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        int i = this.d();
        int j = random.nextInt(4);

        if (this.m != null) {
            switch (SwitchHelperDirection7.a[this.m.ordinal()]) {
            case 1:
                if (j <= 1) {
                    WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a, this.l.b - 1 + random.nextInt(3), this.l.c - 1, this.m, i);
                } else if (j == 2) {
                    WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a - 1, this.l.b - 1 + random.nextInt(3), this.l.c, EnumDirection.WEST, i);
                } else {
                    WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.d + 1, this.l.b - 1 + random.nextInt(3), this.l.c, EnumDirection.EAST, i);
                }
                break;

            case 2:
                if (j <= 1) {
                    WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a, this.l.b - 1 + random.nextInt(3), this.l.f + 1, this.m, i);
                } else if (j == 2) {
                    WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a - 1, this.l.b - 1 + random.nextInt(3), this.l.f - 3, EnumDirection.WEST, i);
                } else {
                    WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.d + 1, this.l.b - 1 + random.nextInt(3), this.l.f - 3, EnumDirection.EAST, i);
                }
                break;

            case 3:
                if (j <= 1) {
                    WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a - 1, this.l.b - 1 + random.nextInt(3), this.l.c, this.m, i);
                } else if (j == 2) {
                    WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a, this.l.b - 1 + random.nextInt(3), this.l.c - 1, EnumDirection.NORTH, i);
                } else {
                    WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a, this.l.b - 1 + random.nextInt(3), this.l.f + 1, EnumDirection.SOUTH, i);
                }
                break;

            case 4:
                if (j <= 1) {
                    WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.d + 1, this.l.b - 1 + random.nextInt(3), this.l.c, this.m, i);
                } else if (j == 2) {
                    WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.d - 3, this.l.b - 1 + random.nextInt(3), this.l.c - 1, EnumDirection.NORTH, i);
                } else {
                    WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.d - 3, this.l.b - 1 + random.nextInt(3), this.l.f + 1, EnumDirection.SOUTH, i);
                }
            }
        }

        if (i < 8) {
            int k;
            int l;

            if (this.m != EnumDirection.NORTH && this.m != EnumDirection.SOUTH) {
                for (k = this.l.a + 3; k + 3 <= this.l.d; k += 5) {
                    l = random.nextInt(5);
                    if (l == 0) {
                        WorldGenMineshaftPieces.a(structurepiece, list, random, k, this.l.b, this.l.c - 1, EnumDirection.NORTH, i + 1);
                    } else if (l == 1) {
                        WorldGenMineshaftPieces.a(structurepiece, list, random, k, this.l.b, this.l.f + 1, EnumDirection.SOUTH, i + 1);
                    }
                }
            } else {
                for (k = this.l.c + 3; k + 3 <= this.l.f; k += 5) {
                    l = random.nextInt(5);
                    if (l == 0) {
                        WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a - 1, this.l.b, k, EnumDirection.WEST, i + 1);
                    } else if (l == 1) {
                        WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.d + 1, this.l.b, k, EnumDirection.EAST, i + 1);
                    }
                }
            }
        }

    }

    protected boolean a(World world, StructureBoundingBox structureboundingbox, Random random, int i, int j, int k, List list, int l) {
        BlockPosition blockposition = new BlockPosition(this.a(i, k), this.d(j), this.b(i, k));

        if (structureboundingbox.b((BaseBlockPosition) blockposition) && world.getType(blockposition).getBlock().getMaterial() == Material.AIR) {
            int i1 = random.nextBoolean() ? 1 : 0;

            world.setTypeAndData(blockposition, Blocks.RAIL.fromLegacyData(this.a(Blocks.RAIL, i1)), 2);
            EntityMinecartChest entityminecartchest = new EntityMinecartChest(world, (double) ((float) blockposition.getX() + 0.5F), (double) ((float) blockposition.getY() + 0.5F), (double) ((float) blockposition.getZ() + 0.5F));

            StructurePieceTreasure.a(random, list, (IInventory) entityminecartchest, l);
            world.addEntity(entityminecartchest);
            return true;
        } else {
            return false;
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            boolean flag = false;
            boolean flag1 = true;
            boolean flag2 = false;
            boolean flag3 = true;
            int i = this.d * 5 - 1;

            this.a(world, structureboundingbox, 0, 0, 0, 2, 1, i, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, random, 0.8F, 0, 2, 0, 2, 2, i, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            if (this.b) {
                this.a(world, structureboundingbox, random, 0.6F, 0, 0, 0, 2, 1, i, Blocks.WEB.getBlockData(), Blocks.AIR.getBlockData(), false);
            }

            int j;
            int k;

            for (j = 0; j < this.d; ++j) {
                k = 2 + j * 5;
                this.a(world, structureboundingbox, 0, 0, k, 0, 1, k, Blocks.FENCE.getBlockData(), Blocks.AIR.getBlockData(), false);
                this.a(world, structureboundingbox, 2, 0, k, 2, 1, k, Blocks.FENCE.getBlockData(), Blocks.AIR.getBlockData(), false);
                if (random.nextInt(4) == 0) {
                    this.a(world, structureboundingbox, 0, 2, k, 0, 2, k, Blocks.PLANKS.getBlockData(), Blocks.AIR.getBlockData(), false);
                    this.a(world, structureboundingbox, 2, 2, k, 2, 2, k, Blocks.PLANKS.getBlockData(), Blocks.AIR.getBlockData(), false);
                } else {
                    this.a(world, structureboundingbox, 0, 2, k, 2, 2, k, Blocks.PLANKS.getBlockData(), Blocks.AIR.getBlockData(), false);
                }

                this.a(world, structureboundingbox, random, 0.1F, 0, 2, k - 1, Blocks.WEB.getBlockData());
                this.a(world, structureboundingbox, random, 0.1F, 2, 2, k - 1, Blocks.WEB.getBlockData());
                this.a(world, structureboundingbox, random, 0.1F, 0, 2, k + 1, Blocks.WEB.getBlockData());
                this.a(world, structureboundingbox, random, 0.1F, 2, 2, k + 1, Blocks.WEB.getBlockData());
                this.a(world, structureboundingbox, random, 0.05F, 0, 2, k - 2, Blocks.WEB.getBlockData());
                this.a(world, structureboundingbox, random, 0.05F, 2, 2, k - 2, Blocks.WEB.getBlockData());
                this.a(world, structureboundingbox, random, 0.05F, 0, 2, k + 2, Blocks.WEB.getBlockData());
                this.a(world, structureboundingbox, random, 0.05F, 2, 2, k + 2, Blocks.WEB.getBlockData());
                this.a(world, structureboundingbox, random, 0.05F, 1, 2, k - 1, Blocks.TORCH.fromLegacyData(EnumDirection.UP.a()));
                this.a(world, structureboundingbox, random, 0.05F, 1, 2, k + 1, Blocks.TORCH.fromLegacyData(EnumDirection.UP.a()));
                if (random.nextInt(100) == 0) {
                    this.a(world, structureboundingbox, random, 2, 0, k - 1, StructurePieceTreasure.a(WorldGenMineshaftPieces.b(), new StructurePieceTreasure[] { Items.ENCHANTED_BOOK.b(random)}), 3 + random.nextInt(4));
                }

                if (random.nextInt(100) == 0) {
                    this.a(world, structureboundingbox, random, 0, 0, k + 1, StructurePieceTreasure.a(WorldGenMineshaftPieces.b(), new StructurePieceTreasure[] { Items.ENCHANTED_BOOK.b(random)}), 3 + random.nextInt(4));
                }

                if (this.b && !this.c) {
                    int l = this.d(0);
                    int i1 = k - 1 + random.nextInt(3);
                    int j1 = this.a(1, i1);

                    i1 = this.b(1, i1);
                    BlockPosition blockposition = new BlockPosition(j1, l, i1);

                    if (structureboundingbox.b((BaseBlockPosition) blockposition)) {
                        this.c = true;
                        world.setTypeAndData(blockposition, Blocks.MOB_SPAWNER.getBlockData(), 2);
                        TileEntity tileentity = world.getTileEntity(blockposition);

                        if (tileentity instanceof TileEntityMobSpawner) {
                            ((TileEntityMobSpawner) tileentity).getSpawner().setMobName("CaveSpider");
                        }
                    }
                }
            }

            for (j = 0; j <= 2; ++j) {
                for (k = 0; k <= i; ++k) {
                    byte b0 = -1;
                    IBlockData iblockdata = this.a(world, j, b0, k, structureboundingbox);

                    if (iblockdata.getBlock().getMaterial() == Material.AIR) {
                        byte b1 = -1;

                        this.a(world, Blocks.PLANKS.getBlockData(), j, b1, k, structureboundingbox);
                    }
                }
            }

            if (this.a) {
                for (j = 0; j <= i; ++j) {
                    IBlockData iblockdata1 = this.a(world, 1, -1, j, structureboundingbox);

                    if (iblockdata1.getBlock().getMaterial() != Material.AIR && iblockdata1.getBlock().m()) {
                        this.a(world, structureboundingbox, random, 0.7F, 1, 0, j, Blocks.RAIL.fromLegacyData(this.a(Blocks.RAIL, 0)));
                    }
                }
            }

            return true;
        }
    }
}
