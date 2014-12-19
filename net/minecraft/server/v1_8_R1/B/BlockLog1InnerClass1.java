package net.minecraft.server;

import com.google.common.base.Predicate;

final class BlockLog1InnerClass1 implements Predicate {

    BlockLog1InnerClass1() {}

    public boolean a(EnumLogVariant enumlogvariant) {
        return enumlogvariant.a() < 4;
    }

    public boolean apply(Object object) {
        return this.a((EnumLogVariant) object);
    }
}
