package net.minecraft.server;

import java.util.Random;

class MerchantRecipeOptionBook implements IMerchantRecipeOption {

    public MerchantRecipeOptionBook() {}

    public void a(MerchantRecipeList merchantrecipelist, Random random) {
        Enchantment enchantment = Enchantment.b[random.nextInt(Enchantment.b.length)];
        int i = MathHelper.nextInt(random, enchantment.getStartLevel(), enchantment.getMaxLevel());
        ItemStack itemstack = Items.ENCHANTED_BOOK.a(new WeightedRandomEnchant(enchantment, i));
        int j = 2 + random.nextInt(5 + i * 10) + 3 * i;

        if (j > 64) {
            j = 64;
        }

        merchantrecipelist.add(new MerchantRecipe(new ItemStack(Items.BOOK), new ItemStack(Items.EMERALD, j), itemstack));
    }
}
