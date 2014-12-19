package net.minecraft.server;

import java.util.Random;

public class WorldGenMonumentPiece6 extends WorldGenMonumentPiece {

    public WorldGenMonumentPiece6() {}

    public WorldGenMonumentPiece6(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        super(1, enumdirection, worldgenmonumentstatetracker, 1, 2, 2);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        WorldGenMonumentStateTracker worldgenmonumentstatetracker = this.k.b[EnumDirection.NORTH.a()];
        WorldGenMonumentStateTracker worldgenmonumentstatetracker1 = this.k;
        WorldGenMonumentStateTracker worldgenmonumentstatetracker2 = worldgenmonumentstatetracker.b[EnumDirection.UP.a()];
        WorldGenMonumentStateTracker worldgenmonumentstatetracker3 = worldgenmonumentstatetracker1.b[EnumDirection.UP.a()];

        if (this.k.a / 25 > 0) {
            this.a(world, structureboundingbox, 0, 8, worldgenmonumentstatetracker.c[EnumDirection.DOWN.a()]);
            this.a(world, structureboundingbox, 0, 0, worldgenmonumentstatetracker1.c[EnumDirection.DOWN.a()]);
        }

        if (worldgenmonumentstatetracker3.b[EnumDirection.UP.a()] == null) {
            this.a(world, structureboundingbox, 1, 8, 1, 6, 8, 7, WorldGenMonumentPiece6.a);
        }

        if (worldgenmonumentstatetracker2.b[EnumDirection.UP.a()] == null) {
            this.a(world, structureboundingbox, 1, 8, 8, 6, 8, 14, WorldGenMonumentPiece6.a);
        }

        int i;
        IBlockData iblockdata;

        for (i = 1; i <= 7; ++i) {
            iblockdata = WorldGenMonumentPiece6.b;
            if (i == 2 || i == 6) {
                iblockdata = WorldGenMonumentPiece6.a;
            }

            this.a(world, structureboundingbox, 0, i, 0, 0, i, 15, iblockdata, iblockdata, false);
            this.a(world, structureboundingbox, 7, i, 0, 7, i, 15, iblockdata, iblockdata, false);
            this.a(world, structureboundingbox, 1, i, 0, 6, i, 0, iblockdata, iblockdata, false);
            this.a(world, structureboundingbox, 1, i, 15, 6, i, 15, iblockdata, iblockdata, false);
        }

        for (i = 1; i <= 7; ++i) {
            iblockdata = WorldGenMonumentPiece6.c;
            if (i == 2 || i == 6) {
                iblockdata = WorldGenMonumentPiece6.e;
            }

            this.a(world, structureboundingbox, 3, i, 7, 4, i, 8, iblockdata, iblockdata, false);
        }

        if (worldgenmonumentstatetracker1.c[EnumDirection.SOUTH.a()]) {
            this.a(world, structureboundingbox, 3, 1, 0, 4, 2, 0, WorldGenMonumentPiece6.f, WorldGenMonumentPiece6.f, false);
        }

        if (worldgenmonumentstatetracker1.c[EnumDirection.EAST.a()]) {
            this.a(world, structureboundingbox, 7, 1, 3, 7, 2, 4, WorldGenMonumentPiece6.f, WorldGenMonumentPiece6.f, false);
        }

        if (worldgenmonumentstatetracker1.c[EnumDirection.WEST.a()]) {
            this.a(world, structureboundingbox, 0, 1, 3, 0, 2, 4, WorldGenMonumentPiece6.f, WorldGenMonumentPiece6.f, false);
        }

        if (worldgenmonumentstatetracker.c[EnumDirection.NORTH.a()]) {
            this.a(world, structureboundingbox, 3, 1, 15, 4, 2, 15, WorldGenMonumentPiece6.f, WorldGenMonumentPiece6.f, false);
        }

        if (worldgenmonumentstatetracker.c[EnumDirection.WEST.a()]) {
            this.a(world, structureboundingbox, 0, 1, 11, 0, 2, 12, WorldGenMonumentPiece6.f, WorldGenMonumentPiece6.f, false);
        }

        if (worldgenmonumentstatetracker.c[EnumDirection.EAST.a()]) {
            this.a(world, structureboundingbox, 7, 1, 11, 7, 2, 12, WorldGenMonumentPiece6.f, WorldGenMonumentPiece6.f, false);
        }

        if (worldgenmonumentstatetracker3.c[EnumDirection.SOUTH.a()]) {
            this.a(world, structureboundingbox, 3, 5, 0, 4, 6, 0, WorldGenMonumentPiece6.f, WorldGenMonumentPiece6.f, false);
        }

        if (worldgenmonumentstatetracker3.c[EnumDirection.EAST.a()]) {
            this.a(world, structureboundingbox, 7, 5, 3, 7, 6, 4, WorldGenMonumentPiece6.f, WorldGenMonumentPiece6.f, false);
            this.a(world, structureboundingbox, 5, 4, 2, 6, 4, 5, WorldGenMonumentPiece6.b, WorldGenMonumentPiece6.b, false);
            this.a(world, structureboundingbox, 6, 1, 2, 6, 3, 2, WorldGenMonumentPiece6.b, WorldGenMonumentPiece6.b, false);
            this.a(world, structureboundingbox, 6, 1, 5, 6, 3, 5, WorldGenMonumentPiece6.b, WorldGenMonumentPiece6.b, false);
        }

        if (worldgenmonumentstatetracker3.c[EnumDirection.WEST.a()]) {
            this.a(world, structureboundingbox, 0, 5, 3, 0, 6, 4, WorldGenMonumentPiece6.f, WorldGenMonumentPiece6.f, false);
            this.a(world, structureboundingbox, 1, 4, 2, 2, 4, 5, WorldGenMonumentPiece6.b, WorldGenMonumentPiece6.b, false);
            this.a(world, structureboundingbox, 1, 1, 2, 1, 3, 2, WorldGenMonumentPiece6.b, WorldGenMonumentPiece6.b, false);
            this.a(world, structureboundingbox, 1, 1, 5, 1, 3, 5, WorldGenMonumentPiece6.b, WorldGenMonumentPiece6.b, false);
        }

        if (worldgenmonumentstatetracker2.c[EnumDirection.NORTH.a()]) {
            this.a(world, structureboundingbox, 3, 5, 15, 4, 6, 15, WorldGenMonumentPiece6.f, WorldGenMonumentPiece6.f, false);
        }

        if (worldgenmonumentstatetracker2.c[EnumDirection.WEST.a()]) {
            this.a(world, structureboundingbox, 0, 5, 11, 0, 6, 12, WorldGenMonumentPiece6.f, WorldGenMonumentPiece6.f, false);
            this.a(world, structureboundingbox, 1, 4, 10, 2, 4, 13, WorldGenMonumentPiece6.b, WorldGenMonumentPiece6.b, false);
            this.a(world, structureboundingbox, 1, 1, 10, 1, 3, 10, WorldGenMonumentPiece6.b, WorldGenMonumentPiece6.b, false);
            this.a(world, structureboundingbox, 1, 1, 13, 1, 3, 13, WorldGenMonumentPiece6.b, WorldGenMonumentPiece6.b, false);
        }

        if (worldgenmonumentstatetracker2.c[EnumDirection.EAST.a()]) {
            this.a(world, structureboundingbox, 7, 5, 11, 7, 6, 12, WorldGenMonumentPiece6.f, WorldGenMonumentPiece6.f, false);
            this.a(world, structureboundingbox, 5, 4, 10, 6, 4, 13, WorldGenMonumentPiece6.b, WorldGenMonumentPiece6.b, false);
            this.a(world, structureboundingbox, 6, 1, 10, 6, 3, 10, WorldGenMonumentPiece6.b, WorldGenMonumentPiece6.b, false);
            this.a(world, structureboundingbox, 6, 1, 13, 6, 3, 13, WorldGenMonumentPiece6.b, WorldGenMonumentPiece6.b, false);
        }

        return true;
    }
}
