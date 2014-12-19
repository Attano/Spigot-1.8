package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

// CraftBukkit start
import org.bukkit.Location;
import org.bukkit.event.entity.EntityPortalExitEvent;
import org.bukkit.util.Vector;
// CraftBukkit end

public class PortalTravelAgent {

    private final WorldServer a;
    private final Random b;
    private final LongHashMap c = new LongHashMap();
    private final List d = Lists.newArrayList();

    public PortalTravelAgent(WorldServer worldserver) {
        this.a = worldserver;
        this.b = new Random(worldserver.getSeed());
    }

    public void a(Entity entity, float f) {
        if (this.a.worldProvider.getDimension() != 1) {
            if (!this.b(entity, f)) {
                this.a(entity);
                this.b(entity, f);
            }
        } else {
            int i = MathHelper.floor(entity.locX);
            int j = MathHelper.floor(entity.locY) - 1;
            int k = MathHelper.floor(entity.locZ);
            // CraftBukkit start - Modularize end portal creation
            BlockPosition created = this.createEndPortal(entity.locX, entity.locY, entity.locZ);
            entity.setPositionRotation((double) created.getX(), (double) created.getY(), (double) created.getZ(), entity.yaw, 0.0F);
            entity.motX = entity.motY = entity.motZ = 0.0D;
        }
    }

    // Split out from original a(Entity, double, double, double, float) method in order to enable being called from createPortal
    private BlockPosition createEndPortal(double x, double y, double z) {
            int i = MathHelper.floor(x);
            int j = MathHelper.floor(y) - 1;
            int k = MathHelper.floor(z);
            byte b0 = 1;
            byte b1 = 0;
            // CraftBukkit end

            for (int l = -2; l <= 2; ++l) {
                for (int i1 = -2; i1 <= 2; ++i1) {
                    for (int j1 = -1; j1 < 3; ++j1) {
                        int k1 = i + i1 * b0 + l * b1;
                        int l1 = j + j1;
                        int i2 = k + i1 * b1 - l * b0;
                        boolean flag = j1 < 0;

                        this.a.setTypeUpdate(new BlockPosition(k1, l1, i2), flag ? Blocks.OBSIDIAN.getBlockData() : Blocks.AIR.getBlockData());
                    }
                }
            }

        // CraftBukkit start
        return new BlockPosition(i, k, k);
    }

    // use logic based on creation to verify end portal
    private BlockPosition findEndPortal(BlockPosition portal) {
        int i = portal.getX();
        int j = portal.getY() - 1;
        int k = portal.getZ();
        byte b0 = 1;
        byte b1 = 0;

        for (int l = -2; l <= 2; ++l) {
            for (int i1 = -2; i1 <= 2; ++i1) {
                for (int j1 = -1; j1 < 3; ++j1) {
                    int k1 = i + i1 * b0 + l * b1;
                    int l1 = j + j1;
                    int i2 = k + i1 * b1 - l * b0;
                    boolean flag = j1 < 0;

                    if (this.a.getType(new BlockPosition(k1, l1, i2)).getBlock() != (flag ? Blocks.OBSIDIAN : Blocks.AIR)) {
                        return null;
                    }
                }
            }
        }
        return new BlockPosition(i, j, k);
    }
    // CraftBukkit end

    public boolean b(Entity entity, float f) {
        // CraftBukkit start - Modularize portal search process and entity teleportation
        BlockPosition found = this.findPortal(entity.locX, entity.locY, entity.locZ, 128);
        if (found == null) {
            return false;
        }

        Location exit = new Location(this.a.getWorld(), found.getX(), found.getY(), found.getZ(), f, entity.pitch);
        Vector velocity = entity.getBukkitEntity().getVelocity();
        this.adjustExit(entity, exit, velocity);
        entity.setPositionRotation(exit.getX(), exit.getY(), exit.getZ(), exit.getYaw(), exit.getPitch());
        if (entity.motX != velocity.getX() || entity.motY != velocity.getY() || entity.motZ != velocity.getZ()) {
            entity.getBukkitEntity().setVelocity(velocity);
        }
        return true;
    }

    public BlockPosition findPortal(double x, double y, double z, int short1) {
        if (this.a.getWorld().getEnvironment() == org.bukkit.World.Environment.THE_END) {
            return this.findEndPortal(this.a.worldProvider.h());
        }
        // CraftBukkit end
        double d0 = -1.0D;
        // CraftBukkit start
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(z);
        // CraftBukkit end
        boolean flag1 = true;
        Object object = BlockPosition.ZERO;
        long k = ChunkCoordIntPair.a(i, j);

        if (this.c.contains(k)) {
            ChunkCoordinatesPortal chunkcoordinatesportal = (ChunkCoordinatesPortal) this.c.getEntry(k);

            d0 = 0.0D;
            object = chunkcoordinatesportal;
            chunkcoordinatesportal.b = this.a.getTime();
            flag1 = false;
        } else {
            BlockPosition blockposition = new BlockPosition(x, y, z);

            for (int l = -128; l <= 128; ++l) {
                BlockPosition blockposition1;

                for (int i1 = -128; i1 <= 128; ++i1) {
                    for (BlockPosition blockposition2 = blockposition.a(l, this.a.V() - 1 - blockposition.getY(), i1); blockposition2.getY() >= 0; blockposition2 = blockposition1) {
                        blockposition1 = blockposition2.down();
                        if (this.a.getType(blockposition2).getBlock() == Blocks.PORTAL) {
                            while (this.a.getType(blockposition1 = blockposition2.down()).getBlock() == Blocks.PORTAL) {
                                blockposition2 = blockposition1;
                            }

                            double d1 = blockposition2.i(blockposition);

                            if (d0 < 0.0D || d1 < d0) {
                                d0 = d1;
                                object = blockposition2;
                            }
                        }
                    }
                }
            }
        }

        if (d0 >= 0.0D) {
            if (flag1) {
                this.c.put(k, new ChunkCoordinatesPortal(this, (BlockPosition) object, this.a.getTime()));
                this.d.add(Long.valueOf(k));
            }
            // CraftBukkit start - Move entity teleportation logic into exit
            return (BlockPosition) object;
        } else {
            return null;
        }
    }

    // Entity repositioning logic split out from original b method and combined with repositioning logic for The End from original a method
    public void adjustExit(Entity entity, Location position, Vector velocity) {
        Location from = position.clone();
        Vector before = velocity.clone();
        BlockPosition object = new BlockPosition(position.getBlockX(), position.getBlockY(), position.getBlockZ());
        float f = position.getYaw();

        if (this.a.getWorld().getEnvironment() == org.bukkit.World.Environment.THE_END) {
            // entity.setPositionRotation((double) i, (double) j, (double) k, entity.yaw, 0.0F);
            // entity.motX = entity.motY = entity.motZ = 0.0D;
            position.setPitch(0.0F);
            velocity.setX(0);
            velocity.setY(0);
            velocity.setZ(0);
        } else {
            // CraftBukkit end
            double d2 = (double) ((BlockPosition) object).getX() + 0.5D;
            double d3 = (double) ((BlockPosition) object).getY() + 0.5D;
            double d4 = (double) ((BlockPosition) object).getZ() + 0.5D;
            EnumDirection enumdirection = null;

            if (this.a.getType(((BlockPosition) object).west()).getBlock() == Blocks.PORTAL) {
                enumdirection = EnumDirection.NORTH;
            }

            if (this.a.getType(((BlockPosition) object).east()).getBlock() == Blocks.PORTAL) {
                enumdirection = EnumDirection.SOUTH;
            }

            if (this.a.getType(((BlockPosition) object).north()).getBlock() == Blocks.PORTAL) {
                enumdirection = EnumDirection.EAST;
            }

            if (this.a.getType(((BlockPosition) object).south()).getBlock() == Blocks.PORTAL) {
                enumdirection = EnumDirection.WEST;
            }

            EnumDirection enumdirection1 = EnumDirection.fromType2(entity.aG());

            if (enumdirection != null) {
                EnumDirection enumdirection2 = enumdirection.f();
                BlockPosition blockposition3 = ((BlockPosition) object).shift(enumdirection);
                boolean flag2 = this.a(blockposition3);
                boolean flag3 = this.a(blockposition3.shift(enumdirection2));

                if (flag3 && flag2) {
                    object = ((BlockPosition) object).shift(enumdirection2);
                    enumdirection = enumdirection.opposite();
                    enumdirection2 = enumdirection2.opposite();
                    BlockPosition blockposition4 = ((BlockPosition) object).shift(enumdirection);

                    flag2 = this.a(blockposition4);
                    flag3 = this.a(blockposition4.shift(enumdirection2));
                }

                float f1 = 0.5F;
                float f2 = 0.5F;

                if (!flag3 && flag2) {
                    f1 = 1.0F;
                } else if (flag3 && !flag2) {
                    f1 = 0.0F;
                } else if (flag3) {
                    f2 = 0.0F;
                }

                d2 = (double) ((BlockPosition) object).getX() + 0.5D;
                d3 = (double) ((BlockPosition) object).getY() + 0.5D;
                d4 = (double) ((BlockPosition) object).getZ() + 0.5D;
                d2 += (double) ((float) enumdirection2.getAdjacentX() * f1 + (float) enumdirection.getAdjacentX() * f2);
                d4 += (double) ((float) enumdirection2.getAdjacentZ() * f1 + (float) enumdirection.getAdjacentZ() * f2);
                float f3 = 0.0F;
                float f4 = 0.0F;
                float f5 = 0.0F;
                float f6 = 0.0F;

                if (enumdirection == enumdirection1) {
                    f3 = 1.0F;
                    f4 = 1.0F;
                } else if (enumdirection == enumdirection1.opposite()) {
                    f3 = -1.0F;
                    f4 = -1.0F;
                } else if (enumdirection == enumdirection1.e()) {
                    f5 = 1.0F;
                    f6 = -1.0F;
                } else {
                    f5 = -1.0F;
                    f6 = 1.0F;
                }

                // CraftBukkit start
                double d5 = velocity.getX();
                double d6 = velocity.getZ();
                // CraftBukkit end

                // CraftBukkit start - Adjust position and velocity instances instead of entity
                velocity.setX(d5 * (double) f3 + d6 * (double) f6);
                velocity.setZ(d5 * (double) f5 + d6 * (double) f4);
                f = f - (float) (enumdirection1.b() * 90) + (float) (enumdirection.b() * 90);
            } else {
                velocity.setX(0);
                velocity.setY(0);
                velocity.setZ(0);
            }

            // entity.setPositionRotation(d2, d3, d4, entity.yaw, entity.pitch);
            position.setX(d2);
            position.setY(d3);
            position.setZ(d4);
            position.setYaw(f);
        }
        EntityPortalExitEvent event = new EntityPortalExitEvent(entity.getBukkitEntity(), from, position, before, velocity);
        this.a.getServer().getPluginManager().callEvent(event);
        Location to = event.getTo();
        if (event.isCancelled() || to == null || !entity.isAlive()) {
            position.setX(from.getX());
            position.setY(from.getY());
            position.setZ(from.getZ());
            position.setYaw(from.getYaw());
            position.setPitch(from.getPitch());
            velocity.copy(before);
        } else {
            position.setX(to.getX());
            position.setY(to.getY());
            position.setZ(to.getZ());
            position.setYaw(to.getYaw());
            position.setPitch(to.getPitch());
            velocity.copy(event.getAfter()); // event.getAfter() will never be null, as setAfter() will cause an NPE if null is passed in
        }
        // CraftBukkit end
    }

    private boolean a(BlockPosition blockposition) {
        return !this.a.isEmpty(blockposition) || !this.a.isEmpty(blockposition.up());
    }

    public boolean a(Entity entity) {
        // CraftBukkit start - Allow for portal creation to be based on coordinates instead of entity
        return this.createPortal(entity.locX, entity.locY, entity.locZ, 16);
    }

    public boolean createPortal(double x, double y, double z, int b0) {
        if (this.a.getWorld().getEnvironment() == org.bukkit.World.Environment.THE_END) {
            createEndPortal(x, y, z);
            return true;
        }
        // CraftBukkit end
        double d0 = -1.0D;
        // CraftBukkit start
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(y);
        int k = MathHelper.floor(z);
        // CraftBukkit end
        int l = i;
        int i1 = j;
        int j1 = k;
        int k1 = 0;
        int l1 = this.b.nextInt(4);

        int i2;
        double d1;
        int j2;
        double d2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        double d3;
        double d4;

        for (i2 = i - b0; i2 <= i + b0; ++i2) {
            d1 = (double) i2 + 0.5D - x; // CraftBukkit

            for (j2 = k - b0; j2 <= k + b0; ++j2) {
                d2 = (double) j2 + 0.5D - z; // CraftBukkit

                label271:
                for (k2 = this.a.V() - 1; k2 >= 0; --k2) {
                    if (this.a.isEmpty(new BlockPosition(i2, k2, j2))) {
                        while (k2 > 0 && this.a.isEmpty(new BlockPosition(i2, k2 - 1, j2))) {
                            --k2;
                        }

                        for (l2 = l1; l2 < l1 + 4; ++l2) {
                            i3 = l2 % 2;
                            j3 = 1 - i3;
                            if (l2 % 4 >= 2) {
                                i3 = -i3;
                                j3 = -j3;
                            }

                            for (k3 = 0; k3 < 3; ++k3) {
                                for (l3 = 0; l3 < 4; ++l3) {
                                    for (i4 = -1; i4 < 4; ++i4) {
                                        j4 = i2 + (l3 - 1) * i3 + k3 * j3;
                                        k4 = k2 + i4;
                                        int l4 = j2 + (l3 - 1) * j3 - k3 * i3;

                                        if (i4 < 0 && !this.a.getType(new BlockPosition(j4, k4, l4)).getBlock().getMaterial().isBuildable() || i4 >= 0 && !this.a.isEmpty(new BlockPosition(j4, k4, l4))) {
                                            continue label271;
                                        }
                                    }
                                }
                            }

                            d3 = (double) k2 + 0.5D - y; // CraftBukkit
                            d4 = d1 * d1 + d3 * d3 + d2 * d2;
                            if (d0 < 0.0D || d4 < d0) {
                                d0 = d4;
                                l = i2;
                                i1 = k2;
                                j1 = j2;
                                k1 = l2 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D) {
            for (i2 = i - b0; i2 <= i + b0; ++i2) {
                d1 = (double) i2 + 0.5D - x; // CraftBukkit

                for (j2 = k - b0; j2 <= k + b0; ++j2) {
                    d2 = (double) j2 + 0.5D - z; // CraftBukkit

                    label219:
                    for (k2 = this.a.V() - 1; k2 >= 0; --k2) {
                        if (this.a.isEmpty(new BlockPosition(i2, k2, j2))) {
                            while (k2 > 0 && this.a.isEmpty(new BlockPosition(i2, k2 - 1, j2))) {
                                --k2;
                            }

                            for (l2 = l1; l2 < l1 + 2; ++l2) {
                                i3 = l2 % 2;
                                j3 = 1 - i3;

                                for (k3 = 0; k3 < 4; ++k3) {
                                    for (l3 = -1; l3 < 4; ++l3) {
                                        i4 = i2 + (k3 - 1) * i3;
                                        j4 = k2 + l3;
                                        k4 = j2 + (k3 - 1) * j3;
                                        if (l3 < 0 && !this.a.getType(new BlockPosition(i4, j4, k4)).getBlock().getMaterial().isBuildable() || l3 >= 0 && !this.a.isEmpty(new BlockPosition(i4, j4, k4))) {
                                            continue label219;
                                        }
                                    }
                                }

                                d3 = (double) k2 + 0.5D - y; // CraftBukkit
                                d4 = d1 * d1 + d3 * d3 + d2 * d2;
                                if (d0 < 0.0D || d4 < d0) {
                                    d0 = d4;
                                    l = i2;
                                    i1 = k2;
                                    j1 = j2;
                                    k1 = l2 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int i5 = l;
        int j5 = i1;

        j2 = j1;
        int k5 = k1 % 2;
        int l5 = 1 - k5;

        if (k1 % 4 >= 2) {
            k5 = -k5;
            l5 = -l5;
        }

        if (d0 < 0.0D) {
            i1 = MathHelper.clamp(i1, 70, this.a.V() - 10);
            j5 = i1;

            for (k2 = -1; k2 <= 1; ++k2) {
                for (l2 = 1; l2 < 3; ++l2) {
                    for (i3 = -1; i3 < 3; ++i3) {
                        j3 = i5 + (l2 - 1) * k5 + k2 * l5;
                        k3 = j5 + i3;
                        l3 = j2 + (l2 - 1) * l5 - k2 * k5;
                        boolean flag = i3 < 0;

                        this.a.setTypeUpdate(new BlockPosition(j3, k3, l3), flag ? Blocks.OBSIDIAN.getBlockData() : Blocks.AIR.getBlockData());
                    }
                }
            }
        }

        IBlockData iblockdata = Blocks.PORTAL.getBlockData().set(BlockPortal.AXIS, k5 != 0 ? EnumAxis.X : EnumAxis.Z);

        for (l2 = 0; l2 < 4; ++l2) {
            for (i3 = 0; i3 < 4; ++i3) {
                for (j3 = -1; j3 < 4; ++j3) {
                    k3 = i5 + (i3 - 1) * k5;
                    l3 = j5 + j3;
                    i4 = j2 + (i3 - 1) * l5;
                    boolean flag1 = i3 == 0 || i3 == 3 || j3 == -1 || j3 == 3;

                    this.a.setTypeAndData(new BlockPosition(k3, l3, i4), flag1 ? Blocks.OBSIDIAN.getBlockData() : iblockdata, 2);
                }
            }

            for (i3 = 0; i3 < 4; ++i3) {
                for (j3 = -1; j3 < 4; ++j3) {
                    k3 = i5 + (i3 - 1) * k5;
                    l3 = j5 + j3;
                    i4 = j2 + (i3 - 1) * l5;
                    this.a.applyPhysics(new BlockPosition(k3, l3, i4), this.a.getType(new BlockPosition(k3, l3, i4)).getBlock());
                }
            }
        }

        return true;
    }

    public void a(long i) {
        if (i % 100L == 0L) {
            Iterator iterator = this.d.iterator();
            long j = i - 600L;

            while (iterator.hasNext()) {
                Long olong = (Long) iterator.next();
                ChunkCoordinatesPortal chunkcoordinatesportal = (ChunkCoordinatesPortal) this.c.getEntry(olong.longValue());

                if (chunkcoordinatesportal == null || chunkcoordinatesportal.b < j) {
                    iterator.remove();
                    this.c.remove(olong.longValue());
                }
            }
        }

    }
}
