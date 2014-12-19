package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionStonebrickType implements Function {

    DisplayStateFunctionStonebrickType() {}

    public String a(ItemStack itemstack) {
        return EnumStonebrickType.a(itemstack.getData()).c();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
