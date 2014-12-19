package net.minecraft.server;

import java.util.Random;

public class WorldGenTaiga2 extends WorldGenTreeAbstract {

    public WorldGenTaiga2(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, BlockPosition blockposition) {
        int i = random.nextInt(4) + 6;
        int j = 1 + random.nextInt(2);
        int k = i - j;
        int l = 2 + random.nextInt(2);
        boolean flag = true;

        if (blockposition.getY() >= 1 && blockposition.getY() + i + 1 <= 256) {
            int i1;
            int j1;

            for (int k1 = blockposition.getY(); k1 <= blockposition.getY() + 1 + i && flag; ++k1) {
                boolean flag1 = true;

                if (k1 - blockposition.getY() < j) {
                    j1 = 0;
                } else {
                    j1 = l;
                }

                for (i1 = blockposition.getX() - j1; i1 <= blockposition.getX() + j1 && flag; ++i1) {
                    for (int l1 = blockposition.getZ() - j1; l1 <= blockposition.getZ() + j1 && flag; ++l1) {
                        if (k1 >= 0 && k1 < 256) {
                            Block block = world.getType(new BlockPosition(i1, k1, l1)).getBlock();

                            if (block.getMaterial() != Material.AIR && block.getMaterial() != Material.LEAVES) {
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
                Block block1 = world.getType(blockposition.down()).getBlock();

                if ((block1 == Blocks.GRASS || block1 == Blocks.DIRT || block1 == Blocks.FARMLAND) && blockposition.getY() < 256 - i - 1) {
                    this.a(world, blockposition.down());
                    j1 = random.nextInt(2);
                    i1 = 1;
                    byte b0 = 0;

                    int i2;
                    int j2;

                    for (j2 = 0; j2 <= k; ++j2) {
                        i2 = blockposition.getY() + i - j2;

                        for (int k2 = blockposition.getX() - j1; k2 <= blockposition.getX() + j1; ++k2) {
                            int l2 = k2 - blockposition.getX();

                            for (int i3 = blockposition.getZ() - j1; i3 <= blockposition.getZ() + j1; ++i3) {
                                int j3 = i3 - blockposition.getZ();

                                if (Math.abs(l2) != j1 || Math.abs(j3) != j1 || j1 <= 0) {
                                    BlockPosition blockposition1 = new BlockPosition(k2, i2, i3);

                                    if (!world.getType(blockposition1).getBlock().m()) {
                                        this.a(world, blockposition1, Blocks.LEAVES, EnumLogVariant.SPRUCE.a());
                                    }
                                }
                            }
                        }

                        if (j1 >= i1) {
                            j1 = b0;
                            b0 = 1;
                            ++i1;
                            if (i1 > l) {
                                i1 = l;
                            }
                        } else {
                            ++j1;
                        }
                    }

                    j2 = random.nextInt(3);

                    for (i2 = 0; i2 < i - j2; ++i2) {
                        Block block2 = world.getType(blockposition.up(i2)).getBlock();

                        if (block2.getMaterial() == Material.AIR || block2.getMaterial() == Material.LEAVES) {
                            this.a(world, blockposition.up(i2), Blocks.LOG, EnumLogVariant.SPRUCE.a());
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
