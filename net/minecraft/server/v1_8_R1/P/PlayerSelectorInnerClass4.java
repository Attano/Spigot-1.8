package net.minecraft.server;

import com.google.common.base.Predicate;

final class PlayerSelectorInnerClass4 implements Predicate {

    final String a;
    final boolean b;

    PlayerSelectorInnerClass4(String s, boolean flag) {
        this.a = s;
        this.b = flag;
    }

    public boolean a(Entity entity) {
        if (!(entity instanceof EntityLiving)) {
            return false;
        } else {
            EntityLiving entityliving = (EntityLiving) entity;
            ScoreboardTeamBase scoreboardteambase = entityliving.getScoreboardTeam();
            String s = scoreboardteambase == null ? "" : scoreboardteambase.getName();

            return s.equals(this.a) != this.b;
        }
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
