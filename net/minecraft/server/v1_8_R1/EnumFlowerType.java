package net.minecraft.server;

public enum EnumFlowerType {

    YELLOW, RED;

    private EnumFlowerType() {}

    public BlockFlowers a() {
        return this == EnumFlowerType.YELLOW ? Blocks.YELLOW_FLOWER : Blocks.RED_FLOWER;
    }
}
