package net.minecraft.server;

public class PacketPlayInArmAnimation implements Packet {

    public PacketPlayInArmAnimation() {}

    public void a(PacketDataSerializer packetdataserializer) {}

    public void b(PacketDataSerializer packetdataserializer) {}

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
