package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionRedFlower implements Function {

    DisplayStateFunctionRedFlower() {}

    public String a(ItemStack itemstack) {
        return EnumFlowerVarient.a(EnumFlowerType.RED, itemstack.getData()).d();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
