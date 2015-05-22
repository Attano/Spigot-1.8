package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInSettings implements Packet<PacketListenerPlayIn> {

    private String a;
    private int b;
    private EntityHuman.EnumChatVisibility c;
    private boolean d;
    private int e;

    public PacketPlayInSettings() {}

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.c(7);
        this.b = packetdataserializer.readByte();
        this.c = EntityHuman.EnumChatVisibility.a(packetdataserializer.readByte());
        this.d = packetdataserializer.readBoolean();
        this.e = packetdataserializer.readUnsignedByte();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
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

    public EntityHuman.EnumChatVisibility c() {
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
