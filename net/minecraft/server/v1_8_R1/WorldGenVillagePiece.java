package net.minecraft.server;

import java.util.List;
import java.util.Random;

abstract class WorldGenVillagePiece extends StructurePiece {

    protected int h = -1;
    private int a;
    private boolean b;

    public WorldGenVillagePiece() {}

    protected WorldGenVillagePiece(WorldGenVillageStartPiece worldgenvillagestartpiece, int i) {
        super(i);
        if (worldgenvillagestartpiece != null) {
            this.b = worldgenvillagestartpiece.b;
        }

    }

    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.setInt("HPos", this.h);
        nbttagcompound.setInt("VCount", this.a);
        nbttagcompound.setBoolean("Desert", this.b);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        this.h = nbttagcompound.getInt("HPos");
        this.a = nbttagcompound.getInt("VCount");
        this.b = nbttagcompound.getBoolean("Desert");
    }

    protected StructurePiece a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j) {
        if (this.m != null) {
            switch (SwitchHelperDirection3.a[this.m.ordinal()]) {
            case 1:
                return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.l.a - 1, this.l.b + i, this.l.c + j, EnumDirection.WEST, this.d());

            case 2:
                return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.l.a - 1, this.l.b + i, this.l.c + j, EnumDirection.WEST, this.d());

            case 3:
                return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.l.a + j, this.l.b + i, this.l.c - 1, EnumDirection.NORTH, this.d());

            case 4:
                return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.l.a + j, this.l.b + i, this.l.c - 1, EnumDirection.NORTH, this.d());
            }
        }

        return null;
    }

    protected StructurePiece b(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j) {
        if (this.m != null) {
            switch (SwitchHelperDirection3.a[this.m.ordinal()]) {
            case 1:
                return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.l.d + 1, this.l.b + i, this.l.c + j, EnumDirection.EAST, this.d());

            case 2:
                return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.l.d + 1, this.l.b + i, this.l.c + j, EnumDirection.EAST, this.d());

            case 3:
                return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.l.a + j, this.l.b + i, this.l.f + 1, EnumDirection.SOUTH, this.d());

            case 4:
                return WorldGenVillagePieces.a(worldgenvillagestartpiece, list, random, this.l.a + j, this.l.b + i, this.l.f + 1, EnumDirection.SOUTH, this.d());
            }
        }

        return null;
    }

    protected int b(World world, StructureBoundingBox structureboundingbox) {
        int i = 0;
        int j = 0;

        for (int k = this.l.c; k <= this.l.f; ++k) {
            for (int l = this.l.a; l <= this.l.d; ++l) {
                BlockPosition blockposition = new BlockPosition(l, 64, k);

                if (structureboundingbox.b((BaseBlockPosition) blockposition)) {
                    i += Math.max(world.r(blockposition).getY(), world.worldProvider.getSeaLevel());
                    ++j;
                }
            }
        }

        if (j == 0) {
            return -1;
        } else {
            return i / j;
        }
    }

    protected static boolean a(StructureBoundingBox structureboundingbox) {
        return structureboundingbox != null && structureboundingbox.b > 10;
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l) {
        if (this.a < l) {
            for (int i1 = this.a; i1 < l; ++i1) {
                int j1 = this.a(i + i1, k);
                int k1 = this.d(j);
                int l1 = this.b(i + i1, k);

                if (!structureboundingbox.b((BaseBlockPosition) (new BlockPosition(j1, k1, l1)))) {
                    break;
                }

                ++this.a;
                EntityVillager entityvillager = new EntityVillager(world);

                entityvillager.setPositionRotation((double) j1 + 0.5D, (double) k1, (double) l1 + 0.5D, 0.0F, 0.0F);
                entityvillager.prepare(world.E(new BlockPosition(entityvillager)), (GroupDataEntity) null);
                entityvillager.setProfession(this.c(i1, entityvillager.getProfession()));
                world.addEntity(entityvillager, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.CHUNK_GEN); // CraftBukkit - add SpawnReason
            }

        }
    }

    protected int c(int i, int j) {
        return j;
    }

    protected IBlockData a(IBlockData iblockdata) {
        if (this.b) {
            if (iblockdata.getBlock() == Blocks.LOG || iblockdata.getBlock() == Blocks.LOG2) {
                return Blocks.SANDSTONE.getBlockData();
            }

            if (iblockdata.getBlock() == Blocks.COBBLESTONE) {
                return Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.DEFAULT.a());
            }

            if (iblockdata.getBlock() == Blocks.PLANKS) {
                return Blocks.SANDSTONE.fromLegacyData(EnumSandstoneVariant.SMOOTH.a());
            }

            if (iblockdata.getBlock() == Blocks.OAK_STAIRS) {
                return Blocks.SANDSTONE_STAIRS.getBlockData().set(BlockStairs.FACING, iblockdata.get(BlockStairs.FACING));
            }

            if (iblockdata.getBlock() == Blocks.STONE_STAIRS) {
                return Blocks.SANDSTONE_STAIRS.getBlockData().set(BlockStairs.FACING, iblockdata.get(BlockStairs.FACING));
            }

            if (iblockdata.getBlock() == Blocks.GRAVEL) {
                return Blocks.SANDSTONE.getBlockData();
            }
        }

        return iblockdata;
    }

    protected void a(World world, IBlockData iblockdata, int i, int j, int k, StructureBoundingBox structureboundingbox) {
        IBlockData iblockdata1 = this.a(iblockdata);

        super.a(world, iblockdata1, i, j, k, structureboundingbox);
    }

    protected void a(World world, StructureBoundingBox structureboundingbox, int i, int j, int k, int l, int i1, int j1, IBlockData iblockdata, IBlockData iblockdata1, boolean flag) {
        IBlockData iblockdata2 = this.a(iblockdata);
        IBlockData iblockdata3 = this.a(iblockdata1);

        super.a(world, structureboundingbox, i, j, k, l, i1, j1, iblockdata2, iblockdata3, flag);
    }

    protected void b(World world, IBlockData iblockdata, int i, int j, int k, StructureBoundingBox structureboundingbox) {
        IBlockData iblockdata1 = this.a(iblockdata);

        super.b(world, iblockdata1, i, j, k, structureboundingbox);
    }

    protected void a(boolean flag) {
        this.b = flag;
    }
}
