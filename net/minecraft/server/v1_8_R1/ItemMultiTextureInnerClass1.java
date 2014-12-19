package net.minecraft.server;

import com.google.common.base.Function;

class ItemMultiTextureInnerClass1 implements Function {

    final String[] a;

    ItemMultiTextureInnerClass1(String[] astring) {
        this.a = astring;
    }

    public String a(ItemStack itemstack) {
        int i = itemstack.getData();

        if (i < 0 || i >= this.a.length) {
            i = 0;
        }

        return this.a[i];
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
