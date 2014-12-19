package net.minecraft.server;

import com.google.common.base.Predicate;

class BlockRedstoneComparatorInnerClass1 implements Predicate {

    final EnumDirection a;
    final BlockRedstoneComparator b;

    BlockRedstoneComparatorInnerClass1(BlockRedstoneComparator blockredstonecomparator, EnumDirection enumdirection) {
        this.b = blockredstonecomparator;
        this.a = enumdirection;
    }

    public boolean a(Entity entity) {
        return entity != null && entity.getDirection() == this.a;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
