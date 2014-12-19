package net.minecraft.server;

import java.util.Random;

public class BiomeJungle extends BiomeBase {

    private boolean aD;

    public BiomeJungle(int i, boolean flag) {
        super(i);
        this.aD = flag;
        if (flag) {
            this.as.A = 2;
        } else {
            this.as.A = 50;
        }

        this.as.C = 25;
        this.as.B = 4;
        if (!flag) {
            this.at.add(new BiomeMeta(EntityOcelot.class, 2, 1, 1));
        }

        this.au.add(new BiomeMeta(EntityChicken.class, 10, 4, 4));
    }

    public WorldGenTreeAbstract a(Random random) {
        return (WorldGenTreeAbstract) (random.nextInt(10) == 0 ? this.aB : (random.nextInt(2) == 0 ? new WorldGenGroundBush(EnumLogVariant.JUNGLE.a(), EnumLogVariant.OAK.a()) : (!this.aD && random.nextInt(3) == 0 ? new WorldGenJungleTree(false, 10, 20, EnumLogVariant.JUNGLE.a(), EnumLogVariant.JUNGLE.a()) : new WorldGenTrees(false, 4 + random.nextInt(7), EnumLogVariant.JUNGLE.a(), EnumLogVariant.JUNGLE.a(), true))));
    }

    public WorldGenerator b(Random random) {
        return random.nextInt(4) == 0 ? new WorldGenGrass(EnumTallGrassType.FERN) : new WorldGenGrass(EnumTallGrassType.GRASS);
    }

    public void a(World world, Random random, BlockPosition blockposition) {
        super.a(world, random, blockposition);
        int i = random.nextInt(16) + 8;
        int j = random.nextInt(16) + 8;
        int k = random.nextInt(world.getHighestBlockYAt(blockposition.a(i, 0, j)).getY() * 2);

        (new WorldGenMelon()).generate(world, random, blockposition.a(i, k, j));
        WorldGenVines worldgenvines = new WorldGenVines();

        for (j = 0; j < 50; ++j) {
            k = random.nextInt(16) + 8;
            boolean flag = true;
            int l = random.nextInt(16) + 8;

            worldgenvines.generate(world, random, blockposition.a(k, 128, l));
        }

    }
}
