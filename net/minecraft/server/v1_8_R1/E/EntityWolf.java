package net.minecraft.server;

// CraftBukkit start
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
// CraftBukkit end

public class EntityWolf extends EntityTameableAnimal {

    private float bm;
    private float bn;
    private boolean bo;
    private boolean bp;
    private float bq;
    private float br;

    public EntityWolf(World world) {
        super(world);
        this.a(0.6F, 0.8F);
        ((Navigation) this.getNavigation()).a(true);
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, this.bk);
        this.goalSelector.a(3, new PathfinderGoalLeapAtTarget(this, 0.4F));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, true));
        this.goalSelector.a(5, new PathfinderGoalFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.a(6, new PathfinderGoalBreed(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalBeg(this, 8.0F));
        this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(9, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalOwnerHurtByTarget(this));
        this.targetSelector.a(2, new PathfinderGoalOwnerHurtTarget(this));
        this.targetSelector.a(3, new PathfinderGoalHurtByTarget(this, true, new Class[0]));
        this.targetSelector.a(4, new PathfinderGoalRandomTargetNonTamed(this, EntityAnimal.class, false, new EntitySelectorWolfTargetSheepRabbit(this)));
        this.targetSelector.a(5, new PathfinderGoalNearestAttackableTarget(this, EntitySkeleton.class, false));
        this.setTamed(false);
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.d).setValue(0.30000001192092896D);
        if (this.isTamed()) {
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(20.0D);
        } else {
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(8.0D);
        }

        this.getAttributeMap().b(GenericAttributes.e);
        this.getAttributeInstance(GenericAttributes.e).setValue(2.0D);
    }

    public void setGoalTarget(EntityLiving entityliving) {
        super.setGoalTarget(entityliving);
        if (entityliving == null) {
            this.setAngry(false);
        } else if (!this.isTamed()) {
            this.setAngry(true);
        }
    }

    // CraftBukkit - add overriden version
    @Override
    public void setGoalTarget(EntityLiving entityliving, org.bukkit.event.entity.EntityTargetEvent.TargetReason reason, boolean fire) {
        super.setGoalTarget(entityliving, reason, fire);
        if (entityliving == null) {
            this.setAngry(false);
        } else if (!this.isTamed()) {
            this.setAngry(true);
        }
    }
    // CraftBukkit end

    protected void E() {
        this.datawatcher.watch(18, Float.valueOf(this.getHealth()));
    }

    protected void h() {
        super.h();
        this.datawatcher.a(18, new Float(this.getHealth()));
        this.datawatcher.a(19, new Byte((byte) 0));
        this.datawatcher.a(20, new Byte((byte) EnumColor.RED.getColorIndex()));
    }

    protected void a(BlockPosition blockposition, Block block) {
        this.makeSound("mob.wolf.step", 0.15F, 1.0F);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setBoolean("Angry", this.isAngry());
        nbttagcompound.setByte("CollarColor", (byte) this.getCollarColor().getInvColorIndex());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setAngry(nbttagcompound.getBoolean("Angry"));
        if (nbttagcompound.hasKeyOfType("CollarColor", 99)) {
            this.setCollarColor(EnumColor.fromInvColorIndex(nbttagcompound.getByte("CollarColor")));
        }

    }

    protected String z() {
        // CraftBukkit - (getFloat(18) < 10) -> (getFloat(18) < this.getMaxHealth() / 2)
        return this.isAngry() ? "mob.wolf.growl" : (this.random.nextInt(3) == 0 ? (this.isTamed() && this.datawatcher.getFloat(18) < this.getMaxHealth() / 2 ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    protected String bn() {
        return "mob.wolf.hurt";
    }

    protected String bo() {
        return "mob.wolf.death";
    }

    protected float bA() {
        return 0.4F;
    }

    protected Item getLoot() {
        return Item.getById(-1);
    }

    public void m() {
        super.m();
        if (!this.world.isStatic && this.bo && !this.bp && !this.cd() && this.onGround) {
            this.bp = true;
            this.bq = 0.0F;
            this.br = 0.0F;
            this.world.broadcastEntityEffect(this, (byte) 8);
        }

        if (!this.world.isStatic && this.getGoalTarget() == null && this.isAngry()) {
            this.setAngry(false);
        }

    }

    public void s_() {
        super.s_();
        this.bn = this.bm;
        if (this.cv()) {
            this.bm += (1.0F - this.bm) * 0.4F;
        } else {
            this.bm += (0.0F - this.bm) * 0.4F;
        }

        if (this.U()) {
            this.bo = true;
            this.bp = false;
            this.bq = 0.0F;
            this.br = 0.0F;
        } else if ((this.bo || this.bp) && this.bp) {
            if (this.bq == 0.0F) {
                this.makeSound("mob.wolf.shake", this.bA(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            }

            this.br = this.bq;
            this.bq += 0.05F;
            if (this.br >= 2.0F) {
                this.bo = false;
                this.bp = false;
                this.br = 0.0F;
                this.bq = 0.0F;
            }

            if (this.bq > 0.4F) {
                float f = (float) this.getBoundingBox().b;
                int i = (int) (MathHelper.sin((this.bq - 0.4F) * 3.1415927F) * 7.0F);

                for (int j = 0; j < i; ++j) {
                    float f1 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    float f2 = (this.random.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;

                    this.world.addParticle(EnumParticle.WATER_SPLASH, this.locX + (double) f1, (double) (f + 0.8F), this.locZ + (double) f2, this.motX, this.motY, this.motZ, new int[0]);
                }
            }
        }

    }

    public float getHeadHeight() {
        return this.length * 0.8F;
    }

    public int bP() {
        return this.isSitting() ? 20 : super.bP();
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (this.isInvulnerable(damagesource)) {
            return false;
        } else {
            Entity entity = damagesource.getEntity();

            this.bk.setSitting(false);
            if (entity != null && !(entity instanceof EntityHuman) && !(entity instanceof EntityArrow)) {
                f = (f + 1.0F) / 2.0F;
            }

            return super.damageEntity(damagesource, f);
        }
    }

    public boolean r(Entity entity) {
        boolean flag = entity.damageEntity(DamageSource.mobAttack(this), (float) ((int) this.getAttributeInstance(GenericAttributes.e).getValue()));

        if (flag) {
            this.a((EntityLiving) this, entity);
        }

        return flag;
    }

    public void setTamed(boolean flag) {
        super.setTamed(flag);
        if (flag) {
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(20.0D);
        } else {
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(8.0D);
        }

        this.getAttributeInstance(GenericAttributes.e).setValue(4.0D);
    }

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (this.isTamed()) {
            if (itemstack != null) {
                if (itemstack.getItem() instanceof ItemFood) {
                    ItemFood itemfood = (ItemFood) itemstack.getItem();

                    if (itemfood.g() && this.datawatcher.getFloat(18) < 20.0F) {
                        if (!entityhuman.abilities.canInstantlyBuild) {
                            --itemstack.count;
                        }

                        this.heal((float) itemfood.getNutrition(itemstack), org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason.EATING); // CraftBukkit
                        if (itemstack.count <= 0) {
                            entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
                        }

                        return true;
                    }
                } else if (itemstack.getItem() == Items.DYE) {
                    EnumColor enumcolor = EnumColor.fromInvColorIndex(itemstack.getData());

                    if (enumcolor != this.getCollarColor()) {
                        this.setCollarColor(enumcolor);
                        if (!entityhuman.abilities.canInstantlyBuild && --itemstack.count <= 0) {
                            entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
                        }

                        return true;
                    }
                }
            }

            if (this.e((EntityLiving) entityhuman) && !this.world.isStatic && !this.d(itemstack)) {
                this.bk.setSitting(!this.isSitting());
                this.aW = false;
                this.navigation.n();
                this.setGoalTarget((EntityLiving) null, TargetReason.FORGOT_TARGET, true); // CraftBukkit - reason
            }
        } else if (itemstack != null && itemstack.getItem() == Items.BONE && !this.isAngry()) {
            if (!entityhuman.abilities.canInstantlyBuild) {
                --itemstack.count;
            }

            if (itemstack.count <= 0) {
                entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
            }

            if (!this.world.isStatic) {
                // CraftBukkit - added event call and isCancelled check.
                if (this.random.nextInt(3) == 0 && !CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled()) {
                    this.setTamed(true);
                    this.navigation.n();
                    this.setGoalTarget((EntityLiving) null, TargetReason.FORGOT_TARGET, true);
                    this.bk.setSitting(true);
                    this.setHealth(this.getMaxHealth()); // CraftBukkit - 20.0 -> getMaxHealth()
                    this.setOwnerUUID(entityhuman.getUniqueID().toString());
                    this.l(true);
                    this.world.broadcastEntityEffect(this, (byte) 7);
                } else {
                    this.l(false);
                    this.world.broadcastEntityEffect(this, (byte) 6);
                }
            }

            return true;
        }

        return super.a(entityhuman);
    }

    public boolean d(ItemStack itemstack) {
        return itemstack == null ? false : (!(itemstack.getItem() instanceof ItemFood) ? false : ((ItemFood) itemstack.getItem()).g());
    }

    public int bU() {
        return 8;
    }

    public boolean isAngry() {
        return (this.datawatcher.getByte(16) & 2) != 0;
    }

    public void setAngry(boolean flag) {
        byte b0 = this.datawatcher.getByte(16);

        if (flag) {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.datawatcher.watch(16, Byte.valueOf((byte) (b0 & -3)));
        }

    }

    public EnumColor getCollarColor() {
        return EnumColor.fromInvColorIndex(this.datawatcher.getByte(20) & 15);
    }

    public void setCollarColor(EnumColor enumcolor) {
        this.datawatcher.watch(20, Byte.valueOf((byte) (enumcolor.getInvColorIndex() & 15)));
    }

    public EntityWolf b(EntityAgeable entityageable) {
        EntityWolf entitywolf = new EntityWolf(this.world);
        String s = this.getOwnerUUID();

        if (s != null && s.trim().length() > 0) {
            entitywolf.setOwnerUUID(s);
            entitywolf.setTamed(true);
        }

        return entitywolf;
    }

    public void p(boolean flag) {
        if (flag) {
            this.datawatcher.watch(19, Byte.valueOf((byte) 1));
        } else {
            this.datawatcher.watch(19, Byte.valueOf((byte) 0));
        }

    }

    public boolean mate(EntityAnimal entityanimal) {
        if (entityanimal == this) {
            return false;
        } else if (!this.isTamed()) {
            return false;
        } else if (!(entityanimal instanceof EntityWolf)) {
            return false;
        } else {
            EntityWolf entitywolf = (EntityWolf) entityanimal;

            return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false : this.cp() && entitywolf.cp());
        }
    }

    public boolean cv() {
        return this.datawatcher.getByte(19) == 1;
    }

    protected boolean isTypeNotPersistent() {
        return !this.isTamed() /*&& this.ticksLived > 2400*/; // CraftBukkit
    }

    public boolean a(EntityLiving entityliving, EntityLiving entityliving1) {
        if (!(entityliving instanceof EntityCreeper) && !(entityliving instanceof EntityGhast)) {
            if (entityliving instanceof EntityWolf) {
                EntityWolf entitywolf = (EntityWolf) entityliving;

                if (entitywolf.isTamed() && entitywolf.getOwner() == entityliving1) {
                    return false;
                }
            }

            return entityliving instanceof EntityHuman && entityliving1 instanceof EntityHuman && !((EntityHuman) entityliving1).a((EntityHuman) entityliving) ? false : !(entityliving instanceof EntityHorse) || !((EntityHorse) entityliving).isTame();
        } else {
            return false;
        }
    }

    public boolean ca() {
        return !this.isAngry() && super.ca();
    }

    public EntityAgeable createChild(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
