package net.minecraft.server;

public class RegistryDefault<K, V> extends RegistrySimple<K, V> {

    private final V a;

    public RegistryDefault(V v0) {
        this.a = v0;
    }

    public V get(K k0) {
        Object object = super.get(k0);

        return object == null ? this.a : object;
    }
}
