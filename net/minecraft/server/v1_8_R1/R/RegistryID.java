package net.minecraft.server;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

public class RegistryID implements Registry {

    private final IdentityHashMap a = new IdentityHashMap(512);
    private final List b = Lists.newArrayList();

    public RegistryID() {}

    public void a(Object object, int i) {
        this.a.put(object, Integer.valueOf(i));

        while (this.b.size() <= i) {
            this.b.add((Object) null);
        }

        this.b.set(i, object);
    }

    public int b(Object object) {
        Integer integer = (Integer) this.a.get(object);

        return integer == null ? -1 : integer.intValue();
    }

    public final Object a(int i) {
        return i >= 0 && i < this.b.size() ? this.b.get(i) : null;
    }

    public Iterator iterator() {
        return Iterators.filter(this.b.iterator(), Predicates.notNull());
    }
}
