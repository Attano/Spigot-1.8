package net.minecraft.server;

public class PacketPlayOutBlockChange implements Packet {

    private BlockPosition a;
    public IBlockData block;

    public PacketPlayOutBlockChange() {}

    public PacketPlayOutBlockChange(World world, BlockPosition blockposition) {
        this.a = blockposition;
        this.block = world.getType(blockposition);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.c();
        this.block = (IBlockData) Block.d.a(packetdataserializer.e());
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.b(Block.d.b(this.block));
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
