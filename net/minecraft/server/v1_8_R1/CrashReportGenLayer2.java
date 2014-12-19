package net.minecraft.server;

import java.util.concurrent.Callable;

final class CrashReportGenLayer2 implements Callable {

    final BiomeBase a;

    CrashReportGenLayer2(BiomeBase biomebase) {
        this.a = biomebase;
    }

    public String a() {
        return String.valueOf(this.a);
    }

    public Object call() {
        return this.a();
    }
}
