package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageHouse2 extends WorldGenVillagePiece {

    public WorldGenVillageHouse2() {}

    public WorldGenVillageHouse2(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(worldgenvillagestartpiece, i);
        this.m = enumdirection;
        this.l = structureboundingbox;
    }

    public static WorldGenVillageHouse2 a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 9, 7, 12, enumdirection);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageHouse2(worldgenvillagestartpiece, l, random, structureboundingbox, enumdirection) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.h < 0) {
            this.h = this.b(world, structureboundingbox);
            if (this.h < 0) {
                return true;
            }

            this.l.a(0, this.h - this.l.e + 7 - 1, 0);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 7, 4, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 2, 1, 6, 8, 4, 10, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 2, 0, 5, 8, 0, 10, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 0, 1, 7, 0, 4, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 0, 0, 0, 3, 5, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 8, 0, 0, 8, 3, 10, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 0, 0, 7, 2, 0, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 0, 5, 2, 1, 5, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 2, 0, 6, 2, 3, 10, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 3, 0, 10, 7, 3, 10, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 2, 0, 7, 3, 0, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 2, 5, 2, 3, 5, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 4, 1, 8, 4, 1, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 4, 4, 3, 4, 4, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 5, 2, 8, 5, 3, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, Blocks.PLANKS.getBlockData(), 0, 4, 2, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 0, 4, 3, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 8, 4, 2, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 8, 4, 3, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 8, 4, 4, structureboundingbox);
        int i = this.a(Blocks.OAK_STAIRS, 3);
        int j = this.a(Blocks.OAK_STAIRS, 2);

        int k;
        int l;

        for (k = -1; k <= 2; ++k) {
            for (l = 0; l <= 8; ++l) {
                this.a(world, Blocks.OAK_STAIRS.fromLegacyData(i), l, 4 + k, k, structureboundingbox);
                if ((k > -1 || l <= 1) && (k > 0 || l <= 3) && (k > 1 || l <= 4 || l >= 6)) {
                    this.a(world, Blocks.OAK_STAIRS.fromLegacyData(j), l, 4 + k, 5 - k, structureboundingbox);
                }
            }
        }

        this.a(world, structureboundingbox, 3, 4, 5, 3, 4, 10, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 7, 4, 2, 7, 4, 10, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 4, 5, 4, 4, 5, 10, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 6, 5, 4, 6, 5, 10, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 5, 6, 3, 5, 6, 10, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        k = this.a(Blocks.OAK_STAIRS, 0);

        int i1;

        for (l = 4; l >= 1; --l) {
            this.a(world, Blocks.PLANKS.getBlockData(), l, 2 + l, 7 - l, structureboundingbox);

            for (i1 = 8 - l; i1 <= 10; ++i1) {
                this.a(world, Blocks.OAK_STAIRS.fromLegacyData(k), l, 2 + l, i1, structureboundingbox);
            }
        }

        l = this.a(Blocks.OAK_STAIRS, 1);
        this.a(world, Blocks.PLANKS.getBlockData(), 6, 6, 3, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 7, 5, 4, structureboundingbox);
        this.a(world, Blocks.OAK_STAIRS.fromLegacyData(l), 6, 6, 4, structureboundingbox);

        int j1;

        for (i1 = 6; i1 <= 8; ++i1) {
            for (j1 = 5; j1 <= 10; ++j1) {
                this.a(world, Blocks.OAK_STAIRS.fromLegacyData(l), i1, 12 - i1, j1, structureboundingbox);
            }
        }

        this.a(world, Blocks.LOG.getBlockData(), 0, 2, 1, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 0, 2, 4, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 0, 2, 2, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 0, 2, 3, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 4, 2, 0, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 5, 2, 0, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 6, 2, 0, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 8, 2, 1, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 8, 2, 2, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 8, 2, 3, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 8, 2, 4, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 8, 2, 5, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 8, 2, 6, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 8, 2, 7, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 8, 2, 8, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 8, 2, 9, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 2, 2, 6, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 2, 2, 7, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 2, 2, 8, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 2, 2, 9, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 4, 4, 10, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 5, 4, 10, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 6, 4, 10, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 5, 5, 10, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 2, 1, 0, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 2, 2, 0, structureboundingbox);
        this.a(world, Blocks.TORCH.getBlockData().set(BlockTorch.FACING, this.m), 2, 3, 1, structureboundingbox);
        this.a(world, structureboundingbox, random, 2, 1, 0, EnumDirection.fromType2(this.a(Blocks.WOODEN_DOOR, 1)));
        this.a(world, structureboundingbox, 1, 0, -1, 3, 2, -1, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        if (this.a(world, 2, 0, -1, structureboundingbox).getBlock().getMaterial() == Material.AIR && this.a(world, 2, -1, -1, structureboundingbox).getBlock().getMaterial() != Material.AIR) {
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(this.a(Blocks.STONE_STAIRS, 3)), 2, 0, -1, structureboundingbox);
        }

        for (i1 = 0; i1 < 5; ++i1) {
            for (j1 = 0; j1 < 9; ++j1) {
                this.b(world, j1, 7, i1, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE.getBlockData(), j1, -1, i1, structureboundingbox);
            }
        }

        for (i1 = 5; i1 < 11; ++i1) {
            for (j1 = 2; j1 < 9; ++j1) {
                this.b(world, j1, 7, i1, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE.getBlockData(), j1, -1, i1, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 4, 1, 2, 2);
        return true;
    }
}
