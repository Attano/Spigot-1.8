package net.minecraft.server;

import com.google.common.base.Predicate;

public class EntityOcelot extends EntityTameableAnimal {

    private PathfinderGoalAvoidTarget bm;
    private PathfinderGoalTempt bn;
    public boolean spawnBonus = true; // Spigot

    public EntityOcelot(World world) {
        super(world);
        this.a(0.6F, 0.7F);
        ((Navigation) this.getNavigation()).a(true);
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, this.bk);
        this.goalSelector.a(3, this.bn = new PathfinderGoalTempt(this, 0.6D, Items.FISH, true));
        this.goalSelector.a(5, new PathfinderGoalFollowOwner(this, 1.0D, 10.0F, 5.0F));
        this.goalSelector.a(6, new PathfinderGoalJumpOnBlock(this, 0.8D));
        this.goalSelector.a(7, new PathfinderGoalLeapAtTarget(this, 0.3F));
        this.goalSelector.a(8, new PathfinderGoalOcelotAttack(this));
        this.goalSelector.a(9, new PathfinderGoalBreed(this, 0.8D));
        this.goalSelector.a(10, new PathfinderGoalRandomStroll(this, 0.8D));
        this.goalSelector.a(11, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 10.0F));
        this.targetSelector.a(1, new PathfinderGoalRandomTargetNonTamed(this, EntityChicken.class, false, (Predicate) null));
    }

    protected void h() {
        super.h();
        this.datawatcher.a(18, Byte.valueOf((byte) 0));
    }

    // Spigot start - When this ocelot begins standing, chests below this ocelot must be
    // updated as if its contents have changed. We update chests if this ocelot is sitting
    // knowing that it may be dead, gone, or standing after this method returns.
    // Called each tick on each ocelot.
    @Override
    public void s_() {
        if (this.world.spigotConfig.altHopperTicking && this.isSitting()) {
            int xi = MathHelper.floor(this.getBoundingBox().a);
            int yi = MathHelper.floor(this.getBoundingBox().b) - 1;
            int zi = MathHelper.floor(this.getBoundingBox().c);
            int xf = MathHelper.floor(this.getBoundingBox().d);
            int yf = MathHelper.floor(this.getBoundingBox().e) - 1;
            int zf = MathHelper.floor(this.getBoundingBox().f);
            for (int a = xi; a <= xf; a++) {
                for (int c = zi; c <= zf; c++) {
                    for (int b = yi; b <= yf; b++) {
                        this.world.updateChestAndHoppers(new BlockPosition(a, b, c));
                    }
                }
            }
        }
        super.s_();
    }
    // Spigot end

    public void E() {
        if (this.getControllerMove().a()) {
            double d0 = this.getControllerMove().b();

            if (d0 == 0.6D) {
                this.setSneaking(true);
                this.setSprinting(false);
            } else if (d0 == 1.33D) {
                this.setSneaking(false);
                this.setSprinting(true);
            } else {
                this.setSneaking(false);
                this.setSprinting(false);
            }
        } else {
            this.setSneaking(false);
            this.setSprinting(false);
        }

    }

    protected boolean isTypeNotPersistent() {
        return !this.isTamed() /*&& this.ticksLived > 2400*/; // CraftBukkit
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(10.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.30000001192092896D);
    }

    public void e(float f, float f1) {}

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("CatType", this.getCatType());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setCatType(nbttagcompound.getInt("CatType"));
    }

    protected String z() {
        return this.isTamed() ? (this.cp() ? "mob.cat.purr" : (this.random.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
    }

    protected String bn() {
        return "mob.cat.hitt";
    }

    protected String bo() {
        return "mob.cat.hitt";
    }

    protected float bA() {
        return 0.4F;
    }

    protected Item getLoot() {
        return Items.LEATHER;
    }

    public boolean r(Entity entity) {
        return entity.damageEntity(DamageSource.mobAttack(this), 3.0F);
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (this.isInvulnerable(damagesource)) {
            return false;
        } else {
            this.bk.setSitting(false);
            return super.damageEntity(damagesource, f);
        }
    }

    protected void dropDeathLoot(boolean flag, int i) {}

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.inventory.getItemInHand();

        if (this.isTamed()) {
            if (this.e((EntityLiving) entityhuman) && !this.world.isStatic && !this.d(itemstack)) {
                this.bk.setSitting(!this.isSitting());
            }
        } else if (this.bn.f() && itemstack != null && itemstack.getItem() == Items.FISH && entityhuman.h(this) < 9.0D) {
            if (!entityhuman.abilities.canInstantlyBuild) {
                --itemstack.count;
            }

            if (itemstack.count <= 0) {
                entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
            }

            if (!this.world.isStatic) {
                // CraftBukkit - added event call and isCancelled check
                if (this.random.nextInt(3) == 0 && !org.bukkit.craftbukkit.event.CraftEventFactory.callEntityTameEvent(this, entityhuman).isCancelled()) {
                    this.setTamed(true);
                    this.setCatType(1 + this.world.random.nextInt(3));
                    this.setOwnerUUID(entityhuman.getUniqueID().toString());
                    this.l(true);
                    this.bk.setSitting(true);
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

    public EntityOcelot b(EntityAgeable entityageable) {
        EntityOcelot entityocelot = new EntityOcelot(this.world);

        if (this.isTamed()) {
            entityocelot.setOwnerUUID(this.getOwnerUUID());
            entityocelot.setTamed(true);
            entityocelot.setCatType(this.getCatType());
        }

        return entityocelot;
    }

    public boolean d(ItemStack itemstack) {
        return itemstack != null && itemstack.getItem() == Items.FISH;
    }

    public boolean mate(EntityAnimal entityanimal) {
        if (entityanimal == this) {
            return false;
        } else if (!this.isTamed()) {
            return false;
        } else if (!(entityanimal instanceof EntityOcelot)) {
            return false;
        } else {
            EntityOcelot entityocelot = (EntityOcelot) entityanimal;

            return !entityocelot.isTamed() ? false : this.cp() && entityocelot.cp();
        }
    }

    public int getCatType() {
        return this.datawatcher.getByte(18);
    }

    public void setCatType(int i) {
        this.datawatcher.watch(18, Byte.valueOf((byte) i));
    }

    public boolean bQ() {
        return this.world.random.nextInt(3) != 0;
    }

    public boolean canSpawn() {
        if (this.world.a(this.getBoundingBox(), (Entity) this) && this.world.getCubes(this, this.getBoundingBox()).isEmpty() && !this.world.containsLiquid(this.getBoundingBox())) {
            BlockPosition blockposition = new BlockPosition(this.locX, this.getBoundingBox().b, this.locZ);

            if (blockposition.getY() < 63) {
                return false;
            }

            Block block = this.world.getType(blockposition.down()).getBlock();

            if (block == Blocks.GRASS || block.getMaterial() == Material.LEAVES) {
                return true;
            }
        }

        return false;
    }

    public String getName() {
        return this.hasCustomName() ? this.getCustomName() : (this.isTamed() ? LocaleI18n.get("entity.Cat.name") : super.getName());
    }

    public void setTamed(boolean flag) {
        super.setTamed(flag);
    }

    protected void ck() {
        if (this.bm == null) {
            this.bm = new PathfinderGoalAvoidTarget(this, new EntitySelectorOceletHuman(this), 16.0F, 0.8D, 1.33D);
        }

        this.goalSelector.a((PathfinderGoal) this.bm);
        if (!this.isTamed()) {
            this.goalSelector.a(4, this.bm);
        }

    }

    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, GroupDataEntity groupdataentity) {
        groupdataentity = super.prepare(difficultydamagescaler, groupdataentity);
        if (spawnBonus && this.world.random.nextInt(7) == 0) { // Spigot
            for (int i = 0; i < 2; ++i) {
                EntityOcelot entityocelot = new EntityOcelot(this.world);

                entityocelot.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, 0.0F);
                entityocelot.setAgeRaw(-24000);
                this.world.addEntity(entityocelot, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.OCELOT_BABY); // CraftBukkit - add SpawnReason
            }
        }

        return groupdataentity;
    }

    public EntityAgeable createChild(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
