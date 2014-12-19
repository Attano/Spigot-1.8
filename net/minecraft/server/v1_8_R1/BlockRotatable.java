package net.minecraft.server;

public abstract class BlockRotatable extends Block {

    public static final BlockStateEnum AXIS = BlockStateEnum.of("axis", EnumAxis.class);

    protected BlockRotatable(Material material) {
        super(material);
    }
}
