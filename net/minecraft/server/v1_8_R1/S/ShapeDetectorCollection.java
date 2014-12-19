package net.minecraft.server;

import com.google.common.cache.LoadingCache;

public class ShapeDetectorCollection {

    private final BlockPosition a;
    private final EnumDirection b;
    private final EnumDirection c;
    private final LoadingCache d;

    public ShapeDetectorCollection(BlockPosition blockposition, EnumDirection enumdirection, EnumDirection enumdirection1, LoadingCache loadingcache) {
        this.a = blockposition;
        this.b = enumdirection;
        this.c = enumdirection1;
        this.d = loadingcache;
    }

    public EnumDirection b() {
        return this.b;
    }

    public EnumDirection c() {
        return this.c;
    }

    public ShapeDetectorBlock a(int i, int j, int k) {
        return (ShapeDetectorBlock) this.d.getUnchecked(ShapeDetector.a(this.a, this.b(), this.c(), i, j, k));
    }
}
