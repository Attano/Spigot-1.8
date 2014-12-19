package net.minecraft.server;

import java.util.List;
import java.util.Random;

abstract class WorldGenStrongholdPiece extends StructurePiece {

    protected WorldGenStrongholdDoorType d;

    public WorldGenStrongholdPiece() {
        this.d = WorldGenStrongholdDoorType.OPENING;
    }

    protected WorldGenStrongholdPiece(int i) {
        super(i);
        this.d = WorldGenStrongholdDoorType.OPENING;
    }

    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.setString("EntryDoor", this.d.name());
    }

    protected void b(NBTTagCompound nbttagcompound) {
        this.d = WorldGenStrongholdDoorType.valueOf(nbttagcompound.getString("EntryDoor"));
    }

    protected void a(World world, Random random, StructureBoundingBox structureboundingbox, WorldGenStrongholdDoorType worldgenstrongholddoortype, int i, int j, int k) {
        switch (WorldGenStrongholdPieceWeight3.a[worldgenstrongholddoortype.ordinal()]) {
        case 1:
        default:
            this.a(world, structureboundingbox, i, j, k, i + 3 - 1, j + 3 - 1, k, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            break;

        case 2:
            this.a(world, Blocks.STONEBRICK.getBlockData(), i, j, k, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), i, j + 1, k, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), i, j + 2, k, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), i + 1, j + 2, k, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), i + 2, j + 2, k, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), i + 2, j + 1, k, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), i + 2, j, k, structureboundingbox);
            this.a(world, Blocks.WOODEN_DOOR.getBlockData(), i + 1, j, k, structureboundingbox);
            this.a(world, Blocks.WOODEN_DOOR.fromLegacyData(8), i + 1, j + 1, k, structureboundingbox);
            break;

        case 3:
            this.a(world, Blocks.AIR.getBlockData(), i + 1, j, k, structureboundingbox);
            this.a(world, Blocks.AIR.getBlockData(), i + 1, j + 1, k, structureboundingbox);
            this.a(world, Blocks.IRON_BARS.getBlockData(), i, j, k, structureboundingbox);
            this.a(world, Blocks.IRON_BARS.getBlockData(), i, j + 1, k, structureboundingbox);
            this.a(world, Blocks.IRON_BARS.getBlockData(), i, j + 2, k, structureboundingbox);
            this.a(world, Blocks.IRON_BARS.getBlockData(), i + 1, j + 2, k, structureboundingbox);
            this.a(world, Blocks.IRON_BARS.getBlockData(), i + 2, j + 2, k, structureboundingbox);
            this.a(world, Blocks.IRON_BARS.getBlockData(), i + 2, j + 1, k, structureboundingbox);
            this.a(world, Blocks.IRON_BARS.getBlockData(), i + 2, j, k, structureboundingbox);
            break;

        case 4:
            this.a(world, Blocks.STONEBRICK.getBlockData(), i, j, k, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), i, j + 1, k, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), i, j + 2, k, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), i + 1, j + 2, k, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), i + 2, j + 2, k, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), i + 2, j + 1, k, structureboundingbox);
            this.a(world, Blocks.STONEBRICK.getBlockData(), i + 2, j, k, structureboundingbox);
            this.a(world, Blocks.IRON_DOOR.getBlockData(), i + 1, j, k, structureboundingbox);
            this.a(world, Blocks.IRON_DOOR.fromLegacyData(8), i + 1, j + 1, k, structureboundingbox);
            this.a(world, Blocks.STONE_BUTTON.fromLegacyData(this.a(Blocks.STONE_BUTTON, 4)), i + 2, j + 1, k + 1, structureboundingbox);
            this.a(world, Blocks.STONE_BUTTON.fromLegacyData(this.a(Blocks.STONE_BUTTON, 3)), i + 2, j + 1, k - 1, structureboundingbox);
        }

    }

    protected WorldGenStrongholdDoorType a(Random random) {
        int i = random.nextInt(5);

        switch (i) {
        case 0:
        case 1:
        default:
            return WorldGenStrongholdDoorType.OPENING;

        case 2:
            return WorldGenStrongholdDoorType.WOOD_DOOR;

        case 3:
            return WorldGenStrongholdDoorType.GRATES;

        case 4:
            return WorldGenStrongholdDoorType.IRON_DOOR;
        }
    }

    protected StructurePiece a(WorldGenStrongholdStart worldgenstrongholdstart, List list, Random random, int i, int j) {
        if (this.m != null) {
            switch (WorldGenStrongholdPieceWeight3.b[this.m.ordinal()]) {
            case 1:
                return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.l.a + i, this.l.b + j, this.l.c - 1, this.m, this.d());

            case 2:
                return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.l.a + i, this.l.b + j, this.l.f + 1, this.m, this.d());

            case 3:
                return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.l.a - 1, this.l.b + j, this.l.c + i, this.m, this.d());

            case 4:
                return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.l.d + 1, this.l.b + j, this.l.c + i, this.m, this.d());
            }
        }

        return null;
    }

    protected StructurePiece b(WorldGenStrongholdStart worldgenstrongholdstart, List list, Random random, int i, int j) {
        if (this.m != null) {
            switch (WorldGenStrongholdPieceWeight3.b[this.m.ordinal()]) {
            case 1:
                return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.l.a - 1, this.l.b + i, this.l.c + j, EnumDirection.WEST, this.d());

            case 2:
                return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.l.a - 1, this.l.b + i, this.l.c + j, EnumDirection.WEST, this.d());

            case 3:
                return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.l.a + j, this.l.b + i, this.l.c - 1, EnumDirection.NORTH, this.d());

            case 4:
                return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.l.a + j, this.l.b + i, this.l.c - 1, EnumDirection.NORTH, this.d());
            }
        }

        return null;
    }

    protected StructurePiece c(WorldGenStrongholdStart worldgenstrongholdstart, List list, Random random, int i, int j) {
        if (this.m != null) {
            switch (WorldGenStrongholdPieceWeight3.b[this.m.ordinal()]) {
            case 1:
                return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.l.d + 1, this.l.b + i, this.l.c + j, EnumDirection.EAST, this.d());

            case 2:
                return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.l.d + 1, this.l.b + i, this.l.c + j, EnumDirection.EAST, this.d());

            case 3:
                return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.l.a + j, this.l.b + i, this.l.f + 1, EnumDirection.SOUTH, this.d());

            case 4:
                return WorldGenStrongholdPieces.a(worldgenstrongholdstart, list, random, this.l.a + j, this.l.b + i, this.l.f + 1, EnumDirection.SOUTH, this.d());
            }
        }

        return null;
    }

    protected static boolean a(StructureBoundingBox structureboundingbox) {
        return structureboundingbox != null && structureboundingbox.b > 10;
    }
}
