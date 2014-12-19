package net.minecraft.server;

public enum EnumGamemode {

    NOT_SET(-1, ""), SURVIVAL(0, "survival"), CREATIVE(1, "creative"), ADVENTURE(2, "adventure"), SPECTATOR(3, "spectator");

    int f;
    String g;

    private EnumGamemode(int i, String s) {
        this.f = i;
        this.g = s;
    }

    public int getId() {
        return this.f;
    }

    public String b() {
        return this.g;
    }

    public void a(PlayerAbilities playerabilities) {
        if (this == EnumGamemode.CREATIVE) {
            playerabilities.canFly = true;
            playerabilities.canInstantlyBuild = true;
            playerabilities.isInvulnerable = true;
        } else if (this == EnumGamemode.SPECTATOR) {
            playerabilities.canFly = true;
            playerabilities.canInstantlyBuild = false;
            playerabilities.isInvulnerable = true;
            playerabilities.isFlying = true;
        } else {
            playerabilities.canFly = false;
            playerabilities.canInstantlyBuild = false;
            playerabilities.isInvulnerable = false;
            playerabilities.isFlying = false;
        }

        playerabilities.mayBuild = !this.c();
    }

    public boolean c() {
        return this == EnumGamemode.ADVENTURE || this == EnumGamemode.SPECTATOR;
    }

    public boolean d() {
        return this == EnumGamemode.CREATIVE;
    }

    public boolean e() {
        return this == EnumGamemode.SURVIVAL || this == EnumGamemode.ADVENTURE;
    }

    public static EnumGamemode getById(int i) {
        EnumGamemode[] aenumgamemode = values();
        int j = aenumgamemode.length;

        for (int k = 0; k < j; ++k) {
            EnumGamemode enumgamemode = aenumgamemode[k];

            if (enumgamemode.f == i) {
                return enumgamemode;
            }
        }

        return EnumGamemode.SURVIVAL;
    }
}
