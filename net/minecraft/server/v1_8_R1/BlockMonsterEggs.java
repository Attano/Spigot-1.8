package net.minecraft.server;

import java.util.Random;

import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason; // CraftBukkit

public class BlockMonsterEggs extends Block {

    public static final BlockStateEnum VARIANT = BlockStateEnum.of("variant", EnumMonsterEggVarient.class);

    public BlockMonsterEggs() {
        super(Material.CLAY);
        this.j(this.blockStateList.getBlockData().set(BlockMonsterEggs.VARIANT, EnumMonsterEggVarient.STONE));
        this.c(0.0F);
        this.a(CreativeModeTab.c);
    }

    public int a(Random random) {
        return 0;
    }

    public static boolean d(IBlockData iblockdata) {
        Block block = iblockdata.getBlock();

        return iblockdata == Blocks.STONE.getBlockData().set(BlockStone.VARIANT, EnumStoneVariant.STONE) || block == Blocks.COBBLESTONE || block == Blocks.STONEBRICK;
    }

    protected ItemStack i(IBlockData iblockdata) {
        switch (SwitchHelperMonsterEggVarient.a[((EnumMonsterEggVarient) iblockdata.get(BlockMonsterEggs.VARIANT)).ordinal()]) {
        case 1:
            return new ItemStack(Blocks.COBBLESTONE);

        case 2:
            return new ItemStack(Blocks.STONEBRICK);

        case 3:
            return new ItemStack(Blocks.STONEBRICK, 1, EnumStonebrickType.MOSSY.a());

        case 4:
            return new ItemStack(Blocks.STONEBRICK, 1, EnumStonebrickType.CRACKED.a());

        case 5:
            return new ItemStack(Blocks.STONEBRICK, 1, EnumStonebrickType.CHISELED.a());

        default:
            return new ItemStack(Blocks.STONE);
        }
    }

    public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
        if (!world.isStatic && world.getGameRules().getBoolean("doTileDrops")) {
            EntitySilverfish entitysilverfish = new EntitySilverfish(world);

            entitysilverfish.setPositionRotation((double) blockposition.getX() + 0.5D, (double) blockposition.getY(), (double) blockposition.getZ() + 0.5D, 0.0F, 0.0F);
            world.addEntity(entitysilverfish, SpawnReason.SILVERFISH_BLOCK); // CraftBukkit - add SpawnReason
            entitysilverfish.y();
        }

    }

    public int getDropData(World world, BlockPosition blockposition) {
        IBlockData iblockdata = world.getType(blockposition);

        return iblockdata.getBlock().toLegacyData(iblockdata);
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockMonsterEggs.VARIANT, EnumMonsterEggVarient.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumMonsterEggVarient) iblockdata.get(BlockMonsterEggs.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockMonsterEggs.VARIANT});
    }
}
