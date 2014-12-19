package net.minecraft.server;

import java.util.Random;

abstract class WorldGenScatteredPiece extends StructurePiece {

    protected int a;
    protected int b;
    protected int c;
    protected int d = -1;

    public WorldGenScatteredPiece() {}

    protected WorldGenScatteredPiece(Random random, int i, int j, int k, int l, int i1, int j1) {
        super(0);
        this.a = l;
        this.b = i1;
        this.c = j1;
        this.m = EnumDirectionLimit.HORIZONTAL.a(random);
        switch (WorldGenJungleTempleUnknown.a[this.m.ordinal()]) {
        case 1:
        case 2:
            this.l = new StructureBoundingBox(i, j, k, i + l - 1, j + i1 - 1, k + j1 - 1);
            break;

        default:
            this.l = new StructureBoundingBox(i, j, k, i + j1 - 1, j + i1 - 1, k + l - 1);
        }

    }

    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.setInt("Width", this.a);
        nbttagcompound.setInt("Height", this.b);
        nbttagcompound.setInt("Depth", this.c);
        nbttagcompound.setInt("HPos", this.d);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        this.a = nbttagcompound.getInt("Width");
        this.b = nbttagcompound.getInt("Height");
        this.c = nbttagcompound.getInt("Depth");
        this.d = nbttagcompound.getInt("HPos");
    }

    protected boolean a(World world, StructureBoundingBox structureboundingbox, int i) {
        if (this.d >= 0) {
            return true;
        } else {
            int j = 0;
            int k = 0;

            for (int l = this.l.c; l <= this.l.f; ++l) {
                for (int i1 = this.l.a; i1 <= this.l.d; ++i1) {
                    BlockPosition blockposition = new BlockPosition(i1, 64, l);

                    if (structureboundingbox.b((BaseBlockPosition) blockposition)) {
                        j += Math.max(world.r(blockposition).getY(), world.worldProvider.getSeaLevel());
                        ++k;
                    }
                }
            }

            if (k == 0) {
                return false;
            } else {
                this.d = j / k;
                this.l.a(0, this.d - this.l.b + i, 0);
                return true;
            }
        }
    }
}
