package net.minecraft.server;

public class PacketPlayInFlying implements Packet {

    protected double x;
    protected double y;
    protected double z;
    protected float yaw;
    protected float pitch;
    protected boolean f;
    protected boolean hasPos;
    protected boolean hasLook;

    public PacketPlayInFlying() {}

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.f = packetdataserializer.readUnsignedByte() != 0;
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeByte(this.f ? 1 : 0);
    }

    public double a() {
        return this.x;
    }

    public double b() {
        return this.y;
    }

    public double c() {
        return this.z;
    }

    public float d() {
        return this.yaw;
    }

    public float e() {
        return this.pitch;
    }

    public boolean f() {
        return this.f;
    }

    public boolean g() {
        return this.hasPos;
    }

    public boolean h() {
        return this.hasLook;
    }

    public void a(boolean flag) {
        this.hasPos = flag;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayIn) packetlistener);
    }
}
