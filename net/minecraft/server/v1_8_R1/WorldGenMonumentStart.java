package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class WorldGenMonumentStart extends StructureStart {

    private Set c = Sets.newHashSet();
    private boolean d;

    public WorldGenMonumentStart() {}

    public WorldGenMonumentStart(World world, Random random, int i, int j) {
        super(i, j);
        this.b(world, random, i, j);
    }

    private void b(World world, Random random, int i, int j) {
        random.setSeed(world.getSeed());
        long k = random.nextLong();
        long l = random.nextLong();
        long i1 = (long) i * k;
        long j1 = (long) j * l;

        random.setSeed(i1 ^ j1 ^ world.getSeed());
        int k1 = i * 16 + 8 - 29;
        int l1 = j * 16 + 8 - 29;
        EnumDirection enumdirection = EnumDirectionLimit.HORIZONTAL.a(random);

        this.a.add(new WorldGenMonumentPiece1(random, k1, l1, enumdirection));
        this.c();
        this.d = true;
    }

    public void a(World world, Random random, StructureBoundingBox structureboundingbox) {
        if (!this.d) {
            this.a.clear();
            this.b(world, random, this.e(), this.f());
        }

        super.a(world, random, structureboundingbox);
    }

    public boolean a(ChunkCoordIntPair chunkcoordintpair) {
        return this.c.contains(chunkcoordintpair) ? false : super.a(chunkcoordintpair);
    }

    public void b(ChunkCoordIntPair chunkcoordintpair) {
        super.b(chunkcoordintpair);
        this.c.add(chunkcoordintpair);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();
        Iterator iterator = this.c.iterator();

        while (iterator.hasNext()) {
            ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair) iterator.next();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

            nbttagcompound1.setInt("X", chunkcoordintpair.x);
            nbttagcompound1.setInt("Z", chunkcoordintpair.z);
            nbttaglist.add(nbttagcompound1);
        }

        nbttagcompound.set("Processed", nbttaglist);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (nbttagcompound.hasKeyOfType("Processed", 9)) {
            NBTTagList nbttaglist = nbttagcompound.getList("Processed", 10);

            for (int i = 0; i < nbttaglist.size(); ++i) {
                NBTTagCompound nbttagcompound1 = nbttaglist.get(i);

                this.c.add(new ChunkCoordIntPair(nbttagcompound1.getInt("X"), nbttagcompound1.getInt("Z")));
            }
        }

    }
}
