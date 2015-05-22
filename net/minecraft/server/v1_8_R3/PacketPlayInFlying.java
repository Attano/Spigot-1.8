package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInFlying implements Packet<PacketListenerPlayIn> {

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

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.f = packetdataserializer.readUnsignedByte() != 0;
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
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

    public static class PacketPlayInLook extends PacketPlayInFlying {

        public PacketPlayInLook() {
            this.hasLook = true;
        }

        public void a(PacketDataSerializer packetdataserializer) throws IOException {
            this.yaw = packetdataserializer.readFloat();
            this.pitch = packetdataserializer.readFloat();
            super.a(packetdataserializer);
        }

        public void b(PacketDataSerializer packetdataserializer) throws IOException {
            packetdataserializer.writeFloat(this.yaw);
            packetdataserializer.writeFloat(this.pitch);
            super.b(packetdataserializer);
        }

        public void a(PacketListener packetlistener) {
            super.a((PacketListenerPlayIn) packetlistener);
        }
    }

    public static class PacketPlayInPosition extends PacketPlayInFlying {

        public PacketPlayInPosition() {
            this.hasPos = true;
        }

        public void a(PacketDataSerializer packetdataserializer) throws IOException {
            this.x = packetdataserializer.readDouble();
            this.y = packetdataserializer.readDouble();
            this.z = packetdataserializer.readDouble();
            super.a(packetdataserializer);
        }

        public void b(PacketDataSerializer packetdataserializer) throws IOException {
            packetdataserializer.writeDouble(this.x);
            packetdataserializer.writeDouble(this.y);
            packetdataserializer.writeDouble(this.z);
            super.b(packetdataserializer);
        }

        public void a(PacketListener packetlistener) {
            super.a((PacketListenerPlayIn) packetlistener);
        }
    }

    public static class PacketPlayInPositionLook extends PacketPlayInFlying {

        public PacketPlayInPositionLook() {
            this.hasPos = true;
            this.hasLook = true;
        }

        public void a(PacketDataSerializer packetdataserializer) throws IOException {
            this.x = packetdataserializer.readDouble();
            this.y = packetdataserializer.readDouble();
            this.z = packetdataserializer.readDouble();
            this.yaw = packetdataserializer.readFloat();
            this.pitch = packetdataserializer.readFloat();
            super.a(packetdataserializer);
        }

        public void b(PacketDataSerializer packetdataserializer) throws IOException {
            packetdataserializer.writeDouble(this.x);
            packetdataserializer.writeDouble(this.y);
            packetdataserializer.writeDouble(this.z);
            packetdataserializer.writeFloat(this.yaw);
            packetdataserializer.writeFloat(this.pitch);
            super.b(packetdataserializer);
        }

        public void a(PacketListener packetlistener) {
            super.a((PacketListenerPlayIn) packetlistener);
        }
    }
}
