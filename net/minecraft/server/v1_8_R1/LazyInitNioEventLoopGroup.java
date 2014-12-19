package net.minecraft.server;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.nio.NioEventLoopGroup;

final class LazyInitNioEventLoopGroup extends LazyInitVar {

    LazyInitNioEventLoopGroup() {}

    protected NioEventLoopGroup a() {
        return new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Server IO #%d").setDaemon(true).build());
    }

    protected Object init() {
        return this.a();
    }
}
