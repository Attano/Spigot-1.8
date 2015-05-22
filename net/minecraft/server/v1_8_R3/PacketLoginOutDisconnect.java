package net.minecraft.server;

import java.io.IOException;

public class PacketLoginOutDisconnect implements Packet<PacketLoginOutListener> {

    private IChatBaseComponent a;

    public PacketLoginOutDisconnect() {}

    public PacketLoginOutDisconnect(IChatBaseComponent ichatbasecomponent) {
        this.a = ichatbasecomponent;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.d();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a);
    }

    public void a(PacketLoginOutListener packetloginoutlistener) {
        packetloginoutlistener.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketLoginOutListener) packetlistener);
    }
}
