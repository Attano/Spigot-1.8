package net.minecraft.server;

class BlockBeaconInnerClass1InnerClass1 implements Runnable {

    final BlockPosition a;
    final BlockBeaconInnerClass1 b;

    BlockBeaconInnerClass1InnerClass1(BlockBeaconInnerClass1 blockbeaconinnerclass1, BlockPosition blockposition) {
        this.b = blockbeaconinnerclass1;
        this.a = blockposition;
    }

    public void run() {
        TileEntity tileentity = this.b.a.getTileEntity(this.a);

        if (tileentity instanceof TileEntityBeacon) {
            ((TileEntityBeacon) tileentity).m();
            this.b.a.playBlockAction(this.a, Blocks.BEACON, 1, 0);
        }

    }
}
