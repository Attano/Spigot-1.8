package net.minecraft.server;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandDebug extends CommandAbstract {

    private static final Logger a = LogManager.getLogger();
    private long b;
    private int c;

    public CommandDebug() {}

    public String getCommand() {
        return "debug";
    }

    public int a() {
        return 3;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.debug.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 1) {
            throw new ExceptionUsage("commands.debug.usage", new Object[0]);
        } else {
            if (astring[0].equals("start")) {
                if (astring.length != 1) {
                    throw new ExceptionUsage("commands.debug.usage", new Object[0]);
                }

                a(icommandlistener, this, "commands.debug.start", new Object[0]);
                MinecraftServer.getServer().au();
                this.b = MinecraftServer.az();
                this.c = MinecraftServer.getServer().at();
            } else {
                if (!astring[0].equals("stop")) {
                    throw new ExceptionUsage("commands.debug.usage", new Object[0]);
                }

                if (astring.length != 1) {
                    throw new ExceptionUsage("commands.debug.usage", new Object[0]);
                }

                if (!MinecraftServer.getServer().methodProfiler.a) {
                    throw new CommandException("commands.debug.notStarted", new Object[0]);
                }

                long i = MinecraftServer.az();
                int j = MinecraftServer.getServer().at();
                long k = i - this.b;
                int l = j - this.c;

                this.a(k, l);
                MinecraftServer.getServer().methodProfiler.a = false;
                a(icommandlistener, this, "commands.debug.stop", new Object[] { Float.valueOf((float) k / 1000.0F), Integer.valueOf(l)});
            }

        }
    }

    private void a(long i, int j) {
        File file = new File(MinecraftServer.getServer().d("debug"), "profile-results-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + ".txt");

        file.getParentFile().mkdirs();

        try {
            FileWriter filewriter = new FileWriter(file);

            filewriter.write(this.b(i, j));
            filewriter.close();
        } catch (Throwable throwable) {
            CommandDebug.a.error("Could not save profiler results to " + file, throwable);
        }

    }

    private String b(long i, int j) {
        StringBuilder stringbuilder = new StringBuilder();

        stringbuilder.append("---- Minecraft Profiler Results ----\n");
        stringbuilder.append("// ");
        stringbuilder.append(d());
        stringbuilder.append("\n\n");
        stringbuilder.append("Time span: ").append(i).append(" ms\n");
        stringbuilder.append("Tick span: ").append(j).append(" ticks\n");
        stringbuilder.append("// This is approximately ").append(String.format("%.2f", new Object[] { Float.valueOf((float) j / ((float) i / 1000.0F))})).append(" ticks per second. It should be ").append(20).append(" ticks per second\n\n");
        stringbuilder.append("--- BEGIN PROFILE DUMP ---\n\n");
        this.a(0, "root", stringbuilder);
        stringbuilder.append("--- END PROFILE DUMP ---\n\n");
        return stringbuilder.toString();
    }

    private void a(int i, String s, StringBuilder stringbuilder) {
        List list = MinecraftServer.getServer().methodProfiler.b(s);

        if (list != null && list.size() >= 3) {
            for (int j = 1; j < list.size(); ++j) {
                MethodProfiler.ProfilerInfo methodprofiler_profilerinfo = (MethodProfiler.ProfilerInfo) list.get(j);

                stringbuilder.append(String.format("[%02d] ", new Object[] { Integer.valueOf(i)}));

                for (int k = 0; k < i; ++k) {
                    stringbuilder.append(" ");
                }

                stringbuilder.append(methodprofiler_profilerinfo.c).append(" - ").append(String.format("%.2f", new Object[] { Double.valueOf(methodprofiler_profilerinfo.a)})).append("%/").append(String.format("%.2f", new Object[] { Double.valueOf(methodprofiler_profilerinfo.b)})).append("%\n");
                if (!methodprofiler_profilerinfo.c.equals("unspecified")) {
                    try {
                        this.a(i + 1, s + "." + methodprofiler_profilerinfo.c, stringbuilder);
                    } catch (Exception exception) {
                        stringbuilder.append("[[ EXCEPTION ").append(exception).append(" ]]");
                    }
                }
            }

        }
    }

    private static String d() {
        String[] astring = new String[] { "Shiny numbers!", "Am I not running fast enough? :(", "I\'m working as hard as I can!", "Will I ever be good enough for you? :(", "Speedy. Zoooooom!", "Hello world", "40% better than a crash report.", "Now with extra numbers", "Now with less numbers", "Now with the same numbers", "You should add flames to things, it makes them go faster!", "Do you feel the need for... optimization?", "*cracks redstone whip*", "Maybe if you treated it better then it\'ll have more motivation to work faster! Poor server."};

        try {
            return astring[(int) (System.nanoTime() % (long) astring.length)];
        } catch (Throwable throwable) {
            return "Witty comment unavailable :(";
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        return astring.length == 1 ? a(astring, new String[] { "start", "stop"}) : null;
    }
}
