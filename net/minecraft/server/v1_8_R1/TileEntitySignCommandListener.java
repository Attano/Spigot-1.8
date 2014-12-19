package net.minecraft.server;

class TileEntitySignCommandListener implements ICommandListener {

    final TileEntitySign a;

    TileEntitySignCommandListener(TileEntitySign tileentitysign) {
        this.a = tileentitysign;
    }

    public String getName() {
        return "Sign";
    }

    public IChatBaseComponent getScoreboardDisplayName() {
        return new ChatComponentText(this.getName());
    }

    public void sendMessage(IChatBaseComponent ichatbasecomponent) {}

    public boolean a(int i, String s) {
        return true;
    }

    public BlockPosition getChunkCoordinates() {
        return this.a.position;
    }

    public Vec3D d() {
        return new Vec3D((double) this.a.position.getX() + 0.5D, (double) this.a.position.getY() + 0.5D, (double) this.a.position.getZ() + 0.5D);
    }

    public World getWorld() {
        return this.a.world;
    }

    public Entity f() {
        return null;
    }

    public boolean getSendCommandFeedback() {
        return false;
    }

    public void a(EnumCommandResult enumcommandresult, int i) {}
}
