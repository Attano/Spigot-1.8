package net.minecraft.server;

import java.util.Random;

public class WorldGenMonumentPiece8 extends WorldGenMonumentPiece {

    private int o;

    public WorldGenMonumentPiece8() {}

    public WorldGenMonumentPiece8(EnumDirection enumdirection, StructureBoundingBox structureboundingbox, int i) {
        super(enumdirection, structureboundingbox);
        this.o = i & 1;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.o == 0) {
            int i;

            for (i = 0; i < 4; ++i) {
                this.a(world, structureboundingbox, 10 - i, 3 - i, 20 - i, 12 + i, 3 - i, 20, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            }

            this.a(world, structureboundingbox, 7, 0, 6, 15, 0, 16, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 6, 0, 6, 6, 3, 20, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 16, 0, 6, 16, 3, 20, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 7, 1, 7, 7, 1, 20, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 15, 1, 7, 15, 1, 20, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 7, 1, 6, 9, 3, 6, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 13, 1, 6, 15, 3, 6, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 8, 1, 7, 9, 1, 7, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 13, 1, 7, 14, 1, 7, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 9, 0, 5, 13, 0, 5, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 10, 0, 7, 12, 0, 7, WorldGenMonumentPiece8.c, WorldGenMonumentPiece8.c, false);
            this.a(world, structureboundingbox, 8, 0, 10, 8, 0, 12, WorldGenMonumentPiece8.c, WorldGenMonumentPiece8.c, false);
            this.a(world, structureboundingbox, 14, 0, 10, 14, 0, 12, WorldGenMonumentPiece8.c, WorldGenMonumentPiece8.c, false);

            for (i = 18; i >= 7; i -= 3) {
                this.a(world, WorldGenMonumentPiece8.e, 6, 3, i, structureboundingbox);
                this.a(world, WorldGenMonumentPiece8.e, 16, 3, i, structureboundingbox);
            }

            this.a(world, WorldGenMonumentPiece8.e, 10, 0, 10, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.e, 12, 0, 10, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.e, 10, 0, 12, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.e, 12, 0, 12, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.e, 8, 3, 6, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.e, 14, 3, 6, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.b, 4, 2, 4, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.e, 4, 1, 4, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.b, 4, 0, 4, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.b, 18, 2, 4, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.e, 18, 1, 4, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.b, 18, 0, 4, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.b, 4, 2, 18, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.e, 4, 1, 18, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.b, 4, 0, 18, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.b, 18, 2, 18, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.e, 18, 1, 18, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.b, 18, 0, 18, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.b, 9, 7, 20, structureboundingbox);
            this.a(world, WorldGenMonumentPiece8.b, 13, 7, 20, structureboundingbox);
            this.a(world, structureboundingbox, 6, 0, 21, 7, 4, 21, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 15, 0, 21, 16, 4, 21, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 11, 2, 16);
        } else if (this.o == 1) {
            this.a(world, structureboundingbox, 9, 3, 18, 13, 3, 20, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 9, 0, 18, 9, 2, 18, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            this.a(world, structureboundingbox, 13, 0, 18, 13, 2, 18, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            byte b0 = 9;
            byte b1 = 20;
            byte b2 = 5;

            int j;

            for (j = 0; j < 2; ++j) {
                this.a(world, WorldGenMonumentPiece8.b, b0, b2 + 1, b1, structureboundingbox);
                this.a(world, WorldGenMonumentPiece8.e, b0, b2, b1, structureboundingbox);
                this.a(world, WorldGenMonumentPiece8.b, b0, b2 - 1, b1, structureboundingbox);
                b0 = 13;
            }

            this.a(world, structureboundingbox, 7, 3, 7, 15, 3, 14, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
            b0 = 10;

            for (j = 0; j < 2; ++j) {
                this.a(world, structureboundingbox, b0, 0, 10, b0, 6, 10, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
                this.a(world, structureboundingbox, b0, 0, 12, b0, 6, 12, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
                this.a(world, WorldGenMonumentPiece8.e, b0, 0, 10, structureboundingbox);
                this.a(world, WorldGenMonumentPiece8.e, b0, 0, 12, structureboundingbox);
                this.a(world, WorldGenMonumentPiece8.e, b0, 4, 10, structureboundingbox);
                this.a(world, WorldGenMonumentPiece8.e, b0, 4, 12, structureboundingbox);
                b0 = 12;
            }

            b0 = 8;

            for (j = 0; j < 2; ++j) {
                this.a(world, structureboundingbox, b0, 0, 7, b0, 2, 7, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
                this.a(world, structureboundingbox, b0, 0, 14, b0, 2, 14, WorldGenMonumentPiece8.b, WorldGenMonumentPiece8.b, false);
                b0 = 14;
            }

            this.a(world, structureboundingbox, 8, 3, 8, 8, 3, 13, WorldGenMonumentPiece8.c, WorldGenMonumentPiece8.c, false);
            this.a(world, structureboundingbox, 14, 3, 8, 14, 3, 13, WorldGenMonumentPiece8.c, WorldGenMonumentPiece8.c, false);
            this.a(world, structureboundingbox, 11, 5, 13);
        }

        return true;
    }
}
