package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportLevelSpawnLocation implements Callable {

    final WorldData a;

    CrashReportLevelSpawnLocation(WorldData worlddata) {
        this.a = worlddata;
    }

    public String a() {
        return CrashReportSystemDetails.a((double) WorldData.d(this.a), (double) WorldData.e(this.a), (double) WorldData.f(this.a));
    }

    public Object call() {
        return this.a();
    }
}
