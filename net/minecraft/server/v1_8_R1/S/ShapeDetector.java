package net.minecraft.server;

import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import java.util.Iterator;

public class ShapeDetector {

    private final Predicate[][][] a;
    private final int b;
    private final int c;
    private final int d;

    public ShapeDetector(Predicate[][][] apredicate) {
        this.a = apredicate;
        this.b = apredicate.length;
        if (this.b > 0) {
            this.c = apredicate[0].length;
            if (this.c > 0) {
                this.d = apredicate[0][0].length;
            } else {
                this.d = 0;
            }
        } else {
            this.c = 0;
            this.d = 0;
        }

    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    private ShapeDetectorCollection a(BlockPosition blockposition, EnumDirection enumdirection, EnumDirection enumdirection1, LoadingCache loadingcache) {
        for (int i = 0; i < this.d; ++i) {
            for (int j = 0; j < this.c; ++j) {
                for (int k = 0; k < this.b; ++k) {
                    if (!this.a[k][j][i].apply(loadingcache.getUnchecked(a(blockposition, enumdirection, enumdirection1, i, j, k)))) {
                        return null;
                    }
                }
            }
        }

        return new ShapeDetectorCollection(blockposition, enumdirection, enumdirection1, loadingcache);
    }

    public ShapeDetectorCollection a(World world, BlockPosition blockposition) {
        LoadingCache loadingcache = CacheBuilder.newBuilder().build(new ShapeDetectorInnerClass1(world));
        int i = Math.max(Math.max(this.d, this.c), this.b);
        Iterator iterator = BlockPosition.a(blockposition, blockposition.a(i - 1, i - 1, i - 1)).iterator();

        while (iterator.hasNext()) {
            BlockPosition blockposition1 = (BlockPosition) iterator.next();
            EnumDirection[] aenumdirection = EnumDirection.values();
            int j = aenumdirection.length;

            for (int k = 0; k < j; ++k) {
                EnumDirection enumdirection = aenumdirection[k];
                EnumDirection[] aenumdirection1 = EnumDirection.values();
                int l = aenumdirection1.length;

                for (int i1 = 0; i1 < l; ++i1) {
                    EnumDirection enumdirection1 = aenumdirection1[i1];

                    if (enumdirection1 != enumdirection && enumdirection1 != enumdirection.opposite()) {
                        ShapeDetectorCollection shapedetectorcollection = this.a(blockposition1, enumdirection, enumdirection1, loadingcache);

                        if (shapedetectorcollection != null) {
                            return shapedetectorcollection;
                        }
                    }
                }
            }
        }

        return null;
    }

    protected static BlockPosition a(BlockPosition blockposition, EnumDirection enumdirection, EnumDirection enumdirection1, int i, int j, int k) {
        if (enumdirection != enumdirection1 && enumdirection != enumdirection1.opposite()) {
            BaseBlockPosition baseblockposition = new BaseBlockPosition(enumdirection.getAdjacentX(), enumdirection.getAdjacentY(), enumdirection.getAdjacentZ());
            BaseBlockPosition baseblockposition1 = new BaseBlockPosition(enumdirection1.getAdjacentX(), enumdirection1.getAdjacentY(), enumdirection1.getAdjacentZ());
            BaseBlockPosition baseblockposition2 = baseblockposition.d(baseblockposition1);

            return blockposition.a(baseblockposition1.getX() * -j + baseblockposition2.getX() * i + baseblockposition.getX() * k, baseblockposition1.getY() * -j + baseblockposition2.getY() * i + baseblockposition.getY() * k, baseblockposition1.getZ() * -j + baseblockposition2.getZ() * i + baseblockposition.getZ() * k);
        } else {
            throw new IllegalArgumentException("Invalid forwards & up combination");
        }
    }
}
