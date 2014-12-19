package net.minecraft.server;

import java.util.List;

public class TileEntityBanner extends TileEntity {

    public int color;
    public NBTTagList patterns;
    private boolean g;
    private List h;
    private List i;
    private String j;

    public TileEntityBanner() {}

    public void a(ItemStack itemstack) {
        this.patterns = null;
        if (itemstack.hasTag() && itemstack.getTag().hasKeyOfType("BlockEntityTag", 10)) {
            NBTTagCompound nbttagcompound = itemstack.getTag().getCompound("BlockEntityTag");

            if (nbttagcompound.hasKey("Patterns")) {
                this.patterns = (NBTTagList) nbttagcompound.getList("Patterns", 10).clone();
            }

            if (nbttagcompound.hasKeyOfType("Base", 99)) {
                this.color = nbttagcompound.getInt("Base");
            } else {
                this.color = itemstack.getData() & 15;
            }
        } else {
            this.color = itemstack.getData() & 15;
        }

        this.h = null;
        this.i = null;
        this.j = "";
        this.g = true;
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Base", this.color);
        if (this.patterns != null) {
            nbttagcompound.set("Patterns", this.patterns);
        }

    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.color = nbttagcompound.getInt("Base");
        this.patterns = nbttagcompound.getList("Patterns", 10);
        this.h = null;
        this.i = null;
        this.j = null;
        this.g = true;
    }

    public Packet getUpdatePacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        this.b(nbttagcompound);
        return new PacketPlayOutTileEntityData(this.position, 6, nbttagcompound);
    }

    public int b() {
        return this.color;
    }

    public static int b(ItemStack itemstack) {
        NBTTagCompound nbttagcompound = itemstack.a("BlockEntityTag", false);

        return nbttagcompound != null && nbttagcompound.hasKey("Base") ? nbttagcompound.getInt("Base") : itemstack.getData();
    }

    public static int c(ItemStack itemstack) {
        NBTTagCompound nbttagcompound = itemstack.a("BlockEntityTag", false);

        return nbttagcompound != null && nbttagcompound.hasKey("Patterns") ? nbttagcompound.getList("Patterns", 10).size() : 0;
    }

    public static void e(ItemStack itemstack) {
        NBTTagCompound nbttagcompound = itemstack.a("BlockEntityTag", false);

        if (nbttagcompound != null && nbttagcompound.hasKeyOfType("Patterns", 9)) {
            NBTTagList nbttaglist = nbttagcompound.getList("Patterns", 10);

            if (nbttaglist.size() > 0) {
                nbttaglist.a(nbttaglist.size() - 1);
                if (nbttaglist.isEmpty()) {
                    itemstack.getTag().remove("BlockEntityTag");
                    if (itemstack.getTag().isEmpty()) {
                        itemstack.setTag((NBTTagCompound) null);
                    }
                }

            }
        }
    }
}
