package net.minecraft.server;

public class EntityMinecartCommandBlockListener extends CommandBlockListenerAbstract {

    final EntityMinecartCommandBlock a;

    EntityMinecartCommandBlockListener(EntityMinecartCommandBlock entityminecartcommandblock) {
        this.a = entityminecartcommandblock;
        this.sender = (org.bukkit.craftbukkit.entity.CraftMinecartCommand) entityminecartcommandblock.getBukkitEntity(); // CraftBukkit - Set the sender
    }

    public void h() {
        this.a.getDataWatcher().watch(23, this.getCommand());
        this.a.getDataWatcher().watch(24, ChatSerializer.a(this.k()));
    }

    public BlockPosition getChunkCoordinates() {
        return new BlockPosition(this.a.locX, this.a.locY + 0.5D, this.a.locZ);
    }

    public Vec3D d() {
        return new Vec3D(this.a.locX, this.a.locY, this.a.locZ);
    }

    public World getWorld() {
        return this.a.world;
    }

    public Entity f() {
        return this.a;
    }
}
