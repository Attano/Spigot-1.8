package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionLogVariant3 implements Function {

    DisplayStateFunctionLogVariant3() {}

    public String a(ItemStack itemstack) {
        return EnumLogVariant.a(itemstack.getData()).c();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
