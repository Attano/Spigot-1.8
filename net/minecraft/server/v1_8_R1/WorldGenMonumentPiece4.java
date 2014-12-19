package net.minecraft.server;

import java.util.Random;

public class WorldGenMonumentPiece4 extends WorldGenMonumentPiece {

    public WorldGenMonumentPiece4() {}

    public WorldGenMonumentPiece4(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        super(1, enumdirection, worldgenmonumentstatetracker, 2, 2, 1);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        WorldGenMonumentStateTracker worldgenmonumentstatetracker = this.k.b[EnumDirection.EAST.a()];
        WorldGenMonumentStateTracker worldgenmonumentstatetracker1 = this.k;
        WorldGenMonumentStateTracker worldgenmonumentstatetracker2 = worldgenmonumentstatetracker1.b[EnumDirection.UP.a()];
        WorldGenMonumentStateTracker worldgenmonumentstatetracker3 = worldgenmonumentstatetracker.b[EnumDirection.UP.a()];

        if (this.k.a / 25 > 0) {
            this.a(world, structureboundingbox, 8, 0, worldgenmonumentstatetracker.c[EnumDirection.DOWN.a()]);
            this.a(world, structureboundingbox, 0, 0, worldgenmonumentstatetracker1.c[EnumDirection.DOWN.a()]);
        }

        if (worldgenmonumentstatetracker2.b[EnumDirection.UP.a()] == null) {
            this.a(world, structureboundingbox, 1, 8, 1, 7, 8, 6, WorldGenMonumentPiece4.a);
        }

        if (worldgenmonumentstatetracker3.b[EnumDirection.UP.a()] == null) {
            this.a(world, structureboundingbox, 8, 8, 1, 14, 8, 6, WorldGenMonumentPiece4.a);
        }

        for (int i = 1; i <= 7; ++i) {
            IBlockData iblockdata = WorldGenMonumentPiece4.b;

            if (i == 2 || i == 6) {
                iblockdata = WorldGenMonumentPiece4.a;
            }

            this.a(world, structureboundingbox, 0, i, 0, 0, i, 7, iblockdata, iblockdata, false);
            this.a(world, structureboundingbox, 15, i, 0, 15, i, 7, iblockdata, iblockdata, false);
            this.a(world, structureboundingbox, 1, i, 0, 15, i, 0, iblockdata, iblockdata, false);
            this.a(world, structureboundingbox, 1, i, 7, 14, i, 7, iblockdata, iblockdata, false);
        }

        this.a(world, structureboundingbox, 2, 1, 3, 2, 7, 4, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, structureboundingbox, 3, 1, 2, 4, 7, 2, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, structureboundingbox, 3, 1, 5, 4, 7, 5, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, structureboundingbox, 13, 1, 3, 13, 7, 4, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, structureboundingbox, 11, 1, 2, 12, 7, 2, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, structureboundingbox, 11, 1, 5, 12, 7, 5, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, structureboundingbox, 5, 1, 3, 5, 3, 4, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, structureboundingbox, 10, 1, 3, 10, 3, 4, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, structureboundingbox, 5, 7, 2, 10, 7, 5, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, structureboundingbox, 5, 5, 2, 5, 7, 2, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, structureboundingbox, 10, 5, 2, 10, 7, 2, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, structureboundingbox, 5, 5, 5, 5, 7, 5, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, structureboundingbox, 10, 5, 5, 10, 7, 5, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, WorldGenMonumentPiece4.b, 6, 6, 2, structureboundingbox);
        this.a(world, WorldGenMonumentPiece4.b, 9, 6, 2, structureboundingbox);
        this.a(world, WorldGenMonumentPiece4.b, 6, 6, 5, structureboundingbox);
        this.a(world, WorldGenMonumentPiece4.b, 9, 6, 5, structureboundingbox);
        this.a(world, structureboundingbox, 5, 4, 3, 6, 4, 4, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, structureboundingbox, 9, 4, 3, 10, 4, 4, WorldGenMonumentPiece4.b, WorldGenMonumentPiece4.b, false);
        this.a(world, WorldGenMonumentPiece4.e, 5, 4, 2, structureboundingbox);
        this.a(world, WorldGenMonumentPiece4.e, 5, 4, 5, structureboundingbox);
        this.a(world, WorldGenMonumentPiece4.e, 10, 4, 2, structureboundingbox);
        this.a(world, WorldGenMonumentPiece4.e, 10, 4, 5, structureboundingbox);
        if (worldgenmonumentstatetracker1.c[EnumDirection.SOUTH.a()]) {
            this.a(world, structureboundingbox, 3, 1, 0, 4, 2, 0, WorldGenMonumentPiece4.f, WorldGenMonumentPiece4.f, false);
        }

        if (worldgenmonumentstatetracker1.c[EnumDirection.NORTH.a()]) {
            this.a(world, structureboundingbox, 3, 1, 7, 4, 2, 7, WorldGenMonumentPiece4.f, WorldGenMonumentPiece4.f, false);
        }

        if (worldgenmonumentstatetracker1.c[EnumDirection.WEST.a()]) {
            this.a(world, structureboundingbox, 0, 1, 3, 0, 2, 4, WorldGenMonumentPiece4.f, WorldGenMonumentPiece4.f, false);
        }

        if (worldgenmonumentstatetracker.c[EnumDirection.SOUTH.a()]) {
            this.a(world, structureboundingbox, 11, 1, 0, 12, 2, 0, WorldGenMonumentPiece4.f, WorldGenMonumentPiece4.f, false);
        }

        if (worldgenmonumentstatetracker.c[EnumDirection.NORTH.a()]) {
            this.a(world, structureboundingbox, 11, 1, 7, 12, 2, 7, WorldGenMonumentPiece4.f, WorldGenMonumentPiece4.f, false);
        }

        if (worldgenmonumentstatetracker.c[EnumDirection.EAST.a()]) {
            this.a(world, structureboundingbox, 15, 1, 3, 15, 2, 4, WorldGenMonumentPiece4.f, WorldGenMonumentPiece4.f, false);
        }

        if (worldgenmonumentstatetracker2.c[EnumDirection.SOUTH.a()]) {
            this.a(world, structureboundingbox, 3, 5, 0, 4, 6, 0, WorldGenMonumentPiece4.f, WorldGenMonumentPiece4.f, false);
        }

        if (worldgenmonumentstatetracker2.c[EnumDirection.NORTH.a()]) {
            this.a(world, structureboundingbox, 3, 5, 7, 4, 6, 7, WorldGenMonumentPiece4.f, WorldGenMonumentPiece4.f, false);
        }

        if (worldgenmonumentstatetracker2.c[EnumDirection.WEST.a()]) {
            this.a(world, structureboundingbox, 0, 5, 3, 0, 6, 4, WorldGenMonumentPiece4.f, WorldGenMonumentPiece4.f, false);
        }

        if (worldgenmonumentstatetracker3.c[EnumDirection.SOUTH.a()]) {
            this.a(world, structureboundingbox, 11, 5, 0, 12, 6, 0, WorldGenMonumentPiece4.f, WorldGenMonumentPiece4.f, false);
        }

        if (worldgenmonumentstatetracker3.c[EnumDirection.NORTH.a()]) {
            this.a(world, structureboundingbox, 11, 5, 7, 12, 6, 7, WorldGenMonumentPiece4.f, WorldGenMonumentPiece4.f, false);
        }

        if (worldgenmonumentstatetracker3.c[EnumDirection.EAST.a()]) {
            this.a(world, structureboundingbox, 15, 5, 3, 15, 6, 4, WorldGenMonumentPiece4.f, WorldGenMonumentPiece4.f, false);
        }

        return true;
    }
}
