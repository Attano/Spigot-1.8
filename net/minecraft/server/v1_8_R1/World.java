package net.minecraft.server;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;

// CraftBukkit start
import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.util.LongHashSet;
import org.bukkit.craftbukkit.SpigotTimings; // Spigot
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
// CraftBukkit end

public abstract class World implements IBlockAccess {

    protected boolean e;
    // Spigot start - guard entity list from removals
    public List entityList = new java.util.ArrayList()
    {
        @Override
        public Object remove(int index)
        {
            guard();
            return super.remove( index );
        }

        @Override
        public boolean remove(Object o)
        {
            guard();
            return super.remove( o );
        }

        private void guard()
        {
            if ( guardEntityList )
            {
                throw new java.util.ConcurrentModificationException();
            }
        }
    };
    // Spigot end
    protected final List g = Lists.newArrayList();
    public final List h = Lists.newArrayList();
    public final List tileEntityList = Lists.newArrayList();
    private final List a = Lists.newArrayList();
    private final List b = Lists.newArrayList();
    public final List players = Lists.newArrayList();
    public final List k = Lists.newArrayList();
    protected final IntHashMap entitiesById = new IntHashMap();
    private long c = 16777215L;
    private int d;
    protected int m = (new Random()).nextInt();
    protected final int n = 1013904223;
    protected float o;
    protected float p;
    protected float q;
    protected float r;
    private int I;
    public final Random random = new Random();
    public WorldProvider worldProvider;
    protected List u = Lists.newArrayList();
    protected IChunkProvider chunkProvider;
    protected final IDataManager dataManager;
    public WorldData worldData;
    protected boolean isLoading;
    public PersistentCollection worldMaps;
    protected PersistentVillage villages;
    public final MethodProfiler methodProfiler;
    private final Calendar J = Calendar.getInstance();
    public Scoreboard scoreboard = new Scoreboard();
    public final boolean isStatic;
    // CraftBukkit - longhashset
    // protected LongHashSet chunkTickList = new LongHashSet(); // Spigot
    private int K;
    public boolean allowMonsters;
    public boolean allowAnimals;
    private boolean L;
    private final WorldBorder M;
    int[] H;

    // CraftBukkit start Added the following
    private final CraftWorld world;
    public boolean pvpMode;
    public boolean keepSpawnInMemory = true;
    public ChunkGenerator generator;

    public boolean captureBlockStates = false;
    public boolean captureTreeGeneration = false;
    public ArrayList<BlockState> capturedBlockStates= new ArrayList<BlockState>();
    public long ticksPerAnimalSpawns;
    public long ticksPerMonsterSpawns;
    public boolean populating;
    private int tickPosition;

    // Spigot start
    private boolean guardEntityList;
    protected final gnu.trove.map.hash.TLongShortHashMap chunkTickList;
    protected float growthOdds = 100;
    protected float modifiedOdds = 100;
    private final byte chunkTickRadius;
    public static boolean haveWeSilencedAPhysicsCrash;
    public static String blockLocation;
    public List<TileEntity> triggerHoppersList = new ArrayList<TileEntity>(); // Spigot, When altHopperTicking, tile entities being added go through here.

    public static long chunkToKey(int x, int z)
    {
        long k = ( ( ( (long) x ) & 0xFFFF0000L ) << 16 ) | ( ( ( (long) x ) & 0x0000FFFFL ) << 0 );
        k |= ( ( ( (long) z ) & 0xFFFF0000L ) << 32 ) | ( ( ( (long) z ) & 0x0000FFFFL ) << 16 );
        return k;
    }

    public static int keyToX(long k)
    {
        return (int) ( ( ( k >> 16 ) & 0xFFFF0000 ) | ( k & 0x0000FFFF ) );
    }

    public static int keyToZ(long k)
    {
        return (int) ( ( ( k >> 32 ) & 0xFFFF0000L ) | ( ( k >> 16 ) & 0x0000FFFF ) );
    }

    // Spigot Start - Hoppers need to be born ticking.
    private void initializeHoppers() {
        if (this.spigotConfig.altHopperTicking) {
            for (TileEntity o : this.triggerHoppersList) {
                o.scheduleTicks();
                if (o instanceof TileEntityHopper) {
                    ((TileEntityHopper) o).convertToScheduling();
                    ((TileEntityHopper) o).scheduleHopperTick();
                }
            }
        }
        triggerHoppersList.clear();
    }
    
    // Helper method for altHopperTicking. Updates chests at the specified location,
    // accounting for double chests. Updating the chest will update adjacent hoppers.
    public void updateChestAndHoppers(BlockPosition blockposition) {
        Block block = this.getType(blockposition).getBlock();
        if (block instanceof BlockChest) {
            TileEntity tile = this.getTileEntity(blockposition);
            if (tile instanceof TileEntityChest) {
                tile.scheduleTicks();
            }
            for (int i = 2; i < 6; i++) {
            	// Facing class provides arrays for direction offset.
                BlockPosition pos = blockposition.shift(EnumDirection.fromType1(i));
                if (this.getType(pos) == block) {
                    tile = this.getTileEntity(pos);
                    if (tile instanceof TileEntityChest) {
                        tile.scheduleTicks();
                    }
                    break;
                }
            }
        }
    }
    // Spigot end

    public final org.spigotmc.SpigotWorldConfig spigotConfig; // Spigot

    public final SpigotTimings.WorldTimingsHandler timings; // Spigot

    public CraftWorld getWorld() {
        return this.world;
    }

    public CraftServer getServer() {
        return (CraftServer) Bukkit.getServer();
    }

    public Chunk getChunkIfLoaded(int x, int z) {
        return ((ChunkProviderServer) this.chunkProvider).getChunkIfLoaded(x, z);
    }

    protected World(IDataManager idatamanager, WorldData worlddata, WorldProvider worldprovider, MethodProfiler methodprofiler, boolean flag, ChunkGenerator gen, org.bukkit.World.Environment env) {
        this.spigotConfig = new org.spigotmc.SpigotWorldConfig( worlddata.getName() ); // Spigot
        this.generator = gen;
        this.world = new CraftWorld((WorldServer) this, gen, env);
        this.ticksPerAnimalSpawns = this.getServer().getTicksPerAnimalSpawns(); // CraftBukkit
        this.ticksPerMonsterSpawns = this.getServer().getTicksPerMonsterSpawns(); // CraftBukkit
        // CraftBukkit end
        // Spigot start
        this.chunkTickRadius = (byte) ( ( this.getServer().getViewDistance() < 7 ) ? this.getServer().getViewDistance() : 7 );
        this.chunkTickList = new gnu.trove.map.hash.TLongShortHashMap( spigotConfig.chunksPerTick * 5, 0.7f, Long.MIN_VALUE, Short.MIN_VALUE );
        this.chunkTickList.setAutoCompactionFactor( 0 );
        // Spigot end

        this.K = this.random.nextInt(12000);
        this.allowMonsters = true;
        this.allowAnimals = true;
        this.H = new int['\u8000'];
        this.dataManager = idatamanager;
        this.methodProfiler = methodprofiler;
        this.worldData = worlddata;
        this.worldProvider = worldprovider;
        this.isStatic = flag;
        this.M = worldprovider.getWorldBorder();

        this.getServer().addWorld(this.world); // CraftBukkit
        timings = new SpigotTimings.WorldTimingsHandler(this); // Spigot - code below can generate new world and access timings
    }

    public World b() {
        return this;
    }

    public BiomeBase getBiome(BlockPosition blockposition) {
        if (this.isLoaded(blockposition)) {
            Chunk chunk = this.getChunkAtWorldCoords(blockposition);

            try {
                return chunk.getBiome(blockposition, this.worldProvider.m());
            } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.a(throwable, "Getting biome");
                CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Coordinates of biome request");

                crashreportsystemdetails.a("Location", (Callable) (new CrashReportWorldLocation(this, blockposition)));
                throw new ReportedException(crashreport);
            }
        } else {
            return this.worldProvider.m().getBiome(blockposition, BiomeBase.PLAINS);
        }
    }

    public WorldChunkManager getWorldChunkManager() {
        return this.worldProvider.m();
    }

    protected abstract IChunkProvider k();

    public void a(WorldSettings worldsettings) {
        this.worldData.d(true);
    }

    public Block c(BlockPosition blockposition) {
        BlockPosition blockposition1;

        for (blockposition1 = new BlockPosition(blockposition.getX(), 63, blockposition.getZ()); !this.isEmpty(blockposition1.up()); blockposition1 = blockposition1.up()) {
            ;
        }

        return this.getType(blockposition1).getBlock();
    }

    private boolean isValidLocation(BlockPosition blockposition) {
        return blockposition.getX() >= -30000000 && blockposition.getZ() >= -30000000 && blockposition.getX() < 30000000 && blockposition.getZ() < 30000000 && blockposition.getY() >= 0 && blockposition.getY() < 256;
    }

    public boolean isEmpty(BlockPosition blockposition) {
        return this.getType(blockposition).getBlock().getMaterial() == Material.AIR;
    }

    public boolean isLoaded(BlockPosition blockposition) {
        return this.a(blockposition, true);
    }

    public boolean a(BlockPosition blockposition, boolean flag) {
        return !this.isValidLocation(blockposition) ? false : this.isChunkLoaded(blockposition.getX() >> 4, blockposition.getZ() >> 4, flag);
    }

    public boolean areChunksLoaded(BlockPosition blockposition, int i) {
        return this.areChunksLoaded(blockposition, i, true);
    }

    public boolean areChunksLoaded(BlockPosition blockposition, int i, boolean flag) {
        return this.isAreaLoaded(blockposition.getX() - i, blockposition.getY() - i, blockposition.getZ() - i, blockposition.getX() + i, blockposition.getY() + i, blockposition.getZ() + i, flag);
    }

    public boolean areChunksLoadedBetween(BlockPosition blockposition, BlockPosition blockposition1) {
        return this.areChunksLoadedBetween(blockposition, blockposition1, true);
    }

    public boolean areChunksLoadedBetween(BlockPosition blockposition, BlockPosition blockposition1, boolean flag) {
        return this.isAreaLoaded(blockposition.getX(), blockposition.getY(), blockposition.getZ(), blockposition1.getX(), blockposition1.getY(), blockposition1.getZ(), flag);
    }

    public boolean a(StructureBoundingBox structureboundingbox) {
        return this.b(structureboundingbox, true);
    }

    public boolean b(StructureBoundingBox structureboundingbox, boolean flag) {
        return this.isAreaLoaded(structureboundingbox.a, structureboundingbox.b, structureboundingbox.c, structureboundingbox.d, structureboundingbox.e, structureboundingbox.f, flag);
    }

    private boolean isAreaLoaded(int i, int j, int k, int l, int i1, int j1, boolean flag) {
        if (i1 >= 0 && j < 256) {
            i >>= 4;
            k >>= 4;
            l >>= 4;
            j1 >>= 4;

            for (int k1 = i; k1 <= l; ++k1) {
                for (int l1 = k; l1 <= j1; ++l1) {
                    if (!this.isChunkLoaded(k1, l1, flag)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    protected boolean isChunkLoaded(int i, int j, boolean flag) {
        return this.chunkProvider.isChunkLoaded(i, j) && (flag || !this.chunkProvider.getOrCreateChunk(i, j).isEmpty());
    }

    public Chunk getChunkAtWorldCoords(BlockPosition blockposition) {
        return this.getChunkAt(blockposition.getX() >> 4, blockposition.getZ() >> 4);
    }

    public Chunk getChunkAt(int i, int j) {
        return this.chunkProvider.getOrCreateChunk(i, j);
    }

    public boolean setTypeAndData(BlockPosition blockposition, IBlockData iblockdata, int i) {
        // CraftBukkit start - tree generation
        if (this.captureTreeGeneration) {
            BlockState blockstate = null;
            Iterator<BlockState> it = capturedBlockStates.iterator();
            while (it.hasNext()) {
                BlockState previous = it.next();
                if (previous.getX() == blockposition.getX() && previous.getY() == blockposition.getY() && previous.getZ() == blockposition.getZ()) {
                    blockstate = previous;
                    it.remove();
                    break;
                }
            }
            if (blockstate == null) {
                blockstate = org.bukkit.craftbukkit.block.CraftBlockState.getBlockState(this, blockposition.getX(), blockposition.getY(), blockposition.getZ(), i);
            }
            blockstate.setTypeId(CraftMagicNumbers.getId(iblockdata.getBlock()));
            blockstate.setRawData((byte) iblockdata.getBlock().toLegacyData(iblockdata));
            this.capturedBlockStates.add(blockstate);
            return true;
        }
        // CraftBukkit end
        if (!this.isValidLocation(blockposition)) {
            return false;
        } else if (!this.isStatic && this.worldData.getType() == WorldType.DEBUG_ALL_BLOCK_STATES) {
            return false;
        } else {
            Chunk chunk = this.getChunkAtWorldCoords(blockposition);
            Block block = iblockdata.getBlock();
                
            // CraftBukkit start - capture blockstates
            BlockState blockstate = null;
            if (this.captureBlockStates) {
                blockstate = org.bukkit.craftbukkit.block.CraftBlockState.getBlockState(this, blockposition.getX(), blockposition.getY(), blockposition.getZ(), i);
                this.capturedBlockStates.add(blockstate);
            }
            // CraftBukkit end
                
            IBlockData iblockdata1 = chunk.a(blockposition, iblockdata);

            if (iblockdata1 == null) {
                // CraftBukkit start - remove blockstate if failed
                if (!this.captureBlockStates) {
                    this.capturedBlockStates.remove(blockstate);
                }
                // CraftBukkit end
                return false;
            } else {
                Block block1 = iblockdata1.getBlock();

                if (block.n() != block1.n() || block.p() != block1.p()) {
                    this.methodProfiler.a("checkLight");
                    this.x(blockposition);
                    this.methodProfiler.b();
                }

                /*
                if ((i & 2) != 0 && (!this.isStatic || (i & 4) == 0) && chunk.isReady()) {
                    this.notify(blockposition);
                }

                if (!this.isStatic && (i & 1) != 0) {
                    this.update(blockposition, iblockdata1.getBlock());
                    if (block.isComplexRedstone()) {
                        this.updateAdjacentComparators(blockposition, block);
                    }
                }
                */

                // CraftBukkit start
                if (!this.captureBlockStates) { // Don't notify clients or update physics while capturing blockstates
                    // Modularize client and physic updates
                    notifyAndUpdatePhysics(blockposition, chunk, block1, block, i);
                }
                // Spigot start - If this block is changing to that which a chest beneath it
                // becomes able to be opened, then the chest must be updated.
                // block1 is the old block. block is the new block. r returns true if the block type
                // prevents access to a chest.
                if (this.spigotConfig.altHopperTicking && block1 != null && block1.isOccluding()&& !block.isOccluding()) {
                    this.updateChestAndHoppers(blockposition.down());
                }
                // Spigot end
                // CraftBukkit end

                return true;
            }
        }
    }

    // CraftBukkit start - Split off from original setTypeAndData(int i, int j, int k, Block block, int l, int i1) method in order to directly send client and physic updates
    public void notifyAndUpdatePhysics(BlockPosition blockposition, Chunk chunk, Block oldBlock, Block newBLock, int flag) {
        if ((flag & 2) != 0 && (chunk == null || chunk.isReady())) {  // allow chunk to be null here as chunk.isReady() is false when we send our notification during block placement
            this.notify(blockposition);
        }

        if (!this.isStatic && (flag & 1) != 0) {
            this.update(blockposition, oldBlock);
            if (newBLock.isComplexRedstone()) {
                this.updateAdjacentComparators(blockposition, newBLock);
            }
        }
    }
    // CraftBukkit end

    public boolean setAir(BlockPosition blockposition) {
        return this.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 3);
    }

    public boolean setAir(BlockPosition blockposition, boolean flag) {
        IBlockData iblockdata = this.getType(blockposition);
        Block block = iblockdata.getBlock();

        if (block.getMaterial() == Material.AIR) {
            return false;
        } else {
            this.triggerEffect(2001, blockposition, Block.getCombinedId(iblockdata));
            if (flag) {
                block.b(this, blockposition, iblockdata, 0);
            }

            return this.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 3);
        }
    }

    public boolean setTypeUpdate(BlockPosition blockposition, IBlockData iblockdata) {
        return this.setTypeAndData(blockposition, iblockdata, 3);
    }

    public void notify(BlockPosition blockposition) {
        for (int i = 0; i < this.u.size(); ++i) {
            ((IWorldAccess) this.u.get(i)).a(blockposition);
        }

    }

    public void update(BlockPosition blockposition, Block block) {
        if (this.worldData.getType() != WorldType.DEBUG_ALL_BLOCK_STATES) {
            // CraftBukkit start
            if (populating) {
                return;
            }
            // CraftBukkit end
            this.applyPhysics(blockposition, block);
        }

    }

    public void a(int i, int j, int k, int l) {
        int i1;

        if (k > l) {
            i1 = l;
            l = k;
            k = i1;
        }

        if (!this.worldProvider.o()) {
            for (i1 = k; i1 <= l; ++i1) {
                this.c(EnumSkyBlock.SKY, new BlockPosition(i, i1, j));
            }
        }

        this.b(i, k, j, i, l, j);
    }

    public void b(BlockPosition blockposition, BlockPosition blockposition1) {
        this.b(blockposition.getX(), blockposition.getY(), blockposition.getZ(), blockposition1.getX(), blockposition1.getY(), blockposition1.getZ());
    }

    public void b(int i, int j, int k, int l, int i1, int j1) {
        for (int k1 = 0; k1 < this.u.size(); ++k1) {
            ((IWorldAccess) this.u.get(k1)).a(i, j, k, l, i1, j1);
        }

    }

    public void applyPhysics(BlockPosition blockposition, Block block) {
        this.d(blockposition.west(), block);
        this.d(blockposition.east(), block);
        this.d(blockposition.down(), block);
        this.d(blockposition.up(), block);
        this.d(blockposition.north(), block);
        this.d(blockposition.south(), block);
        spigotConfig.antiXrayInstance.updateNearbyBlocks(this, blockposition); // Spigot
    }

    public void a(BlockPosition blockposition, Block block, EnumDirection enumdirection) {
        if (enumdirection != EnumDirection.WEST) {
            this.d(blockposition.west(), block);
        }

        if (enumdirection != EnumDirection.EAST) {
            this.d(blockposition.east(), block);
        }

        if (enumdirection != EnumDirection.DOWN) {
            this.d(blockposition.down(), block);
        }

        if (enumdirection != EnumDirection.UP) {
            this.d(blockposition.up(), block);
        }

        if (enumdirection != EnumDirection.NORTH) {
            this.d(blockposition.north(), block);
        }

        if (enumdirection != EnumDirection.SOUTH) {
            this.d(blockposition.south(), block);
        }

    }

    public void d(BlockPosition blockposition, Block block) {
        if (!this.isStatic) {
            IBlockData iblockdata = this.getType(blockposition);

            try {
                // CraftBukkit start
                CraftWorld world = ((WorldServer) this).getWorld();
                if (world != null) {
                    BlockPhysicsEvent event = new BlockPhysicsEvent(world.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), CraftMagicNumbers.getId(block));
                    this.getServer().getPluginManager().callEvent(event);

                    if (event.isCancelled()) {
                        return;
                    }
                }
                // CraftBukkit end
                iblockdata.getBlock().doPhysics(this, blockposition, iblockdata, block);
            } catch (StackOverflowError stackoverflowerror) { // Spigot Start
                haveWeSilencedAPhysicsCrash = true;
                blockLocation = blockposition.getX() + ", " + blockposition.getY() + ", " + blockposition.getZ(); // Spigot End
            } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.a(throwable, "Exception while updating neighbours");
                CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Block being updated");

                crashreportsystemdetails.a("Source block type", (Callable) (new CrashReportSourceBlockType(this, block)));
                CrashReportSystemDetails.a(crashreportsystemdetails, blockposition, iblockdata);
                throw new ReportedException(crashreport);
            }
        }
    }

    public boolean a(BlockPosition blockposition, Block block) {
        return false;
    }

    public boolean i(BlockPosition blockposition) {
        return this.getChunkAtWorldCoords(blockposition).d(blockposition);
    }

    public boolean j(BlockPosition blockposition) {
        if (blockposition.getY() >= 63) {
            return this.i(blockposition);
        } else {
            BlockPosition blockposition1 = new BlockPosition(blockposition.getX(), 63, blockposition.getZ());

            if (!this.i(blockposition1)) {
                return false;
            } else {
                for (blockposition1 = blockposition1.down(); blockposition1.getY() > blockposition.getY(); blockposition1 = blockposition1.down()) {
                    Block block = this.getType(blockposition1).getBlock();

                    if (block.n() > 0 && !block.getMaterial().isLiquid()) {
                        return false;
                    }
                }

                return true;
            }
        }
    }

    public int k(BlockPosition blockposition) {
        if (blockposition.getY() < 0) {
            return 0;
        } else {
            if (blockposition.getY() >= 256) {
                blockposition = new BlockPosition(blockposition.getX(), 255, blockposition.getZ());
            }

            return this.getChunkAtWorldCoords(blockposition).a(blockposition, 0);
        }
    }

    public int getLightLevel(BlockPosition blockposition) {
        return this.c(blockposition, true);
    }

    public int c(BlockPosition blockposition, boolean flag) {
        if (blockposition.getX() >= -30000000 && blockposition.getZ() >= -30000000 && blockposition.getX() < 30000000 && blockposition.getZ() < 30000000) {
            if (flag && this.getType(blockposition).getBlock().q()) {
                int i = this.c(blockposition.up(), false);
                int j = this.c(blockposition.east(), false);
                int k = this.c(blockposition.west(), false);
                int l = this.c(blockposition.south(), false);
                int i1 = this.c(blockposition.north(), false);

                if (j > i) {
                    i = j;
                }

                if (k > i) {
                    i = k;
                }

                if (l > i) {
                    i = l;
                }

                if (i1 > i) {
                    i = i1;
                }

                return i;
            } else if (blockposition.getY() < 0) {
                return 0;
            } else {
                if (blockposition.getY() >= 256) {
                    blockposition = new BlockPosition(blockposition.getX(), 255, blockposition.getZ());
                }

                Chunk chunk = this.getChunkAtWorldCoords(blockposition);

                return chunk.a(blockposition, this.d);
            }
        } else {
            return 15;
        }
    }

    public BlockPosition getHighestBlockYAt(BlockPosition blockposition) {
        int i;

        if (blockposition.getX() >= -30000000 && blockposition.getZ() >= -30000000 && blockposition.getX() < 30000000 && blockposition.getZ() < 30000000) {
            if (this.isChunkLoaded(blockposition.getX() >> 4, blockposition.getZ() >> 4, true)) {
                i = this.getChunkAt(blockposition.getX() >> 4, blockposition.getZ() >> 4).b(blockposition.getX() & 15, blockposition.getZ() & 15);
            } else {
                i = 0;
            }
        } else {
            i = 64;
        }

        return new BlockPosition(blockposition.getX(), i, blockposition.getZ());
    }

    public int b(int i, int j) {
        if (i >= -30000000 && j >= -30000000 && i < 30000000 && j < 30000000) {
            if (!this.isChunkLoaded(i >> 4, j >> 4, true)) {
                return 0;
            } else {
                Chunk chunk = this.getChunkAt(i >> 4, j >> 4);

                return chunk.v();
            }
        } else {
            return 64;
        }
    }

    public int b(EnumSkyBlock enumskyblock, BlockPosition blockposition) {
        if (blockposition.getY() < 0) {
            blockposition = new BlockPosition(blockposition.getX(), 0, blockposition.getZ());
        }

        if (!this.isValidLocation(blockposition)) {
            return enumskyblock.c;
        } else if (!this.isLoaded(blockposition)) {
            return enumskyblock.c;
        } else {
            Chunk chunk = this.getChunkAtWorldCoords(blockposition);

            return chunk.getBrightness(enumskyblock, blockposition);
        }
    }

    public void a(EnumSkyBlock enumskyblock, BlockPosition blockposition, int i) {
        if (this.isValidLocation(blockposition)) {
            if (this.isLoaded(blockposition)) {
                Chunk chunk = this.getChunkAtWorldCoords(blockposition);

                chunk.a(enumskyblock, blockposition, i);
                this.n(blockposition);
            }
        }
    }

    public void n(BlockPosition blockposition) {
        for (int i = 0; i < this.u.size(); ++i) {
            ((IWorldAccess) this.u.get(i)).b(blockposition);
        }

    }

    public float o(BlockPosition blockposition) {
        return this.worldProvider.p()[this.getLightLevel(blockposition)];
    }

    // Spigot start
    public IBlockData getType(BlockPosition blockposition)
    {
        return getType( blockposition, true );
    }
    
    public IBlockData getType(BlockPosition blockposition, boolean useCaptured) {
        // CraftBukkit start - tree generation
        if (captureTreeGeneration && useCaptured) {
    // Spigot end
            Iterator<BlockState> it = capturedBlockStates.iterator();
            while (it.hasNext()) {
                BlockState previous = it.next();
                if (previous.getX() == blockposition.getX() && previous.getY() == blockposition.getY() && previous.getZ() == blockposition.getZ()) {
                    return CraftMagicNumbers.getBlock(previous.getTypeId()).fromLegacyData(previous.getRawData());
                }
            }
        }
        // CraftBukkit end
        if (!this.isValidLocation(blockposition)) {
            return Blocks.AIR.getBlockData();
        } else {
            Chunk chunk = this.getChunkAtWorldCoords(blockposition);

            return chunk.getBlockData(blockposition);
        }
    }

    public boolean w() {
        return this.d < 4;
    }

    public MovingObjectPosition rayTrace(Vec3D vec3d, Vec3D vec3d1) {
        return this.rayTrace(vec3d, vec3d1, false, false, false);
    }

    public MovingObjectPosition rayTrace(Vec3D vec3d, Vec3D vec3d1, boolean flag) {
        return this.rayTrace(vec3d, vec3d1, flag, false, false);
    }

    public MovingObjectPosition rayTrace(Vec3D vec3d, Vec3D vec3d1, boolean flag, boolean flag1, boolean flag2) {
        if (!Double.isNaN(vec3d.a) && !Double.isNaN(vec3d.b) && !Double.isNaN(vec3d.c)) {
            if (!Double.isNaN(vec3d1.a) && !Double.isNaN(vec3d1.b) && !Double.isNaN(vec3d1.c)) {
                int i = MathHelper.floor(vec3d1.a);
                int j = MathHelper.floor(vec3d1.b);
                int k = MathHelper.floor(vec3d1.c);
                int l = MathHelper.floor(vec3d.a);
                int i1 = MathHelper.floor(vec3d.b);
                int j1 = MathHelper.floor(vec3d.c);
                BlockPosition blockposition = new BlockPosition(l, i1, j1);

                new BlockPosition(i, j, k);
                IBlockData iblockdata = this.getType(blockposition);
                Block block = iblockdata.getBlock();

                if ((!flag1 || block.a(this, blockposition, iblockdata) != null) && block.a(iblockdata, flag)) {
                    MovingObjectPosition movingobjectposition = block.a(this, blockposition, vec3d, vec3d1);

                    if (movingobjectposition != null) {
                        return movingobjectposition;
                    }
                }

                MovingObjectPosition movingobjectposition1 = null;
                int k1 = 200;

                while (k1-- >= 0) {
                    if (Double.isNaN(vec3d.a) || Double.isNaN(vec3d.b) || Double.isNaN(vec3d.c)) {
                        return null;
                    }

                    if (l == i && i1 == j && j1 == k) {
                        return flag2 ? movingobjectposition1 : null;
                    }

                    boolean flag3 = true;
                    boolean flag4 = true;
                    boolean flag5 = true;
                    double d0 = 999.0D;
                    double d1 = 999.0D;
                    double d2 = 999.0D;

                    if (i > l) {
                        d0 = (double) l + 1.0D;
                    } else if (i < l) {
                        d0 = (double) l + 0.0D;
                    } else {
                        flag3 = false;
                    }

                    if (j > i1) {
                        d1 = (double) i1 + 1.0D;
                    } else if (j < i1) {
                        d1 = (double) i1 + 0.0D;
                    } else {
                        flag4 = false;
                    }

                    if (k > j1) {
                        d2 = (double) j1 + 1.0D;
                    } else if (k < j1) {
                        d2 = (double) j1 + 0.0D;
                    } else {
                        flag5 = false;
                    }

                    double d3 = 999.0D;
                    double d4 = 999.0D;
                    double d5 = 999.0D;
                    double d6 = vec3d1.a - vec3d.a;
                    double d7 = vec3d1.b - vec3d.b;
                    double d8 = vec3d1.c - vec3d.c;

                    if (flag3) {
                        d3 = (d0 - vec3d.a) / d6;
                    }

                    if (flag4) {
                        d4 = (d1 - vec3d.b) / d7;
                    }

                    if (flag5) {
                        d5 = (d2 - vec3d.c) / d8;
                    }

                    if (d3 == -0.0D) {
                        d3 = -1.0E-4D;
                    }

                    if (d4 == -0.0D) {
                        d4 = -1.0E-4D;
                    }

                    if (d5 == -0.0D) {
                        d5 = -1.0E-4D;
                    }

                    EnumDirection enumdirection;

                    if (d3 < d4 && d3 < d5) {
                        enumdirection = i > l ? EnumDirection.WEST : EnumDirection.EAST;
                        vec3d = new Vec3D(d0, vec3d.b + d7 * d3, vec3d.c + d8 * d3);
                    } else if (d4 < d5) {
                        enumdirection = j > i1 ? EnumDirection.DOWN : EnumDirection.UP;
                        vec3d = new Vec3D(vec3d.a + d6 * d4, d1, vec3d.c + d8 * d4);
                    } else {
                        enumdirection = k > j1 ? EnumDirection.NORTH : EnumDirection.SOUTH;
                        vec3d = new Vec3D(vec3d.a + d6 * d5, vec3d.b + d7 * d5, d2);
                    }

                    l = MathHelper.floor(vec3d.a) - (enumdirection == EnumDirection.EAST ? 1 : 0);
                    i1 = MathHelper.floor(vec3d.b) - (enumdirection == EnumDirection.UP ? 1 : 0);
                    j1 = MathHelper.floor(vec3d.c) - (enumdirection == EnumDirection.SOUTH ? 1 : 0);
                    blockposition = new BlockPosition(l, i1, j1);
                    IBlockData iblockdata1 = this.getType(blockposition);
                    Block block1 = iblockdata1.getBlock();

                    if (!flag1 || block1.a(this, blockposition, iblockdata1) != null) {
                        if (block1.a(iblockdata1, flag)) {
                            MovingObjectPosition movingobjectposition2 = block1.a(this, blockposition, vec3d, vec3d1);

                            if (movingobjectposition2 != null) {
                                return movingobjectposition2;
                            }
                        } else {
                            movingobjectposition1 = new MovingObjectPosition(EnumMovingObjectType.MISS, vec3d, enumdirection, blockposition);
                        }
                    }
                }

                return flag2 ? movingobjectposition1 : null;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void makeSound(Entity entity, String s, float f, float f1) {
        for (int i = 0; i < this.u.size(); ++i) {
            ((IWorldAccess) this.u.get(i)).a(s, entity.locX, entity.locY, entity.locZ, f, f1);
        }

    }

    public void a(EntityHuman entityhuman, String s, float f, float f1) {
        for (int i = 0; i < this.u.size(); ++i) {
            ((IWorldAccess) this.u.get(i)).a(entityhuman, s, entityhuman.locX, entityhuman.locY, entityhuman.locZ, f, f1);
        }

    }

    public void makeSound(double d0, double d1, double d2, String s, float f, float f1) {
        for (int i = 0; i < this.u.size(); ++i) {
            ((IWorldAccess) this.u.get(i)).a(s, d0, d1, d2, f, f1);
        }

    }

    public void a(double d0, double d1, double d2, String s, float f, float f1, boolean flag) {}

    public void a(BlockPosition blockposition, String s) {
        for (int i = 0; i < this.u.size(); ++i) {
            ((IWorldAccess) this.u.get(i)).a(s, blockposition);
        }

    }

    public void addParticle(EnumParticle enumparticle, double d0, double d1, double d2, double d3, double d4, double d5, int... aint) {
        this.a(enumparticle.c(), enumparticle.e(), d0, d1, d2, d3, d4, d5, aint);
    }

    private void a(int i, boolean flag, double d0, double d1, double d2, double d3, double d4, double d5, int... aint) {
        for (int j = 0; j < this.u.size(); ++j) {
            ((IWorldAccess) this.u.get(j)).a(i, flag, d0, d1, d2, d3, d4, d5, aint);
        }

    }

    public boolean strikeLightning(Entity entity) {
        this.k.add(entity);
        return true;
    }

    public boolean addEntity(Entity entity) {
        // CraftBukkit start - Used for entities other than creatures
        return addEntity(entity, SpawnReason.DEFAULT);
    }

    public boolean addEntity(Entity entity, SpawnReason spawnReason) { // Changed signature, added SpawnReason
        org.spigotmc.AsyncCatcher.catchOp( "entity add"); // Spigot
        if (entity == null) return false;
        // CraftBukkit end
        int i = MathHelper.floor(entity.locX / 16.0D);
        int j = MathHelper.floor(entity.locZ / 16.0D);
        boolean flag = entity.attachedToPlayer;

        if (entity instanceof EntityHuman) {
            flag = true;
        }

        // CraftBukkit start
        org.bukkit.event.Cancellable event = null;
        if (entity instanceof EntityLiving && !(entity instanceof EntityPlayer)) {
            boolean isAnimal = entity instanceof EntityAnimal || entity instanceof EntityWaterAnimal || entity instanceof EntityGolem;
            boolean isMonster = entity instanceof EntityMonster || entity instanceof EntityGhast || entity instanceof EntitySlime;

            if (spawnReason != SpawnReason.CUSTOM) {
                if (isAnimal && !allowAnimals || isMonster && !allowMonsters) {
                    entity.dead = true;
                    return false;
                }
            }

            event = CraftEventFactory.callCreatureSpawnEvent((EntityLiving) entity, spawnReason);
        } else if (entity instanceof EntityItem) {
            event = CraftEventFactory.callItemSpawnEvent((EntityItem) entity);
        } else if (entity.getBukkitEntity() instanceof org.bukkit.entity.Projectile) {
            // Not all projectiles extend EntityProjectile, so check for Bukkit interface instead
            event = CraftEventFactory.callProjectileLaunchEvent(entity);
        }
        // Spigot start
        else if (entity instanceof EntityExperienceOrb) {
            EntityExperienceOrb xp = (EntityExperienceOrb) entity;
            double radius = spigotConfig.expMerge;
            if (radius > 0) {
                List<Entity> entities = this.getEntities(entity, entity.getBoundingBox().grow(radius, radius, radius));
                for (Entity e : entities) {
                    if (e instanceof EntityExperienceOrb) {
                        EntityExperienceOrb loopItem = (EntityExperienceOrb) e;
                        if (!loopItem.dead) {
                            xp.value += loopItem.value;
                            loopItem.die();
                        }
                    }
                }
            }
        } // Spigot end

        if (event != null && (event.isCancelled() || entity.dead)) {
            entity.dead = true;
            return false;
        }
        // CraftBukkit end

        if (!flag && !this.isChunkLoaded(i, j, true)) {
            entity.dead = true;
            return false;
        } else {
            if (entity instanceof EntityHuman) {
                EntityHuman entityhuman = (EntityHuman) entity;

                this.players.add(entityhuman);
                this.everyoneSleeping();
            }

            this.getChunkAt(i, j).a(entity);
            this.entityList.add(entity);
            this.a(entity);
            return true;
        }
    }

    protected void a(Entity entity) {
        for (int i = 0; i < this.u.size(); ++i) {
            ((IWorldAccess) this.u.get(i)).a(entity);
        }

        entity.valid = true; // CraftBukkit
    }

    protected void b(Entity entity) {
        for (int i = 0; i < this.u.size(); ++i) {
            ((IWorldAccess) this.u.get(i)).b(entity);
        }

        entity.valid = false; // CraftBukkit
    }

    public void kill(Entity entity) {
        if (entity.passenger != null) {
            entity.passenger.mount((Entity) null);
        }

        if (entity.vehicle != null) {
            entity.mount((Entity) null);
        }

        entity.die();
        if (entity instanceof EntityHuman) {
            this.players.remove(entity);
            // Spigot start
            for ( Object o : worldMaps.c )
            {
                if ( o instanceof WorldMap )
                {
                    WorldMap map = (WorldMap) o;
                    map.i.remove( entity );
                    for ( Iterator<WorldMapHumanTracker> iter = (Iterator<WorldMapHumanTracker>) map.g.iterator(); iter.hasNext(); )
                    {
                        if ( iter.next().trackee == entity )
                        {
                            iter.remove();
                        }
                    }
                }
            }
            // Spigot end
            this.everyoneSleeping();
            this.b(entity);
        }

    }

    public void removeEntity(Entity entity) {
        org.spigotmc.AsyncCatcher.catchOp( "entity remove"); // Spigot
        entity.die();
        if (entity instanceof EntityHuman) {
            this.players.remove(entity);
            this.everyoneSleeping();
        }

        if (!guardEntityList) { // Spigot - It will get removed after the tick if we are ticking
        int i = entity.ae;
        int j = entity.ag;

        if (entity.ad && this.isChunkLoaded(i, j, true)) {
            this.getChunkAt(i, j).b(entity);
        }

        // CraftBukkit start - Decrement loop variable field if we've already ticked this entity
        int index = this.entityList.indexOf(entity);
        if (index != -1) {
            if (index <= this.tickPosition) {
                this.tickPosition--;
            }
            this.entityList.remove(index);
        }
        // CraftBukkit end
        } // Spigot
        this.b(entity);
    }

    public void addIWorldAccess(IWorldAccess iworldaccess) {
        this.u.add(iworldaccess);
    }

    public List getCubes(Entity entity, AxisAlignedBB axisalignedbb) {
        ArrayList arraylist = Lists.newArrayList();
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d + 1.0D);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

        // Spigot start
        int ystart = ( ( k - 1 ) < 0 ) ? 0 : ( k - 1 );
        for ( int chunkx = ( i >> 4 ); chunkx <= ( ( j - 1 ) >> 4 ); chunkx++ )
        {
            int cx = chunkx << 4;
            for ( int chunkz = ( i1 >> 4 ); chunkz <= ( ( j1 - 1 ) >> 4 ); chunkz++ )
            {
                if ( !this.isChunkLoaded( chunkx, chunkz, true ) )
                {
                    continue;
                }
                int cz = chunkz << 4;
                Chunk chunk = this.getChunkAt( chunkx, chunkz );
                // Compute ranges within chunk
                int xstart = ( i < cx ) ? cx : i;
                int xend = ( j < ( cx + 16 ) ) ? j : ( cx + 16 );
                int zstart = ( i1 < cz ) ? cz : i1;
                int zend = ( j1 < ( cz + 16 ) ) ? j1 : ( cz + 16 );
                // Loop through blocks within chunk
                for ( int x = xstart; x < xend; x++ )
                {
                    for ( int z = zstart; z < zend; z++ )
                    {
                        for ( int y = ystart; y < l; y++ )
                        {
                            BlockPosition blockposition = new BlockPosition( x, y, z );
                            boolean flag = entity.aS();
                            boolean flag1 = this.a(this.af(), entity);

                            if (flag && flag1) {
                                entity.h(false);
                            } else if (!flag && !flag1) {
                                entity.h(true);
                            }

                            IBlockData iblockdata;

                            IBlockData block; 
                            if (!this.af().a(blockposition) && flag1) {
                                block = Blocks.STONE.getBlockData();
                            } else 
                            {
                                block = chunk.getBlockData( blockposition );
                            }
                            if ( block != null )
                            {
                                block.getBlock().a(this, blockposition, block, axisalignedbb, arraylist, entity);
                            }
                        }
                    }
                }
            }
        }
        // Spigot end

        double d0 = 0.25D;
        List list = this.getEntities(entity, axisalignedbb.grow(d0, d0, d0));

        for (int j2 = 0; j2 < list.size(); ++j2) {
            if (entity.passenger != list && entity.vehicle != list) {
                AxisAlignedBB axisalignedbb1 = ((Entity) list.get(j2)).S();

                if (axisalignedbb1 != null && axisalignedbb1.b(axisalignedbb)) {
                    arraylist.add(axisalignedbb1);
                }

                axisalignedbb1 = entity.j((Entity) list.get(j2));
                if (axisalignedbb1 != null && axisalignedbb1.b(axisalignedbb)) {
                    arraylist.add(axisalignedbb1);
                }
            }
        }

        return arraylist;
    }

    public boolean a(WorldBorder worldborder, Entity entity) {
        double d0 = worldborder.b();
        double d1 = worldborder.c();
        double d2 = worldborder.d();
        double d3 = worldborder.e();

        if (entity.aS()) {
            ++d0;
            ++d1;
            --d2;
            --d3;
        } else {
            --d0;
            --d1;
            ++d2;
            ++d3;
        }

        return entity.locX > d0 && entity.locX < d2 && entity.locZ > d1 && entity.locZ < d3;
    }

    public List a(AxisAlignedBB axisalignedbb) {
        ArrayList arraylist = Lists.newArrayList();
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d + 1.0D);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = i1; l1 < j1; ++l1) {
                if (this.isLoaded(new BlockPosition(k1, 64, l1))) {
                    for (int i2 = k - 1; i2 < l; ++i2) {
                        BlockPosition blockposition = new BlockPosition(k1, i2, l1);
                        IBlockData iblockdata;

                        if (k1 >= -30000000 && k1 < 30000000 && l1 >= -30000000 && l1 < 30000000) {
                            iblockdata = this.getType(blockposition);
                        } else {
                            iblockdata = Blocks.BEDROCK.getBlockData();
                        }

                        iblockdata.getBlock().a(this, blockposition, iblockdata, axisalignedbb, arraylist, (Entity) null);
                    }
                }
            }
        }

        return arraylist;
    }

    public int a(float f) {
        float f1 = this.c(f);
        float f2 = 1.0F - (MathHelper.cos(f1 * 3.1415927F * 2.0F) * 2.0F + 0.5F);

        f2 = MathHelper.a(f2, 0.0F, 1.0F);
        f2 = 1.0F - f2;
        f2 = (float) ((double) f2 * (1.0D - (double) (this.j(f) * 5.0F) / 16.0D));
        f2 = (float) ((double) f2 * (1.0D - (double) (this.h(f) * 5.0F) / 16.0D));
        f2 = 1.0F - f2;
        return (int) (f2 * 11.0F);
    }

    public float c(float f) {
        return this.worldProvider.a(this.worldData.getDayTime(), f);
    }

    public float y() {
        return WorldProvider.a[this.worldProvider.a(this.worldData.getDayTime())];
    }

    public float d(float f) {
        float f1 = this.c(f);

        return f1 * 3.1415927F * 2.0F;
    }

    public BlockPosition q(BlockPosition blockposition) {
        return this.getChunkAtWorldCoords(blockposition).h(blockposition);
    }

    public BlockPosition r(BlockPosition blockposition) {
        Chunk chunk = this.getChunkAtWorldCoords(blockposition);

        BlockPosition blockposition1;
        BlockPosition blockposition2;

        for (blockposition1 = new BlockPosition(blockposition.getX(), chunk.g() + 16, blockposition.getZ()); blockposition1.getY() >= 0; blockposition1 = blockposition2) {
            blockposition2 = blockposition1.down();
            Material material = chunk.getType(blockposition2).getMaterial();

            if (material.isSolid() && material != Material.LEAVES) {
                break;
            }
        }

        return blockposition1;
    }

    public void a(BlockPosition blockposition, Block block, int i) {}

    public void a(BlockPosition blockposition, Block block, int i, int j) {}

    public void b(BlockPosition blockposition, Block block, int i, int j) {}

    public void tickEntities() {
        this.methodProfiler.a("entities");
        this.methodProfiler.a("global");

        int i;
        Entity entity;
        CrashReport crashreport;
        CrashReportSystemDetails crashreportsystemdetails;

        for (i = 0; i < this.k.size(); ++i) {
            entity = (Entity) this.k.get(i);
            // CraftBukkit start - Fixed an NPE
            if (entity == null) {
                continue;
            }
            // CraftBukkit end

            try {
                ++entity.ticksLived;
                entity.s_();
            } catch (Throwable throwable) {
                crashreport = CrashReport.a(throwable, "Ticking entity");
                crashreportsystemdetails = crashreport.a("Entity being ticked");
                if (entity == null) {
                    crashreportsystemdetails.a("Entity", (Object) "~~NULL~~");
                } else {
                    entity.appendEntityCrashDetails(crashreportsystemdetails);
                }

                throw new ReportedException(crashreport);
            }

            if (entity.dead) {
                this.k.remove(i--);
            }
        }

        this.methodProfiler.c("remove");
        this.entityList.removeAll(this.g);

        int j;
        int k;

        for (i = 0; i < this.g.size(); ++i) {
            entity = (Entity) this.g.get(i);
            j = entity.ae;
            k = entity.ag;
            if (entity.ad && this.isChunkLoaded(j, k, true)) {
                this.getChunkAt(j, k).b(entity);
            }
        }

        for (i = 0; i < this.g.size(); ++i) {
            this.b((Entity) this.g.get(i));
        }

        this.g.clear();
        this.methodProfiler.c("regular");

        org.spigotmc.ActivationRange.activateEntities(this); // Spigot
        timings.entityTick.startTiming(); // Spigot
        guardEntityList = true; // Spigot
        // CraftBukkit start - Use field for loop variable
        for (this.tickPosition = 0; this.tickPosition < this.entityList.size(); ++this.tickPosition) {
            entity = (Entity) this.entityList.get(this.tickPosition);
            // CraftBukkit end
            if (entity.vehicle != null) {
                if (!entity.vehicle.dead && entity.vehicle.passenger == entity) {
                    continue;
                }

                entity.vehicle.passenger = null;
                entity.vehicle = null;
            }

            this.methodProfiler.a("tick");
            if (!entity.dead) {
                try {
                    SpigotTimings.tickEntityTimer.startTiming(); // Spigot
                    this.g(entity);
                    SpigotTimings.tickEntityTimer.stopTiming(); // Spigot
                } catch (Throwable throwable1) {
                    crashreport = CrashReport.a(throwable1, "Ticking entity");
                    crashreportsystemdetails = crashreport.a("Entity being ticked");
                    entity.appendEntityCrashDetails(crashreportsystemdetails);
                    throw new ReportedException(crashreport);
                }
            }

            this.methodProfiler.b();
            this.methodProfiler.a("remove");
            if (entity.dead) {
                j = entity.ae;
                k = entity.ag;
                if (entity.ad && this.isChunkLoaded(j, k, true)) {
                    this.getChunkAt(j, k).b(entity);
                }

                guardEntityList = false; // Spigot
                this.entityList.remove(this.tickPosition--); // CraftBukkit - Use field for loop variable
                guardEntityList = true; // Spigot
                this.b(entity);
            }

            this.methodProfiler.b();
        }
        guardEntityList = false; // Spigot

        timings.entityTick.stopTiming(); // Spigot
        this.methodProfiler.c("blockEntities");
        timings.tileEntityTick.startTiming(); // Spigot
        this.L = true;
        // CraftBukkit start - From below, clean up tile entities before ticking them
        if (!this.b.isEmpty()) {
            this.tileEntityList.removeAll(this.b);
            this.h.removeAll(this.b);
            this.b.clear();
        }
        // CraftBukkit end

        this.initializeHoppers(); // Spigot - Initializes hoppers which have been added recently.
        Iterator iterator = this.tileEntityList.iterator();

        while (iterator.hasNext()) {
            TileEntity tileentity = (TileEntity) iterator.next();
            // Spigot start
            if (tileentity == null) {
                getServer().getLogger().severe("Spigot has detected a null entity and has removed it, preventing a crash");
                iterator.remove();
                continue;
            }
            // Spigot end

            if (!tileentity.x() && tileentity.t()) {
                BlockPosition blockposition = tileentity.getPosition();

                if (this.isLoaded(blockposition) && this.M.a(blockposition)) {
                    try {
                        tileentity.tickTimer.startTiming(); // Spigot
                        ((IUpdatePlayerListBox) tileentity).c();
                    } catch (Throwable throwable2) {
                        CrashReport crashreport1 = CrashReport.a(throwable2, "Ticking block entity");
                        CrashReportSystemDetails crashreportsystemdetails1 = crashreport1.a("Block entity being ticked");

                        tileentity.a(crashreportsystemdetails1);
                        throw new ReportedException(crashreport1);
                    }
                    // Spigot start
                    finally {
                        tileentity.tickTimer.stopTiming();
                    }
                    // Spigot end
                }
            }

            if (tileentity.x()) {
                iterator.remove();
                this.h.remove(tileentity);
                if (this.isLoaded(tileentity.getPosition())) {
                    this.getChunkAtWorldCoords(tileentity.getPosition()).e(tileentity.getPosition());
                }
            }
        }

        timings.tileEntityTick.stopTiming(); // Spigot
        timings.tileEntityPending.startTiming(); // Spigot
        this.L = false;
        /* CraftBukkit start - Moved up
        if (!this.b.isEmpty()) {
            this.tileEntityList.removeAll(this.b);
            this.h.removeAll(this.b);
            this.b.clear();
        }
        */ // CraftBukkit end

        this.methodProfiler.c("pendingBlockEntities");
        if (!this.a.isEmpty()) {
            for (int l = 0; l < this.a.size(); ++l) {
                TileEntity tileentity1 = (TileEntity) this.a.get(l);

                if (!tileentity1.x()) {
                    /* CraftBukkit start - Order matters, moved down
                    if (!this.h.contains(tileentity1)) {
                        this.a(tileentity1);
                    }
                    // CraftBukkit end */

                    if (this.isLoaded(tileentity1.getPosition())) {
                        this.getChunkAtWorldCoords(tileentity1.getPosition()).a(tileentity1.getPosition(), tileentity1);
                    }

                    this.notify(tileentity1.getPosition());
                }
            }

            this.a.clear();
        }

        timings.tileEntityPending.stopTiming(); // Spigot
        this.methodProfiler.b();
        this.methodProfiler.b();
    }

    public boolean a(TileEntity tileentity) {
        boolean flag = this.h.add(tileentity);

        if (flag && tileentity instanceof IUpdatePlayerListBox) {
            this.tileEntityList.add(tileentity);
        }

        return flag;
    }

    public void a(Collection collection) {
        if (this.L) {
            this.a.addAll(collection);
        } else {
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
                TileEntity tileentity = (TileEntity) iterator.next();

                this.h.add(tileentity);
                if (tileentity instanceof IUpdatePlayerListBox) {
                    this.tileEntityList.add(tileentity);
                }
            }
        }

    }

    public void g(Entity entity) {
        this.entityJoinedWorld(entity, true);
    }

    public void entityJoinedWorld(Entity entity, boolean flag) {
        int i = MathHelper.floor(entity.locX);
        int j = MathHelper.floor(entity.locZ);
        byte b0 = 32;

        // Spigot start
        if (!org.spigotmc.ActivationRange.checkIfActive(entity)) {
            entity.ticksLived++;
            entity.inactiveTick();
        } else {
            entity.tickTimer.startTiming(); // Spigot
            // CraftBukkit end
            entity.P = entity.locX;
            entity.Q = entity.locY;
            entity.R = entity.locZ;
            entity.lastYaw = entity.yaw;
            entity.lastPitch = entity.pitch;
            if (flag && entity.ad) {
                ++entity.ticksLived;
                if (entity.vehicle != null) {
                    entity.ak();
                } else {
                    entity.s_();
                }
            }

            this.methodProfiler.a("chunkCheck");
            if (Double.isNaN(entity.locX) || Double.isInfinite(entity.locX)) {
                entity.locX = entity.P;
            }

            if (Double.isNaN(entity.locY) || Double.isInfinite(entity.locY)) {
                entity.locY = entity.Q;
            }

            if (Double.isNaN(entity.locZ) || Double.isInfinite(entity.locZ)) {
                entity.locZ = entity.R;
            }

            if (Double.isNaN((double) entity.pitch) || Double.isInfinite((double) entity.pitch)) {
                entity.pitch = entity.lastPitch;
            }

            if (Double.isNaN((double) entity.yaw) || Double.isInfinite((double) entity.yaw)) {
                entity.yaw = entity.lastYaw;
            }

            int k = MathHelper.floor(entity.locX / 16.0D);
            int l = MathHelper.floor(entity.locY / 16.0D);
            int i1 = MathHelper.floor(entity.locZ / 16.0D);

            if (!entity.ad || entity.ae != k || entity.af != l || entity.ag != i1) {
                if (entity.ad && this.isChunkLoaded(entity.ae, entity.ag, true)) {
                    this.getChunkAt(entity.ae, entity.ag).a(entity, entity.af);
                }

                if (this.isChunkLoaded(k, i1, true)) {
                    entity.ad = true;
                    this.getChunkAt(k, i1).a(entity);
                } else {
                    entity.ad = false;
                }
            }

            this.methodProfiler.b();
            if (flag && entity.ad && entity.passenger != null) {
                if (!entity.passenger.dead && entity.passenger.vehicle == entity) {
                    this.g(entity.passenger);
                } else {
                    entity.passenger.vehicle = null;
                    entity.passenger = null;
                }
            }

            entity.tickTimer.stopTiming(); // Spigot
        }
    }

    public boolean b(AxisAlignedBB axisalignedbb) {
        return this.a(axisalignedbb, (Entity) null);
    }

    public boolean a(AxisAlignedBB axisalignedbb, Entity entity) {
        List list = this.getEntities((Entity) null, axisalignedbb);

        for (int i = 0; i < list.size(); ++i) {
            Entity entity1 = (Entity) list.get(i);

            if (!entity1.dead && entity1.k && entity1 != entity && (entity == null || entity.vehicle != entity1 && entity.passenger != entity1)) {
                return false;
            }
        }

        return true;
    }

    public boolean c(AxisAlignedBB axisalignedbb) {
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f);

        for (int k1 = i; k1 <= j; ++k1) {
            for (int l1 = k; l1 <= l; ++l1) {
                for (int i2 = i1; i2 <= j1; ++i2) {
                    Block block = this.getType(new BlockPosition(k1, l1, i2)).getBlock();

                    if (block.getMaterial() != Material.AIR) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean containsLiquid(AxisAlignedBB axisalignedbb) {
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f);

        for (int k1 = i; k1 <= j; ++k1) {
            for (int l1 = k; l1 <= l; ++l1) {
                for (int i2 = i1; i2 <= j1; ++i2) {
                    Block block = this.getType(new BlockPosition(k1, l1, i2)).getBlock();

                    if (block.getMaterial().isLiquid()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean e(AxisAlignedBB axisalignedbb) {
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d + 1.0D);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

        if (this.isAreaLoaded(i, k, i1, j, l, j1, true)) {
            for (int k1 = i; k1 < j; ++k1) {
                for (int l1 = k; l1 < l; ++l1) {
                    for (int i2 = i1; i2 < j1; ++i2) {
                        Block block = this.getType(new BlockPosition(k1, l1, i2)).getBlock();

                        if (block == Blocks.FIRE || block == Blocks.FLOWING_LAVA || block == Blocks.LAVA) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean a(AxisAlignedBB axisalignedbb, Material material, Entity entity) {
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d + 1.0D);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

        if (!this.isAreaLoaded(i, k, i1, j, l, j1, true)) {
            return false;
        } else {
            boolean flag = false;
            Vec3D vec3d = new Vec3D(0.0D, 0.0D, 0.0D);

            for (int k1 = i; k1 < j; ++k1) {
                for (int l1 = k; l1 < l; ++l1) {
                    for (int i2 = i1; i2 < j1; ++i2) {
                        BlockPosition blockposition = new BlockPosition(k1, l1, i2);
                        IBlockData iblockdata = this.getType(blockposition);
                        Block block = iblockdata.getBlock();

                        if (block.getMaterial() == material) {
                            double d0 = (double) ((float) (l1 + 1) - BlockFluids.b(((Integer) iblockdata.get(BlockFluids.LEVEL)).intValue()));

                            if ((double) l >= d0) {
                                flag = true;
                                vec3d = block.a(this, blockposition, entity, vec3d);
                            }
                        }
                    }
                }
            }

            if (vec3d.b() > 0.0D && entity.aK()) {
                vec3d = vec3d.a();
                double d1 = 0.014D;

                entity.motX += vec3d.a * d1;
                entity.motY += vec3d.b * d1;
                entity.motZ += vec3d.c * d1;
            }

            return flag;
        }
    }

    public boolean a(AxisAlignedBB axisalignedbb, Material material) {
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d + 1.0D);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    if (this.getType(new BlockPosition(k1, l1, i2)).getBlock().getMaterial() == material) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean b(AxisAlignedBB axisalignedbb, Material material) {
        int i = MathHelper.floor(axisalignedbb.a);
        int j = MathHelper.floor(axisalignedbb.d + 1.0D);
        int k = MathHelper.floor(axisalignedbb.b);
        int l = MathHelper.floor(axisalignedbb.e + 1.0D);
        int i1 = MathHelper.floor(axisalignedbb.c);
        int j1 = MathHelper.floor(axisalignedbb.f + 1.0D);

        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    BlockPosition blockposition = new BlockPosition(k1, l1, i2);
                    IBlockData iblockdata = this.getType(blockposition);
                    Block block = iblockdata.getBlock();

                    if (block.getMaterial() == material) {
                        int j2 = ((Integer) iblockdata.get(BlockFluids.LEVEL)).intValue();
                        double d0 = (double) (l1 + 1);

                        if (j2 < 8) {
                            d0 = (double) (l1 + 1) - (double) j2 / 8.0D;
                        }

                        if (d0 >= axisalignedbb.b) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public Explosion explode(Entity entity, double d0, double d1, double d2, float f, boolean flag) {
        return this.createExplosion(entity, d0, d1, d2, f, false, flag);
    }

    public Explosion createExplosion(Entity entity, double d0, double d1, double d2, float f, boolean flag, boolean flag1) {
        Explosion explosion = new Explosion(this, entity, d0, d1, d2, f, flag, flag1);

        explosion.a();
        explosion.a(true);
        return explosion;
    }

    public float a(Vec3D vec3d, AxisAlignedBB axisalignedbb) {
        double d0 = 1.0D / ((axisalignedbb.d - axisalignedbb.a) * 2.0D + 1.0D);
        double d1 = 1.0D / ((axisalignedbb.e - axisalignedbb.b) * 2.0D + 1.0D);
        double d2 = 1.0D / ((axisalignedbb.f - axisalignedbb.c) * 2.0D + 1.0D);

        if (d0 >= 0.0D && d1 >= 0.0D && d2 >= 0.0D) {
            int i = 0;
            int j = 0;

            for (float f = 0.0F; f <= 1.0F; f = (float) ((double) f + d0)) {
                for (float f1 = 0.0F; f1 <= 1.0F; f1 = (float) ((double) f1 + d1)) {
                    for (float f2 = 0.0F; f2 <= 1.0F; f2 = (float) ((double) f2 + d2)) {
                        double d3 = axisalignedbb.a + (axisalignedbb.d - axisalignedbb.a) * (double) f;
                        double d4 = axisalignedbb.b + (axisalignedbb.e - axisalignedbb.b) * (double) f1;
                        double d5 = axisalignedbb.c + (axisalignedbb.f - axisalignedbb.c) * (double) f2;

                        if (this.rayTrace(new Vec3D(d3, d4, d5), vec3d) == null) {
                            ++i;
                        }

                        ++j;
                    }
                }
            }

            return (float) i / (float) j;
        } else {
            return 0.0F;
        }
    }

    public boolean douseFire(EntityHuman entityhuman, BlockPosition blockposition, EnumDirection enumdirection) {
        blockposition = blockposition.shift(enumdirection);
        if (this.getType(blockposition).getBlock() == Blocks.FIRE) {
            this.a(entityhuman, 1004, blockposition, 0);
            this.setAir(blockposition);
            return true;
        } else {
            return false;
        }
    }

    public TileEntity getTileEntity(BlockPosition blockposition) {
        if (!this.isValidLocation(blockposition)) {
            return null;
        } else {
            TileEntity tileentity = null;
            int i;
            TileEntity tileentity1;

            if (this.L) {
                for (i = 0; i < this.a.size(); ++i) {
                    tileentity1 = (TileEntity) this.a.get(i);
                    if (!tileentity1.x() && tileentity1.getPosition().equals(blockposition)) {
                        tileentity = tileentity1;
                        break;
                    }
                }
            }

            if (tileentity == null) {
                tileentity = this.getChunkAtWorldCoords(blockposition).a(blockposition, EnumTileEntityState.IMMEDIATE);
            }

            if (tileentity == null) {
                for (i = 0; i < this.a.size(); ++i) {
                    tileentity1 = (TileEntity) this.a.get(i);
                    if (!tileentity1.x() && tileentity1.getPosition().equals(blockposition)) {
                        tileentity = tileentity1;
                        break;
                    }
                }
            }

            return tileentity;
        }
    }

    public void setTileEntity(BlockPosition blockposition, TileEntity tileentity) {
        if (tileentity != null && !tileentity.x()) {
            if (this.L) {
                tileentity.a(blockposition);
                Iterator iterator = this.a.iterator();

                while (iterator.hasNext()) {
                    TileEntity tileentity1 = (TileEntity) iterator.next();

                    if (tileentity1.getPosition().equals(blockposition)) {
                        tileentity1.y();
                        iterator.remove();
                    }
                }

                tileentity.a(this); // Spigot - No null worlds
                this.a.add(tileentity);
            } else {
                this.a(tileentity);
                this.getChunkAtWorldCoords(blockposition).a(blockposition, tileentity);
            }
        }

    }

    public void t(BlockPosition blockposition) {
        TileEntity tileentity = this.getTileEntity(blockposition);

        if (tileentity != null && this.L) {
            tileentity.y();
            this.a.remove(tileentity);
        } else {
            if (tileentity != null) {
                this.a.remove(tileentity);
                this.h.remove(tileentity);
                this.tileEntityList.remove(tileentity);
            }

            this.getChunkAtWorldCoords(blockposition).e(blockposition);
        }

    }

    public void b(TileEntity tileentity) {
        this.b.add(tileentity);
    }

    public boolean u(BlockPosition blockposition) {
        IBlockData iblockdata = this.getType(blockposition);
        AxisAlignedBB axisalignedbb = iblockdata.getBlock().a(this, blockposition, iblockdata);

        return axisalignedbb != null && axisalignedbb.a() >= 1.0D;
    }

    public static boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
        IBlockData iblockdata = iblockaccess.getType(blockposition);
        Block block = iblockdata.getBlock();

        return block.getMaterial().k() && block.d() ? true : (block instanceof BlockStairs ? iblockdata.get(BlockStairs.HALF) == EnumHalf.TOP : (block instanceof BlockStepAbstract ? iblockdata.get(BlockStepAbstract.HALF) == EnumSlabHalf.TOP : (block instanceof BlockHopper ? true : (block instanceof BlockSnow ? ((Integer) iblockdata.get(BlockSnow.LAYERS)).intValue() == 7 : false))));
    }

    public boolean d(BlockPosition blockposition, boolean flag) {
        if (!this.isValidLocation(blockposition)) {
            return flag;
        } else {
            Chunk chunk = this.chunkProvider.getChunkAt(blockposition);

            if (chunk.isEmpty()) {
                return flag;
            } else {
                Block block = this.getType(blockposition).getBlock();

                return block.getMaterial().k() && block.d();
            }
        }
    }

    public void B() {
        int i = this.a(1.0F);

        if (i != this.d) {
            this.d = i;
        }

    }

    public void setSpawnFlags(boolean flag, boolean flag1) {
        this.allowMonsters = flag;
        this.allowAnimals = flag1;
    }

    public void doTick() {
        this.p();
    }

    protected void C() {
        if (this.worldData.hasStorm()) {
            this.p = 1.0F;
            if (this.worldData.isThundering()) {
                this.r = 1.0F;
            }
        }

    }

    protected void p() {
        if (!this.worldProvider.o()) {
            if (!this.isStatic) {
                int i = this.worldData.A();

                if (i > 0) {
                    --i;
                    this.worldData.i(i);
                    this.worldData.setThunderDuration(this.worldData.isThundering() ? 1 : 2);
                    this.worldData.setWeatherDuration(this.worldData.hasStorm() ? 1 : 2);
                }

                int j = this.worldData.getThunderDuration();

                if (j <= 0) {
                    if (this.worldData.isThundering()) {
                        this.worldData.setThunderDuration(this.random.nextInt(12000) + 3600);
                    } else {
                        this.worldData.setThunderDuration(this.random.nextInt(168000) + 12000);
                    }
                } else {
                    --j;
                    this.worldData.setThunderDuration(j);
                    if (j <= 0) {
                        // CraftBukkit start
                        ThunderChangeEvent thunder = new ThunderChangeEvent(this.getWorld(), !this.worldData.isThundering());
                        this.getServer().getPluginManager().callEvent(thunder);
                        if (!thunder.isCancelled()) {
                            this.worldData.setThundering(!this.worldData.isThundering());
                        }
                        // CraftBukkit end
                    }
                }

                this.q = this.r;
                if (this.worldData.isThundering()) {
                    this.r = (float) ((double) this.r + 0.01D);
                } else {
                    this.r = (float) ((double) this.r - 0.01D);
                }

                this.r = MathHelper.a(this.r, 0.0F, 1.0F);
                int k = this.worldData.getWeatherDuration();

                if (k <= 0) {
                    if (this.worldData.hasStorm()) {
                        this.worldData.setWeatherDuration(this.random.nextInt(12000) + 12000);
                    } else {
                        this.worldData.setWeatherDuration(this.random.nextInt(168000) + 12000);
                    }
                } else {
                    --k;
                    this.worldData.setWeatherDuration(k);
                    if (k <= 0) {
                        // CraftBukkit start
                        WeatherChangeEvent weather = new WeatherChangeEvent(this.getWorld(), !this.worldData.hasStorm());
                        this.getServer().getPluginManager().callEvent(weather);

                        if (!weather.isCancelled()) {
                            this.worldData.setStorm(!this.worldData.hasStorm());
                        }
                        // CraftBukkit end
                    }
                }

                this.o = this.p;
                if (this.worldData.hasStorm()) {
                    this.p = (float) ((double) this.p + 0.01D);
                } else {
                    this.p = (float) ((double) this.p - 0.01D);
                }

                this.p = MathHelper.a(this.p, 0.0F, 1.0F);
            }
        }
    }

    protected void D() {
        // this.chunkTickList.clear(); // CraftBukkit - removed
        this.methodProfiler.a("buildList");

        int i;
        EntityHuman entityhuman;
        int j;
        int k;
        int l;

        // Spigot start
        int optimalChunks = spigotConfig.chunksPerTick;
        // Quick conditions to allow us to exist early
        if ( optimalChunks <= 0 || players.isEmpty() )
        {
            return;
        }
        // Keep chunks with growth inside of the optimal chunk range
        int chunksPerPlayer = Math.min( 200, Math.max( 1, (int) ( ( ( optimalChunks - players.size() ) / (double) players.size() ) + 0.5 ) ) );
        int randRange = 3 + chunksPerPlayer / 30;
        // Limit to normal tick radius - including view distance
        randRange = ( randRange > chunkTickRadius ) ? chunkTickRadius : randRange;
        // odds of growth happening vs growth happening in vanilla
        this.growthOdds = this.modifiedOdds = Math.max( 35, Math.min( 100, ( ( chunksPerPlayer + 1 ) * 100F ) / 15F ) );
        // Spigot end
        for (i = 0; i < this.players.size(); ++i) {
            entityhuman = (EntityHuman) this.players.get(i);
            j = MathHelper.floor(entityhuman.locX / 16.0D);
            k = MathHelper.floor(entityhuman.locZ / 16.0D);
            l = this.q();

            // Spigot start - Always update the chunk the player is on
            long key = chunkToKey( j, k );
            int existingPlayers = Math.max( 0, chunkTickList.get( key ) ); // filter out -1
            chunkTickList.put( key, (short) ( existingPlayers + 1 ) );

            // Check and see if we update the chunks surrounding the player this tick
            for ( int chunk = 0; chunk < chunksPerPlayer; chunk++ )
            {
                int dx = ( random.nextBoolean() ? 1 : -1 ) * random.nextInt( randRange );
                int dz = ( random.nextBoolean() ? 1 : -1 ) * random.nextInt( randRange );
                long hash = chunkToKey( dx + j, dz + k );
                if ( !chunkTickList.contains( hash ) && this.chunkProvider.isChunkLoaded(dx + j, dz + k ) )
                {
                    chunkTickList.put( hash, (short) -1 ); // no players
                }
            }
            // Spigot End
        }

        this.methodProfiler.b();
        if (this.K > 0) {
            --this.K;
        }

        this.methodProfiler.a("playerCheckLight");
        if (spigotConfig.randomLightUpdates && !this.players.isEmpty()) { // Spigot
            i = this.random.nextInt(this.players.size());
            entityhuman = (EntityHuman) this.players.get(i);
            j = MathHelper.floor(entityhuman.locX) + this.random.nextInt(11) - 5;
            k = MathHelper.floor(entityhuman.locY) + this.random.nextInt(11) - 5;
            l = MathHelper.floor(entityhuman.locZ) + this.random.nextInt(11) - 5;
            this.x(new BlockPosition(j, k, l));
        }

        this.methodProfiler.b();
    }

    protected abstract int q();

    protected void a(int i, int j, Chunk chunk) {
        this.methodProfiler.c("moodSound");
        if (this.K == 0 && !this.isStatic) {
            this.m = this.m * 3 + 1013904223;
            int k = this.m >> 2;
            int l = k & 15;
            int i1 = k >> 8 & 15;
            int j1 = k >> 16 & 255;
            BlockPosition blockposition = new BlockPosition(l, j1, i1);
            Block block = chunk.getType(blockposition);

            l += i;
            i1 += j;
            if (block.getMaterial() == Material.AIR && this.k(blockposition) <= this.random.nextInt(8) && this.b(EnumSkyBlock.SKY, blockposition) <= 0) {
                EntityHuman entityhuman = this.findNearbyPlayer((double) l + 0.5D, (double) j1 + 0.5D, (double) i1 + 0.5D, 8.0D);

                if (entityhuman != null && entityhuman.e((double) l + 0.5D, (double) j1 + 0.5D, (double) i1 + 0.5D) > 4.0D) {
                    this.makeSound((double) l + 0.5D, (double) j1 + 0.5D, (double) i1 + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + this.random.nextFloat() * 0.2F);
                    this.K = this.random.nextInt(12000) + 6000;
                }
            }
        }

        this.methodProfiler.c("checkLight");
        chunk.m();
    }

    protected void h() {
        this.D();
    }

    public void a(Block block, BlockPosition blockposition, Random random) {
        this.e = true;
        block.b(this, blockposition, this.getType(blockposition), random);
        this.e = false;
    }

    public boolean v(BlockPosition blockposition) {
        return this.e(blockposition, false);
    }

    public boolean w(BlockPosition blockposition) {
        return this.e(blockposition, true);
    }

    public boolean e(BlockPosition blockposition, boolean flag) {
        BiomeBase biomebase = this.getBiome(blockposition);
        float f = biomebase.a(blockposition);

        if (f > 0.15F) {
            return false;
        } else {
            if (blockposition.getY() >= 0 && blockposition.getY() < 256 && this.b(EnumSkyBlock.BLOCK, blockposition) < 10) {
                IBlockData iblockdata = this.getType(blockposition);
                Block block = iblockdata.getBlock();

                if ((block == Blocks.WATER || block == Blocks.FLOWING_WATER) && ((Integer) iblockdata.get(BlockFluids.LEVEL)).intValue() == 0) {
                    if (!flag) {
                        return true;
                    }

                    boolean flag1 = this.F(blockposition.west()) && this.F(blockposition.east()) && this.F(blockposition.north()) && this.F(blockposition.south());

                    if (!flag1) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    private boolean F(BlockPosition blockposition) {
        return this.getType(blockposition).getBlock().getMaterial() == Material.WATER;
    }

    public boolean f(BlockPosition blockposition, boolean flag) {
        BiomeBase biomebase = this.getBiome(blockposition);
        float f = biomebase.a(blockposition);

        if (f > 0.15F) {
            return false;
        } else if (!flag) {
            return true;
        } else {
            if (blockposition.getY() >= 0 && blockposition.getY() < 256 && this.b(EnumSkyBlock.BLOCK, blockposition) < 10) {
                Block block = this.getType(blockposition).getBlock();

                if (block.getMaterial() == Material.AIR && Blocks.SNOW_LAYER.canPlace(this, blockposition)) {
                    return true;
                }
            }

            return false;
        }
    }

    public boolean x(BlockPosition blockposition) {
        boolean flag = false;

        if (!this.worldProvider.o()) {
            flag |= this.c(EnumSkyBlock.SKY, blockposition);
        }

        flag |= this.c(EnumSkyBlock.BLOCK, blockposition);
        return flag;
    }

    private int a(BlockPosition blockposition, EnumSkyBlock enumskyblock) {
        if (enumskyblock == EnumSkyBlock.SKY && this.i(blockposition)) {
            return 15;
        } else {
            Block block = this.getType(blockposition).getBlock();
            int i = enumskyblock == EnumSkyBlock.SKY ? 0 : block.p();
            int j = block.n();

            if (j >= 15 && block.p() > 0) {
                j = 1;
            }

            if (j < 1) {
                j = 1;
            }

            if (j >= 15) {
                return 0;
            } else if (i >= 14) {
                return i;
            } else {
                EnumDirection[] aenumdirection = EnumDirection.values();
                int k = aenumdirection.length;

                for (int l = 0; l < k; ++l) {
                    EnumDirection enumdirection = aenumdirection[l];
                    BlockPosition blockposition1 = blockposition.shift(enumdirection);
                    int i1 = this.b(enumskyblock, blockposition1) - j;

                    if (i1 > i) {
                        i = i1;
                    }

                    if (i >= 14) {
                        return i;
                    }
                }

                return i;
            }
        }
    }

    public boolean c(EnumSkyBlock enumskyblock, BlockPosition blockposition) {
        // CraftBukkit start - Use neighbor cache instead of looking up
        Chunk chunk = this.getChunkIfLoaded(blockposition.getX() >> 4, blockposition.getZ() >> 4);
        if (chunk == null || !chunk.areNeighborsLoaded(1) /*!this.areChunksLoaded(blockposition, 17, false)*/) {
            // CraftBukkit end
            return false;
        } else {
            int i = 0;
            int j = 0;

            this.methodProfiler.a("getBrightness");
            int k = this.b(enumskyblock, blockposition);
            int l = this.a(blockposition, enumskyblock);
            int i1 = blockposition.getX();
            int j1 = blockposition.getY();
            int k1 = blockposition.getZ();
            int l1;
            int i2;
            int j2;
            int k2;
            int l2;
            int i3;
            int j3;
            int k3;

            if (l > k) {
                this.H[j++] = 133152;
            } else if (l < k) {
                this.H[j++] = 133152 | k << 18;

                while (i < j) {
                    l1 = this.H[i++];
                    i2 = (l1 & 63) - 32 + i1;
                    j2 = (l1 >> 6 & 63) - 32 + j1;
                    k2 = (l1 >> 12 & 63) - 32 + k1;
                    int l3 = l1 >> 18 & 15;
                    BlockPosition blockposition1 = new BlockPosition(i2, j2, k2);

                    l2 = this.b(enumskyblock, blockposition1);
                    if (l2 == l3) {
                        this.a(enumskyblock, blockposition1, 0);
                        if (l3 > 0) {
                            i3 = MathHelper.a(i2 - i1);
                            j3 = MathHelper.a(j2 - j1);
                            k3 = MathHelper.a(k2 - k1);
                            if (i3 + j3 + k3 < 17) {
                                EnumDirection[] aenumdirection = EnumDirection.values();
                                int i4 = aenumdirection.length;

                                for (int j4 = 0; j4 < i4; ++j4) {
                                    EnumDirection enumdirection = aenumdirection[j4];
                                    int k4 = i2 + enumdirection.getAdjacentX();
                                    int l4 = j2 + enumdirection.getAdjacentY();
                                    int i5 = k2 + enumdirection.getAdjacentZ();
                                    BlockPosition blockposition2 = new BlockPosition(k4, l4, i5);
                                    int j5 = Math.max(1, this.getType(blockposition2).getBlock().n());

                                    l2 = this.b(enumskyblock, blockposition2);
                                    if (l2 == l3 - j5 && j < this.H.length) {
                                        this.H[j++] = k4 - i1 + 32 | l4 - j1 + 32 << 6 | i5 - k1 + 32 << 12 | l3 - j5 << 18;
                                    }
                                }
                            }
                        }
                    }
                }

                i = 0;
            }

            this.methodProfiler.b();
            this.methodProfiler.a("checkedPosition < toCheckCount");

            while (i < j) {
                l1 = this.H[i++];
                i2 = (l1 & 63) - 32 + i1;
                j2 = (l1 >> 6 & 63) - 32 + j1;
                k2 = (l1 >> 12 & 63) - 32 + k1;
                BlockPosition blockposition3 = new BlockPosition(i2, j2, k2);
                int k5 = this.b(enumskyblock, blockposition3);

                l2 = this.a(blockposition3, enumskyblock);
                if (l2 != k5) {
                    this.a(enumskyblock, blockposition3, l2);
                    if (l2 > k5) {
                        i3 = Math.abs(i2 - i1);
                        j3 = Math.abs(j2 - j1);
                        k3 = Math.abs(k2 - k1);
                        boolean flag = j < this.H.length - 6;

                        if (i3 + j3 + k3 < 17 && flag) {
                            if (this.b(enumskyblock, blockposition3.west()) < l2) {
                                this.H[j++] = i2 - 1 - i1 + 32 + (j2 - j1 + 32 << 6) + (k2 - k1 + 32 << 12);
                            }

                            if (this.b(enumskyblock, blockposition3.east()) < l2) {
                                this.H[j++] = i2 + 1 - i1 + 32 + (j2 - j1 + 32 << 6) + (k2 - k1 + 32 << 12);
                            }

                            if (this.b(enumskyblock, blockposition3.down()) < l2) {
                                this.H[j++] = i2 - i1 + 32 + (j2 - 1 - j1 + 32 << 6) + (k2 - k1 + 32 << 12);
                            }

                            if (this.b(enumskyblock, blockposition3.up()) < l2) {
                                this.H[j++] = i2 - i1 + 32 + (j2 + 1 - j1 + 32 << 6) + (k2 - k1 + 32 << 12);
                            }

                            if (this.b(enumskyblock, blockposition3.north()) < l2) {
                                this.H[j++] = i2 - i1 + 32 + (j2 - j1 + 32 << 6) + (k2 - 1 - k1 + 32 << 12);
                            }

                            if (this.b(enumskyblock, blockposition3.south()) < l2) {
                                this.H[j++] = i2 - i1 + 32 + (j2 - j1 + 32 << 6) + (k2 + 1 - k1 + 32 << 12);
                            }
                        }
                    }
                }
            }

            this.methodProfiler.b();
            return true;
        }
    }

    public boolean a(boolean flag) {
        return false;
    }

    public List a(Chunk chunk, boolean flag) {
        return null;
    }

    public List a(StructureBoundingBox structureboundingbox, boolean flag) {
        return null;
    }

    public List getEntities(Entity entity, AxisAlignedBB axisalignedbb) {
        return this.a(entity, axisalignedbb, IEntitySelector.d);
    }

    public List a(Entity entity, AxisAlignedBB axisalignedbb, Predicate predicate) {
        ArrayList arraylist = Lists.newArrayList();
        int i = MathHelper.floor((axisalignedbb.a - 2.0D) / 16.0D);
        int j = MathHelper.floor((axisalignedbb.d + 2.0D) / 16.0D);
        int k = MathHelper.floor((axisalignedbb.c - 2.0D) / 16.0D);
        int l = MathHelper.floor((axisalignedbb.f + 2.0D) / 16.0D);

        for (int i1 = i; i1 <= j; ++i1) {
            for (int j1 = k; j1 <= l; ++j1) {
                if (this.isChunkLoaded(i1, j1, true)) {
                    this.getChunkAt(i1, j1).a(entity, axisalignedbb, arraylist, predicate);
                }
            }
        }

        return arraylist;
    }

    public List a(Class oclass, Predicate predicate) {
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = this.entityList.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (oclass.isAssignableFrom(entity.getClass()) && predicate.apply(entity)) {
                arraylist.add(entity);
            }
        }

        return arraylist;
    }

    public List b(Class oclass, Predicate predicate) {
        ArrayList arraylist = Lists.newArrayList();
        Iterator iterator = this.players.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();

            if (oclass.isAssignableFrom(entity.getClass()) && predicate.apply(entity)) {
                arraylist.add(entity);
            }
        }

        return arraylist;
    }

    public List a(Class oclass, AxisAlignedBB axisalignedbb) {
        return this.a(oclass, axisalignedbb, IEntitySelector.d);
    }

    public List a(Class oclass, AxisAlignedBB axisalignedbb, Predicate predicate) {
        int i = MathHelper.floor((axisalignedbb.a - 2.0D) / 16.0D);
        int j = MathHelper.floor((axisalignedbb.d + 2.0D) / 16.0D);
        int k = MathHelper.floor((axisalignedbb.c - 2.0D) / 16.0D);
        int l = MathHelper.floor((axisalignedbb.f + 2.0D) / 16.0D);
        ArrayList arraylist = Lists.newArrayList();

        for (int i1 = i; i1 <= j; ++i1) {
            for (int j1 = k; j1 <= l; ++j1) {
                if (this.isChunkLoaded(i1, j1, true)) {
                    this.getChunkAt(i1, j1).a(oclass, axisalignedbb, arraylist, predicate);
                }
            }
        }

        return arraylist;
    }

    public Entity a(Class oclass, AxisAlignedBB axisalignedbb, Entity entity) {
        List list = this.a(oclass, axisalignedbb);
        Entity entity1 = null;
        double d0 = Double.MAX_VALUE;

        for (int i = 0; i < list.size(); ++i) {
            Entity entity2 = (Entity) list.get(i);

            if (entity2 != entity && IEntitySelector.d.apply(entity2)) {
                double d1 = entity.h(entity2);

                if (d1 <= d0) {
                    entity1 = entity2;
                    d0 = d1;
                }
            }
        }

        return entity1;
    }

    public Entity a(int i) {
        return (Entity) this.entitiesById.get(i);
    }

    public void b(BlockPosition blockposition, TileEntity tileentity) {
        if (this.isLoaded(blockposition)) {
            this.getChunkAtWorldCoords(blockposition).e();
        }

    }

    public int a(Class oclass) {
        int i = 0;
        Iterator iterator = this.entityList.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();
            // CraftBukkit start - Split out persistent check, don't apply it to special persistent mobs
            if (entity instanceof EntityInsentient) {
                EntityInsentient entityinsentient = (EntityInsentient) entity;
                if (entityinsentient.isTypeNotPersistent() && entityinsentient.isPersistent()) {
                    continue;
                }
            }

            if (oclass.isAssignableFrom(entity.getClass())) {
            // if ((!(entity instanceof EntityInsentient) || !((EntityInsentient) entity).isPersistent()) && oclass.isAssignableFrom(entity.getClass())) {
                // CraftBukkit end
                ++i;
            }
        }

        return i;
    }

    public void b(Collection collection) {
        org.spigotmc.AsyncCatcher.catchOp( "entity world add"); // Spigot
        // CraftBukkit start
        // this.entityList.addAll(collection);
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();
            if (entity == null) {
                continue;
            }
            this.entityList.add(entity);
            // CraftBukkit end
            this.a(entity);
        }

    }

    public void c(Collection collection) {
        this.g.addAll(collection);
    }

    public boolean a(Block block, BlockPosition blockposition, boolean flag, EnumDirection enumdirection, Entity entity, ItemStack itemstack) {
        Block block1 = this.getType(blockposition).getBlock();
        AxisAlignedBB axisalignedbb = flag ? null : block.a(this, blockposition, block.getBlockData());

        // CraftBukkit start - store default return
        boolean defaultReturn = axisalignedbb != null && !this.a(axisalignedbb, entity) ? false : (block1.getMaterial() == Material.ORIENTABLE && block == Blocks.ANVIL ? true : block1.getMaterial().isReplaceable() && block.canPlace(this, blockposition, enumdirection, itemstack));
        BlockCanBuildEvent event = new BlockCanBuildEvent(this.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), CraftMagicNumbers.getId(block), defaultReturn);
        this.getServer().getPluginManager().callEvent(event);

        return event.isBuildable();
        // CraftBukkit end
    }

    public int getBlockPower(BlockPosition blockposition, EnumDirection enumdirection) {
        IBlockData iblockdata = this.getType(blockposition);

        return iblockdata.getBlock().b((IBlockAccess) this, blockposition, iblockdata, enumdirection);
    }

    public WorldType G() {
        return this.worldData.getType();
    }

    public int getBlockPower(BlockPosition blockposition) {
        byte b0 = 0;
        int i = Math.max(b0, this.getBlockPower(blockposition.down(), EnumDirection.DOWN));

        if (i >= 15) {
            return i;
        } else {
            i = Math.max(i, this.getBlockPower(blockposition.up(), EnumDirection.UP));
            if (i >= 15) {
                return i;
            } else {
                i = Math.max(i, this.getBlockPower(blockposition.north(), EnumDirection.NORTH));
                if (i >= 15) {
                    return i;
                } else {
                    i = Math.max(i, this.getBlockPower(blockposition.south(), EnumDirection.SOUTH));
                    if (i >= 15) {
                        return i;
                    } else {
                        i = Math.max(i, this.getBlockPower(blockposition.west(), EnumDirection.WEST));
                        if (i >= 15) {
                            return i;
                        } else {
                            i = Math.max(i, this.getBlockPower(blockposition.east(), EnumDirection.EAST));
                            return i >= 15 ? i : i;
                        }
                    }
                }
            }
        }
    }

    public boolean isBlockFacePowered(BlockPosition blockposition, EnumDirection enumdirection) {
        return this.getBlockFacePower(blockposition, enumdirection) > 0;
    }

    public int getBlockFacePower(BlockPosition blockposition, EnumDirection enumdirection) {
        IBlockData iblockdata = this.getType(blockposition);
        Block block = iblockdata.getBlock();

        return block.isOccluding() ? this.getBlockPower(blockposition) : block.a((IBlockAccess) this, blockposition, iblockdata, enumdirection);
    }

    public boolean isBlockIndirectlyPowered(BlockPosition blockposition) {
        return this.getBlockFacePower(blockposition.down(), EnumDirection.DOWN) > 0 ? true : (this.getBlockFacePower(blockposition.up(), EnumDirection.UP) > 0 ? true : (this.getBlockFacePower(blockposition.north(), EnumDirection.NORTH) > 0 ? true : (this.getBlockFacePower(blockposition.south(), EnumDirection.SOUTH) > 0 ? true : (this.getBlockFacePower(blockposition.west(), EnumDirection.WEST) > 0 ? true : this.getBlockFacePower(blockposition.east(), EnumDirection.EAST) > 0))));
    }

    public int A(BlockPosition blockposition) {
        int i = 0;
        EnumDirection[] aenumdirection = EnumDirection.values();
        int j = aenumdirection.length;

        for (int k = 0; k < j; ++k) {
            EnumDirection enumdirection = aenumdirection[k];
            int l = this.getBlockFacePower(blockposition.shift(enumdirection), enumdirection);

            if (l >= 15) {
                return 15;
            }

            if (l > i) {
                i = l;
            }
        }

        return i;
    }

    public EntityHuman findNearbyPlayer(Entity entity, double d0) {
        return this.findNearbyPlayer(entity.locX, entity.locY, entity.locZ, d0);
    }

    public EntityHuman findNearbyPlayer(double d0, double d1, double d2, double d3) {
        double d4 = -1.0D;
        EntityHuman entityhuman = null;

        for (int i = 0; i < this.players.size(); ++i) {
            EntityHuman entityhuman1 = (EntityHuman) this.players.get(i);
            // CraftBukkit start - Fixed an NPE
            if (entityhuman1 == null || entityhuman1.dead) {
                continue;
            }
            // CraftBukkit end

            if (IEntitySelector.d.apply(entityhuman1)) {
                double d5 = entityhuman1.e(d0, d1, d2);

                if ((d3 < 0.0D || d5 < d3 * d3) && (d4 == -1.0D || d5 < d4)) {
                    d4 = d5;
                    entityhuman = entityhuman1;
                }
            }
        }

        return entityhuman;
    }

    public boolean isPlayerNearby(double d0, double d1, double d2, double d3) {
        for (int i = 0; i < this.players.size(); ++i) {
            EntityHuman entityhuman = (EntityHuman) this.players.get(i);

            if (IEntitySelector.d.apply(entityhuman)) {
                double d4 = entityhuman.e(d0, d1, d2);

                if (d3 < 0.0D || d4 < d3 * d3) {
                    return true;
                }
            }
        }

        return false;
    }

    public EntityHuman a(String s) {
        for (int i = 0; i < this.players.size(); ++i) {
            EntityHuman entityhuman = (EntityHuman) this.players.get(i);

            if (s.equals(entityhuman.getName())) {
                return entityhuman;
            }
        }

        return null;
    }

    public EntityHuman b(UUID uuid) {
        for (int i = 0; i < this.players.size(); ++i) {
            EntityHuman entityhuman = (EntityHuman) this.players.get(i);

            if (uuid.equals(entityhuman.getUniqueID())) {
                return entityhuman;
            }
        }

        return null;
    }

    public void checkSession() throws ExceptionWorldConflict { // CraftBukkit - added throws
        this.dataManager.checkSession();
    }

    public long getSeed() {
        return this.worldData.getSeed();
    }

    public long getTime() {
        return this.worldData.getTime();
    }

    public long getDayTime() {
        return this.worldData.getDayTime();
    }

    public void setDayTime(long i) {
        this.worldData.setDayTime(i);
    }

    public BlockPosition getSpawn() {
        BlockPosition blockposition = new BlockPosition(this.worldData.c(), this.worldData.d(), this.worldData.e());

        if (!this.af().a(blockposition)) {
            blockposition = this.getHighestBlockYAt(new BlockPosition(this.af().f(), 0.0D, this.af().g()));
        }

        return blockposition;
    }

    public void B(BlockPosition blockposition) {
        this.worldData.setSpawn(blockposition);
    }

    public boolean a(EntityHuman entityhuman, BlockPosition blockposition) {
        return true;
    }

    public void broadcastEntityEffect(Entity entity, byte b0) {}

    public IChunkProvider N() {
        return this.chunkProvider;
    }

    public void playBlockAction(BlockPosition blockposition, Block block, int i, int j) {
        block.a(this, blockposition, this.getType(blockposition), i, j);
    }

    public IDataManager getDataManager() {
        return this.dataManager;
    }

    public WorldData getWorldData() {
        return this.worldData;
    }

    public GameRules getGameRules() {
        return this.worldData.x();
    }

    public void everyoneSleeping() {}

    // CraftBukkit start
    // Calls the method that checks to see if players are sleeping
    // Called by CraftPlayer.setPermanentSleeping()
    public void checkSleepStatus() {
        if (!this.isStatic) {
            this.everyoneSleeping();
        }
    }
    // CraftBukkit end

    public float h(float f) {
        return (this.q + (this.r - this.q) * f) * this.j(f);
    }

    public float j(float f) {
        return this.o + (this.p - this.o) * f;
    }

    public boolean R() {
        return (double) this.h(1.0F) > 0.9D;
    }

    public boolean S() {
        return (double) this.j(1.0F) > 0.2D;
    }

    public boolean isRainingAt(BlockPosition blockposition) {
        if (!this.S()) {
            return false;
        } else if (!this.i(blockposition)) {
            return false;
        } else if (this.q(blockposition).getY() > blockposition.getY()) {
            return false;
        } else {
            BiomeBase biomebase = this.getBiome(blockposition);

            return biomebase.d() ? false : (this.f(blockposition, false) ? false : biomebase.e());
        }
    }

    public boolean D(BlockPosition blockposition) {
        BiomeBase biomebase = this.getBiome(blockposition);

        return biomebase.f();
    }

    public PersistentCollection T() {
        return this.worldMaps;
    }

    public void a(String s, PersistentBase persistentbase) {
        this.worldMaps.a(s, persistentbase);
    }

    public PersistentBase a(Class oclass, String s) {
        return this.worldMaps.get(oclass, s);
    }

    public int b(String s) {
        return this.worldMaps.a(s);
    }

    public void a(int i, BlockPosition blockposition, int j) {
        for (int k = 0; k < this.u.size(); ++k) {
            ((IWorldAccess) this.u.get(k)).a(i, blockposition, j);
        }

    }

    public void triggerEffect(int i, BlockPosition blockposition, int j) {
        this.a((EntityHuman) null, i, blockposition, j);
    }

    public void a(EntityHuman entityhuman, int i, BlockPosition blockposition, int j) {
        try {
            for (int k = 0; k < this.u.size(); ++k) {
                ((IWorldAccess) this.u.get(k)).a(entityhuman, i, blockposition, j);
            }

        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.a(throwable, "Playing level event");
            CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Level event being played");

            crashreportsystemdetails.a("Block coordinates", (Object) CrashReportSystemDetails.a(blockposition));
            crashreportsystemdetails.a("Event source", (Object) entityhuman);
            crashreportsystemdetails.a("Event type", (Object) Integer.valueOf(i));
            crashreportsystemdetails.a("Event data", (Object) Integer.valueOf(j));
            throw new ReportedException(crashreport);
        }
    }

    public int getHeight() {
        return 256;
    }

    public int V() {
        return this.worldProvider.o() ? 128 : 256;
    }

    public Random a(int i, int j, int k) {
        long l = (long) i * 341873128712L + (long) j * 132897987541L + this.getWorldData().getSeed() + (long) k;

        this.random.setSeed(l);
        return this.random;
    }

    public BlockPosition a(String s, BlockPosition blockposition) {
        return this.N().findNearestMapFeature(this, s, blockposition);
    }

    public CrashReportSystemDetails a(CrashReport crashreport) {
        CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Affected level", 1);

        crashreportsystemdetails.a("Level name", (Object) (this.worldData == null ? "????" : this.worldData.getName()));
        crashreportsystemdetails.a("All players", (Callable) (new CrashReportPlayers(this)));
        crashreportsystemdetails.a("Chunk stats", (Callable) (new CrashReportChunkStats(this)));

        try {
            this.worldData.a(crashreportsystemdetails);
        } catch (Throwable throwable) {
            crashreportsystemdetails.a("Level Data Unobtainable", throwable);
        }

        return crashreportsystemdetails;
    }

    public void c(int i, BlockPosition blockposition, int j) {
        for (int k = 0; k < this.u.size(); ++k) {
            IWorldAccess iworldaccess = (IWorldAccess) this.u.get(k);

            iworldaccess.b(i, blockposition, j);
        }

    }

    public Calendar Y() {
        if (this.getTime() % 600L == 0L) {
            this.J.setTimeInMillis(MinecraftServer.ax());
        }

        return this.J;
    }

    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }

    public void updateAdjacentComparators(BlockPosition blockposition, Block block) {
        Iterator iterator = EnumDirectionLimit.HORIZONTAL.iterator();

        while (iterator.hasNext()) {
            EnumDirection enumdirection = (EnumDirection) iterator.next();
            BlockPosition blockposition1 = blockposition.shift(enumdirection);

            if (this.isLoaded(blockposition1)) {
                IBlockData iblockdata = this.getType(blockposition1);

                if (Blocks.UNPOWERED_COMPARATOR.e(iblockdata.getBlock())) {
                    iblockdata.getBlock().doPhysics(this, blockposition1, iblockdata, block);
                } else if (iblockdata.getBlock().isOccluding()) {
                    blockposition1 = blockposition1.shift(enumdirection);
                    iblockdata = this.getType(blockposition1);
                    if (Blocks.UNPOWERED_COMPARATOR.e(iblockdata.getBlock())) {
                        iblockdata.getBlock().doPhysics(this, blockposition1, iblockdata, block);
                    }
                }
            }
        }

    }

    public DifficultyDamageScaler E(BlockPosition blockposition) {
        long i = 0L;
        float f = 0.0F;

        if (this.isLoaded(blockposition)) {
            f = this.y();
            i = this.getChunkAtWorldCoords(blockposition).w();
        }

        return new DifficultyDamageScaler(this.getDifficulty(), this.getDayTime(), i, f);
    }

    public EnumDifficulty getDifficulty() {
        return this.getWorldData().y();
    }

    public int ab() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public void c(int i) {
        this.I = i;
    }

    public boolean ad() {
        return this.isLoading;
    }

    public PersistentVillage ae() {
        return this.villages;
    }

    public WorldBorder af() {
        return this.M;
    }

    public boolean c(int i, int j) {
        BlockPosition blockposition = this.getSpawn();
        int k = i * 16 + 8 - blockposition.getX();
        int l = j * 16 + 8 - blockposition.getZ();
        short short0 = 128;

        return k >= -short0 && k <= short0 && l >= -short0 && l <= short0 || !this.keepSpawnInMemory; // CraftBukkit - Added 'this.world.keepSpawnInMemory'
    }
}
