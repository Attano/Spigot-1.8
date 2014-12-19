package net.minecraft.server;

import java.util.Random;

public class WorldGenMonumentPiece7 extends WorldGenMonumentPiece {

    public WorldGenMonumentPiece7() {}

    public WorldGenMonumentPiece7(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        super(1, enumdirection, worldgenmonumentstatetracker, 1, 1, 2);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        WorldGenMonumentStateTracker worldgenmonumentstatetracker = this.k.b[EnumDirection.NORTH.a()];
        WorldGenMonumentStateTracker worldgenmonumentstatetracker1 = this.k;

        if (this.k.a / 25 > 0) {
            this.a(world, structureboundingbox, 0, 8, worldgenmonumentstatetracker.c[EnumDirection.DOWN.a()]);
            this.a(world, structureboundingbox, 0, 0, worldgenmonumentstatetracker1.c[EnumDirection.DOWN.a()]);
        }

        if (worldgenmonumentstatetracker1.b[EnumDirection.UP.a()] == null) {
            this.a(world, structureboundingbox, 1, 4, 1, 6, 4, 7, WorldGenMonumentPiece7.a);
        }

        if (worldgenmonumentstatetracker.b[EnumDirection.UP.a()] == null) {
            this.a(world, structureboundingbox, 1, 4, 8, 6, 4, 14, WorldGenMonumentPiece7.a);
        }

        this.a(world, structureboundingbox, 0, 3, 0, 0, 3, 15, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 7, 3, 0, 7, 3, 15, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 1, 3, 0, 7, 3, 0, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 1, 3, 15, 6, 3, 15, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 0, 2, 0, 0, 2, 15, WorldGenMonumentPiece7.a, WorldGenMonumentPiece7.a, false);
        this.a(world, structureboundingbox, 7, 2, 0, 7, 2, 15, WorldGenMonumentPiece7.a, WorldGenMonumentPiece7.a, false);
        this.a(world, structureboundingbox, 1, 2, 0, 7, 2, 0, WorldGenMonumentPiece7.a, WorldGenMonumentPiece7.a, false);
        this.a(world, structureboundingbox, 1, 2, 15, 6, 2, 15, WorldGenMonumentPiece7.a, WorldGenMonumentPiece7.a, false);
        this.a(world, structureboundingbox, 0, 1, 0, 0, 1, 15, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 7, 1, 0, 7, 1, 15, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 1, 1, 0, 7, 1, 0, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 1, 1, 15, 6, 1, 15, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 1, 1, 1, 1, 1, 2, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 6, 1, 1, 6, 1, 2, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 1, 3, 1, 1, 3, 2, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 6, 3, 1, 6, 3, 2, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 1, 1, 13, 1, 1, 14, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 6, 1, 13, 6, 1, 14, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 1, 3, 13, 1, 3, 14, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 6, 3, 13, 6, 3, 14, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 2, 1, 6, 2, 3, 6, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 5, 1, 6, 5, 3, 6, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 2, 1, 9, 2, 3, 9, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 5, 1, 9, 5, 3, 9, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 3, 2, 6, 4, 2, 6, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 3, 2, 9, 4, 2, 9, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 2, 2, 7, 2, 2, 8, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, structureboundingbox, 5, 2, 7, 5, 2, 8, WorldGenMonumentPiece7.b, WorldGenMonumentPiece7.b, false);
        this.a(world, WorldGenMonumentPiece7.e, 2, 2, 5, structureboundingbox);
        this.a(world, WorldGenMonumentPiece7.e, 5, 2, 5, structureboundingbox);
        this.a(world, WorldGenMonumentPiece7.e, 2, 2, 10, structureboundingbox);
        this.a(world, WorldGenMonumentPiece7.e, 5, 2, 10, structureboundingbox);
        this.a(world, WorldGenMonumentPiece7.b, 2, 3, 5, structureboundingbox);
        this.a(world, WorldGenMonumentPiece7.b, 5, 3, 5, structureboundingbox);
        this.a(world, WorldGenMonumentPiece7.b, 2, 3, 10, structureboundingbox);
        this.a(world, WorldGenMonumentPiece7.b, 5, 3, 10, structureboundingbox);
        if (worldgenmonumentstatetracker1.c[EnumDirection.SOUTH.a()]) {
            this.a(world, structureboundingbox, 3, 1, 0, 4, 2, 0, WorldGenMonumentPiece7.f, WorldGenMonumentPiece7.f, false);
        }

        if (worldgenmonumentstatetracker1.c[EnumDirection.EAST.a()]) {
            this.a(world, structureboundingbox, 7, 1, 3, 7, 2, 4, WorldGenMonumentPiece7.f, WorldGenMonumentPiece7.f, false);
        }

        if (worldgenmonumentstatetracker1.c[EnumDirection.WEST.a()]) {
            this.a(world, structureboundingbox, 0, 1, 3, 0, 2, 4, WorldGenMonumentPiece7.f, WorldGenMonumentPiece7.f, false);
        }

        if (worldgenmonumentstatetracker.c[EnumDirection.NORTH.a()]) {
            this.a(world, structureboundingbox, 3, 1, 15, 4, 2, 15, WorldGenMonumentPiece7.f, WorldGenMonumentPiece7.f, false);
        }

        if (worldgenmonumentstatetracker.c[EnumDirection.WEST.a()]) {
            this.a(world, structureboundingbox, 0, 1, 11, 0, 2, 12, WorldGenMonumentPiece7.f, WorldGenMonumentPiece7.f, false);
        }

        if (worldgenmonumentstatetracker.c[EnumDirection.EAST.a()]) {
            this.a(world, structureboundingbox, 7, 1, 11, 7, 2, 12, WorldGenMonumentPiece7.f, WorldGenMonumentPiece7.f, false);
        }

        return true;
    }
}
