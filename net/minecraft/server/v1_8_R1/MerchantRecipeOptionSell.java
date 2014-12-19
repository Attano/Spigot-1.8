package net.minecraft.server;

import java.util.Random;

class MerchantRecipeOptionSell implements IMerchantRecipeOption {

    public ItemStack a;
    public MerchantOptionRandomRange b;

    public MerchantRecipeOptionSell(Item item, MerchantOptionRandomRange merchantoptionrandomrange) {
        this.a = new ItemStack(item);
        this.b = merchantoptionrandomrange;
    }

    public MerchantRecipeOptionSell(ItemStack itemstack, MerchantOptionRandomRange merchantoptionrandomrange) {
        this.a = itemstack;
        this.b = merchantoptionrandomrange;
    }

    public void a(MerchantRecipeList merchantrecipelist, Random random) {
        int i = 1;

        if (this.b != null) {
            i = this.b.a(random);
        }

        ItemStack itemstack;
        ItemStack itemstack1;

        if (i < 0) {
            itemstack = new ItemStack(Items.EMERALD, 1, 0);
            itemstack1 = new ItemStack(this.a.getItem(), -i, this.a.getData());
        } else {
            itemstack = new ItemStack(Items.EMERALD, i, 0);
            itemstack1 = new ItemStack(this.a.getItem(), 1, this.a.getData());
        }

        merchantrecipelist.add(new MerchantRecipe(itemstack, itemstack1));
    }
}
