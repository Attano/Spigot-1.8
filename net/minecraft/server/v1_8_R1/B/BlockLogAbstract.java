package net.minecraft.server;

import java.util.Iterator;

public abstract class BlockLogAbstract extends BlockRotatable {

    public static final BlockStateEnum AXIS = BlockStateEnum.of("axis", EnumLogRotation.class);

    public BlockLogAbstract() {
        super(Material.WOOD);
        this.a(CreativeModeTab.b);
        this.c(2.0F);
        this.a(BlockLogAbstract.f);
    }

    public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
        byte b0 = 4;
        int i = b0 + 1;

        if (world.areChunksLoadedBetween(blockposition.a(-i, -i, -i), blockposition.a(i, i, i))) {
            Iterator iterator = BlockPosition.a(blockposition.a(-b0, -b0, -b0), blockposition.a(b0, b0, b0)).iterator();

            while (iterator.hasNext()) {
                BlockPosition blockposition1 = (BlockPosition) iterator.next();
                IBlockData iblockdata1 = world.getType(blockposition1);

                if (iblockdata1.getBlock().getMaterial() == Material.LEAVES && !((Boolean) iblockdata1.get(BlockLeaves.CHECK_DECAY)).booleanValue()) {
                    world.setTypeAndData(blockposition1, iblockdata1.set(BlockLeaves.CHECK_DECAY, Boolean.valueOf(true)), 4);
                }
            }

        }
    }

    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
        return super.getPlacedState(world, blockposition, enumdirection, f, f1, f2, i, entityliving).set(BlockLogAbstract.AXIS, EnumLogRotation.a(enumdirection.k()));
    }
}
