package net.minecraft.server;

public class RecipesBanner {

    public RecipesBanner() {}

    void a(CraftingManager craftingmanager) {
        EnumColor[] aenumcolor = EnumColor.values();
        int i = aenumcolor.length;

        for (int j = 0; j < i; ++j) {
            EnumColor enumcolor = aenumcolor[j];

            craftingmanager.registerShapedRecipe(new ItemStack(Items.BANNER, 1, enumcolor.getInvColorIndex()), new Object[] { "###", "###", " | ", Character.valueOf('#'), new ItemStack(Blocks.WOOL, 1, enumcolor.getColorIndex()), Character.valueOf('|'), Items.STICK});
        }

        craftingmanager.a(new RecipesBannerInnerClass1((EmptyClass2) null));
        craftingmanager.a(new RecipesBannerInnerClass2((EmptyClass2) null));
    }
}
