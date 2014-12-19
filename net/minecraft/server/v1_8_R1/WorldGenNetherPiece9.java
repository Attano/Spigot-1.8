package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece9 extends WorldGenNetherPiece {

    public WorldGenNetherPiece9() {}

    public WorldGenNetherPiece9(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(i);
        this.m = enumdirection;
        this.l = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenNetherPiece15) structurepiece, list, random, 1, 0, true);
    }

    public static WorldGenNetherPiece9 a(List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, 0, 0, 5, 7, 5, enumdirection);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece9(l, random, structureboundingbox, enumdirection) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 2, 0, 4, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 3, 1, 0, 4, 1, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 3, 3, 0, 4, 3, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
        this.a(world, structureboundingbox, 4, 3, 1, 4, 4, 1, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
        this.a(world, structureboundingbox, 4, 3, 3, 4, 4, 3, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);

        for (int i = 0; i <= 4; ++i) {
            for (int j = 0; j <= 4; ++j) {
                this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
            }
        }

        return true;
    }
}
