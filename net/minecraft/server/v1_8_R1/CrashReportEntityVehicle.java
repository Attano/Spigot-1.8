package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportEntityVehicle implements Callable {

    final Entity a;

    CrashReportEntityVehicle(Entity entity) {
        this.a = entity;
    }

    public String a() {
        return this.a.vehicle.toString();
    }

    public Object call() {
        return this.a();
    }
}
