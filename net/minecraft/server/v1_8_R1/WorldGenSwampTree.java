package net.minecraft.server;

import java.util.Random;

public class WorldGenSwampTree extends WorldGenTreeAbstract {

    public WorldGenSwampTree() {
        super(false);
    }

    public boolean generate(World world, Random random, BlockPosition blockposition) {
        int i;

        for (i = random.nextInt(4) + 5; world.getType(blockposition.down()).getBlock().getMaterial() == Material.WATER; blockposition = blockposition.down()) {
            ;
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
                    b0 = 3;
                }

                for (j = blockposition.getX() - b0; j <= blockposition.getX() + b0 && flag; ++j) {
                    for (k = blockposition.getZ() - b0; k <= blockposition.getZ() + b0 && flag; ++k) {
                        if (l >= 0 && l < 256) {
                            Block block = world.getType(new BlockPosition(j, l, k)).getBlock();

                            if (block.getMaterial() != Material.AIR && block.getMaterial() != Material.LEAVES) {
                                if (block != Blocks.WATER && block != Blocks.FLOWING_WATER) {
                                    flag = false;
                                } else if (l > blockposition.getY()) {
                                    flag = false;
                                }
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

                if ((block1 == Blocks.GRASS || block1 == Blocks.DIRT) && blockposition.getY() < 256 - i - 1) {
                    this.a(world, blockposition.down());

                    int i1;
                    BlockPosition blockposition1;
                    int j1;
                    int k1;

                    for (j1 = blockposition.getY() - 3 + i; j1 <= blockposition.getY() + i; ++j1) {
                        j = j1 - (blockposition.getY() + i);
                        k = 2 - j / 2;

                        for (k1 = blockposition.getX() - k; k1 <= blockposition.getX() + k; ++k1) {
                            i1 = k1 - blockposition.getX();

                            for (int l1 = blockposition.getZ() - k; l1 <= blockposition.getZ() + k; ++l1) {
                                int i2 = l1 - blockposition.getZ();

                                if (Math.abs(i1) != k || Math.abs(i2) != k || random.nextInt(2) != 0 && j != 0) {
                                    blockposition1 = new BlockPosition(k1, j1, l1);
                                    if (!world.getType(blockposition1).getBlock().m()) {
                                        this.a(world, blockposition1, (Block) Blocks.LEAVES);
                                    }
                                }
                            }
                        }
                    }

                    for (j1 = 0; j1 < i; ++j1) {
                        Block block2 = world.getType(blockposition.up(j1)).getBlock();

                        if (block2.getMaterial() == Material.AIR || block2.getMaterial() == Material.LEAVES || block2 == Blocks.FLOWING_WATER || block2 == Blocks.WATER) {
                            this.a(world, blockposition.up(j1), Blocks.LOG);
                        }
                    }

                    for (j1 = blockposition.getY() - 3 + i; j1 <= blockposition.getY() + i; ++j1) {
                        j = j1 - (blockposition.getY() + i);
                        k = 2 - j / 2;

                        for (k1 = blockposition.getX() - k; k1 <= blockposition.getX() + k; ++k1) {
                            for (i1 = blockposition.getZ() - k; i1 <= blockposition.getZ() + k; ++i1) {
                                BlockPosition blockposition2 = new BlockPosition(k1, j1, i1);

                                if (world.getType(blockposition2).getBlock().getMaterial() == Material.LEAVES) {
                                    BlockPosition blockposition3 = blockposition2.west();

                                    blockposition1 = blockposition2.east();
                                    BlockPosition blockposition4 = blockposition2.north();
                                    BlockPosition blockposition5 = blockposition2.south();

                                    if (random.nextInt(4) == 0 && world.getType(blockposition3).getBlock().getMaterial() == Material.AIR) {
                                        this.a(world, blockposition3, BlockVine.S);
                                    }

                                    if (random.nextInt(4) == 0 && world.getType(blockposition1).getBlock().getMaterial() == Material.AIR) {
                                        this.a(world, blockposition1, BlockVine.T);
                                    }

                                    if (random.nextInt(4) == 0 && world.getType(blockposition4).getBlock().getMaterial() == Material.AIR) {
                                        this.a(world, blockposition4, BlockVine.Q);
                                    }

                                    if (random.nextInt(4) == 0 && world.getType(blockposition5).getBlock().getMaterial() == Material.AIR) {
                                        this.a(world, blockposition5, BlockVine.R);
                                    }
                                }
                            }
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

    private void a(World world, BlockPosition blockposition, int i) {
        this.a(world, blockposition, Blocks.VINE, i);
        int j = 4;

        for (blockposition = blockposition.down(); world.getType(blockposition).getBlock().getMaterial() == Material.AIR && j > 0; --j) {
            this.a(world, blockposition, Blocks.VINE, i);
            blockposition = blockposition.down();
        }

    }
}
