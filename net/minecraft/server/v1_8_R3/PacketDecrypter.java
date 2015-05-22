package net.minecraft.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import javax.crypto.Cipher;

public class PacketDecrypter extends MessageToMessageDecoder<ByteBuf> {

    private final PacketEncryptionHandler a;

    public PacketDecrypter(Cipher cipher) {
        this.a = new PacketEncryptionHandler(cipher);
    }

    protected void a(ChannelHandlerContext channelhandlercontext, ByteBuf bytebuf, List<Object> list) throws Exception {
        list.add(this.a.a(channelhandlercontext, bytebuf));
    }

    protected void decode(ChannelHandlerContext channelhandlercontext, Object object, List list) throws Exception {
        this.a(channelhandlercontext, (ByteBuf) object, list);
    }
}
