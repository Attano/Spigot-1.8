package net.minecraft.server;

import java.util.List;

public class PacketPlayOutWindowItems implements Packet {

    private int a;
    private ItemStack[] b;

    public PacketPlayOutWindowItems() {}

    public PacketPlayOutWindowItems(int i, List list) {
        this.a = i;
        this.b = new ItemStack[list.size()];

        for (int j = 0; j < this.b.length; ++j) {
            ItemStack itemstack = (ItemStack) list.get(j);

            this.b[j] = itemstack == null ? null : itemstack.cloneItemStack();
        }

    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readUnsignedByte();
        short short0 = packetdataserializer.readShort();

        this.b = new ItemStack[short0];

        for (int i = 0; i < short0; ++i) {
            this.b[i] = packetdataserializer.i();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.writeShort(this.b.length);
        ItemStack[] aitemstack = this.b;
        int i = aitemstack.length;

        for (int j = 0; j < i; ++j) {
            ItemStack itemstack = aitemstack[j];

            packetdataserializer.a(itemstack);
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
