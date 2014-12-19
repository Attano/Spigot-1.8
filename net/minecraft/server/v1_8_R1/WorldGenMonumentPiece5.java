package net.minecraft.server;

import java.util.Random;

public class WorldGenMonumentPiece5 extends WorldGenMonumentPiece {

    public WorldGenMonumentPiece5() {}

    public WorldGenMonumentPiece5(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        super(1, enumdirection, worldgenmonumentstatetracker, 1, 2, 1);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.k.a / 25 > 0) {
            this.a(world, structureboundingbox, 0, 0, this.k.c[EnumDirection.DOWN.a()]);
        }

        WorldGenMonumentStateTracker worldgenmonumentstatetracker = this.k.b[EnumDirection.UP.a()];

        if (worldgenmonumentstatetracker.b[EnumDirection.UP.a()] == null) {
            this.a(world, structureboundingbox, 1, 8, 1, 6, 8, 6, WorldGenMonumentPiece5.a);
        }

        this.a(world, structureboundingbox, 0, 4, 0, 0, 4, 7, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
        this.a(world, structureboundingbox, 7, 4, 0, 7, 4, 7, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
        this.a(world, structureboundingbox, 1, 4, 0, 6, 4, 0, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
        this.a(world, structureboundingbox, 1, 4, 7, 6, 4, 7, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
        this.a(world, structureboundingbox, 2, 4, 1, 2, 4, 2, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
        this.a(world, structureboundingbox, 1, 4, 2, 1, 4, 2, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
        this.a(world, structureboundingbox, 5, 4, 1, 5, 4, 2, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
        this.a(world, structureboundingbox, 6, 4, 2, 6, 4, 2, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
        this.a(world, structureboundingbox, 2, 4, 5, 2, 4, 6, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
        this.a(world, structureboundingbox, 1, 4, 5, 1, 4, 5, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
        this.a(world, structureboundingbox, 5, 4, 5, 5, 4, 6, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
        this.a(world, structureboundingbox, 6, 4, 5, 6, 4, 5, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
        WorldGenMonumentStateTracker worldgenmonumentstatetracker1 = this.k;

        for (int i = 1; i <= 5; i += 4) {
            byte b0 = 0;

            if (worldgenmonumentstatetracker1.c[EnumDirection.SOUTH.a()]) {
                this.a(world, structureboundingbox, 2, i, b0, 2, i + 2, b0, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
                this.a(world, structureboundingbox, 5, i, b0, 5, i + 2, b0, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
                this.a(world, structureboundingbox, 3, i + 2, b0, 4, i + 2, b0, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
            } else {
                this.a(world, structureboundingbox, 0, i, b0, 7, i + 2, b0, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
                this.a(world, structureboundingbox, 0, i + 1, b0, 7, i + 1, b0, WorldGenMonumentPiece5.a, WorldGenMonumentPiece5.a, false);
            }

            b0 = 7;
            if (worldgenmonumentstatetracker1.c[EnumDirection.NORTH.a()]) {
                this.a(world, structureboundingbox, 2, i, b0, 2, i + 2, b0, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
                this.a(world, structureboundingbox, 5, i, b0, 5, i + 2, b0, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
                this.a(world, structureboundingbox, 3, i + 2, b0, 4, i + 2, b0, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
            } else {
                this.a(world, structureboundingbox, 0, i, b0, 7, i + 2, b0, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
                this.a(world, structureboundingbox, 0, i + 1, b0, 7, i + 1, b0, WorldGenMonumentPiece5.a, WorldGenMonumentPiece5.a, false);
            }

            byte b1 = 0;

            if (worldgenmonumentstatetracker1.c[EnumDirection.WEST.a()]) {
                this.a(world, structureboundingbox, b1, i, 2, b1, i + 2, 2, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
                this.a(world, structureboundingbox, b1, i, 5, b1, i + 2, 5, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
                this.a(world, structureboundingbox, b1, i + 2, 3, b1, i + 2, 4, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
            } else {
                this.a(world, structureboundingbox, b1, i, 0, b1, i + 2, 7, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
                this.a(world, structureboundingbox, b1, i + 1, 0, b1, i + 1, 7, WorldGenMonumentPiece5.a, WorldGenMonumentPiece5.a, false);
            }

            b1 = 7;
            if (worldgenmonumentstatetracker1.c[EnumDirection.EAST.a()]) {
                this.a(world, structureboundingbox, b1, i, 2, b1, i + 2, 2, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
                this.a(world, structureboundingbox, b1, i, 5, b1, i + 2, 5, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
                this.a(world, structureboundingbox, b1, i + 2, 3, b1, i + 2, 4, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
            } else {
                this.a(world, structureboundingbox, b1, i, 0, b1, i + 2, 7, WorldGenMonumentPiece5.b, WorldGenMonumentPiece5.b, false);
                this.a(world, structureboundingbox, b1, i + 1, 0, b1, i + 1, 7, WorldGenMonumentPiece5.a, WorldGenMonumentPiece5.a, false);
            }

            worldgenmonumentstatetracker1 = worldgenmonumentstatetracker;
        }

        return true;
    }
}
