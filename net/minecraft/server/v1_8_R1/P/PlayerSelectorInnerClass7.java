package net.minecraft.server;

import com.google.common.base.Predicate;

final class PlayerSelectorInnerClass7 implements Predicate {

    final BlockPosition a;
    final int b;
    final int c;
    final int d;
    final int e;

    PlayerSelectorInnerClass7(BlockPosition blockposition, int i, int j, int k, int l) {
        this.a = blockposition;
        this.b = i;
        this.c = j;
        this.d = k;
        this.e = l;
    }

    public boolean a(Entity entity) {
        int i = (int) entity.c(this.a);

        return (this.b < 0 || i >= this.c) && (this.d < 0 || i <= this.e);
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
