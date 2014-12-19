package net.minecraft.server;

public class PacketPlayOutCloseWindow implements Packet {

    private int a;

    public PacketPlayOutCloseWindow() {}

    public PacketPlayOutCloseWindow(int i) {
        this.a = i;
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readUnsignedByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
