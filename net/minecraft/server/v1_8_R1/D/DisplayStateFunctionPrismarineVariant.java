package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionPrismarineVariant implements Function {

    DisplayStateFunctionPrismarineVariant() {}

    public String a(ItemStack itemstack) {
        return EnumPrismarineVariant.a(itemstack.getData()).c();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
