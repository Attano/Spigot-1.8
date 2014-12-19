package net.minecraft.server;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import java.util.Collection;
import java.util.Iterator;

public abstract class BlockDataAbstract implements IBlockData {

    private static final Joiner a = Joiner.on(',');
    private static final Function b = new BlockDataAbstractInnerClass1();

    public BlockDataAbstract() {}

    public IBlockData a(IBlockState iblockstate) {
        return this.set(iblockstate, (Comparable) a(iblockstate.c(), this.get(iblockstate)));
    }

    protected static Object a(Collection collection, Object object) {
        Iterator iterator = collection.iterator();

        do {
            if (!iterator.hasNext()) {
                return iterator.next();
            }
        } while (!iterator.next().equals(object));

        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return collection.iterator().next();
        }
    }

    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();

        stringbuilder.append(Block.REGISTRY.c(this.getBlock()));
        if (!this.b().isEmpty()) {
            stringbuilder.append("[");
            BlockDataAbstract.a.appendTo(stringbuilder, Iterables.transform(this.b().entrySet(), BlockDataAbstract.b));
            stringbuilder.append("]");
        }

        return stringbuilder.toString();
    }
}
