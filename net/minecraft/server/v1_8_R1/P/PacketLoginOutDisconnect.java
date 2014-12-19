package net.minecraft.server;

public class PacketLoginOutDisconnect implements Packet {

    private IChatBaseComponent a;

    public PacketLoginOutDisconnect() {}

    public PacketLoginOutDisconnect(IChatBaseComponent ichatbasecomponent) {
        this.a = ichatbasecomponent;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.d();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
    }

    public void a(PacketLoginOutListener packetloginoutlistener) {
        packetloginoutlistener.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketLoginOutListener) packetlistener);
    }
}
