package net.minecraft.server;

import com.google.common.base.Predicate;

public interface IMonster extends IAnimal {

    Predicate d = new EntitySelectorMonster();
    Predicate e = new EntitySelectorVisibleMonster();
}
