package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class EntityWitch extends EntityMonster implements IRangedEntity {

    private static final UUID b = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
    private static final AttributeModifier c = (new AttributeModifier(EntityWitch.b, "Drinking speed penalty", -0.25D, 0)).a(false);
    private static final Item[] bk = new Item[] { Items.GLOWSTONE_DUST, Items.SUGAR, Items.REDSTONE, Items.SPIDER_EYE, Items.GLASS_BOTTLE, Items.GUNPOWDER, Items.STICK, Items.STICK};
    private int bl;

    public EntityWitch(World world) {
        super(world);
        this.a(0.6F, 1.95F);
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalArrowAttack(this, 1.0D, 60, 10.0F));
        this.goalSelector.a(2, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(2, this.a);
        this.goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
    }

    protected void h() {
        super.h();
        this.getDataWatcher().a(21, Byte.valueOf((byte) 0));
    }

    protected String z() {
        return null;
    }

    protected String bn() {
        return null;
    }

    protected String bo() {
        return null;
    }

    public void a(boolean flag) {
        this.getDataWatcher().watch(21, Byte.valueOf((byte) (flag ? 1 : 0)));
    }

    public boolean n() {
        return this.getDataWatcher().getByte(21) == 1;
    }

    protected void aW() {
        super.aW();
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(26.0D);
        this.getAttributeInstance(GenericAttributes.d).setValue(0.25D);
    }

    public void m() {
        if (!this.world.isStatic) {
            if (this.n()) {
                if (this.bl-- <= 0) {
                    this.a(false);
                    ItemStack itemstack = this.bz();

                    this.setEquipment(0, (ItemStack) null);
                    if (itemstack != null && itemstack.getItem() == Items.POTION) {
                        List list = Items.POTION.h(itemstack);

                        if (list != null) {
                            Iterator iterator = list.iterator();

                            while (iterator.hasNext()) {
                                MobEffect mobeffect = (MobEffect) iterator.next();

                                this.addEffect(new MobEffect(mobeffect));
                            }
                        }
                    }

                    this.getAttributeInstance(GenericAttributes.d).c(EntityWitch.c);
                }
            } else {
                short short0 = -1;

                if (this.random.nextFloat() < 0.15F && this.a(Material.WATER) && !this.hasEffect(MobEffectList.WATER_BREATHING)) {
                    short0 = 8237;
                } else if (this.random.nextFloat() < 0.15F && this.isBurning() && !this.hasEffect(MobEffectList.FIRE_RESISTANCE)) {
                    short0 = 16307;
                } else if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
                    short0 = 16341;
                } else if (this.random.nextFloat() < 0.25F && this.getGoalTarget() != null && !this.hasEffect(MobEffectList.FASTER_MOVEMENT) && this.getGoalTarget().h(this) > 121.0D) {
                    short0 = 16274;
                } else if (this.random.nextFloat() < 0.25F && this.getGoalTarget() != null && !this.hasEffect(MobEffectList.FASTER_MOVEMENT) && this.getGoalTarget().h(this) > 121.0D) {
                    short0 = 16274;
                }

                if (short0 > -1) {
                    this.setEquipment(0, new ItemStack(Items.POTION, 1, short0));
                    this.bl = this.bz().l();
                    this.a(true);
                    AttributeInstance attributeinstance = this.getAttributeInstance(GenericAttributes.d);

                    attributeinstance.c(EntityWitch.c);
                    attributeinstance.b(EntityWitch.c);
                }
            }

            if (this.random.nextFloat() < 7.5E-4F) {
                this.world.broadcastEntityEffect(this, (byte) 15);
            }
        }

        super.m();
    }

    protected float applyMagicModifier(DamageSource damagesource, float f) {
        f = super.applyMagicModifier(damagesource, f);
        if (damagesource.getEntity() == this) {
            f = 0.0F;
        }

        if (damagesource.isMagic()) {
            f = (float) ((double) f * 0.15D);
        }

        return f;
    }

    protected void dropDeathLoot(boolean flag, int i) {
        int j = this.random.nextInt(3) + 1;

        for (int k = 0; k < j; ++k) {
            int l = this.random.nextInt(3);
            Item item = EntityWitch.bk[this.random.nextInt(EntityWitch.bk.length)];

            if (i > 0) {
                l += this.random.nextInt(i + 1);
            }

            for (int i1 = 0; i1 < l; ++i1) {
                this.a(item, 1);
            }
        }

    }

    public void a(EntityLiving entityliving, float f) {
        if (!this.n()) {
            EntityPotion entitypotion = new EntityPotion(this.world, this, 32732);
            double d0 = entityliving.locY + (double) entityliving.getHeadHeight() - 1.100000023841858D;

            entitypotion.pitch -= -20.0F;
            double d1 = entityliving.locX + entityliving.motX - this.locX;
            double d2 = d0 - this.locY;
            double d3 = entityliving.locZ + entityliving.motZ - this.locZ;
            float f1 = MathHelper.sqrt(d1 * d1 + d3 * d3);

            if (f1 >= 8.0F && !entityliving.hasEffect(MobEffectList.SLOWER_MOVEMENT)) {
                entitypotion.setPotionValue(32698);
            } else if (entityliving.getHealth() >= 8.0F && !entityliving.hasEffect(MobEffectList.POISON)) {
                entitypotion.setPotionValue(32660);
            } else if (f1 <= 3.0F && !entityliving.hasEffect(MobEffectList.WEAKNESS) && this.random.nextFloat() < 0.25F) {
                entitypotion.setPotionValue(32696);
            }

            entitypotion.shoot(d1, d2 + (double) (f1 * 0.2F), d3, 0.75F, 8.0F);
            this.world.addEntity(entitypotion);
        }
    }

    public float getHeadHeight() {
        return 1.62F;
    }
}
