package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionRedSandstoneVariant implements Function {

    DisplayStateFunctionRedSandstoneVariant() {}

    public String a(ItemStack itemstack) {
        return EnumRedSandstoneVariant.a(itemstack.getData()).c();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
