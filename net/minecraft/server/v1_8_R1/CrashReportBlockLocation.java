package net.minecraft.server;

import java.util.concurrent.Callable;

final class CrashReportBlockLocation implements Callable {

    final BlockPosition a;

    CrashReportBlockLocation(BlockPosition blockposition) {
        this.a = blockposition;
    }

    public String a() {
        return CrashReportSystemDetails.a(this.a);
    }

    public Object call() {
        return this.a();
    }
}
