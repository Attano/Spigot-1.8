package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class WorldGenMonument extends StructureGenerator {

    private int f;
    private int g;
    public static final List d = Arrays.asList(new BiomeBase[] { BiomeBase.OCEAN, BiomeBase.DEEP_OCEAN, BiomeBase.RIVER, BiomeBase.FROZEN_OCEAN, BiomeBase.FROZEN_RIVER});
    private static final List h = Lists.newArrayList();

    public WorldGenMonument() {
        this.f = 32;
        this.g = 5;
    }

    public WorldGenMonument(Map map) {
        this();
        Iterator iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();

            if (((String) entry.getKey()).equals("spacing")) {
                this.f = MathHelper.a((String) entry.getValue(), this.f, 1);
            } else if (((String) entry.getKey()).equals("separation")) {
                this.g = MathHelper.a((String) entry.getValue(), this.g, 1);
            }
        }

    }

    public String a() {
        return "Monument";
    }

    protected boolean a(int i, int j) {
        int k = i;
        int l = j;

        if (i < 0) {
            i -= this.f - 1;
        }

        if (j < 0) {
            j -= this.f - 1;
        }

        int i1 = i / this.f;
        int j1 = j / this.f;
        Random random = this.c.a(i1, j1, 10387313);

        i1 *= this.f;
        j1 *= this.f;
        i1 += (random.nextInt(this.f - this.g) + random.nextInt(this.f - this.g)) / 2;
        j1 += (random.nextInt(this.f - this.g) + random.nextInt(this.f - this.g)) / 2;
        if (k == i1 && l == j1) {
            if (this.c.getWorldChunkManager().getBiome(new BlockPosition(k * 16 + 8, 64, l * 16 + 8), (BiomeBase) null) != BiomeBase.DEEP_OCEAN) {
                return false;
            }

            boolean flag = this.c.getWorldChunkManager().a(k * 16 + 8, l * 16 + 8, 29, WorldGenMonument.d);

            if (flag) {
                return true;
            }
        }

        return false;
    }

    protected StructureStart b(int i, int j) {
        return new WorldGenMonumentStart(this.c, this.b, i, j);
    }

    public List b() {
        return WorldGenMonument.h;
    }

    static {
        WorldGenMonument.h.add(new BiomeMeta(EntityGuardian.class, 1, 2, 4));
    }
}
