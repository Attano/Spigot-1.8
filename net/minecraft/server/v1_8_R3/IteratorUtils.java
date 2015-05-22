package net.minecraft.server;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class IteratorUtils {

    public static <T> Iterable<T[]> a(Class<T> oclass, Iterable<? extends Iterable<? extends T>> iterable) {
        return new IteratorUtils.ClassIterable(oclass, (Iterable[]) b(Iterable.class, iterable), (IteratorUtils.SyntheticClass_1) null);
    }

    public static <T> Iterable<List<T>> a(Iterable<? extends Iterable<? extends T>> iterable) {
        return b(a(Object.class, iterable));
    }

    private static <T> Iterable<List<T>> b(Iterable<Object[]> iterable) {
        return Iterables.transform(iterable, new IteratorUtils.ArrayToList((IteratorUtils.SyntheticClass_1) null));
    }

    private static <T> T[] b(Class<? super T> oclass, Iterable<? extends T> iterable) {
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            Object object = iterator.next();

            arraylist.add(object);
        }

        return (Object[]) arraylist.toArray(b(oclass, arraylist.size()));
    }

    private static <T> T[] b(Class<? super T> oclass, int i) {
        return (Object[]) ((Object[]) Array.newInstance(oclass, i));
    }

    static class SyntheticClass_1 {    }

    static class ClassIterable<T> implements Iterable<T[]> {

        private final Class<T> a;
        private final Iterable<? extends T>[] b;

        private ClassIterable(Class<T> oclass, Iterable<? extends T>[] aiterable) {
            this.a = oclass;
            this.b = aiterable;
        }

        public Iterator<T[]> iterator() {
            return (Iterator) (this.b.length <= 0 ? Collections.singletonList((Object[]) IteratorUtils.b(this.a, 0)).iterator() : new IteratorUtils.ClassIterable.ClassIterable$ClassIterator(this.a, this.b, (IteratorUtils.SyntheticClass_1) null));
        }

        ClassIterable(Class oclass, Iterable[] aiterable, IteratorUtils.SyntheticClass_1 iteratorutils_syntheticclass_1) {
            this(oclass, aiterable);
        }

        static class ClassIterable$ClassIterator<T> extends UnmodifiableIterator<T[]> {

            private int a;
            private final Iterable<? extends T>[] b;
            private final Iterator<? extends T>[] c;
            private final T[] d;

            private ClassIterable$ClassIterator(Class<T> oclass, Iterable<? extends T>[] aiterable) {
                this.a = -2;
                this.b = aiterable;
                this.c = (Iterator[]) IteratorUtils.b(Iterator.class, this.b.length);

                for (int i = 0; i < this.b.length; ++i) {
                    this.c[i] = aiterable[i].iterator();
                }

                this.d = IteratorUtils.b(oclass, this.c.length);
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

            public T[] a() {
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

            ClassIterable$ClassIterator(Class oclass, Iterable[] aiterable, IteratorUtils.SyntheticClass_1 iteratorutils_syntheticclass_1) {
                this(oclass, aiterable);
            }
        }
    }

    static class ArrayToList<T> implements Function<Object[], List<T>> {

        private ArrayToList() {}

        public List<T> a(Object[] aobject) {
            return Arrays.asList((Object[]) aobject);
        }

        public Object apply(Object object) {
            return this.a((Object[]) object);
        }

        ArrayToList(IteratorUtils.SyntheticClass_1 iteratorutils_syntheticclass_1) {
            this();
        }
    }
}
