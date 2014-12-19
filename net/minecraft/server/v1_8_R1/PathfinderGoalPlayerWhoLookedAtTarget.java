package net.minecraft.server;

import java.util.Collections;
import java.util.List;

class PathfinderGoalPlayerWhoLookedAtTarget extends PathfinderGoalNearestAttackableTarget {

    private EntityHuman g;
    private int h;
    private int i;
    private EntityEnderman j;

    public PathfinderGoalPlayerWhoLookedAtTarget(EntityEnderman entityenderman) {
        super(entityenderman, EntityHuman.class, true);
        this.j = entityenderman;
    }

    public boolean a() {
        double d0 = this.f();
        List list = this.e.world.a(EntityHuman.class, this.e.getBoundingBox().grow(d0, 4.0D, d0), this.c);

        Collections.sort(list, this.b);
        if (list.isEmpty()) {
            return false;
        } else {
            this.g = (EntityHuman) list.get(0);
            return true;
        }
    }

    public void c() {
        this.h = 5;
        this.i = 0;
    }

    public void d() {
        this.g = null;
        this.j.a(false);
        AttributeInstance attributeinstance = this.j.getAttributeInstance(GenericAttributes.d);

        attributeinstance.c(EntityEnderman.cn());
        super.d();
    }

    public boolean b() {
        if (this.g != null) {
            if (!EntityEnderman.a(this.j, this.g)) {
                return false;
            } else {
                EntityEnderman.a(this.j, true);
                this.j.a(this.g, 10.0F, 10.0F);
                return true;
            }
        } else {
            return super.b();
        }
    }

    public void e() {
        if (this.g != null) {
            if (--this.h <= 0) {
                this.d = this.g;
                this.g = null;
                super.c();
                this.j.makeSound("mob.endermen.stare", 1.0F, 1.0F);
                this.j.a(true);
                AttributeInstance attributeinstance = this.j.getAttributeInstance(GenericAttributes.d);

                attributeinstance.b(EntityEnderman.cn());
            }
        } else {
            if (this.d != null) {
                if (this.d instanceof EntityHuman && EntityEnderman.a(this.j, (EntityHuman) this.d)) {
                    if (this.d.h(this.j) < 16.0D) {
                        this.j.n();
                    }

                    this.i = 0;
                } else if (this.d.h(this.j) > 256.0D && this.i++ >= 30 && this.j.b((Entity) this.d)) {
                    this.i = 0;
                }
            }

            super.e();
        }

    }
}
