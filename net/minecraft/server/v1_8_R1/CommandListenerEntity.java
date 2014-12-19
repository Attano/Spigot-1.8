package net.minecraft.server;

class CommandListenerEntity implements ICommandListener {

    final Entity a;
    final ICommandListener b;
    final BlockPosition c;
    final double d;
    final double e;
    final double f;
    final CommandExecute g;

    CommandListenerEntity(CommandExecute commandexecute, Entity entity, ICommandListener icommandlistener, BlockPosition blockposition, double d0, double d1, double d2) {
        this.g = commandexecute;
        this.a = entity;
        this.b = icommandlistener;
        this.c = blockposition;
        this.d = d0;
        this.e = d1;
        this.f = d2;
    }

    public String getName() {
        return this.a.getName();
    }

    public IChatBaseComponent getScoreboardDisplayName() {
        return this.a.getScoreboardDisplayName();
    }

    public void sendMessage(IChatBaseComponent ichatbasecomponent) {
        this.b.sendMessage(ichatbasecomponent);
    }

    public boolean a(int i, String s) {
        return this.b.a(i, s);
    }

    public BlockPosition getChunkCoordinates() {
        return this.c;
    }

    public Vec3D d() {
        return new Vec3D(this.d, this.e, this.f);
    }

    public World getWorld() {
        return this.a.world;
    }

    public Entity f() {
        return this.a;
    }

    public boolean getSendCommandFeedback() {
        MinecraftServer minecraftserver = MinecraftServer.getServer();

        return minecraftserver == null || minecraftserver.worldServer[0].getGameRules().getBoolean("commandBlockOutput");
    }

    public void a(EnumCommandResult enumcommandresult, int i) {
        this.a.a(enumcommandresult, i);
    }
}
