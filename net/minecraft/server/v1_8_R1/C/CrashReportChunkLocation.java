package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportChunkLocation implements Callable {

    final BlockPosition a;
    final Chunk b;

    CrashReportChunkLocation(Chunk chunk, BlockPosition blockposition) {
        this.b = chunk;
        this.a = blockposition;
    }

    public String a() {
        return CrashReportSystemDetails.a(this.a);
    }

    public Object call() {
        return this.a();
    }
}
