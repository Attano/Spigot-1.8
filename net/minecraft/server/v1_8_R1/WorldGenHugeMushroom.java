package net.minecraft.server;

import java.util.Random;

public class WorldGenHugeMushroom extends WorldGenerator {

    private int a = -1;

    public WorldGenHugeMushroom(int i) {
        super(true);
        this.a = i;
    }

    public WorldGenHugeMushroom() {
        super(false);
    }

    public boolean generate(World world, Random random, BlockPosition blockposition) {
        int i = random.nextInt(2);

        if (this.a >= 0) {
            i = this.a;
        }

        int j = random.nextInt(3) + 4;
        boolean flag = true;

        if (blockposition.getY() >= 1 && blockposition.getY() + j + 1 < 256) {
            int k;
            int l;

            for (int i1 = blockposition.getY(); i1 <= blockposition.getY() + 1 + j; ++i1) {
                byte b0 = 3;

                if (i1 <= blockposition.getY() + 3) {
                    b0 = 0;
                }

                for (k = blockposition.getX() - b0; k <= blockposition.getX() + b0 && flag; ++k) {
                    for (l = blockposition.getZ() - b0; l <= blockposition.getZ() + b0 && flag; ++l) {
                        if (i1 >= 0 && i1 < 256) {
                            Block block = world.getType(new BlockPosition(k, i1, l)).getBlock();

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

                if (block1 != Blocks.DIRT && block1 != Blocks.GRASS && block1 != Blocks.MYCELIUM) {
                    return false;
                } else {
                    int j1 = blockposition.getY() + j;

                    if (i == 1) {
                        j1 = blockposition.getY() + j - 3;
                    }

                    for (k = j1; k <= blockposition.getY() + j; ++k) {
                        l = 1;
                        if (k < blockposition.getY() + j) {
                            ++l;
                        }

                        if (i == 0) {
                            l = 3;
                        }

                        for (int k1 = blockposition.getX() - l; k1 <= blockposition.getX() + l; ++k1) {
                            for (int l1 = blockposition.getZ() - l; l1 <= blockposition.getZ() + l; ++l1) {
                                int i2 = 5;

                                if (k1 == blockposition.getX() - l) {
                                    --i2;
                                }

                                if (k1 == blockposition.getX() + l) {
                                    ++i2;
                                }

                                if (l1 == blockposition.getZ() - l) {
                                    i2 -= 3;
                                }

                                if (l1 == blockposition.getZ() + l) {
                                    i2 += 3;
                                }

                                if (i == 0 || k < blockposition.getY() + j) {
                                    if ((k1 == blockposition.getX() - l || k1 == blockposition.getX() + l) && (l1 == blockposition.getZ() - l || l1 == blockposition.getZ() + l)) {
                                        continue;
                                    }

                                    if (k1 == blockposition.getX() - (l - 1) && l1 == blockposition.getZ() - l) {
                                        i2 = 1;
                                    }

                                    if (k1 == blockposition.getX() - l && l1 == blockposition.getZ() - (l - 1)) {
                                        i2 = 1;
                                    }

                                    if (k1 == blockposition.getX() + (l - 1) && l1 == blockposition.getZ() - l) {
                                        i2 = 3;
                                    }

                                    if (k1 == blockposition.getX() + l && l1 == blockposition.getZ() - (l - 1)) {
                                        i2 = 3;
                                    }

                                    if (k1 == blockposition.getX() - (l - 1) && l1 == blockposition.getZ() + l) {
                                        i2 = 7;
                                    }

                                    if (k1 == blockposition.getX() - l && l1 == blockposition.getZ() + (l - 1)) {
                                        i2 = 7;
                                    }

                                    if (k1 == blockposition.getX() + (l - 1) && l1 == blockposition.getZ() + l) {
                                        i2 = 9;
                                    }

                                    if (k1 == blockposition.getX() + l && l1 == blockposition.getZ() + (l - 1)) {
                                        i2 = 9;
                                    }
                                }

                                if (i2 == 5 && k < blockposition.getY() + j) {
                                    i2 = 0;
                                }

                                if (i2 != 0 || blockposition.getY() >= blockposition.getY() + j - 1) {
                                    BlockPosition blockposition1 = new BlockPosition(k1, k, l1);

                                    if (!world.getType(blockposition1).getBlock().m()) {
                                        this.a(world, blockposition1, Block.getById(Block.getId(Blocks.BROWN_MUSHROOM_BLOCK) + i), i2);
                                    }
                                }
                            }
                        }
                    }

                    for (k = 0; k < j; ++k) {
                        Block block2 = world.getType(blockposition.up(k)).getBlock();

                        if (!block2.m()) {
                            this.a(world, blockposition.up(k), Block.getById(Block.getId(Blocks.BROWN_MUSHROOM_BLOCK) + i), 10);
                        }
                    }

                    return true;
                }
            }
        } else {
            return false;
        }
    }
}
