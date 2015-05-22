package net.minecraft.server;

import java.io.IOException;
import java.util.UUID;

public class PacketPlayInSpectate implements Packet<PacketListenerPlayIn> {

    private UUID a;

    public PacketPlayInSpectate() {}

    public PacketPlayInSpectate(UUID uuid) {
        this.a = uuid;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = packetdataserializer.g();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
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
