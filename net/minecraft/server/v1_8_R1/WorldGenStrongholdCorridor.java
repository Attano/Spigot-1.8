package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdCorridor extends WorldGenStrongholdPiece {

    private int a;

    public WorldGenStrongholdCorridor() {}

    public WorldGenStrongholdCorridor(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(i);
        this.m = enumdirection;
        this.l = structureboundingbox;
        this.a = enumdirection != EnumDirection.NORTH && enumdirection != EnumDirection.SOUTH ? structureboundingbox.c() : structureboundingbox.e();
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        nbttagcompound.setInt("Steps", this.a);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        this.a = nbttagcompound.getInt("Steps");
    }

    public static StructureBoundingBox a(List list, Random random, int i, int j, int k, EnumDirection enumdirection) {
        boolean flag = true;
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 4, enumdirection);
        StructurePiece structurepiece = StructurePiece.a(list, structureboundingbox);

        if (structurepiece == null) {
            return null;
        } else {
            if (structurepiece.c().b == structureboundingbox.b) {
                for (int l = 3; l >= 1; --l) {
                    structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, l - 1, enumdirection);
                    if (!structurepiece.c().a(structureboundingbox)) {
                        return StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, l, enumdirection);
                    }
                }
            }

            return null;
        }
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            for (int i = 0; i < this.a; ++i) {
                this.a(world, Blocks.STONEBRICK.getBlockData(), 0, 0, i, structureboundingbox);
                this.a(world, Blocks.STONEBRICK.getBlockData(), 1, 0, i, structureboundingbox);
                this.a(world, Blocks.STONEBRICK.getBlockData(), 2, 0, i, structureboundingbox);
                this.a(world, Blocks.STONEBRICK.getBlockData(), 3, 0, i, structureboundingbox);
                this.a(world, Blocks.STONEBRICK.getBlockData(), 4, 0, i, structureboundingbox);

                for (int j = 1; j <= 3; ++j) {
                    this.a(world, Blocks.STONEBRICK.getBlockData(), 0, j, i, structureboundingbox);
                    this.a(world, Blocks.AIR.getBlockData(), 1, j, i, structureboundingbox);
                    this.a(world, Blocks.AIR.getBlockData(), 2, j, i, structureboundingbox);
                    this.a(world, Blocks.AIR.getBlockData(), 3, j, i, structureboundingbox);
                    this.a(world, Blocks.STONEBRICK.getBlockData(), 4, j, i, structureboundingbox);
                }

                this.a(world, Blocks.STONEBRICK.getBlockData(), 0, 4, i, structureboundingbox);
                this.a(world, Blocks.STONEBRICK.getBlockData(), 1, 4, i, structureboundingbox);
                this.a(world, Blocks.STONEBRICK.getBlockData(), 2, 4, i, structureboundingbox);
                this.a(world, Blocks.STONEBRICK.getBlockData(), 3, 4, i, structureboundingbox);
                this.a(world, Blocks.STONEBRICK.getBlockData(), 4, 4, i, structureboundingbox);
            }

            return true;
        }
    }
}
