package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageHut extends WorldGenVillagePiece {

    private boolean a;
    private int b;

    public WorldGenVillageHut() {}

    public WorldGenVillageHut(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(worldgenvillagestartpiece, i);
        this.m = enumdirection;
        this.l = structureboundingbox;
        this.a = random.nextBoolean();
        this.b = random.nextInt(3);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setInt("T", this.b);
        nbttagcompound.setBoolean("C", this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.b = nbttagcompound.getInt("T");
        this.a = nbttagcompound.getBoolean("C");
    }

    public static WorldGenVillageHut a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 4, 6, 5, enumdirection);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageHut(worldgenvillagestartpiece, l, random, structureboundingbox, enumdirection) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.h < 0) {
            this.h = this.b(world, structureboundingbox);
            if (this.h < 0) {
                return true;
            }

            this.l.a(0, this.h - this.l.e + 6 - 1, 0);
        }

        this.a(world, structureboundingbox, 1, 1, 1, 3, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 0, 0, 3, 0, 4, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 0, 1, 2, 0, 3, Blocks.DIRT.getBlockData(), Blocks.DIRT.getBlockData(), false);
        if (this.a) {
            this.a(world, structureboundingbox, 1, 4, 1, 2, 4, 3, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        } else {
            this.a(world, structureboundingbox, 1, 5, 1, 2, 5, 3, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        }

        this.a(world, Blocks.LOG.getBlockData(), 1, 4, 0, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 2, 4, 0, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 1, 4, 4, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 2, 4, 4, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 0, 4, 1, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 0, 4, 2, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 0, 4, 3, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 3, 4, 1, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 3, 4, 2, structureboundingbox);
        this.a(world, Blocks.LOG.getBlockData(), 3, 4, 3, structureboundingbox);
        this.a(world, structureboundingbox, 0, 1, 0, 0, 3, 0, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        this.a(world, structureboundingbox, 3, 1, 0, 3, 3, 0, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 1, 4, 0, 3, 4, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        this.a(world, structureboundingbox, 3, 1, 4, 3, 3, 4, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 3, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 3, 1, 1, 3, 3, 3, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 1, 0, 2, 3, 0, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 1, 4, 2, 3, 4, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 0, 2, 2, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 3, 2, 2, structureboundingbox);
        if (this.b > 0) {
            this.a(world, Blocks.FENCE.getBlockData(), this.b, 1, 3, structureboundingbox);
            this.a(world, Blocks.WOODEN_PRESSURE_PLATE.getBlockData(), this.b, 2, 3, structureboundingbox);
        }

        this.a(world, Blocks.AIR.getBlockData(), 1, 1, 0, structureboundingbox);
        this.a(world, Blocks.AIR.getBlockData(), 1, 2, 0, structureboundingbox);
        this.a(world, structureboundingbox, random, 1, 1, 0, EnumDirection.fromType2(this.a(Blocks.WOODEN_DOOR, 1)));
        if (this.a(world, 1, 0, -1, structureboundingbox).getBlock().getMaterial() == Material.AIR && this.a(world, 1, -1, -1, structureboundingbox).getBlock().getMaterial() != Material.AIR) {
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(this.a(Blocks.STONE_STAIRS, 3)), 1, 0, -1, structureboundingbox);
        }

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.b(world, j, 6, i, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE.getBlockData(), j, -1, i, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 1, 1, 2, 1);
        return true;
    }
}
