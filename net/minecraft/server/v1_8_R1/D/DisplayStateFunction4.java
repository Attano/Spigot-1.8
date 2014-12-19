package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunction4 implements Function {

    DisplayStateFunction4() {}

    public String a(ItemStack itemstack) {
        return EnumLogVariant.a(itemstack.getData() + 4).c();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
