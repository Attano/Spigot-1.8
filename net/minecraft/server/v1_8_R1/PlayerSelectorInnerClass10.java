package net.minecraft.server;

import com.google.common.collect.ComparisonChain;
import java.util.Comparator;

final class PlayerSelectorInnerClass10 implements Comparator {

    final BlockPosition a;

    PlayerSelectorInnerClass10(BlockPosition blockposition) {
        this.a = blockposition;
    }

    public int a(Entity entity, Entity entity1) {
        return ComparisonChain.start().compare(entity.b(this.a), entity1.b(this.a)).result();
    }

    public int compare(Object object, Object object1) {
        return this.a((Entity) object, (Entity) object1);
    }
}
