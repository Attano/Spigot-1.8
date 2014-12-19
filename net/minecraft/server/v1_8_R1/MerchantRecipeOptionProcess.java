package net.minecraft.server;

import java.util.Random;

class MerchantRecipeOptionProcess implements IMerchantRecipeOption {

    public ItemStack a;
    public MerchantOptionRandomRange b;
    public ItemStack c;
    public MerchantOptionRandomRange d;

    public MerchantRecipeOptionProcess(Item item, MerchantOptionRandomRange merchantoptionrandomrange, Item item1, MerchantOptionRandomRange merchantoptionrandomrange1) {
        this.a = new ItemStack(item);
        this.b = merchantoptionrandomrange;
        this.c = new ItemStack(item1);
        this.d = merchantoptionrandomrange1;
    }

    public void a(MerchantRecipeList merchantrecipelist, Random random) {
        int i = 1;

        if (this.b != null) {
            i = this.b.a(random);
        }

        int j = 1;

        if (this.d != null) {
            j = this.d.a(random);
        }

        merchantrecipelist.add(new MerchantRecipe(new ItemStack(this.a.getItem(), i, this.a.getData()), new ItemStack(Items.EMERALD), new ItemStack(this.c.getItem(), j, this.c.getData())));
    }
}
