package net.minecraft.server;

import java.util.Random;

public class WorldGenMonumentPiece2 extends WorldGenMonumentPiece {

    public WorldGenMonumentPiece2() {}

    public WorldGenMonumentPiece2(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        super(1, enumdirection, worldgenmonumentstatetracker, 2, 2, 2);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 1, 8, 0, 14, 8, 14, WorldGenMonumentPiece2.a);
        byte b0 = 7;
        IBlockData iblockdata = WorldGenMonumentPiece2.b;

        this.a(world, structureboundingbox, 0, b0, 0, 0, b0, 15, iblockdata, iblockdata, false);
        this.a(world, structureboundingbox, 15, b0, 0, 15, b0, 15, iblockdata, iblockdata, false);
        this.a(world, structureboundingbox, 1, b0, 0, 15, b0, 0, iblockdata, iblockdata, false);
        this.a(world, structureboundingbox, 1, b0, 15, 14, b0, 15, iblockdata, iblockdata, false);

        int i;

        for (i = 1; i <= 6; ++i) {
            iblockdata = WorldGenMonumentPiece2.b;
            if (i == 2 || i == 6) {
                iblockdata = WorldGenMonumentPiece2.a;
            }

            for (int j = 0; j <= 15; j += 15) {
                this.a(world, structureboundingbox, j, i, 0, j, i, 1, iblockdata, iblockdata, false);
                this.a(world, structureboundingbox, j, i, 6, j, i, 9, iblockdata, iblockdata, false);
                this.a(world, structureboundingbox, j, i, 14, j, i, 15, iblockdata, iblockdata, false);
            }

            this.a(world, structureboundingbox, 1, i, 0, 1, i, 0, iblockdata, iblockdata, false);
            this.a(world, structureboundingbox, 6, i, 0, 9, i, 0, iblockdata, iblockdata, false);
            this.a(world, structureboundingbox, 14, i, 0, 14, i, 0, iblockdata, iblockdata, false);
            this.a(world, structureboundingbox, 1, i, 15, 14, i, 15, iblockdata, iblockdata, false);
        }

        this.a(world, structureboundingbox, 6, 3, 6, 9, 6, 9, WorldGenMonumentPiece2.c, WorldGenMonumentPiece2.c, false);
        this.a(world, structureboundingbox, 7, 4, 7, 8, 5, 8, Blocks.GOLD_BLOCK.getBlockData(), Blocks.GOLD_BLOCK.getBlockData(), false);

        for (i = 3; i <= 6; i += 3) {
            for (int k = 6; k <= 9; k += 3) {
                this.a(world, WorldGenMonumentPiece2.e, k, i, 6, structureboundingbox);
                this.a(world, WorldGenMonumentPiece2.e, k, i, 9, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 5, 1, 6, 5, 2, 6, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 5, 1, 9, 5, 2, 9, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 10, 1, 6, 10, 2, 6, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 10, 1, 9, 10, 2, 9, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 6, 1, 5, 6, 2, 5, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 9, 1, 5, 9, 2, 5, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 6, 1, 10, 6, 2, 10, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 9, 1, 10, 9, 2, 10, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 5, 2, 5, 5, 6, 5, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 5, 2, 10, 5, 6, 10, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 10, 2, 5, 10, 6, 5, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 10, 2, 10, 10, 6, 10, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 5, 7, 1, 5, 7, 6, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 10, 7, 1, 10, 7, 6, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 5, 7, 9, 5, 7, 14, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 10, 7, 9, 10, 7, 14, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 1, 7, 5, 6, 7, 5, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 1, 7, 10, 6, 7, 10, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 9, 7, 5, 14, 7, 5, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 9, 7, 10, 14, 7, 10, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 2, 1, 2, 2, 1, 3, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 3, 1, 2, 3, 1, 2, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 13, 1, 2, 13, 1, 3, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 12, 1, 2, 12, 1, 2, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 2, 1, 12, 2, 1, 13, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 3, 1, 13, 3, 1, 13, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 13, 1, 12, 13, 1, 13, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        this.a(world, structureboundingbox, 12, 1, 13, 12, 1, 13, WorldGenMonumentPiece2.b, WorldGenMonumentPiece2.b, false);
        return true;
    }
}
