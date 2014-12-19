package net.minecraft.server;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Proxy;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// CraftBukkit start
import java.io.PrintStream;
import org.apache.logging.log4j.Level;

import org.bukkit.craftbukkit.LoggerOutputStream;
import org.bukkit.craftbukkit.SpigotTimings; // Spigot
import org.bukkit.event.server.ServerCommandEvent;
// CraftBukkit end

public class DedicatedServer extends MinecraftServer implements IMinecraftServer {

    private static final Logger LOGGER = LogManager.getLogger();
    private final List k = Collections.synchronizedList(Lists.newArrayList());
    private RemoteStatusListener l;
    private RemoteControlListener m;
    public PropertyManager propertyManager;
    private EULA o;
    private boolean generateStructures;
    private EnumGamemode q;
    private boolean r;

    // CraftBukkit start - Signature changed
    public DedicatedServer(joptsimple.OptionSet options) {
        super(options, Proxy.NO_PROXY, a);
        // super(file, Proxy.NO_PROXY, a);
        // CraftBukkit end
        new ThreadSleepForever(this, "Server Infinisleeper");
    }

    protected boolean init() throws java.net.UnknownHostException { // CraftBukkit - throws UnknownHostException
        ThreadCommandReader threadcommandreader = new ThreadCommandReader(this, "Server console handler");

        threadcommandreader.setDaemon(true);
        threadcommandreader.start();

        // CraftBukkit start - TODO: handle command-line logging arguments
        java.util.logging.Logger global = java.util.logging.Logger.getLogger("");
        global.setUseParentHandlers(false);
        for (java.util.logging.Handler handler : global.getHandlers()) {
            global.removeHandler(handler);
        }
        global.addHandler(new org.bukkit.craftbukkit.util.ForwardLogHandler());

        final org.apache.logging.log4j.core.Logger logger = ((org.apache.logging.log4j.core.Logger) LogManager.getRootLogger());
        for (org.apache.logging.log4j.core.Appender appender : logger.getAppenders().values()) {
            if (appender instanceof org.apache.logging.log4j.core.appender.ConsoleAppender) {
                logger.removeAppender(appender);
            }
        }

        new Thread(new org.bukkit.craftbukkit.util.TerminalConsoleWriterThread(System.out, this.reader)).start();

        System.setOut(new PrintStream(new LoggerOutputStream(logger, Level.INFO), true));
        System.setErr(new PrintStream(new LoggerOutputStream(logger, Level.WARN), true));
        // CraftBukkit end

        DedicatedServer.LOGGER.info("Starting minecraft server version 1.8");
        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
            DedicatedServer.LOGGER.warn("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
        }

        DedicatedServer.LOGGER.info("Loading properties");
        this.propertyManager = new PropertyManager(this.options); // CraftBukkit - CLI argument support
        this.o = new EULA(new File("eula.txt"));
        // Spigot Start
        boolean eulaAgreed = Boolean.getBoolean( "com.mojang.eula.agree" );
        if ( eulaAgreed )
        {
            System.err.println( "You have used the Spigot command line EULA agreement flag." );
            System.err.println( "By using this setting you are indicating your agreement to Mojang's EULA (https://account.mojang.com/documents/minecraft_eula)." );
            System.err.println( "If you do not agree to the above EULA please stop your server and remove this flag immediately." );
        }
        // Spigot End
        if (!this.o.a() && !eulaAgreed) { // Spigot
            DedicatedServer.LOGGER.info("You need to agree to the EULA in order to run the server. Go to eula.txt for more info.");
            this.o.b();
            return false;
        } else {
            if (this.S()) {
                this.c("127.0.0.1");
            } else {
                this.setOnlineMode(this.propertyManager.getBoolean("online-mode", true));
                this.c(this.propertyManager.getString("server-ip", ""));
            }

            this.setSpawnAnimals(this.propertyManager.getBoolean("spawn-animals", true));
            this.setSpawnNPCs(this.propertyManager.getBoolean("spawn-npcs", true));
            this.setPVP(this.propertyManager.getBoolean("pvp", true));
            this.setAllowFlight(this.propertyManager.getBoolean("allow-flight", false));
            this.setResourcePack(this.propertyManager.getString("resource-pack", ""), this.propertyManager.getString("resource-pack-hash", ""));
            this.setMotd(this.propertyManager.getString("motd", "A Minecraft Server"));
            this.setForceGamemode(this.propertyManager.getBoolean("force-gamemode", false));
            this.setIdleTimeout(this.propertyManager.getInt("player-idle-timeout", 0));
            if (this.propertyManager.getInt("difficulty", 1) < 0) {
                this.propertyManager.setProperty("difficulty", Integer.valueOf(0));
            } else if (this.propertyManager.getInt("difficulty", 1) > 3) {
                this.propertyManager.setProperty("difficulty", Integer.valueOf(3));
            }

            this.generateStructures = this.propertyManager.getBoolean("generate-structures", true);
            int i = this.propertyManager.getInt("gamemode", EnumGamemode.SURVIVAL.getId());

            this.q = WorldSettings.a(i);
            DedicatedServer.LOGGER.info("Default game type: " + this.q);
            InetAddress inetaddress = null;

            if (this.getServerIp().length() > 0) {
                inetaddress = InetAddress.getByName(this.getServerIp());
            }

            if (this.Q() < 0) {
                this.setPort(this.propertyManager.getInt("server-port", 25565));
            }
            // Spigot start
            this.a((PlayerList) (new DedicatedPlayerList(this)));
            org.spigotmc.SpigotConfig.init();
            org.spigotmc.SpigotConfig.registerCommands();
            // Spigot end

            DedicatedServer.LOGGER.info("Generating keypair");
            this.a(MinecraftEncryption.b());
            DedicatedServer.LOGGER.info("Starting Minecraft server on " + (this.getServerIp().length() == 0 ? "*" : this.getServerIp()) + ":" + this.Q());

        if (!org.spigotmc.SpigotConfig.lateBind) {
            try {
                this.ao().a(inetaddress, this.Q());
            } catch (Throwable ioexception) { // CraftBukkit - IOException -> Throwable
                DedicatedServer.LOGGER.warn("**** FAILED TO BIND TO PORT!");
                DedicatedServer.LOGGER.warn("The exception was: {}", new Object[] { ioexception.toString()});
                DedicatedServer.LOGGER.warn("Perhaps a server is already running on that port?");
                return false;
            }
        }

            // Spigot Start - Move DedicatedPlayerList up and bring plugin loading from CraftServer to here
            // this.a((PlayerList) (new DedicatedPlayerList(this))); // CraftBukkit
            server.loadPlugins();
            server.enablePlugins(org.bukkit.plugin.PluginLoadOrder.STARTUP);
            // Spigot End

            if (!this.getOnlineMode()) {
                DedicatedServer.LOGGER.warn("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
                DedicatedServer.LOGGER.warn("The server will make no attempt to authenticate usernames. Beware.");
                DedicatedServer.LOGGER.warn("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
                DedicatedServer.LOGGER.warn("To change this, set \"online-mode\" to \"true\" in the server.properties file.");
            }

            if (this.aP()) {
                this.getUserCache().c();
            }

            if (!NameReferencingFileConverter.a(this.propertyManager)) {
                return false;
            } else {
                // this.a((PlayerList) (new DedicatedPlayerList(this))); // CraftBukkit - moved up
                this.convertable = new WorldLoaderServer(server.getWorldContainer()); // CraftBukkit - moved from MinecraftServer constructor
                long j = System.nanoTime();

                if (this.T() == null) {
                    this.setWorld(this.propertyManager.getString("level-name", "world"));
                }

                String s = this.propertyManager.getString("level-seed", "");
                String s1 = this.propertyManager.getString("level-type", "DEFAULT");
                String s2 = this.propertyManager.getString("generator-settings", "");
                long k = (new Random()).nextLong();

                if (s.length() > 0) {
                    try {
                        long l = Long.parseLong(s);

                        if (l != 0L) {
                            k = l;
                        }
                    } catch (NumberFormatException numberformatexception) {
                        k = (long) s.hashCode();
                    }
                }

                WorldType worldtype = WorldType.getType(s1);

                if (worldtype == null) {
                    worldtype = WorldType.NORMAL;
                }

                this.az();
                this.getEnableCommandBlock();
                this.p();
                this.getSnooperEnabled();
                this.aI();
                this.c(this.propertyManager.getInt("max-build-height", 256));
                this.c((this.getMaxBuildHeight() + 8) / 16 * 16);
                this.c(MathHelper.clamp(this.getMaxBuildHeight(), 64, 256));
                this.propertyManager.setProperty("max-build-height", Integer.valueOf(this.getMaxBuildHeight()));
                DedicatedServer.LOGGER.info("Preparing level \"" + this.T() + "\"");
                this.a(this.T(), this.T(), k, worldtype, s2);
                long i1 = System.nanoTime() - j;
                String s3 = String.format("%.3fs", new Object[] { Double.valueOf((double) i1 / 1.0E9D)});

                DedicatedServer.LOGGER.info("Done (" + s3 + ")! For help, type \"help\" or \"?\"");
                if (this.propertyManager.getBoolean("enable-query", false)) {
                    DedicatedServer.LOGGER.info("Starting GS4 status listener");
                    this.l = new RemoteStatusListener(this);
                    this.l.a();
                }

                if (this.propertyManager.getBoolean("enable-rcon", false)) {
                    DedicatedServer.LOGGER.info("Starting remote control listener");
                    this.m = new RemoteControlListener(this);
                    this.m.a();
                    this.remoteConsole = new org.bukkit.craftbukkit.command.CraftRemoteConsoleCommandSender(); // CraftBukkit
                }

                // CraftBukkit start
                if (this.server.getBukkitSpawnRadius() > -1) {
                    DedicatedServer.LOGGER.info("'settings.spawn-radius' in bukkit.yml has been moved to 'spawn-protection' in server.properties. I will move your config for you.");
                    this.propertyManager.properties.remove("spawn-protection");
                    this.propertyManager.getInt("spawn-protection", this.server.getBukkitSpawnRadius());
                    this.server.removeBukkitSpawnRadius();
                    this.propertyManager.savePropertiesFile();
                }
                // CraftBukkit end

        if (org.spigotmc.SpigotConfig.lateBind) {
            try {
                this.ao().a(inetaddress, this.Q());
            } catch (Throwable ioexception) { // CraftBukkit - IOException -> Throwable
                DedicatedServer.LOGGER.warn("**** FAILED TO BIND TO PORT!");
                DedicatedServer.LOGGER.warn("The exception was: {}", new Object[] { ioexception.toString()});
                DedicatedServer.LOGGER.warn("Perhaps a server is already running on that port?");
                return false;
            }
        }

                if (false && this.aQ() > 0L) { // Spigot - disable
                    Thread thread = new Thread(new ThreadWatchdog(this));

                    thread.setName("Server Watchdog");
                    thread.setDaemon(true);
                    thread.start();
                }

                return true;
            }
        }
    }

    // CraftBukkit start
    public PropertyManager getPropertyManager() {
        return this.propertyManager;
    }
    // CraftBukkit end

    public void setGamemode(EnumGamemode enumgamemode) {
        super.setGamemode(enumgamemode);
        this.q = enumgamemode;
    }

    public boolean getGenerateStructures() {
        return this.generateStructures;
    }

    public EnumGamemode getGamemode() {
        return this.q;
    }

    public EnumDifficulty getDifficulty() {
        return EnumDifficulty.getById(this.propertyManager.getInt("difficulty", 1));
    }

    public boolean isHardcore() {
        return this.propertyManager.getBoolean("hardcore", false);
    }

    protected void a(CrashReport crashreport) {
        /* CraftBukkit start - not sure why you would want to continue running commands once the server crashed
        while (this.isRunning()) {
            this.aM();

            try {
                Thread.sleep(10L);
            } catch (InterruptedException interruptedexception) {
                ;
            }
        }
        // CraftBukkit end */        
    }

    public CrashReport b(CrashReport crashreport) {
        crashreport = super.b(crashreport);
        crashreport.g().a("Is Modded", (Callable) (new CrashReportModded(this)));
        crashreport.g().a("Type", (Callable) (new CrashReportType(this)));
        return crashreport;
    }

    protected void x() {
        System.exit(0);
    }

    public void z() {
        super.z();
        this.aM();
    }

    public boolean getAllowNether() {
        return this.propertyManager.getBoolean("allow-nether", true);
    }

    public boolean getSpawnMonsters() {
        return this.propertyManager.getBoolean("spawn-monsters", true);
    }

    public void a(MojangStatisticsGenerator mojangstatisticsgenerator) {
        mojangstatisticsgenerator.a("whitelist_enabled", Boolean.valueOf(this.aN().getHasWhitelist()));
        mojangstatisticsgenerator.a("whitelist_count", Integer.valueOf(this.aN().getWhitelisted().length));
        super.a(mojangstatisticsgenerator);
    }

    public boolean getSnooperEnabled() {
        return this.propertyManager.getBoolean("snooper-enabled", true);
    }

    public void issueCommand(String s, ICommandListener icommandlistener) {
        this.k.add(new ServerCommand(s, icommandlistener));
    }

    public void aM() {
        SpigotTimings.serverCommandTimer.startTiming(); // Spigot
        while (!this.k.isEmpty()) {
            ServerCommand servercommand = (ServerCommand) this.k.remove(0);

            // CraftBukkit start - ServerCommand for preprocessing
            ServerCommandEvent event = new ServerCommandEvent(console, servercommand.command);
            server.getPluginManager().callEvent(event);
            servercommand = new ServerCommand(event.getCommand(), servercommand.source);

            // this.getCommandHandler().a(servercommand.source, servercommand.command); // Called in dispatchServerCommand
            server.dispatchServerCommand(console, servercommand);
            // CraftBukkit end
        }

        SpigotTimings.serverCommandTimer.stopTiming(); // Spigot
    }

    public boolean ad() {
        return true;
    }

    public DedicatedPlayerList aN() {
        return (DedicatedPlayerList) super.getPlayerList();
    }

    public int a(String s, int i) {
        return this.propertyManager.getInt(s, i);
    }

    public String a(String s, String s1) {
        return this.propertyManager.getString(s, s1);
    }

    public boolean a(String s, boolean flag) {
        return this.propertyManager.getBoolean(s, flag);
    }

    public void a(String s, Object object) {
        this.propertyManager.setProperty(s, object);
    }

    public void a() {
        this.propertyManager.savePropertiesFile();
    }

    public String b() {
        File file = this.propertyManager.c();

        return file != null ? file.getAbsolutePath() : "No settings file";
    }

    public void aO() {
        ServerGUI.a(this);
        this.r = true;
    }

    public boolean aq() {
        return this.r;
    }

    public String a(EnumGamemode enumgamemode, boolean flag) {
        return "";
    }

    public boolean getEnableCommandBlock() {
        return this.propertyManager.getBoolean("enable-command-block", false);
    }

    public int getSpawnProtection() {
        return this.propertyManager.getInt("spawn-protection", super.getSpawnProtection());
    }

    public boolean a(World world, BlockPosition blockposition, EntityHuman entityhuman) {
        if (world.worldProvider.getDimension() != 0) {
            return false;
        } else if (this.aN().getOPs().isEmpty()) {
            return false;
        } else if (this.aN().isOp(entityhuman.getProfile())) {
            return false;
        } else if (this.getSpawnProtection() <= 0) {
            return false;
        } else {
            BlockPosition blockposition1 = world.getSpawn();
            int i = MathHelper.a(blockposition.getX() - blockposition1.getX());
            int j = MathHelper.a(blockposition.getZ() - blockposition1.getZ());
            int k = Math.max(i, j);

            return k <= this.getSpawnProtection();
        }
    }

    public int p() {
        return this.propertyManager.getInt("op-permission-level", 4);
    }

    public void setIdleTimeout(int i) {
        super.setIdleTimeout(i);
        this.propertyManager.setProperty("player-idle-timeout", Integer.valueOf(i));
        this.a();
    }

    public boolean az() {
        return this.propertyManager.getBoolean("announce-player-achievements", true);
    }

    public int aG() {
        int i = this.propertyManager.getInt("max-world-size", super.aG());

        if (i < 1) {
            i = 1;
        } else if (i > super.aG()) {
            i = super.aG();
        }

        return i;
    }

    public int aI() {
        return this.propertyManager.getInt("network-compression-threshold", super.aI());
    }

    protected boolean aP() {
        server.getLogger().info( "**** Beginning UUID conversion, this may take A LONG time ****"); // Spigot, let the user know whats up!
        boolean flag = false;

        int i;

        for (i = 0; !flag && i <= 2; ++i) {
            if (i > 0) {
                DedicatedServer.LOGGER.warn("Encountered a problem while converting the user banlist, retrying in a few seconds");
                this.aS();
            }

            flag = NameReferencingFileConverter.a((MinecraftServer) this);
        }

        boolean flag1 = false;

        for (i = 0; !flag1 && i <= 2; ++i) {
            if (i > 0) {
                DedicatedServer.LOGGER.warn("Encountered a problem while converting the ip banlist, retrying in a few seconds");
                this.aS();
            }

            flag1 = NameReferencingFileConverter.b((MinecraftServer) this);
        }

        boolean flag2 = false;

        for (i = 0; !flag2 && i <= 2; ++i) {
            if (i > 0) {
                DedicatedServer.LOGGER.warn("Encountered a problem while converting the op list, retrying in a few seconds");
                this.aS();
            }

            flag2 = NameReferencingFileConverter.c((MinecraftServer) this);
        }

        boolean flag3 = false;

        for (i = 0; !flag3 && i <= 2; ++i) {
            if (i > 0) {
                DedicatedServer.LOGGER.warn("Encountered a problem while converting the whitelist, retrying in a few seconds");
                this.aS();
            }

            flag3 = NameReferencingFileConverter.d((MinecraftServer) this);
        }

        boolean flag4 = false;

        for (i = 0; !flag4 && i <= 2; ++i) {
            if (i > 0) {
                DedicatedServer.LOGGER.warn("Encountered a problem while converting the player save files, retrying in a few seconds");
                this.aS();
            }

            flag4 = NameReferencingFileConverter.a(this, this.propertyManager);
        }

        return flag || flag1 || flag2 || flag3 || flag4;
    }

    private void aS() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException interruptedexception) {
            ;
        }
    }

    public long aQ() {
        return this.propertyManager.getLong("max-tick-time", TimeUnit.MINUTES.toMillis(1L));
    }

    public PlayerList getPlayerList() {
        return this.aN();
    }

    static Logger aR() {
        return DedicatedServer.LOGGER;
    }
}
