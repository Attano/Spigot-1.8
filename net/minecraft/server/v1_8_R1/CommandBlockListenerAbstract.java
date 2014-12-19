package net.minecraft.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

// CraftBukkit start
import java.util.ArrayList;
import org.apache.logging.log4j.Level;
import org.bukkit.craftbukkit.command.VanillaCommandWrapper;
import com.google.common.base.Joiner;
// CraftBukkit end

public abstract class CommandBlockListenerAbstract implements ICommandListener {

    private static final SimpleDateFormat a = new SimpleDateFormat("HH:mm:ss");
    private int b;
    private boolean c = true;
    private IChatBaseComponent d = null;
    public String e = "";
    private String f = "@";
    private final CommandObjectiveExecutor g = new CommandObjectiveExecutor();
    protected org.bukkit.command.CommandSender sender; // CraftBukkit - add sender

    public CommandBlockListenerAbstract() {}

    public int j() {
        return this.b;
    }

    public IChatBaseComponent k() {
        return this.d;
    }

    public void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.setString("Command", this.e);
        nbttagcompound.setInt("SuccessCount", this.b);
        nbttagcompound.setString("CustomName", this.f);
        nbttagcompound.setBoolean("TrackOutput", this.c);
        if (this.d != null && this.c) {
            nbttagcompound.setString("LastOutput", ChatSerializer.a(this.d));
        }

        this.g.b(nbttagcompound);
    }

    public void b(NBTTagCompound nbttagcompound) {
        this.e = nbttagcompound.getString("Command");
        this.b = nbttagcompound.getInt("SuccessCount");
        if (nbttagcompound.hasKeyOfType("CustomName", 8)) {
            this.f = nbttagcompound.getString("CustomName");
        }

        if (nbttagcompound.hasKeyOfType("TrackOutput", 1)) {
            this.c = nbttagcompound.getBoolean("TrackOutput");
        }

        if (nbttagcompound.hasKeyOfType("LastOutput", 8) && this.c) {
            this.d = ChatSerializer.a(nbttagcompound.getString("LastOutput"));
        }

        this.g.a(nbttagcompound);
    }

    public boolean a(int i, String s) {
        return i <= 2;
    }

    public void setCommand(String s) {
        this.e = s;
        this.b = 0;
    }

    public String getCommand() {
        return this.e;
    }

    public void a(World world) {
        if (world.isStatic) {
            this.b = 0;
        }

        MinecraftServer minecraftserver = MinecraftServer.getServer();

        if (minecraftserver != null && minecraftserver.N() && minecraftserver.getEnableCommandBlock()) {
            ICommandHandler icommandhandler = minecraftserver.getCommandHandler();

            try {
                this.d = null;
                // this.b = icommandhandler.a(this, this.e);
                // CraftBukkit start - Handle command block commands using Bukkit dispatcher
                org.bukkit.command.SimpleCommandMap commandMap = minecraftserver.server.getCommandMap();
                Joiner joiner = Joiner.on(" ");
                String command = this.e;
                if (this.e.startsWith("/")) {
                    command = this.e.substring(1);
                }
                String[] args = command.split(" ");
                ArrayList<String[]> commands = new ArrayList<String[]>();

                // Block disallowed commands
                if (args[0].equalsIgnoreCase("stop") || args[0].equalsIgnoreCase("kick") || args[0].equalsIgnoreCase("op") ||
                        args[0].equalsIgnoreCase("deop") || args[0].equalsIgnoreCase("ban") || args[0].equalsIgnoreCase("ban-ip") ||
                        args[0].equalsIgnoreCase("pardon") || args[0].equalsIgnoreCase("pardon-ip") || args[0].equalsIgnoreCase("reload")) {
                    this.b = 0;
                    return;
                }

                // If the world has no players don't run
                if (this.getWorld().players.isEmpty()) {
                    this.b = 0;
                    return;
                }

                // Handle vanilla commands;
                if (minecraftserver.server.getCommandBlockOverride(args[0])) {
                    org.bukkit.command.Command commandBlockCommand = commandMap.getCommand("minecraft:" + args[0]);
                    if (commandBlockCommand instanceof VanillaCommandWrapper) {
                        this.b = ((VanillaCommandWrapper) commandBlockCommand).dispatchVanillaCommandBlock(this, this.e);
                        return;
                    }
                }

                // Spigot start - check for manually prefixed command or commands that don't need a prefix
                org.bukkit.command.Command commandBlockCommand = commandMap.getCommand(args[0]);
                if (commandBlockCommand instanceof VanillaCommandWrapper) {
                    this.b = ((VanillaCommandWrapper) commandBlockCommand).dispatchVanillaCommandBlock(this, this.e);
                    return;
                }
                // Spigot end

                // Make sure this is a valid command
                if (commandMap.getCommand(args[0]) == null) {
                    this.b = 0;
                    return;
                }

                // testfor command requires special handling
                if (args[0].equalsIgnoreCase("testfor")) {
                    if (args.length < 2) {
                        this.b = 0;
                        return;
                    }

                    EntityPlayer[] players = ((java.util.List<EntityPlayer>)PlayerSelector.getPlayers(this, args[1], EntityPlayer.class)).toArray(new EntityPlayer[0]);

                    if (players != null && players.length > 0) {
                        this.b = players.length;
                        return;
                    } else {
                        EntityPlayer player = MinecraftServer.getServer().getPlayerList().getPlayer(args[1]);
                        if (player == null) {
                            this.b = 0;
                            return;
                        } else {
                            this.b = 1;
                            return;
                        }
                    }
                }

                commands.add(args);

                // Find positions of command block syntax, if any
                ArrayList<String[]> newCommands = new ArrayList<String[]>();
                for (int i = 0; i < args.length; i++) {
                    if (PlayerSelector.isPattern(args[i])) {
                        for (int j = 0; j < commands.size(); j++) {
                            newCommands.addAll(this.buildCommands(commands.get(j), i));
                        }
                        ArrayList<String[]> temp = commands;
                        commands = newCommands;
                        newCommands = temp;
                        newCommands.clear();
                    }
                }

                int completed = 0;

                // Now dispatch all of the commands we ended up with
                for (int i = 0; i < commands.size(); i++) {
                    try {
                        if (commandMap.dispatch(sender, joiner.join(java.util.Arrays.asList(commands.get(i))))) {
                            completed++;
                        }
                    } catch (Throwable exception) {
                        if(this instanceof TileEntityCommandListener) {
                            TileEntityCommandListener listener = (TileEntityCommandListener) this;
                            MinecraftServer.getLogger().log(Level.WARN, String.format("CommandBlock at (%d,%d,%d) failed to handle command", listener.getChunkCoordinates().getX(), listener.getChunkCoordinates().getY(), listener.getChunkCoordinates().getZ()), exception);
                        } else if (this instanceof EntityMinecartCommandBlockListener) {
                            EntityMinecartCommandBlockListener listener = (EntityMinecartCommandBlockListener) this;
                            MinecraftServer.getLogger().log(Level.WARN, String.format("MinecartCommandBlock at (%d,%d,%d) failed to handle command", listener.getChunkCoordinates().getX(), listener.getChunkCoordinates().getY(), listener.getChunkCoordinates().getZ()), exception);
                        } else {
                            MinecraftServer.getLogger().log(Level.WARN, String.format("Unknown CommandBlock failed to handle command"), exception);
                        }
                    }
                }

                this.b = completed;
                // CraftBukkit end
            } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.a(throwable, "Executing command block");
                CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Command to be executed");

                crashreportsystemdetails.a("Command", (Callable) (new CrashReportCommandBlockCommand(this)));
                crashreportsystemdetails.a("Name", (Callable) (new CrashReportCommandBlockName(this)));
                throw new ReportedException(crashreport);
            }
        } else {
            this.b = 0;
        }
    }
 
    // CraftBukkit start
    private ArrayList<String[]> buildCommands(String[] args, int pos) {
        ArrayList<String[]> commands = new ArrayList<String[]>();
        EntityPlayer[] players = ((java.util.List<EntityPlayer>)PlayerSelector.getPlayers(this, args[pos], EntityPlayer.class)).toArray(new EntityPlayer[0]);
        if (players != null) {
            for (EntityPlayer player : players) {
                if (player.world != this.getWorld()) {
                    continue;
                }
                String[] command = args.clone();
                command[pos] = player.getName();
                commands.add(command);
            }
        }

        return commands;
    }
    // CraftBukkit end

    public String getName() {
        return this.f;
    }

    public IChatBaseComponent getScoreboardDisplayName() {
        return new ChatComponentText(this.getName());
    }

    public void setName(String s) {
        this.f = s;
    }

    public void sendMessage(IChatBaseComponent ichatbasecomponent) {
        if (this.c && this.getWorld() != null && !this.getWorld().isStatic) {
            this.d = (new ChatComponentText("[" + CommandBlockListenerAbstract.a.format(new Date()) + "] ")).addSibling(ichatbasecomponent);
            this.h();
        }

    }

    public boolean getSendCommandFeedback() {
        MinecraftServer minecraftserver = MinecraftServer.getServer();

        return minecraftserver == null || !minecraftserver.N() || minecraftserver.worldServer[0].getGameRules().getBoolean("commandBlockOutput");
    }

    public void a(EnumCommandResult enumcommandresult, int i) {
        this.g.a(this, enumcommandresult, i);
    }

    public abstract void h();

    public void b(IChatBaseComponent ichatbasecomponent) {
        this.d = ichatbasecomponent;
    }

    public void a(boolean flag) {
        this.c = flag;
    }

    public boolean m() {
        return this.c;
    }

    public boolean a(EntityHuman entityhuman) {
        if (!entityhuman.abilities.canInstantlyBuild) {
            return false;
        } else {
            if (entityhuman.getWorld().isStatic) {
                entityhuman.a(this);
            }

            return true;
        }
    }

    public CommandObjectiveExecutor n() {
        return this.g;
    }
}
