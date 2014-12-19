package net.minecraft.server;

class TileEntitySignPlayerWrapper implements ICommandListener {

    final EntityHuman a;
    final TileEntitySign b;

    TileEntitySignPlayerWrapper(TileEntitySign tileentitysign, EntityHuman entityhuman) {
        this.b = tileentitysign;
        this.a = entityhuman;
    }

    public String getName() {
        return this.a.getName();
    }

    public IChatBaseComponent getScoreboardDisplayName() {
        return this.a.getScoreboardDisplayName();
    }

    public void sendMessage(IChatBaseComponent ichatbasecomponent) {}

    public boolean a(int i, String s) {
        return true;
    }

    public BlockPosition getChunkCoordinates() {
        return this.b.position;
    }

    public Vec3D d() {
        return new Vec3D((double) this.b.position.getX() + 0.5D, (double) this.b.position.getY() + 0.5D, (double) this.b.position.getZ() + 0.5D);
    }

    public World getWorld() {
        return this.a.getWorld();
    }

    public Entity f() {
        return this.a;
    }

    public boolean getSendCommandFeedback() {
        return false;
    }

    public void a(EnumCommandResult enumcommandresult, int i) {
        TileEntitySign.a(this.b).a(this, enumcommandresult, i);
    }
}
