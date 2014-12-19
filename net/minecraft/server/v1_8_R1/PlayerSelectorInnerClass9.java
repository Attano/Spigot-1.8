package net.minecraft.server;

import com.google.common.base.Predicate;

final class PlayerSelectorInnerClass9 implements Predicate {

    final int a;
    final int b;

    PlayerSelectorInnerClass9(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public boolean a(Entity entity) {
        int i = PlayerSelector.a((int) Math.floor((double) entity.pitch));

        return this.a > this.b ? i >= this.a || i <= this.b : i >= this.a && i <= this.b;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
