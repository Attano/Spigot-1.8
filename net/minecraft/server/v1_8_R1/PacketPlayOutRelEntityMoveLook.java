package net.minecraft.server;

public class PacketPlayOutRelEntityMoveLook extends PacketPlayOutEntity {

    public PacketPlayOutRelEntityMoveLook() {
        this.h = true;
    }

    public PacketPlayOutRelEntityMoveLook(int i, byte b0, byte b1, byte b2, byte b3, byte b4, boolean flag) {
        super(i);
        this.b = b0;
        this.c = b1;
        this.d = b2;
        this.e = b3;
        this.f = b4;
        this.g = flag;
        this.h = true;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        super.a(packetdataserializer);
        this.b = packetdataserializer.readByte();
        this.c = packetdataserializer.readByte();
        this.d = packetdataserializer.readByte();
        this.e = packetdataserializer.readByte();
        this.f = packetdataserializer.readByte();
        this.g = packetdataserializer.readBoolean();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        super.b(packetdataserializer);
        packetdataserializer.writeByte(this.b);
        packetdataserializer.writeByte(this.c);
        packetdataserializer.writeByte(this.d);
        packetdataserializer.writeByte(this.e);
        packetdataserializer.writeByte(this.f);
        packetdataserializer.writeBoolean(this.g);
    }

    public void a(PacketListener packetlistener) {
        super.a((PacketListenerPlayOut) packetlistener);
    }
}
