package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class WorldGenVillageStartPiece extends WorldGenVillageWell {

    public WorldChunkManager a;
    public boolean b;
    public int c;
    public WorldGenVillagePieceWeight d;
    public List e;
    public List f = Lists.newArrayList();
    public List g = Lists.newArrayList();

    public WorldGenVillageStartPiece() {}

    public WorldGenVillageStartPiece(WorldChunkManager worldchunkmanager, int i, Random random, int j, int k, List list, int l) {
        super((WorldGenVillageStartPiece) null, 0, random, j, k);
        this.a = worldchunkmanager;
        this.e = list;
        this.c = l;
        BiomeBase biomebase = worldchunkmanager.getBiome(new BlockPosition(j, 0, k), BiomeBase.ad);

        this.b = biomebase == BiomeBase.DESERT || biomebase == BiomeBase.DESERT_HILLS;
        this.a(this.b);
    }

    public WorldChunkManager e() {
        return this.a;
    }
}
