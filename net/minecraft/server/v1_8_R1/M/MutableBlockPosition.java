package net.minecraft.server;

public final class MutableBlockPosition extends BlockPosition {

    public int b;
    public int c;
    public int d;

    private MutableBlockPosition(int i, int j, int k) {
        super(0, 0, 0);
        this.b = i;
        this.c = j;
        this.d = k;
    }

    public int getX() {
        return this.b;
    }

    public int getY() {
        return this.c;
    }

    public int getZ() {
        return this.d;
    }

    public BaseBlockPosition d(BaseBlockPosition baseblockposition) {
        return super.c(baseblockposition);
    }

    MutableBlockPosition(int i, int j, int k, BlockPositionRange blockpositionrange) {
        this(i, j, k);
    }
}
