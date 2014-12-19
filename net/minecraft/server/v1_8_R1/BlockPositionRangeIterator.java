package net.minecraft.server;

import com.google.common.collect.AbstractIterator;

class BlockPositionRangeIterator extends AbstractIterator {

    private BlockPosition b;
    final BlockPositionRange a;

    BlockPositionRangeIterator(BlockPositionRange blockpositionrange) {
        this.a = blockpositionrange;
        this.b = null;
    }

    protected BlockPosition a() {
        if (this.b == null) {
            this.b = this.a.a;
            return this.b;
        } else if (this.b.equals(this.a.b)) {
            return (BlockPosition) this.endOfData();
        } else {
            int i = this.b.getX();
            int j = this.b.getY();
            int k = this.b.getZ();

            if (i < this.a.b.getX()) {
                ++i;
            } else if (j < this.a.b.getY()) {
                i = this.a.a.getX();
                ++j;
            } else if (k < this.a.b.getZ()) {
                i = this.a.a.getX();
                j = this.a.a.getY();
                ++k;
            }

            this.b = new BlockPosition(i, j, k);
            return this.b;
        }
    }

    protected Object computeNext() {
        return this.a();
    }
}
