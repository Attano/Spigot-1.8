package net.minecraft.server;

public class PacketPlayInClientCommand implements Packet {

    private EnumClientCommand a;

    public PacketPlayInClientCommand() {}

    public PacketPlayInClientCommand(EnumClientCommand enumclientcommand) {
        this.a = enumclientcommand;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = (EnumClientCommand) packetdataserializer.a(EnumClientCommand.class);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a((Enum) this.a);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public EnumClientCommand a() {
        return this.a;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
