package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionDirtVariant implements Function {

    DisplayStateFunctionDirtVariant() {}

    public String a(ItemStack itemstack) {
        return EnumDirtVariant.a(itemstack.getData()).c();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
