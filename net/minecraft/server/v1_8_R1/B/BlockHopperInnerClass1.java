package net.minecraft.server;

import com.google.common.base.Predicate;

final class BlockHopperInnerClass1 implements Predicate {

    BlockHopperInnerClass1() {}

    public boolean a(EnumDirection enumdirection) {
        return enumdirection != EnumDirection.UP;
    }

    public boolean apply(Object object) {
        return this.a((EnumDirection) object);
    }
}
