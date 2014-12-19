package net.minecraft.server;

import com.google.common.base.Function;
import java.util.Map.Entry;

final class BlockDataAbstractInnerClass1 implements Function {

    BlockDataAbstractInnerClass1() {}

    public String a(Entry entry) {
        if (entry == null) {
            return "<NULL>";
        } else {
            IBlockState iblockstate = (IBlockState) entry.getKey();

            return iblockstate.a() + "=" + iblockstate.a((Comparable) entry.getValue());
        }
    }

    public Object apply(Object object) {
        return this.a((Entry) object);
    }
}
