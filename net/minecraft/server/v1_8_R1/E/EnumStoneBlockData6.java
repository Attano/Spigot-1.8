package net.minecraft.server;

enum EnumStoneBlockData6 {;
    EnumStoneBlockData6(int i, String s, String s1) {}

    public IBlockData d() {
        return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, EnumStonebrickType.CHISELED);
    }
}
