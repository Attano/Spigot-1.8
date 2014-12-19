package net.minecraft.server;

import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class PlayerSelectorInnerClass5 implements Predicate {

    final Map a;

    PlayerSelectorInnerClass5(Map map) {
        this.a = map;
    }

    public boolean a(Entity entity) {
        Scoreboard scoreboard = MinecraftServer.getServer().getWorldServer(0).getScoreboard();
        Iterator iterator = this.a.entrySet().iterator();

        Entry entry;
        boolean flag;
        int i;

        do {
            if (!iterator.hasNext()) {
                return true;
            }

            entry = (Entry) iterator.next();
            String s = (String) entry.getKey();

            flag = false;
            if (s.endsWith("_min") && s.length() > 4) {
                flag = true;
                s = s.substring(0, s.length() - 4);
            }

            ScoreboardObjective scoreboardobjective = scoreboard.getObjective(s);

            if (scoreboardobjective == null) {
                return false;
            }

            String s1 = entity instanceof EntityPlayer ? entity.getName() : entity.getUniqueID().toString();

            if (!scoreboard.b(s1, scoreboardobjective)) {
                return false;
            }

            ScoreboardScore scoreboardscore = scoreboard.getPlayerScoreForObjective(s1, scoreboardobjective);

            i = scoreboardscore.getScore();
            if (i < ((Integer) entry.getValue()).intValue() && flag) {
                return false;
            }
        } while (i <= ((Integer) entry.getValue()).intValue() || flag);

        return false;
    }

    public boolean apply(Object object) {
        return this.a((Entity) object);
    }
}
