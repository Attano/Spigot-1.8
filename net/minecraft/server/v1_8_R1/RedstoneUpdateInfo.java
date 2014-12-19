package net.minecraft.server;

class RedstoneUpdateInfo {

    BlockPosition a;
    long b;

    public RedstoneUpdateInfo(BlockPosition blockposition, long i) {
        this.a = blockposition;
        this.b = i;
    }
}
