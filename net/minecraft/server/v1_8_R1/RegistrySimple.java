package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrySimple implements IRegistry {

    private static final Logger a = LogManager.getLogger();
    protected final Map c = this.b();

    public RegistrySimple() {}

    protected Map b() {
        return Maps.newHashMap();
    }

    public Object get(Object object) {
        return this.c.get(object);
    }

    public void a(Object object, Object object1) {
        Validate.notNull(object);
        Validate.notNull(object1);
        if (this.c.containsKey(object)) {
            RegistrySimple.a.debug("Adding duplicate key \'" + object + "\' to registry");
        }

        this.c.put(object, object1);
    }

    public Set keySet() {
        return Collections.unmodifiableSet(this.c.keySet());
    }

    public boolean d(Object object) {
        return this.c.containsKey(object);
    }

    public Iterator iterator() {
        return this.c.values().iterator();
    }
}
