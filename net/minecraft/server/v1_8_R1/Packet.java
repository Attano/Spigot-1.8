package net.minecraft.server;

public interface Packet {

    void a(PacketDataSerializer packetdataserializer) throws java.io.IOException; // CraftBukkit - added throws

    void b(PacketDataSerializer packetdataserializer) throws java.io.IOException; // CraftBukkit - added throws

    void a(PacketListener packetlistener);
}
