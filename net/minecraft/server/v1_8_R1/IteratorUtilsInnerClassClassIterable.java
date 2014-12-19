package net.minecraft.server;

import java.util.Collections;
import java.util.Iterator;

class IteratorUtilsInnerClassClassIterable implements Iterable {

    private final Class a;
    private final Iterable[] b;

    private IteratorUtilsInnerClassClassIterable(Class oclass, Iterable[] aiterable) {
        this.a = oclass;
        this.b = aiterable;
    }

    public Iterator iterator() {
        return (Iterator) (this.b.length <= 0 ? Collections.singletonList((Object[]) IteratorUtils.a(this.a, 0)).iterator() : new IteratorUtilsInnerClassClassIterator(this.a, this.b, (EmptyClass3) null));
    }

    IteratorUtilsInnerClassClassIterable(Class oclass, Iterable[] aiterable, EmptyClass3 emptyclass3) {
        this(oclass, aiterable);
    }
}
