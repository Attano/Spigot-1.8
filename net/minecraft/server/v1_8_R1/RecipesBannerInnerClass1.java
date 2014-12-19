package net.minecraft.server;

class RecipesBannerInnerClass1 extends ShapelessRecipes implements IRecipe { // CraftBukkit - added extends

    // CraftBukkit start - Delegate to new parent class with bogus info
    private RecipesBannerInnerClass1() {
        super(new ItemStack(Items.BANNER, 0, 0), java.util.Arrays.asList(new ItemStack(Items.BANNER)));
    }
    // CraftBukkit end

    public boolean a(InventoryCrafting inventorycrafting, World world) {
        ItemStack itemstack = null;
        ItemStack itemstack1 = null;

        for (int i = 0; i < inventorycrafting.getSize(); ++i) {
            ItemStack itemstack2 = inventorycrafting.getItem(i);

            if (itemstack2 != null) {
                if (itemstack2.getItem() != Items.BANNER) {
                    return false;
                }

                if (itemstack != null && itemstack1 != null) {
                    return false;
                }

                int j = TileEntityBanner.b(itemstack2);
                boolean flag = TileEntityBanner.c(itemstack2) > 0;

                if (itemstack != null) {
                    if (flag) {
                        return false;
                    }

                    if (j != TileEntityBanner.b(itemstack)) {
                        return false;
                    }

                    itemstack1 = itemstack2;
                } else if (itemstack1 != null) {
                    if (!flag) {
                        return false;
                    }

                    if (j != TileEntityBanner.b(itemstack1)) {
                        return false;
                    }

                    itemstack = itemstack2;
                } else if (flag) {
                    itemstack = itemstack2;
                } else {
                    itemstack1 = itemstack2;
                }
            }
        }

        return itemstack != null && itemstack1 != null;
    }

    public ItemStack a(InventoryCrafting inventorycrafting) {
        for (int i = 0; i < inventorycrafting.getSize(); ++i) {
            ItemStack itemstack = inventorycrafting.getItem(i);

            if (itemstack != null && TileEntityBanner.c(itemstack) > 0) {
                ItemStack itemstack1 = itemstack.cloneItemStack();

                itemstack1.count = 1;
                return itemstack1;
            }
        }

        return null;
    }

    public int a() {
        return 2;
    }

    public ItemStack b() {
        return null;
    }

    public ItemStack[] b(InventoryCrafting inventorycrafting) {
        ItemStack[] aitemstack = new ItemStack[inventorycrafting.getSize()];

        for (int i = 0; i < aitemstack.length; ++i) {
            ItemStack itemstack = inventorycrafting.getItem(i);

            if (itemstack != null) {
                if (itemstack.getItem().r()) {
                    aitemstack[i] = new ItemStack(itemstack.getItem().q());
                } else if (itemstack.hasTag() && TileEntityBanner.c(itemstack) > 0) {
                    aitemstack[i] = itemstack.cloneItemStack();
                    aitemstack[i].count = 1;
                }
            }
        }

        return aitemstack;
    }

    RecipesBannerInnerClass1(EmptyClass2 emptyclass2) {
        this();
    }
}
