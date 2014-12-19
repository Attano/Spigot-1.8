package net.minecraft.server;

import java.util.Random;

public class WorldGenTaiga1 extends WorldGenTreeAbstract {

    public WorldGenTaiga1() {
        super(false);
    }

    public boolean generate(World world, Random random, BlockPosition blockposition) {
        int i = random.nextInt(5) + 7;
        int j = i - random.nextInt(2) - 3;
        int k = i - j;
        int l = 1 + random.nextInt(k + 1);
        boolean flag = true;

        if (blockposition.getY() >= 1 && blockposition.getY() + i + 1 <= 256) {
            int i1;
            int j1;
            int k1;

            for (int l1 = blockposition.getY(); l1 <= blockposition.getY() + 1 + i && flag; ++l1) {
                boolean flag1 = true;

                if (l1 - blockposition.getY() < j) {
                    k1 = 0;
                } else {
                    k1 = l;
                }

                for (i1 = blockposition.getX() - k1; i1 <= blockposition.getX() + k1 && flag; ++i1) {
                    for (j1 = blockposition.getZ() - k1; j1 <= blockposition.getZ() + k1 && flag; ++j1) {
                        if (l1 >= 0 && l1 < 256) {
                            if (!this.a(world.getType(new BlockPosition(i1, l1, j1)).getBlock())) {
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

                if ((block == Blocks.GRASS || block == Blocks.DIRT) && blockposition.getY() < 256 - i - 1) {
                    this.a(world, blockposition.down());
                    k1 = 0;

                    for (i1 = blockposition.getY() + i; i1 >= blockposition.getY() + j; --i1) {
                        for (j1 = blockposition.getX() - k1; j1 <= blockposition.getX() + k1; ++j1) {
                            int i2 = j1 - blockposition.getX();

                            for (int j2 = blockposition.getZ() - k1; j2 <= blockposition.getZ() + k1; ++j2) {
                                int k2 = j2 - blockposition.getZ();

                                if (Math.abs(i2) != k1 || Math.abs(k2) != k1 || k1 <= 0) {
                                    BlockPosition blockposition1 = new BlockPosition(j1, i1, j2);

                                    if (!world.getType(blockposition1).getBlock().m()) {
                                        this.a(world, blockposition1, Blocks.LEAVES, EnumLogVariant.SPRUCE.a());
                                    }
                                }
                            }
                        }

                        if (k1 >= 1 && i1 == blockposition.getY() + j + 1) {
                            --k1;
                        } else if (k1 < l) {
                            ++k1;
                        }
                    }

                    for (i1 = 0; i1 < i - 1; ++i1) {
                        Block block1 = world.getType(blockposition.up(i1)).getBlock();

                        if (block1.getMaterial() == Material.AIR || block1.getMaterial() == Material.LEAVES) {
                            this.a(world, blockposition.up(i1), Blocks.LOG, EnumLogVariant.SPRUCE.a());
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
