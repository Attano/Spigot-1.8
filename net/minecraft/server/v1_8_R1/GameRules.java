package net.minecraft.server;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class GameRules {

    private TreeMap a = new TreeMap();

    public GameRules() {
        this.a("doFireTick", "true", EnumGameRuleType.BOOLEAN_VALUE);
        this.a("mobGriefing", "true", EnumGameRuleType.BOOLEAN_VALUE);
        this.a("keepInventory", "false", EnumGameRuleType.BOOLEAN_VALUE);
        this.a("doMobSpawning", "true", EnumGameRuleType.BOOLEAN_VALUE);
        this.a("doMobLoot", "true", EnumGameRuleType.BOOLEAN_VALUE);
        this.a("doTileDrops", "true", EnumGameRuleType.BOOLEAN_VALUE);
        this.a("commandBlockOutput", "true", EnumGameRuleType.BOOLEAN_VALUE);
        this.a("naturalRegeneration", "true", EnumGameRuleType.BOOLEAN_VALUE);
        this.a("doDaylightCycle", "true", EnumGameRuleType.BOOLEAN_VALUE);
        this.a("logAdminCommands", "true", EnumGameRuleType.BOOLEAN_VALUE);
        this.a("showDeathMessages", "true", EnumGameRuleType.BOOLEAN_VALUE);
        this.a("randomTickSpeed", "3", EnumGameRuleType.NUMERICAL_VALUE);
        this.a("sendCommandFeedback", "true", EnumGameRuleType.BOOLEAN_VALUE);
        this.a("reducedDebugInfo", "false", EnumGameRuleType.BOOLEAN_VALUE);
    }

    public void a(String s, String s1, EnumGameRuleType enumgameruletype) {
        this.a.put(s, new GameRuleValue(s1, enumgameruletype));
    }

    public void set(String s, String s1) {
        GameRuleValue gamerulevalue = (GameRuleValue) this.a.get(s);

        if (gamerulevalue != null) {
            gamerulevalue.a(s1);
        } else {
            this.a(s, s1, EnumGameRuleType.ANY_VALUE);
        }

    }

    public String get(String s) {
        GameRuleValue gamerulevalue = (GameRuleValue) this.a.get(s);

        return gamerulevalue != null ? gamerulevalue.a() : "";
    }

    public boolean getBoolean(String s) {
        GameRuleValue gamerulevalue = (GameRuleValue) this.a.get(s);

        return gamerulevalue != null ? gamerulevalue.b() : false;
    }

    public int c(String s) {
        GameRuleValue gamerulevalue = (GameRuleValue) this.a.get(s);

        return gamerulevalue != null ? gamerulevalue.c() : 0;
    }

    public NBTTagCompound a() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        Iterator iterator = this.a.keySet().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            GameRuleValue gamerulevalue = (GameRuleValue) this.a.get(s);

            nbttagcompound.setString(s, gamerulevalue.a());
        }

        return nbttagcompound;
    }

    public void a(NBTTagCompound nbttagcompound) {
        Set set = nbttagcompound.c();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            String s1 = nbttagcompound.getString(s);

            this.set(s, s1);
        }

    }

    public String[] getGameRules() {
        return (String[]) this.a.keySet().toArray(new String[0]);
    }

    public boolean contains(String s) {
        return this.a.containsKey(s);
    }

    public boolean a(String s, EnumGameRuleType enumgameruletype) {
        GameRuleValue gamerulevalue = (GameRuleValue) this.a.get(s);

        return gamerulevalue != null && (gamerulevalue.e() == enumgameruletype || enumgameruletype == EnumGameRuleType.ANY_VALUE);
    }
}
