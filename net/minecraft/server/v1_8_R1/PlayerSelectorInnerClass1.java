package net.minecraft.server;

import com.google.common.base.Predicate;

final class PlayerSelectorInnerClass1 implements Predicate {

    final String a;
    final boolean b;

    PlayerSelectorInnerClass1(String s, boolean flag) {
        this.a = s;
        this.b = flag;
    }

    public boolean a(Entity entity) {
        return EntityTypes.a(entity, this.a) != this.b;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
