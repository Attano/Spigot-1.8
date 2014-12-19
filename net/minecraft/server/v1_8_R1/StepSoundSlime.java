package net.minecraft.server;

final class StepSoundSlime extends StepSound {

    StepSoundSlime(String s, float f, float f1) {
        super(s, f, f1);
    }

    public String getBreakSound() {
        return "mob.slime.big";
    }

    public String getPlaceSound() {
        return "mob.slime.big";
    }

    public String getStepSound() {
        return "mob.slime.small";
    }
}
