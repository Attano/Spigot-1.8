package net.minecraft.server;

import com.google.common.base.Predicate;

class BlockFlowersInnerClass1 implements Predicate {

    final BlockFlowers a;

    BlockFlowersInnerClass1(BlockFlowers blockflowers) {
        this.a = blockflowers;
    }

    public boolean a(EnumFlowerVarient enumflowervarient) {
        return enumflowervarient.a() == this.a.j();
    }

    public boolean apply(Object object) {
        return this.a((EnumFlowerVarient) object);
    }
}
