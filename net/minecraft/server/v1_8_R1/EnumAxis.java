package net.minecraft.server;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Map;

public enum EnumAxis implements Predicate, INamable {

    X("x", EnumDirectionLimit.HORIZONTAL), Y("y", EnumDirectionLimit.VERTICAL), Z("z", EnumDirectionLimit.HORIZONTAL);

    private static final Map d = Maps.newHashMap();
    private final String e;
    private final EnumDirectionLimit f;

    private EnumAxis(String s, EnumDirectionLimit enumdirectionlimit) {
        this.e = s;
        this.f = enumdirectionlimit;
    }

    public String a() {
        return this.e;
    }

    public boolean b() {
        return this.f == EnumDirectionLimit.VERTICAL;
    }

    public boolean c() {
        return this.f == EnumDirectionLimit.HORIZONTAL;
    }

    public String toString() {
        return this.e;
    }

    public boolean a(EnumDirection enumdirection) {
        return enumdirection != null && enumdirection.k() == this;
    }

    public EnumDirectionLimit d() {
        return this.f;
    }

    public String getName() {
        return this.e;
    }

    public boolean apply(Object object) {
        return this.a((EnumDirection) object);
    }

    static {
        EnumAxis[] aenumaxis = values();
        int i = aenumaxis.length;

        for (int j = 0; j < i; ++j) {
            EnumAxis enumaxis = aenumaxis[j];

            EnumAxis.d.put(enumaxis.a().toLowerCase(), enumaxis);
        }

    }
}
