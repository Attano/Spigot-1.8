package net.minecraft.server;

import java.util.Random;

public class WorldGenMonumentPiecePenthouse extends WorldGenMonumentPiece {

    public WorldGenMonumentPiecePenthouse() {}

    public WorldGenMonumentPiecePenthouse(EnumDirection enumdirection, StructureBoundingBox structureboundingbox) {
        super(enumdirection, structureboundingbox);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 2, -1, 2, 11, -1, 11, WorldGenMonumentPiecePenthouse.b, WorldGenMonumentPiecePenthouse.b, false);
        this.a(world, structureboundingbox, 0, -1, 0, 1, -1, 11, WorldGenMonumentPiecePenthouse.a, WorldGenMonumentPiecePenthouse.a, false);
        this.a(world, structureboundingbox, 12, -1, 0, 13, -1, 11, WorldGenMonumentPiecePenthouse.a, WorldGenMonumentPiecePenthouse.a, false);
        this.a(world, structureboundingbox, 2, -1, 0, 11, -1, 1, WorldGenMonumentPiecePenthouse.a, WorldGenMonumentPiecePenthouse.a, false);
        this.a(world, structureboundingbox, 2, -1, 12, 11, -1, 13, WorldGenMonumentPiecePenthouse.a, WorldGenMonumentPiecePenthouse.a, false);
        this.a(world, structureboundingbox, 0, 0, 0, 0, 0, 13, WorldGenMonumentPiecePenthouse.b, WorldGenMonumentPiecePenthouse.b, false);
        this.a(world, structureboundingbox, 13, 0, 0, 13, 0, 13, WorldGenMonumentPiecePenthouse.b, WorldGenMonumentPiecePenthouse.b, false);
        this.a(world, structureboundingbox, 1, 0, 0, 12, 0, 0, WorldGenMonumentPiecePenthouse.b, WorldGenMonumentPiecePenthouse.b, false);
        this.a(world, structureboundingbox, 1, 0, 13, 12, 0, 13, WorldGenMonumentPiecePenthouse.b, WorldGenMonumentPiecePenthouse.b, false);

        for (int i = 2; i <= 11; i += 3) {
            this.a(world, WorldGenMonumentPiecePenthouse.e, 0, 0, i, structureboundingbox);
            this.a(world, WorldGenMonumentPiecePenthouse.e, 13, 0, i, structureboundingbox);
            this.a(world, WorldGenMonumentPiecePenthouse.e, i, 0, 0, structureboundingbox);
        }

        this.a(world, structureboundingbox, 2, 0, 3, 4, 0, 9, WorldGenMonumentPiecePenthouse.b, WorldGenMonumentPiecePenthouse.b, false);
        this.a(world, structureboundingbox, 9, 0, 3, 11, 0, 9, WorldGenMonumentPiecePenthouse.b, WorldGenMonumentPiecePenthouse.b, false);
        this.a(world, structureboundingbox, 4, 0, 9, 9, 0, 11, WorldGenMonumentPiecePenthouse.b, WorldGenMonumentPiecePenthouse.b, false);
        this.a(world, WorldGenMonumentPiecePenthouse.b, 5, 0, 8, structureboundingbox);
        this.a(world, WorldGenMonumentPiecePenthouse.b, 8, 0, 8, structureboundingbox);
        this.a(world, WorldGenMonumentPiecePenthouse.b, 10, 0, 10, structureboundingbox);
        this.a(world, WorldGenMonumentPiecePenthouse.b, 3, 0, 10, structureboundingbox);
        this.a(world, structureboundingbox, 3, 0, 3, 3, 0, 7, WorldGenMonumentPiecePenthouse.c, WorldGenMonumentPiecePenthouse.c, false);
        this.a(world, structureboundingbox, 10, 0, 3, 10, 0, 7, WorldGenMonumentPiecePenthouse.c, WorldGenMonumentPiecePenthouse.c, false);
        this.a(world, structureboundingbox, 6, 0, 10, 7, 0, 10, WorldGenMonumentPiecePenthouse.c, WorldGenMonumentPiecePenthouse.c, false);
        byte b0 = 3;

        for (int j = 0; j < 2; ++j) {
            for (int k = 2; k <= 8; k += 3) {
                this.a(world, structureboundingbox, b0, 0, k, b0, 2, k, WorldGenMonumentPiecePenthouse.b, WorldGenMonumentPiecePenthouse.b, false);
            }

            b0 = 10;
        }

        this.a(world, structureboundingbox, 5, 0, 10, 5, 2, 10, WorldGenMonumentPiecePenthouse.b, WorldGenMonumentPiecePenthouse.b, false);
        this.a(world, structureboundingbox, 8, 0, 10, 8, 2, 10, WorldGenMonumentPiecePenthouse.b, WorldGenMonumentPiecePenthouse.b, false);
        this.a(world, structureboundingbox, 6, -1, 7, 7, -1, 8, WorldGenMonumentPiecePenthouse.c, WorldGenMonumentPiecePenthouse.c, false);
        this.a(world, structureboundingbox, 6, -1, 3, 7, -1, 4, WorldGenMonumentPiecePenthouse.f, WorldGenMonumentPiecePenthouse.f, false);
        this.a(world, structureboundingbox, 6, 1, 6);
        return true;
    }
}
