package net.minecraft.server;

import java.util.Iterator;

final class BlockPositionRange implements Iterable {

    final BlockPosition a;
    final BlockPosition b;

    BlockPositionRange(BlockPosition blockposition, BlockPosition blockposition1) {
        this.a = blockposition;
        this.b = blockposition1;
    }

    public Iterator iterator() {
        return new BlockPositionRangeIterator(this);
    }
}
