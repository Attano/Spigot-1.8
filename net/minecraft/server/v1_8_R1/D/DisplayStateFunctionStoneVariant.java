package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionStoneVariant implements Function {

    DisplayStateFunctionStoneVariant() {}

    public String a(ItemStack itemstack) {
        return EnumStoneVariant.a(itemstack.getData()).c();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
