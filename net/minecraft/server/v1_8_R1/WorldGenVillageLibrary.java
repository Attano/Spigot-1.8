package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageLibrary extends WorldGenVillagePiece {

    public WorldGenVillageLibrary() {}

    public WorldGenVillageLibrary(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(worldgenvillagestartpiece, i);
        this.m = enumdirection;
        this.l = structureboundingbox;
    }

    public static WorldGenVillageLibrary a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 9, 9, 6, enumdirection);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageLibrary(worldgenvillagestartpiece, l, random, structureboundingbox, enumdirection) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.h < 0) {
            this.h = this.b(world, structureboundingbox);
            if (this.h < 0) {
                return true;
            }

            this.l.a(0, this.h - this.l.e + 9 - 1, 0);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 7, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 0, 0, 8, 0, 5, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 5, 0, 8, 5, 5, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 6, 1, 8, 6, 4, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 7, 2, 8, 7, 3, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        int i = this.a(Blocks.OAK_STAIRS, 3);
        int j = this.a(Blocks.OAK_STAIRS, 2);

        int k;
        int l;

        for (k = -1; k <= 2; ++k) {
            for (l = 0; l <= 8; ++l) {
                this.a(world, Blocks.OAK_STAIRS.fromLegacyData(i), l, 6 + k, k, structureboundingbox);
                this.a(world, Blocks.OAK_STAIRS.fromLegacyData(j), l, 6 + k, 5 - k, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 0, 1, 0, 0, 1, 5, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 1, 5, 8, 1, 5, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 8, 1, 0, 8, 1, 4, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 2, 1, 0, 7, 1, 0, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 2, 0, 0, 4, 0, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 2, 5, 0, 4, 5, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 8, 2, 5, 8, 4, 5, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 8, 2, 0, 8, 4, 0, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 2, 1, 0, 4, 4, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 2, 5, 7, 4, 5, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 8, 2, 1, 8, 4, 4, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 2, 0, 7, 4, 0, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 4, 2, 0, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 5, 2, 0, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 6, 2, 0, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 4, 3, 0, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 5, 3, 0, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 6, 3, 0, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 0, 2, 2, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 0, 2, 3, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 0, 3, 2, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 0, 3, 3, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 8, 2, 2, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 8, 2, 3, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 8, 3, 2, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 8, 3, 3, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 2, 2, 5, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 3, 2, 5, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 5, 2, 5, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 6, 2, 5, structureboundingbox);
        this.a(world, structureboundingbox, 1, 4, 1, 7, 4, 1, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 4, 4, 7, 4, 4, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 3, 4, 7, 3, 4, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
        this.a(world, Blocks.PLANKS.getBlockData(), 7, 1, 4, structureboundingbox);
        this.a(world, Blocks.OAK_STAIRS.fromLegacyData(this.a(Blocks.OAK_STAIRS, 0)), 7, 1, 3, structureboundingbox);
        k = this.a(Blocks.OAK_STAIRS, 3);
        this.a(world, Blocks.OAK_STAIRS.fromLegacyData(k), 6, 1, 4, structureboundingbox);
        this.a(world, Blocks.OAK_STAIRS.fromLegacyData(k), 5, 1, 4, structureboundingbox);
        this.a(world, Blocks.OAK_STAIRS.fromLegacyData(k), 4, 1, 4, structureboundingbox);
        this.a(world, Blocks.OAK_STAIRS.fromLegacyData(k), 3, 1, 4, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 6, 1, 3, structureboundingbox);
        this.a(world, Blocks.WOODEN_PRESSURE_PLATE.getBlockData(), 6, 2, 3, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 4, 1, 3, structureboundingbox);
        this.a(world, Blocks.WOODEN_PRESSURE_PLATE.getBlockData(), 4, 2, 3, structureboundingbox);
        this.a(world, Blocks.CRAFTING_TABLE.getBlockData(), 7, 1, 1, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 1, 1, 0, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 1, 2, 0, structureboundingbox);
        this.a(world, structureboundingbox, random, 1, 1, 0, EnumDirection.fromType2(this.a(Blocks.WOODEN_DOOR, 1)));
        if (this.a(world, 1, 0, -1, structureboundingbox).getBlock().getMaterial() == Material.AIR && this.a(world, 1, -1, -1, structureboundingbox).getBlock().getMaterial() != Material.AIR) {
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(this.a(Blocks.STONE_STAIRS, 3)), 1, 0, -1, structureboundingbox);
        }

        for (l = 0; l < 6; ++l) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.b(world, i1, 9, l, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE.getBlockData(), i1, -1, l, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 2, 1, 2, 1);
        return true;
    }

    protected int c(int i, int j) {
        return 1;
    }
}
