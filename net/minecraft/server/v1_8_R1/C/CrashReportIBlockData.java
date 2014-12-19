package net.minecraft.server;

import java.util.concurrent.Callable;

final class CrashReportIBlockData implements Callable {

    final IBlockData a;

    CrashReportIBlockData(IBlockData iblockdata) {
        this.a = iblockdata;
    }

    public String a() {
        return this.a.toString();
    }

    public Object call() {
        return this.a();
    }
}
