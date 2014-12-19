package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;

public class PacketPlayOutMapChunk implements Packet {

    private int a;
    private int b;
    private ChunkMap c;
    private boolean d;

    public PacketPlayOutMapChunk() {}

    public PacketPlayOutMapChunk(Chunk chunk, boolean flag, int i) {
        this.a = chunk.locX;
        this.b = chunk.locZ;
        this.d = flag;
        this.c = a(chunk, flag, !chunk.getWorld().worldProvider.o(), i);
        chunk.world.spigotConfig.antiXrayInstance.obfuscateSync(chunk.locX, chunk.locZ, c.b, c.a, chunk.world);
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.a = packetdataserializer.readInt();
        this.b = packetdataserializer.readInt();
        this.d = packetdataserializer.readBoolean();
        this.c = new ChunkMap();
        this.c.b = packetdataserializer.readShort();
        this.c.a = packetdataserializer.a();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.a);
        packetdataserializer.writeInt(this.b);
        packetdataserializer.writeBoolean(this.d);
        packetdataserializer.writeShort((short) (this.c.b & '\uffff'));
        packetdataserializer.a(this.c.a);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    protected static int a(int i, boolean flag, boolean flag1) {
        int j = i * 2 * 16 * 16 * 16;
        int k = i * 16 * 16 * 16 / 2;
        int l = flag ? i * 16 * 16 * 16 / 2 : 0;
        int i1 = flag1 ? 256 : 0;

        return j + k + l + i1;
    }

    public static ChunkMap a(Chunk chunk, boolean flag, boolean flag1, int i) {
        ChunkSection[] achunksection = chunk.getSections();
        ChunkMap chunkmap = new ChunkMap();
        ArrayList arraylist = Lists.newArrayList();

        int j;

        for (j = 0; j < achunksection.length; ++j) {
            ChunkSection chunksection = achunksection[j];

            if (chunksection != null && (!flag || !chunksection.a()) && (i & 1 << j) != 0) {
                chunkmap.b |= 1 << j;
                arraylist.add(chunksection);
            }
        }

        chunkmap.a = new byte[a(Integer.bitCount(chunkmap.b), flag1, flag)];
        j = 0;
        Iterator iterator = arraylist.iterator();

        ChunkSection chunksection1;

        while (iterator.hasNext()) {
            chunksection1 = (ChunkSection) iterator.next();
            char[] achar = chunksection1.getIdArray();
            char[] achar1 = achar;
            int k = achar.length;

            for (int l = 0; l < k; ++l) {
                char c0 = achar1[l];

                chunkmap.a[j++] = (byte) (c0 & 255);
                chunkmap.a[j++] = (byte) (c0 >> 8 & 255);
            }
        }

        for (iterator = arraylist.iterator(); iterator.hasNext(); j = a(chunksection1.getEmittedLightArray().a(), chunkmap.a, j)) {
            chunksection1 = (ChunkSection) iterator.next();
        }

        if (flag1) {
            for (iterator = arraylist.iterator(); iterator.hasNext(); j = a(chunksection1.getSkyLightArray().a(), chunkmap.a, j)) {
                chunksection1 = (ChunkSection) iterator.next();
            }
        }

        if (flag) {
            a(chunk.getBiomeIndex(), chunkmap.a, j);
        }

        return chunkmap;
    }

    private static int a(byte[] abyte, byte[] abyte1, int i) {
        System.arraycopy(abyte, 0, abyte1, i, abyte.length);
        return i + abyte.length;
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketListenerPlayOut) packetlistener);
    }
}
