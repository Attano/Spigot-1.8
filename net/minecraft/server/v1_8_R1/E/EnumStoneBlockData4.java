package net.minecraft.server;

enum EnumStoneBlockData4 {;
    EnumStoneBlockData4(int i, String s, String s1) {}

    public IBlockData d() {
        return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, EnumStonebrickType.MOSSY);
    }
}
