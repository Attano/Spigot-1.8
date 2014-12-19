package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenNetherPiece6 extends WorldGenNetherPiece {

    public WorldGenNetherPiece6() {}

    public WorldGenNetherPiece6(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(i);
        this.m = enumdirection;
        this.l = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        this.a((WorldGenNetherPiece15) structurepiece, list, random, 5, 3, true);
    }

    public static WorldGenNetherPiece6 a(List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -5, -3, 0, 13, 14, 13, enumdirection);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenNetherPiece6(l, random, structureboundingbox, enumdirection) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 5, 0, 12, 13, 12, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 5, 8, 0, 7, 8, 0, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);

        int i;

        for (i = 1; i <= 11; i += 2) {
            this.a(world, structureboundingbox, i, 10, 0, i, 11, 0, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, i, 10, 12, i, 11, 12, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 10, i, 0, 11, i, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 12, 10, i, 12, 11, i, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, Blocks.NETHER_BRICK.getBlockData(), i, 13, 0, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK.getBlockData(), i, 13, 12, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK.getBlockData(), 0, 13, i, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK.getBlockData(), 12, 13, i, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), i + 1, 13, 0, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), i + 1, 13, 12, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 0, 13, i + 1, structureboundingbox);
            this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 12, 13, i + 1, structureboundingbox);
        }

        this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 0, 13, 0, structureboundingbox);
        this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 0, 13, 12, structureboundingbox);
        this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 0, 13, 0, structureboundingbox);
        this.a(world, Blocks.NETHER_BRICK_FENCE.getBlockData(), 12, 13, 0, structureboundingbox);

        for (i = 3; i <= 9; i += 2) {
            this.a(world, structureboundingbox, 1, 7, i, 1, 8, i, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
            this.a(world, structureboundingbox, 11, 7, i, 11, 8, i, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
        }

        this.a(world, structureboundingbox, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);

        int j;

        for (i = 4; i <= 8; ++i) {
            for (j = 0; j <= 2; ++j) {
                this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, 12 - j, structureboundingbox);
            }
        }

        for (i = 0; i <= 2; ++i) {
            for (j = 4; j <= 8; ++j) {
                this.b(world, Blocks.NETHER_BRICK.getBlockData(), i, -1, j, structureboundingbox);
                this.b(world, Blocks.NETHER_BRICK.getBlockData(), 12 - i, -1, j, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 5, 5, 5, 7, 5, 7, Blocks.NETHER_BRICK.getBlockData(), Blocks.NETHER_BRICK.getBlockData(), false);
        this.a(world, structureboundingbox, 6, 1, 6, 6, 4, 6, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, Blocks.NETHER_BRICK.getBlockData(), 6, 0, 6, structureboundingbox);
        this.a(world, Blocks.FLOWING_LAVA.getBlockData(), 6, 5, 6, structureboundingbox);
        BlockPosition blockposition = new BlockPosition(this.a(6, 6), this.d(5), this.b(6, 6));

        if (structureboundingbox.b((BaseBlockPosition) blockposition)) {
            world.a((Block) Blocks.FLOWING_LAVA, blockposition, random);
        }

        return true;
    }
}
