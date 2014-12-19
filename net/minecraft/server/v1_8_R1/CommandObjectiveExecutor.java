package net.minecraft.server;

public class CommandObjectiveExecutor {

    private static final int a = EnumCommandResult.values().length;
    private static final String[] b = new String[CommandObjectiveExecutor.a];
    private String[] c;
    private String[] d;

    public CommandObjectiveExecutor() {
        this.c = CommandObjectiveExecutor.b;
        this.d = CommandObjectiveExecutor.b;
    }

    public void a(ICommandListener icommandlistener, EnumCommandResult enumcommandresult, int i) {
        String s = this.c[enumcommandresult.a()];

        if (s != null) {
            String s1;

            try {
                s1 = CommandAbstract.e(icommandlistener, s);
            } catch (ExceptionEntityNotFound exceptionentitynotfound) {
                return;
            }

            String s2 = this.d[enumcommandresult.a()];

            if (s2 != null) {
                Scoreboard scoreboard = icommandlistener.getWorld().getScoreboard();
                ScoreboardObjective scoreboardobjective = scoreboard.getObjective(s2);

                if (scoreboardobjective != null) {
                    if (scoreboard.b(s1, scoreboardobjective)) {
                        ScoreboardScore scoreboardscore = scoreboard.getPlayerScoreForObjective(s1, scoreboardobjective);

                        scoreboardscore.setScore(i);
                    }
                }
            }
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        if (nbttagcompound.hasKeyOfType("CommandStats", 10)) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("CommandStats");
            EnumCommandResult[] aenumcommandresult = EnumCommandResult.values();
            int i = aenumcommandresult.length;

            for (int j = 0; j < i; ++j) {
                EnumCommandResult enumcommandresult = aenumcommandresult[j];
                String s = enumcommandresult.b() + "Name";
                String s1 = enumcommandresult.b() + "Objective";

                if (nbttagcompound1.hasKeyOfType(s, 8) && nbttagcompound1.hasKeyOfType(s1, 8)) {
                    String s2 = nbttagcompound1.getString(s);
                    String s3 = nbttagcompound1.getString(s1);

                    a(this, enumcommandresult, s2, s3);
                }
            }

        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        EnumCommandResult[] aenumcommandresult = EnumCommandResult.values();
        int i = aenumcommandresult.length;

        for (int j = 0; j < i; ++j) {
            EnumCommandResult enumcommandresult = aenumcommandresult[j];
            String s = this.c[enumcommandresult.a()];
            String s1 = this.d[enumcommandresult.a()];

            if (s != null && s1 != null) {
                nbttagcompound1.setString(enumcommandresult.b() + "Name", s);
                nbttagcompound1.setString(enumcommandresult.b() + "Objective", s1);
            }
        }

        if (!nbttagcompound1.isEmpty()) {
            nbttagcompound.set("CommandStats", nbttagcompound1);
        }

    }

    public static void a(CommandObjectiveExecutor commandobjectiveexecutor, EnumCommandResult enumcommandresult, String s, String s1) {
        if (s != null && s.length() != 0 && s1 != null && s1.length() != 0) {
            if (commandobjectiveexecutor.c == CommandObjectiveExecutor.b || commandobjectiveexecutor.d == CommandObjectiveExecutor.b) {
                commandobjectiveexecutor.c = new String[CommandObjectiveExecutor.a];
                commandobjectiveexecutor.d = new String[CommandObjectiveExecutor.a];
            }

            commandobjectiveexecutor.c[enumcommandresult.a()] = s;
            commandobjectiveexecutor.d[enumcommandresult.a()] = s1;
        } else {
            a(commandobjectiveexecutor, enumcommandresult);
        }
    }

    private static void a(CommandObjectiveExecutor commandobjectiveexecutor, EnumCommandResult enumcommandresult) {
        if (commandobjectiveexecutor.c != CommandObjectiveExecutor.b && commandobjectiveexecutor.d != CommandObjectiveExecutor.b) {
            commandobjectiveexecutor.c[enumcommandresult.a()] = null;
            commandobjectiveexecutor.d[enumcommandresult.a()] = null;
            boolean flag = true;
            EnumCommandResult[] aenumcommandresult = EnumCommandResult.values();
            int i = aenumcommandresult.length;

            for (int j = 0; j < i; ++j) {
                EnumCommandResult enumcommandresult1 = aenumcommandresult[j];

                if (commandobjectiveexecutor.c[enumcommandresult1.a()] != null && commandobjectiveexecutor.d[enumcommandresult1.a()] != null) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                commandobjectiveexecutor.c = CommandObjectiveExecutor.b;
                commandobjectiveexecutor.d = CommandObjectiveExecutor.b;
            }

        }
    }

    public void a(CommandObjectiveExecutor commandobjectiveexecutor) {
        EnumCommandResult[] aenumcommandresult = EnumCommandResult.values();
        int i = aenumcommandresult.length;

        for (int j = 0; j < i; ++j) {
            EnumCommandResult enumcommandresult = aenumcommandresult[j];

            a(this, enumcommandresult, commandobjectiveexecutor.c[enumcommandresult.a()], commandobjectiveexecutor.d[enumcommandresult.a()]);
        }

    }
}
