package net.minecraft.server;

public class PacketPlayOutChat implements Packet {

    private IChatBaseComponent a;
    private byte b;

    public PacketPlayOutChat() {}

    public PacketPlayOutChat(IChatBaseComponent ichatbasecomponent) {
        this(ichatbasecomponent, (byte) 1);
    }

    public PacketPlayOutChat(IChatBaseComponent ichatbasecomponent, byte b0) {
        this.a = ichatbasecomponent;
        this.b = b0;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.d();
        this.b = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.writeByte(this.b);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public boolean b() {
        return this.b == 1 || this.b == 2;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
