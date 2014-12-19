package net.minecraft.server;

import com.google.common.base.Predicate;

final class BlockStemInnerClass1 implements Predicate {

    BlockStemInnerClass1() {}

    public boolean a(EnumDirection enumdirection) {
        return enumdirection != EnumDirection.DOWN;
    }

    public boolean apply(Object object) {
        return this.a((EnumDirection) object);
    }
}
