package net.minecraft.server;

import java.util.Arrays;
import java.util.Random;

public class BiomeMesa extends BiomeBase {

    private IBlockData[] aD;
    private long aE;
    private NoiseGenerator3 aF;
    private NoiseGenerator3 aG;
    private NoiseGenerator3 aH;
    private boolean aI;
    private boolean aJ;

    public BiomeMesa(int i, boolean flag, boolean flag1) {
        super(i);
        this.aI = flag;
        this.aJ = flag1;
        this.b();
        this.a(2.0F, 0.0F);
        this.au.clear();
        this.ak = Blocks.SAND.getBlockData().set(BlockSand.VARIANT, EnumSandVariant.RED_SAND);
        this.al = Blocks.STAINED_HARDENED_CLAY.getBlockData();
        this.as.A = -999;
        this.as.D = 20;
        this.as.F = 3;
        this.as.G = 5;
        this.as.B = 0;
        this.au.clear();
        if (flag1) {
            this.as.A = 5;
        }

    }

    public WorldGenTreeAbstract a(Random random) {
        return this.aA;
    }

    public void a(World world, Random random, BlockPosition blockposition) {
        super.a(world, random, blockposition);
    }

    public void a(World world, Random random, ChunkSnapshot chunksnapshot, int i, int j, double d0) {
        if (this.aD == null || this.aE != world.getSeed()) {
            this.a(world.getSeed());
        }

        if (this.aF == null || this.aG == null || this.aE != world.getSeed()) {
            Random random1 = new Random(this.aE);

            this.aF = new NoiseGenerator3(random1, 4);
            this.aG = new NoiseGenerator3(random1, 1);
        }

        this.aE = world.getSeed();
        double d1 = 0.0D;
        int k;
        int l;

        if (this.aI) {
            k = (i & -16) + (j & 15);
            l = (j & -16) + (i & 15);
            double d2 = Math.min(Math.abs(d0), this.aF.a((double) k * 0.25D, (double) l * 0.25D));

            if (d2 > 0.0D) {
                double d3 = 0.001953125D;
                double d4 = Math.abs(this.aG.a((double) k * d3, (double) l * d3));

                d1 = d2 * d2 * 2.5D;
                double d5 = Math.ceil(d4 * 50.0D) + 14.0D;

                if (d1 > d5) {
                    d1 = d5;
                }

                d1 += 64.0D;
            }
        }

        k = i & 15;
        l = j & 15;
        boolean flag = true;
        IBlockData iblockdata = Blocks.STAINED_HARDENED_CLAY.getBlockData();
        IBlockData iblockdata1 = this.al;
        int i1 = (int) (d0 / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        boolean flag1 = Math.cos(d0 / 3.0D * 3.141592653589793D) > 0.0D;
        int j1 = -1;
        boolean flag2 = false;

        for (int k1 = 255; k1 >= 0; --k1) {
            if (chunksnapshot.a(l, k1, k).getBlock().getMaterial() == Material.AIR && k1 < (int) d1) {
                chunksnapshot.a(l, k1, k, Blocks.STONE.getBlockData());
            }

            if (k1 <= random.nextInt(5)) {
                chunksnapshot.a(l, k1, k, Blocks.BEDROCK.getBlockData());
            } else {
                IBlockData iblockdata2 = chunksnapshot.a(l, k1, k);

                if (iblockdata2.getBlock().getMaterial() == Material.AIR) {
                    j1 = -1;
                } else if (iblockdata2.getBlock() == Blocks.STONE) {
                    IBlockData iblockdata3;

                    if (j1 == -1) {
                        flag2 = false;
                        if (i1 <= 0) {
                            iblockdata = null;
                            iblockdata1 = Blocks.STONE.getBlockData();
                        } else if (k1 >= 59 && k1 <= 64) {
                            iblockdata = Blocks.STAINED_HARDENED_CLAY.getBlockData();
                            iblockdata1 = this.al;
                        }

                        if (k1 < 63 && (iblockdata == null || iblockdata.getBlock().getMaterial() == Material.AIR)) {
                            iblockdata = Blocks.WATER.getBlockData();
                        }

                        j1 = i1 + Math.max(0, k1 - 63);
                        if (k1 >= 62) {
                            if (this.aJ && k1 > 86 + i1 * 2) {
                                if (flag1) {
                                    chunksnapshot.a(l, k1, k, Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, EnumDirtVariant.COARSE_DIRT));
                                } else {
                                    chunksnapshot.a(l, k1, k, Blocks.GRASS.getBlockData());
                                }
                            } else if (k1 > 66 + i1) {
                                if (k1 >= 64 && k1 <= 127) {
                                    if (flag1) {
                                        iblockdata3 = Blocks.HARDENED_CLAY.getBlockData();
                                    } else {
                                        iblockdata3 = this.a(i, k1, j);
                                    }
                                } else {
                                    iblockdata3 = Blocks.STAINED_HARDENED_CLAY.getBlockData().set(BlockCloth.COLOR, EnumColor.ORANGE);
                                }

                                chunksnapshot.a(l, k1, k, iblockdata3);
                            } else {
                                chunksnapshot.a(l, k1, k, this.ak);
                                flag2 = true;
                            }
                        } else {
                            chunksnapshot.a(l, k1, k, iblockdata1);
                            if (iblockdata1.getBlock() == Blocks.STAINED_HARDENED_CLAY) {
                                chunksnapshot.a(l, k1, k, iblockdata1.getBlock().getBlockData().set(BlockCloth.COLOR, EnumColor.ORANGE));
                            }
                        }
                    } else if (j1 > 0) {
                        --j1;
                        if (flag2) {
                            chunksnapshot.a(l, k1, k, Blocks.STAINED_HARDENED_CLAY.getBlockData().set(BlockCloth.COLOR, EnumColor.ORANGE));
                        } else {
                            iblockdata3 = this.a(i, k1, j);
                            chunksnapshot.a(l, k1, k, iblockdata3);
                        }
                    }
                }
            }
        }

    }

    private void a(long i) {
        this.aD = new IBlockData[64];
        Arrays.fill(this.aD, Blocks.HARDENED_CLAY.getBlockData());
        Random random = new Random(i);

        this.aH = new NoiseGenerator3(random, 1);

        int j;

        for (j = 0; j < 64; ++j) {
            j += random.nextInt(5) + 1;
            if (j < 64) {
                this.aD[j] = Blocks.STAINED_HARDENED_CLAY.getBlockData().set(BlockCloth.COLOR, EnumColor.ORANGE);
            }
        }

        j = random.nextInt(4) + 2;

        int k;
        int l;
        int i1;
        int j1;

        for (k = 0; k < j; ++k) {
            l = random.nextInt(3) + 1;
            i1 = random.nextInt(64);

            for (j1 = 0; i1 + j1 < 64 && j1 < l; ++j1) {
                this.aD[i1 + j1] = Blocks.STAINED_HARDENED_CLAY.getBlockData().set(BlockCloth.COLOR, EnumColor.YELLOW);
            }
        }

        k = random.nextInt(4) + 2;

        int k1;

        for (l = 0; l < k; ++l) {
            i1 = random.nextInt(3) + 2;
            j1 = random.nextInt(64);

            for (k1 = 0; j1 + k1 < 64 && k1 < i1; ++k1) {
                this.aD[j1 + k1] = Blocks.STAINED_HARDENED_CLAY.getBlockData().set(BlockCloth.COLOR, EnumColor.BROWN);
            }
        }

        l = random.nextInt(4) + 2;

        for (i1 = 0; i1 < l; ++i1) {
            j1 = random.nextInt(3) + 1;
            k1 = random.nextInt(64);

            for (int l1 = 0; k1 + l1 < 64 && l1 < j1; ++l1) {
                this.aD[k1 + l1] = Blocks.STAINED_HARDENED_CLAY.getBlockData().set(BlockCloth.COLOR, EnumColor.RED);
            }
        }

        i1 = random.nextInt(3) + 3;
        j1 = 0;

        for (k1 = 0; k1 < i1; ++k1) {
            byte b0 = 1;

            j1 += random.nextInt(16) + 4;

            for (int i2 = 0; j1 + i2 < 64 && i2 < b0; ++i2) {
                this.aD[j1 + i2] = Blocks.STAINED_HARDENED_CLAY.getBlockData().set(BlockCloth.COLOR, EnumColor.WHITE);
                if (j1 + i2 > 1 && random.nextBoolean()) {
                    this.aD[j1 + i2 - 1] = Blocks.STAINED_HARDENED_CLAY.getBlockData().set(BlockCloth.COLOR, EnumColor.SILVER);
                }

                if (j1 + i2 < 63 && random.nextBoolean()) {
                    this.aD[j1 + i2 + 1] = Blocks.STAINED_HARDENED_CLAY.getBlockData().set(BlockCloth.COLOR, EnumColor.SILVER);
                }
            }
        }

    }

    private IBlockData a(int i, int j, int k) {
        int l = (int) Math.round(this.aH.a((double) i * 1.0D / 512.0D, (double) i * 1.0D / 512.0D) * 2.0D);

        return this.aD[(j + l + 64) % 64];
    }

    protected BiomeBase d(int i) {
        boolean flag = this.id == BiomeBase.MESA.id;
        BiomeMesa biomemesa = new BiomeMesa(i, flag, this.aJ);

        if (!flag) {
            biomemesa.a(BiomeMesa.g);
            biomemesa.a(this.ah + " M");
        } else {
            biomemesa.a(this.ah + " (Bryce)");
        }

        biomemesa.a(this.ai, true);
        return biomemesa;
    }
}
