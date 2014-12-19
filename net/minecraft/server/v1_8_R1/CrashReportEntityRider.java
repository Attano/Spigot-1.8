package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportEntityRider implements Callable {

    final Entity a;

    CrashReportEntityRider(Entity entity) {
        this.a = entity;
    }

    public String a() {
        return this.a.passenger.toString();
    }

    public Object call() {
        return this.a();
    }
}
