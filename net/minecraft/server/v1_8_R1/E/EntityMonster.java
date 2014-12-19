package net.minecraft.server;

import org.bukkit.event.entity.EntityCombustByEntityEvent; // CraftBukkit

public abstract class EntityMonster extends EntityCreature implements IMonster {

    protected final PathfinderGoal a = new PathfinderGoalAvoidTarget(this, new EntitySelectorExplodingCreeper(this), 4.0F, 1.0D, 2.0D);

    public EntityMonster(World world) {
        super(world);
        this.b_ = 5;
    }

    public void m() {
        this.bw();
        float f = this.c(1.0F);

        if (f > 0.5F) {
            this.aO += 2;
        }

        super.m();
    }

    public void s_() {
        super.s_();
        if (!this.world.isStatic && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
            this.die();
        }

    }

    protected String P() {
        return "game.hostile.swim";
    }

    protected String aa() {
        return "game.hostile.swim.splash";
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (this.isInvulnerable(damagesource)) {
            return false;
        } else if (super.damageEntity(damagesource, f)) {
            Entity entity = damagesource.getEntity();

            return this.passenger != entity && this.vehicle != entity ? true : true;
        } else {
            return false;
        }
    }

    protected String bn() {
        return "game.hostile.hurt";
    }

    protected String bo() {
        return "game.hostile.die";
    }

    protected String n(int i) {
        return i > 4 ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
    }

    public boolean r(Entity entity) {
        float f = (float) this.getAttributeInstance(GenericAttributes.e).getValue();
        int i = 0;

        if (entity instanceof EntityLiving) {
            f += EnchantmentManager.a(this.bz(), ((EntityLiving) entity).getMonsterType());
            i += EnchantmentManager.a((EntityLiving) this);
        }

        boolean flag = entity.damageEntity(DamageSource.mobAttack(this), f);

        if (flag) {
            if (i > 0) {
                entity.g((double) (-MathHelper.sin(this.yaw * 3.1415927F / 180.0F) * (float) i * 0.5F), 0.1D, (double) (MathHelper.cos(this.yaw * 3.1415927F / 180.0F) * (float) i * 0.5F));
                this.motX *= 0.6D;
                this.motZ *= 0.6D;
            }

            int j = EnchantmentManager.getFireAspectEnchantmentLevel(this);

            if (j > 0) {
                // CraftBukkit start - Call a combust event when somebody hits with a fire enchanted item
                EntityCombustByEntityEvent combustEvent = new EntityCombustByEntityEvent(this.getBukkitEntity(), entity.getBukkitEntity(), j * 4);
                org.bukkit.Bukkit.getPluginManager().callEvent(combustEvent);

                if (!combustEvent.isCancelled()) {
                    entity.setOnFire(combustEvent.getDuration());
                }
                // CraftBukkit end
            }

            this.a((EntityLiving) this, entity);
        }

        return flag;
    }

    public float a(BlockPosition blockposition) {
        return 0.5F - this.world.o(blockposition);
    }

    protected boolean m_() {
        BlockPosition blockposition = new BlockPosition(this.locX, this.getBoundingBox().b, this.locZ);

        if (this.world.b(EnumSkyBlock.SKY, blockposition) > this.random.nextInt(32)) {
            return false;
        } else {
            int i = this.world.getLightLevel(blockposition);

            if (this.world.R()) {
                int j = this.world.ab();

                this.world.b(10);
                i = this.world.getLightLevel(blockposition);
                this.world.b(j);
            }

            return i <= this.random.nextInt(8);
        }
    }

    public boolean bQ() {
        return this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.m_() && super.bQ();
    }

    protected void aW() {
        super.aW();
        this.getAttributeMap().b(GenericAttributes.e);
    }

    protected boolean aZ() {
        return true;
    }
}
