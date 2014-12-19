package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdPortalRoom extends WorldGenStrongholdPiece {

    private boolean a;

    public WorldGenStrongholdPortalRoom() {}

    public WorldGenStrongholdPortalRoom(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(i);
        this.m = enumdirection;
        this.l = structureboundingbox;
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setBoolean("Mob", this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.getBoolean("Mob");
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        if (structurepiece != null) {
            ((WorldGenStrongholdStart) structurepiece).b = this;
        }

    }

    public static WorldGenStrongholdPortalRoom a(List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -4, -1, 0, 11, 8, 16, enumdirection);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdPortalRoom(l, random, structureboundingbox, enumdirection) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        this.a(world, structureboundingbox, 0, 0, 0, 10, 7, 15, false, random, WorldGenStrongholdPieces.c());
        this.a(world, random, structureboundingbox, WorldGenStrongholdDoorType.GRATES, 4, 1, 0);
        byte b0 = 6;

        this.a(world, structureboundingbox, 1, b0, 1, 1, b0, 14, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 9, b0, 1, 9, b0, 14, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 2, b0, 1, 8, b0, 2, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 2, b0, 14, 8, b0, 14, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 1, 1, 1, 2, 1, 4, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 8, 1, 1, 9, 1, 4, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 1, 1, 1, 1, 1, 3, Blocks.FLOWING_LAVA.getBlockData(), Blocks.FLOWING_LAVA.getBlockData(), false);
        this.a(world, structureboundingbox, 9, 1, 1, 9, 1, 3, Blocks.FLOWING_LAVA.getBlockData(), Blocks.FLOWING_LAVA.getBlockData(), false);
        this.a(world, structureboundingbox, 3, 1, 8, 7, 1, 12, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 4, 1, 9, 6, 1, 11, Blocks.FLOWING_LAVA.getBlockData(), Blocks.FLOWING_LAVA.getBlockData(), false);

        int i;

        for (i = 3; i < 14; i += 2) {
            this.a(world, structureboundingbox, 0, 3, i, 0, 4, i, Blocks.IRON_BARS.getBlockData(), Blocks.IRON_BARS.getBlockData(), false);
            this.a(world, structureboundingbox, 10, 3, i, 10, 4, i, Blocks.IRON_BARS.getBlockData(), Blocks.IRON_BARS.getBlockData(), false);
        }

        for (i = 2; i < 9; i += 2) {
            this.a(world, structureboundingbox, i, 3, 15, i, 4, 15, Blocks.IRON_BARS.getBlockData(), Blocks.IRON_BARS.getBlockData(), false);
        }

        i = this.a(Blocks.STONE_BRICK_STAIRS, 3);
        this.a(world, structureboundingbox, 4, 1, 5, 6, 1, 7, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 4, 2, 6, 6, 2, 7, false, random, WorldGenStrongholdPieces.c());
        this.a(world, structureboundingbox, 4, 3, 7, 6, 3, 7, false, random, WorldGenStrongholdPieces.c());

        int j;

        for (j = 4; j <= 6; ++j) {
            this.a(world, Blocks.STONE_BRICK_STAIRS.fromLegacyData(i), j, 1, 4, structureboundingbox);
            this.a(world, Blocks.STONE_BRICK_STAIRS.fromLegacyData(i), j, 2, 5, structureboundingbox);
            this.a(world, Blocks.STONE_BRICK_STAIRS.fromLegacyData(i), j, 3, 6, structureboundingbox);
        }

        j = EnumDirection.NORTH.b();
        int k = EnumDirection.SOUTH.b();
        int l = EnumDirection.EAST.b();
        int i1 = EnumDirection.WEST.b();

        if (this.m != null) {
            switch (WorldGenStrongholdPieceWeight3.b[this.m.ordinal()]) {
            case 2:
                j = EnumDirection.SOUTH.b();
                k = EnumDirection.NORTH.b();
                break;

            case 3:
                j = EnumDirection.WEST.b();
                k = EnumDirection.EAST.b();
                l = EnumDirection.SOUTH.b();
                i1 = EnumDirection.NORTH.b();
                break;

            case 4:
                j = EnumDirection.EAST.b();
                k = EnumDirection.WEST.b();
                l = EnumDirection.SOUTH.b();
                i1 = EnumDirection.NORTH.b();
            }
        }

        this.a(world, Blocks.END_PORTAL_FRAME.fromLegacyData(j).set(BlockEnderPortalFrame.EYE, Boolean.valueOf(random.nextFloat() > 0.9F)), 4, 3, 8, structureboundingbox);
        this.a(world, Blocks.END_PORTAL_FRAME.fromLegacyData(j).set(BlockEnderPortalFrame.EYE, Boolean.valueOf(random.nextFloat() > 0.9F)), 5, 3, 8, structureboundingbox);
        this.a(world, Blocks.END_PORTAL_FRAME.fromLegacyData(j).set(BlockEnderPortalFrame.EYE, Boolean.valueOf(random.nextFloat() > 0.9F)), 6, 3, 8, structureboundingbox);
        this.a(world, Blocks.END_PORTAL_FRAME.fromLegacyData(k).set(BlockEnderPortalFrame.EYE, Boolean.valueOf(random.nextFloat() > 0.9F)), 4, 3, 12, structureboundingbox);
        this.a(world, Blocks.END_PORTAL_FRAME.fromLegacyData(k).set(BlockEnderPortalFrame.EYE, Boolean.valueOf(random.nextFloat() > 0.9F)), 5, 3, 12, structureboundingbox);
        this.a(world, Blocks.END_PORTAL_FRAME.fromLegacyData(k).set(BlockEnderPortalFrame.EYE, Boolean.valueOf(random.nextFloat() > 0.9F)), 6, 3, 12, structureboundingbox);
        this.a(world, Blocks.END_PORTAL_FRAME.fromLegacyData(l).set(BlockEnderPortalFrame.EYE, Boolean.valueOf(random.nextFloat() > 0.9F)), 3, 3, 9, structureboundingbox);
        this.a(world, Blocks.END_PORTAL_FRAME.fromLegacyData(l).set(BlockEnderPortalFrame.EYE, Boolean.valueOf(random.nextFloat() > 0.9F)), 3, 3, 10, structureboundingbox);
        this.a(world, Blocks.END_PORTAL_FRAME.fromLegacyData(l).set(BlockEnderPortalFrame.EYE, Boolean.valueOf(random.nextFloat() > 0.9F)), 3, 3, 11, structureboundingbox);
        this.a(world, Blocks.END_PORTAL_FRAME.fromLegacyData(i1).set(BlockEnderPortalFrame.EYE, Boolean.valueOf(random.nextFloat() > 0.9F)), 7, 3, 9, structureboundingbox);
        this.a(world, Blocks.END_PORTAL_FRAME.fromLegacyData(i1).set(BlockEnderPortalFrame.EYE, Boolean.valueOf(random.nextFloat() > 0.9F)), 7, 3, 10, structureboundingbox);
        this.a(world, Blocks.END_PORTAL_FRAME.fromLegacyData(i1).set(BlockEnderPortalFrame.EYE, Boolean.valueOf(random.nextFloat() > 0.9F)), 7, 3, 11, structureboundingbox);
        if (!this.a) {
            int j1 = this.d(3);
            BlockPosition blockposition = new BlockPosition(this.a(5, 6), j1, this.b(5, 6));

            if (structureboundingbox.b((BaseBlockPosition) blockposition)) {
                this.a = true;
                world.setTypeAndData(blockposition, Blocks.MOB_SPAWNER.getBlockData(), 2);
                TileEntity tileentity = world.getTileEntity(blockposition);

                if (tileentity instanceof TileEntityMobSpawner) {
                    ((TileEntityMobSpawner) tileentity).getSpawner().setMobName("Silverfish");
                }
            }
        }

        return true;
    }
}
