package net.minecraft.server;

import java.util.Random;

public class BiomeSavanna extends BiomeBase {

    private static final WorldGenAcaciaTree aD = new WorldGenAcaciaTree(false);

    protected BiomeSavanna(int i) {
        super(i);
        this.au.add(new BiomeMeta(EntityHorse.class, 1, 2, 6));
        this.as.A = 1;
        this.as.B = 4;
        this.as.C = 20;
    }

    public WorldGenTreeAbstract a(Random random) {
        return (WorldGenTreeAbstract) (random.nextInt(5) > 0 ? BiomeSavanna.aD : this.aA);
    }

    protected BiomeBase d(int i) {
        BiomeSavannaSub biomesavannasub = new BiomeSavannaSub(i, this);

        biomesavannasub.temperature = (this.temperature + 1.0F) * 0.5F;
        biomesavannasub.an = this.an * 0.5F + 0.3F;
        biomesavannasub.ao = this.ao * 0.5F + 1.2F;
        return biomesavannasub;
    }

    public void a(World world, Random random, BlockPosition blockposition) {
        BiomeSavanna.ag.a(EnumTallFlowerVariants.GRASS);

        for (int i = 0; i < 7; ++i) {
            int j = random.nextInt(16) + 8;
            int k = random.nextInt(16) + 8;
            int l = random.nextInt(world.getHighestBlockYAt(blockposition.a(j, 0, k)).getY() + 32);

            BiomeSavanna.ag.generate(world, random, blockposition.a(j, l, k));
        }

        super.a(world, random, blockposition);
    }
}
