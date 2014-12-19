package net.minecraft.server;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;

public class PathfinderGoalAvoidTarget extends PathfinderGoal {

    public final Predicate a = new EntitySelectorViewable(this);
    protected EntityCreature b;
    private double d;
    private double e;
    protected Entity c;
    private float f;
    private PathEntity g;
    private NavigationAbstract h;
    private Predicate i;

    public PathfinderGoalAvoidTarget(EntityCreature entitycreature, Predicate predicate, float f, double d0, double d1) {
        this.b = entitycreature;
        this.i = predicate;
        this.f = f;
        this.d = d0;
        this.e = d1;
        this.h = entitycreature.getNavigation();
        this.a(1);
    }

    public boolean a() {
        List list = this.b.world.a((Entity) this.b, this.b.getBoundingBox().grow((double) this.f, 3.0D, (double) this.f), Predicates.and(new Predicate[] { IEntitySelector.d, this.a, this.i}));

        if (list.isEmpty()) {
            return false;
        } else {
            this.c = (Entity) list.get(0);
            Vec3D vec3d = RandomPositionGenerator.b(this.b, 16, 7, new Vec3D(this.c.locX, this.c.locY, this.c.locZ));

            if (vec3d == null) {
                return false;
            } else if (this.c.e(vec3d.a, vec3d.b, vec3d.c) < this.c.h(this.b)) {
                return false;
            } else {
                this.g = this.h.a(vec3d.a, vec3d.b, vec3d.c);
                return this.g == null ? false : this.g.b(vec3d);
            }
        }
    }

    public boolean b() {
        return !this.h.m();
    }

    public void c() {
        this.h.a(this.g, this.d);
    }

    public void d() {
        this.c = null;
    }

    public void e() {
        if (this.b.h(this.c) < 49.0D) {
            this.b.getNavigation().a(this.e);
        } else {
            this.b.getNavigation().a(this.d);
        }

    }
}
