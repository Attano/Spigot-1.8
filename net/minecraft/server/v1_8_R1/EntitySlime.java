package net.minecraft.server;

// CraftBukkit start
import org.bukkit.event.entity.SlimeSplitEvent;
// CraftBukkit end

public class EntitySlime extends EntityInsentient implements IMonster {

    public float a;
    public float b;
    public float c;
    private boolean bi;

    public EntitySlime(World world) {
        super(world);
        this.moveController = new ControllerMoveSlime(this);
        this.goalSelector.a(1, new PathfinderGoalSlimeRandomJump(this));
        this.goalSelector.a(2, new PathfinderGoalSlimeNearestPlayer(this));
        this.goalSelector.a(3, new PathfinderGoalSlimeRandomDirection(this));
        this.goalSelector.a(5, new PathfinderGoalSlimeIdle(this));
        this.targetSelector.a(1, new PathfinderGoalTargetNearestPlayer(this));
        this.targetSelector.a(3, new PathfinderGoalNearestAttackableTargetInsentient(this, EntityIronGolem.class));
    }

    protected void h() {
        super.h();
        this.datawatcher.a(16, Byte.valueOf((byte) 1));
    }

    public void setSize(int i) {
        this.datawatcher.watch(16, Byte.valueOf((byte) i));
        this.a(0.51000005F * (float) i, 0.51000005F * (float) i);
        this.setPosition(this.locX, this.locY, this.locZ);
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue((double) (i * i));
        this.getAttributeInstance(GenericAttributes.d).setValue((double) (0.2F + 0.1F * (float) i));
        this.setHealth(this.getMaxHealth());
        this.b_ = i;
    }

    public int getSize() {
        return this.datawatcher.getByte(16);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Size", this.getSize() - 1);
        nbttagcompound.setBoolean("wasOnGround", this.bi);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        int i = nbttagcompound.getInt("Size");

        if (i < 0) {
            i = 0;
        }

        this.setSize(i + 1);
        this.bi = nbttagcompound.getBoolean("wasOnGround");
    }

    protected EnumParticle n() {
        return EnumParticle.SLIME;
    }

    protected String ci() {
        return "mob.slime." + (this.getSize() > 1 ? "big" : "small");
    }

    public void s_() {
        if (!this.world.isStatic && this.world.getDifficulty() == EnumDifficulty.PEACEFUL && this.getSize() > 0) {
            this.dead = true;
        }

        this.b += (this.a - this.b) * 0.5F;
        this.c = this.b;
        super.s_();
        if (this.onGround && !this.bi) {
            int i = this.getSize();

            for (int j = 0; j < i * 8; ++j) {
                float f = this.random.nextFloat() * 3.1415927F * 2.0F;
                float f1 = this.random.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float) i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float) i * 0.5F * f1;
                World world = this.world;
                EnumParticle enumparticle = this.n();
                double d0 = this.locX + (double) f2;
                double d1 = this.locZ + (double) f3;

                world.addParticle(enumparticle, d0, this.getBoundingBox().b, d1, 0.0D, 0.0D, 0.0D, new int[0]);
            }

            if (this.cj()) {
                this.makeSound(this.ci(), this.bA(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.a = -0.5F;
        } else if (!this.onGround && this.bi) {
            this.a = 1.0F;
        }

        this.bi = this.onGround;
        this.cf();
    }

    protected void cf() {
        this.a *= 0.6F;
    }

    protected int ce() {
        return this.random.nextInt(20) + 10;
    }

    protected EntitySlime cd() {
        return new EntitySlime(this.world);
    }

    public void i(int i) {
        if (i == 16) {
            int j = this.getSize();

            this.a(0.51000005F * (float) j, 0.51000005F * (float) j);
            this.yaw = this.aI;
            this.aG = this.aI;
            if (this.V() && this.random.nextInt(20) == 0) {
                this.X();
            }
        }

        super.i(i);
    }

    public void die() {
        int i = this.getSize();

        if (!this.world.isStatic && i > 1 && this.getHealth() <= 0.0F) {
            int j = 2 + this.random.nextInt(3);
 
            // CraftBukkit start
            SlimeSplitEvent event = new SlimeSplitEvent((org.bukkit.entity.Slime) this.getBukkitEntity(), j);
            this.world.getServer().getPluginManager().callEvent(event);

            if (!event.isCancelled() && event.getCount() > 0) {
                j = event.getCount();
            } else {
                super.die();
                return;
            }
            // CraftBukkit end

            for (int k = 0; k < j; ++k) {
                float f = ((float) (k % 2) - 0.5F) * (float) i / 4.0F;
                float f1 = ((float) (k / 2) - 0.5F) * (float) i / 4.0F;
                EntitySlime entityslime = this.cd();

                if (this.hasCustomName()) {
                    entityslime.setCustomName(this.getCustomName());
                }

                if (this.isPersistent()) {
                    entityslime.bW();
                }

                entityslime.setSize(i / 2);
                entityslime.setPositionRotation(this.locX + (double) f, this.locY + 0.5D, this.locZ + (double) f1, this.random.nextFloat() * 360.0F, 0.0F);
                this.world.addEntity(entityslime, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.SLIME_SPLIT); // CraftBukkit - SpawnReason
            }
        }

        super.die();
    }

    public void collide(Entity entity) {
        super.collide(entity);
        if (entity instanceof EntityIronGolem && this.cg()) {
            this.e((EntityLiving) entity);
        }

    }

    public void d(EntityHuman entityhuman) {
        if (this.cg()) {
            this.e((EntityLiving) entityhuman);
        }

    }

    protected void e(EntityLiving entityliving) {
        int i = this.getSize();

        if (this.hasLineOfSight(entityliving) && this.h(entityliving) < 0.6D * (double) i * 0.6D * (double) i && entityliving.damageEntity(DamageSource.mobAttack(this), (float) this.ch())) {
            this.makeSound("mob.attack", 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.a((EntityLiving) this, (Entity) entityliving);
        }

    }

    public float getHeadHeight() {
        return 0.625F * this.length;
    }

    protected boolean cg() {
        return this.getSize() > 1;
    }

    protected int ch() {
        return this.getSize();
    }

    protected String bn() {
        return "mob.slime." + (this.getSize() > 1 ? "big" : "small");
    }

    protected String bo() {
        return "mob.slime." + (this.getSize() > 1 ? "big" : "small");
    }

    protected Item getLoot() {
        return this.getSize() == 1 ? Items.SLIME : null;
    }

    public boolean bQ() {
        Chunk chunk = this.world.getChunkAtWorldCoords(new BlockPosition(MathHelper.floor(this.locX), 0, MathHelper.floor(this.locZ)));

        if (this.world.getWorldData().getType() == WorldType.FLAT && this.random.nextInt(4) != 1) {
            return false;
        } else {
            if (this.world.getDifficulty() != EnumDifficulty.PEACEFUL) {
                BiomeBase biomebase = this.world.getBiome(new BlockPosition(MathHelper.floor(this.locX), 0, MathHelper.floor(this.locZ)));

                if (biomebase == BiomeBase.SWAMPLAND && this.locY > 50.0D && this.locY < 70.0D && this.random.nextFloat() < 0.5F && this.random.nextFloat() < this.world.y() && this.world.getLightLevel(new BlockPosition(this)) <= this.random.nextInt(8)) {
                    return super.bQ();
                }

                if (this.random.nextInt(10) == 0 && chunk.a(987234911L).nextInt(10) == 0 && this.locY < 40.0D) {
                    return super.bQ();
                }
            }

            return false;
        }
    }

    protected float bA() {
        return 0.4F * (float) this.getSize();
    }

    public int bP() {
        return 0;
    }

    protected boolean cl() {
        return this.getSize() > 0;
    }

    protected boolean cj() {
        return this.getSize() > 2;
    }

    protected void bE() {
        this.motY = 0.41999998688697815D;
        this.ai = true;
    }

    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, GroupDataEntity groupdataentity) {
        int i = this.random.nextInt(3);

        if (i < 2 && this.random.nextFloat() < 0.5F * difficultydamagescaler.c()) {
            ++i;
        }

        int j = 1 << i;

        this.setSize(j);
        return super.prepare(difficultydamagescaler, groupdataentity);
    }
}
