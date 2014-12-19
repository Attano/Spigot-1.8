package net.minecraft.server;

import com.google.common.base.Predicate;
import java.util.List;
import java.util.Random;

import org.bukkit.event.block.BlockRedstoneEvent; // CraftBukkit

public class BlockMinecartDetector extends BlockMinecartTrackAbstract {

    public static final BlockStateEnum SHAPE = BlockStateEnum.a("shape", EnumTrackPosition.class, (Predicate) (new BlockMinecartDetectorInnerClass1()));
    public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");

    public BlockMinecartDetector() {
        super(true);
        this.j(this.blockStateList.getBlockData().set(BlockMinecartDetector.POWERED, Boolean.valueOf(false)).set(BlockMinecartDetector.SHAPE, EnumTrackPosition.NORTH_SOUTH));
        this.a(true);
    }

    public int a(World world) {
        return 20;
    }

    public boolean isPowerSource() {
        return true;
    }

    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
        if (!world.isStatic) {
            if (!((Boolean) iblockdata.get(BlockMinecartDetector.POWERED)).booleanValue()) {
                this.e(world, blockposition, iblockdata);
            }
        }
    }

    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {}

    public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
        if (!world.isStatic && ((Boolean) iblockdata.get(BlockMinecartDetector.POWERED)).booleanValue()) {
            this.e(world, blockposition, iblockdata);
        }
    }

    public int a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, EnumDirection enumdirection) {
        return ((Boolean) iblockdata.get(BlockMinecartDetector.POWERED)).booleanValue() ? 15 : 0;
    }

    public int b(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, EnumDirection enumdirection) {
        return !((Boolean) iblockdata.get(BlockMinecartDetector.POWERED)).booleanValue() ? 0 : (enumdirection == EnumDirection.UP ? 15 : 0);
    }

    private void e(World world, BlockPosition blockposition, IBlockData iblockdata) {
        boolean flag = ((Boolean) iblockdata.get(BlockMinecartDetector.POWERED)).booleanValue();
        boolean flag1 = false;
        List list = this.a(world, blockposition, EntityMinecartAbstract.class, new Predicate[0]);

        if (!list.isEmpty()) {
            flag1 = true;
        }
        
       // CraftBukkit start
        if (flag != flag1) {
            org.bukkit.block.Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());

            BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, flag ? 15 : 0, flag1 ? 15 : 0);
            world.getServer().getPluginManager().callEvent(eventRedstone);

            flag1 = eventRedstone.getNewCurrent() > 0;
        }
        // CraftBukkit end

        if (flag1 && !flag) {
            world.setTypeAndData(blockposition, iblockdata.set(BlockMinecartDetector.POWERED, Boolean.valueOf(true)), 3);
            world.applyPhysics(blockposition, this);
            world.applyPhysics(blockposition.down(), this);
            world.b(blockposition, blockposition);
        }

        if (!flag1 && flag) {
            world.setTypeAndData(blockposition, iblockdata.set(BlockMinecartDetector.POWERED, Boolean.valueOf(false)), 3);
            world.applyPhysics(blockposition, this);
            world.applyPhysics(blockposition.down(), this);
            world.b(blockposition, blockposition);
        }

        if (flag1) {
            world.a(blockposition, (Block) this, this.a(world));
        }

        world.updateAdjacentComparators(blockposition, this);
    }

    public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
        super.onPlace(world, blockposition, iblockdata);
        this.e(world, blockposition, iblockdata);
    }

    public IBlockState l() {
        return BlockMinecartDetector.SHAPE;
    }

    public boolean isComplexRedstone() {
        return true;
    }

    public int l(World world, BlockPosition blockposition) {
        if (((Boolean) world.getType(blockposition).get(BlockMinecartDetector.POWERED)).booleanValue()) {
            List list = this.a(world, blockposition, EntityMinecartCommandBlock.class, new Predicate[0]);

            if (!list.isEmpty()) {
                return ((EntityMinecartCommandBlock) list.get(0)).getCommandBlock().j();
            }

            List list1 = this.a(world, blockposition, EntityMinecartAbstract.class, new Predicate[] { IEntitySelector.c});

            if (!list1.isEmpty()) {
                return Container.b((IInventory) list1.get(0));
            }
        }

        return 0;
    }

    protected List a(World world, BlockPosition blockposition, Class oclass, Predicate... apredicate) {
        AxisAlignedBB axisalignedbb = this.a(blockposition);

        return apredicate.length != 1 ? world.a(oclass, axisalignedbb) : world.a(oclass, axisalignedbb, apredicate[0]);
    }

    private AxisAlignedBB a(BlockPosition blockposition) {
        float f = 0.2F;

        return new AxisAlignedBB((double) ((float) blockposition.getX() + 0.2F), (double) blockposition.getY(), (double) ((float) blockposition.getZ() + 0.2F), (double) ((float) (blockposition.getX() + 1) - 0.2F), (double) ((float) (blockposition.getY() + 1) - 0.2F), (double) ((float) (blockposition.getZ() + 1) - 0.2F));
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockMinecartDetector.SHAPE, EnumTrackPosition.a(i & 7)).set(BlockMinecartDetector.POWERED, Boolean.valueOf((i & 8) > 0));
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((EnumTrackPosition) iblockdata.get(BlockMinecartDetector.SHAPE)).a();

        if (((Boolean) iblockdata.get(BlockMinecartDetector.POWERED)).booleanValue()) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockMinecartDetector.SHAPE, BlockMinecartDetector.POWERED});
    }
}
