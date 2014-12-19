package net.minecraft.server;

import com.google.common.base.Predicate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BlockStairs extends Block {

    public static final BlockStateDirection FACING = BlockStateDirection.of("facing", (Predicate) EnumDirectionLimit.HORIZONTAL);
    public static final BlockStateEnum HALF = BlockStateEnum.of("half", EnumHalf.class);
    public static final BlockStateEnum SHAPE = BlockStateEnum.of("shape", EnumStairShape.class);
    private static final int[][] N = new int[][] { { 4, 5}, { 5, 7}, { 6, 7}, { 4, 6}, { 0, 1}, { 1, 3}, { 2, 3}, { 0, 2}};
    private final Block O;
    private final IBlockData P;
    private boolean Q;
    private int R;

    protected BlockStairs(IBlockData iblockdata) {
        super(iblockdata.getBlock().material);
        this.j(this.blockStateList.getBlockData().set(BlockStairs.FACING, EnumDirection.NORTH).set(BlockStairs.HALF, EnumHalf.BOTTOM).set(BlockStairs.SHAPE, EnumStairShape.STRAIGHT));
        this.O = iblockdata.getBlock();
        this.P = iblockdata;
        this.c(this.O.strength);
        this.b(this.O.durability / 3.0F);
        this.a(this.O.stepSound);
        this.e(255);
        this.a(CreativeModeTab.b);
    }

    public void updateShape(IBlockAccess iblockaccess, BlockPosition blockposition) {
        if (this.Q) {
            this.a(0.5F * (float) (this.R % 2), 0.5F * (float) (this.R / 4 % 2), 0.5F * (float) (this.R / 2 % 2), 0.5F + 0.5F * (float) (this.R % 2), 0.5F + 0.5F * (float) (this.R / 4 % 2), 0.5F + 0.5F * (float) (this.R / 2 % 2));
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public void e(IBlockAccess iblockaccess, BlockPosition blockposition) {
        if (iblockaccess.getType(blockposition).get(BlockStairs.HALF) == EnumHalf.TOP) {
            this.a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }

    }

    public static boolean c(Block block) {
        return block instanceof BlockStairs;
    }

    public static boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata) {
        IBlockData iblockdata1 = iblockaccess.getType(blockposition);
        Block block = iblockdata1.getBlock();

        return c(block) && iblockdata1.get(BlockStairs.HALF) == iblockdata.get(BlockStairs.HALF) && iblockdata1.get(BlockStairs.FACING) == iblockdata.get(BlockStairs.FACING);
    }

    public int f(IBlockAccess iblockaccess, BlockPosition blockposition) {
        IBlockData iblockdata = iblockaccess.getType(blockposition);
        EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockStairs.FACING);
        EnumHalf enumhalf = (EnumHalf) iblockdata.get(BlockStairs.HALF);
        boolean flag = enumhalf == EnumHalf.TOP;
        IBlockData iblockdata1;
        Block block;
        EnumDirection enumdirection1;

        if (enumdirection == EnumDirection.EAST) {
            iblockdata1 = iblockaccess.getType(blockposition.east());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.NORTH && !a(iblockaccess, blockposition.south(), iblockdata)) {
                    return flag ? 1 : 2;
                }

                if (enumdirection1 == EnumDirection.SOUTH && !a(iblockaccess, blockposition.north(), iblockdata)) {
                    return flag ? 2 : 1;
                }
            }
        } else if (enumdirection == EnumDirection.WEST) {
            iblockdata1 = iblockaccess.getType(blockposition.west());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.NORTH && !a(iblockaccess, blockposition.south(), iblockdata)) {
                    return flag ? 2 : 1;
                }

                if (enumdirection1 == EnumDirection.SOUTH && !a(iblockaccess, blockposition.north(), iblockdata)) {
                    return flag ? 1 : 2;
                }
            }
        } else if (enumdirection == EnumDirection.SOUTH) {
            iblockdata1 = iblockaccess.getType(blockposition.south());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.WEST && !a(iblockaccess, blockposition.east(), iblockdata)) {
                    return flag ? 2 : 1;
                }

                if (enumdirection1 == EnumDirection.EAST && !a(iblockaccess, blockposition.west(), iblockdata)) {
                    return flag ? 1 : 2;
                }
            }
        } else if (enumdirection == EnumDirection.NORTH) {
            iblockdata1 = iblockaccess.getType(blockposition.north());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.WEST && !a(iblockaccess, blockposition.east(), iblockdata)) {
                    return flag ? 1 : 2;
                }

                if (enumdirection1 == EnumDirection.EAST && !a(iblockaccess, blockposition.west(), iblockdata)) {
                    return flag ? 2 : 1;
                }
            }
        }

        return 0;
    }

    public int g(IBlockAccess iblockaccess, BlockPosition blockposition) {
        IBlockData iblockdata = iblockaccess.getType(blockposition);
        EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockStairs.FACING);
        EnumHalf enumhalf = (EnumHalf) iblockdata.get(BlockStairs.HALF);
        boolean flag = enumhalf == EnumHalf.TOP;
        IBlockData iblockdata1;
        Block block;
        EnumDirection enumdirection1;

        if (enumdirection == EnumDirection.EAST) {
            iblockdata1 = iblockaccess.getType(blockposition.west());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.NORTH && !a(iblockaccess, blockposition.north(), iblockdata)) {
                    return flag ? 1 : 2;
                }

                if (enumdirection1 == EnumDirection.SOUTH && !a(iblockaccess, blockposition.south(), iblockdata)) {
                    return flag ? 2 : 1;
                }
            }
        } else if (enumdirection == EnumDirection.WEST) {
            iblockdata1 = iblockaccess.getType(blockposition.east());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.NORTH && !a(iblockaccess, blockposition.north(), iblockdata)) {
                    return flag ? 2 : 1;
                }

                if (enumdirection1 == EnumDirection.SOUTH && !a(iblockaccess, blockposition.south(), iblockdata)) {
                    return flag ? 1 : 2;
                }
            }
        } else if (enumdirection == EnumDirection.SOUTH) {
            iblockdata1 = iblockaccess.getType(blockposition.north());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.WEST && !a(iblockaccess, blockposition.west(), iblockdata)) {
                    return flag ? 2 : 1;
                }

                if (enumdirection1 == EnumDirection.EAST && !a(iblockaccess, blockposition.east(), iblockdata)) {
                    return flag ? 1 : 2;
                }
            }
        } else if (enumdirection == EnumDirection.NORTH) {
            iblockdata1 = iblockaccess.getType(blockposition.south());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.WEST && !a(iblockaccess, blockposition.west(), iblockdata)) {
                    return flag ? 1 : 2;
                }

                if (enumdirection1 == EnumDirection.EAST && !a(iblockaccess, blockposition.east(), iblockdata)) {
                    return flag ? 2 : 1;
                }
            }
        }

        return 0;
    }

    public boolean h(IBlockAccess iblockaccess, BlockPosition blockposition) {
        IBlockData iblockdata = iblockaccess.getType(blockposition);
        EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockStairs.FACING);
        EnumHalf enumhalf = (EnumHalf) iblockdata.get(BlockStairs.HALF);
        boolean flag = enumhalf == EnumHalf.TOP;
        float f = 0.5F;
        float f1 = 1.0F;

        if (flag) {
            f = 0.0F;
            f1 = 0.5F;
        }

        float f2 = 0.0F;
        float f3 = 1.0F;
        float f4 = 0.0F;
        float f5 = 0.5F;
        boolean flag1 = true;
        IBlockData iblockdata1;
        Block block;
        EnumDirection enumdirection1;

        if (enumdirection == EnumDirection.EAST) {
            f2 = 0.5F;
            f5 = 1.0F;
            iblockdata1 = iblockaccess.getType(blockposition.east());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.NORTH && !a(iblockaccess, blockposition.south(), iblockdata)) {
                    f5 = 0.5F;
                    flag1 = false;
                } else if (enumdirection1 == EnumDirection.SOUTH && !a(iblockaccess, blockposition.north(), iblockdata)) {
                    f4 = 0.5F;
                    flag1 = false;
                }
            }
        } else if (enumdirection == EnumDirection.WEST) {
            f3 = 0.5F;
            f5 = 1.0F;
            iblockdata1 = iblockaccess.getType(blockposition.west());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.NORTH && !a(iblockaccess, blockposition.south(), iblockdata)) {
                    f5 = 0.5F;
                    flag1 = false;
                } else if (enumdirection1 == EnumDirection.SOUTH && !a(iblockaccess, blockposition.north(), iblockdata)) {
                    f4 = 0.5F;
                    flag1 = false;
                }
            }
        } else if (enumdirection == EnumDirection.SOUTH) {
            f4 = 0.5F;
            f5 = 1.0F;
            iblockdata1 = iblockaccess.getType(blockposition.south());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.WEST && !a(iblockaccess, blockposition.east(), iblockdata)) {
                    f3 = 0.5F;
                    flag1 = false;
                } else if (enumdirection1 == EnumDirection.EAST && !a(iblockaccess, blockposition.west(), iblockdata)) {
                    f2 = 0.5F;
                    flag1 = false;
                }
            }
        } else if (enumdirection == EnumDirection.NORTH) {
            iblockdata1 = iblockaccess.getType(blockposition.north());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.WEST && !a(iblockaccess, blockposition.east(), iblockdata)) {
                    f3 = 0.5F;
                    flag1 = false;
                } else if (enumdirection1 == EnumDirection.EAST && !a(iblockaccess, blockposition.west(), iblockdata)) {
                    f2 = 0.5F;
                    flag1 = false;
                }
            }
        }

        this.a(f2, f, f4, f3, f1, f5);
        return flag1;
    }

    public boolean i(IBlockAccess iblockaccess, BlockPosition blockposition) {
        IBlockData iblockdata = iblockaccess.getType(blockposition);
        EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockStairs.FACING);
        EnumHalf enumhalf = (EnumHalf) iblockdata.get(BlockStairs.HALF);
        boolean flag = enumhalf == EnumHalf.TOP;
        float f = 0.5F;
        float f1 = 1.0F;

        if (flag) {
            f = 0.0F;
            f1 = 0.5F;
        }

        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = 0.5F;
        float f5 = 1.0F;
        boolean flag1 = false;
        IBlockData iblockdata1;
        Block block;
        EnumDirection enumdirection1;

        if (enumdirection == EnumDirection.EAST) {
            iblockdata1 = iblockaccess.getType(blockposition.west());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.NORTH && !a(iblockaccess, blockposition.north(), iblockdata)) {
                    f4 = 0.0F;
                    f5 = 0.5F;
                    flag1 = true;
                } else if (enumdirection1 == EnumDirection.SOUTH && !a(iblockaccess, blockposition.south(), iblockdata)) {
                    f4 = 0.5F;
                    f5 = 1.0F;
                    flag1 = true;
                }
            }
        } else if (enumdirection == EnumDirection.WEST) {
            iblockdata1 = iblockaccess.getType(blockposition.east());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                f2 = 0.5F;
                f3 = 1.0F;
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.NORTH && !a(iblockaccess, blockposition.north(), iblockdata)) {
                    f4 = 0.0F;
                    f5 = 0.5F;
                    flag1 = true;
                } else if (enumdirection1 == EnumDirection.SOUTH && !a(iblockaccess, blockposition.south(), iblockdata)) {
                    f4 = 0.5F;
                    f5 = 1.0F;
                    flag1 = true;
                }
            }
        } else if (enumdirection == EnumDirection.SOUTH) {
            iblockdata1 = iblockaccess.getType(blockposition.north());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                f4 = 0.0F;
                f5 = 0.5F;
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.WEST && !a(iblockaccess, blockposition.west(), iblockdata)) {
                    flag1 = true;
                } else if (enumdirection1 == EnumDirection.EAST && !a(iblockaccess, blockposition.east(), iblockdata)) {
                    f2 = 0.5F;
                    f3 = 1.0F;
                    flag1 = true;
                }
            }
        } else if (enumdirection == EnumDirection.NORTH) {
            iblockdata1 = iblockaccess.getType(blockposition.south());
            block = iblockdata1.getBlock();
            if (c(block) && enumhalf == iblockdata1.get(BlockStairs.HALF)) {
                enumdirection1 = (EnumDirection) iblockdata1.get(BlockStairs.FACING);
                if (enumdirection1 == EnumDirection.WEST && !a(iblockaccess, blockposition.west(), iblockdata)) {
                    flag1 = true;
                } else if (enumdirection1 == EnumDirection.EAST && !a(iblockaccess, blockposition.east(), iblockdata)) {
                    f2 = 0.5F;
                    f3 = 1.0F;
                    flag1 = true;
                }
            }
        }

        if (flag1) {
            this.a(f2, f, f4, f3, f1, f5);
        }

        return flag1;
    }

    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        this.e(world, blockposition);
        super.a(world, blockposition, iblockdata, axisalignedbb, list, entity);
        boolean flag = this.h(world, blockposition);

        super.a(world, blockposition, iblockdata, axisalignedbb, list, entity);
        if (flag && this.i(world, blockposition)) {
            super.a(world, blockposition, iblockdata, axisalignedbb, list, entity);
        }

        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void attack(World world, BlockPosition blockposition, EntityHuman entityhuman) {
        this.O.attack(world, blockposition, entityhuman);
    }

    public void postBreak(World world, BlockPosition blockposition, IBlockData iblockdata) {
        this.O.postBreak(world, blockposition, iblockdata);
    }

    public float a(Entity entity) {
        return this.O.a(entity);
    }

    public int a(World world) {
        return this.O.a(world);
    }

    public Vec3D a(World world, BlockPosition blockposition, Entity entity, Vec3D vec3d) {
        return this.O.a(world, blockposition, entity, vec3d);
    }

    public boolean y() {
        return this.O.y();
    }

    public boolean a(IBlockData iblockdata, boolean flag) {
        return this.O.a(iblockdata, flag);
    }

    public boolean canPlace(World world, BlockPosition blockposition) {
        return this.O.canPlace(world, blockposition);
    }

    public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
        this.doPhysics(world, blockposition, this.P, Blocks.AIR);
        this.O.onPlace(world, blockposition, this.P);
    }

    public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
        this.O.remove(world, blockposition, this.P);
    }

    public void a(World world, BlockPosition blockposition, Entity entity) {
        this.O.a(world, blockposition, entity);
    }

    public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
        this.O.b(world, blockposition, iblockdata, random);
    }

    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumDirection enumdirection, float f, float f1, float f2) {
        return this.O.interact(world, blockposition, this.P, entityhuman, EnumDirection.DOWN, 0.0F, 0.0F, 0.0F);
    }

    public void wasExploded(World world, BlockPosition blockposition, Explosion explosion) {
        this.O.wasExploded(world, blockposition, explosion);
    }

    public MaterialMapColor g(IBlockData iblockdata) {
        return this.O.g(this.P);
    }

    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
        IBlockData iblockdata = super.getPlacedState(world, blockposition, enumdirection, f, f1, f2, i, entityliving);

        iblockdata = iblockdata.set(BlockStairs.FACING, entityliving.getDirection()).set(BlockStairs.SHAPE, EnumStairShape.STRAIGHT);
        return enumdirection != EnumDirection.DOWN && (enumdirection == EnumDirection.UP || (double) f1 <= 0.5D) ? iblockdata.set(BlockStairs.HALF, EnumHalf.BOTTOM) : iblockdata.set(BlockStairs.HALF, EnumHalf.TOP);
    }

    public MovingObjectPosition a(World world, BlockPosition blockposition, Vec3D vec3d, Vec3D vec3d1) {
        MovingObjectPosition[] amovingobjectposition = new MovingObjectPosition[8];
        IBlockData iblockdata = world.getType(blockposition);
        int i = ((EnumDirection) iblockdata.get(BlockStairs.FACING)).b();
        boolean flag = iblockdata.get(BlockStairs.HALF) == EnumHalf.TOP;
        int[] aint = BlockStairs.N[i + (flag ? 4 : 0)];

        this.Q = true;

        for (int j = 0; j < 8; ++j) {
            this.R = j;
            if (Arrays.binarySearch(aint, j) < 0) {
                amovingobjectposition[j] = super.a(world, blockposition, vec3d, vec3d1);
            }
        }

        int[] aint1 = aint;
        int k = aint.length;

        for (int l = 0; l < k; ++l) {
            int i1 = aint1[l];

            amovingobjectposition[i1] = null;
        }

        MovingObjectPosition movingobjectposition = null;
        double d0 = 0.0D;
        MovingObjectPosition[] amovingobjectposition1 = amovingobjectposition;
        int j1 = amovingobjectposition.length;

        for (int k1 = 0; k1 < j1; ++k1) {
            MovingObjectPosition movingobjectposition1 = amovingobjectposition1[k1];

            if (movingobjectposition1 != null) {
                double d1 = movingobjectposition1.pos.distanceSquared(vec3d1);

                if (d1 > d0) {
                    movingobjectposition = movingobjectposition1;
                    d0 = d1;
                }
            }
        }

        return movingobjectposition;
    }

    public IBlockData fromLegacyData(int i) {
        IBlockData iblockdata = this.getBlockData().set(BlockStairs.HALF, (i & 4) > 0 ? EnumHalf.TOP : EnumHalf.BOTTOM);

        iblockdata = iblockdata.set(BlockStairs.FACING, EnumDirection.fromType1(5 - (i & 3)));
        return iblockdata;
    }

    public int toLegacyData(IBlockData iblockdata) {
        int i = 0;

        if (iblockdata.get(BlockStairs.HALF) == EnumHalf.TOP) {
            i |= 4;
        }

        i |= 5 - ((EnumDirection) iblockdata.get(BlockStairs.FACING)).a();
        return i;
    }

    public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        if (this.h(iblockaccess, blockposition)) {
            switch (this.g(iblockaccess, blockposition)) {
            case 0:
                iblockdata = iblockdata.set(BlockStairs.SHAPE, EnumStairShape.STRAIGHT);
                break;

            case 1:
                iblockdata = iblockdata.set(BlockStairs.SHAPE, EnumStairShape.INNER_RIGHT);
                break;

            case 2:
                iblockdata = iblockdata.set(BlockStairs.SHAPE, EnumStairShape.INNER_LEFT);
            }
        } else {
            switch (this.f(iblockaccess, blockposition)) {
            case 0:
                iblockdata = iblockdata.set(BlockStairs.SHAPE, EnumStairShape.STRAIGHT);
                break;

            case 1:
                iblockdata = iblockdata.set(BlockStairs.SHAPE, EnumStairShape.OUTER_RIGHT);
                break;

            case 2:
                iblockdata = iblockdata.set(BlockStairs.SHAPE, EnumStairShape.OUTER_LEFT);
            }
        }

        return iblockdata;
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockStairs.FACING, BlockStairs.HALF, BlockStairs.SHAPE});
    }
}
