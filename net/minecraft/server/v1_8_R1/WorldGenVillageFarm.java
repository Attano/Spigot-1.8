package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageFarm extends WorldGenVillagePiece {

    private Block a;
    private Block b;

    public WorldGenVillageFarm() {}

    public WorldGenVillageFarm(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(worldgenvillagestartpiece, i);
        this.m = enumdirection;
        this.l = structureboundingbox;
        this.a = this.a(random);
        this.b = this.a(random);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setInt("CA", Block.REGISTRY.b(this.a));
        nbttagcompound.setInt("CB", Block.REGISTRY.b(this.b));
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = Block.getById(nbttagcompound.getInt("CA"));
        this.b = Block.getById(nbttagcompound.getInt("CB"));
    }

    private Block a(Random random) {
        switch (random.nextInt(5)) {
        case 0:
            return Blocks.CARROTS;

        case 1:
            return Blocks.POTATOES;

        default:
            return Blocks.WHEAT;
        }
    }

    public static WorldGenVillageFarm a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 7, 4, 9, enumdirection);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageFarm(worldgenvillagestartpiece, l, random, structureboundingbox, enumdirection) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.h < 0) {
            this.h = this.b(world, structureboundingbox);
            if (this.h < 0) {
                return true;
            }

            this.l.a(0, this.h - this.l.e + 4 - 1, 0);
        }

        this.a(world, structureboundingbox, 0, 1, 0, 6, 4, 8, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 0, 1, 2, 0, 7, Blocks.FARMLAND.getBlockData(), Blocks.FARMLAND.getBlockData(), false);
        this.a(world, structureboundingbox, 4, 0, 1, 5, 0, 7, Blocks.FARMLAND.getBlockData(), Blocks.FARMLAND.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 0, 0, 0, 0, 8, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        this.a(world, structureboundingbox, 6, 0, 0, 6, 0, 8, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 0, 0, 5, 0, 0, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 0, 8, 5, 0, 8, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        this.a(world, structureboundingbox, 3, 0, 1, 3, 0, 7, Blocks.WATER.getBlockData(), Blocks.WATER.getBlockData(), false);

        int i;

        for (i = 1; i <= 7; ++i) {
            this.a(world, this.a.fromLegacyData(MathHelper.nextInt(random, 2, 7)), 1, 1, i, structureboundingbox);
            this.a(world, this.a.fromLegacyData(MathHelper.nextInt(random, 2, 7)), 2, 1, i, structureboundingbox);
            this.a(world, this.b.fromLegacyData(MathHelper.nextInt(random, 2, 7)), 4, 1, i, structureboundingbox);
            this.a(world, this.b.fromLegacyData(MathHelper.nextInt(random, 2, 7)), 5, 1, i, structureboundingbox);
        }

        for (i = 0; i < 9; ++i) {
            for (int j = 0; j < 7; ++j) {
                this.b(world, j, 4, i, structureboundingbox);
                this.b(world, Blocks.DIRT.getBlockData(), j, -1, i, structureboundingbox);
            }
        }

        return true;
    }
}
