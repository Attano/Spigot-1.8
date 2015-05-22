package net.minecraft.server;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class WeightedRandom {

    public static int a(Collection<? extends WeightedRandom.WeightedRandomChoice> collection) {
        int i = 0;

        WeightedRandom.WeightedRandomChoice weightedrandom_weightedrandomchoice;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); i += weightedrandom_weightedrandomchoice.a) {
            weightedrandom_weightedrandomchoice = (WeightedRandom.WeightedRandomChoice) iterator.next();
        }

        return i;
    }

    public static <T extends WeightedRandom.WeightedRandomChoice> T a(Random random, Collection<T> collection, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException();
        } else {
            int j = random.nextInt(i);

            return a(collection, j);
        }
    }

    public static <T extends WeightedRandom.WeightedRandomChoice> T a(Collection<T> collection, int i) {
        Iterator iterator = collection.iterator();

        WeightedRandom.WeightedRandomChoice weightedrandom_weightedrandomchoice;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            weightedrandom_weightedrandomchoice = (WeightedRandom.WeightedRandomChoice) iterator.next();
            i -= weightedrandom_weightedrandomchoice.a;
        } while (i >= 0);

        return weightedrandom_weightedrandomchoice;
    }

    public static <T extends WeightedRandom.WeightedRandomChoice> T a(Random random, Collection<T> collection) {
        return a(random, collection, a(collection));
    }

    public static class WeightedRandomChoice {

        protected int a;

        public WeightedRandomChoice(int i) {
            this.a = i;
        }
    }
}
