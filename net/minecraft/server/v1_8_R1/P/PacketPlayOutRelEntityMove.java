package net.minecraft.server;

public class PacketPlayOutRelEntityMove extends PacketPlayOutEntity {

    public PacketPlayOutRelEntityMove() {}

    public PacketPlayOutRelEntityMove(int i, byte b0, byte b1, byte b2, boolean flag) {
        super(i);
        this.b = b0;
        this.c = b1;
        this.d = b2;
        this.g = flag;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        super.a(packetdataserializer);
        this.b = packetdataserializer.readByte();
        this.c = packetdataserializer.readByte();
        this.d = packetdataserializer.readByte();
        this.g = packetdataserializer.readBoolean();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        super.b(packetdataserializer);
        packetdataserializer.writeByte(this.b);
        packetdataserializer.writeByte(this.c);
        packetdataserializer.writeByte(this.d);
        packetdataserializer.writeBoolean(this.g);
    }

    public void a(PacketListener packetlistener) {
        super.a((PacketListenerPlayOut) packetlistener);
    }
}
