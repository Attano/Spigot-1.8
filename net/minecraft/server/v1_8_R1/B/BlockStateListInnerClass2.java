package net.minecraft.server;

import java.util.Comparator;

class BlockStateListInnerClass2 implements Comparator {

    final BlockStateList a;

    BlockStateListInnerClass2(BlockStateList blockstatelist) {
        this.a = blockstatelist;
    }

    public int a(IBlockState iblockstate, IBlockState iblockstate1) {
        return iblockstate.a().compareTo(iblockstate1.a());
    }

    public int compare(Object object, Object object1) {
        return this.a((IBlockState) object, (IBlockState) object1);
    }
}
