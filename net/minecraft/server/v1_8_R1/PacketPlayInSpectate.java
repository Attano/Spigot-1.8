package net.minecraft.server;

import java.util.UUID;

public class PacketPlayInSpectate implements Packet {

    private UUID a;

    public PacketPlayInSpectate() {}

    public PacketPlayInSpectate(UUID uuid) {
        this.a = uuid;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.g();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public Entity a(WorldServer worldserver) {
        return worldserver.getEntity(this.a);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
