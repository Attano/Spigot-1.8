package net.minecraft.server;

public class PacketHandshakingInSetProtocol implements Packet {

    private int a;
    public String b;
    public int c;
    private EnumProtocol d;

    public PacketHandshakingInSetProtocol() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.e();
        this.b = packetdataserializer.c(Short.MAX_VALUE); // Spigot
        this.c = packetdataserializer.readUnsignedShort();
        this.d = EnumProtocol.a(packetdataserializer.e());
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.b(this.a);
        packetdataserializer.a(this.b);
        packetdataserializer.writeShort(this.c);
        packetdataserializer.b(this.d.a());
    }

    public void a(PacketHandshakingInListener packethandshakinginlistener) {
        packethandshakinginlistener.a(this);
    }

    public EnumProtocol a() {
        return this.d;
    }

    public int b() {
        return this.a;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketHandshakingInListener) packetlistener);
    }
}
