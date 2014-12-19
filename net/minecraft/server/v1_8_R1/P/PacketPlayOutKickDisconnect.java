package net.minecraft.server;

public class PacketPlayOutKickDisconnect implements Packet {

    private IChatBaseComponent a;

    public PacketPlayOutKickDisconnect() {}

    public PacketPlayOutKickDisconnect(IChatBaseComponent ichatbasecomponent) {
        this.a = ichatbasecomponent;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.d();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
