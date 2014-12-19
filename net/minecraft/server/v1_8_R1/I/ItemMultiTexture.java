package net.minecraft.server;

import com.google.common.base.Function;

public class ItemMultiTexture extends ItemBlock {

    protected final Block b;
    protected final Function c;

    public ItemMultiTexture(Block block, Block block1, Function function) {
        super(block);
        this.b = block1;
        this.c = function;
        this.setMaxDurability(0);
        this.a(true);
    }

    public ItemMultiTexture(Block block, Block block1, String[] astring) {
        this(block, block1, (Function) (new ItemMultiTextureInnerClass1(astring)));
    }

    public int filterData(int i) {
        return i;
    }

    public String e_(ItemStack itemstack) {
        return super.getName() + "." + (String) this.c.apply(itemstack);
    }
}
