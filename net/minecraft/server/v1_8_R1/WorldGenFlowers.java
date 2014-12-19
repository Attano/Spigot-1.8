package net.minecraft.server;

import java.util.Random;

public class WorldGenFlowers extends WorldGenerator {

    private BlockFlowers a;
    private IBlockData b;

    public WorldGenFlowers(BlockFlowers blockflowers, EnumFlowerVarient enumflowervarient) {
        this.a(blockflowers, enumflowervarient);
    }

    public void a(BlockFlowers blockflowers, EnumFlowerVarient enumflowervarient) {
        this.a = blockflowers;
        this.b = blockflowers.getBlockData().set(blockflowers.l(), enumflowervarient);
    }

    public boolean generate(World world, Random random, BlockPosition blockposition) {
        for (int i = 0; i < 64; ++i) {
            BlockPosition blockposition1 = blockposition.a(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            if (world.isEmpty(blockposition1) && (!world.worldProvider.o() || blockposition1.getY() < 255) && this.a.f(world, blockposition1, this.b)) {
                world.setTypeAndData(blockposition1, this.b, 2);
            }
        }

        return true;
    }
}
