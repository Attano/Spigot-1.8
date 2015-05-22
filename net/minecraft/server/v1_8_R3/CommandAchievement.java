package net.minecraft.server;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CommandAchievement extends CommandAbstract {

    public CommandAchievement() {}

    public String getCommand() {
        return "achievement";
    }

    public int a() {
        return 2;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.achievement.usage";
    }

    public void execute(ICommandListener icommandlistener, String[] astring) throws CommandException {
        if (astring.length < 2) {
            throw new ExceptionUsage("commands.achievement.usage", new Object[0]);
        } else {
            final Statistic statistic = StatisticList.getStatistic(astring[1]);

            if (statistic == null && !astring[1].equals("*")) {
                throw new CommandException("commands.achievement.unknownAchievement", new Object[] { astring[1]});
            } else {
                final EntityPlayer entityplayer = astring.length >= 3 ? a(icommandlistener, astring[2]) : b(icommandlistener);
                boolean flag = astring[0].equalsIgnoreCase("give");
                boolean flag1 = astring[0].equalsIgnoreCase("take");

                if (flag || flag1) {
                    if (statistic == null) {
                        Iterator iterator;
                        Achievement achievement;

                        if (flag) {
                            iterator = AchievementList.e.iterator();

                            while (iterator.hasNext()) {
                                achievement = (Achievement) iterator.next();
                                entityplayer.b((Statistic) achievement);
                            }

                            a(icommandlistener, this, "commands.achievement.give.success.all", new Object[] { entityplayer.getName()});
                        } else if (flag1) {
                            iterator = Lists.reverse(AchievementList.e).iterator();

                            while (iterator.hasNext()) {
                                achievement = (Achievement) iterator.next();
                                entityplayer.a((Statistic) achievement);
                            }

                            a(icommandlistener, this, "commands.achievement.take.success.all", new Object[] { entityplayer.getName()});
                        }

                    } else {
                        if (statistic instanceof Achievement) {
                            Achievement achievement1 = (Achievement) statistic;
                            ArrayList arraylist;

                            if (flag) {
                                if (entityplayer.getStatisticManager().hasAchievement(achievement1)) {
                                    throw new CommandException("commands.achievement.alreadyHave", new Object[] { entityplayer.getName(), statistic.j()});
                                }

                                for (arraylist = Lists.newArrayList(); achievement1.c != null && !entityplayer.getStatisticManager().hasAchievement(achievement1.c); achievement1 = achievement1.c) {
                                    arraylist.add(achievement1.c);
                                }

                                Iterator iterator1 = Lists.reverse(arraylist).iterator();

                                while (iterator1.hasNext()) {
                                    Achievement achievement2 = (Achievement) iterator1.next();

                                    entityplayer.b((Statistic) achievement2);
                                }
                            } else if (flag1) {
                                if (!entityplayer.getStatisticManager().hasAchievement(achievement1)) {
                                    throw new CommandException("commands.achievement.dontHave", new Object[] { entityplayer.getName(), statistic.j()});
                                }

                                arraylist = Lists.newArrayList(Iterators.filter(AchievementList.e.iterator(), new Predicate() {
                                    public boolean a(Achievement achievement) {
                                        return entityplayer.getStatisticManager().hasAchievement(achievement) && achievement != statistic;
                                    }

                                    public boolean apply(Object object) {
                                        return this.a((Achievement) object);
                                    }
                                }));
                                ArrayList arraylist1 = Lists.newArrayList(arraylist);
                                Iterator iterator2 = arraylist.iterator();

                                Achievement achievement3;

                                while (iterator2.hasNext()) {
                                    achievement3 = (Achievement) iterator2.next();
                                    Achievement achievement4 = achievement3;

                                    boolean flag2;

                                    for (flag2 = false; achievement4 != null; achievement4 = achievement4.c) {
                                        if (achievement4 == statistic) {
                                            flag2 = true;
                                        }
                                    }

                                    if (!flag2) {
                                        for (achievement4 = achievement3; achievement4 != null; achievement4 = achievement4.c) {
                                            arraylist1.remove(achievement3);
                                        }
                                    }
                                }

                                iterator2 = arraylist1.iterator();

                                while (iterator2.hasNext()) {
                                    achievement3 = (Achievement) iterator2.next();
                                    entityplayer.a((Statistic) achievement3);
                                }
                            }
                        }

                        if (flag) {
                            entityplayer.b(statistic);
                            a(icommandlistener, this, "commands.achievement.give.success.one", new Object[] { entityplayer.getName(), statistic.j()});
                        } else if (flag1) {
                            entityplayer.a(statistic);
                            a(icommandlistener, this, "commands.achievement.take.success.one", new Object[] { statistic.j(), entityplayer.getName()});
                        }

                    }
                }
            }
        }
    }

    public List<String> tabComplete(ICommandListener icommandlistener, String[] astring, BlockPosition blockposition) {
        if (astring.length == 1) {
            return a(astring, new String[] { "give", "take"});
        } else if (astring.length != 2) {
            return astring.length == 3 ? a(astring, MinecraftServer.getServer().getPlayers()) : null;
        } else {
            ArrayList arraylist = Lists.newArrayList();
            Iterator iterator = StatisticList.stats.iterator();

            while (iterator.hasNext()) {
                Statistic statistic = (Statistic) iterator.next();

                arraylist.add(statistic.name);
            }

            return a(astring, (Collection) arraylist);
        }
    }

    public boolean isListStart(String[] astring, int i) {
        return i == 2;
    }
}
