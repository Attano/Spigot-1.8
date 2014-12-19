package net.minecraft.server;

import com.google.common.base.Predicate;

final class ShapeDetectorBlockInnerClass1 implements Predicate {

    final Predicate a;

    ShapeDetectorBlockInnerClass1(Predicate predicate) {
        this.a = predicate;
    }

    public boolean a(ShapeDetectorBlock shapedetectorblock) {
        return shapedetectorblock != null && this.a.apply(shapedetectorblock.a());
    }

    public boolean apply(Object object) {
        return this.a((ShapeDetectorBlock) object);
    }
}
