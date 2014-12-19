package net.minecraft.server;

public interface IMinecraftServer {

    int a(String s, int i);

    String a(String s, String s1);

    void a(String s, Object object);

    void a();

    String b();

    String C();

    int D();

    String E();

    String getVersion();

    int G();

    int H();

    String[] getPlayers();

    String T();

    String getPlugins();

    String executeRemoteCommand(String s);

    boolean isDebugging();

    void info(String s);

    void warning(String s);

    void h(String s);

    void i(String s);
}
