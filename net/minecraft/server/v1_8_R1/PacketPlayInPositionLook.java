package net.minecraft.server;

public class PacketPlayInPositionLook extends PacketPlayInFlying {

    public PacketPlayInPositionLook() {
        this.hasPos = true;
        this.hasLook = true;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.x = packetdataserializer.readDouble();
        this.y = packetdataserializer.readDouble();
        this.z = packetdataserializer.readDouble();
        this.yaw = packetdataserializer.readFloat();
        this.pitch = packetdataserializer.readFloat();
        super.a(packetdataserializer);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeDouble(this.x);
        packetdataserializer.writeDouble(this.y);
        packetdataserializer.writeDouble(this.z);
        packetdataserializer.writeFloat(this.yaw);
        packetdataserializer.writeFloat(this.pitch);
        super.b(packetdataserializer);
    }

    public void a(PacketListener packetlistener) {
        super.a((PacketListenerPlayIn) packetlistener);
    }
}
