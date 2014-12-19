package net.minecraft.server;

import java.util.Random;

public class BlockFlowerPot extends BlockContainer {

    public static final BlockStateInteger LEGACY_DATA = BlockStateInteger.of("legacy_data", 0, 15);
    public static final BlockStateEnum CONTENTS = BlockStateEnum.of("contents", EnumFlowerPotContents.class);

    public BlockFlowerPot() {
        super(Material.ORIENTABLE);
        this.j(this.blockStateList.getBlockData().set(BlockFlowerPot.CONTENTS, EnumFlowerPotContents.EMPTY).set(BlockFlowerPot.LEGACY_DATA, Integer.valueOf(0)));
        this.h();
    }

    public void h() {
        float f = 0.375F;
        float f1 = f / 2.0F;

        this.a(0.5F - f1, 0.0F, 0.5F - f1, 0.5F + f1, f, 0.5F + f1);
    }

    public boolean c() {
        return false;
    }

    public int b() {
        return 3;
    }

    public boolean d() {
        return false;
    }

    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumDirection enumdirection, float f, float f1, float f2) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (itemstack != null && itemstack.getItem() instanceof ItemBlock) {
            TileEntityFlowerPot tileentityflowerpot = this.d(world, blockposition);

            if (tileentityflowerpot == null) {
                return false;
            } else if (tileentityflowerpot.b() != null) {
                return false;
            } else {
                Block block = Block.asBlock(itemstack.getItem());

                if (!this.a(block, itemstack.getData())) {
                    return false;
                } else {
                    tileentityflowerpot.a(itemstack.getItem(), itemstack.getData());
                    tileentityflowerpot.update();
                    world.notify(blockposition);
                    if (!entityhuman.abilities.canInstantlyBuild && --itemstack.count <= 0) {
                        entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
                    }

                    return true;
                }
            }
        } else {
            return false;
        }
    }

    private boolean a(Block block, int i) {
        return block != Blocks.YELLOW_FLOWER && block != Blocks.RED_FLOWER && block != Blocks.CACTUS && block != Blocks.BROWN_MUSHROOM && block != Blocks.RED_MUSHROOM && block != Blocks.SAPLING && block != Blocks.DEADBUSH ? block == Blocks.TALLGRASS && i == EnumTallGrassType.FERN.a() : true;
    }

    public int getDropData(World world, BlockPosition blockposition) {
        TileEntityFlowerPot tileentityflowerpot = this.d(world, blockposition);

        return tileentityflowerpot != null && tileentityflowerpot.b() != null ? tileentityflowerpot.c() : 0;
    }

    public boolean canPlace(World world, BlockPosition blockposition) {
        return super.canPlace(world, blockposition) && World.a((IBlockAccess) world, blockposition.down());
    }

    public void doPhysics(World world, BlockPosition blockposition, IBlockData iblockdata, Block block) {
        if (!World.a((IBlockAccess) world, blockposition.down())) {
            this.b(world, blockposition, iblockdata, 0);
            world.setAir(blockposition);
        }

    }

    public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
        TileEntityFlowerPot tileentityflowerpot = this.d(world, blockposition);

        if (tileentityflowerpot != null && tileentityflowerpot.b() != null) {
            a(world, blockposition, new ItemStack(tileentityflowerpot.b(), 1, tileentityflowerpot.c()));
            tileentityflowerpot.a( null, 0 ); // Spigot
        }

        super.remove(world, blockposition, iblockdata);
    }

    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
        super.a(world, blockposition, iblockdata, entityhuman);
        if (entityhuman.abilities.canInstantlyBuild) {
            TileEntityFlowerPot tileentityflowerpot = this.d(world, blockposition);

            if (tileentityflowerpot != null) {
                tileentityflowerpot.a((Item) null, 0);
            }
        }

    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Items.FLOWER_POT;
    }

    private TileEntityFlowerPot d(World world, BlockPosition blockposition) {
        TileEntity tileentity = world.getTileEntity(blockposition);

        return tileentity instanceof TileEntityFlowerPot ? (TileEntityFlowerPot) tileentity : null;
    }

    public TileEntity a(World world, int i) {
        Object object = null;
        int j = 0;

        switch (i) {
        case 1:
            object = Blocks.RED_FLOWER;
            j = EnumFlowerVarient.POPPY.b();
            break;

        case 2:
            object = Blocks.YELLOW_FLOWER;
            break;

        case 3:
            object = Blocks.SAPLING;
            j = EnumLogVariant.OAK.a();
            break;

        case 4:
            object = Blocks.SAPLING;
            j = EnumLogVariant.SPRUCE.a();
            break;

        case 5:
            object = Blocks.SAPLING;
            j = EnumLogVariant.BIRCH.a();
            break;

        case 6:
            object = Blocks.SAPLING;
            j = EnumLogVariant.JUNGLE.a();
            break;

        case 7:
            object = Blocks.RED_MUSHROOM;
            break;

        case 8:
            object = Blocks.BROWN_MUSHROOM;
            break;

        case 9:
            object = Blocks.CACTUS;
            break;

        case 10:
            object = Blocks.DEADBUSH;
            break;

        case 11:
            object = Blocks.TALLGRASS;
            j = EnumTallGrassType.FERN.a();
            break;

        case 12:
            object = Blocks.SAPLING;
            j = EnumLogVariant.ACACIA.a();
            break;

        case 13:
            object = Blocks.SAPLING;
            j = EnumLogVariant.DARK_OAK.a();
        }

        return new TileEntityFlowerPot(Item.getItemOf((Block) object), j);
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockFlowerPot.CONTENTS, BlockFlowerPot.LEGACY_DATA});
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((Integer) iblockdata.get(BlockFlowerPot.LEGACY_DATA)).intValue();
    }

    public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        EnumFlowerPotContents enumflowerpotcontents = EnumFlowerPotContents.EMPTY;
        TileEntity tileentity = iblockaccess.getTileEntity(blockposition);

        if (tileentity instanceof TileEntityFlowerPot) {
            TileEntityFlowerPot tileentityflowerpot = (TileEntityFlowerPot) tileentity;
            Item item = tileentityflowerpot.b();

            if (item instanceof ItemBlock) {
                int i = tileentityflowerpot.c();
                Block block = Block.asBlock(item);

                if (block == Blocks.SAPLING) {
                    switch (SwitchHelperBlockFlowerPot.a[EnumLogVariant.a(i).ordinal()]) {
                    case 1:
                        enumflowerpotcontents = EnumFlowerPotContents.OAK_SAPLING;
                        break;

                    case 2:
                        enumflowerpotcontents = EnumFlowerPotContents.SPRUCE_SAPLING;
                        break;

                    case 3:
                        enumflowerpotcontents = EnumFlowerPotContents.BIRCH_SAPLING;
                        break;

                    case 4:
                        enumflowerpotcontents = EnumFlowerPotContents.JUNGLE_SAPLING;
                        break;

                    case 5:
                        enumflowerpotcontents = EnumFlowerPotContents.ACACIA_SAPLING;
                        break;

                    case 6:
                        enumflowerpotcontents = EnumFlowerPotContents.DARK_OAK_SAPLING;
                        break;

                    default:
                        enumflowerpotcontents = EnumFlowerPotContents.EMPTY;
                    }
                } else if (block == Blocks.TALLGRASS) {
                    switch (i) {
                    case 0:
                        enumflowerpotcontents = EnumFlowerPotContents.DEAD_BUSH;
                        break;

                    case 2:
                        enumflowerpotcontents = EnumFlowerPotContents.FERN;
                        break;

                    default:
                        enumflowerpotcontents = EnumFlowerPotContents.EMPTY;
                    }
                } else if (block == Blocks.YELLOW_FLOWER) {
                    enumflowerpotcontents = EnumFlowerPotContents.DANDELION;
                } else if (block == Blocks.RED_FLOWER) {
                    switch (SwitchHelperBlockFlowerPot.b[EnumFlowerVarient.a(EnumFlowerType.RED, i).ordinal()]) {
                    case 1:
                        enumflowerpotcontents = EnumFlowerPotContents.POPPY;
                        break;

                    case 2:
                        enumflowerpotcontents = EnumFlowerPotContents.BLUE_ORCHID;
                        break;

                    case 3:
                        enumflowerpotcontents = EnumFlowerPotContents.ALLIUM;
                        break;

                    case 4:
                        enumflowerpotcontents = EnumFlowerPotContents.HOUSTONIA;
                        break;

                    case 5:
                        enumflowerpotcontents = EnumFlowerPotContents.RED_TULIP;
                        break;

                    case 6:
                        enumflowerpotcontents = EnumFlowerPotContents.ORANGE_TULIP;
                        break;

                    case 7:
                        enumflowerpotcontents = EnumFlowerPotContents.WHITE_TULIP;
                        break;

                    case 8:
                        enumflowerpotcontents = EnumFlowerPotContents.PINK_TULIP;
                        break;

                    case 9:
                        enumflowerpotcontents = EnumFlowerPotContents.OXEYE_DAISY;
                        break;

                    default:
                        enumflowerpotcontents = EnumFlowerPotContents.EMPTY;
                    }
                } else if (block == Blocks.RED_MUSHROOM) {
                    enumflowerpotcontents = EnumFlowerPotContents.MUSHROOM_RED;
                } else if (block == Blocks.BROWN_MUSHROOM) {
                    enumflowerpotcontents = EnumFlowerPotContents.MUSHROOM_BROWN;
                } else if (block == Blocks.DEADBUSH) {
                    enumflowerpotcontents = EnumFlowerPotContents.DEAD_BUSH;
                } else if (block == Blocks.CACTUS) {
                    enumflowerpotcontents = EnumFlowerPotContents.CACTUS;
                }
            }
        }

        return iblockdata.set(BlockFlowerPot.CONTENTS, enumflowerpotcontents);
    }

}
