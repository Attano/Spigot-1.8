package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandTrigger extends CommandAbstract {

    public CommandTrigger() {}

    public String getCommand() {
        return "trigger";
    }

    public int a() {
        return 0;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.trigger.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 3) {
            throw new ExceptionUsage("commands.trigger.usage", new Object[0]);
        } else {
            EntityPlayer entityplayer;

            if (icommandlistener instanceof EntityPlayer) {
                entityplayer = (EntityPlayer) icommandlistener;
            } else {
                Entity entity = icommandlistener.f();

                if (!(entity instanceof EntityPlayer)) {
                    throw new CommandException("commands.trigger.invalidPlayer", new Object[0]);
                }

                entityplayer = (EntityPlayer) entity;
            }

            Scoreboard scoreboard = MinecraftServer.getServer().getWorldServer(0).getScoreboard();
            ScoreboardObjective scoreboardobjective = scoreboard.getObjective(astring[0]);

            if (scoreboardobjective != null && scoreboardobjective.getCriteria() == IScoreboardCriteria.c) {
                int i = a(astring[2]);

                if (!scoreboard.b(entityplayer.getName(), scoreboardobjective)) {
                    throw new CommandException("commands.trigger.invalidObjective", new Object[] { astring[0]});
                } else {
                    ScoreboardScore scoreboardscore = scoreboard.getPlayerScoreForObjective(entityplayer.getName(), scoreboardobjective);

                    if (scoreboardscore.g()) {
                        throw new CommandException("commands.trigger.disabled", new Object[] { astring[0]});
                    } else {
                        if ("set".equals(astring[1])) {
                            scoreboardscore.setScore(i);
                        } else {
                            if (!"add".equals(astring[1])) {
                                throw new CommandException("commands.trigger.invalidMode", new Object[] { astring[1]});
                            }

                            scoreboardscore.addScore(i);
                        }

                        scoreboardscore.a(true);
                        if (entityplayer.playerInteractManager.isCreative()) {
                            a(icommandlistener, this, "commands.trigger.success", new Object[] { astring[0], astring[1], astring[2]});
                        }

                    }
                }
            } else {
                throw new CommandException("commands.trigger.invalidObjective", new Object[] { astring[0]});
            }
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        if (astring.length == 1) {
            Scoreboard scoreboard = MinecraftServer.getServer().getWorldServer(0).getScoreboard();
            ArrayList arraylist = Lists.newArrayList();
            Iterator iterator = scoreboard.getObjectives().iterator();

            while (iterator.hasNext()) {
                ScoreboardObjective scoreboardobjective = (ScoreboardObjective) iterator.next();

                if (scoreboardobjective.getCriteria() == IScoreboardCriteria.c) {
                    arraylist.add(scoreboardobjective.getName());
                }
            }

            return a(astring, (String[]) arraylist.toArray(new String[arraylist.size()]));
        } else {
            return astring.length == 2 ? a(astring, new String[] { "add", "set"}) : null;
        }
    }
}
