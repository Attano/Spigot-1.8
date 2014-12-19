package net.minecraft.server;

import java.util.Iterator;

final class MutableBlockPositionRange implements Iterable {

    final BlockPosition a;
    final BlockPosition b;

    MutableBlockPositionRange(BlockPosition blockposition, BlockPosition blockposition1) {
        this.a = blockposition;
        this.b = blockposition1;
    }

    public Iterator iterator() {
        return new MutableBlockPositionRangeIterator(this);
    }
}
