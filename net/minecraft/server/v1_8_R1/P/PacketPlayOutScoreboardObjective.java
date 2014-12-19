package net.minecraft.server;

public class PacketPlayOutScoreboardObjective implements Packet {

    private String a;
    private String b;
    private EnumScoreboardHealthDisplay c;
    private int d;

    public PacketPlayOutScoreboardObjective() {}

    public PacketPlayOutScoreboardObjective(ScoreboardObjective scoreboardobjective, int i) {
        this.a = scoreboardobjective.getName();
        this.b = scoreboardobjective.getDisplayName();
        this.c = scoreboardobjective.getCriteria().c();
        this.d = i;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c(16);
        this.d = packetdataserializer.readByte();
        if (this.d == 0 || this.d == 2) {
            this.b = packetdataserializer.c(32);
            this.c = EnumScoreboardHealthDisplay.a(packetdataserializer.c(16));
        }

    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.writeByte(this.d);
        if (this.d == 0 || this.d == 2) {
            packetdataserializer.a(this.b);
            packetdataserializer.a(this.c.a());
        }

    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
