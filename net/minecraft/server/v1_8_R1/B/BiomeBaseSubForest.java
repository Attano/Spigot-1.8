package net.minecraft.server;

import java.util.Random;

class BiomeBaseSubForest extends BiomeBaseSub {

    final BiomeForest aD;

    BiomeBaseSubForest(BiomeForest biomeforest, int i, BiomeBase biomebase) {
        super(i, biomebase);
        this.aD = biomeforest;
    }

    public WorldGenTreeAbstract a(Random random) {
        return random.nextBoolean() ? BiomeForest.aD : BiomeForest.aE;
    }
}
