package net.minecraft.server;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ClassUtils.Interfaces;

public class EntitySlice extends AbstractSet {

    private final Multimap a = HashMultimap.create();
    private final Set b = Sets.newIdentityHashSet();
    private final Class c;

    public EntitySlice(Class oclass) {
        this.c = oclass;
        this.b.add(oclass);
    }

    public void a(Class oclass) {
        Iterator iterator = this.a.get(this.a(oclass, false)).iterator();

        while (iterator.hasNext()) {
            Object object = iterator.next();

            if (oclass.isAssignableFrom(object.getClass())) {
                this.a.put(oclass, object);
            }
        }

        this.b.add(oclass);
    }

    protected Class a(Class oclass, boolean flag) {
        Iterator iterator = ClassUtils.hierarchy(oclass, Interfaces.INCLUDE).iterator();

        Class oclass1;

        do {
            if (!iterator.hasNext()) {
                throw new IllegalArgumentException("Don\'t know how to search for " + oclass);
            }

            oclass1 = (Class) iterator.next();
        } while (!this.b.contains(oclass1));

        if (oclass1 == this.c && flag) {
            this.a(oclass);
        }

        return oclass1;
    }

    public boolean add(Object object) {
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            Class oclass = (Class) iterator.next();

            if (oclass.isAssignableFrom(object.getClass())) {
                this.a.put(oclass, object);
            }
        }

        return true;
    }

    public boolean remove(Object object) {
        Object object1 = object;
        boolean flag = false;
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            Class oclass = (Class) iterator.next();

            if (oclass.isAssignableFrom(object1.getClass())) {
                flag |= this.a.remove(oclass, object1);
            }
        }

        return flag;
    }

    public Iterable b(Class oclass) {
        return new EntitySliceInnerClass1(this, oclass);
    }

    public Iterator iterator() {
        Iterator iterator = this.a.get(this.c).iterator();

        return new EntitySliceInnerClass2(this, iterator);
    }

    public int size() {
        return this.a.get(this.c).size();
    }

    static Multimap a(EntitySlice entityslice) {
        return entityslice.a;
    }
}
