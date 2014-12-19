package net.minecraft.server;

public abstract class BlockMinecartTrackAbstract extends Block {

    protected final boolean a;

    public static boolean d(World world, BlockPosition blockposition) {
        return d(world.getType(blockposition));
    }

    public static boolean d(IBlockData iblockdata) {
        Block block = iblockdata.getBlock();

        return block == Blocks.RAIL || block == Blocks.GOLDEN_RAIL || block == Blocks.DETECTOR_RAIL || block == Blocks.ACTIVATOR_RAIL;
    }

    protected BlockMinecartTrackAbstract(boolean flag) {
        super(Material.ORIENTABLE);
        this.a = flag;
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.a(CreativeModeTab.e);
    }

    public AxisAlignedBB a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return null;
    }

    public boolean c() {
        return false;
    }

    public MovingObjectPosition a(World world, BlockPosition blockposition, Vec3D vec3d, Vec3D vec3d1) {
        this.updateShape(world, blockposition);
        return super.a(world, blockposition, vec3d, vec3d1);
    }

    public void updateShape(IBlockAccess iblockaccess, BlockPosition blockposition) {
        IBlockData iblockdata = iblockaccess.getType(blockposition);
        EnumTrackPosition enumtrackposition = iblockdata.getBlock() == this ? (EnumTrackPosition) iblockdata.get(this.l()) : null;

        if (enumtrackposition != null && enumtrackposition.c()) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        }

    }

    public boolean d() {
        return false;
    }

    public boolean canPlace(World world, BlockPosition blockposition) {
        return World.a((IBlockAccess) world, blockposition.down());
    }

    public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
        if (!world.isStatic) {
            iblockdata = this.a(world, blockposition, iblockdata, true);
            if (this.a) {
                this.doPhysics(world, blockposition, iblockdata, this);
            }
        }

    }

    public void doPhysics(World world, BlockPosition blockposition, IBlockData iblockdata, Block block) {
        if (!world.isStatic) {
            EnumTrackPosition enumtrackposition = (EnumTrackPosition) iblockdata.get(this.l());
            boolean flag = false;

            if (!World.a((IBlockAccess) world, blockposition.down())) {
                flag = true;
            }

            if (enumtrackposition == EnumTrackPosition.ASCENDING_EAST && !World.a((IBlockAccess) world, blockposition.east())) {
                flag = true;
            } else if (enumtrackposition == EnumTrackPosition.ASCENDING_WEST && !World.a((IBlockAccess) world, blockposition.west())) {
                flag = true;
            } else if (enumtrackposition == EnumTrackPosition.ASCENDING_NORTH && !World.a((IBlockAccess) world, blockposition.north())) {
                flag = true;
            } else if (enumtrackposition == EnumTrackPosition.ASCENDING_SOUTH && !World.a((IBlockAccess) world, blockposition.south())) {
                flag = true;
            }

            if (flag) {
                this.b(world, blockposition, iblockdata, 0);
                world.setAir(blockposition);
            } else {
                this.b(world, blockposition, iblockdata, block);
            }

        }
    }

    protected void b(World world, BlockPosition blockposition, IBlockData iblockdata, Block block) {}

    protected IBlockData a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
        return world.isStatic ? iblockdata : (new MinecartTrackLogic(this, world, blockposition, iblockdata)).a(world.isBlockIndirectlyPowered(blockposition), flag).b();
    }

    public int i() {
        return 0;
    }

    public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
        super.remove(world, blockposition, iblockdata);
        if (((EnumTrackPosition) iblockdata.get(this.l())).c()) {
            world.applyPhysics(blockposition.up(), this);
        }

        if (this.a) {
            world.applyPhysics(blockposition, this);
            world.applyPhysics(blockposition.down(), this);
        }

    }

    public abstract IBlockState l();
}
