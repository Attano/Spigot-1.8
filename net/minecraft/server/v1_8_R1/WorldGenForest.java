package net.minecraft.server;

import java.util.Random;

public class WorldGenForest extends WorldGenTreeAbstract {

    private boolean a;

    public WorldGenForest(boolean flag, boolean flag1) {
        super(flag);
        this.a = flag1;
    }

    public boolean generate(World world, Random random, BlockPosition blockposition) {
        int i = random.nextInt(3) + 5;

        if (this.a) {
            i += random.nextInt(7);
        }

        boolean flag = true;

        if (blockposition.getY() >= 1 && blockposition.getY() + i + 1 <= 256) {
            int j;
            int k;

            for (int l = blockposition.getY(); l <= blockposition.getY() + 1 + i; ++l) {
                byte b0 = 1;

                if (l == blockposition.getY()) {
                    b0 = 0;
                }

                if (l >= blockposition.getY() + 1 + i - 2) {
                    b0 = 2;
                }

                for (j = blockposition.getX() - b0; j <= blockposition.getX() + b0 && flag; ++j) {
                    for (k = blockposition.getZ() - b0; k <= blockposition.getZ() + b0 && flag; ++k) {
                        if (l >= 0 && l < 256) {
                            if (!this.a(world.getType(new BlockPosition(j, l, k)).getBlock())) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                Block block = world.getType(blockposition.down()).getBlock();

                if ((block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.FARMLAND) && blockposition.getY() < 256 - i - 1) {
                    this.a(world, blockposition.down());

                    int i1;

                    for (i1 = blockposition.getY() - 3 + i; i1 <= blockposition.getY() + i; ++i1) {
                        j = i1 - (blockposition.getY() + i);
                        k = 1 - j / 2;

                        for (int j1 = blockposition.getX() - k; j1 <= blockposition.getX() + k; ++j1) {
                            int k1 = j1 - blockposition.getX();

                            for (int l1 = blockposition.getZ() - k; l1 <= blockposition.getZ() + k; ++l1) {
                                int i2 = l1 - blockposition.getZ();

                                if (Math.abs(k1) != k || Math.abs(i2) != k || random.nextInt(2) != 0 && j != 0) {
                                    BlockPosition blockposition1 = new BlockPosition(j1, i1, l1);
                                    Block block1 = world.getType(blockposition1).getBlock();

                                    if (block1.getMaterial() == Material.AIR || block1.getMaterial() == Material.LEAVES) {
                                        this.a(world, blockposition1, Blocks.LEAVES, EnumLogVariant.BIRCH.a());
                                    }
                                }
                            }
                        }
                    }

                    for (i1 = 0; i1 < i; ++i1) {
                        Block block2 = world.getType(blockposition.up(i1)).getBlock();

                        if (block2.getMaterial() == Material.AIR || block2.getMaterial() == Material.LEAVES) {
                            this.a(world, blockposition.up(i1), Blocks.LOG, EnumLogVariant.BIRCH.a());
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
