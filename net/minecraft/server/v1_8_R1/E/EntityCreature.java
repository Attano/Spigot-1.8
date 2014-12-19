package net.minecraft.server;

import java.util.UUID;

// CraftBukkit start
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityUnleashEvent;
// CraftBukkit end

public abstract class EntityCreature extends EntityInsentient {

    public static final UUID bi = UUID.fromString("E199AD21-BA8A-4C53-8D13-6182D5C69D3A");
    public static final AttributeModifier bj = (new AttributeModifier(EntityCreature.bi, "Fleeing speed bonus", 2.0D, 2)).a(false);
    private BlockPosition a;
    private float b;
    private PathfinderGoal c;
    private boolean bk;

    public EntityCreature(World world) {
        super(world);
        this.a = BlockPosition.ZERO;
        this.b = -1.0F;
        this.c = new PathfinderGoalMoveTowardsRestriction(this, 1.0D);
    }

    public float a(BlockPosition blockposition) {
        return 0.0F;
    }

    public boolean bQ() {
        return super.bQ() && this.a(new BlockPosition(this.locX, this.getBoundingBox().b, this.locZ)) >= 0.0F;
    }

    public boolean cd() {
        return !this.navigation.m();
    }

    public boolean ce() {
        return this.d(new BlockPosition(this));
    }

    public boolean d(BlockPosition blockposition) {
        return this.b == -1.0F ? true : this.a.i(blockposition) < (double) (this.b * this.b);
    }

    public void a(BlockPosition blockposition, int i) {
        this.a = blockposition;
        this.b = (float) i;
    }

    public BlockPosition cf() {
        return this.a;
    }

    public float cg() {
        return this.b;
    }

    public void ch() {
        this.b = -1.0F;
    }

    public boolean ci() {
        return this.b != -1.0F;
    }

    protected void bZ() {
        super.bZ();
        if (this.cb() && this.getLeashHolder() != null && this.getLeashHolder().world == this.world) {
            Entity entity = this.getLeashHolder();

            this.a(new BlockPosition((int) entity.locX, (int) entity.locY, (int) entity.locZ), 5);
            float f = this.g(entity);

            if (this instanceof EntityTameableAnimal && ((EntityTameableAnimal) this).isSitting()) {
                if (f > 10.0F) {
                    this.world.getServer().getPluginManager().callEvent(new EntityUnleashEvent(this.getBukkitEntity(), EntityUnleashEvent.UnleashReason.DISTANCE)); // CraftBukkit
                    this.unleash(true, true);
                }

                return;
            }

            if (!this.bk) {
                this.goalSelector.a(2, this.c);
                if (this.getNavigation() instanceof Navigation) {
                    ((Navigation) this.getNavigation()).a(false);
                }

                this.bk = true;
            }

            this.n(f);
            if (f > 4.0F) {
                this.getNavigation().a(entity, 1.0D);
            }

            if (f > 6.0F) {
                double d0 = (entity.locX - this.locX) / (double) f;
                double d1 = (entity.locY - this.locY) / (double) f;
                double d2 = (entity.locZ - this.locZ) / (double) f;

                this.motX += d0 * Math.abs(d0) * 0.4D;
                this.motY += d1 * Math.abs(d1) * 0.4D;
                this.motZ += d2 * Math.abs(d2) * 0.4D;
            }

            if (f > 10.0F) {
                this.world.getServer().getPluginManager().callEvent(new EntityUnleashEvent(this.getBukkitEntity(), EntityUnleashEvent.UnleashReason.DISTANCE)); // CraftBukkit
                this.unleash(true, true);
            }
        } else if (!this.cb() && this.bk) {
            this.bk = false;
            this.goalSelector.a(this.c);
            if (this.getNavigation() instanceof Navigation) {
                ((Navigation) this.getNavigation()).a(true);
            }

            this.ch();
        }

    }

    protected void n(float f) {}
}
