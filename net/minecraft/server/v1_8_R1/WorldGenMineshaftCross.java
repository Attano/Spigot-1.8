package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenMineshaftCross extends StructurePiece {

    private EnumDirection a;
    private boolean b;

    public WorldGenMineshaftCross() {}

    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.setBoolean("tf", this.b);
        nbttagcompound.setInt("D", this.a.b());
    }

    protected void b(NBTTagCompound nbttagcompound) {
        this.b = nbttagcompound.getBoolean("tf");
        this.a = EnumDirection.fromType2(nbttagcompound.getInt("D"));
    }

    public WorldGenMineshaftCross(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(i);
        this.a = enumdirection;
        this.l = structureboundingbox;
        this.b = structureboundingbox.d() > 3;
    }

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, EnumDirection enumdirection) {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(i, j, k, i, j + 2, k);

        if (random.nextInt(4) == 0) {
            structureboundingbox.e += 4;
        }

        switch (SwitchHelperDirection7.a[enumdirection.ordinal()]) {
        case 1:
            structureboundingbox.a = i - 1;
            structureboundingbox.d = i + 3;
            structureboundingbox.c = k - 4;
            break;

        case 2:
            structureboundingbox.a = i - 1;
            structureboundingbox.d = i + 3;
            structureboundingbox.f = k + 4;
            break;

        case 3:
            structureboundingbox.a = i - 4;
            structureboundingbox.c = k - 1;
            structureboundingbox.f = k + 3;
            break;

        case 4:
            structureboundingbox.d = i + 4;
            structureboundingbox.c = k - 1;
            structureboundingbox.f = k + 3;
        }

        return StructurePiece.a(list, structureboundingbox) != null ? null : structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        int i = this.d();

        switch (SwitchHelperDirection7.a[this.a.ordinal()]) {
        case 1:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a + 1, this.l.b, this.l.c - 1, EnumDirection.NORTH, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a - 1, this.l.b, this.l.c + 1, EnumDirection.WEST, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.d + 1, this.l.b, this.l.c + 1, EnumDirection.EAST, i);
            break;

        case 2:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a + 1, this.l.b, this.l.f + 1, EnumDirection.SOUTH, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a - 1, this.l.b, this.l.c + 1, EnumDirection.WEST, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.d + 1, this.l.b, this.l.c + 1, EnumDirection.EAST, i);
            break;

        case 3:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a + 1, this.l.b, this.l.c - 1, EnumDirection.NORTH, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a + 1, this.l.b, this.l.f + 1, EnumDirection.SOUTH, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a - 1, this.l.b, this.l.c + 1, EnumDirection.WEST, i);
            break;

        case 4:
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a + 1, this.l.b, this.l.c - 1, EnumDirection.NORTH, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a + 1, this.l.b, this.l.f + 1, EnumDirection.SOUTH, i);
            WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.d + 1, this.l.b, this.l.c + 1, EnumDirection.EAST, i);
        }

        if (this.b) {
            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a + 1, this.l.b + 3 + 1, this.l.c - 1, EnumDirection.NORTH, i);
            }

            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a - 1, this.l.b + 3 + 1, this.l.c + 1, EnumDirection.WEST, i);
            }

            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.d + 1, this.l.b + 3 + 1, this.l.c + 1, EnumDirection.EAST, i);
            }

            if (random.nextBoolean()) {
                WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a + 1, this.l.b + 3 + 1, this.l.f + 1, EnumDirection.SOUTH, i);
            }
        }

    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            if (this.b) {
                this.a(world, structureboundingbox, this.l.a + 1, this.l.b, this.l.c, this.l.d - 1, this.l.b + 3 - 1, this.l.f, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
                this.a(world, structureboundingbox, this.l.a, this.l.b, this.l.c + 1, this.l.d, this.l.b + 3 - 1, this.l.f - 1, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
                this.a(world, structureboundingbox, this.l.a + 1, this.l.e - 2, this.l.c, this.l.d - 1, this.l.e, this.l.f, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
                this.a(world, structureboundingbox, this.l.a, this.l.e - 2, this.l.c + 1, this.l.d, this.l.e, this.l.f - 1, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
                this.a(world, structureboundingbox, this.l.a + 1, this.l.b + 3, this.l.c + 1, this.l.d - 1, this.l.b + 3, this.l.f - 1, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            } else {
                this.a(world, structureboundingbox, this.l.a + 1, this.l.b, this.l.c, this.l.d - 1, this.l.e, this.l.f, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
                this.a(world, structureboundingbox, this.l.a, this.l.b, this.l.c + 1, this.l.d, this.l.e, this.l.f - 1, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            }

            this.a(world, structureboundingbox, this.l.a + 1, this.l.b, this.l.c + 1, this.l.a + 1, this.l.e, this.l.c + 1, Blocks.PLANKS.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, this.l.a + 1, this.l.b, this.l.f - 1, this.l.a + 1, this.l.e, this.l.f - 1, Blocks.PLANKS.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, this.l.d - 1, this.l.b, this.l.c + 1, this.l.d - 1, this.l.e, this.l.c + 1, Blocks.PLANKS.getBlockData(), Blocks.AIR.getBlockData(), false);
            this.a(world, structureboundingbox, this.l.d - 1, this.l.b, this.l.f - 1, this.l.d - 1, this.l.e, this.l.f - 1, Blocks.PLANKS.getBlockData(), Blocks.AIR.getBlockData(), false);

            for (int i = this.l.a; i <= this.l.d; ++i) {
                for (int j = this.l.c; j <= this.l.f; ++j) {
                    if (this.a(world, i, this.l.b - 1, j, structureboundingbox).getBlock().getMaterial() == Material.AIR) {
                        this.a(world, Blocks.PLANKS.getBlockData(), i, this.l.b - 1, j, structureboundingbox);
                    }
                }
            }

            return true;
        }
    }
}
