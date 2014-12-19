package net.minecraft.server;

public class EntitySilverfish extends EntityMonster {

    private PathfinderGoalSilverfishWakeOthers b;

    public EntitySilverfish(World world) {
        super(world);
        this.a(0.4F, 0.3F);
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(3, this.b = new PathfinderGoalSilverfishWakeOthers(this));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, false));
        this.goalSelector.a(5, new PathfinderGoalSilverfishHideInBlock(this));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[0]));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
    }

    public float getHeadHeight() {
        return 0.1F;
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(8.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.25D);
        this.getAttributeInstance(GenericAttributes.e).setValue(1.0D);
    }

    protected boolean r_() {
        return false;
    }

    protected String z() {
        return "mob.silverfish.say";
    }

    protected String bn() {
        return "mob.silverfish.hit";
    }

    protected String bo() {
        return "mob.silverfish.kill";
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (this.isInvulnerable(damagesource)) {
            return false;
        } else {
            if (damagesource instanceof EntityDamageSource || damagesource == DamageSource.MAGIC) {
                this.b.f();
            }

            return super.damageEntity(damagesource, f);
        }
    }

    protected void a(BlockPosition blockposition, Block block) {
        this.makeSound("mob.silverfish.step", 0.15F, 1.0F);
    }

    protected Item getLoot() {
        return null;
    }

    public void s_() {
        this.aG = this.yaw;
        super.s_();
    }

    public float a(BlockPosition blockposition) {
        return this.world.getType(blockposition.down()).getBlock() == Blocks.STONE ? 10.0F : super.a(blockposition);
    }

    protected boolean m_() {
        return true;
    }

    public boolean bQ() {
        if (super.bQ()) {
            EntityHuman entityhuman = this.world.findNearbyPlayer(this, 5.0D);

            return entityhuman == null;
        } else {
            return false;
        }
    }

    public EnumMonsterType getMonsterType() {
        return EnumMonsterType.ARTHROPOD;
    }
}
