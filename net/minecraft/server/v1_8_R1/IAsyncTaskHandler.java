package net.minecraft.server;

import com.google.common.util.concurrent.ListenableFuture;

public interface IAsyncTaskHandler {

    ListenableFuture postToMainThread(Runnable runnable);

    boolean isMainThread();
}
