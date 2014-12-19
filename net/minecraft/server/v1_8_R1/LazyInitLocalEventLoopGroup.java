package net.minecraft.server;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.local.LocalEventLoopGroup;

final class LazyInitLocalEventLoopGroup extends LazyInitVar {

    LazyInitLocalEventLoopGroup() {}

    protected LocalEventLoopGroup a() {
        return new LocalEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Local Server IO #%d").setDaemon(true).build());
    }

    protected Object init() {
        return this.a();
    }
}
