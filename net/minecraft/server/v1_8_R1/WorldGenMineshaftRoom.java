package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WorldGenMineshaftRoom extends StructurePiece {

    private List a = Lists.newLinkedList();

    public WorldGenMineshaftRoom() {}

    public WorldGenMineshaftRoom(int i, Random random, int j, int k) {
        super(i);
        this.l = new StructureBoundingBox(j, 50, k, j + 7 + random.nextInt(6), 54 + random.nextInt(6), k + 7 + random.nextInt(6));
    }

    public void a(StructurePiece structurepiece, List list, Random random) {
        int i = this.d();
        int j = this.l.d() - 3 - 1;

        if (j <= 0) {
            j = 1;
        }

        int k;
        StructurePiece structurepiece1;
        StructureBoundingBox structureboundingbox;

        for (k = 0; k < this.l.c(); k += 4) {
            k += random.nextInt(this.l.c());
            if (k + 3 > this.l.c()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a + k, this.l.b + random.nextInt(j) + 1, this.l.c - 1, EnumDirection.NORTH, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.c();
                this.a.add(new StructureBoundingBox(structureboundingbox.a, structureboundingbox.b, this.l.c, structureboundingbox.d, structureboundingbox.e, this.l.c + 1));
            }
        }

        for (k = 0; k < this.l.c(); k += 4) {
            k += random.nextInt(this.l.c());
            if (k + 3 > this.l.c()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a + k, this.l.b + random.nextInt(j) + 1, this.l.f + 1, EnumDirection.SOUTH, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.c();
                this.a.add(new StructureBoundingBox(structureboundingbox.a, structureboundingbox.b, this.l.f - 1, structureboundingbox.d, structureboundingbox.e, this.l.f));
            }
        }

        for (k = 0; k < this.l.e(); k += 4) {
            k += random.nextInt(this.l.e());
            if (k + 3 > this.l.e()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.a - 1, this.l.b + random.nextInt(j) + 1, this.l.c + k, EnumDirection.WEST, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.c();
                this.a.add(new StructureBoundingBox(this.l.a, structureboundingbox.b, structureboundingbox.c, this.l.a + 1, structureboundingbox.e, structureboundingbox.f));
            }
        }

        for (k = 0; k < this.l.e(); k += 4) {
            k += random.nextInt(this.l.e());
            if (k + 3 > this.l.e()) {
                break;
            }

            structurepiece1 = WorldGenMineshaftPieces.a(structurepiece, list, random, this.l.d + 1, this.l.b + random.nextInt(j) + 1, this.l.c + k, EnumDirection.EAST, i);
            if (structurepiece1 != null) {
                structureboundingbox = structurepiece1.c();
                this.a.add(new StructureBoundingBox(this.l.d - 1, structureboundingbox.b, structureboundingbox.c, this.l.d, structureboundingbox.e, structureboundingbox.f));
            }
        }

    }

    public boolean a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (this.a(world, structureboundingbox)) {
            return false;
        } else {
            this.a(world, structureboundingbox, this.l.a, this.l.b, this.l.c, this.l.d, this.l.b, this.l.f, Blocks.DIRT.getBlockData(), Blocks.AIR.getBlockData(), true);
            this.a(world, structureboundingbox, this.l.a, this.l.b + 1, this.l.c, this.l.d, Math.min(this.l.b + 3, this.l.e), this.l.f, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            Iterator iterator = this.a.iterator();

            while (iterator.hasNext()) {
                StructureBoundingBox structureboundingbox1 = (StructureBoundingBox) iterator.next();

                this.a(world, structureboundingbox, structureboundingbox1.a, structureboundingbox1.e - 2, structureboundingbox1.c, structureboundingbox1.d, structureboundingbox1.e, structureboundingbox1.f, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
            }

            this.a(world, structureboundingbox, this.l.a, this.l.b + 4, this.l.c, this.l.d, this.l.e, this.l.f, Blocks.AIR.getBlockData(), false);
            return true;
        }
    }

    protected void a(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = new NBTTagList();
        Iterator iterator = this.a.iterator();

        while (iterator.hasNext()) {
            StructureBoundingBox structureboundingbox = (StructureBoundingBox) iterator.next();

            nbttaglist.add(structureboundingbox.g());
        }

        nbttagcompound.set("Entrances", nbttaglist);
    }

    protected void b(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = nbttagcompound.getList("Entrances", 11);

        for (int i = 0; i < nbttaglist.size(); ++i) {
            this.a.add(new StructureBoundingBox(nbttaglist.c(i)));
        }

    }
}
