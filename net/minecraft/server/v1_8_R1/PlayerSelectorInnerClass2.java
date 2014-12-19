package net.minecraft.server;

import com.google.common.base.Predicate;

final class PlayerSelectorInnerClass2 implements Predicate {

    final int a;
    final int b;

    PlayerSelectorInnerClass2(int i, int j) {
        this.a = i;
        this.b = j;
    }

    public boolean a(Entity entity) {
        if (!(entity instanceof EntityPlayer)) {
            return false;
        } else {
            EntityPlayer entityplayer = (EntityPlayer) entity;

            return (this.a <= -1 || entityplayer.expLevel >= this.a) && (this.b <= -1 || entityplayer.expLevel <= this.b);
        }
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
