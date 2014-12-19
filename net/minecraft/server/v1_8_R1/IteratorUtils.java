package net.minecraft.server;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class IteratorUtils {

    public static Iterable a(Class oclass, Iterable iterable) {
        return new IteratorUtilsInnerClassClassIterable(oclass, (Iterable[]) b(Iterable.class, iterable), (EmptyClass3) null);
    }

    public static Iterable a(Iterable iterable) {
        return b(a(Object.class, iterable));
    }

    private static Iterable b(Iterable iterable) {
        return Iterables.transform(iterable, new IteratorUtilsInnerClassArrayToList((EmptyClass3) null));
    }

    private static Object[] b(Class oclass, Iterable iterable) {
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            Object object = iterator.next();

            arraylist.add(object);
        }

        return (Object[]) arraylist.toArray(b(oclass, arraylist.size()));
    }

    private static Object[] b(Class oclass, int i) {
        return (Object[]) ((Object[]) Array.newInstance(oclass, i));
    }

    static Object[] a(Class oclass, int i) {
        return b(oclass, i);
    }
}
