package net.minecraft.server;

import com.google.common.base.Predicate;

final class EnumFlowerTypeInnerClassSelector implements Predicate {

    final EnumFlowerType a;

    EnumFlowerTypeInnerClassSelector(EnumFlowerType enumflowertype) {
        this.a = enumflowertype;
    }

    public boolean a(EnumFlowerVarient enumflowervarient) {
        return enumflowervarient.a() == this.a;
    }

    public boolean apply(Object object) {
        return this.a((EnumFlowerVarient) object);
    }
}
