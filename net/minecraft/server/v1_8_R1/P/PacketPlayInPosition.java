package net.minecraft.server;

public class PacketPlayInPosition extends PacketPlayInFlying {

    public PacketPlayInPosition() {
        this.hasPos = true;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.x = packetdataserializer.readDouble();
        this.y = packetdataserializer.readDouble();
        this.z = packetdataserializer.readDouble();
        super.a(packetdataserializer);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeDouble(this.x);
        packetdataserializer.writeDouble(this.y);
        packetdataserializer.writeDouble(this.z);
        super.b(packetdataserializer);
    }

    public void a(PacketListener packetlistener) {
        super.a((PacketListenerPlayIn) packetlistener);
    }
}
