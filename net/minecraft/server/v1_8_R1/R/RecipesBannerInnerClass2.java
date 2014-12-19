package net.minecraft.server;

class RecipesBannerInnerClass2 extends ShapelessRecipes implements IRecipe { // CraftBukkit - added extends

    // CraftBukkit start - Delegate to new parent class with bogus info
    private RecipesBannerInnerClass2() {
        super(new ItemStack(Items.BANNER, 0, 0), java.util.Arrays.asList(new ItemStack(Items.DYE, 0, 5)));
    }
    // CraftBukkit end

    public boolean a(InventoryCrafting inventorycrafting, World world) {
        boolean flag = false;

        for (int i = 0; i < inventorycrafting.getSize(); ++i) {
            ItemStack itemstack = inventorycrafting.getItem(i);

            if (itemstack != null && itemstack.getItem() == Items.BANNER) {
                if (flag) {
                    return false;
                }

                if (TileEntityBanner.c(itemstack) >= 6) {
                    return false;
                }

                flag = true;
            }
        }

        if (!flag) {
            return false;
        } else {
            return this.c(inventorycrafting) != null;
        }
    }

    public ItemStack a(InventoryCrafting inventorycrafting) {
        ItemStack itemstack = null;

        for (int i = 0; i < inventorycrafting.getSize(); ++i) {
            ItemStack itemstack1 = inventorycrafting.getItem(i);

            if (itemstack1 != null && itemstack1.getItem() == Items.BANNER) {
                itemstack = itemstack1.cloneItemStack();
                itemstack.count = 1;
                break;
            }
        }

        EnumBannerPatternType enumbannerpatterntype = this.c(inventorycrafting);

        if (enumbannerpatterntype != null) {
            int j = 0;

            ItemStack itemstack2;

            for (int k = 0; k < inventorycrafting.getSize(); ++k) {
                itemstack2 = inventorycrafting.getItem(k);
                if (itemstack2 != null && itemstack2.getItem() == Items.DYE) {
                    j = itemstack2.getData();
                    break;
                }
            }

            NBTTagCompound nbttagcompound = itemstack.a("BlockEntityTag", true);

            itemstack2 = null;
            NBTTagList nbttaglist;

            if (nbttagcompound.hasKeyOfType("Patterns", 9)) {
                nbttaglist = nbttagcompound.getList("Patterns", 10);
            } else {
                nbttaglist = new NBTTagList();
                nbttagcompound.set("Patterns", nbttaglist);
            }

            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

            nbttagcompound1.setString("Pattern", enumbannerpatterntype.b());
            nbttagcompound1.setInt("Color", j);
            nbttaglist.add(nbttagcompound1);
        }

        return itemstack;
    }

    public int a() {
        return 10;
    }

    public ItemStack b() {
        return null;
    }

    public ItemStack[] b(InventoryCrafting inventorycrafting) {
        ItemStack[] aitemstack = new ItemStack[inventorycrafting.getSize()];

        for (int i = 0; i < aitemstack.length; ++i) {
            ItemStack itemstack = inventorycrafting.getItem(i);

            if (itemstack != null && itemstack.getItem().r()) {
                aitemstack[i] = new ItemStack(itemstack.getItem().q());
            }
        }

        return aitemstack;
    }

    private EnumBannerPatternType c(InventoryCrafting inventorycrafting) {
        EnumBannerPatternType[] aenumbannerpatterntype = EnumBannerPatternType.values();
        int i = aenumbannerpatterntype.length;

        for (int j = 0; j < i; ++j) {
            EnumBannerPatternType enumbannerpatterntype = aenumbannerpatterntype[j];

            if (enumbannerpatterntype.d()) {
                boolean flag = true;
                int k;

                if (enumbannerpatterntype.e()) {
                    boolean flag1 = false;
                    boolean flag2 = false;

                    for (k = 0; k < inventorycrafting.getSize() && flag; ++k) {
                        ItemStack itemstack = inventorycrafting.getItem(k);

                        if (itemstack != null && itemstack.getItem() != Items.BANNER) {
                            if (itemstack.getItem() == Items.DYE) {
                                if (flag2) {
                                    flag = false;
                                    break;
                                }

                                flag2 = true;
                            } else {
                                if (flag1 || !itemstack.doMaterialsMatch(enumbannerpatterntype.f())) {
                                    flag = false;
                                    break;
                                }

                                flag1 = true;
                            }
                        }
                    }

                    if (!flag1) {
                        flag = false;
                    }
                } else if (inventorycrafting.getSize() != enumbannerpatterntype.c().length * enumbannerpatterntype.c()[0].length()) {
                    flag = false;
                } else {
                    int l = -1;

                    for (int i1 = 0; i1 < inventorycrafting.getSize() && flag; ++i1) {
                        k = i1 / 3;
                        int j1 = i1 % 3;
                        ItemStack itemstack1 = inventorycrafting.getItem(i1);

                        if (itemstack1 != null && itemstack1.getItem() != Items.BANNER) {
                            if (itemstack1.getItem() != Items.DYE) {
                                flag = false;
                                break;
                            }

                            if (l != -1 && l != itemstack1.getData()) {
                                flag = false;
                                break;
                            }

                            if (enumbannerpatterntype.c()[k].charAt(j1) == 32) {
                                flag = false;
                                break;
                            }

                            l = itemstack1.getData();
                        } else if (enumbannerpatterntype.c()[k].charAt(j1) != 32) {
                            flag = false;
                            break;
                        }
                    }
                }

                if (flag) {
                    return enumbannerpatterntype;
                }
            }
        }

        return null;
    }

    RecipesBannerInnerClass2(EmptyClass2 emptyclass2) {
        this();
    }
}
