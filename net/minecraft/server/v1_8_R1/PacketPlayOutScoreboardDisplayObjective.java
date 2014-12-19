package net.minecraft.server;

public class PacketPlayOutScoreboardDisplayObjective implements Packet {

    private int a;
    private String b;

    public PacketPlayOutScoreboardDisplayObjective() {}

    public PacketPlayOutScoreboardDisplayObjective(int i, ScoreboardObjective scoreboardobjective) {
        this.a = i;
        if (scoreboardobjective == null) {
            this.b = "";
        } else {
            this.b = scoreboardobjective.getName();
        }

    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readByte();
        this.b = packetdataserializer.c(16);
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.a(this.b);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
