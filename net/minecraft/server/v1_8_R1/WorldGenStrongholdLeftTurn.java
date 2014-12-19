package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class WorldGenStrongholdLeftTurn extends WorldGenStrongholdPiece {

    public WorldGenStrongholdLeftTurn() {}

    public WorldGenStrongholdLeftTurn(int i, Random random, StructureBoundingBox structureboundingbox, EnumDirection enumdirection) {
        super(i);
        this.m = enumdirection;
        this.d = this.a(random);
        this.l = structureboundingbox;
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        if (this.m != EnumDirection.NORTH && this.m != EnumDirection.EAST) {
            this.c((WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
        } else {
            this.b((WorldGenStrongholdStart) structurepiece, list, random, 1, 1);
        }

    }

    public static WorldGenStrongholdLeftTurn a(List list, Random random, int i, int j, int k, EnumDirection enumdirection, int l) {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.a(i, j, k, -1, -1, 0, 5, 5, 5, enumdirection);

        return a(structureboundingbox) && StructurePiece.a(list, structureboundingbox) == null ? new WorldGenStrongholdLeftTurn(l, random, structureboundingbox, enumdirection) : null;
    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, 0, 0, 0, 4, 4, 4, true, random, WorldGenStrongholdPieces.c());
            this.a(world, random, structureboundingbox, this.d, 1, 1, 0);
            if (this.m != EnumDirection.NORTH && this.m != EnumDirection.EAST) {
                this.a(world, structureboundingbox, 4, 1, 1, 4, 3, 3, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            } else {
                this.a(world, structureboundingbox, 0, 1, 1, 0, 3, 3, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            }

            return true;
        }
    }
}
