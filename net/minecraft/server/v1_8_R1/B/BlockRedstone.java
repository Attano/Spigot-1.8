package net.minecraft.server;

public class BlockRedstone extends BlockOreBlock {

    public BlockRedstone(MaterialMapColor materialmapcolor) {
        super(materialmapcolor);
        this.a(CreativeModeTab.d);
    }

    public boolean isPowerSource() {
        return true;
    }

    public int a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, EnumDirection enumdirection) {
        return 15;
    }
}
