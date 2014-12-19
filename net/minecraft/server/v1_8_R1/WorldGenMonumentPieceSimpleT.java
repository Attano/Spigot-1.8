package net.minecraft.server;

import java.util.Random;

public class WorldGenMonumentPieceSimpleT extends WorldGenMonumentPiece {

    public WorldGenMonumentPieceSimpleT() {}

    public WorldGenMonumentPieceSimpleT(EnumDirection enumdirection, WorldGenMonumentStateTracker worldgenmonumentstatetracker, Random random) {
        super(1, enumdirection, worldgenmonumentstatetracker, 1, 1, 1);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.k.a / 25 > 0) {
            this.a(world, structureboundingbox, 0, 0, this.k.c[EnumDirection.DOWN.a()]);
        }

        if (this.k.b[EnumDirection.UP.a()] == null) {
            this.a(world, structureboundingbox, 1, 4, 1, 6, 4, 6, WorldGenMonumentPieceSimpleT.a);
        }

        for (int i = 1; i <= 6; ++i) {
            for (int j = 1; j <= 6; ++j) {
                if (random.nextInt(3) != 0) {
                    int k = 2 + (random.nextInt(4) == 0 ? 0 : 1);

                    this.a(world, structureboundingbox, i, k, j, i, 3, j, Blocks.SPONGE.fromLegacyData(1), Blocks.SPONGE.fromLegacyData(1), false);
                }
            }
        }

        this.a(world, structureboundingbox, 0, 1, 0, 0, 1, 7, WorldGenMonumentPieceSimpleT.b, WorldGenMonumentPieceSimpleT.b, false);
        this.a(world, structureboundingbox, 7, 1, 0, 7, 1, 7, WorldGenMonumentPieceSimpleT.b, WorldGenMonumentPieceSimpleT.b, false);
        this.a(world, structureboundingbox, 1, 1, 0, 6, 1, 0, WorldGenMonumentPieceSimpleT.b, WorldGenMonumentPieceSimpleT.b, false);
        this.a(world, structureboundingbox, 1, 1, 7, 6, 1, 7, WorldGenMonumentPieceSimpleT.b, WorldGenMonumentPieceSimpleT.b, false);
        this.a(world, structureboundingbox, 0, 2, 0, 0, 2, 7, WorldGenMonumentPieceSimpleT.c, WorldGenMonumentPieceSimpleT.c, false);
        this.a(world, structureboundingbox, 7, 2, 0, 7, 2, 7, WorldGenMonumentPieceSimpleT.c, WorldGenMonumentPieceSimpleT.c, false);
        this.a(world, structureboundingbox, 1, 2, 0, 6, 2, 0, WorldGenMonumentPieceSimpleT.c, WorldGenMonumentPieceSimpleT.c, false);
        this.a(world, structureboundingbox, 1, 2, 7, 6, 2, 7, WorldGenMonumentPieceSimpleT.c, WorldGenMonumentPieceSimpleT.c, false);
        this.a(world, structureboundingbox, 0, 3, 0, 0, 3, 7, WorldGenMonumentPieceSimpleT.b, WorldGenMonumentPieceSimpleT.b, false);
        this.a(world, structureboundingbox, 7, 3, 0, 7, 3, 7, WorldGenMonumentPieceSimpleT.b, WorldGenMonumentPieceSimpleT.b, false);
        this.a(world, structureboundingbox, 1, 3, 0, 6, 3, 0, WorldGenMonumentPieceSimpleT.b, WorldGenMonumentPieceSimpleT.b, false);
        this.a(world, structureboundingbox, 1, 3, 7, 6, 3, 7, WorldGenMonumentPieceSimpleT.b, WorldGenMonumentPieceSimpleT.b, false);
        this.a(world, structureboundingbox, 0, 1, 3, 0, 2, 4, WorldGenMonumentPieceSimpleT.c, WorldGenMonumentPieceSimpleT.c, false);
        this.a(world, structureboundingbox, 7, 1, 3, 7, 2, 4, WorldGenMonumentPieceSimpleT.c, WorldGenMonumentPieceSimpleT.c, false);
        this.a(world, structureboundingbox, 3, 1, 0, 4, 2, 0, WorldGenMonumentPieceSimpleT.c, WorldGenMonumentPieceSimpleT.c, false);
        this.a(world, structureboundingbox, 3, 1, 7, 4, 2, 7, WorldGenMonumentPieceSimpleT.c, WorldGenMonumentPieceSimpleT.c, false);
        if (this.k.c[EnumDirection.SOUTH.a()]) {
            this.a(world, structureboundingbox, 3, 1, 0, 4, 2, 0, WorldGenMonumentPieceSimpleT.f, WorldGenMonumentPieceSimpleT.f, false);
        }

        return true;
    }
}
