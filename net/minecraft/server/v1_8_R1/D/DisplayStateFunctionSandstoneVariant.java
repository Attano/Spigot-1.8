package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionSandstoneVariant implements Function {

    DisplayStateFunctionSandstoneVariant() {}

    public String a(ItemStack itemstack) {
        return EnumSandstoneVariant.a(itemstack.getData()).c();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
