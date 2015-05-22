package net.minecraft.server;

import org.apache.commons.lang3.Validate;

public class RegistryBlocks<K, V> extends RegistryMaterials<K, V> {

    private final K d;
    private V e;

    public RegistryBlocks(K k0) {
        this.d = k0;
    }

    public void a(int i, K k0, V v0) {
        if (this.d.equals(k0)) {
            this.e = v0;
        }

        super.a(i, k0, v0);
    }

    public void a() {
        Validate.notNull(this.d);
    }

    public V get(K k0) {
        Object object = super.get(k0);

        return object == null ? this.e : object;
    }

    public V a(int i) {
        Object object = super.a(i);

        return object == null ? this.e : object;
    }
}
