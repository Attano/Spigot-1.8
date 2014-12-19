package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportCommandBlockName implements Callable {

    final CommandBlockListenerAbstract a;

    CrashReportCommandBlockName(CommandBlockListenerAbstract commandblocklistenerabstract) {
        this.a = commandblocklistenerabstract;
    }

    public String a() {
        return this.a.getName();
    }

    public Object call() {
        return this.a();
    }
}
