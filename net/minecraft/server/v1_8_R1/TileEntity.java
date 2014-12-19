package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.spigotmc.CustomTimingsHandler; // Spigot
import org.bukkit.inventory.InventoryHolder; // CraftBukkit

public abstract class TileEntity {

    public CustomTimingsHandler tickTimer = org.bukkit.craftbukkit.SpigotTimings.getTileEntityTimings(this); // Spigot
    private static final Logger a = LogManager.getLogger();
    private static Map f = Maps.newHashMap();
    private static Map g = Maps.newHashMap();
    protected World world;
    protected BlockPosition position;
    protected boolean d;
    private int h;
    protected Block e;

    // Spigot start
    // Helper method for scheduleTicks. If the hopper at x0, y0, z0 is pointed
    // at this tile entity, then make it active.
    private void scheduleTick(BlockPosition blockposition) {
        TileEntity tileEntity = this.world.getTileEntity(blockposition);
        if (tileEntity instanceof TileEntityHopper && tileEntity.world != null) {
            // i is the metadeta assoiated with the direction the hopper faces.
            EnumDirection dir = BlockHopper.b(tileEntity.u());
            
            // Facing class provides arrays for direction offset.
            if (tileEntity.position.shift(dir).equals(position)) {
                ((TileEntityHopper) tileEntity).makeTick();
            }
        }
    }
    
    // Called from update when the contents have changed, so hoppers need updates.
    // Check all 6 faces.
    public void scheduleTicks() {
        if (this.world != null && this.world.spigotConfig.altHopperTicking) {
            // Check the top
            this.scheduleTick(position.up());
            // Check the sides
            for (int i = 2; i < 6; i++) {
                this.scheduleTick(position.shift(EnumDirection.fromType1(i)));
            }
            // Check the bottom.
            TileEntity tileEntity = this.world.getTileEntity(position.down());
            if (tileEntity instanceof TileEntityHopper && tileEntity.world != null) {
                ((TileEntityHopper) tileEntity).makeTick();
            }
        }
    }
    // Spigot end

    public TileEntity() {
        this.position = BlockPosition.ZERO;
        this.h = -1;
    }

    private static void a(Class oclass, String s) {
        if (TileEntity.f.containsKey(s)) {
            throw new IllegalArgumentException("Duplicate id: " + s);
        } else {
            TileEntity.f.put(s, oclass);
            TileEntity.g.put(oclass, s);
        }
    }

    public World getWorld() {
        return this.world;
    }

    public void a(World world) {
        this.world = world;
    }

    public boolean t() {
        return this.world != null;
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.position = new BlockPosition(nbttagcompound.getInt("x"), nbttagcompound.getInt("y"), nbttagcompound.getInt("z"));
    }

    public void b(NBTTagCompound nbttagcompound) {
        String s = (String) TileEntity.g.get(this.getClass());

        if (s == null) {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        } else {
            nbttagcompound.setString("id", s);
            nbttagcompound.setInt("x", this.position.getX());
            nbttagcompound.setInt("y", this.position.getY());
            nbttagcompound.setInt("z", this.position.getZ());
        }
    }

    public static TileEntity c(NBTTagCompound nbttagcompound) {
        TileEntity tileentity = null;

        try {
            Class oclass = (Class) TileEntity.f.get(nbttagcompound.getString("id"));

            if (oclass != null) {
                tileentity = (TileEntity) oclass.newInstance();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (tileentity != null) {
            tileentity.a(nbttagcompound);
        } else {
            TileEntity.a.warn("Skipping BlockEntity with id " + nbttagcompound.getString("id"));
        }

        return tileentity;
    }

    public int u() {
        if (this.h == -1) {
            IBlockData iblockdata = this.world.getType(this.position);

            this.h = iblockdata.getBlock().toLegacyData(iblockdata);
        }

        return this.h;
    }

    public void update() {
        if (this.world != null) {
            IBlockData iblockdata = this.world.getType(this.position);

            this.h = iblockdata.getBlock().toLegacyData(iblockdata);
            this.world.b(this.position, this);
            if (this.w() != Blocks.AIR) {
                this.world.updateAdjacentComparators(this.position, this.w());
            }
            // Spigot start - Called when the contents have changed, so hoppers around this
            // tile need updating.
            this.scheduleTicks();
            // Spigot end
        }

    }

    public BlockPosition getPosition() {
        return this.position;
    }

    public Block w() {
        if (this.e == null) {
            this.e = this.world.getType(this.position).getBlock();
        }

        return this.e;
    }

    public Packet getUpdatePacket() {
        return null;
    }

    public boolean x() {
        return this.d;
    }

    public void y() {
        this.d = true;
    }

    public void D() {
        this.d = false;
    }

    public boolean c(int i, int j) {
        return false;
    }

    public void E() {
        this.e = null;
        this.h = -1;
    }

    public void a(CrashReportSystemDetails crashreportsystemdetails) {
        crashreportsystemdetails.a("Name", (Callable) (new CrashReportTileEntityName(this)));
        if (this.world != null) {
            CrashReportSystemDetails.a(crashreportsystemdetails, this.position, this.w(), this.u());
            crashreportsystemdetails.a("Actual block type", (Callable) (new CrashReportTileEntityType(this)));
            crashreportsystemdetails.a("Actual block data value", (Callable) (new CrashReportTileEntityData(this)));
        }
    }

    public void a(BlockPosition blockposition) {
        this.position = blockposition;
    }

    static Map F() {
        return TileEntity.g;
    }

    static {
        a(TileEntityFurnace.class, "Furnace");
        a(TileEntityChest.class, "Chest");
        a(TileEntityEnderChest.class, "EnderChest");
        a(TileEntityRecordPlayer.class, "RecordPlayer");
        a(TileEntityDispenser.class, "Trap");
        a(TileEntityDropper.class, "Dropper");
        a(TileEntitySign.class, "Sign");
        a(TileEntityMobSpawner.class, "MobSpawner");
        a(TileEntityNote.class, "Music");
        a(TileEntityPiston.class, "Piston");
        a(TileEntityBrewingStand.class, "Cauldron");
        a(TileEntityEnchantTable.class, "EnchantTable");
        a(TileEntityEnderPortal.class, "Airportal");
        a(TileEntityCommand.class, "Control");
        a(TileEntityBeacon.class, "Beacon");
        a(TileEntitySkull.class, "Skull");
        a(TileEntityLightDetector.class, "DLDetector");
        a(TileEntityHopper.class, "Hopper");
        a(TileEntityComparator.class, "Comparator");
        a(TileEntityFlowerPot.class, "FlowerPot");
        a(TileEntityBanner.class, "Banner");
    }

    // CraftBukkit start - add method
    public InventoryHolder getOwner() {
        // Spigot start
        org.bukkit.block.Block block = world.getWorld().getBlockAt(position.getX(), position.getY(), position.getZ());
        if (block == null) {
            org.bukkit.Bukkit.getLogger().log(java.util.logging.Level.WARNING, "No block for owner at %s %d %d %d", new Object[]{world.getWorld(), position.getX(), position.getY(), position.getZ()});
            return null;
        }
        // Spigot end
        org.bukkit.block.BlockState state = block.getState();
        if (state instanceof InventoryHolder) return (InventoryHolder) state;
        return null;
    }
    // CraftBukkit end
}
