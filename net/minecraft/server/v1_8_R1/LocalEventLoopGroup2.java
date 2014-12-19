package net.minecraft.server;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.local.LocalEventLoopGroup;

final class LocalEventLoopGroup2 extends LazyInitVar {

    LocalEventLoopGroup2() {}

    protected LocalEventLoopGroup a() {
        return new LocalEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Local Client IO #%d").setDaemon(true).build());
    }

    protected Object init() {
        return this.a();
    }
}
