package net.minecraft.server;

import java.util.Random;

public class BlockHugeMushroom extends Block {

    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumHugeMushroomVariant.class);
    private final Block b;

    public BlockHugeMushroom(Material material, Block block) {
        super(material);
        this.j(this.blockStateList.getBlockData().set(BlockHugeMushroom.VARIANT, EnumHugeMushroomVariant.ALL_OUTSIDE));
        this.b = block;
    }

    public int a(Random random) {
        return Math.max(0, random.nextInt(10) - 7);
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Item.getItemOf(this.b);
    }

    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
        return this.getBlockData();
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockHugeMushroom.VARIANT, EnumHugeMushroomVariant.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumHugeMushroomVariant) iblockdata.get(BlockHugeMushroom.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockHugeMushroom.VARIANT});
    }
}
