package net.minecraft.server;

import java.util.Random;

class MerchantOptionRandomRange extends Tuple {

    public MerchantOptionRandomRange(int i, int j) {
        super(Integer.valueOf(i), Integer.valueOf(j));
    }

    public int a(Random random) {
        return ((Integer) this.a()).intValue() >= ((Integer) this.b()).intValue() ? ((Integer) this.a()).intValue() : ((Integer) this.a()).intValue() + random.nextInt(((Integer) this.b()).intValue() - ((Integer) this.a()).intValue() + 1);
    }
}
