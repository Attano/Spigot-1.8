package net.minecraft.server;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Iterator;
import java.util.Map;

public class RegistryMaterials extends RegistrySimple implements Registry {

    protected final RegistryID a = new RegistryID();
    protected final Map b;

    public RegistryMaterials() {
        this.b = ((BiMap) this.c).inverse();
    }

    public void a(int i, Object object, Object object1) {
        this.a.a(object1, i);
        this.a(object, object1);
    }

    protected Map b() {
        return HashBiMap.create();
    }

    public Object get(Object object) {
        return super.get(object);
    }

    public Object c(Object object) {
        return this.b.get(object);
    }

    public boolean d(Object object) {
        return super.d(object);
    }

    public int b(Object object) {
        return this.a.b(object);
    }

    public Object a(int i) {
        return this.a.a(i);
    }

    public Iterator iterator() {
        return this.a.iterator();
    }
}
