package net.minecraft.server;

import com.google.common.base.Predicate;

public final class IEntitySelector {

    public static final Predicate a = new EntitySelectorLiving();
    public static final Predicate b = new EntitySelectorChickenJockey();
    public static final Predicate c = new EntitySelectorContainer();
    public static final Predicate d = new EntitySelectorNonPlayer();
}
