package net.minecraft.server;

public class PacketPlayInBlockDig implements Packet {

    private BlockPosition a;
    private EnumDirection b;
    private EnumPlayerDigType c;

    public PacketPlayInBlockDig() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.c = (EnumPlayerDigType) packetdataserializer.a(EnumPlayerDigType.class);
        this.a = packetdataserializer.c();
        this.b = EnumDirection.fromType1(packetdataserializer.readUnsignedByte());
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a((Enum) this.c);
        packetdataserializer.a(this.a);
        packetdataserializer.writeByte(this.b.a());
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public BlockPosition a() {
        return this.a;
    }

    public EnumDirection b() {
        return this.b;
    }

    public EnumPlayerDigType c() {
        return this.c;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
