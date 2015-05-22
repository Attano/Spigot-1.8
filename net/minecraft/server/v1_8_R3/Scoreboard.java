package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Scoreboard {

    private final Map<String, ScoreboardObjective> objectivesByName = Maps.newHashMap();
    private final Map<IScoreboardCriteria, List<ScoreboardObjective>> objectivesByCriteria = Maps.newHashMap();
    private final Map<String, Map<ScoreboardObjective, ScoreboardScore>> playerScores = Maps.newHashMap();
    private final ScoreboardObjective[] displaySlots = new ScoreboardObjective[19];
    private final Map<String, ScoreboardTeam> teamsByName = Maps.newHashMap();
    private final Map<String, ScoreboardTeam> teamsByPlayer = Maps.newHashMap();
    private static String[] g = null;

    public Scoreboard() {}

    public ScoreboardObjective getObjective(String s) {
        return (ScoreboardObjective) this.objectivesByName.get(s);
    }

    public ScoreboardObjective registerObjective(String s, IScoreboardCriteria iscoreboardcriteria) {
        if (s.length() > 16) {
            throw new IllegalArgumentException("The objective name \'" + s + "\' is too long!");
        } else {
            ScoreboardObjective scoreboardobjective = this.getObjective(s);

            if (scoreboardobjective != null) {
                throw new IllegalArgumentException("An objective with the name \'" + s + "\' already exists!");
            } else {
                scoreboardobjective = new ScoreboardObjective(this, s, iscoreboardcriteria);
                Object object = (List) this.objectivesByCriteria.get(iscoreboardcriteria);

                if (object == null) {
                    object = Lists.newArrayList();
                    this.objectivesByCriteria.put(iscoreboardcriteria, object);
                }

                ((List) object).add(scoreboardobjective);
                this.objectivesByName.put(s, scoreboardobjective);
                this.handleObjectiveAdded(scoreboardobjective);
                return scoreboardobjective;
            }
        }
    }

    public Collection<ScoreboardObjective> getObjectivesForCriteria(IScoreboardCriteria iscoreboardcriteria) {
        Collection collection = (Collection) this.objectivesByCriteria.get(iscoreboardcriteria);

        return collection == null ? Lists.newArrayList() : Lists.newArrayList(collection);
    }

    public boolean b(String s, ScoreboardObjective scoreboardobjective) {
        Map map = (Map) this.playerScores.get(s);

        if (map == null) {
            return false;
        } else {
            ScoreboardScore scoreboardscore = (ScoreboardScore) map.get(scoreboardobjective);

            return scoreboardscore != null;
        }
    }

    public ScoreboardScore getPlayerScoreForObjective(String s, ScoreboardObjective scoreboardobjective) {
        if (s.length() > 40) {
            throw new IllegalArgumentException("The player name \'" + s + "\' is too long!");
        } else {
            Object object = (Map) this.playerScores.get(s);

            if (object == null) {
                object = Maps.newHashMap();
                this.playerScores.put(s, object);
            }

            ScoreboardScore scoreboardscore = (ScoreboardScore) ((Map) object).get(scoreboardobjective);

            if (scoreboardscore == null) {
                scoreboardscore = new ScoreboardScore(this, scoreboardobjective, s);
                ((Map) object).put(scoreboardobjective, scoreboardscore);
            }

            return scoreboardscore;
        }
    }

    public Collection<ScoreboardScore> getScoresForObjective(ScoreboardObjective scoreboardobjective) {
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = this.playerScores.values().iterator();

        while (iterator.hasNext()) {
            Map map = (Map) iterator.next();
            ScoreboardScore scoreboardscore = (ScoreboardScore) map.get(scoreboardobjective);

            if (scoreboardscore != null) {
                arraylist.add(scoreboardscore);
            }
        }

        Collections.sort(arraylist, ScoreboardScore.a);
        return arraylist;
    }

    public Collection<ScoreboardObjective> getObjectives() {
        return this.objectivesByName.values();
    }

    public Collection<String> getPlayers() {
        return this.playerScores.keySet();
    }

    public void resetPlayerScores(String s, ScoreboardObjective scoreboardobjective) {
        Map map;

        if (scoreboardobjective == null) {
            map = (Map) this.playerScores.remove(s);
            if (map != null) {
                this.handlePlayerRemoved(s);
            }
        } else {
            map = (Map) this.playerScores.get(s);
            if (map != null) {
                ScoreboardScore scoreboardscore = (ScoreboardScore) map.remove(scoreboardobjective);

                if (map.size() < 1) {
                    Map map1 = (Map) this.playerScores.remove(s);

                    if (map1 != null) {
                        this.handlePlayerRemoved(s);
                    }
                } else if (scoreboardscore != null) {
                    this.a(s, scoreboardobjective);
                }
            }
        }

    }

    public Collection<ScoreboardScore> getScores() {
        Collection collection = this.playerScores.values();
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            Map map = (Map) iterator.next();

            arraylist.addAll(map.values());
        }

        return arraylist;
    }

    public Map<ScoreboardObjective, ScoreboardScore> getPlayerObjectives(String s) {
        Object object = (Map) this.playerScores.get(s);

        if (object == null) {
            object = Maps.newHashMap();
        }

        return (Map) object;
    }

    public void unregisterObjective(ScoreboardObjective scoreboardobjective) {
        this.objectivesByName.remove(scoreboardobjective.getName());

        for (int i = 0; i < 19; ++i) {
            if (this.getObjectiveForSlot(i) == scoreboardobjective) {
                this.setDisplaySlot(i, (ScoreboardObjective) null);
            }
        }

        List list = (List) this.objectivesByCriteria.get(scoreboardobjective.getCriteria());

        if (list != null) {
            list.remove(scoreboardobjective);
        }

        Iterator iterator = this.playerScores.values().iterator();

        while (iterator.hasNext()) {
            Map map = (Map) iterator.next();

            map.remove(scoreboardobjective);
        }

        this.handleObjectiveRemoved(scoreboardobjective);
    }

    public void setDisplaySlot(int i, ScoreboardObjective scoreboardobjective) {
        this.displaySlots[i] = scoreboardobjective;
    }

    public ScoreboardObjective getObjectiveForSlot(int i) {
        return this.displaySlots[i];
    }

    public ScoreboardTeam getTeam(String s) {
        return (ScoreboardTeam) this.teamsByName.get(s);
    }

    public ScoreboardTeam createTeam(String s) {
        if (s.length() > 16) {
            throw new IllegalArgumentException("The team name \'" + s + "\' is too long!");
        } else {
            ScoreboardTeam scoreboardteam = this.getTeam(s);

            if (scoreboardteam != null) {
                throw new IllegalArgumentException("A team with the name \'" + s + "\' already exists!");
            } else {
                scoreboardteam = new ScoreboardTeam(this, s);
                this.teamsByName.put(s, scoreboardteam);
                this.handleTeamAdded(scoreboardteam);
                return scoreboardteam;
            }
        }
    }

    public void removeTeam(ScoreboardTeam scoreboardteam) {
        this.teamsByName.remove(scoreboardteam.getName());
        Iterator iterator = scoreboardteam.getPlayerNameSet().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();

            this.teamsByPlayer.remove(s);
        }

        this.handleTeamRemoved(scoreboardteam);
    }

    public boolean addPlayerToTeam(String s, String s1) {
        if (s.length() > 40) {
            throw new IllegalArgumentException("The player name \'" + s + "\' is too long!");
        } else if (!this.teamsByName.containsKey(s1)) {
            return false;
        } else {
            ScoreboardTeam scoreboardteam = this.getTeam(s1);

            if (this.getPlayerTeam(s) != null) {
                this.removePlayerFromTeam(s);
            }

            this.teamsByPlayer.put(s, scoreboardteam);
            scoreboardteam.getPlayerNameSet().add(s);
            return true;
        }
    }

    public boolean removePlayerFromTeam(String s) {
        ScoreboardTeam scoreboardteam = this.getPlayerTeam(s);

        if (scoreboardteam != null) {
            this.removePlayerFromTeam(s, scoreboardteam);
            return true;
        } else {
            return false;
        }
    }

    public void removePlayerFromTeam(String s, ScoreboardTeam scoreboardteam) {
        if (this.getPlayerTeam(s) != scoreboardteam) {
            throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team \'" + scoreboardteam.getName() + "\'.");
        } else {
            this.teamsByPlayer.remove(s);
            scoreboardteam.getPlayerNameSet().remove(s);
        }
    }

    public Collection<String> getTeamNames() {
        return this.teamsByName.keySet();
    }

    public Collection<ScoreboardTeam> getTeams() {
        return this.teamsByName.values();
    }

    public ScoreboardTeam getPlayerTeam(String s) {
        return (ScoreboardTeam) this.teamsByPlayer.get(s);
    }

    public void handleObjectiveAdded(ScoreboardObjective scoreboardobjective) {}

    public void handleObjectiveChanged(ScoreboardObjective scoreboardobjective) {}

    public void handleObjectiveRemoved(ScoreboardObjective scoreboardobjective) {}

    public void handleScoreChanged(ScoreboardScore scoreboardscore) {}

    public void handlePlayerRemoved(String s) {}

    public void a(String s, ScoreboardObjective scoreboardobjective) {}

    public void handleTeamAdded(ScoreboardTeam scoreboardteam) {}

    public void handleTeamChanged(ScoreboardTeam scoreboardteam) {}

    public void handleTeamRemoved(ScoreboardTeam scoreboardteam) {}

    public static String getSlotName(int i) {
        switch (i) {
        case 0:
            return "list";

        case 1:
            return "sidebar";

        case 2:
            return "belowName";

        default:
            if (i >= 3 && i <= 18) {
                EnumChatFormat enumchatformat = EnumChatFormat.a(i - 3);

                if (enumchatformat != null && enumchatformat != EnumChatFormat.RESET) {
                    return "sidebar.team." + enumchatformat.e();
                }
            }

            return null;
        }
    }

    public static int getSlotForName(String s) {
        if (s.equalsIgnoreCase("list")) {
            return 0;
        } else if (s.equalsIgnoreCase("sidebar")) {
            return 1;
        } else if (s.equalsIgnoreCase("belowName")) {
            return 2;
        } else {
            if (s.startsWith("sidebar.team.")) {
                String s1 = s.substring("sidebar.team.".length());
                EnumChatFormat enumchatformat = EnumChatFormat.b(s1);

                if (enumchatformat != null && enumchatformat.b() >= 0) {
                    return enumchatformat.b() + 3;
                }
            }

            return -1;
        }
    }

    public static String[] h() {
        if (Scoreboard.g == null) {
            Scoreboard.g = new String[19];

            for (int i = 0; i < 19; ++i) {
                Scoreboard.g[i] = getSlotName(i);
            }
        }

        return Scoreboard.g;
    }

    public void a(Entity entity) {
        if (entity != null && !(entity instanceof EntityHuman) && !entity.isAlive()) {
            String s = entity.getUniqueID().toString();

            this.resetPlayerScores(s, (ScoreboardObjective) null);
            this.removePlayerFromTeam(s);
        }
    }
}
