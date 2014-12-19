package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionTallFlowerVariants implements Function {

    DisplayStateFunctionTallFlowerVariants() {}

    public String a(ItemStack itemstack) {
        return EnumTallFlowerVariants.a(itemstack.getData()).c();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
