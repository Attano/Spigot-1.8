package net.minecraft.server;

import java.util.List;

// CraftBukkit start
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.Inventory;
// CraftBukkit end

public class TileEntityHopper extends TileEntityContainer implements IHopper, IUpdatePlayerListBox {

    private ItemStack[] items = new ItemStack[5];
    private String f;
    private int g = -1;

    // Spigot start
    private long nextTick = -1; // Next tick this hopper will be ticked.
    private long lastTick = -1; // Last tick this hopper was polled.
    
    // If this hopper is not cooling down, assaign a visible tick for next time.
    public void makeTick() {
        if (!this.n()) {
            this.d(0);
        }
    }
    
    // Contents changed, so make this hopper active.
    public void scheduleHopperTick() {
        if (this.world != null && this.world.spigotConfig.altHopperTicking) {
            this.makeTick();
        }
    }
    
	// Called after this hopper is assaigned a world or when altHopperTicking is turned
	// on from reload.
    public void convertToScheduling() {
    	// j is the cooldown in ticks
        this.d(this.g);
    }
    
    // Called when alt hopper ticking is turned off from the reload command
    public void convertToPolling() {
        long cooldownDiff;
        if (this.lastTick == this.world.getTime()) {
            cooldownDiff = this.nextTick - this.world.getTime();
        } else {
            cooldownDiff = this.nextTick - this.world.getTime() + 1;
        }
        this.d((int) Math.max(0, Math.min(cooldownDiff, Integer.MAX_VALUE)));
    }
    // Spigot end
    
    // CraftBukkit start - add fields and methods
    public List<HumanEntity> transaction = new java.util.ArrayList<HumanEntity>();
    private int maxStack = MAX_STACK;

    public ItemStack[] getContents() {
        return this.items;
    }

    public void onOpen(CraftHumanEntity who) {
        transaction.add(who);
    }

    public void onClose(CraftHumanEntity who) {
        transaction.remove(who);
    }

    public List<HumanEntity> getViewers() {
        return transaction;
    }

    public void setMaxStackSize(int size) {
        maxStack = size;
    }
    // CraftBukkit end    

    public TileEntityHopper() {}

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getList("Items", 10);

        this.items = new ItemStack[this.getSize()];
        if (nbttagcompound.hasKeyOfType("CustomName", 8)) {
            this.f = nbttagcompound.getString("CustomName");
        }

        this.g = nbttagcompound.getInt("TransferCooldown");

        for (int i = 0; i < nbttaglist.size(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.get(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.items.length) {
                this.items[b0] = ItemStack.createStack(nbttagcompound1);
            }
        }

    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.setByte("Slot", (byte) i);
                this.items[i].save(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
            }
        }

        nbttagcompound.set("Items", nbttaglist);
        // Spigot start - Need to write the correct cooldown to disk. We convert from long to int on saving.
        if (this.world != null && this.world.spigotConfig.altHopperTicking) {
            long cooldownDiff;
            if (this.lastTick == this.world.getTime()) {
                cooldownDiff = this.nextTick - this.world.getTime();
            } else {
                cooldownDiff = this.nextTick - this.world.getTime() + 1;
            }
            nbttagcompound.setInt("TransferCooldown", (int) Math.max(0, Math.min(cooldownDiff, Integer.MAX_VALUE)));
        } else {
        	// g is the cooldown in ticks.
            nbttagcompound.setInt("TransferCooldown", this.g);
        }
        // Spigot end
        if (this.hasCustomName()) {
            nbttagcompound.setString("CustomName", this.f);
        }

    }

    public void update() {
        super.update();
        // Spigot start - The contents have changed, so make this hopper active.
        this.scheduleHopperTick();
        // Spigot end
    }

    public int getSize() {
        return this.items.length;
    }

    public ItemStack getItem(int i) {
        return this.items[i];
    }

    public ItemStack splitStack(int i, int j) {
        if (this.items[i] != null) {
            ItemStack itemstack;

            if (this.items[i].count <= j) {
                itemstack = this.items[i];
                this.items[i] = null;
                return itemstack;
            } else {
                itemstack = this.items[i].a(j);
                if (this.items[i].count == 0) {
                    this.items[i] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack splitWithoutUpdate(int i) {
        if (this.items[i] != null) {
            ItemStack itemstack = this.items[i];

            this.items[i] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void setItem(int i, ItemStack itemstack) {
        this.items[i] = itemstack;
        if (itemstack != null && itemstack.count > this.getMaxStackSize()) {
            itemstack.count = this.getMaxStackSize();
        }

    }

    public String getName() {
        return this.hasCustomName() ? this.f : "container.hopper";
    }

    public boolean hasCustomName() {
        return this.f != null && this.f.length() > 0;
    }

    public void a(String s) {
        this.f = s;
    }

    public int getMaxStackSize() {
        return maxStack; // CraftBukkit
    }

    public boolean a(EntityHuman entityhuman) {
        return this.world.getTileEntity(this.position) != this ? false : entityhuman.e((double) this.position.getX() + 0.5D, (double) this.position.getY() + 0.5D, (double) this.position.getZ() + 0.5D) <= 64.0D;
    }

    public void startOpen(EntityHuman entityhuman) {}

    public void closeContainer(EntityHuman entityhuman) {}

    public boolean b(int i, ItemStack itemstack) {
        return true;
    }

    public void c() {
        if (this.world != null && !this.world.isStatic) {
            // Spigot start
            if (this.world.spigotConfig.altHopperTicking) {
                this.lastTick = this.world.getTime();
                if (this.nextTick == this.world.getTime()) {
                	// Method that does the pushing and pulling.
                    this.m();
                }
            } else {
                --this.g;
                if (!this.n()) {
                    this.d(0);
                    this.m();
                }
            }

        }
    }

    public boolean m() {
        if (this.world != null && !this.world.isStatic) {
            if (!this.n() && BlockHopper.f(this.u())) {
                boolean flag = false;

                if (!this.p()) {
                    flag = this.r();
                }

                if (!this.q()) {
                    flag = a((IHopper) this) || flag;
                }

                if (flag) {
                    this.d(world.spigotConfig.hopperTransfer); // Spigot
                    this.update();
                    return true;
                }
            }

            // Spigot start
            if ( !world.spigotConfig.altHopperTicking && !this.n() )
            {
                this.d( world.spigotConfig.hopperCheck );
            }
            // Spigot end
            return false;
        } else {
            return false;
        }
    }

    private boolean p() {
        ItemStack[] aitemstack = this.items;
        int i = aitemstack.length;

        for (int j = 0; j < i; ++j) {
            ItemStack itemstack = aitemstack[j];

            if (itemstack != null) {
                return false;
            }
        }

        return true;
    }

    private boolean q() {
        ItemStack[] aitemstack = this.items;
        int i = aitemstack.length;

        for (int j = 0; j < i; ++j) {
            ItemStack itemstack = aitemstack[j];

            if (itemstack == null || itemstack.count != itemstack.getMaxStackSize()) {
                return false;
            }
        }

        return true;
    }

    private boolean r() {
        IInventory iinventory = this.G();

        if (iinventory == null) {
            return false;
        } else {
            EnumDirection enumdirection = BlockHopper.b(this.u()).opposite();

            if (this.a(iinventory, enumdirection)) {
                return false;
            } else {
                for (int i = 0; i < this.getSize(); ++i) {
                    if (this.getItem(i) != null) {
                        ItemStack itemstack = this.getItem(i).cloneItemStack();
                        // ItemStack itemstack1 = addItem(iinventory, this.splitStack(i, 1), enumdirection);
                        
                        // CraftBukkit start - Call event when pushing items into other inventories
                        CraftItemStack oitemstack = CraftItemStack.asCraftMirror(this.splitStack(i, world.spigotConfig.hopperAmount)); // Spigot

                        Inventory destinationInventory;
                        // Have to special case large chests as they work oddly
                        if (iinventory instanceof InventoryLargeChest) {
                            destinationInventory = new org.bukkit.craftbukkit.inventory.CraftInventoryDoubleChest((InventoryLargeChest) iinventory);
                        } else {
                            destinationInventory = iinventory.getOwner().getInventory();
                        }

                        InventoryMoveItemEvent event = new InventoryMoveItemEvent(this.getOwner().getInventory(), oitemstack.clone(), destinationInventory, true);
                        this.getWorld().getServer().getPluginManager().callEvent(event);
                        if (event.isCancelled()) {
                            this.setItem(i, itemstack);
                            this.d(world.spigotConfig.hopperTransfer); // Spigot
                            return false;
                        }
                        int origCount = event.getItem().getAmount(); // Spigot
                        ItemStack itemstack1 = addItem(iinventory, CraftItemStack.asNMSCopy(event.getItem()), enumdirection);

                        if (itemstack1 == null || itemstack1.count == 0) {
                            if (event.getItem().equals(oitemstack)) {
                                iinventory.update();
                            } else {
                                this.setItem(i, itemstack);
                            }
                            // CraftBukkit end
                            return true;
                        }
                        itemstack.count -= origCount - itemstack1.count; // Spigot
                        this.setItem(i, itemstack);
                    }
                }

                return false;
            }
        }
    }

    private boolean a(IInventory iinventory, EnumDirection enumdirection) {
        if (iinventory instanceof IWorldInventory) {
            IWorldInventory iworldinventory = (IWorldInventory) iinventory;
            int[] aint = iworldinventory.getSlotsForFace(enumdirection);

            for (int i = 0; i < aint.length; ++i) {
                ItemStack itemstack = iworldinventory.getItem(aint[i]);

                if (itemstack == null || itemstack.count != itemstack.getMaxStackSize()) {
                    return false;
                }
            }
        } else {
            int j = iinventory.getSize();

            for (int k = 0; k < j; ++k) {
                ItemStack itemstack1 = iinventory.getItem(k);

                if (itemstack1 == null || itemstack1.count != itemstack1.getMaxStackSize()) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean b(IInventory iinventory, EnumDirection enumdirection) {
        if (iinventory instanceof IWorldInventory) {
            IWorldInventory iworldinventory = (IWorldInventory) iinventory;
            int[] aint = iworldinventory.getSlotsForFace(enumdirection);

            for (int i = 0; i < aint.length; ++i) {
                if (iworldinventory.getItem(aint[i]) != null) {
                    return false;
                }
            }
        } else {
            int j = iinventory.getSize();

            for (int k = 0; k < j; ++k) {
                if (iinventory.getItem(k) != null) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean a(IHopper ihopper) {
        IInventory iinventory = b(ihopper);

        if (iinventory != null) {
            EnumDirection enumdirection = EnumDirection.DOWN;

            if (b(iinventory, enumdirection)) {
                return false;
            }

            if (iinventory instanceof IWorldInventory) {
                IWorldInventory iworldinventory = (IWorldInventory) iinventory;
                int[] aint = iworldinventory.getSlotsForFace(enumdirection);

                for (int i = 0; i < aint.length; ++i) {
                    if (a(ihopper, iinventory, aint[i], enumdirection)) {
                        return true;
                    }
                }
            } else {
                int j = iinventory.getSize();

                for (int k = 0; k < j; ++k) {
                    if (a(ihopper, iinventory, k, enumdirection)) {
                        return true;
                    }
                }
            }
        } else {
            EntityItem entityitem = a(ihopper.getWorld(), ihopper.A(), ihopper.B() + 1.0D, ihopper.C());

            if (entityitem != null) {
                return a((IInventory) ihopper, entityitem);
            }
        }

        return false;
    }

    private static boolean a(IHopper ihopper, IInventory iinventory, int i, EnumDirection enumdirection) {
        ItemStack itemstack = iinventory.getItem(i);

        if (itemstack != null && b(iinventory, itemstack, i, enumdirection)) {
            ItemStack itemstack1 = itemstack.cloneItemStack();
            // ItemStack itemstack2 = addItem(ihopper, iinventory.splitStack(i, 1), (EnumDirection) null);
            // CraftBukkit start - Call event on collection of items from inventories into the hopper
            CraftItemStack oitemstack = CraftItemStack.asCraftMirror(iinventory.splitStack(i, ihopper.getWorld().spigotConfig.hopperAmount)); // Spigot

            Inventory sourceInventory;
            // Have to special case large chests as they work oddly
            if (iinventory instanceof InventoryLargeChest) {
                sourceInventory = new org.bukkit.craftbukkit.inventory.CraftInventoryDoubleChest((InventoryLargeChest) iinventory);
            } else {
                sourceInventory = iinventory.getOwner().getInventory();
            }

            InventoryMoveItemEvent event = new InventoryMoveItemEvent(sourceInventory, oitemstack.clone(), ihopper.getOwner().getInventory(), false);

            ihopper.getWorld().getServer().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                iinventory.setItem(i, itemstack1);

                if (ihopper instanceof TileEntityHopper) {
                    ((TileEntityHopper) ihopper).d(ihopper.getWorld().spigotConfig.hopperTransfer); // Spigot
                } else if (ihopper instanceof EntityMinecartHopper) {
                    ((EntityMinecartHopper) ihopper).l(ihopper.getWorld().spigotConfig.hopperTransfer / 2); // Spigot
                }

                return false;
            }
            int origCount = event.getItem().getAmount(); // Spigot
            ItemStack itemstack2 = addItem(ihopper, CraftItemStack.asNMSCopy(event.getItem()), null);

            if (itemstack2 == null || itemstack2.count == 0) {
                if (event.getItem().equals(oitemstack)) {
                    iinventory.update();
                } else {
                    iinventory.setItem(i, itemstack1);
                }
                // CraftBukkit end
                return true;
            }
            itemstack1.count -= origCount - itemstack2.count; // Spigot

            iinventory.setItem(i, itemstack1);
        }

        return false;
    }

    public static boolean a(IInventory iinventory, EntityItem entityitem) {
        boolean flag = false;

        if (entityitem == null) {
            return false;
        } else {
            // CraftBukkit start
            InventoryPickupItemEvent event = new InventoryPickupItemEvent(iinventory.getOwner().getInventory(), (org.bukkit.entity.Item) entityitem.getBukkitEntity());
            entityitem.world.getServer().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return false;
            }
            // CraftBukkit end
        
            ItemStack itemstack = entityitem.getItemStack().cloneItemStack();
            ItemStack itemstack1 = addItem(iinventory, itemstack, (EnumDirection) null);

            if (itemstack1 != null && itemstack1.count != 0) {
                entityitem.setItemStack(itemstack1);
            } else {
                flag = true;
                entityitem.die();
            }

            return flag;
        }
    }

    public static ItemStack addItem(IInventory iinventory, ItemStack itemstack, EnumDirection enumdirection) {
        if (iinventory instanceof IWorldInventory && enumdirection != null) {
            IWorldInventory iworldinventory = (IWorldInventory) iinventory;
            int[] aint = iworldinventory.getSlotsForFace(enumdirection);

            for (int i = 0; i < aint.length && itemstack != null && itemstack.count > 0; ++i) {
                itemstack = c(iinventory, itemstack, aint[i], enumdirection);
            }
        } else {
            int j = iinventory.getSize();

            for (int k = 0; k < j && itemstack != null && itemstack.count > 0; ++k) {
                itemstack = c(iinventory, itemstack, k, enumdirection);
            }
        }

        if (itemstack != null && itemstack.count == 0) {
            itemstack = null;
        }

        return itemstack;
    }

    private static boolean a(IInventory iinventory, ItemStack itemstack, int i, EnumDirection enumdirection) {
        return !iinventory.b(i, itemstack) ? false : !(iinventory instanceof IWorldInventory) || ((IWorldInventory) iinventory).canPlaceItemThroughFace(i, itemstack, enumdirection);
    }

    private static boolean b(IInventory iinventory, ItemStack itemstack, int i, EnumDirection enumdirection) {
        return !(iinventory instanceof IWorldInventory) || ((IWorldInventory) iinventory).canTakeItemThroughFace(i, itemstack, enumdirection);
    }

    private static ItemStack c(IInventory iinventory, ItemStack itemstack, int i, EnumDirection enumdirection) {
        ItemStack itemstack1 = iinventory.getItem(i);

        if (a(iinventory, itemstack, i, enumdirection)) {
            boolean flag = false;

            if (itemstack1 == null) {
                iinventory.setItem(i, itemstack);
                itemstack = null;
                flag = true;
            } else if (a(itemstack1, itemstack)) {
                int j = itemstack.getMaxStackSize() - itemstack1.count;
                int k = Math.min(itemstack.count, j);

                itemstack.count -= k;
                itemstack1.count += k;
                flag = k > 0;
            }

            if (flag) {
                if (iinventory instanceof TileEntityHopper) {
                    TileEntityHopper tileentityhopper = (TileEntityHopper) iinventory;

                    if (tileentityhopper.o()) {
                        tileentityhopper.d(tileentityhopper.world.spigotConfig.hopperTransfer); // Spigot
                    }

                    iinventory.update();
                }

                iinventory.update();
            }
        }

        return itemstack;
    }

    private IInventory G() {
        EnumDirection enumdirection = BlockHopper.b(this.u());

        return b(this.getWorld(), (double) (this.position.getX() + enumdirection.getAdjacentX()), (double) (this.position.getY() + enumdirection.getAdjacentY()), (double) (this.position.getZ() + enumdirection.getAdjacentZ()));
    }

    public static IInventory b(IHopper ihopper) {
        return b(ihopper.getWorld(), ihopper.A(), ihopper.B() + 1.0D, ihopper.C());
    }

    public static EntityItem a(World world, double d0, double d1, double d2) {
        List list = world.a(EntityItem.class, new AxisAlignedBB(d0, d1, d2, d0 + 1.0D, d1 + 1.0D, d2 + 1.0D), IEntitySelector.a);

        return list.size() > 0 ? (EntityItem) list.get(0) : null;
    }

    public static IInventory b(World world, double d0, double d1, double d2) {
        Object object = null;
        int i = MathHelper.floor(d0);
        int j = MathHelper.floor(d1);
        int k = MathHelper.floor(d2);
        BlockPosition blockposition = new BlockPosition(i, j, k);
        if ( !world.isLoaded( blockposition ) ) return null; // Spigot
        TileEntity tileentity = world.getTileEntity(new BlockPosition(i, j, k));

        if (tileentity instanceof IInventory) {
            object = (IInventory) tileentity;
            if (object instanceof TileEntityChest) {
                Block block = world.getType(new BlockPosition(i, j, k)).getBlock();

                if (block instanceof BlockChest) {
                    object = ((BlockChest) block).d(world, blockposition);
                }
            }
        }

        if (object == null) {
            List list = world.a((Entity) null, new AxisAlignedBB(d0, d1, d2, d0 + 1.0D, d1 + 1.0D, d2 + 1.0D), IEntitySelector.c);

            if (list.size() > 0) {
                object = (IInventory) list.get(world.random.nextInt(list.size()));
            }
        }

        return (IInventory) object;
    }

    private static boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return itemstack.getItem() != itemstack1.getItem() ? false : (itemstack.getData() != itemstack1.getData() ? false : (itemstack.count > itemstack.getMaxStackSize() ? false : ItemStack.equals(itemstack, itemstack1)));
    }

    public double A() {
        return (double) this.position.getX();
    }

    public double B() {
        return (double) this.position.getY();
    }

    public double C() {
        return (double) this.position.getZ();
    }

    public void d(int i) {
        // Spigot start - i is the delay for which this hopper will be ticked next.
        // i of 1 or below implies a tick next tick.
        if (this.world != null && this.world.spigotConfig.altHopperTicking) {
            if (i <= 0) {
                i = 1;
            }
            if (this.lastTick == this.world.getTime()) {
                this.nextTick = this.world.getTime() + i;
            } else {
                this.nextTick = this.world.getTime() + i - 1;
            }
        } else {
            this.g = i;
        }
        // Spigot end
    }

    public boolean n() {
        // Spigot start - Return whether this hopper is cooling down.
        if (this.world != null && this.world.spigotConfig.altHopperTicking) {
            if (this.lastTick == this.world.getTime()) {
                return this.nextTick > this.world.getTime();
            } else {
                return this.nextTick >= this.world.getTime();
            }
        } else {
            return this.g > 0;
        }
        // Spigot end
    }

    public boolean o() {
        return this.g <= 1;
    }

    public String getContainerName() {
        return "minecraft:hopper";
    }

    public Container createContainer(PlayerInventory playerinventory, EntityHuman entityhuman) {
        return new ContainerHopper(playerinventory, this, entityhuman);
    }

    public int getProperty(int i) {
        return 0;
    }

    public void b(int i, int j) {}

    public int g() {
        return 0;
    }

    public void l() {
        for (int i = 0; i < this.items.length; ++i) {
            this.items[i] = null;
        }

    }
}
