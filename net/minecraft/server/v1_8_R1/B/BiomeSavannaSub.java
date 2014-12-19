package net.minecraft.server;

import java.util.Random;

public class BiomeSavannaSub extends BiomeBaseSub {

    public BiomeSavannaSub(int i, BiomeBase biomebase) {
        super(i, biomebase);
        this.as.A = 2;
        this.as.B = 2;
        this.as.C = 5;
    }

    public void a(World world, Random random, ChunkSnapshot chunksnapshot, int i, int j, double d0) {
        this.ak = Blocks.GRASS.getBlockData();
        this.al = Blocks.DIRT.getBlockData();
        if (d0 > 1.75D) {
            this.ak = Blocks.STONE.getBlockData();
            this.al = Blocks.STONE.getBlockData();
        } else if (d0 > -0.5D) {
            this.ak = Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, EnumDirtVariant.COARSE_DIRT);
        }

        this.b(world, random, chunksnapshot, i, j, d0);
    }

    public void a(World world, Random random, BlockPosition blockposition) {
        this.as.a(world, random, this, blockposition);
    }
}
