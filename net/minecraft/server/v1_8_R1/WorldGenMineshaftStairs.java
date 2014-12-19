package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenMineshaftStairs extends StructurePiece {

    public WorldGenMineshaftStairs() {}

    public WorldGenMineshaftStairs(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(i);
        this.m = enumdirection;
        this.l = structureboundingbox;
    }

    protected void a(NBTTagCompound nbttagcompound) {}

    protected void b(NBTTagCompound nbttagcompound) {}

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, EnumDirection enumdirection) {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j - 5, k, i, j + 2, k);

        switch (SwitchHelperDirection7.a[enumdirection.ordinal()]) {
        case 1:
            structureboundingbox.d = i + 2;
            structureboundingbox.c = k - 8;
            break;

        case 2:
            structureboundingbox.d = i + 2;
            structureboundingbox.f = k + 8;
            break;

        case 3:
            structureboundingbox.a = i - 8;
            structureboundingbox.f = k + 2;
            break;

        case 4:
            structureboundingbox.d = i + 8;
            structureboundingbox.f = k + 2;
        }

        return StructurePiece.a(list, structureboundingbox) != null ? null : structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        int i = this.d();

        if (this.m != null) {
            switch (SwitchHelperDirection7.a[this.m.ordinal()]) {
            case 1:
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a, this.l.b, this.l.c - 1, EnumDirection.NORTH, i);
                break;

            case 2:
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a, this.l.b, this.l.f + 1, EnumDirection.SOUTH, i);
                break;

            case 3:
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a - 1, this.l.b, this.l.c, EnumDirection.WEST, i);
                break;

            case 4:
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.d + 1, this.l.b, this.l.c, EnumDirection.EAST, i);
            }
        }

    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 5, 0, 2, 7, 1, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, 0, 0, 7, 2, 2, 8, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);

            for (int i = 0; i < 5; ++i) {
                this.a(world, structureboundingbox, 0, 5 - i - (i < 4 ? 1 : 0), 2 + i, 2, 7 - i, 2 + i, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            }

            return true;
        }
    }
}
