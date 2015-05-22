package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutCamera implements Packet<PacketListenerPlayOut> {

    public int a;

    public PacketPlayOutCamera() {}

    public PacketPlayOutCamera(Entity entity) {
        this.a = entity.getId();
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.e();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.b(this.a);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
