package net.minecraft.server;

public class PacketPlayOutEntityLook extends PacketPlayOutEntity {

    public PacketPlayOutEntityLook() {
        this.h = true;
    }

    public PacketPlayOutEntityLook(int i, byte b0, byte b1, boolean flag) {
        super(i);
        this.e = b0;
        this.f = b1;
        this.h = true;
        this.g = flag;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        super.a(packetdataserializer);
        this.e = packetdataserializer.readByte();
        this.f = packetdataserializer.readByte();
        this.g = packetdataserializer.readBoolean();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        super.b(packetdataserializer);
        packetdataserializer.writeByte(this.e);
        packetdataserializer.writeByte(this.f);
        packetdataserializer.writeBoolean(this.g);
    }

    public void a(PacketListener packetlistener) {
        super.a((PacketListenerPlayOut) packetlistener);
    }
}
