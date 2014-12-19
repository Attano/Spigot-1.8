package net.minecraft.server;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;

public enum EnumFlowerVarient implements INamable {

    DANDELION(EnumFlowerType.YELLOW, 0, "dandelion"), POPPY(EnumFlowerType.RED, 0, "poppy"), BLUE_ORCHID(EnumFlowerType.RED, 1, "blue_orchid", "blueOrchid"), ALLIUM(EnumFlowerType.RED, 2, "allium"), HOUSTONIA(EnumFlowerType.RED, 3, "houstonia"), RED_TULIP(EnumFlowerType.RED, 4, "red_tulip", "tulipRed"), ORANGE_TULIP(EnumFlowerType.RED, 5, "orange_tulip", "tulipOrange"), WHITE_TULIP(EnumFlowerType.RED, 6, "white_tulip", "tulipWhite"), PINK_TULIP(EnumFlowerType.RED, 7, "pink_tulip", "tulipPink"), OXEYE_DAISY(EnumFlowerType.RED, 8, "oxeye_daisy", "oxeyeDaisy");

    private static final EnumFlowerVarient[][] k = new EnumFlowerVarient[EnumFlowerType.values().length][];
    private final EnumFlowerType l;
    private final int m;
    private final String n;
    private final String o;

    private EnumFlowerVarient(EnumFlowerType enumflowertype, int i, String s) {
        this(enumflowertype, i, s, s);
    }

    private EnumFlowerVarient(EnumFlowerType enumflowertype, int i, String s, String s1) {
        this.l = enumflowertype;
        this.m = i;
        this.n = s;
        this.o = s1;
    }

    public EnumFlowerType a() {
        return this.l;
    }

    public int b() {
        return this.m;
    }

    public static EnumFlowerVarient a(EnumFlowerType enumflowertype, int i) {
        EnumFlowerVarient[] aenumflowervarient = EnumFlowerVarient.k[enumflowertype.ordinal()];

        if (i < 0 || i >= aenumflowervarient.length) {
            i = 0;
        }

        return aenumflowervarient[i];
    }

    public String toString() {
        return this.n;
    }

    public String getName() {
        return this.n;
    }

    public String d() {
        return this.o;
    }

    static {
        EnumFlowerType[] aenumflowertype = EnumFlowerType.values();
        int i = aenumflowertype.length;

        for (int j = 0; j < i; ++j) {
            EnumFlowerType enumflowertype = aenumflowertype[j];
            Collection collection = Collections2.filter(Lists.newArrayList(values()), new EnumFlowerTypeInnerClassSelector(enumflowertype));

            EnumFlowerVarient.k[enumflowertype.ordinal()] = (EnumFlowerVarient[]) collection.toArray(new EnumFlowerVarient[collection.size()]);
        }

    }
}
