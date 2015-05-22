package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInClientCommand implements Packet<PacketListenerPlayIn> {

    private PacketPlayInClientCommand.EnumClientCommand a;

    public PacketPlayInClientCommand() {}

    public PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand packetplayinclientcommand_enumclientcommand) {
        this.a = packetplayinclientcommand_enumclientcommand;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = (PacketPlayInClientCommand.EnumClientCommand) packetdataserializer.a(PacketPlayInClientCommand.EnumClientCommand.class);
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.a);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public PacketPlayInClientCommand.EnumClientCommand a() {
        return this.a;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }

    public static enum EnumClientCommand {

        PERFORM_RESPAWN, REQUEST_STATS, OPEN_INVENTORY_ACHIEVEMENT;

        private EnumClientCommand() {}
    }
}
