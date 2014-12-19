package net.minecraft.server;

import java.util.Random;

public class BlockTallPlant extends BlockPlant implements IBlockFragilePlantElement {

    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumTallFlowerVariants.class);
    public static final BlockStateEnum HALF = BlockStateEnum.of("half", EnumTallPlantHalf.class);

    public BlockTallPlant() {
        super(Material.REPLACEABLE_PLANT);
        this.j(this.blockStateList.getBlockData().set(BlockTallPlant.VARIANT, EnumTallFlowerVariants.SUNFLOWER).set(BlockTallPlant.HALF, EnumTallPlantHalf.LOWER));
        this.c(0.0F);
        this.a(BlockTallPlant.h);
        this.c("doublePlant");
    }

    public void updateShape(IBlockAccess iblockaccess, BlockPosition blockposition) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public EnumTallFlowerVariants e(IBlockAccess iblockaccess, BlockPosition blockposition) {
        IBlockData iblockdata = iblockaccess.getType(blockposition);

        if (iblockdata.getBlock() == this) {
            iblockdata = this.updateState(iblockdata, iblockaccess, blockposition);
            return (EnumTallFlowerVariants) iblockdata.get(BlockTallPlant.VARIANT);
        } else {
            return EnumTallFlowerVariants.FERN;
        }
    }

    public boolean canPlace(World world, BlockPosition blockposition) {
        return super.canPlace(world, blockposition) && world.isEmpty(blockposition.up());
    }

    public boolean f(World world, BlockPosition blockposition) {
        IBlockData iblockdata = world.getType(blockposition);

        if (iblockdata.getBlock() != this) {
            return true;
        } else {
            EnumTallFlowerVariants enumtallflowervariants = (EnumTallFlowerVariants) this.updateState(iblockdata, world, blockposition).get(BlockTallPlant.VARIANT);

            return enumtallflowervariants == EnumTallFlowerVariants.FERN || enumtallflowervariants == EnumTallFlowerVariants.GRASS;
        }
    }

    protected void e(World world, BlockPosition blockposition, IBlockData iblockdata) {
        if (!this.f(world, blockposition, iblockdata)) {
            boolean flag = iblockdata.get(BlockTallPlant.HALF) == EnumTallPlantHalf.UPPER;
            BlockPosition blockposition1 = flag ? blockposition : blockposition.up();
            BlockPosition blockposition2 = flag ? blockposition.down() : blockposition;
            Object object = flag ? this : world.getType(blockposition1).getBlock();
            Object object1 = flag ? world.getType(blockposition2).getBlock() : this;

            if (object == this) {
                world.setTypeAndData(blockposition1, Blocks.AIR.getBlockData(), 3);
            }

            if (object1 == this) {
                world.setTypeAndData(blockposition2, Blocks.AIR.getBlockData(), 3);
                if (!flag) {
                    this.b(world, blockposition2, iblockdata, 0);
                }
            }

        }
    }

    public boolean f(World world, BlockPosition blockposition, IBlockData iblockdata) {
        if (iblockdata.get(BlockTallPlant.HALF) == EnumTallPlantHalf.UPPER) {
            return world.getType(blockposition.down()).getBlock() == this;
        } else {
            IBlockData iblockdata1 = world.getType(blockposition.up());

            return iblockdata1.getBlock() == this && super.f(world, blockposition, iblockdata1);
        }
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        if (iblockdata.get(BlockTallPlant.HALF) == EnumTallPlantHalf.UPPER) {
            return null;
        } else {
            EnumTallFlowerVariants enumtallflowervariants = (EnumTallFlowerVariants) iblockdata.get(BlockTallPlant.VARIANT);

            return enumtallflowervariants == EnumTallFlowerVariants.FERN ? null : (enumtallflowervariants == EnumTallFlowerVariants.GRASS ? (random.nextInt(8) == 0 ? Items.WHEAT_SEEDS : null) : Item.getItemOf(this));
        }
    }

    public int getDropData(IBlockData iblockdata) {
        return iblockdata.get(BlockTallPlant.HALF) != EnumTallPlantHalf.UPPER && iblockdata.get(BlockTallPlant.VARIANT) != EnumTallFlowerVariants.GRASS ? ((EnumTallFlowerVariants) iblockdata.get(BlockTallPlant.VARIANT)).a() : 0;
    }

    public void a(World world, BlockPosition blockposition, EnumTallFlowerVariants enumtallflowervariants, int i) {
        world.setTypeAndData(blockposition, this.getBlockData().set(BlockTallPlant.HALF, EnumTallPlantHalf.LOWER).set(BlockTallPlant.VARIANT, enumtallflowervariants), i);
        world.setTypeAndData(blockposition.up(), this.getBlockData().set(BlockTallPlant.HALF, EnumTallPlantHalf.UPPER), i);
    }

    public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
        world.setTypeAndData(blockposition.up(), this.getBlockData().set(BlockTallPlant.HALF, EnumTallPlantHalf.UPPER), 2);
    }

    public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, TileEntity tileentity) {
        if (world.isStatic || entityhuman.bY() == null || entityhuman.bY().getItem() != Items.SHEARS || iblockdata.get(BlockTallPlant.HALF) != EnumTallPlantHalf.LOWER || !this.b(world, blockposition, iblockdata, entityhuman)) {
            super.a(world, entityhuman, blockposition, iblockdata, tileentity);
        }
    }

    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
        if (iblockdata.get(BlockTallPlant.HALF) == EnumTallPlantHalf.UPPER) {
            if (world.getType(blockposition.down()).getBlock() == this) {
                if (!entityhuman.abilities.canInstantlyBuild) {
                    IBlockData iblockdata1 = world.getType(blockposition.down());
                    EnumTallFlowerVariants enumtallflowervariants = (EnumTallFlowerVariants) iblockdata1.get(BlockTallPlant.VARIANT);

                    if (enumtallflowervariants != EnumTallFlowerVariants.FERN && enumtallflowervariants != EnumTallFlowerVariants.GRASS) {
                        world.setAir(blockposition.down(), true);
                    } else if (!world.isStatic) {
                        if (entityhuman.bY() != null && entityhuman.bY().getItem() == Items.SHEARS) {
                            this.b(world, blockposition, iblockdata1, entityhuman);
                            world.setAir(blockposition.down());
                        } else {
                            world.setAir(blockposition.down(), true);
                        }
                    } else {
                        world.setAir(blockposition.down());
                    }
                } else {
                    world.setAir(blockposition.down());
                }
            }
        } else if (entityhuman.abilities.canInstantlyBuild && world.getType(blockposition.up()).getBlock() == this) {
            world.setTypeAndData(blockposition.up(), Blocks.AIR.getBlockData(), 2);
        }

        super.a(world, blockposition, iblockdata, entityhuman);
    }

    private boolean b(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
        EnumTallFlowerVariants enumtallflowervariants = (EnumTallFlowerVariants) iblockdata.get(BlockTallPlant.VARIANT);

        if (enumtallflowervariants != EnumTallFlowerVariants.FERN && enumtallflowervariants != EnumTallFlowerVariants.GRASS) {
            return false;
        } else {
            entityhuman.b(StatisticList.MINE_BLOCK_COUNT[Block.getId(this)]);
            int i = (enumtallflowervariants == EnumTallFlowerVariants.GRASS ? EnumTallGrassType.GRASS : EnumTallGrassType.FERN).a();

            a(world, blockposition, new ItemStack(Blocks.TALLGRASS, 2, i));
            return true;
        }
    }

    public int getDropData(World world, BlockPosition blockposition) {
        return this.e(world, blockposition).a();
    }

    public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
        EnumTallFlowerVariants enumtallflowervariants = this.e(world, blockposition);

        return enumtallflowervariants != EnumTallFlowerVariants.GRASS && enumtallflowervariants != EnumTallFlowerVariants.FERN;
    }

    public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        return true;
    }

    public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        a(world, blockposition, new ItemStack(this, 1, this.e(world, blockposition).a()));
    }

    public IBlockData fromLegacyData(int i) {
        return (i & 8) > 0 ? this.getBlockData().set(BlockTallPlant.HALF, EnumTallPlantHalf.UPPER) : this.getBlockData().set(BlockTallPlant.HALF, EnumTallPlantHalf.LOWER).set(BlockTallPlant.VARIANT, EnumTallFlowerVariants.a(i & 7));
    }

    public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        if (iblockdata.get(BlockTallPlant.HALF) == EnumTallPlantHalf.UPPER) {
            IBlockData iblockdata1 = iblockaccess.getType(blockposition.down());

            if (iblockdata1.getBlock() == this) {
                iblockdata = iblockdata.set(BlockTallPlant.VARIANT, iblockdata1.get(BlockTallPlant.VARIANT));
            }
        }

        return iblockdata;
    }

    public int toLegacyData(IBlockData iblockdata) {
        return iblockdata.get(BlockTallPlant.HALF) == EnumTallPlantHalf.UPPER ? 8 : ((EnumTallFlowerVariants) iblockdata.get(BlockTallPlant.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockTallPlant.HALF, BlockTallPlant.VARIANT});
    }
}
