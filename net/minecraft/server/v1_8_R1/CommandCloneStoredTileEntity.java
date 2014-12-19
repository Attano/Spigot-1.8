package net.minecraft.server;

class CommandCloneStoredTileEntity {

    public final BlockPosition a;
    public final IBlockData b;
    public final NBTTagCompound c;

    public CommandCloneStoredTileEntity(BlockPosition blockposition, IBlockData iblockdata, NBTTagCompound nbttagcompound) {
        this.a = blockposition;
        this.b = iblockdata;
        this.c = nbttagcompound;
    }
}
