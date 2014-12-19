package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportWorldLocation implements Callable {

    final BlockPosition a;
    final World b;

    CrashReportWorldLocation(World world, BlockPosition blockposition) {
        this.b = world;
        this.a = blockposition;
    }

    public String a() {
        return CrashReportSystemDetails.a(this.a);
    }

    public Object call() {
        return this.a();
    }
}
