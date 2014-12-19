package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageWell extends WorldGenVillagePiece {

    public WorldGenVillageWell() {}

    public WorldGenVillageWell(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, int j, int k) {
        super(worldgenvillagestartpiece, i);
        this.m = EnumDirectionLimit.HORIZONTAL.a(random);
        switch (SwitchHelperDirection3.a[this.m.ordinal()]) {
        case 1:
        case 2:
            this.l = new StructureBoundingBox(j, 64, k, j + 6 - 1, 78, k + 6 - 1);
            break;

        default:
            this.l = new StructureBoundingBox(j, 64, k, j + 6 - 1, 78, k + 6 - 1);
        }

    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.l.a - 1, this.l.e - 4, this.l.c + 1, EnumDirection.WEST, this.d());
        WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.l.d + 1, this.l.e - 4, this.l.c + 1, EnumDirection.EAST, this.d());
        WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.l.a + 1, this.l.e - 4, this.l.c - 1, EnumDirection.NORTH, this.d());
        WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.l.a + 1, this.l.e - 4, this.l.f + 1, EnumDirection.SOUTH, this.d());
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.h < 0) {
            this.h = this.b(world, structureboundingbox);
            if (this.h < 0) {
                return true;
            }

            this.l.a(0, this.h - this.l.e + 3, 0);
        }

        this.a(world, structureboundingbox, 1, 0, 1, 4, 12, 4, Blocks.COBBLESTONE.getBlockData(), Blocks.FLOWING_WATER.getBlockData(), false);
        this.a(world, Blocks.AIR.getBlockData(), 2, 12, 2, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 3, 12, 2, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 2, 12, 3, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 3, 12, 3, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 1, 13, 1, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 1, 14, 1, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 4, 13, 1, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 4, 14, 1, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 1, 13, 4, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 1, 14, 4, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 4, 13, 4, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 4, 14, 4, structureboundingbox);
        this.a(world, structureboundingbox, 1, 15, 1, 4, 15, 4, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);

        for (int i = 0; i <= 5; ++i) {
            for (int j = 0; j <= 5; ++j) {
                if (j == 0 || j == 5 || i == 0 || i == 5) {
                    this.a(world, Blocks.GRAVEL.getBlockData(), j, 11, i, structureboundingbox);
                    this.b(world, j, 12, i, structureboundingbox);
                }
            }
        }

        return true;
    }
}
