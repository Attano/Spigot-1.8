package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockPistonExtension extends Block {

    public static final BlockStateDirection FACING = BlockStateDirection.of("facing");
    public static final BlockStateEnum TYPE = BlockStateEnum.of("type", EnumPistonType.class);
    public static final BlockStateBoolean SHORT = BlockStateBoolean.of("short");

    public BlockPistonExtension() {
        super(Material.PISTON);
        this.j(this.blockStateList.getBlockData().set(BlockPistonExtension.FACING, EnumDirection.NORTH).set(BlockPistonExtension.TYPE, EnumPistonType.DEFAULT).set(BlockPistonExtension.SHORT, Boolean.valueOf(false)));
        this.a(BlockPistonExtension.i);
        this.c(0.5F);
    }

    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
        if (entityhuman.abilities.canInstantlyBuild) {
            EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockPistonExtension.FACING);

            if (enumdirection != null) {
                BlockPosition blockposition1 = blockposition.shift(enumdirection.opposite());
                Block block = world.getType(blockposition1).getBlock();

                if (block == Blocks.PISTON || block == Blocks.STICKY_PISTON) {
                    world.setAir(blockposition1);
                }
            }
        }

        super.a(world, blockposition, iblockdata, entityhuman);
    }

    public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
        super.remove(world, blockposition, iblockdata);
        EnumDirection enumdirection = ((EnumDirection) iblockdata.get(BlockPistonExtension.FACING)).opposite();

        blockposition = blockposition.shift(enumdirection);
        IBlockData iblockdata1 = world.getType(blockposition);

        if ((iblockdata1.getBlock() == Blocks.PISTON || iblockdata1.getBlock() == Blocks.STICKY_PISTON) && ((Boolean) iblockdata1.get(BlockPiston.EXTENDED)).booleanValue()) {
            iblockdata1.getBlock().b(world, blockposition, iblockdata1, 0);
            world.setAir(blockposition);
        }

    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public boolean canPlace(World world, BlockPosition blockposition) {
        return false;
    }

    public boolean canPlace(World world, BlockPosition blockposition, EnumDirection enumdirection) {
        return false;
    }

    public int a(Random random) {
        return 0;
    }

    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        this.d(iblockdata);
        super.a(world, blockposition, iblockdata, axisalignedbb, list, entity);
        this.e(iblockdata);
        super.a(world, blockposition, iblockdata, axisalignedbb, list, entity);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    private void e(IBlockData iblockdata) {
        float f = 0.25F;
        float f1 = 0.375F;
        float f2 = 0.625F;
        float f3 = 0.25F;
        float f4 = 0.75F;

        switch (SwitchHelperDirection9.a[((EnumDirection) iblockdata.get(BlockPistonExtension.FACING)).ordinal()]) {
        case 1:
            this.a(0.375F, 0.25F, 0.375F, 0.625F, 1.0F, 0.625F);
            break;

        case 2:
            this.a(0.375F, 0.0F, 0.375F, 0.625F, 0.75F, 0.625F);
            break;

        case 3:
            this.a(0.25F, 0.375F, 0.25F, 0.75F, 0.625F, 1.0F);
            break;

        case 4:
            this.a(0.25F, 0.375F, 0.0F, 0.75F, 0.625F, 0.75F);
            break;

        case 5:
            this.a(0.375F, 0.25F, 0.25F, 0.625F, 0.75F, 1.0F);
            break;

        case 6:
            this.a(0.0F, 0.375F, 0.25F, 0.75F, 0.625F, 0.75F);
        }

    }

    public void updateShape(IBlockAccess iblockaccess, BlockPosition blockposition) {
        this.d(iblockaccess.getType(blockposition));
    }

    public void d(IBlockData iblockdata) {
        float f = 0.25F;
        EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockPistonExtension.FACING);

        if (enumdirection != null) {
            switch (SwitchHelperDirection9.a[enumdirection.ordinal()]) {
            case 1:
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
                break;

            case 2:
                this.a(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
                break;

            case 3:
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
                break;

            case 4:
                this.a(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
                break;

            case 5:
                this.a(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
                break;

            case 6:
                this.a(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }

        }
    }

    public void doPhysics(World world, BlockPosition blockposition, IBlockData iblockdata, Block block) {
        EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockPistonExtension.FACING);
        BlockPosition blockposition1 = blockposition.shift(enumdirection.opposite());
        IBlockData iblockdata1 = world.getType(blockposition1);

        if (iblockdata1.getBlock() != Blocks.PISTON && iblockdata1.getBlock() != Blocks.STICKY_PISTON) {
            world.setAir(blockposition);
        } else {
            iblockdata1.getBlock().doPhysics(world, blockposition1, iblockdata1, block);
        }

    }

    public static EnumDirection b(int i) {
        int j = i & 7;

        return j > 5 ? null : EnumDirection.fromType1(j);
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockPistonExtension.FACING, b(i)).set(BlockPistonExtension.TYPE, (i & 8) > 0 ? EnumPistonType.STICKY : EnumPistonType.DEFAULT);
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((EnumDirection) iblockdata.get(BlockPistonExtension.FACING)).a();

        if (iblockdata.get(BlockPistonExtension.TYPE) == EnumPistonType.STICKY) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockPistonExtension.FACING, BlockPistonExtension.TYPE, BlockPistonExtension.SHORT});
    }
}
