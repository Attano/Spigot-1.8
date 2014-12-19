package net.minecraft.server;

import com.google.common.base.Predicate;

final class BlockTorchInnerClass1 implements Predicate {

    BlockTorchInnerClass1() {}

    public boolean a(EnumDirection enumdirection) {
        return enumdirection != EnumDirection.DOWN;
    }

    public boolean apply(Object object) {
        return this.a((EnumDirection) object);
    }
}
