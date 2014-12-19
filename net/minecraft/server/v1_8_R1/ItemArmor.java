package net.minecraft.server;

public class ItemArmor extends Item {

    private static final int[] k = new int[] { 11, 16, 15, 13};
    public static final String[] a = new String[] { "minecraft:items/empty_armor_slot_helmet", "minecraft:items/empty_armor_slot_chestplate", "minecraft:items/empty_armor_slot_leggings", "minecraft:items/empty_armor_slot_boots"};
    private static final IDispenseBehavior l = new DispenseBehaviorArmor();
    public final int b;
    public final int c;
    public final int d;
    private final EnumArmorMaterial m;

    public ItemArmor(EnumArmorMaterial enumarmormaterial, int i, int j) {
        this.m = enumarmormaterial;
        this.b = j;
        this.d = i;
        this.c = enumarmormaterial.b(j);
        this.setMaxDurability(enumarmormaterial.a(j));
        this.maxStackSize = 1;
        this.a(CreativeModeTab.j);
        BlockDispenser.M.a(this, ItemArmor.l);
    }

    public int b() {
        return this.m.a();
    }

    public EnumArmorMaterial w_() {
        return this.m;
    }

    public boolean d_(ItemStack itemstack) {
        return this.m != EnumArmorMaterial.LEATHER ? false : (!itemstack.hasTag() ? false : (!itemstack.getTag().hasKeyOfType("display", 10) ? false : itemstack.getTag().getCompound("display").hasKeyOfType("color", 3)));
    }

    public int b(ItemStack itemstack) {
        if (this.m != EnumArmorMaterial.LEATHER) {
            return -1;
        } else {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound != null) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

                if (nbttagcompound1 != null && nbttagcompound1.hasKeyOfType("color", 3)) {
                    return nbttagcompound1.getInt("color");
                }
            }

            return 10511680;
        }
    }

    public void c(ItemStack itemstack) {
        if (this.m == EnumArmorMaterial.LEATHER) {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound != null) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

                if (nbttagcompound1.hasKey("color")) {
                    nbttagcompound1.remove("color");
                }

            }
        }
    }

    public void b(ItemStack itemstack, int i) {
        if (this.m != EnumArmorMaterial.LEATHER) {
            throw new UnsupportedOperationException("Can\'t dye non-leather!");
        } else {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound == null) {
                nbttagcompound = new NBTTagCompound();
                itemstack.setTag(nbttagcompound);
            }

            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

            if (!nbttagcompound.hasKeyOfType("display", 10)) {
                nbttagcompound.set("display", nbttagcompound1);
            }

            nbttagcompound1.setInt("color", i);
        }
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return this.m.b() == itemstack1.getItem() ? true : super.a(itemstack, itemstack1);
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        int i = EntityInsentient.c(itemstack) - 1;
        ItemStack itemstack1 = entityhuman.q(i);

        if (itemstack1 == null) {
            entityhuman.setEquipment(i, itemstack.cloneItemStack());
            itemstack.count = 0;
        }

        return itemstack;
    }

    static int[] d() {
        return ItemArmor.k;
    }
}
