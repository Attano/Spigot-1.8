package net.minecraft.server;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collections;
import java.util.List;

public class PathfinderGoalNearestAttackableTarget extends PathfinderGoalTarget {

    protected final Class a;
    private final int g;
    protected final DistanceComparator b;
    protected Predicate c;
    protected EntityLiving d;

    public PathfinderGoalNearestAttackableTarget(EntityCreature entitycreature, Class oclass, boolean flag) {
        this(entitycreature, oclass, flag, false);
    }

    public PathfinderGoalNearestAttackableTarget(EntityCreature entitycreature, Class oclass, boolean flag, boolean flag1) {
        this(entitycreature, oclass, 10, flag, flag1, (Predicate) null);
    }

    public PathfinderGoalNearestAttackableTarget(EntityCreature entitycreature, Class oclass, int i, boolean flag, boolean flag1, Predicate predicate) {
        super(entitycreature, flag, flag1);
        this.a = oclass;
        this.g = i;
        this.b = new DistanceComparator(entitycreature);
        this.a(1);
        this.c = new EntitySelectorNearestAttackableTarget(this, predicate);
    }

    public boolean a() {
        if (this.g > 0 && this.e.bb().nextInt(this.g) != 0) {
            return false;
        } else {
            double d0 = this.f();
            List list = this.e.world.a(this.a, this.e.getBoundingBox().grow(d0, 4.0D, d0), Predicates.and(this.c, IEntitySelector.d));

            Collections.sort(list, this.b);
            if (list.isEmpty()) {
                return false;
            } else {
                this.d = (EntityLiving) list.get(0);
                return true;
            }
        }
    }

    public void c() {
        this.e.setGoalTarget(this.d, org.bukkit.event.entity.EntityTargetEvent.TargetReason.CLOSEST_PLAYER, true); // Craftbukkit - reason
        super.c();
    }
}
