package net.minecraft.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PacketStatusOutServerInfo implements Packet {

    private static final Gson a = (new GsonBuilder()).registerTypeAdapter(ServerPingServerData.class, new ServerPingServerDataSerializer()).registerTypeAdapter(ServerPingPlayerSample.class, new ServerPingPlayerSampleSerializer()).registerTypeAdapter(ServerPing.class, new ServerPingSerializer()).registerTypeHierarchyAdapter(IChatBaseComponent.class, new ChatSerializer()).registerTypeHierarchyAdapter(ChatModifier.class, new ChatModifierSerializer()).registerTypeAdapterFactory(new ChatTypeAdapterFactory()).create();
    private ServerPing b;

    public PacketStatusOutServerInfo() {}

    public PacketStatusOutServerInfo(ServerPing serverping) {
        this.b = serverping;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.b = (ServerPing) PacketStatusOutServerInfo.a.fromJson(packetdataserializer.c(32767), ServerPing.class);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(PacketStatusOutServerInfo.a.toJson(this.b));
    }

    public void a(PacketStatusOutListener packetstatusoutlistener) {
        packetstatusoutlistener.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketStatusOutListener) packetlistener);
    }
}
