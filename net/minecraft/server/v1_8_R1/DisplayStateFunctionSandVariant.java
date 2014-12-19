package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionSandVariant implements Function {

    DisplayStateFunctionSandVariant() {}

    public String a(ItemStack itemstack) {
        return EnumSandVariant.a(itemstack.getData()).d();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
