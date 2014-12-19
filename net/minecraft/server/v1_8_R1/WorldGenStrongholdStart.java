package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class WorldGenStrongholdStart extends WorldGenStrongholdStairs2 {

    public WorldGenStrongholdPieceWeight a;
    public WorldGenStrongholdPortalRoom b;
    public List c = Lists.newArrayList();

    public WorldGenStrongholdStart() {}

    public WorldGenStrongholdStart(int i, Random random, int j, int k) {
        super(0, random, j, k);
    }

    public BlockPosition a() {
        return this.b != null ? this.b.a() : super.a();
    }
}
