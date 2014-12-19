package net.minecraft.server;

import java.util.concurrent.Callable;

final class CrashReportGenLayer1 implements Callable {

    final BiomeBase a;

    CrashReportGenLayer1(BiomeBase biomebase) {
        this.a = biomebase;
    }

    public String a() {
        return String.valueOf(this.a);
    }

    public Object call() {
        return this.a();
    }
}
