package net.minecraft.server;

import com.google.common.base.Function;

final class BlockStateListInnerClass1 implements Function {

    BlockStateListInnerClass1() {}

    public String a(IBlockState iblockstate) {
        return iblockstate == null ? "<NULL>" : iblockstate.a();
    }

    public Object apply(Object object) {
        return this.a((IBlockState) object);
    }
}
