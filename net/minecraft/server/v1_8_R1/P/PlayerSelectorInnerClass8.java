package net.minecraft.server;

import com.google.common.base.Predicate;

final class PlayerSelectorInnerClass8 implements Predicate {

    final int a;
    final int b;

    PlayerSelectorInnerClass8(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public boolean a(Entity entity) {
        int i = PlayerSelector.a((int) Math.floor((double) entity.yaw));

        return this.a > this.b ? i >= this.a || i <= this.b : i >= this.a && i <= this.b;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
