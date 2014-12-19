package net.minecraft.server;

import com.google.common.cache.CacheLoader;

class ShapeDetectorInnerClass1 extends CacheLoader {

    private final World a;

    public ShapeDetectorInnerClass1(World world) {
        this.a = world;
    }

    public ShapeDetectorBlock a(BlockPosition blockposition) {
        return new ShapeDetectorBlock(this.a, blockposition);
    }

    public Object load(Object object) {
        return this.a((BlockPosition) object);
    }
}
