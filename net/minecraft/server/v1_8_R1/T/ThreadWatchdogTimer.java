package net.minecraft.server;

import java.util.TimerTask;

class ThreadWatchdogTimer extends TimerTask {

    final ThreadWatchdog a;

    ThreadWatchdogTimer(ThreadWatchdog threadwatchdog) {
        this.a = threadwatchdog;
    }

    public void run() {
        Runtime.getRuntime().halt(1);
    }
}
