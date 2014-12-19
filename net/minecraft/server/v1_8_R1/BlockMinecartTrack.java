package net.minecraft.server;

public class BlockMinecartTrack extends BlockMinecartTrackAbstract {

    public static final BlockStateEnum SHAPE = BlockStateEnum.of("shape", EnumTrackPosition.class);

    protected BlockMinecartTrack() {
        super(false);
        this.j(this.blockStateList.getBlockData().set(BlockMinecartTrack.SHAPE, EnumTrackPosition.NORTH_SOUTH));
    }

    protected void b(World world, BlockPosition blockposition, IBlockData iblockdata, Block block) {
        if (block.isPowerSource() && (new MinecartTrackLogic(this, world, blockposition, iblockdata)).a() == 3) {
            this.a(world, blockposition, iblockdata, false);
        }

    }

    public IBlockState l() {
        return BlockMinecartTrack.SHAPE;
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockMinecartTrack.SHAPE, EnumTrackPosition.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumTrackPosition) iblockdata.get(BlockMinecartTrack.SHAPE)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockMinecartTrack.SHAPE});
    }
}
