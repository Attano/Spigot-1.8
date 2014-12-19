package net.minecraft.server;

import org.apache.commons.lang3.Validate;

public class RegistryBlocks extends RegistryMaterials {

    private final Object d;
    private Object e;

    public RegistryBlocks(Object object) {
        this.d = object;
    }

    public void a(int i, Object object, Object object1) {
        if (this.d.equals(object)) {
            this.e = object1;
        }

        super.a(i, object, object1);
    }

    public void a() {
        Validate.notNull(this.d);
    }

    public Object get(Object object) {
        Object object1 = super.get(object);

        return object1 == null ? this.e : object1;
    }

    public Object a(int i) {
        Object object = super.a(i);

        return object == null ? this.e : object;
    }
}
