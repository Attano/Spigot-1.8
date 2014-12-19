package net.minecraft.server;

import com.google.common.base.Predicate;

public class BlockLog2 extends BlockLogAbstract {

    public static final BlockStateEnum VARIANT = BlockStateEnum.a("variant", EnumLogVariant.class, (Predicate) (new BlockLog2InnerClass1()));

    public BlockLog2() {
        this.j(this.blockStateList.getBlockData().set(BlockLog2.VARIANT, EnumLogVariant.ACACIA).set(BlockLog2.AXIS, EnumLogRotation.Y));
    }

    public IBlockData fromLegacyData(int i) {
        IBlockData iblockdata = this.getBlockData().set(BlockLog2.VARIANT, EnumLogVariant.a((i & 3) + 4));

        switch (i & 12) {
        case 0:
            iblockdata = iblockdata.set(BlockLog2.AXIS, EnumLogRotation.Y);
            break;

        case 4:
            iblockdata = iblockdata.set(BlockLog2.AXIS, EnumLogRotation.X);
            break;

        case 8:
            iblockdata = iblockdata.set(BlockLog2.AXIS, EnumLogRotation.Z);
            break;

        default:
            iblockdata = iblockdata.set(BlockLog2.AXIS, EnumLogRotation.NONE);
        }

        return iblockdata;
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((EnumLogVariant) iblockdata.get(BlockLog2.VARIANT)).a() - 4;

        switch (SwitchHelperRotation2.a[((EnumLogRotation) iblockdata.get(BlockLog2.AXIS)).ordinal()]) {
        case 1:
            i |= 4;
            break;

        case 2:
            i |= 8;
            break;

        case 3:
            i |= 12;
        }

        return i;
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockLog2.VARIANT, BlockLog2.AXIS});
    }

    protected ItemStack i(IBlockData iblockdata) {
        return new ItemStack(Item.getItemOf(this), 1, ((EnumLogVariant) iblockdata.get(BlockLog2.VARIANT)).a() - 4);
    }

    public int getDropData(IBlockData iblockdata) {
        return ((EnumLogVariant) iblockdata.get(BlockLog2.VARIANT)).a() - 4;
    }
}
