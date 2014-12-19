package net.minecraft.server;

import java.util.Random;

class MerchantRecipeOptionEnchant implements IMerchantRecipeOption {

    public ItemStack a;
    public MerchantOptionRandomRange b;

    public MerchantRecipeOptionEnchant(Item item, MerchantOptionRandomRange merchantoptionrandomrange) {
        this.a = new ItemStack(item);
        this.b = merchantoptionrandomrange;
    }

    public void a(MerchantRecipeList merchantrecipelist, Random random) {
        int i = 1;

        if (this.b != null) {
            i = this.b.a(random);
        }

        ItemStack itemstack = new ItemStack(Items.EMERALD, i, 0);
        ItemStack itemstack1 = new ItemStack(this.a.getItem(), 1, this.a.getData());

        itemstack1 = EnchantmentManager.a(random, itemstack1, 5 + random.nextInt(15));
        merchantrecipelist.add(new MerchantRecipe(itemstack, itemstack1));
    }
}
