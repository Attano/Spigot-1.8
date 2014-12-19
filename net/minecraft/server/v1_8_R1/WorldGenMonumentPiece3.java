package net.minecraft.server;

import java.util.Random;

public class WorldGenMonumentPiece3 extends WorldGenMonumentPiece {

    public WorldGenMonumentPiece3() {}

    public WorldGenMonumentPiece3(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        super(1, enumdirection, worldgenmonumentstatetracker, 2, 1, 1);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        WorldGenMonumentStateTracker worldgenmonumentstatetracker = this.k.b[EnumDirection.EAST.a()];
        WorldGenMonumentStateTracker worldgenmonumentstatetracker1 = this.k;

        if (this.k.a / 25 > 0) {
            this.a(world, structureboundingbox, 8, 0, worldgenmonumentstatetracker.c[EnumDirection.DOWN.a()]);
            this.a(world, structureboundingbox, 0, 0, worldgenmonumentstatetracker1.c[EnumDirection.DOWN.a()]);
        }

        if (worldgenmonumentstatetracker1.b[EnumDirection.UP.a()] == null) {
            this.a(world, structureboundingbox, 1, 4, 1, 7, 4, 6, WorldGenMonumentPiece3.a);
        }

        if (worldgenmonumentstatetracker.b[EnumDirection.UP.a()] == null) {
            this.a(world, structureboundingbox, 8, 4, 1, 14, 4, 6, WorldGenMonumentPiece3.a);
        }

        this.a(world, structureboundingbox, 0, 3, 0, 0, 3, 7, WorldGenMonumentPiece3.b, WorldGenMonumentPiece3.b, false);
        this.a(world, structureboundingbox, 15, 3, 0, 15, 3, 7, WorldGenMonumentPiece3.b, WorldGenMonumentPiece3.b, false);
        this.a(world, structureboundingbox, 1, 3, 0, 15, 3, 0, WorldGenMonumentPiece3.b, WorldGenMonumentPiece3.b, false);
        this.a(world, structureboundingbox, 1, 3, 7, 14, 3, 7, WorldGenMonumentPiece3.b, WorldGenMonumentPiece3.b, false);
        this.a(world, structureboundingbox, 0, 2, 0, 0, 2, 7, WorldGenMonumentPiece3.a, WorldGenMonumentPiece3.a, false);
        this.a(world, structureboundingbox, 15, 2, 0, 15, 2, 7, WorldGenMonumentPiece3.a, WorldGenMonumentPiece3.a, false);
        this.a(world, structureboundingbox, 1, 2, 0, 15, 2, 0, WorldGenMonumentPiece3.a, WorldGenMonumentPiece3.a, false);
        this.a(world, structureboundingbox, 1, 2, 7, 14, 2, 7, WorldGenMonumentPiece3.a, WorldGenMonumentPiece3.a, false);
        this.a(world, structureboundingbox, 0, 1, 0, 0, 1, 7, WorldGenMonumentPiece3.b, WorldGenMonumentPiece3.b, false);
        this.a(world, structureboundingbox, 15, 1, 0, 15, 1, 7, WorldGenMonumentPiece3.b, WorldGenMonumentPiece3.b, false);
        this.a(world, structureboundingbox, 1, 1, 0, 15, 1, 0, WorldGenMonumentPiece3.b, WorldGenMonumentPiece3.b, false);
        this.a(world, structureboundingbox, 1, 1, 7, 14, 1, 7, WorldGenMonumentPiece3.b, WorldGenMonumentPiece3.b, false);
        this.a(world, structureboundingbox, 5, 1, 0, 10, 1, 4, WorldGenMonumentPiece3.b, WorldGenMonumentPiece3.b, false);
        this.a(world, structureboundingbox, 6, 2, 0, 9, 2, 3, WorldGenMonumentPiece3.a, WorldGenMonumentPiece3.a, false);
        this.a(world, structureboundingbox, 5, 3, 0, 10, 3, 4, WorldGenMonumentPiece3.b, WorldGenMonumentPiece3.b, false);
        this.a(world, WorldGenMonumentPiece3.e, 6, 2, 3, structureboundingbox);
        this.a(world, WorldGenMonumentPiece3.e, 9, 2, 3, structureboundingbox);
        if (worldgenmonumentstatetracker1.c[EnumDirection.SOUTH.a()]) {
            this.a(world, structureboundingbox, 3, 1, 0, 4, 2, 0, WorldGenMonumentPiece3.f, WorldGenMonumentPiece3.f, false);
        }

        if (worldgenmonumentstatetracker1.c[EnumDirection.NORTH.a()]) {
            this.a(world, structureboundingbox, 3, 1, 7, 4, 2, 7, WorldGenMonumentPiece3.f, WorldGenMonumentPiece3.f, false);
        }

        if (worldgenmonumentstatetracker1.c[EnumDirection.WEST.a()]) {
            this.a(world, structureboundingbox, 0, 1, 3, 0, 2, 4, WorldGenMonumentPiece3.f, WorldGenMonumentPiece3.f, false);
        }

        if (worldgenmonumentstatetracker.c[EnumDirection.SOUTH.a()]) {
            this.a(world, structureboundingbox, 11, 1, 0, 12, 2, 0, WorldGenMonumentPiece3.f, WorldGenMonumentPiece3.f, false);
        }

        if (worldgenmonumentstatetracker.c[EnumDirection.NORTH.a()]) {
            this.a(world, structureboundingbox, 11, 1, 7, 12, 2, 7, WorldGenMonumentPiece3.f, WorldGenMonumentPiece3.f, false);
        }

        if (worldgenmonumentstatetracker.c[EnumDirection.EAST.a()]) {
            this.a(world, structureboundingbox, 15, 1, 3, 15, 2, 4, WorldGenMonumentPiece3.f, WorldGenMonumentPiece3.f, false);
        }

        return true;
    }
}
