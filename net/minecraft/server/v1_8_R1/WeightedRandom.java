package net.minecraft.server;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class WeightedRandom {

    public static int a(Collection collection) {
        int i = 0;

        WeightedRandomChoice weightedrandomchoice;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); i += weightedrandomchoice.a) {
            weightedrandomchoice = (WeightedRandomChoice) iterator.next();
        }

        return i;
    }

    public static WeightedRandomChoice a(Random random, Collection collection, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException();
        } else {
            int j = random.nextInt(i);

            return a(collection, j);
        }
    }

    public static WeightedRandomChoice a(Collection collection, int i) {
        Iterator iterator = collection.iterator();

        WeightedRandomChoice weightedrandomchoice;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            weightedrandomchoice = (WeightedRandomChoice) iterator.next();
            i -= weightedrandomchoice.a;
        } while (i >= 0);

        return weightedrandomchoice;
    }

    public static WeightedRandomChoice a(Random random, Collection collection) {
        return a(random, collection, a(collection));
    }
}
