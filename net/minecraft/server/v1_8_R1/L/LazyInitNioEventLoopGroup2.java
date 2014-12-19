package net.minecraft.server;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.nio.NioEventLoopGroup;

final class LazyInitNioEventLoopGroup2 extends LazyInitVar {

    LazyInitNioEventLoopGroup2() {}

    protected NioEventLoopGroup a() {
        return new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Client IO #%d").setDaemon(true).build());
    }

    protected Object init() {
        return this.a();
    }
}
