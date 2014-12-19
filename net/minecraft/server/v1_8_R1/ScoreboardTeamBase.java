package net.minecraft.server;

import java.util.Collection;

public abstract class ScoreboardTeamBase {

    public ScoreboardTeamBase() {}

    public boolean isAlly(ScoreboardTeamBase scoreboardteambase) {
        return scoreboardteambase == null ? false : this == scoreboardteambase;
    }

    public abstract String getName();

    public abstract String getFormattedName(String s);

    public abstract boolean allowFriendlyFire();

    public abstract Collection getPlayerNameSet();

    public abstract EnumNameTagVisibility j();
}
