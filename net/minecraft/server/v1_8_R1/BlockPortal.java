package net.minecraft.server;

import java.util.Random;

import org.bukkit.event.entity.EntityPortalEnterEvent; // CraftBukkit

public class BlockPortal extends BlockHalfTransparent {

    public static final BlockStateEnum AXIS = BlockStateEnum.of("axis", EnumAxis.class, new EnumAxis[] { EnumAxis.X, EnumAxis.Z});

    public BlockPortal() {
        super(Material.PORTAL, false);
        this.j(this.blockStateList.getBlockData().set(BlockPortal.AXIS, EnumAxis.X));
        this.a(true);
    }

    public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
        super.b(world, blockposition, iblockdata, random);
        if (world.spigotConfig.enableZombiePigmenPortalSpawns && world.worldProvider.d() && world.getGameRules().getBoolean("doMobSpawning") && random.nextInt(2000) < world.getDifficulty().a()) { // Spigot
            int i = blockposition.getY();

            BlockPosition blockposition1;

            for (blockposition1 = blockposition; !World.a((IBlockAccess) world, blockposition1) && blockposition1.getY() > 0; blockposition1 = blockposition1.down()) {
                ;
            }

            if (i > 0 && !world.getType(blockposition1.up()).getBlock().isOccluding()) {
                // CraftBukkit - set spawn reason to NETHER_PORTAL
                Entity entity = ItemMonsterEgg.spawnCreature(world, 57, (double) blockposition1.getX() + 0.5D, (double) blockposition1.getY() + 1.1D, (double) blockposition1.getZ() + 0.5D, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.NETHER_PORTAL);

                if (entity != null) {
                    entity.portalCooldown = entity.ar();
                }
            }
        }

    }

    public AxisAlignedBB a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return null;
    }

    public void updateShape(IBlockAccess iblockaccess, BlockPosition blockposition) {
        EnumAxis enumaxis = (EnumAxis) iblockaccess.getType(blockposition).get(BlockPortal.AXIS);
        float f = 0.125F;
        float f1 = 0.125F;

        if (enumaxis == EnumAxis.X) {
            f = 0.5F;
        }

        if (enumaxis == EnumAxis.Z) {
            f1 = 0.5F;
        }

        this.a(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
    }

    public static int a(EnumAxis enumaxis) {
        return enumaxis == EnumAxis.X ? 1 : (enumaxis == EnumAxis.Z ? 2 : 0);
    }

    public boolean d() {
        return false;
    }

    public boolean d(World world, BlockPosition blockposition) {
        PortalCreator portalcreator = new PortalCreator(world, blockposition, EnumAxis.X);

        if (portalcreator.b() && PortalCreator.a(portalcreator) == 0) {
            // CraftBukkit start - return portalcreator
            return portalcreator.c();
            // return true;
        } else {
            PortalCreator portalcreator1 = new PortalCreator(world, blockposition, EnumAxis.Z);

            if (portalcreator1.b() && PortalCreator.a(portalcreator1) == 0) {
                return portalcreator1.c();
                // return true;
                // CraftBukkit end
            } else {
                return false;
            }
        }
    }

    public void doPhysics(World world, BlockPosition blockposition, IBlockData iblockdata, Block block) {
        EnumAxis enumaxis = (EnumAxis) iblockdata.get(BlockPortal.AXIS);
        PortalCreator portalcreator;

        if (enumaxis == EnumAxis.X) {
            portalcreator = new PortalCreator(world, blockposition, EnumAxis.X);
            if (!portalcreator.b() || PortalCreator.a(portalcreator) < PortalCreator.b(portalcreator) * PortalCreator.c(portalcreator)) {
                world.setTypeUpdate(blockposition, Blocks.AIR.getBlockData());
            }
        } else if (enumaxis == EnumAxis.Z) {
            portalcreator = new PortalCreator(world, blockposition, EnumAxis.Z);
            if (!portalcreator.b() || PortalCreator.a(portalcreator) < PortalCreator.b(portalcreator) * PortalCreator.c(portalcreator)) {
                world.setTypeUpdate(blockposition, Blocks.AIR.getBlockData());
            }
        }

    }

    public int a(Random random) {
        return 0;
    }

    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
        if (entity.vehicle == null && entity.passenger == null) {
            // CraftBukkit start - Entity in portal
            EntityPortalEnterEvent event = new EntityPortalEnterEvent(entity.getBukkitEntity(), new org.bukkit.Location(world.getWorld(), blockposition.getX(), blockposition.getY(), blockposition.getZ()));
            world.getServer().getPluginManager().callEvent(event);
            // CraftBukkit end
            entity.aq();
        }

    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockPortal.AXIS, (i & 3) == 2 ? EnumAxis.Z : EnumAxis.X);
    }

    public int toLegacyData(IBlockData iblockdata) {
        return a((EnumAxis) iblockdata.get(BlockPortal.AXIS));
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockPortal.AXIS});
    }
}
