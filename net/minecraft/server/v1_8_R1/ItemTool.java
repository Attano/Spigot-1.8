package net.minecraft.server;

import com.google.common.collect.Multimap;
import java.util.Set;

public class ItemTool extends Item {

    private Set c;
    protected float a = 4.0F;
    private float d;
    protected EnumToolMaterial b;

    protected ItemTool(float f, EnumToolMaterial enumtoolmaterial, Set set) {
        this.b = enumtoolmaterial;
        this.c = set;
        this.maxStackSize = 1;
        this.setMaxDurability(enumtoolmaterial.a());
        this.a = enumtoolmaterial.b();
        this.d = f + enumtoolmaterial.c();
        this.a(CreativeModeTab.i);
    }

    public float getDestroySpeed(ItemStack itemstack, Block block) {
        return this.c.contains(block) ? this.a : 1.0F;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        itemstack.damage(2, entityliving1);
        return true;
    }

    public boolean a(ItemStack itemstack, World world, Block block, BlockPosition blockposition, EntityLiving entityliving) {
        if ((double) block.g(world, blockposition) != 0.0D) {
            itemstack.damage(1, entityliving);
        }

        return true;
    }

    public EnumToolMaterial g() {
        return this.b;
    }

    public int b() {
        return this.b.e();
    }

    public String h() {
        return this.b.toString();
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return this.b.f() == itemstack1.getItem() ? true : super.a(itemstack, itemstack1);
    }

    public Multimap i() {
        Multimap multimap = super.i();

        multimap.put(GenericAttributes.e.getName(), new AttributeModifier(ItemTool.f, "Tool modifier", (double) this.d, 0));
        return multimap;
    }
}
