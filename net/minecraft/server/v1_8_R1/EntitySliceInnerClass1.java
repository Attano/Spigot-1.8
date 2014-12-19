package net.minecraft.server;

import com.google.common.collect.Iterators;
import java.util.Iterator;

class EntitySliceInnerClass1 implements Iterable {

    final Class a;
    final EntitySlice b;

    EntitySliceInnerClass1(EntitySlice entityslice, Class oclass) {
        this.b = entityslice;
        this.a = oclass;
    }

    public Iterator iterator() {
        Iterator iterator = EntitySlice.a(this.b).get(this.b.a(this.a, true)).iterator();

        return Iterators.filter(iterator, this.a);
    }
}
