package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionSponge implements Function {

    DisplayStateFunctionSponge() {}

    public String a(ItemStack itemstack) {
        return (itemstack.getData() & 1) == 1 ? "wet" : "dry";
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
