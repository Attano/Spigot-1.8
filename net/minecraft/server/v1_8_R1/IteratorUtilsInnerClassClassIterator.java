package net.minecraft.server;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

class IteratorUtilsInnerClassClassIterator extends UnmodifiableIterator {

    private int a;
    private final Iterable[] b;
    private final Iterator[] c;
    private final Object[] d;

    private IteratorUtilsInnerClassClassIterator(Class oclass, Iterable[] aiterable) {
        this.a = -2;
        this.b = aiterable;
        this.c = (Iterator[]) IteratorUtils.a(Iterator.class, this.b.length);

        for (int i = 0; i < this.b.length; ++i) {
            this.c[i] = aiterable[i].iterator();
        }

        this.d = IteratorUtils.a(oclass, this.c.length);
    }

    private void b() {
        this.a = -1;
        Arrays.fill(this.c, (Object) null);
        Arrays.fill(this.d, (Object) null);
    }

    public boolean hasNext() {
        if (this.a == -2) {
            this.a = 0;
            Iterator[] aiterator = this.c;
            int i = aiterator.length;

            for (int j = 0; j < i; ++j) {
                Iterator iterator = aiterator[j];

                if (!iterator.hasNext()) {
                    this.b();
                    break;
                }
            }

            return true;
        } else {
            if (this.a >= this.c.length) {
                for (this.a = this.c.length - 1; this.a >= 0; --this.a) {
                    Iterator iterator1 = this.c[this.a];

                    if (iterator1.hasNext()) {
                        break;
                    }

                    if (this.a == 0) {
                        this.b();
                        break;
                    }

                    iterator1 = this.b[this.a].iterator();
                    this.c[this.a] = iterator1;
                    if (!iterator1.hasNext()) {
                        this.b();
                        break;
                    }
                }
            }

            return this.a >= 0;
        }
    }

    public Object[] a() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        } else {
            while (this.a < this.c.length) {
                this.d[this.a] = this.c[this.a].next();
                ++this.a;
            }

            return (Object[]) this.d.clone();
        }
    }

    public Object next() {
        return this.a();
    }

    IteratorUtilsInnerClassClassIterator(Class oclass, Iterable[] aiterable, EmptyClass3 emptyclass3) {
        this(oclass, aiterable);
    }
}
