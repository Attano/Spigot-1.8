package net.minecraft.server;

public class PacketPlayInResourcePackStatus implements Packet {

    private String a;
    private EnumResourcePackStatus b;

    public PacketPlayInResourcePackStatus() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c(40);
        this.b = (EnumResourcePackStatus) packetdataserializer.a(EnumResourcePackStatus.class);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.a((Enum) this.b);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
