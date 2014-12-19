package net.minecraft.server;

public class PacketPlayInSettings implements Packet {

    private String a;
    private int b;
    private EnumChatVisibility c;
    private boolean d;
    private int e;

    public PacketPlayInSettings() {}

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c(7);
        this.b = packetdataserializer.readByte();
        this.c = EnumChatVisibility.a(packetdataserializer.readByte());
        this.d = packetdataserializer.readBoolean();
        this.e = packetdataserializer.readUnsignedByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.writeByte(this.b);
        packetdataserializer.writeByte(this.c.a());
        packetdataserializer.writeBoolean(this.d);
        packetdataserializer.writeByte(this.e);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public String a() {
        return this.a;
    }

    public EnumChatVisibility c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
