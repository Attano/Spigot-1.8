package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionYellowFlower implements Function {

    DisplayStateFunctionYellowFlower() {}

    public String a(ItemStack itemstack) {
        return EnumFlowerVarient.a(EnumFlowerType.YELLOW, itemstack.getData()).d();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
