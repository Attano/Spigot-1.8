package net.minecraft.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.util.zip.Deflater;

public class PacketCompressor extends MessageToByteEncoder<ByteBuf> {

    private final byte[] a = new byte[8192];
    private final Deflater b;
    private int c;

    public PacketCompressor(int i) {
        this.c = i;
        this.b = new Deflater();
    }

    protected void a(ChannelHandlerContext channelhandlercontext, ByteBuf bytebuf, ByteBuf bytebuf1) throws Exception {
        int i = bytebuf.readableBytes();
        PacketDataSerializer packetdataserializer = new PacketDataSerializer(bytebuf1);

        if (i < this.c) {
            packetdataserializer.b(0);
            packetdataserializer.writeBytes(bytebuf);
        } else {
            byte[] abyte = new byte[i];

            bytebuf.readBytes(abyte);
            packetdataserializer.b(abyte.length);
            this.b.setInput(abyte, 0, i);
            this.b.finish();

            while (!this.b.finished()) {
                int j = this.b.deflate(this.a);

                packetdataserializer.writeBytes(this.a, 0, j);
            }

            this.b.reset();
        }

    }

    public void a(int i) {
        this.c = i;
    }

    protected void encode(ChannelHandlerContext channelhandlercontext, Object object, ByteBuf bytebuf) throws Exception {
        this.a(channelhandlercontext, (ByteBuf) object, bytebuf);
    }
}
