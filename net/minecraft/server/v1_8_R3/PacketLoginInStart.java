package net.minecraft.server;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.UUID;

public class PacketLoginInStart implements Packet<PacketLoginInListener> {

    private GameProfile a;

    public PacketLoginInStart() {}

    public PacketLoginInStart(GameProfile gameprofile) {
        this.a = gameprofile;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = new GameProfile((UUID) null, packetdataserializer.c(16));
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a.getName());
    }

    public void a(PacketLoginInListener packetlogininlistener) {
        packetlogininlistener.a(this);
    }

    public GameProfile a() {
        return this.a;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketLoginInListener) packetlistener);
    }
}
