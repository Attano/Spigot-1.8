package net.minecraft.server;

import com.google.common.base.Predicate;

final class BlockMinecartDetectorInnerClass1 implements Predicate {

    BlockMinecartDetectorInnerClass1() {}

    public boolean a(EnumTrackPosition enumtrackposition) {
        return enumtrackposition != EnumTrackPosition.NORTH_EAST && enumtrackposition != EnumTrackPosition.NORTH_WEST && enumtrackposition != EnumTrackPosition.SOUTH_EAST && enumtrackposition != EnumTrackPosition.SOUTH_WEST;
    }

    public boolean apply(Object object) {
        return this.a((EnumTrackPosition) object);
    }
}
