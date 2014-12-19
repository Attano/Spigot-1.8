package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class WorldGenJungleTemple extends WorldGenScatteredPiece {

    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private static final List i = Lists.newArrayList(new StructurePieceTreasure[] { new StructurePieceTreasure(Items.DIAMOND, 0, 1, 3, 3), new StructurePieceTreasure(Items.IRON_INGOT, 0, 1, 5, 10), new StructurePieceTreasure(Items.GOLD_INGOT, 0, 2, 7, 15), new StructurePieceTreasure(Items.EMERALD, 0, 1, 3, 2), new StructurePieceTreasure(Items.BONE, 0, 4, 6, 20), new StructurePieceTreasure(Items.ROTTEN_FLESH, 0, 3, 7, 16), new StructurePieceTreasure(Items.SADDLE, 0, 1, 1, 3), new StructurePieceTreasure(Items.IRON_HORSE_ARMOR, 0, 1, 1, 1), new StructurePieceTreasure(Items.GOLDEN_HORSE_ARMOR, 0, 1, 1, 1), new StructurePieceTreasure(Items.DIAMOND_HORSE_ARMOR, 0, 1, 1, 1)});
    private static final List j = Lists.newArrayList(new StructurePieceTreasure[] { new StructurePieceTreasure(Items.ARROW, 0, 2, 7, 30)});
    private static WorldGenJungleTemplePiece k = new WorldGenJungleTemplePiece((WorldGenJungleTempleUnknown) null);

    public WorldGenJungleTemple() {}

    public WorldGenJungleTemple(Random random, int i, int j) {
        super(random, i, 64, j, 12, 10, 15);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("placedMainChest", this.e);
        nbttagcompound.setBoolean("placedHiddenChest", this.f);
        nbttagcompound.setBoolean("placedTrap1", this.g);
        nbttagcompound.setBoolean("placedTrap2", this.h);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.e = nbttagcompound.getBoolean("placedMainChest");
        this.f = nbttagcompound.getBoolean("placedHiddenChest");
        this.g = nbttagcompound.getBoolean("placedTrap1");
        this.h = nbttagcompound.getBoolean("placedTrap2");
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (!this.a(world, structureboundingbox, 0)) {
            return false;
        } else {
            int i = this.a(Blocks.STONE_STAIRS, 3);
            int j = this.a(Blocks.STONE_STAIRS, 2);
            int k = this.a(Blocks.STONE_STAIRS, 0);
            int l = this.a(Blocks.STONE_STAIRS, 1);

            this.a(world, structureboundingbox, 0, -4, 0, this.a - 1, 0, this.c - 1, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 2, 1, 2, 9, 2, 2, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 2, 1, 12, 9, 2, 12, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 2, 1, 3, 2, 2, 11, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 9, 1, 3, 9, 2, 11, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 1, 3, 1, 10, 6, 1, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 1, 3, 13, 10, 6, 13, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 1, 3, 2, 1, 6, 12, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 10, 3, 2, 10, 6, 12, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 2, 3, 2, 9, 3, 12, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 2, 6, 2, 9, 6, 12, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 3, 7, 3, 8, 7, 11, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 4, 8, 4, 7, 8, 10, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 3, 1, 3, 8, 2, 11);
            this.a(world, structureboundingbox, 4, 3, 6, 7, 3, 9);
            this.a(world, structureboundingbox, 2, 4, 2, 9, 5, 12);
            this.a(world, structureboundingbox, 4, 6, 5, 7, 6, 9);
            this.a(world, structureboundingbox, 5, 7, 6, 6, 7, 8);
            this.a(world, structureboundingbox, 5, 1, 2, 6, 2, 2);
            this.a(world, structureboundingbox, 5, 2, 12, 6, 2, 12);
            this.a(world, structureboundingbox, 5, 5, 1, 6, 5, 1);
            this.a(world, structureboundingbox, 5, 5, 13, 6, 5, 13);
            this.a(world, Blocks.AIR.getBlockData(), 1, 5, 5, structureboundingbox);
            this.a(world, Blocks.AIR.getBlockData(), 10, 5, 5, structureboundingbox);
            this.a(world, Blocks.AIR.getBlockData(), 1, 5, 9, structureboundingbox);
            this.a(world, Blocks.AIR.getBlockData(), 10, 5, 9, structureboundingbox);

            int i1;

            for (i1 = 0; i1 <= 14; i1 += 14) {
                this.a(world, structureboundingbox, 2, 4, i1, 2, 5, i1, false, random, WorldGenJungleTemple.k);
                this.a(world, structureboundingbox, 4, 4, i1, 4, 5, i1, false, random, WorldGenJungleTemple.k);
                this.a(world, structureboundingbox, 7, 4, i1, 7, 5, i1, false, random, WorldGenJungleTemple.k);
                this.a(world, structureboundingbox, 9, 4, i1, 9, 5, i1, false, random, WorldGenJungleTemple.k);
            }

            this.a(world, structureboundingbox, 5, 6, 0, 6, 6, 0, false, random, WorldGenJungleTemple.k);

            for (i1 = 0; i1 <= 11; i1 += 11) {
                for (int j1 = 2; j1 <= 12; j1 += 2) {
                    this.a(world, structureboundingbox, i1, 4, j1, i1, 5, j1, false, random, WorldGenJungleTemple.k);
                }

                this.a(world, structureboundingbox, i1, 6, 5, i1, 6, 5, false, random, WorldGenJungleTemple.k);
                this.a(world, structureboundingbox, i1, 6, 9, i1, 6, 9, false, random, WorldGenJungleTemple.k);
            }

            this.a(world, structureboundingbox, 2, 7, 2, 2, 9, 2, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 9, 7, 2, 9, 9, 2, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 2, 7, 12, 2, 9, 12, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 9, 7, 12, 9, 9, 12, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 4, 9, 4, 4, 9, 4, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 7, 9, 4, 7, 9, 4, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 4, 9, 10, 4, 9, 10, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 7, 9, 10, 7, 9, 10, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 5, 9, 7, 6, 9, 7, false, random, WorldGenJungleTemple.k);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(i), 5, 9, 6, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(i), 6, 9, 6, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(j), 5, 9, 8, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(j), 6, 9, 8, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(i), 4, 0, 0, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(i), 5, 0, 0, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(i), 6, 0, 0, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(i), 7, 0, 0, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(i), 4, 1, 8, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(i), 4, 2, 9, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(i), 4, 3, 10, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(i), 7, 1, 8, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(i), 7, 2, 9, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(i), 7, 3, 10, structureboundingbox);
            this.a(world, structureboundingbox, 4, 1, 9, 4, 1, 9, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 7, 1, 9, 7, 1, 9, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 4, 1, 10, 7, 2, 10, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 5, 4, 5, 6, 4, 5, false, random, WorldGenJungleTemple.k);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(k), 4, 4, 5, structureboundingbox);
            this.a(world, Blocks.STONE_STAIRS.fromLegacyData(l), 7, 4, 5, structureboundingbox);

            for (i1 = 0; i1 < 4; ++i1) {
                this.a(world, Blocks.STONE_STAIRS.fromLegacyData(j), 5, 0 - i1, 6 + i1, structureboundingbox);
                this.a(world, Blocks.STONE_STAIRS.fromLegacyData(j), 6, 0 - i1, 6 + i1, structureboundingbox);
                this.a(world, structureboundingbox, 5, 0 - i1, 7 + i1, 6, 0 - i1, 9 + i1);
            }

            this.a(world, structureboundingbox, 1, -3, 12, 10, -1, 13);
            this.a(world, structureboundingbox, 1, -3, 1, 3, -1, 13);
            this.a(world, structureboundingbox, 1, -3, 1, 9, -1, 5);

            for (i1 = 1; i1 <= 13; i1 += 2) {
                this.a(world, structureboundingbox, 1, -3, i1, 1, -2, i1, false, random, WorldGenJungleTemple.k);
            }

            for (i1 = 2; i1 <= 12; i1 += 2) {
                this.a(world, structureboundingbox, 1, -1, i1, 3, -1, i1, false, random, WorldGenJungleTemple.k);
            }

            this.a(world, structureboundingbox, 2, -2, 1, 5, -2, 1, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 7, -2, 1, 9, -2, 1, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 6, -3, 1, 6, -3, 1, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 6, -1, 1, 6, -1, 1, false, random, WorldGenJungleTemple.k);
            this.a(world, Blocks.TRIPWIRE_HOOK.fromLegacyData(this.a(Blocks.TRIPWIRE_HOOK, EnumDirection.EAST.b())).set(BlockTripwireHook.ATTACHED, Boolean.valueOf(true)), 1, -3, 8, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE_HOOK.fromLegacyData(this.a(Blocks.TRIPWIRE_HOOK, EnumDirection.WEST.b())).set(BlockTripwireHook.ATTACHED, Boolean.valueOf(true)), 4, -3, 8, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.ATTACHED, Boolean.valueOf(true)), 2, -3, 8, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.ATTACHED, Boolean.valueOf(true)), 3, -3, 8, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 5, -3, 7, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 5, -3, 6, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 5, -3, 5, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 5, -3, 4, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 5, -3, 3, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 5, -3, 2, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 5, -3, 1, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 4, -3, 1, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE.getBlockData(), 3, -3, 1, structureboundingbox);
            if (!this.g) {
                this.g = this.a(world, structureboundingbox, random, 3, -2, 1, EnumDirection.NORTH.a(), WorldGenJungleTemple.j, 2);
            }

            this.a(world, Blocks.VINE.fromLegacyData(15), 3, -2, 2, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE_HOOK.fromLegacyData(this.a(Blocks.TRIPWIRE_HOOK, EnumDirection.NORTH.b())).set(BlockTripwireHook.ATTACHED, Boolean.valueOf(true)), 7, -3, 1, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE_HOOK.fromLegacyData(this.a(Blocks.TRIPWIRE_HOOK, EnumDirection.SOUTH.b())).set(BlockTripwireHook.ATTACHED, Boolean.valueOf(true)), 7, -3, 5, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.ATTACHED, Boolean.valueOf(true)), 7, -3, 2, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.ATTACHED, Boolean.valueOf(true)), 7, -3, 3, structureboundingbox);
            this.a(world, Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.ATTACHED, Boolean.valueOf(true)), 7, -3, 4, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 8, -3, 6, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 9, -3, 6, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 9, -3, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE.getBlockData(), 9, -3, 4, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 9, -2, 4, structureboundingbox);
            if (!this.h) {
                this.h = this.a(world, structureboundingbox, random, 9, -2, 3, EnumDirection.WEST.a(), WorldGenJungleTemple.j, 2);
            }

            this.a(world, Blocks.VINE.fromLegacyData(15), 8, -1, 3, structureboundingbox);
            this.a(world, Blocks.VINE.fromLegacyData(15), 8, -2, 3, structureboundingbox);
            if (!this.e) {
                this.e = this.a(world, structureboundingbox, random, 8, -3, 3, StructurePieceTreasure.a(WorldGenJungleTemple.i, new StructurePieceTreasure[] { Items.ENCHANTED_BOOK.b(random)}), 2 + random.nextInt(5));
            }

            this.a(world, Blocks.MOSSY_COBBLESTONE.getBlockData(), 9, -3, 2, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE.getBlockData(), 8, -3, 1, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE.getBlockData(), 4, -3, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE.getBlockData(), 5, -2, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE.getBlockData(), 5, -1, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE.getBlockData(), 6, -3, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE.getBlockData(), 7, -2, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE.getBlockData(), 7, -1, 5, structureboundingbox);
            this.a(world, Blocks.MOSSY_COBBLESTONE.getBlockData(), 8, -3, 5, structureboundingbox);
            this.a(world, structureboundingbox, 9, -1, 1, 9, -1, 5, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 8, -3, 8, 10, -1, 10);
            this.a(world, Blocks.STONEBRICK.fromLegacyData(BlockSmoothBrick.O), 8, -2, 11, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.fromLegacyData(BlockSmoothBrick.O), 9, -2, 11, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.fromLegacyData(BlockSmoothBrick.O), 10, -2, 11, structureboundingbox);
            this.a(world, Blocks.LEVER.fromLegacyData(BlockLever.a(EnumDirection.fromType1(this.a(Blocks.LEVER, EnumDirection.NORTH.a())))), 8, -2, 12, structureboundingbox);
            this.a(world, Blocks.LEVER.fromLegacyData(BlockLever.a(EnumDirection.fromType1(this.a(Blocks.LEVER, EnumDirection.NORTH.a())))), 9, -2, 12, structureboundingbox);
            this.a(world, Blocks.LEVER.fromLegacyData(BlockLever.a(EnumDirection.fromType1(this.a(Blocks.LEVER, EnumDirection.NORTH.a())))), 10, -2, 12, structureboundingbox);
            this.a(world, structureboundingbox, 8, -3, 8, 8, -3, 10, false, random, WorldGenJungleTemple.k);
            this.a(world, structureboundingbox, 10, -3, 8, 10, -3, 10, false, random, WorldGenJungleTemple.k);
            this.a(world, Blocks.MOSSY_COBBLESTONE.getBlockData(), 10, -2, 9, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 8, -2, 9, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 8, -2, 10, structureboundingbox);
            this.a(world, Blocks.REDSTONE_WIRE.getBlockData(), 10, -1, 9, structureboundingbox);
            this.a(world, Blocks.STICKY_PISTON.fromLegacyData(EnumDirection.UP.a()), 9, -2, 8, structureboundingbox);
            this.a(world, Blocks.STICKY_PISTON.fromLegacyData(this.a(Blocks.STICKY_PISTON, EnumDirection.WEST.a())), 10, -2, 8, structureboundingbox);
            this.a(world, Blocks.STICKY_PISTON.fromLegacyData(this.a(Blocks.STICKY_PISTON, EnumDirection.WEST.a())), 10, -1, 8, structureboundingbox);
            this.a(world, Blocks.UNPOWERED_REPEATER.fromLegacyData(this.a(Blocks.UNPOWERED_REPEATER, EnumDirection.NORTH.b())), 10, -2, 10, structureboundingbox);
            if (!this.f) {
                this.f = this.a(world, structureboundingbox, random, 9, -3, 10, StructurePieceTreasure.a(WorldGenJungleTemple.i, new StructurePieceTreasure[] { Items.ENCHANTED_BOOK.b(random)}), 2 + random.nextInt(5));
            }

            return true;
        }
    }
}
