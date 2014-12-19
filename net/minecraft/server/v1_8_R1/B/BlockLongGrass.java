package net.minecraft.server;

import java.util.Random;

public class BlockLongGrass extends BlockPlant implements IBlockFragilePlantElement {

    public static final BlockStateEnum TYPE = BlockStateEnum.of("type", EnumTallGrassType.class);

    protected BlockLongGrass() {
        super(Material.REPLACEABLE_PLANT);
        this.j(this.blockStateList.getBlockData().set(BlockLongGrass.TYPE, EnumTallGrassType.DEAD_BUSH));
        float f = 0.4F;

        this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    public boolean f(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return this.c(world.getType(blockposition.down()).getBlock());
    }

    public boolean f(World world, BlockPosition blockposition) {
        return true;
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return random.nextInt(8) == 0 ? Items.WHEAT_SEEDS : null;
    }

    public int getDropCount(int i, Random random) {
        return 1 + random.nextInt(i * 2 + 1);
    }

    public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, TileEntity tileentity) {
        if (!world.isStatic && entityhuman.bY() != null && entityhuman.bY().getItem() == Items.SHEARS) {
            entityhuman.b(StatisticList.MINE_BLOCK_COUNT[Block.getId(this)]);
            a(world, blockposition, new ItemStack(Blocks.TALLGRASS, 1, ((EnumTallGrassType) iblockdata.get(BlockLongGrass.TYPE)).a()));
        } else {
            super.a(world, entityhuman, blockposition, iblockdata, tileentity);
        }

    }

    public int getDropData(World world, BlockPosition blockposition) {
        IBlockData iblockdata = world.getType(blockposition);

        return iblockdata.getBlock().toLegacyData(iblockdata);
    }

    public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
        return iblockdata.get(BlockLongGrass.TYPE) != EnumTallGrassType.DEAD_BUSH;
    }

    public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        return true;
    }

    public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        EnumTallFlowerVariants enumtallflowervariants = EnumTallFlowerVariants.GRASS;

        if (iblockdata.get(BlockLongGrass.TYPE) == EnumTallGrassType.FERN) {
            enumtallflowervariants = EnumTallFlowerVariants.FERN;
        }

        if (Blocks.DOUBLE_PLANT.canPlace(world, blockposition)) {
            Blocks.DOUBLE_PLANT.a(world, blockposition, enumtallflowervariants, 2);
        }

    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockLongGrass.TYPE, EnumTallGrassType.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumTallGrassType) iblockdata.get(BlockLongGrass.TYPE)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockLongGrass.TYPE});
    }
}
