package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class WorldGenVillageBlacksmith extends WorldGenVillagePiece {

    private static final List a = Lists.newArrayList(new StructurePieceTreasure[] { new StructurePieceTreasure(Items.DIAMOND, 0, 1, 3, 3), new StructurePieceTreasure(Items.IRON_INGOT, 0, 1, 5, 10), new StructurePieceTreasure(Items.GOLD_INGOT, 0, 1, 3, 5), new StructurePieceTreasure(Items.BREAD, 0, 1, 3, 15), new StructurePieceTreasure(Items.APPLE, 0, 1, 3, 15), new StructurePieceTreasure(Items.IRON_PICKAXE, 0, 1, 1, 5), new StructurePieceTreasure(Items.IRON_SWORD, 0, 1, 1, 5), new StructurePieceTreasure(Items.IRON_CHESTPLATE, 0, 1, 1, 5), new StructurePieceTreasure(Items.IRON_HELMET, 0, 1, 1, 5), new StructurePieceTreasure(Items.IRON_LEGGINGS, 0, 1, 1, 5), new StructurePieceTreasure(Items.IRON_BOOTS, 0, 1, 1, 5), new StructurePieceTreasure(Item.getItemOf(Blocks.OBSIDIAN), 0, 3, 7, 5), new StructurePieceTreasure(Item.getItemOf(Blocks.SAPLING), 0, 3, 7, 5), new StructurePieceTreasure(Items.SADDLE, 0, 1, 1, 3), new StructurePieceTreasure(Items.IRON_HORSE_ARMOR, 0, 1, 1, 1), new StructurePieceTreasure(Items.GOLDEN_HORSE_ARMOR, 0, 1, 1, 1), new StructurePieceTreasure(Items.DIAMOND_HORSE_ARMOR, 0, 1, 1, 1)});
    private boolean b;

    public WorldGenVillageBlacksmith() {}

    public WorldGenVillageBlacksmith(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(worldgenvillagestartpiece, i);
        this.m = enumdirection;
        this.l = structureboundingbox;
    }

    public static WorldGenVillageBlacksmith a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 10, 6, 7, enumdirection);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenVillageBlacksmith(worldgenvillagestartpiece, l, random, structureboundingbox, enumdirection) : null;
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("Chest", this.b);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.b = nbttagcompound.getBoolean("Chest");
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.h < 0) {
            this.h = this.b(world, structureboundingbox);
            if (this.h < 0) {
                return true;
            }

            this.l.a(0, this.h - this.l.e + 6 - 1, 0);
        }

        this.a(world, structureboundingbox, 0, 1, 0, 9, 4, 6, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 0, 0, 9, 0, 6, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 4, 0, 9, 4, 6, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 5, 0, 9, 5, 6, Blocks.STONE_SLAB.getBlockData(), Blocks.STONE_SLAB.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 5, 1, 8, 5, 5, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 1, 0, 2, 3, 0, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 1, 0, 0, 4, 0, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        this.a(world, structureboundingbox, 3, 1, 0, 3, 4, 0, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 1, 6, 0, 4, 6, Blocks.LOG.getBlockData(), Blocks.LOG.getBlockData(), false);
        this.a(world, Blocks.PLANKS.getBlockData(), 3, 3, 1, structureboundingbox);
        this.a(world, structureboundingbox, 3, 1, 2, 3, 3, 2, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 4, 1, 3, 5, 3, 3, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 5, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 1, 1, 6, 5, 3, 6, Blocks.PLANKS.getBlockData(), Blocks.PLANKS.getBlockData(), false);
        this.a(world, structureboundingbox, 5, 1, 0, 5, 3, 0, Blocks.FENCE.getBlockData(), Blocks.FENCE.getBlockData(), false);
        this.a(world, structureboundingbox, 9, 1, 0, 9, 3, 0, Blocks.FENCE.getBlockData(), Blocks.FENCE.getBlockData(), false);
        this.a(world, structureboundingbox, 6, 1, 4, 9, 4, 6, Blocks.COBBLESTONE.getBlockData(), Blocks.COBBLESTONE.getBlockData(), false);
        this.a(world, Blocks.FLOWING_LAVA.getBlockData(), 7, 1, 5, structureboundingbox);
        this.a(world, Blocks.FLOWING_LAVA.getBlockData(), 8, 1, 5, structureboundingbox);
        this.a(world, Blocks.IRON_BARS.getBlockData(), 9, 2, 5, structureboundingbox);
        this.a(world, Blocks.IRON_BARS.getBlockData(), 9, 2, 4, structureboundingbox);
        this.a(world, structureboundingbox, 7, 2, 4, 8, 2, 5, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
        this.a(world, Blocks.COBBLESTONE.getBlockData(), 6, 1, 3, structureboundingbox);
        this.a(world, Blocks.FURNACE.getBlockData(), 6, 2, 3, structureboundingbox);
        this.a(world, Blocks.FURNACE.getBlockData(), 6, 3, 3, structureboundingbox);
        this.a(world, Blocks.DOUBLE_STONE_SLAB.getBlockData(), 8, 1, 1, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 0, 2, 2, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 0, 2, 4, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 2, 2, 6, structureboundingbox);
        this.a(world, Blocks.GLASS_PANE.getBlockData(), 4, 2, 6, structureboundingbox);
        this.a(world, Blocks.FENCE.getBlockData(), 2, 1, 4, structureboundingbox);
        this.a(world, Blocks.WOODEN_PRESSURE_PLATE.getBlockData(), 2, 2, 4, structureboundingbox);
        this.a(world, Blocks.PLANKS.getBlockData(), 1, 1, 5, structureboundingbox);
        this.a(world, Blocks.OAK_STAIRS.fromLegacyData(this.a(Blocks.OAK_STAIRS, 3)), 2, 1, 5, structureboundingbox);
        this.a(world, Blocks.OAK_STAIRS.fromLegacyData(this.a(Blocks.OAK_STAIRS, 1)), 1, 1, 4, structureboundingbox);
        if (!this.b && structureboundingbox.b((BaseBlockPosition) (new BlockPosition(this.a(5, 5), this.d(1), this.b(5, 5))))) {
            this.b = true;
            this.a(world, structureboundingbox, random, 5, 1, 5, WorldGenVillageBlacksmith.a, 3 + random.nextInt(6));
        }

        int i;

        for (i = 6; i <= 8; ++i) {
            if (this.a(world, i, 0, -1, structureboundingbox).getBlock().getMaterial() == Material.AIR && this.a(world, i, -1, -1, structureboundingbox).getBlock().getMaterial() != Material.AIR) {
                this.a(world, Blocks.STONE_STAIRS.fromLegacyData(this.a(Blocks.STONE_STAIRS, 3)), i, 0, -1, structureboundingbox);
            }
        }

        for (i = 0; i < 7; ++i) {
            for (int j = 0; j < 10; ++j) {
                this.b(world, j, 6, i, structureboundingbox);
                this.b(world, Blocks.COBBLESTONE.getBlockData(), j, -1, i, structureboundingbox);
            }
        }

        this.a(world, structureboundingbox, 7, 1, 1, 1);
        return true;
    }

    protected int c(int i, int j) {
        return 3;
    }
}
