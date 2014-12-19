package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionMonsterEggVarient implements Function {

    DisplayStateFunctionMonsterEggVarient() {}

    public String a(ItemStack itemstack) {
        return EnumMonsterEggVarient.a(itemstack.getData()).c();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
