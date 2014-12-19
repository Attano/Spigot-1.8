package net.minecraft.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.Future;

class LoginListenerInnerClass1 implements ChannelFutureListener {

    final LoginListener a;

    LoginListenerInnerClass1(LoginListener loginlistener) {
        this.a = loginlistener;
    }

    public void a(ChannelFuture channelfuture) {
        this.a.networkManager.a(LoginListener.a(this.a).aI());
    }

    public void operationComplete(Future future) {
        this.a((ChannelFuture) future);
    }
}
