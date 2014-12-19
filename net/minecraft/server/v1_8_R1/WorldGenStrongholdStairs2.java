package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdStairs2 extends WorldGenStrongholdPiece {

    private boolean a;

    public WorldGenStrongholdStairs2() {}

    public WorldGenStrongholdStairs2(int i, Random random, int j, int k) {
        super(i);
        this.a = true;
        this.m = EnumDirectionLimit.HORIZONTAL.a(random);
        this.d = WorldGenStrongholdDoorType.OPENING;
        switch (WorldGenStrongholdPieceWeight3.b[this.m.ordinal()]) {
        case 1:
        case 2:
            this.l = new StructureBoundingBox(j, 64, k, j + 5 - 1, 74, k + 5 - 1);
            break;

        default:
            this.l = new StructureBoundingBox(j, 64, k, j + 5 - 1, 74, k + 5 - 1);
        }

    }

    public WorldGenStrongholdStairs2(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(i);
        this.a = false;
        this.m = enumdirection;
        this.d = this.a(random);
        this.l = structureboundingbox;
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("Source", this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.getBoolean("Source");
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        if (this.a) {
            WorldGenStrongholdPieces.a(WorldGenStrongholdCrossing.class);
        }

        this.a((WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
    }

    public static WorldGenStrongholdStairs2 a(List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -7, 0, 5, 11, 5, enumdirection);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdStairs2(l, random, structureboundingbox, enumdirection) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 4, 10, 4, true, random, WorldGenStrongholdPieces.c());
            this.a(world, random, structureboundingbox, this.d, 1, 7, 0);
            this.a(world, random, structureboundingbox, WorldGenStrongholdDoorType.OPENING, 1, 1, 4);
            this.a(world, Blocks.STONEBRICK.getBlockData(), 2, 6, 1, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), 1, 5, 1, structureboundingbox);
            this.a(world, Blocks.STONE_SLAB.fromLegacyData(EnumStoneSlabVariant.STONE.a()), 1, 6, 1, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), 1, 5, 2, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), 1, 4, 3, structureboundingbox);
            this.a(world, Blocks.STONE_SLAB.fromLegacyData(EnumStoneSlabVariant.STONE.a()), 1, 5, 3, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), 2, 4, 3, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), 3, 3, 3, structureboundingbox);
            this.a(world, Blocks.STONE_SLAB.fromLegacyData(EnumStoneSlabVariant.STONE.a()), 3, 4, 3, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), 3, 3, 2, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), 3, 2, 1, structureboundingbox);
            this.a(world, Blocks.STONE_SLAB.fromLegacyData(EnumStoneSlabVariant.STONE.a()), 3, 3, 1, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), 2, 2, 1, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), 1, 1, 1, structureboundingbox);
            this.a(world, Blocks.STONE_SLAB.fromLegacyData(EnumStoneSlabVariant.STONE.a()), 1, 2, 1, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), 1, 1, 2, structureboundingbox);
            this.a(world, Blocks.STONE_SLAB.fromLegacyData(EnumStoneSlabVariant.STONE.a()), 1, 1, 3, structureboundingbox);
            return true;
        }
    }
}
