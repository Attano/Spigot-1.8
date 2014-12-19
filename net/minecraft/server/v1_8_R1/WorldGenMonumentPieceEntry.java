package net.minecraft.server;

import java.util.Random;

public class WorldGenMonumentPieceEntry extends WorldGenMonumentPiece {

    public WorldGenMonumentPieceEntry() {}

    public WorldGenMonumentPieceEntry(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker) {
        super(1, enumdirection, worldgenmonumentstatetracker, 1, 1, 1);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 3, 0, 2, 3, 7, WorldGenMonumentPieceEntry.b, WorldGenMonumentPieceEntry.b, false);
        this.a(world, structureboundingbox, 5, 3, 0, 7, 3, 7, WorldGenMonumentPieceEntry.b, WorldGenMonumentPieceEntry.b, false);
        this.a(world, structureboundingbox, 0, 2, 0, 1, 2, 7, WorldGenMonumentPieceEntry.b, WorldGenMonumentPieceEntry.b, false);
        this.a(world, structureboundingbox, 6, 2, 0, 7, 2, 7, WorldGenMonumentPieceEntry.b, WorldGenMonumentPieceEntry.b, false);
        this.a(world, structureboundingbox, 0, 1, 0, 0, 1, 7, WorldGenMonumentPieceEntry.b, WorldGenMonumentPieceEntry.b, false);
        this.a(world, structureboundingbox, 7, 1, 0, 7, 1, 7, WorldGenMonumentPieceEntry.b, WorldGenMonumentPieceEntry.b, false);
        this.a(world, structureboundingbox, 0, 1, 7, 7, 3, 7, WorldGenMonumentPieceEntry.b, WorldGenMonumentPieceEntry.b, false);
        this.a(world, structureboundingbox, 1, 1, 0, 2, 3, 0, WorldGenMonumentPieceEntry.b, WorldGenMonumentPieceEntry.b, false);
        this.a(world, structureboundingbox, 5, 1, 0, 6, 3, 0, WorldGenMonumentPieceEntry.b, WorldGenMonumentPieceEntry.b, false);
        if (this.k.c[EnumDirection.NORTH.a()]) {
            this.a(world, structureboundingbox, 3, 1, 7, 4, 2, 7, WorldGenMonumentPieceEntry.f, WorldGenMonumentPieceEntry.f, false);
        }

        if (this.k.c[EnumDirection.WEST.a()]) {
            this.a(world, structureboundingbox, 0, 1, 3, 1, 2, 4, WorldGenMonumentPieceEntry.f, WorldGenMonumentPieceEntry.f, false);
        }

        if (this.k.c[EnumDirection.EAST.a()]) {
            this.a(world, structureboundingbox, 6, 1, 3, 7, 2, 4, WorldGenMonumentPieceEntry.f, WorldGenMonumentPieceEntry.f, false);
        }

        return true;
    }
}
