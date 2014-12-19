package net.minecraft.server;

public class PacketPlayInEntityAction implements Packet {

    private int a;
    private EnumPlayerAction animation;
    private int c;

    public PacketPlayInEntityAction() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.e();
        this.animation = (EnumPlayerAction) packetdataserializer.a(EnumPlayerAction.class);
        this.c = packetdataserializer.e();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
        packetdataserializer.a((Enum) this.animation);
        packetdataserializer.b(this.c);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public EnumPlayerAction b() {
        return this.animation;
    }

    public int c() {
        return this.c;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
