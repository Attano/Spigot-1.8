package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageHouse extends WorldGenVillagePiece {

    private boolean a;

    public WorldGenVillageHouse() {}

    public WorldGenVillageHouse(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(worldgenvillagestartpiece, i);
        this.m = enumdirection;
        this.l = structureboundingbox;
        this.a = random.nextBoolean();
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("Terrace", this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.getBoolean("Terrace");
    }

    public static WorldGenVillageHouse a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 5, 6, 5, enumdirection);

        return StructurePiece.a(list, structureboundingbox) != null ? null : new WorldGenVillageHouse(worldgenvillagestartpiece, l, random, structureboundingbox, enumdirection);
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.h < 0) {
            this.h = this.b(world, structureboundingbox);
            if (this.h < 0) {
                return true;
            }

            this.l.a(0, this.h - this.l.e + 6 - 1, 0);
        }

        this.a(world, structureboundingbox, 0, 0, 0, 4, 0, 4, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 4, 0, 4, 4, 4, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 4, 1, 3, 4, 3, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 0, 1, 0, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 0, 2, 0, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 0, 3, 0, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 4, 1, 0, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 4, 2, 0, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 4, 3, 0, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 0, 1, 4, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 0, 2, 4, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 0, 3, 4, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 4, 1, 4, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 4, 2, 4, structureboundingbox);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 4, 3, 4, structureboundingbox);
        this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 3, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 4, 1, 1, 4, 3, 3, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 1, 4, 3, 3, 4, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 0, 2, 2, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 2, 2, 4, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 4, 2, 2, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 1, 1, 0, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 1, 2, 0, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 1, 3, 0, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 2, 3, 0, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 3, 3, 0, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 3, 2, 0, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 3, 1, 0, structureboundingbox);
        if (this.a(world, 2, 0, -1, structureboundingbox).getBlock().getMaterial() == Material.AIR && this.a(world, 2, -1, -1, structureboundingbox).getBlock().getMaterial() != Material.AIR) {
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(this.a(Blocks.STONE_STAIRS, 3)), 2, 0, -1, structureboundingbox);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 3, 3, 3, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        if (this.a) {
            this.a(world, Blocks.FENCE.getBlockData(), 0, 5, 0, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 1, 5, 0, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 2, 5, 0, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 3, 5, 0, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 4, 5, 0, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 0, 5, 4, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 1, 5, 4, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 2, 5, 4, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 3, 5, 4, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 4, 5, 4, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 4, 5, 1, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 4, 5, 2, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 4, 5, 3, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 0, 5, 1, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 0, 5, 2, structureboundingbox);
            this.a(world, Blocks.FENCE.getBlockData(), 0, 5, 3, structureboundingbox);
        }

        int i;

        if (this.a) {
            i = this.a(Blocks.LADDER, 3);
            this.a(world, Blocks.LADDER.fromLegacyData(i), 3, 1, 3, structureboundingbox);
            this.a(world, Blocks.LADDER.fromLegacyData(i), 3, 2, 3, structureboundingbox);
            this.a(world, Blocks.LADDER.fromLegacyData(i), 3, 3, 3, structureboundingbox);
            this.a(world, Blocks.LADDER.fromLegacyData(i), 3, 4, 3, structureboundingbox);
        }

        this.a(world, Blocks.TORCH.getBlockData().set(BlockTorch.FACING, this.m), 2, 3, 1, structureboundingbox);

        for (i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                this.b(world, j, 6, i, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE.getBlockData(), j, -1, i, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 1, 1, 2, 1);
        return true;
    }
}
