package net.minecraft.server;

import com.google.common.base.Predicate;

class CommandAchievementFilter implements Predicate {

    final EntityPlayer a;
    final Statistic b;
    final CommandAchievement c;

    CommandAchievementFilter(CommandAchievement commandachievement, EntityPlayer entityplayer, Statistic statistic) {
        this.c = commandachievement;
        this.a = entityplayer;
        this.b = statistic;
    }

    public boolean a(Achievement achievement) {
        return this.a.getStatisticManager().hasAchievement(achievement) && achievement != this.b;
    }

    public boolean apply(Object object) {
        return this.a((Achievement) object);
    }
}
