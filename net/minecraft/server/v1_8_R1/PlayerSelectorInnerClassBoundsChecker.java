package net.minecraft.server;

import com.google.common.base.Predicate;

final class PlayerSelectorInnerClassBoundsChecker implements Predicate {

    final AxisAlignedBB a;

    PlayerSelectorInnerClassBoundsChecker(AxisAlignedBB axisalignedbb) {
        this.a = axisalignedbb;
    }

    public boolean a(Entity entity) {
        return entity.locX >= this.a.a && entity.locY >= this.a.b && entity.locZ >= this.a.c ? entity.locX < this.a.d && entity.locY < this.a.e && entity.locZ < this.a.f : false;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
