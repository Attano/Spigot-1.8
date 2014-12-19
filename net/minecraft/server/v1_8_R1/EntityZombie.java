package net.minecraft.server;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

//CraftBukkit start
import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityTargetEvent;
//CraftBukkit end

public class EntityZombie extends EntityMonster {

    protected static final IAttribute b = (new AttributeRanged((IAttribute) null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).a("Spawn Reinforcements Chance");
    private static final UUID c = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AttributeModifier bk = new AttributeModifier(EntityZombie.c, "Baby speed boost", 0.5D, 1);
    private final PathfinderGoalBreakDoor bl = new PathfinderGoalBreakDoor(this);
    private int bm;
    private boolean bn = false;
    private float bo = -1.0F;
    private float bp;
    private int lastTick = MinecraftServer.currentTick; // CraftBukkit - add field

    public EntityZombie(World world) {
        super(world);
        ((Navigation) this.getNavigation()).b(true);
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, false));
        this.goalSelector.a(2, this.a);
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.n();
        this.a(0.6F, 1.95F);
    }

    protected void n() {
        if ( world.spigotConfig.zombieAggressiveTowardsVillager ) this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityVillager.class, 1.0D, true)); // Spigot
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityIronGolem.class, 1.0D, true));
        this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 1.0D, false));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[] { EntityPigZombie.class}));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        if ( world.spigotConfig.zombieAggressiveTowardsVillager ) this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, false)); // Spigot
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityIronGolem.class, true));
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.b).setValue(35.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.23000000417232513D);
        this.getAttributeInstance(GenericAttributes.e).setValue(3.0D);
        this.getAttributeMap().b(EntityZombie.b).setValue(this.random.nextDouble() * 0.10000000149011612D);
    }

    protected void h() {
        super.h();
        this.getDataWatcher().a(12, Byte.valueOf((byte) 0));
        this.getDataWatcher().a(13, Byte.valueOf((byte) 0));
        this.getDataWatcher().a(14, Byte.valueOf((byte) 0));
    }

    public int bq() {
        int i = super.bq() + 2;

        if (i > 20) {
            i = 20;
        }

        return i;
    }

    public boolean cl() {
        return this.bn;
    }

    public void a(boolean flag) {
        if (this.bn != flag) {
            this.bn = flag;
            if (flag) {
                this.goalSelector.a(1, this.bl);
            } else {
                this.goalSelector.a((PathfinderGoal) this.bl);
            }
        }

    }

    public boolean isBaby() {
        return this.getDataWatcher().getByte(12) == 1;
    }

    protected int getExpValue(EntityHuman entityhuman) {
        if (this.isBaby()) {
            this.b_ = (int) ((float) this.b_ * 2.5F);
        }

        return super.getExpValue(entityhuman);
    }

    public void setBaby(boolean flag) {
        this.getDataWatcher().watch(12, Byte.valueOf((byte) (flag ? 1 : 0)));
        if (this.world != null && !this.world.isStatic) {
            AttributeInstance attributeinstance = this.getAttributeInstance(GenericAttributes.d);

            attributeinstance.c(EntityZombie.bk);
            if (flag) {
                attributeinstance.b(EntityZombie.bk);
            }
        }

        this.n(flag);
    }

    public boolean isVillager() {
        return this.getDataWatcher().getByte(13) == 1;
    }

    public void setVillager(boolean flag) {
        this.getDataWatcher().watch(13, Byte.valueOf((byte) (flag ? 1 : 0)));
    }

    public void m() {
        if (this.world.w() && !this.world.isStatic && !this.isBaby()) {
            float f = this.c(1.0F);
            BlockPosition blockposition = new BlockPosition(this.locX, (double) Math.round(this.locY), this.locZ);

            if (f > 0.5F && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.i(blockposition)) {
                boolean flag = true;
                ItemStack itemstack = this.getEquipment(4);

                if (itemstack != null) {
                    if (itemstack.e()) {
                        itemstack.setData(itemstack.h() + this.random.nextInt(2));
                        if (itemstack.h() >= itemstack.j()) {
                            this.b(itemstack);
                            this.setEquipment(4, (ItemStack) null);
                        }
                    }

                    flag = false;
                }

                if (flag) {
                    // CraftBukkit start
                    EntityCombustEvent event = new EntityCombustEvent(this.getBukkitEntity(), 8);
                    this.world.getServer().getPluginManager().callEvent(event);

                    if (!event.isCancelled()) {
                        this.setOnFire(event.getDuration());
                    }
                    // CraftBukkit end
                }
            }
        }

        if (this.av() && this.getGoalTarget() != null && this.vehicle instanceof EntityChicken) {
            ((EntityInsentient) this.vehicle).getNavigation().a(this.getNavigation().j(), 1.5D);
        }

        super.m();
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        if (super.damageEntity(damagesource, f)) {
            EntityLiving entityliving = this.getGoalTarget();

            if (entityliving == null && damagesource.getEntity() instanceof EntityLiving) {
                entityliving = (EntityLiving) damagesource.getEntity();
            }

            if (entityliving != null && this.world.getDifficulty() == EnumDifficulty.HARD && (double) this.random.nextFloat() < this.getAttributeInstance(EntityZombie.b).getValue()) {
                int i = MathHelper.floor(this.locX);
                int j = MathHelper.floor(this.locY);
                int k = MathHelper.floor(this.locZ);
                EntityZombie entityzombie = new EntityZombie(this.world);

                for (int l = 0; l < 50; ++l) {
                    int i1 = i + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
                    int j1 = j + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);
                    int k1 = k + MathHelper.nextInt(this.random, 7, 40) * MathHelper.nextInt(this.random, -1, 1);

                    if (World.a((IBlockAccess) this.world, new BlockPosition(i1, j1 - 1, k1)) && this.world.getLightLevel(new BlockPosition(i1, j1, k1)) < 10) {
                        entityzombie.setPosition((double) i1, (double) j1, (double) k1);
                        if (!this.world.isPlayerNearby((double) i1, (double) j1, (double) k1, 7.0D) && this.world.a(entityzombie.getBoundingBox(), (Entity) entityzombie) && this.world.getCubes(entityzombie, entityzombie.getBoundingBox()).isEmpty() && !this.world.containsLiquid(entityzombie.getBoundingBox())) {
                            this.world.addEntity(entityzombie, CreatureSpawnEvent.SpawnReason.REINFORCEMENTS); // CraftBukkit
                            entityzombie.setGoalTarget(entityliving, EntityTargetEvent.TargetReason.REINFORCEMENT_TARGET, true);
                            entityzombie.prepare(this.world.E(new BlockPosition(entityzombie)), (GroupDataEntity) null);
                            this.getAttributeInstance(EntityZombie.b).b(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
                            entityzombie.getAttributeInstance(EntityZombie.b).b(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
                            break;
                        }
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public void s_() {
        if (!this.world.isStatic && this.cn()) {
            int i = this.cp();
 
            // CraftBukkit start - Use wall time instead of ticks for villager conversion
            int elapsedTicks = MinecraftServer.currentTick - this.lastTick;
            this.lastTick = MinecraftServer.currentTick;
            i *= elapsedTicks;
            // CraftBukkit end

            this.bm -= i;
            if (this.bm <= 0) {
                this.co();
            }
        }

        super.s_();
    }

    public boolean r(Entity entity) {
        boolean flag = super.r(entity);

        if (flag) {
            int i = this.world.getDifficulty().a();

            if (this.bz() == null && this.isBurning() && this.random.nextFloat() < (float) i * 0.3F) {
                // CraftBukkit start
                EntityCombustByEntityEvent event = new EntityCombustByEntityEvent(this.getBukkitEntity(), entity.getBukkitEntity(), 2 * i);
                this.world.getServer().getPluginManager().callEvent(event);

                if (!event.isCancelled()) {
                    entity.setOnFire(event.getDuration());
                }
                // CraftBukkit end
            }
        }

        return flag;
    }

    protected String z() {
        return "mob.zombie.say";
    }

    protected String bn() {
        return "mob.zombie.hurt";
    }

    protected String bo() {
        return "mob.zombie.death";
    }

    protected void a(BlockPosition blockposition, Block block) {
        this.makeSound("mob.zombie.step", 0.15F, 1.0F);
    }

    protected Item getLoot() {
        return Items.ROTTEN_FLESH;
    }

    public EnumMonsterType getMonsterType() {
        return EnumMonsterType.UNDEAD;
    }

    protected void getRareDrop() {
        switch (this.random.nextInt(3)) {
        case 0:
            this.a(Items.IRON_INGOT, 1);
            break;

        case 1:
            this.a(Items.CARROT, 1);
            break;

        case 2:
            this.a(Items.POTATO, 1);
        }

    }

    protected void a(DifficultyDamageScaler difficultydamagescaler) {
        super.a(difficultydamagescaler);
        if (this.random.nextFloat() < (this.world.getDifficulty() == EnumDifficulty.HARD ? 0.05F : 0.01F)) {
            int i = this.random.nextInt(3);

            if (i == 0) {
                this.setEquipment(0, new ItemStack(Items.IRON_SWORD));
            } else {
                this.setEquipment(0, new ItemStack(Items.IRON_SHOVEL));
            }
        }

    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.isBaby()) {
            nbttagcompound.setBoolean("IsBaby", true);
        }

        if (this.isVillager()) {
            nbttagcompound.setBoolean("IsVillager", true);
        }

        nbttagcompound.setInt("ConversionTime", this.cn() ? this.bm : -1);
        nbttagcompound.setBoolean("CanBreakDoors", this.cl());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.getBoolean("IsBaby")) {
            this.setBaby(true);
        }

        if (nbttagcompound.getBoolean("IsVillager")) {
            this.setVillager(true);
        }

        if (nbttagcompound.hasKeyOfType("ConversionTime", 99) && nbttagcompound.getInt("ConversionTime") > -1) {
            this.a(nbttagcompound.getInt("ConversionTime"));
        }

        this.a(nbttagcompound.getBoolean("CanBreakDoors"));
    }

    public void a(EntityLiving entityliving) {
        super.a(entityliving);
        if ((this.world.getDifficulty() == EnumDifficulty.NORMAL || this.world.getDifficulty() == EnumDifficulty.HARD) && entityliving instanceof EntityVillager) {
            if (this.world.getDifficulty() != EnumDifficulty.HARD && this.random.nextBoolean()) {
                return;
            }

            EntityZombie entityzombie = new EntityZombie(this.world);

            entityzombie.m(entityliving);
            this.world.kill(entityliving);
            entityzombie.prepare(this.world.E(new BlockPosition(entityzombie)), (GroupDataEntity) null);
            entityzombie.setVillager(true);
            if (entityliving.isBaby()) {
                entityzombie.setBaby(true);
            }

            this.world.addEntity(entityzombie, CreatureSpawnEvent.SpawnReason.INFECTION); // CraftBukkit - add SpawnReason
            this.world.a((EntityHuman) null, 1016, new BlockPosition((int) this.locX, (int) this.locY, (int) this.locZ), 0);
        }

    }

    public float getHeadHeight() {
        float f = 1.74F;

        if (this.isBaby()) {
            f = (float) ((double) f - 0.81D);
        }

        return f;
    }

    protected boolean a(ItemStack itemstack) {
        return itemstack.getItem() == Items.EGG && this.isBaby() && this.av() ? false : super.a(itemstack);
    }

    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, GroupDataEntity groupdataentity) {
        Object object = super.prepare(difficultydamagescaler, groupdataentity);
        float f = difficultydamagescaler.c();

        this.j(this.random.nextFloat() < 0.55F * f);
        if (object == null) {
            object = new GroupDataZombie(this, this.world.random.nextFloat() < 0.05F, this.world.random.nextFloat() < 0.05F, (EmptyClassZombie) null);
        }

        if (object instanceof GroupDataZombie) {
            GroupDataZombie groupdatazombie = (GroupDataZombie) object;

            if (groupdatazombie.b) {
                this.setVillager(true);
            }

            if (groupdatazombie.a) {
                this.setBaby(true);
                if ((double) this.world.random.nextFloat() < 0.05D) {
                    List list = this.world.a(EntityChicken.class, this.getBoundingBox().grow(5.0D, 3.0D, 5.0D), IEntitySelector.b);

                    if (!list.isEmpty()) {
                        EntityChicken entitychicken = (EntityChicken) list.get(0);

                        entitychicken.l(true);
                        this.mount(entitychicken);
                    }
                } else if ((double) this.world.random.nextFloat() < 0.05D) {
                    EntityChicken entitychicken1 = new EntityChicken(this.world);

                    entitychicken1.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, 0.0F);
                    entitychicken1.prepare(difficultydamagescaler, (GroupDataEntity) null);
                    entitychicken1.l(true);
                    this.world.addEntity(entitychicken1, CreatureSpawnEvent.SpawnReason.MOUNT);
                    this.mount(entitychicken1);
                }
            }
        }

        this.a(this.random.nextFloat() < f * 0.1F);
        this.a(difficultydamagescaler);
        this.b(difficultydamagescaler);
        if (this.getEquipment(4) == null) {
            Calendar calendar = this.world.Y();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.random.nextFloat() < 0.25F) {
                this.setEquipment(4, new ItemStack(this.random.nextFloat() < 0.1F ? Blocks.LIT_PUMPKIN : Blocks.PUMPKIN));
                this.dropChances[4] = 0.0F;
            }
        }

        this.getAttributeInstance(GenericAttributes.c).b(new AttributeModifier("Random spawn bonus", this.random.nextDouble() * 0.05000000074505806D, 0));
        double d0 = this.random.nextDouble() * 1.5D * (double) f;

        if (d0 > 1.0D) {
            this.getAttributeInstance(GenericAttributes.b).b(new AttributeModifier("Random zombie-spawn bonus", d0, 2));
        }

        if (this.random.nextFloat() < f * 0.05F) {
            this.getAttributeInstance(EntityZombie.b).b(new AttributeModifier("Leader zombie bonus", this.random.nextDouble() * 0.25D + 0.5D, 0));
            this.getAttributeInstance(GenericAttributes.maxHealth).b(new AttributeModifier("Leader zombie bonus", this.random.nextDouble() * 3.0D + 1.0D, 2));
            this.a(true);
        }

        return (GroupDataEntity) object;
    }

    public boolean a(EntityHuman entityhuman) {
        ItemStack itemstack = entityhuman.bY();

        if (itemstack != null && itemstack.getItem() == Items.GOLDEN_APPLE && itemstack.getData() == 0 && this.isVillager() && this.hasEffect(MobEffectList.WEAKNESS)) {
            if (!entityhuman.abilities.canInstantlyBuild) {
                --itemstack.count;
            }

            if (itemstack.count <= 0) {
                entityhuman.inventory.setItem(entityhuman.inventory.itemInHandIndex, (ItemStack) null);
            }

            if (!this.world.isStatic) {
                this.a(this.random.nextInt(2401) + 3600);
            }

            return true;
        } else {
            return false;
        }
    }

    protected void a(int i) {
        this.bm = i;
        this.getDataWatcher().watch(14, Byte.valueOf((byte) 1));
        this.removeEffect(MobEffectList.WEAKNESS.id);
        this.addEffect(new MobEffect(MobEffectList.INCREASE_DAMAGE.id, i, Math.min(this.world.getDifficulty().a() - 1, 0)));
        this.world.broadcastEntityEffect(this, (byte) 16);
    }

    protected boolean isTypeNotPersistent() {
        return !this.cn();
    }

    public boolean cn() {
        return this.getDataWatcher().getByte(14) == 1;
    }

    protected void co() {
        EntityVillager entityvillager = new EntityVillager(this.world);

        entityvillager.m(this);
        entityvillager.prepare(this.world.E(new BlockPosition(entityvillager)), (GroupDataEntity) null);
        entityvillager.cn();
        if (this.isBaby()) {
            entityvillager.setAgeRaw(-24000);
        }

        this.world.kill(this);
        this.world.addEntity(entityvillager, CreatureSpawnEvent.SpawnReason.CURED); // CraftBukkit - add SpawnReason
        entityvillager.addEffect(new MobEffect(MobEffectList.CONFUSION.id, 200, 0));
        this.world.a((EntityHuman) null, 1017, new BlockPosition((int) this.locX, (int) this.locY, (int) this.locZ), 0);
    }

    protected int cp() {
        int i = 1;

        if (this.random.nextFloat() < 0.01F) {
            int j = 0;

            for (int k = (int) this.locX - 4; k < (int) this.locX + 4 && j < 14; ++k) {
                for (int l = (int) this.locY - 4; l < (int) this.locY + 4 && j < 14; ++l) {
                    for (int i1 = (int) this.locZ - 4; i1 < (int) this.locZ + 4 && j < 14; ++i1) {
                        Block block = this.world.getType(new BlockPosition(k, l, i1)).getBlock();

                        if (block == Blocks.IRON_BARS || block == Blocks.BED) {
                            if (this.random.nextFloat() < 0.3F) {
                                ++i;
                            }

                            ++j;
                        }
                    }
                }
            }
        }

        return i;
    }

    public void n(boolean flag) {
        this.a(flag ? 0.5F : 1.0F);
    }

    protected final void a(float f, float f1) {
        boolean flag = this.bo > 0.0F && this.bp > 0.0F;

        this.bo = f;
        this.bp = f1;
        if (!flag) {
            this.a(1.0F);
        }

    }

    protected final void a(float f) {
        super.a(this.bo * f, this.bp * f);
    }

    public double am() {
        return super.am() - 0.5D;
    }

    public void die(DamageSource damagesource) {
        super.die(damagesource);
        if (damagesource.getEntity() instanceof EntityCreeper && !(this instanceof EntityPigZombie) && ((EntityCreeper) damagesource.getEntity()).isPowered() && ((EntityCreeper) damagesource.getEntity()).cn()) {
            ((EntityCreeper) damagesource.getEntity()).co();
            this.a(new ItemStack(Items.SKULL, 1, 2), 0.0F);
        }

    }
}
