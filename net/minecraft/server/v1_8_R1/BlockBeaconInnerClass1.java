package net.minecraft.server;

final class BlockBeaconInnerClass1 implements Runnable {

    final World a;
    final BlockPosition b;

    BlockBeaconInnerClass1(World world, BlockPosition blockposition) {
        this.a = world;
        this.b = blockposition;
    }

    public void run() {
        Chunk chunk = this.a.getChunkAtWorldCoords(this.b);

        for (int i = this.b.getY() - 1; i >= 0; --i) {
            BlockPosition blockposition = new BlockPosition(this.b.getX(), i, this.b.getZ());

            if (!chunk.d(blockposition)) {
                break;
            }

            IBlockData iblockdata = this.a.getType(blockposition);

            if (iblockdata.getBlock() == Blocks.BEACON) {
                ((WorldServer) this.a).postToMainThread(new BlockBeaconInnerClass1InnerClass1(this, blockposition));
            }
        }

    }
}
