package net.minecraft.server;

import com.google.common.base.Predicate;

final class BlockSkullInnerClass1 implements Predicate {

    BlockSkullInnerClass1() {}

    public boolean a(ShapeDetectorBlock shapedetectorblock) {
        return shapedetectorblock.a().getBlock() == Blocks.SKULL && shapedetectorblock.b() instanceof TileEntitySkull && ((TileEntitySkull) shapedetectorblock.b()).getSkullType() == 1;
    }

    public boolean apply(Object object) {
        return this.a((ShapeDetectorBlock) object);
    }
}
