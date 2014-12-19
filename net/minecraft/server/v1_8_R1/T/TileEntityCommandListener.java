package net.minecraft.server;

public class TileEntityCommandListener extends CommandBlockListenerAbstract {

    final TileEntityCommand a;

    TileEntityCommandListener(TileEntityCommand tileentitycommand) {
        this.a = tileentitycommand;
        sender = new org.bukkit.craftbukkit.command.CraftBlockCommandSender(this); // CraftBukkit - add sender
    }

    public BlockPosition getChunkCoordinates() {
        return this.a.position;
    }

    public Vec3D d() {
        return new Vec3D((double) this.a.position.getX() + 0.5D, (double) this.a.position.getY() + 0.5D, (double) this.a.position.getZ() + 0.5D);
    }

    public World getWorld() {
        return this.a.getWorld();
    }

    public void setCommand(String s) {
        super.setCommand(s);
        this.a.update();
    }

    public void h() {
        this.a.getWorld().notify(this.a.position);
    }

    public Entity f() {
        return null;
    }
}
