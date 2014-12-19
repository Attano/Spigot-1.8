package net.minecraft.server;

import java.util.Random;

public class WorldGenForestTree extends WorldGenTreeAbstract {

    public WorldGenForestTree(boolean flag) {
        super(flag);
    }

    public boolean generate(World world, Random random, BlockPosition blockposition) {
        int i = random.nextInt(3) + random.nextInt(2) + 6;
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

                if ((block == Blocks.GRASS || block == Blocks.DIRT) && blockposition.getY() < 256 - i - 1) {
                    this.a(world, blockposition.down());
                    this.a(world, blockposition.a(1, -1, 0));
                    this.a(world, blockposition.a(1, -1, 1));
                    this.a(world, blockposition.a(0, -1, 1));
                    EnumDirection enumdirection = EnumDirectionLimit.HORIZONTAL.a(random);

                    j = i - random.nextInt(4);
                    k = 2 - random.nextInt(3);
                    int i1 = blockposition.getX();
                    int j1 = blockposition.getZ();
                    int k1 = 0;

                    int l1;
                    int i2;

                    for (l1 = 0; l1 < i; ++l1) {
                        i2 = blockposition.getY() + l1;
                        if (l1 >= j && k > 0) {
                            i1 += enumdirection.getAdjacentX();
                            j1 += enumdirection.getAdjacentZ();
                            --k;
                        }

                        BlockPosition blockposition1 = new BlockPosition(i1, i2, j1);
                        Material material = world.getType(blockposition1).getBlock().getMaterial();

                        if (material == Material.AIR || material == Material.LEAVES) {
                            this.a(world, blockposition1, Blocks.LOG2, EnumLogVariant.DARK_OAK.a() - 4);
                            this.a(world, blockposition1.east(), Blocks.LOG2, EnumLogVariant.DARK_OAK.a() - 4);
                            this.a(world, blockposition1.south(), Blocks.LOG2, EnumLogVariant.DARK_OAK.a() - 4);
                            this.a(world, blockposition1.east().south(), Blocks.LOG2, EnumLogVariant.DARK_OAK.a() - 4);
                            k1 = i2;
                        }
                    }

                    for (l1 = -2; l1 <= 0; ++l1) {
                        for (i2 = -2; i2 <= 0; ++i2) {
                            byte b1 = -1;

                            this.a(world, i1 + l1, k1 + b1, j1 + i2);
                            this.a(world, 1 + i1 - l1, k1 + b1, j1 + i2);
                            this.a(world, i1 + l1, k1 + b1, 1 + j1 - i2);
                            this.a(world, 1 + i1 - l1, k1 + b1, 1 + j1 - i2);
                            if ((l1 > -2 || i2 > -1) && (l1 != -1 || i2 != -2)) {
                                byte b2 = 1;

                                this.a(world, i1 + l1, k1 + b2, j1 + i2);
                                this.a(world, 1 + i1 - l1, k1 + b2, j1 + i2);
                                this.a(world, i1 + l1, k1 + b2, 1 + j1 - i2);
                                this.a(world, 1 + i1 - l1, k1 + b2, 1 + j1 - i2);
                            }
                        }
                    }

                    if (random.nextBoolean()) {
                        this.a(world, i1, k1 + 2, j1);
                        this.a(world, i1 + 1, k1 + 2, j1);
                        this.a(world, i1 + 1, k1 + 2, j1 + 1);
                        this.a(world, i1, k1 + 2, j1 + 1);
                    }

                    for (l1 = -3; l1 <= 4; ++l1) {
                        for (i2 = -3; i2 <= 4; ++i2) {
                            if ((l1 != -3 || i2 != -3) && (l1 != -3 || i2 != 4) && (l1 != 4 || i2 != -3) && (l1 != 4 || i2 != 4) && (Math.abs(l1) < 3 || Math.abs(i2) < 3)) {
                                this.a(world, i1 + l1, k1, j1 + i2);
                            }
                        }
                    }

                    for (l1 = -1; l1 <= 2; ++l1) {
                        for (i2 = -1; i2 <= 2; ++i2) {
                            if ((l1 < 0 || l1 > 1 || i2 < 0 || i2 > 1) && random.nextInt(3) <= 0) {
                                int j2 = random.nextInt(3) + 2;

                                int k2;

                                for (k2 = 0; k2 < j2; ++k2) {
                                    // Spigot start
                                    BlockPosition position = new BlockPosition(blockposition.getX() + l1, k1 - k2 - 1, blockposition.getZ() + i2);
                                    Block bl = world.getType(position).getBlock();

                                    if (bl.getMaterial() == Material.AIR || bl.getMaterial() == Material.LEAVES)
                                    {
                                        this.a(world, position, Blocks.LOG2, EnumLogVariant.DARK_OAK.a() - 4);
                                    }
                                    // Spigot end
                                }

                                int l2;

                                for (k2 = -1; k2 <= 1; ++k2) {
                                    for (l2 = -1; l2 <= 1; ++l2) {
                                        this.a(world, i1 + l1 + k2, k1 - 0, j1 + i2 + l2);
                                    }
                                }

                                for (k2 = -2; k2 <= 2; ++k2) {
                                    for (l2 = -2; l2 <= 2; ++l2) {
                                        if (Math.abs(k2) != 2 || Math.abs(l2) != 2) {
                                            this.a(world, i1 + l1 + k2, k1 - 1, j1 + i2 + l2);
                                        }
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

    private void a(World world, int i, int j, int k) {
        Block block = world.getType(new BlockPosition(i, j, k)).getBlock();

        if (block.getMaterial() == Material.AIR) {
            this.a(world, new BlockPosition(i, j, k), Blocks.LEAVES2, 1);
        }

    }
}
