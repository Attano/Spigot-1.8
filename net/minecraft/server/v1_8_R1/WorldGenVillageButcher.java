package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageButcher extends WorldGenVillagePiece {

    public WorldGenVillageButcher() {}

    public WorldGenVillageButcher(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(worldgenvillagestartpiece, i);
        this.m = enumdirection;
        this.l = structureboundingbox;
    }

    public static WorldGenVillageButcher a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 9, 7, 11, enumdirection);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageButcher(worldgenvillagestartpiece, l, random, structureboundingbox, enumdirection) : null;
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
        this.a(world, structureboundingbox, 2, 0, 6, 8, 0, 10, Blocks.DIRT.getBlockData(), Blocks.DIRT.getBlockData(), false);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 6, 0, 6, structureboundingbox);
        this.a(world, structureboundingbox, 2, 1, 6, 2, 1, 10, Blocks.FENCE.getBlockData(), Blocks.FENCE.getBlockData(), false);
        this.a(world, structureboundingbox, 8, 1, 6, 8, 1, 10, Blocks.FENCE.getBlockData(), Blocks.FENCE.getBlockData(), false);
        this.a(world, structureboundingbox, 3, 1, 10, 7, 1, 10, Blocks.FENCE.getBlockData(), Blocks.FENCE.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 0, 1, 7, 0, 4, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 0, 0, 0, 3, 5, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 8, 0, 0, 8, 3, 5, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 0, 0, 7, 1, 0, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 0, 5, 7, 1, 5, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 2, 0, 7, 3, 0, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 2, 5, 7, 3, 5, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 4, 1, 8, 4, 1, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 4, 4, 8, 4, 4, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 5, 2, 8, 5, 3, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, Blocks.PLANKS.getBlockData(), 0, 4, 2, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 0, 4, 3, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 8, 4, 2, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 8, 4, 3, structureboundingbox);
        int i = this.a(Blocks.OAK_STAIRS, 3);
        int j = this.a(Blocks.OAK_STAIRS, 2);

        int k;
        int l;

        for (k = -1; k <= 2; ++k) {
            for (l = 0; l <= 8; ++l) {
                this.a(world, Blocks.OAK_STAIRS.fromLegacyData(i), l, 4 + k, k, structureboundingbox);
                this.a(world, Blocks.OAK_STAIRS.fromLegacyData(j), l, 4 + k, 5 - k, structureboundingbox);
            }
        }

        this.a(world, Blocks.LOG.getBlockData(), 0, 2, 1, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 0, 2, 4, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 8, 2, 1, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 8, 2, 4, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 0, 2, 2, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 0, 2, 3, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 8, 2, 2, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 8, 2, 3, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 2, 2, 5, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 3, 2, 5, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 5, 2, 0, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 6, 2, 5, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 2, 1, 3, structureboundingbox);
        this.a(world, Blocks.WOODEN_PRESSURE_PLATE.getBlockData(), 2, 2, 3, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 1, 1, 4, structureboundingbox);
        this.a(world, Blocks.OAK_STAIRS.fromLegacyData(this.a(Blocks.OAK_STAIRS, 3)), 2, 1, 4, structureboundingbox);
        this.a(world, Blocks.OAK_STAIRS.fromLegacyData(this.a(Blocks.OAK_STAIRS, 1)), 1, 1, 3, structureboundingbox);
        this.a(world, structureboundingbox, 5, 0, 1, 7, 0, 3, Blocks.DOUBLE_STONE_SLAB.getBlockData(), Blocks.DOUBLE_STONE_SLAB.getBlockData(), false);
        this.a(world, Blocks.DOUBLE_STONE_SLAB.getBlockData(), 6, 1, 1, structureboundingbox);
        this.a(world, Blocks.DOUBLE_STONE_SLAB.getBlockData(), 6, 1, 2, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 2, 1, 0, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 2, 2, 0, structureboundingbox);
        this.a(world, Blocks.TORCH.getBlockData().set(BlockTorch.FACING, this.m), 2, 3, 1, structureboundingbox);
        this.a(world, structureboundingbox, random, 2, 1, 0, EnumDirection.fromType2(this.a(Blocks.WOODEN_DOOR, 1)));
        if (this.a(world, 2, 0, -1, structureboundingbox).getBlock().getMaterial() == Material.AIR && this.a(world, 2, -1, -1, structureboundingbox).getBlock().getMaterial() != Material.AIR) {
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(this.a(Blocks.STONE_STAIRS, 3)), 2, 0, -1, structureboundingbox);
        }

        this.a(world, Blocks.AIR.getBlockData(), 6, 1, 5, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 6, 2, 5, structureboundingbox);
        this.a(world, Blocks.TORCH.getBlockData().set(BlockTorch.FACING, this.m.opposite()), 6, 3, 4, structureboundingbox);
        this.a(world, structureboundingbox, random, 6, 1, 5, EnumDirection.fromType2(this.a(Blocks.WOODEN_DOOR, 1)));

        for (k = 0; k < 5; ++k) {
            for (l = 0; l < 9; ++l) {
                this.b(world, l, 7, k, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE.getBlockData(), l, -1, k, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 4, 1, 2, 2);
        return true;
    }

    protected int c(int i, int j) {
        return i == 0 ? 4 : super.c(i, j);
    }
}
