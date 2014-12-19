package net.minecraft.server;

import java.util.Random;

public class WorldGenMonumentPieceSimple extends WorldGenMonumentPiece {

    private int o;

    public WorldGenMonumentPieceSimple() {}

    public WorldGenMonumentPieceSimple(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        super(1, enumdirection, worldgenmonumentstatetracker, 1, 1, 1);
        this.o = random.nextInt(3);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.k.a / 25 > 0) {
            this.a(world, structureboundingbox, 0, 0, this.k.c[EnumDirection.DOWN.a()]);
        }

        if (this.k.b[EnumDirection.UP.a()] == null) {
            this.a(world, structureboundingbox, 1, 4, 1, 6, 4, 6, WorldGenMonumentPieceSimple.a);
        }

        boolean flag = this.o != 0 && random.nextBoolean() && !this.k.c[EnumDirection.DOWN.a()] && !this.k.c[EnumDirection.UP.a()] && this.k.c() > 1;

        if (this.o == 0) {
            this.a(world, structureboundingbox, 0, 1, 0, 2, 1, 2, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 0, 3, 0, 2, 3, 2, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 0, 2, 0, 0, 2, 2, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
            this.a(world, structureboundingbox, 1, 2, 0, 2, 2, 0, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
            this.a(world, WorldGenMonumentPieceSimple.e, 1, 2, 1, structureboundingbox);
            this.a(world, structureboundingbox, 5, 1, 0, 7, 1, 2, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 5, 3, 0, 7, 3, 2, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 7, 2, 0, 7, 2, 2, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
            this.a(world, structureboundingbox, 5, 2, 0, 6, 2, 0, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
            this.a(world, WorldGenMonumentPieceSimple.e, 6, 2, 1, structureboundingbox);
            this.a(world, structureboundingbox, 0, 1, 5, 2, 1, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 0, 3, 5, 2, 3, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 0, 2, 5, 0, 2, 7, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
            this.a(world, structureboundingbox, 1, 2, 7, 2, 2, 7, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
            this.a(world, WorldGenMonumentPieceSimple.e, 1, 2, 6, structureboundingbox);
            this.a(world, structureboundingbox, 5, 1, 5, 7, 1, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 5, 3, 5, 7, 3, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 7, 2, 5, 7, 2, 7, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
            this.a(world, structureboundingbox, 5, 2, 7, 6, 2, 7, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
            this.a(world, WorldGenMonumentPieceSimple.e, 6, 2, 6, structureboundingbox);
            if (this.k.c[EnumDirection.SOUTH.a()]) {
                this.a(world, structureboundingbox, 3, 3, 0, 4, 3, 0, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            } else {
                this.a(world, structureboundingbox, 3, 3, 0, 4, 3, 1, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
                this.a(world, structureboundingbox, 3, 2, 0, 4, 2, 0, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
                this.a(world, structureboundingbox, 3, 1, 0, 4, 1, 1, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            }

            if (this.k.c[EnumDirection.NORTH.a()]) {
                this.a(world, structureboundingbox, 3, 3, 7, 4, 3, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            } else {
                this.a(world, structureboundingbox, 3, 3, 6, 4, 3, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
                this.a(world, structureboundingbox, 3, 2, 7, 4, 2, 7, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
                this.a(world, structureboundingbox, 3, 1, 6, 4, 1, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            }

            if (this.k.c[EnumDirection.WEST.a()]) {
                this.a(world, structureboundingbox, 0, 3, 3, 0, 3, 4, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            } else {
                this.a(world, structureboundingbox, 0, 3, 3, 1, 3, 4, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
                this.a(world, structureboundingbox, 0, 2, 3, 0, 2, 4, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
                this.a(world, structureboundingbox, 0, 1, 3, 1, 1, 4, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            }

            if (this.k.c[EnumDirection.EAST.a()]) {
                this.a(world, structureboundingbox, 7, 3, 3, 7, 3, 4, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            } else {
                this.a(world, structureboundingbox, 6, 3, 3, 7, 3, 4, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
                this.a(world, structureboundingbox, 7, 2, 3, 7, 2, 4, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
                this.a(world, structureboundingbox, 6, 1, 3, 7, 1, 4, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            }
        } else if (this.o == 1) {
            this.a(world, structureboundingbox, 2, 1, 2, 2, 3, 2, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 2, 1, 5, 2, 3, 5, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 5, 1, 5, 5, 3, 5, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 5, 1, 2, 5, 3, 2, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, WorldGenMonumentPieceSimple.e, 2, 2, 2, structureboundingbox);
            this.a(world, WorldGenMonumentPieceSimple.e, 2, 2, 5, structureboundingbox);
            this.a(world, WorldGenMonumentPieceSimple.e, 5, 2, 5, structureboundingbox);
            this.a(world, WorldGenMonumentPieceSimple.e, 5, 2, 2, structureboundingbox);
            this.a(world, structureboundingbox, 0, 1, 0, 1, 3, 0, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 1, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 0, 1, 7, 1, 3, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 0, 1, 6, 0, 3, 6, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 6, 1, 7, 7, 3, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 7, 1, 6, 7, 3, 6, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 6, 1, 0, 7, 3, 0, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 7, 1, 1, 7, 3, 1, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, WorldGenMonumentPieceSimple.a, 1, 2, 0, structureboundingbox);
            this.a(world, WorldGenMonumentPieceSimple.a, 0, 2, 1, structureboundingbox);
            this.a(world, WorldGenMonumentPieceSimple.a, 1, 2, 7, structureboundingbox);
            this.a(world, WorldGenMonumentPieceSimple.a, 0, 2, 6, structureboundingbox);
            this.a(world, WorldGenMonumentPieceSimple.a, 6, 2, 7, structureboundingbox);
            this.a(world, WorldGenMonumentPieceSimple.a, 7, 2, 6, structureboundingbox);
            this.a(world, WorldGenMonumentPieceSimple.a, 6, 2, 0, structureboundingbox);
            this.a(world, WorldGenMonumentPieceSimple.a, 7, 2, 1, structureboundingbox);
            if (!this.k.c[EnumDirection.SOUTH.a()]) {
                this.a(world, structureboundingbox, 1, 3, 0, 6, 3, 0, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
                this.a(world, structureboundingbox, 1, 2, 0, 6, 2, 0, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
                this.a(world, structureboundingbox, 1, 1, 0, 6, 1, 0, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            }

            if (!this.k.c[EnumDirection.NORTH.a()]) {
                this.a(world, structureboundingbox, 1, 3, 7, 6, 3, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
                this.a(world, structureboundingbox, 1, 2, 7, 6, 2, 7, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
                this.a(world, structureboundingbox, 1, 1, 7, 6, 1, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            }

            if (!this.k.c[EnumDirection.WEST.a()]) {
                this.a(world, structureboundingbox, 0, 3, 1, 0, 3, 6, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
                this.a(world, structureboundingbox, 0, 2, 1, 0, 2, 6, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
                this.a(world, structureboundingbox, 0, 1, 1, 0, 1, 6, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            }

            if (!this.k.c[EnumDirection.EAST.a()]) {
                this.a(world, structureboundingbox, 7, 3, 1, 7, 3, 6, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
                this.a(world, structureboundingbox, 7, 2, 1, 7, 2, 6, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
                this.a(world, structureboundingbox, 7, 1, 1, 7, 1, 6, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            }
        } else if (this.o == 2) {
            this.a(world, structureboundingbox, 0, 1, 0, 0, 1, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 7, 1, 0, 7, 1, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 1, 1, 0, 6, 1, 0, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 1, 1, 7, 6, 1, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 0, 2, 0, 0, 2, 7, WorldGenMonumentPieceSimple.c, WorldGenMonumentPieceSimple.c, false);
            this.a(world, structureboundingbox, 7, 2, 0, 7, 2, 7, WorldGenMonumentPieceSimple.c, WorldGenMonumentPieceSimple.c, false);
            this.a(world, structureboundingbox, 1, 2, 0, 6, 2, 0, WorldGenMonumentPieceSimple.c, WorldGenMonumentPieceSimple.c, false);
            this.a(world, structureboundingbox, 1, 2, 7, 6, 2, 7, WorldGenMonumentPieceSimple.c, WorldGenMonumentPieceSimple.c, false);
            this.a(world, structureboundingbox, 0, 3, 0, 0, 3, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 7, 3, 0, 7, 3, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 1, 3, 0, 6, 3, 0, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 1, 3, 7, 6, 3, 7, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 0, 1, 3, 0, 2, 4, WorldGenMonumentPieceSimple.c, WorldGenMonumentPieceSimple.c, false);
            this.a(world, structureboundingbox, 7, 1, 3, 7, 2, 4, WorldGenMonumentPieceSimple.c, WorldGenMonumentPieceSimple.c, false);
            this.a(world, structureboundingbox, 3, 1, 0, 4, 2, 0, WorldGenMonumentPieceSimple.c, WorldGenMonumentPieceSimple.c, false);
            this.a(world, structureboundingbox, 3, 1, 7, 4, 2, 7, WorldGenMonumentPieceSimple.c, WorldGenMonumentPieceSimple.c, false);
            if (this.k.c[EnumDirection.SOUTH.a()]) {
                this.a(world, structureboundingbox, 3, 1, 0, 4, 2, 0, WorldGenMonumentPieceSimple.f, WorldGenMonumentPieceSimple.f, false);
            }

            if (this.k.c[EnumDirection.NORTH.a()]) {
                this.a(world, structureboundingbox, 3, 1, 7, 4, 2, 7, WorldGenMonumentPieceSimple.f, WorldGenMonumentPieceSimple.f, false);
            }

            if (this.k.c[EnumDirection.WEST.a()]) {
                this.a(world, structureboundingbox, 0, 1, 3, 0, 2, 4, WorldGenMonumentPieceSimple.f, WorldGenMonumentPieceSimple.f, false);
            }

            if (this.k.c[EnumDirection.EAST.a()]) {
                this.a(world, structureboundingbox, 7, 1, 3, 7, 2, 4, WorldGenMonumentPieceSimple.f, WorldGenMonumentPieceSimple.f, false);
            }
        }

        if (flag) {
            this.a(world, structureboundingbox, 3, 1, 3, 4, 1, 4, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
            this.a(world, structureboundingbox, 3, 2, 3, 4, 2, 4, WorldGenMonumentPieceSimple.a, WorldGenMonumentPieceSimple.a, false);
            this.a(world, structureboundingbox, 3, 3, 3, 4, 3, 4, WorldGenMonumentPieceSimple.b, WorldGenMonumentPieceSimple.b, false);
        }

        return true;
    }
}
