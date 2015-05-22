package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class InsensitiveStringMap<V> implements Map<String, V> {

    private final Map<String, V> a = Maps.newLinkedHashMap();

    public InsensitiveStringMap() {}

    public int size() {
        return this.a.size();
    }

    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    public boolean containsKey(Object object) {
        return this.a.containsKey(object.toString().toLowerCase());
    }

    public boolean containsValue(Object object) {
        return this.a.containsKey(object);
    }

    public V get(Object object) {
        return this.a.get(object.toString().toLowerCase());
    }

    public V a(String s, V v0) {
        return this.a.put(s.toLowerCase(), v0);
    }

    public V remove(Object object) {
        return this.a.remove(object.toString().toLowerCase());
    }

    public void putAll(Map<? extends String, ? extends V> map) {
        Iterator iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();

            this.a((String) entry.getKey(), entry.getValue());
        }

    }

    public void clear() {
        this.a.clear();
    }

    public Set<String> keySet() {
        return this.a.keySet();
    }

    public Collection<V> values() {
        return this.a.values();
    }

    public Set<Entry<String, V>> entrySet() {
        return this.a.entrySet();
    }

    public Object put(Object object, Object object1) {
        return this.a((String) object, object1);
    }
}
