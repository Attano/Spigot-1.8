package net.minecraft.server;

public class PacketPlayOutOpenWindow implements Packet {

    private int a;
    private String b;
    private IChatBaseComponent c;
    private int d;
    private int e;

    public PacketPlayOutOpenWindow() {}

    public PacketPlayOutOpenWindow(int i, String s, IChatBaseComponent ichatbasecomponent) {
        this(i, s, ichatbasecomponent, 0);
    }

    public PacketPlayOutOpenWindow(int i, String s, IChatBaseComponent ichatbasecomponent, int j) {
        this.a = i;
        this.b = s;
        this.c = ichatbasecomponent;
        this.d = j;
    }

    public PacketPlayOutOpenWindow(int i, String s, IChatBaseComponent ichatbasecomponent, int j, int k) {
        this(i, s, ichatbasecomponent, j);
        this.e = k;
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readUnsignedByte();
        this.b = packetdataserializer.c(32);
        this.c = packetdataserializer.d();
        this.d = packetdataserializer.readUnsignedByte();
        if (this.b.equals("EntityHorse")) {
            this.e = packetdataserializer.readInt();
        }

    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.a);
        packetdataserializer.a(this.b);
        packetdataserializer.a(this.c);
        packetdataserializer.writeByte(this.d);
        if (this.b.equals("EntityHorse")) {
            packetdataserializer.writeInt(this.e);
        }

    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
