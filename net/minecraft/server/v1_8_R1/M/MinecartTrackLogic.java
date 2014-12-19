package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class MinecartTrackLogic {

    private final World b;
    private final BlockPosition c;
    private final BlockMinecartTrackAbstract d;
    private IBlockData e;
    private final boolean f;
    private final List g;
    final BlockMinecartTrackAbstract a;

    public MinecartTrackLogic(BlockMinecartTrackAbstract blockminecarttrackabstract, World world, BlockPosition blockposition, IBlockData iblockdata) {
        this.a = blockminecarttrackabstract;
        this.g = Lists.newArrayList();
        this.b = world;
        this.c = blockposition;
        this.e = iblockdata;
        this.d = (BlockMinecartTrackAbstract) iblockdata.getBlock();
        EnumTrackPosition enumtrackposition = (EnumTrackPosition) iblockdata.get(blockminecarttrackabstract.l());

        this.f = this.d.a;
        this.a(enumtrackposition);
    }

    private void a(EnumTrackPosition enumtrackposition) {
        this.g.clear();
        switch (SwitchHelperTrackPosition2.a[enumtrackposition.ordinal()]) {
        case 1:
            this.g.add(this.c.north());
            this.g.add(this.c.south());
            break;

        case 2:
            this.g.add(this.c.west());
            this.g.add(this.c.east());
            break;

        case 3:
            this.g.add(this.c.west());
            this.g.add(this.c.east().up());
            break;

        case 4:
            this.g.add(this.c.west().up());
            this.g.add(this.c.east());
            break;

        case 5:
            this.g.add(this.c.north().up());
            this.g.add(this.c.south());
            break;

        case 6:
            this.g.add(this.c.north());
            this.g.add(this.c.south().up());
            break;

        case 7:
            this.g.add(this.c.east());
            this.g.add(this.c.south());
            break;

        case 8:
            this.g.add(this.c.west());
            this.g.add(this.c.south());
            break;

        case 9:
            this.g.add(this.c.west());
            this.g.add(this.c.north());
            break;

        case 10:
            this.g.add(this.c.east());
            this.g.add(this.c.north());
        }

    }

    private void c() {
        for (int i = 0; i < this.g.size(); ++i) {
            MinecartTrackLogic minecarttracklogic = this.b((BlockPosition) this.g.get(i));

            if (minecarttracklogic != null && minecarttracklogic.a(this)) {
                this.g.set(i, minecarttracklogic.c);
            } else {
                this.g.remove(i--);
            }
        }

    }

    private boolean a(BlockPosition blockposition) {
        return BlockMinecartTrackAbstract.d(this.b, blockposition) || BlockMinecartTrackAbstract.d(this.b, blockposition.up()) || BlockMinecartTrackAbstract.d(this.b, blockposition.down());
    }

    private MinecartTrackLogic b(BlockPosition blockposition) {
        IBlockData iblockdata = this.b.getType(blockposition);

        if (BlockMinecartTrackAbstract.d(iblockdata)) {
            return new MinecartTrackLogic(this.a, this.b, blockposition, iblockdata);
        } else {
            BlockPosition blockposition1 = blockposition.up();

            iblockdata = this.b.getType(blockposition1);
            if (BlockMinecartTrackAbstract.d(iblockdata)) {
                return new MinecartTrackLogic(this.a, this.b, blockposition1, iblockdata);
            } else {
                blockposition1 = blockposition.down();
                iblockdata = this.b.getType(blockposition1);
                return BlockMinecartTrackAbstract.d(iblockdata) ? new MinecartTrackLogic(this.a, this.b, blockposition1, iblockdata) : null;
            }
        }
    }

    private boolean a(MinecartTrackLogic minecarttracklogic) {
        return this.c(minecarttracklogic.c);
    }

    private boolean c(BlockPosition blockposition) {
        for (int i = 0; i < this.g.size(); ++i) {
            BlockPosition blockposition1 = (BlockPosition) this.g.get(i);

            if (blockposition1.getX() == blockposition.getX() && blockposition1.getZ() == blockposition.getZ()) {
                return true;
            }
        }

        return false;
    }

    protected int a() {
        int i = 0;
        Iterator iterator = EnumDirectionLimit.HORIZONTAL.iterator();

        while (iterator.hasNext()) {
            EnumDirection enumdirection = (EnumDirection) iterator.next();

            if (this.a(this.c.shift(enumdirection))) {
                ++i;
            }
        }

        return i;
    }

    private boolean b(MinecartTrackLogic minecarttracklogic) {
        return this.a(minecarttracklogic) || this.g.size() != 2;
    }

    private void c(MinecartTrackLogic minecarttracklogic) {
        this.g.add(minecarttracklogic.c);
        BlockPosition blockposition = this.c.north();
        BlockPosition blockposition1 = this.c.south();
        BlockPosition blockposition2 = this.c.west();
        BlockPosition blockposition3 = this.c.east();
        boolean flag = this.c(blockposition);
        boolean flag1 = this.c(blockposition1);
        boolean flag2 = this.c(blockposition2);
        boolean flag3 = this.c(blockposition3);
        EnumTrackPosition enumtrackposition = null;

        if (flag || flag1) {
            enumtrackposition = EnumTrackPosition.NORTH_SOUTH;
        }

        if (flag2 || flag3) {
            enumtrackposition = EnumTrackPosition.EAST_WEST;
        }

        if (!this.f) {
            if (flag1 && flag3 && !flag && !flag2) {
                enumtrackposition = EnumTrackPosition.SOUTH_EAST;
            }

            if (flag1 && flag2 && !flag && !flag3) {
                enumtrackposition = EnumTrackPosition.SOUTH_WEST;
            }

            if (flag && flag2 && !flag1 && !flag3) {
                enumtrackposition = EnumTrackPosition.NORTH_WEST;
            }

            if (flag && flag3 && !flag1 && !flag2) {
                enumtrackposition = EnumTrackPosition.NORTH_EAST;
            }
        }

        if (enumtrackposition == EnumTrackPosition.NORTH_SOUTH) {
            if (BlockMinecartTrackAbstract.d(this.b, blockposition.up())) {
                enumtrackposition = EnumTrackPosition.ASCENDING_NORTH;
            }

            if (BlockMinecartTrackAbstract.d(this.b, blockposition1.up())) {
                enumtrackposition = EnumTrackPosition.ASCENDING_SOUTH;
            }
        }

        if (enumtrackposition == EnumTrackPosition.EAST_WEST) {
            if (BlockMinecartTrackAbstract.d(this.b, blockposition3.up())) {
                enumtrackposition = EnumTrackPosition.ASCENDING_EAST;
            }

            if (BlockMinecartTrackAbstract.d(this.b, blockposition2.up())) {
                enumtrackposition = EnumTrackPosition.ASCENDING_WEST;
            }
        }

        if (enumtrackposition == null) {
            enumtrackposition = EnumTrackPosition.NORTH_SOUTH;
        }

        this.e = this.e.set(this.d.l(), enumtrackposition);
        this.b.setTypeAndData(this.c, this.e, 3);
    }

    private boolean d(BlockPosition blockposition) {
        MinecartTrackLogic minecarttracklogic = this.b(blockposition);

        if (minecarttracklogic == null) {
            return false;
        } else {
            minecarttracklogic.c();
            return minecarttracklogic.b(this);
        }
    }

    public MinecartTrackLogic a(boolean flag, boolean flag1) {
        BlockPosition blockposition = this.c.north();
        BlockPosition blockposition1 = this.c.south();
        BlockPosition blockposition2 = this.c.west();
        BlockPosition blockposition3 = this.c.east();
        boolean flag2 = this.d(blockposition);
        boolean flag3 = this.d(blockposition1);
        boolean flag4 = this.d(blockposition2);
        boolean flag5 = this.d(blockposition3);
        EnumTrackPosition enumtrackposition = null;

        if ((flag2 || flag3) && !flag4 && !flag5) {
            enumtrackposition = EnumTrackPosition.NORTH_SOUTH;
        }

        if ((flag4 || flag5) && !flag2 && !flag3) {
            enumtrackposition = EnumTrackPosition.EAST_WEST;
        }

        if (!this.f) {
            if (flag3 && flag5 && !flag2 && !flag4) {
                enumtrackposition = EnumTrackPosition.SOUTH_EAST;
            }

            if (flag3 && flag4 && !flag2 && !flag5) {
                enumtrackposition = EnumTrackPosition.SOUTH_WEST;
            }

            if (flag2 && flag4 && !flag3 && !flag5) {
                enumtrackposition = EnumTrackPosition.NORTH_WEST;
            }

            if (flag2 && flag5 && !flag3 && !flag4) {
                enumtrackposition = EnumTrackPosition.NORTH_EAST;
            }
        }

        if (enumtrackposition == null) {
            if (flag2 || flag3) {
                enumtrackposition = EnumTrackPosition.NORTH_SOUTH;
            }

            if (flag4 || flag5) {
                enumtrackposition = EnumTrackPosition.EAST_WEST;
            }

            if (!this.f) {
                if (flag) {
                    if (flag3 && flag5) {
                        enumtrackposition = EnumTrackPosition.SOUTH_EAST;
                    }

                    if (flag4 && flag3) {
                        enumtrackposition = EnumTrackPosition.SOUTH_WEST;
                    }

                    if (flag5 && flag2) {
                        enumtrackposition = EnumTrackPosition.NORTH_EAST;
                    }

                    if (flag2 && flag4) {
                        enumtrackposition = EnumTrackPosition.NORTH_WEST;
                    }
                } else {
                    if (flag2 && flag4) {
                        enumtrackposition = EnumTrackPosition.NORTH_WEST;
                    }

                    if (flag5 && flag2) {
                        enumtrackposition = EnumTrackPosition.NORTH_EAST;
                    }

                    if (flag4 && flag3) {
                        enumtrackposition = EnumTrackPosition.SOUTH_WEST;
                    }

                    if (flag3 && flag5) {
                        enumtrackposition = EnumTrackPosition.SOUTH_EAST;
                    }
                }
            }
        }

        if (enumtrackposition == EnumTrackPosition.NORTH_SOUTH) {
            if (BlockMinecartTrackAbstract.d(this.b, blockposition.up())) {
                enumtrackposition = EnumTrackPosition.ASCENDING_NORTH;
            }

            if (BlockMinecartTrackAbstract.d(this.b, blockposition1.up())) {
                enumtrackposition = EnumTrackPosition.ASCENDING_SOUTH;
            }
        }

        if (enumtrackposition == EnumTrackPosition.EAST_WEST) {
            if (BlockMinecartTrackAbstract.d(this.b, blockposition3.up())) {
                enumtrackposition = EnumTrackPosition.ASCENDING_EAST;
            }

            if (BlockMinecartTrackAbstract.d(this.b, blockposition2.up())) {
                enumtrackposition = EnumTrackPosition.ASCENDING_WEST;
            }
        }

        if (enumtrackposition == null) {
            enumtrackposition = EnumTrackPosition.NORTH_SOUTH;
        }

        this.a(enumtrackposition);
        this.e = this.e.set(this.d.l(), enumtrackposition);
        if (flag1 || this.b.getType(this.c) != this.e) {
            this.b.setTypeAndData(this.c, this.e, 3);

            for (int i = 0; i < this.g.size(); ++i) {
                MinecartTrackLogic minecarttracklogic = this.b((BlockPosition) this.g.get(i));

                if (minecarttracklogic != null) {
                    minecarttracklogic.c();
                    if (minecarttracklogic.b(this)) {
                        minecarttracklogic.c(this);
                    }
                }
            }
        }

        return this;
    }

    public IBlockData b() {
        return this.e;
    }
}
