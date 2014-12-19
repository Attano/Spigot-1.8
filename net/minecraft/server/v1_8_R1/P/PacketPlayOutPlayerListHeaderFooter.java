package net.minecraft.server;

public class PacketPlayOutPlayerListHeaderFooter implements Packet {

    private IChatBaseComponent a;
    private IChatBaseComponent b;

    public PacketPlayOutPlayerListHeaderFooter() {}

    public PacketPlayOutPlayerListHeaderFooter(IChatBaseComponent ichatbasecomponent) {
        this.a = ichatbasecomponent;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.d();
        this.b = packetdataserializer.d();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.a(this.b);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
