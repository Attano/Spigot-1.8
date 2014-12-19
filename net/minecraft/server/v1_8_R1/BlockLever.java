package net.minecraft.server;

import java.util.Iterator;

import org.bukkit.event.block.BlockRedstoneEvent; // CraftBukkit

public class BlockLever extends Block {

    public static final BlockStateEnum FACING = BlockStateEnum.of("facing", EnumLeverPosition.class);
    public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");

    protected BlockLever() {
        super(Material.ORIENTABLE);
        this.j(this.blockStateList.getBlockData().set(BlockLever.FACING, EnumLeverPosition.NORTH).set(BlockLever.POWERED, Boolean.valueOf(false)));
        this.a(CreativeModeTab.d);
    }

    public AxisAlignedBB a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return null;
    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public boolean canPlace(World world, BlockPosition blockposition, EnumDirection enumdirection) {
        return enumdirection == EnumDirection.UP && World.a((IBlockAccess) world, blockposition.down()) ? true : this.d(world, blockposition.shift(enumdirection.opposite()));
    }

    public boolean canPlace(World world, BlockPosition blockposition) {
        return this.d(world, blockposition.west()) ? true : (this.d(world, blockposition.east()) ? true : (this.d(world, blockposition.north()) ? true : (this.d(world, blockposition.south()) ? true : (World.a((IBlockAccess) world, blockposition.down()) ? true : this.d(world, blockposition.up())))));
    }

    protected boolean d(World world, BlockPosition blockposition) {
        return world.getType(blockposition).getBlock().isOccluding();
    }

    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
        IBlockData iblockdata = this.getBlockData().set(BlockLever.POWERED, Boolean.valueOf(false));

        if (this.d(world, blockposition.shift(enumdirection.opposite()))) {
            return iblockdata.set(BlockLever.FACING, EnumLeverPosition.a(enumdirection, entityliving.getDirection()));
        } else {
            Iterator iterator = EnumDirectionLimit.HORIZONTAL.iterator();

            EnumDirection enumdirection1;

            do {
                if (!iterator.hasNext()) {
                    if (World.a((IBlockAccess) world, blockposition.down())) {
                        return iblockdata.set(BlockLever.FACING, EnumLeverPosition.a(EnumDirection.UP, entityliving.getDirection()));
                    }

                    return iblockdata;
                }

                enumdirection1 = (EnumDirection) iterator.next();
            } while (enumdirection1 == enumdirection || !this.d(world, blockposition.shift(enumdirection1.opposite())));

            return iblockdata.set(BlockLever.FACING, EnumLeverPosition.a(enumdirection1, entityliving.getDirection()));
        }
    }

    public static int a(EnumDirection enumdirection) {
        switch (SwitchHelperBlockLever.a[enumdirection.ordinal()]) {
        case 1:
            return 0;

        case 2:
            return 5;

        case 3:
            return 4;

        case 4:
            return 3;

        case 5:
            return 2;

        case 6:
            return 1;

        default:
            return -1;
        }
    }

    public void doPhysics(World world, BlockPosition blockposition, IBlockData iblockdata, Block block) {
        if (this.e(world, blockposition) && !this.d(world, blockposition.shift(((EnumLeverPosition) iblockdata.get(BlockLever.FACING)).c().opposite()))) {
            this.b(world, blockposition, iblockdata, 0);
            world.setAir(blockposition);
        }

    }

    private boolean e(World world, BlockPosition blockposition) {
        if (this.canPlace(world, blockposition)) {
            return true;
        } else {
            this.b(world, blockposition, world.getType(blockposition), 0);
            world.setAir(blockposition);
            return false;
        }
    }

    public void updateShape(IBlockAccess iblockaccess, BlockPosition blockposition) {
        float f = 0.1875F;

        switch (SwitchHelperBlockLever.b[((EnumLeverPosition) iblockaccess.getType(blockposition).get(BlockLever.FACING)).ordinal()]) {
        case 1:
            this.a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
            break;

        case 2:
            this.a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
            break;

        case 3:
            this.a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
            break;

        case 4:
            this.a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
            break;

        case 5:
        case 6:
            f = 0.25F;
            this.a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
            break;

        case 7:
        case 8:
            f = 0.25F;
            this.a(0.5F - f, 0.4F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
        }

    }

    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumDirection enumdirection, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            // CraftBukkit start - Interact Lever
            boolean powered = (Boolean)iblockdata.get(POWERED);
            org.bukkit.block.Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
            int old = (powered) ? 15 : 0;
            int current = (!powered) ? 15 : 0;

            BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, old, current);
            world.getServer().getPluginManager().callEvent(eventRedstone);

            if ((eventRedstone.getNewCurrent() > 0) != (!powered)) {
                return true;
            }
            // CraftBukkit end

            iblockdata = iblockdata.a(BlockLever.POWERED);
            world.setTypeAndData(blockposition, iblockdata, 3);
            world.makeSound((double) blockposition.getX() + 0.5D, (double) blockposition.getY() + 0.5D, (double) blockposition.getZ() + 0.5D, "random.click", 0.3F, ((Boolean) iblockdata.get(BlockLever.POWERED)).booleanValue() ? 0.6F : 0.5F);
            world.applyPhysics(blockposition, this);
            EnumDirection enumdirection1 = ((EnumLeverPosition) iblockdata.get(BlockLever.FACING)).c();

            world.applyPhysics(blockposition.shift(enumdirection1.opposite()), this);
            return true;
        }
    }

    public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
        if (((Boolean) iblockdata.get(BlockLever.POWERED)).booleanValue()) {
            world.applyPhysics(blockposition, this);
            EnumDirection enumdirection = ((EnumLeverPosition) iblockdata.get(BlockLever.FACING)).c();

            world.applyPhysics(blockposition.shift(enumdirection.opposite()), this);
        }

        super.remove(world, blockposition, iblockdata);
    }

    public int a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, EnumDirection enumdirection) {
        return ((Boolean) iblockdata.get(BlockLever.POWERED)).booleanValue() ? 15 : 0;
    }

    public int b(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, EnumDirection enumdirection) {
        return !((Boolean) iblockdata.get(BlockLever.POWERED)).booleanValue() ? 0 : (((EnumLeverPosition) iblockdata.get(BlockLever.FACING)).c() == enumdirection ? 15 : 0);
    }

    public boolean isPowerSource() {
        return true;
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockLever.FACING, EnumLeverPosition.a(i & 7)).set(BlockLever.POWERED, Boolean.valueOf((i & 8) > 0));
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((EnumLeverPosition) iblockdata.get(BlockLever.FACING)).a();

        if (((Boolean) iblockdata.get(BlockLever.POWERED)).booleanValue()) {
            i |= 8;
        }

        return i;
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockLever.FACING, BlockLever.POWERED});
    }
}
