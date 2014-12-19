package net.minecraft.server;

import com.google.common.collect.AbstractIterator;

class MutableBlockPositionRangeIterator extends AbstractIterator {

    private MutableBlockPosition b;
    final MutableBlockPositionRange a;

    MutableBlockPositionRangeIterator(MutableBlockPositionRange mutableblockpositionrange) {
        this.a = mutableblockpositionrange;
        this.b = null;
    }

    protected MutableBlockPosition a() {
        if (this.b == null) {
            this.b = new MutableBlockPosition(this.a.a.getX(), this.a.a.getY(), this.a.a.getZ(), (BlockPositionRange) null);
            return this.b;
        } else if (this.b.equals(this.a.b)) {
            return (MutableBlockPosition) this.endOfData();
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

            this.b.b = i;
            this.b.c = j;
            this.b.d = k;
            return this.b;
        }
    }

    protected Object computeNext() {
        return this.a();
    }
}
