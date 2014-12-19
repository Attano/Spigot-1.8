package net.minecraft.server;

import java.util.Random;

class MerchantRecipeOptionBuy implements IMerchantRecipeOption {

    public Item a;
    public MerchantOptionRandomRange b;

    public MerchantRecipeOptionBuy(Item item, MerchantOptionRandomRange merchantoptionrandomrange) {
        this.a = item;
        this.b = merchantoptionrandomrange;
    }

    public void a(MerchantRecipeList merchantrecipelist, Random random) {
        int i = 1;

        if (this.b != null) {
            i = this.b.a(random);
        }

        merchantrecipelist.add(new MerchantRecipe(new ItemStack(this.a, i, 0), Items.EMERALD));
    }
}
