package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenVillageRoad extends WorldGenVillageRoadPiece {

    private int a;

    public WorldGenVillageRoad() {}

    public WorldGenVillageRoad(WorldGenVillageStartPiece worldgenvillagestartpiece, int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(worldgenvillagestartpiece, i);
        this.m = enumdirection;
        this.l = structureboundingbox;
        this.a = Math.max(structureboundingbox.c(), structureboundingbox.e());
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setInt("Length", this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.getInt("Length");
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        boolean flag = false;

        int i;
        StructurePiece structurepiece1;

        for (i = random.nextInt(5); i < this.a - 8; i += 2 + random.nextInt(5)) {
            structurepiece1 = this.a((WorldGenVillageStartPiece) structurepiece, list, random, 0, i);
            if (structurepiece1 != null) {
                i += Math.max(structurepiece1.l.c(), structurepiece1.l.e());
                flag = true;
            }
        }

        for (i = random.nextInt(5); i < this.a - 8; i += 2 + random.nextInt(5)) {
            structurepiece1 = this.b((WorldGenVillageStartPiece) structurepiece, list, random, 0, i);
            if (structurepiece1 != null) {
                i += Math.max(structurepiece1.l.c(), structurepiece1.l.e());
                flag = true;
            }
        }

        if (flag && random.nextInt(3) > 0 && this.m != null) {
            switch (SwitchHelperDirection3.a[this.m.ordinal()]) {
            case 1:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.l.a - 1, this.l.b, this.l.c, EnumDirection.WEST, this.d());
                break;

            case 2:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.l.a - 1, this.l.b, this.l.f - 2, EnumDirection.WEST, this.d());
                break;

            case 3:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.l.a, this.l.b, this.l.c - 1, EnumDirection.NORTH, this.d());
                break;

            case 4:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.l.d - 2, this.l.b, this.l.c - 1, EnumDirection.NORTH, this.d());
            }
        }

        if (flag && random.nextInt(3) > 0 && this.m != null) {
            switch (SwitchHelperDirection3.a[this.m.ordinal()]) {
            case 1:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.l.d + 1, this.l.b, this.l.c, EnumDirection.EAST, this.d());
                break;

            case 2:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.l.d + 1, this.l.b, this.l.f - 2, EnumDirection.EAST, this.d());
                break;

            case 3:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.l.a, this.l.b, this.l.f + 1, EnumDirection.SOUTH, this.d());
                break;

            case 4:
                WorldGenVillagePieces.b((WorldGenVillageStartPiece) structurepiece, list, random, this.l.d - 2, this.l.b, this.l.f + 1, EnumDirection.SOUTH, this.d());
            }
        }

    }

    public static StructureBoundingBox a(WorldGenVillageStartPiece worldgenvillagestartpiece, List list, Random random, int i, int j, int k, EnumDirection enumdirection) {
        for (int l = 7 * MathHelper.nextInt(random, 3, 5); l >= 7; l -= 7) {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, 0, 0, 0, 3, 3, l, enumdirection);

            if (StructurePiece.a(list, structureboundingbox) == null) {
                return structureboundingbox;
            }
        }

        return null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        IBlockData iblockdata = this.a(Blocks.GRAVEL.getBlockData());
        IBlockData iblockdata1 = this.a(Blocks.COBBLESTONE.getBlockData());

        for (int i = this.l.a; i <= this.l.d; ++i) {
            for (int j = this.l.c; j <= this.l.f; ++j) {
                BlockPosition blockposition = new BlockPosition(i, 64, j);

                if (structureboundingbox.b((BaseBlockPosition) blockposition)) {
                    blockposition = world.r(blockposition).down();
                    world.setTypeAndData(blockposition, iblockdata, 2);
                    world.setTypeAndData(blockposition.down(), iblockdata1, 2);
                }
            }
        }

        return true;
    }
}
