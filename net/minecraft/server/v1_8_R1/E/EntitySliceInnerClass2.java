package net.minecraft.server;

import com.google.common.collect.AbstractIterator;
import java.util.Iterator;

class EntitySliceInnerClass2 extends AbstractIterator {

    final Iterator a;
    final EntitySlice b;

    EntitySliceInnerClass2(EntitySlice entityslice, Iterator iterator) {
        this.b = entityslice;
        this.a = iterator;
    }

    protected Object computeNext() {
        return !this.a.hasNext() ? this.endOfData() : this.a.next();
    }
}
