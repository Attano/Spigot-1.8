package net.minecraft.server;

import java.io.IOException;

public class PacketStatusInStart implements Packet<PacketStatusInListener> {

    public PacketStatusInStart() {}

    public void a(PacketDataSerializer packetdataserializer) throws IOException {}

    public void b(PacketDataSerializer packetdataserializer) throws IOException {}

    public void a(PacketStatusInListener packetstatusinlistener) {
        packetstatusinlistener.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketStatusInListener) packetlistener);
    }
}
