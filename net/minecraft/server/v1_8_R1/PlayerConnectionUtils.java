package net.minecraft.server;

public class PlayerConnectionUtils {

    public static void ensureMainThread(Packet packet, PacketListener packetlistener, IAsyncTaskHandler iasynctaskhandler) {
        if (!iasynctaskhandler.isMainThread()) {
            iasynctaskhandler.postToMainThread(new PacketHandleTask(packet, packetlistener));
            throw CancelledPacketHandleException.INSTANCE;
        }
    }
}
