package net.minecraft.server;

import java.util.Random;

public class WorldGenTrees extends WorldGenTreeAbstract {

    private final int a;
    private final boolean b;
    private final int c;
    private final int d;

    public WorldGenTrees(boolean flag) {
        this(flag, 4, 0, 0, false);
    }

    public WorldGenTrees(boolean flag, int i, int j, int k, boolean flag1) {
        super(flag);
        this.a = i;
        this.c = j;
        this.d = k;
        this.b = flag1;
    }

    public boolean generate(World world, Random random, BlockPosition blockposition) {
        int i = random.nextInt(3) + this.a;
        boolean flag = true;

        if (blockposition.getY() >= 1 && blockposition.getY() + i + 1 <= 256) {
            byte b0;
            int j;

            for (int k = blockposition.getY(); k <= blockposition.getY() + 1 + i; ++k) {
                b0 = 1;
                if (k == blockposition.getY()) {
                    b0 = 0;
                }

                if (k >= blockposition.getY() + 1 + i - 2) {
                    b0 = 2;
                }

                for (int l = blockposition.getX() - b0; l <= blockposition.getX() + b0 && flag; ++l) {
                    for (j = blockposition.getZ() - b0; j <= blockposition.getZ() + b0 && flag; ++j) {
                        if (k >= 0 && k < 256) {
                            if (!this.a(world.getType(new BlockPosition(l, k, j)).getBlock())) {
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
                    b0 = 3;
                    byte b1 = 0;

                    int i1;
                    int j1;
                    int k1;
                    int l1;
                    BlockPosition blockposition1;

                    for (j = blockposition.getY() - b0 + i; j <= blockposition.getY() + i; ++j) {
                        i1 = j - (blockposition.getY() + i);
                        j1 = b1 + 1 - i1 / 2;

                        for (k1 = blockposition.getX() - j1; k1 <= blockposition.getX() + j1; ++k1) {
                            l1 = k1 - blockposition.getX();

                            for (int i2 = blockposition.getZ() - j1; i2 <= blockposition.getZ() + j1; ++i2) {
                                int j2 = i2 - blockposition.getZ();

                                if (Math.abs(l1) != j1 || Math.abs(j2) != j1 || random.nextInt(2) != 0 && i1 != 0) {
                                    blockposition1 = new BlockPosition(k1, j, i2);
                                    Block block1 = world.getType(blockposition1).getBlock();

                                    if (block1.getMaterial() == Material.AIR || block1.getMaterial() == Material.LEAVES || block1.getMaterial() == Material.REPLACEABLE_PLANT) {
                                        this.a(world, blockposition1, Blocks.LEAVES, this.d);
                                    }
                                }
                            }
                        }
                    }

                    for (j = 0; j < i; ++j) {
                        Block block2 = world.getType(blockposition.up(j)).getBlock();

                        if (block2.getMaterial() == Material.AIR || block2.getMaterial() == Material.LEAVES || block2.getMaterial() == Material.REPLACEABLE_PLANT) {
                            this.a(world, blockposition.up(j), Blocks.LOG, this.c);
                            if (this.b && j > 0) {
                                if (random.nextInt(3) > 0 && world.isEmpty(blockposition.a(-1, j, 0))) {
                                    this.a(world, blockposition.a(-1, j, 0), Blocks.VINE, BlockVine.S);
                                }

                                if (random.nextInt(3) > 0 && world.isEmpty(blockposition.a(1, j, 0))) {
                                    this.a(world, blockposition.a(1, j, 0), Blocks.VINE, BlockVine.T);
                                }

                                if (random.nextInt(3) > 0 && world.isEmpty(blockposition.a(0, j, -1))) {
                                    this.a(world, blockposition.a(0, j, -1), Blocks.VINE, BlockVine.Q);
                                }

                                if (random.nextInt(3) > 0 && world.isEmpty(blockposition.a(0, j, 1))) {
                                    this.a(world, blockposition.a(0, j, 1), Blocks.VINE, BlockVine.R);
                                }
                            }
                        }
                    }

                    if (this.b) {
                        for (j = blockposition.getY() - 3 + i; j <= blockposition.getY() + i; ++j) {
                            i1 = j - (blockposition.getY() + i);
                            j1 = 2 - i1 / 2;

                            for (k1 = blockposition.getX() - j1; k1 <= blockposition.getX() + j1; ++k1) {
                                for (l1 = blockposition.getZ() - j1; l1 <= blockposition.getZ() + j1; ++l1) {
                                    BlockPosition blockposition2 = new BlockPosition(k1, j, l1);

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

                        if (random.nextInt(5) == 0 && i > 5) {
                            for (j = 0; j < 2; ++j) {
                                for (i1 = 0; i1 < 4; ++i1) {
                                    if (random.nextInt(4 - j) == 0) {
                                        j1 = random.nextInt(3);
                                        EnumDirection enumdirection = EnumDirection.fromType2(i1).opposite();

                                        this.a(world, blockposition.a(enumdirection.getAdjacentX(), i - 5 + j, enumdirection.getAdjacentZ()), Blocks.COCOA, j1 << 2 | EnumDirection.fromType2(i1).b());
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
