package net.minecraft.server;

enum EnumStoneBlockData1 {;
    EnumStoneBlockData1(int i, String s) {}

    public IBlockData d() {
        return Blocks.STONE.getBlockData().set(BlockStone.VARIANT, EnumStoneVariant.STONE);
    }
}
