package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageLight extends WorldGenVillagePiece {

    public WorldGenVillageLight() {}

    public WorldGenVillageLight(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(worldgenvillagestartpiece, i);
        this.m = enumdirection;
        this.l = structureboundingbox;
    }

    public static StructureBoundingBox a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 3, 4, 2, enumdirection);

        return StructurePiece.a(list, structureboundingbox) != null ? null : structureboundingbox;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.h < 0) {
            this.h = this.b(world, structureboundingbox);
            if (this.h < 0) {
                return true;
            }

            this.l.a(0, this.h - this.l.e + 4 - 1, 0);
        }

        this.a(world, structureboundingbox, 0, 0, 0, 2, 3, 1, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, Blocks.FENCE.getBlockData(), 1, 0, 0, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 1, 1, 0, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 1, 2, 0, structureboundingbox);
        this.a(world, Blocks.WOOL.fromLegacyData(EnumColor.WHITE.getInvColorIndex()), 1, 3, 0, structureboundingbox);
        boolean flag = this.m == EnumDirection.EAST || this.m == EnumDirection.NORTH;

        this.a(world, Blocks.TORCH.getBlockData().set(BlockTorch.FACING, this.m.e()), flag ? 2 : 0, 3, 0, structureboundingbox);
        this.a(world, Blocks.TORCH.getBlockData().set(BlockTorch.FACING, this.m), 1, 3, 1, structureboundingbox);
        this.a(world, Blocks.TORCH.getBlockData().set(BlockTorch.FACING, this.m.f()), flag ? 0 : 2, 3, 0, structureboundingbox);
        this.a(world, Blocks.TORCH.getBlockData().set(BlockTorch.FACING, this.m.opposite()), 1, 3, -1, structureboundingbox);
        return true;
    }
}
