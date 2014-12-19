package net.minecraft.server;

import java.io.PrintStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DispenserRegistry {

    private static final PrintStream a = System.out;
    private static boolean b = false;
    private static final Logger c = LogManager.getLogger();

    public static boolean a() {
        return DispenserRegistry.b;
    }

    static void b() {
        BlockDispenser.M.a(Items.ARROW, new DispenseBehaviorArrow());
        BlockDispenser.M.a(Items.EGG, new DispenseBehaviorEgg());
        BlockDispenser.M.a(Items.SNOWBALL, new DispenseBehaviorSnowBall());
        BlockDispenser.M.a(Items.EXPERIENCE_BOTTLE, new DispenseBehaviorExpBottle());
        BlockDispenser.M.a(Items.POTION, new DispenseBehaviorPotion());
        BlockDispenser.M.a(Items.SPAWN_EGG, new DispenseBehaviorMonsterEgg());
        BlockDispenser.M.a(Items.FIREWORKS, new DispenseBehaviorFireworks());
        BlockDispenser.M.a(Items.FIRE_CHARGE, new DispenseBehaviorFireball());
        BlockDispenser.M.a(Items.BOAT, new DispenseBehaviorBoat());
        DispenseBehaviorFilledBucket dispensebehaviorfilledbucket = new DispenseBehaviorFilledBucket();

        BlockDispenser.M.a(Items.LAVA_BUCKET, dispensebehaviorfilledbucket);
        BlockDispenser.M.a(Items.WATER_BUCKET, dispensebehaviorfilledbucket);
        BlockDispenser.M.a(Items.BUCKET, new DispenseBehaviorEmptyBucket());
        BlockDispenser.M.a(Items.FLINT_AND_STEEL, new DispenseBehaviorFlintAndSteel());
        BlockDispenser.M.a(Items.DYE, new DispenseBehaviorBonemeal());
        BlockDispenser.M.a(Item.getItemOf(Blocks.TNT), new DispenseBehaviorTNT());
        BlockDispenser.M.a(Items.SKULL, new DispenseBehaviorSkull());
        BlockDispenser.M.a(Item.getItemOf(Blocks.PUMPKIN), new DispenseBehaviorPumpkin());
        BlockDispenser.M.a(Item.getItemOf(Blocks.COMMAND_BLOCK), new DispenseBehaviorCommandBlock());
    }

    public static void c() {
        if (!DispenserRegistry.b) {
            DispenserRegistry.b = true;
            if (DispenserRegistry.c.isDebugEnabled()) {
                d();
            }

            Block.R();
            BlockFire.j();
            Item.t();
            StatisticList.a();
            b();
        }
    }

    private static void d() {
        System.setErr(new RedirectStream("STDERR", System.err));
        System.setOut(new RedirectStream("STDOUT", DispenserRegistry.a));
    }
}
