package net.minecraft.server;

import com.mojang.authlib.GameProfile;
import java.util.UUID;

public class PacketLoginInStart implements Packet {

    private GameProfile a;

    public PacketLoginInStart() {}

    public PacketLoginInStart(GameProfile gameprofile) {
        this.a = gameprofile;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = new GameProfile((UUID) null, packetdataserializer.c(16));
    }

    public void b(PacketDataSerializer packetdataserializer) {
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
