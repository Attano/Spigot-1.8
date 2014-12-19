package net.minecraft.server;

enum EnumStoneBlockData3 {;
    EnumStoneBlockData3(int i, String s, String s1) {}

    public IBlockData d() {
        return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, EnumStonebrickType.DEFAULT);
    }
}
