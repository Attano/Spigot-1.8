package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

abstract class WorldGenNetherPiece extends StructurePiece {

    protected static final List a = Lists.newArrayList(new StructurePieceTreasure[] { new StructurePieceTreasure(Items.DIAMOND, 0, 1, 3, 5), new StructurePieceTreasure(Items.IRON_INGOT, 0, 1, 5, 5), new StructurePieceTreasure(Items.GOLD_INGOT, 0, 1, 3, 15), new StructurePieceTreasure(Items.GOLDEN_SWORD, 0, 1, 1, 5), new StructurePieceTreasure(Items.GOLDEN_CHESTPLATE, 0, 1, 1, 5), new StructurePieceTreasure(Items.FLINT_AND_STEEL, 0, 1, 1, 5), new StructurePieceTreasure(Items.NETHER_WART, 0, 3, 7, 5), new StructurePieceTreasure(Items.SADDLE, 0, 1, 1, 10), new StructurePieceTreasure(Items.GOLDEN_HORSE_ARMOR, 0, 1, 1, 8), new StructurePieceTreasure(Items.IRON_HORSE_ARMOR, 0, 1, 1, 5), new StructurePieceTreasure(Items.DIAMOND_HORSE_ARMOR, 0, 1, 1, 3), new StructurePieceTreasure(Item.getItemOf(Blocks.OBSIDIAN), 0, 2, 4, 2)});

    public WorldGenNetherPiece() {}

    protected WorldGenNetherPiece(int i) {
        super(i);
    }

    protected void b(NBTTagCompound nbttagcompound) {}

    protected void a(NBTTagCompound nbttagcompound) {}

    private int a(List list) {
        boolean flag = false;
        int i = 0;

        WorldGenNetherPieceWeight worldgennetherpieceweight;

        for (Iterator iterator = list.iterator(); iterator.hasNext(); i += worldgennetherpieceweight.b) {
            worldgennetherpieceweight = (WorldGenNetherPieceWeight) iterator.next();
            if (worldgennetherpieceweight.d > 0 && worldgennetherpieceweight.c < worldgennetherpieceweight.d) {
                flag = true;
            }
        }

        return flag ? i : -1;
    }

    private WorldGenNetherPiece a(WorldGenNetherPiece15 worldgennetherpiece15, List list, List list1, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        int i1 = this.a(list);
        boolean flag = i1 > 0 && l <= 30;
        int j1 = 0;

        while (j1 < 5 && flag) {
            ++j1;
            int k1 = random.nextInt(i1);
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                WorldGenNetherPieceWeight worldgennetherpieceweight = (WorldGenNetherPieceWeight) iterator.next();

                k1 -= worldgennetherpieceweight.b;
                if (k1 < 0) {
                    if (!worldgennetherpieceweight.a(l) || worldgennetherpieceweight == worldgennetherpiece15.b && !worldgennetherpieceweight.e) {
                        break;
                    }

                    WorldGenNetherPiece worldgennetherpiece = WorldGenNetherPieces.a(worldgennetherpieceweight, list1, random, i, j, k, enumdirection, l);

                    if (worldgennetherpiece != null) {
                        ++worldgennetherpieceweight.c;
                        worldgennetherpiece15.b = worldgennetherpieceweight;
                        if (!worldgennetherpieceweight.a()) {
                            list.remove(worldgennetherpieceweight);
                        }

                        return worldgennetherpiece;
                    }
                }
            }
        }

        return WorldGenNetherPiece2.a(list1, random, i, j, k, enumdirection, l);
    }

    private StructurePiece a(WorldGenNetherPiece15 worldgennetherpiece15, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l, boolean flag) {
        if (Math.abs(i - worldgennetherpiece15.c().a) <= 112 && Math.abs(k - worldgennetherpiece15.c().c) <= 112) {
            List list1 = worldgennetherpiece15.c;

            if (flag) {
                list1 = worldgennetherpiece15.d;
            }

            WorldGenNetherPiece worldgennetherpiece = this.a(worldgennetherpiece15, list1, list, random, i, j, k, enumdirection, l + 1);

            if (worldgennetherpiece != null) {
                list.add(worldgennetherpiece);
                worldgennetherpiece15.e.add(worldgennetherpiece);
            }

            return worldgennetherpiece;
        } else {
            return WorldGenNetherPiece2.a(list, random, i, j, k, enumdirection, l);
        }
    }

    protected StructurePiece a(WorldGenNetherPiece15 worldgennetherpiece15, List list, Random random, int i, int j, boolean flag) {
        if (this.m != null) {
            switch (SwitchHelperDirection6.a[this.m.ordinal()]) {
            case 1:
                return this.a(worldgennetherpiece15, list, random, this.l.a + i, this.l.b + j, this.l.c - 1, this.m, this.d(), flag);

            case 2:
                return this.a(worldgennetherpiece15, list, random, this.l.a + i, this.l.b + j, this.l.f + 1, this.m, this.d(), flag);

            case 3:
                return this.a(worldgennetherpiece15, list, random, this.l.a - 1, this.l.b + j, this.l.c + i, this.m, this.d(), flag);

            case 4:
                return this.a(worldgennetherpiece15, list, random, this.l.d + 1, this.l.b + j, this.l.c + i, this.m, this.d(), flag);
            }
        }

        return null;
    }

    protected StructurePiece b(WorldGenNetherPiece15 worldgennetherpiece15, List list, Random random, int i, int j, boolean flag) {
        if (this.m != null) {
            switch (SwitchHelperDirection6.a[this.m.ordinal()]) {
            case 1:
                return this.a(worldgennetherpiece15, list, random, this.l.a - 1, this.l.b + i, this.l.c + j, EnumDirection.WEST, this.d(), flag);

            case 2:
                return this.a(worldgennetherpiece15, list, random, this.l.a - 1, this.l.b + i, this.l.c + j, EnumDirection.WEST, this.d(), flag);

            case 3:
                return this.a(worldgennetherpiece15, list, random, this.l.a + j, this.l.b + i, this.l.c - 1, EnumDirection.NORTH, this.d(), flag);

            case 4:
                return this.a(worldgennetherpiece15, list, random, this.l.a + j, this.l.b + i, this.l.c - 1, EnumDirection.NORTH, this.d(), flag);
            }
        }

        return null;
    }

    protected StructurePiece c(WorldGenNetherPiece15 worldgennetherpiece15, List list, Random random, int i, int j, boolean flag) {
        if (this.m != null) {
            switch (SwitchHelperDirection6.a[this.m.ordinal()]) {
            case 1:
                return this.a(worldgennetherpiece15, list, random, this.l.d + 1, this.l.b + i, this.l.c + j, EnumDirection.EAST, this.d(), flag);

            case 2:
                return this.a(worldgennetherpiece15, list, random, this.l.d + 1, this.l.b + i, this.l.c + j, EnumDirection.EAST, this.d(), flag);

            case 3:
                return this.a(worldgennetherpiece15, list, random, this.l.a + j, this.l.b + i, this.l.f + 1, EnumDirection.SOUTH, this.d(), flag);

            case 4:
                return this.a(worldgennetherpiece15, list, random, this.l.a + j, this.l.b + i, this.l.f + 1, EnumDirection.SOUTH, this.d(), flag);
            }
        }

        return null;
    }

    protected static boolean a(StructureBoundingBox structureboundingbox) {
        return structureboundingbox != null && structureboundingbox.b > 10;
    }
}
