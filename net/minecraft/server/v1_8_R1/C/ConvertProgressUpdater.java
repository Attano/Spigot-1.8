package net.minecraft.server;

public class ConvertProgressUpdater implements IProgressUpdate {

    private long b;
    final MinecraftServer a;

    public ConvertProgressUpdater(MinecraftServer minecraftserver) {
        this.a = minecraftserver;
        this.b = MinecraftServer.ax();
    }

    public void a(String s) {}

    public void a(int i) {
        if (MinecraftServer.ax() - this.b >= 1000L) {
            this.b = MinecraftServer.ax();
            MinecraftServer.getLogger().info("Converting... " + i + "%");
        }

    }

    public void c(String s) {}
}
