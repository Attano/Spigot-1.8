package net.minecraft.server;

import java.util.concurrent.Callable;

class CrashReportCommandBlockCommand implements Callable {

    final CommandBlockListenerAbstract a;

    CrashReportCommandBlockCommand(CommandBlockListenerAbstract commandblocklistenerabstract) {
        this.a = commandblocklistenerabstract;
    }

    public String a() {
        return this.a.getCommand();
    }

    public Object call() {
        return this.a();
    }
}
