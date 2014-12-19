package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;

public class BiomeCache {

    private final WorldChunkManager a;
    private long b;
    private LongHashMap c = new LongHashMap();
    private List d = Lists.newArrayList();

    public BiomeCache(WorldChunkManager worldchunkmanager) {
        this.a = worldchunkmanager;
    }

    public BiomeCacheBlock a(int i, int j) {
        i >>= 4;
        j >>= 4;
        long k = (long) i & 4294967295L | ((long) j & 4294967295L) << 32;
        BiomeCacheBlock biomecacheblock = (BiomeCacheBlock) this.c.getEntry(k);

        if (biomecacheblock == null) {
            biomecacheblock = new BiomeCacheBlock(this, i, j);
            this.c.put(k, biomecacheblock);
            this.d.add(biomecacheblock);
        }

        biomecacheblock.e = MinecraftServer.ax();
        return biomecacheblock;
    }

    public BiomeBase a(int i, int j, BiomeBase biomebase) {
        BiomeBase biomebase1 = this.a(i, j).a(i, j);

        return biomebase1 == null ? biomebase : biomebase1;
    }

    public void a() {
        long i = MinecraftServer.ax();
        long j = i - this.b;

        if (j > 7500L || j < 0L) {
            this.b = i;

            for (int k = 0; k < this.d.size(); ++k) {
                BiomeCacheBlock biomecacheblock = (BiomeCacheBlock) this.d.get(k);
                long l = i - biomecacheblock.e;

                if (l > 30000L || l < 0L) {
                    this.d.remove(k--);
                    long i1 = (long) biomecacheblock.c & 4294967295L | ((long) biomecacheblock.d & 4294967295L) << 32;

                    this.c.remove(i1);
                }
            }
        }

    }

    public BiomeBase[] c(int i, int j) {
        return this.a(i, j).b;
    }

    static WorldChunkManager a(BiomeCache biomecache) {
        return biomecache.a;
    }
}
