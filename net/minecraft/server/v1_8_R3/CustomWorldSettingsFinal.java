package net.minecraft.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class CustomWorldSettingsFinal {

    public final float a;
    public final float b;
    public final float c;
    public final float d;
    public final float e;
    public final float f;
    public final float g;
    public final float h;
    public final float i;
    public final float j;
    public final float k;
    public final float l;
    public final float m;
    public final float n;
    public final float o;
    public final float p;
    public final int q;
    public final boolean r;
    public final boolean s;
    public final int t;
    public final boolean u;
    public final boolean v;
    public final boolean w;
    public final boolean x;
    public final boolean y;
    public final boolean z;
    public final boolean A;
    public final int B;
    public final boolean C;
    public final int D;
    public final boolean E;
    public final int F;
    public final int G;
    public final int H;
    public final int I;
    public final int J;
    public final int K;
    public final int L;
    public final int M;
    public final int N;
    public final int O;
    public final int P;
    public final int Q;
    public final int R;
    public final int S;
    public final int T;
    public final int U;
    public final int V;
    public final int W;
    public final int X;
    public final int Y;
    public final int Z;
    public final int aa;
    public final int ab;
    public final int ac;
    public final int ad;
    public final int ae;
    public final int af;
    public final int ag;
    public final int ah;
    public final int ai;
    public final int aj;
    public final int ak;
    public final int al;
    public final int am;
    public final int an;
    public final int ao;
    public final int ap;
    public final int aq;
    public final int ar;
    public final int as;
    public final int at;
    public final int au;
    public final int av;
    public final int aw;
    public final int ax;
    public final int ay;
    public final int az;

    private CustomWorldSettingsFinal(CustomWorldSettingsFinal.CustomWorldSettings customworldsettingsfinal_customworldsettings) {
        this.a = customworldsettingsfinal_customworldsettings.b;
        this.b = customworldsettingsfinal_customworldsettings.c;
        this.c = customworldsettingsfinal_customworldsettings.d;
        this.d = customworldsettingsfinal_customworldsettings.e;
        this.e = customworldsettingsfinal_customworldsettings.f;
        this.f = customworldsettingsfinal_customworldsettings.g;
        this.g = customworldsettingsfinal_customworldsettings.h;
        this.h = customworldsettingsfinal_customworldsettings.i;
        this.i = customworldsettingsfinal_customworldsettings.j;
        this.j = customworldsettingsfinal_customworldsettings.k;
        this.k = customworldsettingsfinal_customworldsettings.l;
        this.l = customworldsettingsfinal_customworldsettings.m;
        this.m = customworldsettingsfinal_customworldsettings.n;
        this.n = customworldsettingsfinal_customworldsettings.o;
        this.o = customworldsettingsfinal_customworldsettings.p;
        this.p = customworldsettingsfinal_customworldsettings.q;
        this.q = customworldsettingsfinal_customworldsettings.r;
        this.r = customworldsettingsfinal_customworldsettings.s;
        this.s = customworldsettingsfinal_customworldsettings.t;
        this.t = customworldsettingsfinal_customworldsettings.u;
        this.u = customworldsettingsfinal_customworldsettings.v;
        this.v = customworldsettingsfinal_customworldsettings.w;
        this.w = customworldsettingsfinal_customworldsettings.x;
        this.x = customworldsettingsfinal_customworldsettings.y;
        this.y = customworldsettingsfinal_customworldsettings.z;
        this.z = customworldsettingsfinal_customworldsettings.A;
        this.A = customworldsettingsfinal_customworldsettings.B;
        this.B = customworldsettingsfinal_customworldsettings.C;
        this.C = customworldsettingsfinal_customworldsettings.D;
        this.D = customworldsettingsfinal_customworldsettings.E;
        this.E = customworldsettingsfinal_customworldsettings.F;
        this.F = customworldsettingsfinal_customworldsettings.G;
        this.G = customworldsettingsfinal_customworldsettings.H;
        this.H = customworldsettingsfinal_customworldsettings.I;
        this.I = customworldsettingsfinal_customworldsettings.J;
        this.J = customworldsettingsfinal_customworldsettings.K;
        this.K = customworldsettingsfinal_customworldsettings.L;
        this.L = customworldsettingsfinal_customworldsettings.M;
        this.M = customworldsettingsfinal_customworldsettings.N;
        this.N = customworldsettingsfinal_customworldsettings.O;
        this.O = customworldsettingsfinal_customworldsettings.P;
        this.P = customworldsettingsfinal_customworldsettings.Q;
        this.Q = customworldsettingsfinal_customworldsettings.R;
        this.R = customworldsettingsfinal_customworldsettings.S;
        this.S = customworldsettingsfinal_customworldsettings.T;
        this.T = customworldsettingsfinal_customworldsettings.U;
        this.U = customworldsettingsfinal_customworldsettings.V;
        this.V = customworldsettingsfinal_customworldsettings.W;
        this.W = customworldsettingsfinal_customworldsettings.X;
        this.X = customworldsettingsfinal_customworldsettings.Y;
        this.Y = customworldsettingsfinal_customworldsettings.Z;
        this.Z = customworldsettingsfinal_customworldsettings.aa;
        this.aa = customworldsettingsfinal_customworldsettings.ab;
        this.ab = customworldsettingsfinal_customworldsettings.ac;
        this.ac = customworldsettingsfinal_customworldsettings.ad;
        this.ad = customworldsettingsfinal_customworldsettings.ae;
        this.ae = customworldsettingsfinal_customworldsettings.af;
        this.af = customworldsettingsfinal_customworldsettings.ag;
        this.ag = customworldsettingsfinal_customworldsettings.ah;
        this.ah = customworldsettingsfinal_customworldsettings.ai;
        this.ai = customworldsettingsfinal_customworldsettings.aj;
        this.aj = customworldsettingsfinal_customworldsettings.ak;
        this.ak = customworldsettingsfinal_customworldsettings.al;
        this.al = customworldsettingsfinal_customworldsettings.am;
        this.am = customworldsettingsfinal_customworldsettings.an;
        this.an = customworldsettingsfinal_customworldsettings.ao;
        this.ao = customworldsettingsfinal_customworldsettings.ap;
        this.ap = customworldsettingsfinal_customworldsettings.aq;
        this.aq = customworldsettingsfinal_customworldsettings.ar;
        this.ar = customworldsettingsfinal_customworldsettings.as;
        this.as = customworldsettingsfinal_customworldsettings.at;
        this.at = customworldsettingsfinal_customworldsettings.au;
        this.au = customworldsettingsfinal_customworldsettings.av;
        this.av = customworldsettingsfinal_customworldsettings.aw;
        this.aw = customworldsettingsfinal_customworldsettings.ax;
        this.ax = customworldsettingsfinal_customworldsettings.ay;
        this.ay = customworldsettingsfinal_customworldsettings.az;
        this.az = customworldsettingsfinal_customworldsettings.aA;
    }

    CustomWorldSettingsFinal(CustomWorldSettingsFinal.CustomWorldSettings customworldsettingsfinal_customworldsettings, CustomWorldSettingsFinal.SyntheticClass_1 customworldsettingsfinal_syntheticclass_1) {
        this(customworldsettingsfinal_customworldsettings);
    }

    static class SyntheticClass_1 {    }

    public static class CustomWorldSettingsSerializer implements JsonDeserializer<CustomWorldSettingsFinal.CustomWorldSettings>, JsonSerializer<CustomWorldSettingsFinal.CustomWorldSettings> {

        public CustomWorldSettingsSerializer() {}

        public CustomWorldSettingsFinal.CustomWorldSettings a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            JsonObject jsonobject = jsonelement.getAsJsonObject();
            CustomWorldSettingsFinal.CustomWorldSettings customworldsettingsfinal_customworldsettings = new CustomWorldSettingsFinal.CustomWorldSettings();

            try {
                customworldsettingsfinal_customworldsettings.b = ChatDeserializer.a(jsonobject, "coordinateScale", customworldsettingsfinal_customworldsettings.b);
                customworldsettingsfinal_customworldsettings.c = ChatDeserializer.a(jsonobject, "heightScale", customworldsettingsfinal_customworldsettings.c);
                customworldsettingsfinal_customworldsettings.e = ChatDeserializer.a(jsonobject, "lowerLimitScale", customworldsettingsfinal_customworldsettings.e);
                customworldsettingsfinal_customworldsettings.d = ChatDeserializer.a(jsonobject, "upperLimitScale", customworldsettingsfinal_customworldsettings.d);
                customworldsettingsfinal_customworldsettings.f = ChatDeserializer.a(jsonobject, "depthNoiseScaleX", customworldsettingsfinal_customworldsettings.f);
                customworldsettingsfinal_customworldsettings.g = ChatDeserializer.a(jsonobject, "depthNoiseScaleZ", customworldsettingsfinal_customworldsettings.g);
                customworldsettingsfinal_customworldsettings.h = ChatDeserializer.a(jsonobject, "depthNoiseScaleExponent", customworldsettingsfinal_customworldsettings.h);
                customworldsettingsfinal_customworldsettings.i = ChatDeserializer.a(jsonobject, "mainNoiseScaleX", customworldsettingsfinal_customworldsettings.i);
                customworldsettingsfinal_customworldsettings.j = ChatDeserializer.a(jsonobject, "mainNoiseScaleY", customworldsettingsfinal_customworldsettings.j);
                customworldsettingsfinal_customworldsettings.k = ChatDeserializer.a(jsonobject, "mainNoiseScaleZ", customworldsettingsfinal_customworldsettings.k);
                customworldsettingsfinal_customworldsettings.l = ChatDeserializer.a(jsonobject, "baseSize", customworldsettingsfinal_customworldsettings.l);
                customworldsettingsfinal_customworldsettings.m = ChatDeserializer.a(jsonobject, "stretchY", customworldsettingsfinal_customworldsettings.m);
                customworldsettingsfinal_customworldsettings.n = ChatDeserializer.a(jsonobject, "biomeDepthWeight", customworldsettingsfinal_customworldsettings.n);
                customworldsettingsfinal_customworldsettings.o = ChatDeserializer.a(jsonobject, "biomeDepthOffset", customworldsettingsfinal_customworldsettings.o);
                customworldsettingsfinal_customworldsettings.p = ChatDeserializer.a(jsonobject, "biomeScaleWeight", customworldsettingsfinal_customworldsettings.p);
                customworldsettingsfinal_customworldsettings.q = ChatDeserializer.a(jsonobject, "biomeScaleOffset", customworldsettingsfinal_customworldsettings.q);
                customworldsettingsfinal_customworldsettings.r = ChatDeserializer.a(jsonobject, "seaLevel", customworldsettingsfinal_customworldsettings.r);
                customworldsettingsfinal_customworldsettings.s = ChatDeserializer.a(jsonobject, "useCaves", customworldsettingsfinal_customworldsettings.s);
                customworldsettingsfinal_customworldsettings.t = ChatDeserializer.a(jsonobject, "useDungeons", customworldsettingsfinal_customworldsettings.t);
                customworldsettingsfinal_customworldsettings.u = ChatDeserializer.a(jsonobject, "dungeonChance", customworldsettingsfinal_customworldsettings.u);
                customworldsettingsfinal_customworldsettings.v = ChatDeserializer.a(jsonobject, "useStrongholds", customworldsettingsfinal_customworldsettings.v);
                customworldsettingsfinal_customworldsettings.w = ChatDeserializer.a(jsonobject, "useVillages", customworldsettingsfinal_customworldsettings.w);
                customworldsettingsfinal_customworldsettings.x = ChatDeserializer.a(jsonobject, "useMineShafts", customworldsettingsfinal_customworldsettings.x);
                customworldsettingsfinal_customworldsettings.y = ChatDeserializer.a(jsonobject, "useTemples", customworldsettingsfinal_customworldsettings.y);
                customworldsettingsfinal_customworldsettings.z = ChatDeserializer.a(jsonobject, "useMonuments", customworldsettingsfinal_customworldsettings.z);
                customworldsettingsfinal_customworldsettings.A = ChatDeserializer.a(jsonobject, "useRavines", customworldsettingsfinal_customworldsettings.A);
                customworldsettingsfinal_customworldsettings.B = ChatDeserializer.a(jsonobject, "useWaterLakes", customworldsettingsfinal_customworldsettings.B);
                customworldsettingsfinal_customworldsettings.C = ChatDeserializer.a(jsonobject, "waterLakeChance", customworldsettingsfinal_customworldsettings.C);
                customworldsettingsfinal_customworldsettings.D = ChatDeserializer.a(jsonobject, "useLavaLakes", customworldsettingsfinal_customworldsettings.D);
                customworldsettingsfinal_customworldsettings.E = ChatDeserializer.a(jsonobject, "lavaLakeChance", customworldsettingsfinal_customworldsettings.E);
                customworldsettingsfinal_customworldsettings.F = ChatDeserializer.a(jsonobject, "useLavaOceans", customworldsettingsfinal_customworldsettings.F);
                customworldsettingsfinal_customworldsettings.G = ChatDeserializer.a(jsonobject, "fixedBiome", customworldsettingsfinal_customworldsettings.G);
                if (customworldsettingsfinal_customworldsettings.G < 38 && customworldsettingsfinal_customworldsettings.G >= -1) {
                    if (customworldsettingsfinal_customworldsettings.G >= BiomeBase.HELL.id) {
                        customworldsettingsfinal_customworldsettings.G += 2;
                    }
                } else {
                    customworldsettingsfinal_customworldsettings.G = -1;
                }

                customworldsettingsfinal_customworldsettings.H = ChatDeserializer.a(jsonobject, "biomeSize", customworldsettingsfinal_customworldsettings.H);
                customworldsettingsfinal_customworldsettings.I = ChatDeserializer.a(jsonobject, "riverSize", customworldsettingsfinal_customworldsettings.I);
                customworldsettingsfinal_customworldsettings.J = ChatDeserializer.a(jsonobject, "dirtSize", customworldsettingsfinal_customworldsettings.J);
                customworldsettingsfinal_customworldsettings.K = ChatDeserializer.a(jsonobject, "dirtCount", customworldsettingsfinal_customworldsettings.K);
                customworldsettingsfinal_customworldsettings.L = ChatDeserializer.a(jsonobject, "dirtMinHeight", customworldsettingsfinal_customworldsettings.L);
                customworldsettingsfinal_customworldsettings.M = ChatDeserializer.a(jsonobject, "dirtMaxHeight", customworldsettingsfinal_customworldsettings.M);
                customworldsettingsfinal_customworldsettings.N = ChatDeserializer.a(jsonobject, "gravelSize", customworldsettingsfinal_customworldsettings.N);
                customworldsettingsfinal_customworldsettings.O = ChatDeserializer.a(jsonobject, "gravelCount", customworldsettingsfinal_customworldsettings.O);
                customworldsettingsfinal_customworldsettings.P = ChatDeserializer.a(jsonobject, "gravelMinHeight", customworldsettingsfinal_customworldsettings.P);
                customworldsettingsfinal_customworldsettings.Q = ChatDeserializer.a(jsonobject, "gravelMaxHeight", customworldsettingsfinal_customworldsettings.Q);
                customworldsettingsfinal_customworldsettings.R = ChatDeserializer.a(jsonobject, "graniteSize", customworldsettingsfinal_customworldsettings.R);
                customworldsettingsfinal_customworldsettings.S = ChatDeserializer.a(jsonobject, "graniteCount", customworldsettingsfinal_customworldsettings.S);
                customworldsettingsfinal_customworldsettings.T = ChatDeserializer.a(jsonobject, "graniteMinHeight", customworldsettingsfinal_customworldsettings.T);
                customworldsettingsfinal_customworldsettings.U = ChatDeserializer.a(jsonobject, "graniteMaxHeight", customworldsettingsfinal_customworldsettings.U);
                customworldsettingsfinal_customworldsettings.V = ChatDeserializer.a(jsonobject, "dioriteSize", customworldsettingsfinal_customworldsettings.V);
                customworldsettingsfinal_customworldsettings.W = ChatDeserializer.a(jsonobject, "dioriteCount", customworldsettingsfinal_customworldsettings.W);
                customworldsettingsfinal_customworldsettings.X = ChatDeserializer.a(jsonobject, "dioriteMinHeight", customworldsettingsfinal_customworldsettings.X);
                customworldsettingsfinal_customworldsettings.Y = ChatDeserializer.a(jsonobject, "dioriteMaxHeight", customworldsettingsfinal_customworldsettings.Y);
                customworldsettingsfinal_customworldsettings.Z = ChatDeserializer.a(jsonobject, "andesiteSize", customworldsettingsfinal_customworldsettings.Z);
                customworldsettingsfinal_customworldsettings.aa = ChatDeserializer.a(jsonobject, "andesiteCount", customworldsettingsfinal_customworldsettings.aa);
                customworldsettingsfinal_customworldsettings.ab = ChatDeserializer.a(jsonobject, "andesiteMinHeight", customworldsettingsfinal_customworldsettings.ab);
                customworldsettingsfinal_customworldsettings.ac = ChatDeserializer.a(jsonobject, "andesiteMaxHeight", customworldsettingsfinal_customworldsettings.ac);
                customworldsettingsfinal_customworldsettings.ad = ChatDeserializer.a(jsonobject, "coalSize", customworldsettingsfinal_customworldsettings.ad);
                customworldsettingsfinal_customworldsettings.ae = ChatDeserializer.a(jsonobject, "coalCount", customworldsettingsfinal_customworldsettings.ae);
                customworldsettingsfinal_customworldsettings.af = ChatDeserializer.a(jsonobject, "coalMinHeight", customworldsettingsfinal_customworldsettings.af);
                customworldsettingsfinal_customworldsettings.ag = ChatDeserializer.a(jsonobject, "coalMaxHeight", customworldsettingsfinal_customworldsettings.ag);
                customworldsettingsfinal_customworldsettings.ah = ChatDeserializer.a(jsonobject, "ironSize", customworldsettingsfinal_customworldsettings.ah);
                customworldsettingsfinal_customworldsettings.ai = ChatDeserializer.a(jsonobject, "ironCount", customworldsettingsfinal_customworldsettings.ai);
                customworldsettingsfinal_customworldsettings.aj = ChatDeserializer.a(jsonobject, "ironMinHeight", customworldsettingsfinal_customworldsettings.aj);
                customworldsettingsfinal_customworldsettings.ak = ChatDeserializer.a(jsonobject, "ironMaxHeight", customworldsettingsfinal_customworldsettings.ak);
                customworldsettingsfinal_customworldsettings.al = ChatDeserializer.a(jsonobject, "goldSize", customworldsettingsfinal_customworldsettings.al);
                customworldsettingsfinal_customworldsettings.am = ChatDeserializer.a(jsonobject, "goldCount", customworldsettingsfinal_customworldsettings.am);
                customworldsettingsfinal_customworldsettings.an = ChatDeserializer.a(jsonobject, "goldMinHeight", customworldsettingsfinal_customworldsettings.an);
                customworldsettingsfinal_customworldsettings.ao = ChatDeserializer.a(jsonobject, "goldMaxHeight", customworldsettingsfinal_customworldsettings.ao);
                customworldsettingsfinal_customworldsettings.ap = ChatDeserializer.a(jsonobject, "redstoneSize", customworldsettingsfinal_customworldsettings.ap);
                customworldsettingsfinal_customworldsettings.aq = ChatDeserializer.a(jsonobject, "redstoneCount", customworldsettingsfinal_customworldsettings.aq);
                customworldsettingsfinal_customworldsettings.ar = ChatDeserializer.a(jsonobject, "redstoneMinHeight", customworldsettingsfinal_customworldsettings.ar);
                customworldsettingsfinal_customworldsettings.as = ChatDeserializer.a(jsonobject, "redstoneMaxHeight", customworldsettingsfinal_customworldsettings.as);
                customworldsettingsfinal_customworldsettings.at = ChatDeserializer.a(jsonobject, "diamondSize", customworldsettingsfinal_customworldsettings.at);
                customworldsettingsfinal_customworldsettings.au = ChatDeserializer.a(jsonobject, "diamondCount", customworldsettingsfinal_customworldsettings.au);
                customworldsettingsfinal_customworldsettings.av = ChatDeserializer.a(jsonobject, "diamondMinHeight", customworldsettingsfinal_customworldsettings.av);
                customworldsettingsfinal_customworldsettings.aw = ChatDeserializer.a(jsonobject, "diamondMaxHeight", customworldsettingsfinal_customworldsettings.aw);
                customworldsettingsfinal_customworldsettings.ax = ChatDeserializer.a(jsonobject, "lapisSize", customworldsettingsfinal_customworldsettings.ax);
                customworldsettingsfinal_customworldsettings.ay = ChatDeserializer.a(jsonobject, "lapisCount", customworldsettingsfinal_customworldsettings.ay);
                customworldsettingsfinal_customworldsettings.az = ChatDeserializer.a(jsonobject, "lapisCenterHeight", customworldsettingsfinal_customworldsettings.az);
                customworldsettingsfinal_customworldsettings.aA = ChatDeserializer.a(jsonobject, "lapisSpread", customworldsettingsfinal_customworldsettings.aA);
            } catch (Exception exception) {
                ;
            }

            return customworldsettingsfinal_customworldsettings;
        }

        public JsonElement a(CustomWorldSettingsFinal.CustomWorldSettings customworldsettingsfinal_customworldsettings, Type type, JsonSerializationContext jsonserializationcontext) {
            JsonObject jsonobject = new JsonObject();

            jsonobject.addProperty("coordinateScale", Float.valueOf(customworldsettingsfinal_customworldsettings.b));
            jsonobject.addProperty("heightScale", Float.valueOf(customworldsettingsfinal_customworldsettings.c));
            jsonobject.addProperty("lowerLimitScale", Float.valueOf(customworldsettingsfinal_customworldsettings.e));
            jsonobject.addProperty("upperLimitScale", Float.valueOf(customworldsettingsfinal_customworldsettings.d));
            jsonobject.addProperty("depthNoiseScaleX", Float.valueOf(customworldsettingsfinal_customworldsettings.f));
            jsonobject.addProperty("depthNoiseScaleZ", Float.valueOf(customworldsettingsfinal_customworldsettings.g));
            jsonobject.addProperty("depthNoiseScaleExponent", Float.valueOf(customworldsettingsfinal_customworldsettings.h));
            jsonobject.addProperty("mainNoiseScaleX", Float.valueOf(customworldsettingsfinal_customworldsettings.i));
            jsonobject.addProperty("mainNoiseScaleY", Float.valueOf(customworldsettingsfinal_customworldsettings.j));
            jsonobject.addProperty("mainNoiseScaleZ", Float.valueOf(customworldsettingsfinal_customworldsettings.k));
            jsonobject.addProperty("baseSize", Float.valueOf(customworldsettingsfinal_customworldsettings.l));
            jsonobject.addProperty("stretchY", Float.valueOf(customworldsettingsfinal_customworldsettings.m));
            jsonobject.addProperty("biomeDepthWeight", Float.valueOf(customworldsettingsfinal_customworldsettings.n));
            jsonobject.addProperty("biomeDepthOffset", Float.valueOf(customworldsettingsfinal_customworldsettings.o));
            jsonobject.addProperty("biomeScaleWeight", Float.valueOf(customworldsettingsfinal_customworldsettings.p));
            jsonobject.addProperty("biomeScaleOffset", Float.valueOf(customworldsettingsfinal_customworldsettings.q));
            jsonobject.addProperty("seaLevel", Integer.valueOf(customworldsettingsfinal_customworldsettings.r));
            jsonobject.addProperty("useCaves", Boolean.valueOf(customworldsettingsfinal_customworldsettings.s));
            jsonobject.addProperty("useDungeons", Boolean.valueOf(customworldsettingsfinal_customworldsettings.t));
            jsonobject.addProperty("dungeonChance", Integer.valueOf(customworldsettingsfinal_customworldsettings.u));
            jsonobject.addProperty("useStrongholds", Boolean.valueOf(customworldsettingsfinal_customworldsettings.v));
            jsonobject.addProperty("useVillages", Boolean.valueOf(customworldsettingsfinal_customworldsettings.w));
            jsonobject.addProperty("useMineShafts", Boolean.valueOf(customworldsettingsfinal_customworldsettings.x));
            jsonobject.addProperty("useTemples", Boolean.valueOf(customworldsettingsfinal_customworldsettings.y));
            jsonobject.addProperty("useMonuments", Boolean.valueOf(customworldsettingsfinal_customworldsettings.z));
            jsonobject.addProperty("useRavines", Boolean.valueOf(customworldsettingsfinal_customworldsettings.A));
            jsonobject.addProperty("useWaterLakes", Boolean.valueOf(customworldsettingsfinal_customworldsettings.B));
            jsonobject.addProperty("waterLakeChance", Integer.valueOf(customworldsettingsfinal_customworldsettings.C));
            jsonobject.addProperty("useLavaLakes", Boolean.valueOf(customworldsettingsfinal_customworldsettings.D));
            jsonobject.addProperty("lavaLakeChance", Integer.valueOf(customworldsettingsfinal_customworldsettings.E));
            jsonobject.addProperty("useLavaOceans", Boolean.valueOf(customworldsettingsfinal_customworldsettings.F));
            jsonobject.addProperty("fixedBiome", Integer.valueOf(customworldsettingsfinal_customworldsettings.G));
            jsonobject.addProperty("biomeSize", Integer.valueOf(customworldsettingsfinal_customworldsettings.H));
            jsonobject.addProperty("riverSize", Integer.valueOf(customworldsettingsfinal_customworldsettings.I));
            jsonobject.addProperty("dirtSize", Integer.valueOf(customworldsettingsfinal_customworldsettings.J));
            jsonobject.addProperty("dirtCount", Integer.valueOf(customworldsettingsfinal_customworldsettings.K));
            jsonobject.addProperty("dirtMinHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.L));
            jsonobject.addProperty("dirtMaxHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.M));
            jsonobject.addProperty("gravelSize", Integer.valueOf(customworldsettingsfinal_customworldsettings.N));
            jsonobject.addProperty("gravelCount", Integer.valueOf(customworldsettingsfinal_customworldsettings.O));
            jsonobject.addProperty("gravelMinHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.P));
            jsonobject.addProperty("gravelMaxHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.Q));
            jsonobject.addProperty("graniteSize", Integer.valueOf(customworldsettingsfinal_customworldsettings.R));
            jsonobject.addProperty("graniteCount", Integer.valueOf(customworldsettingsfinal_customworldsettings.S));
            jsonobject.addProperty("graniteMinHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.T));
            jsonobject.addProperty("graniteMaxHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.U));
            jsonobject.addProperty("dioriteSize", Integer.valueOf(customworldsettingsfinal_customworldsettings.V));
            jsonobject.addProperty("dioriteCount", Integer.valueOf(customworldsettingsfinal_customworldsettings.W));
            jsonobject.addProperty("dioriteMinHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.X));
            jsonobject.addProperty("dioriteMaxHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.Y));
            jsonobject.addProperty("andesiteSize", Integer.valueOf(customworldsettingsfinal_customworldsettings.Z));
            jsonobject.addProperty("andesiteCount", Integer.valueOf(customworldsettingsfinal_customworldsettings.aa));
            jsonobject.addProperty("andesiteMinHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.ab));
            jsonobject.addProperty("andesiteMaxHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.ac));
            jsonobject.addProperty("coalSize", Integer.valueOf(customworldsettingsfinal_customworldsettings.ad));
            jsonobject.addProperty("coalCount", Integer.valueOf(customworldsettingsfinal_customworldsettings.ae));
            jsonobject.addProperty("coalMinHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.af));
            jsonobject.addProperty("coalMaxHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.ag));
            jsonobject.addProperty("ironSize", Integer.valueOf(customworldsettingsfinal_customworldsettings.ah));
            jsonobject.addProperty("ironCount", Integer.valueOf(customworldsettingsfinal_customworldsettings.ai));
            jsonobject.addProperty("ironMinHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.aj));
            jsonobject.addProperty("ironMaxHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.ak));
            jsonobject.addProperty("goldSize", Integer.valueOf(customworldsettingsfinal_customworldsettings.al));
            jsonobject.addProperty("goldCount", Integer.valueOf(customworldsettingsfinal_customworldsettings.am));
            jsonobject.addProperty("goldMinHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.an));
            jsonobject.addProperty("goldMaxHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.ao));
            jsonobject.addProperty("redstoneSize", Integer.valueOf(customworldsettingsfinal_customworldsettings.ap));
            jsonobject.addProperty("redstoneCount", Integer.valueOf(customworldsettingsfinal_customworldsettings.aq));
            jsonobject.addProperty("redstoneMinHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.ar));
            jsonobject.addProperty("redstoneMaxHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.as));
            jsonobject.addProperty("diamondSize", Integer.valueOf(customworldsettingsfinal_customworldsettings.at));
            jsonobject.addProperty("diamondCount", Integer.valueOf(customworldsettingsfinal_customworldsettings.au));
            jsonobject.addProperty("diamondMinHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.av));
            jsonobject.addProperty("diamondMaxHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.aw));
            jsonobject.addProperty("lapisSize", Integer.valueOf(customworldsettingsfinal_customworldsettings.ax));
            jsonobject.addProperty("lapisCount", Integer.valueOf(customworldsettingsfinal_customworldsettings.ay));
            jsonobject.addProperty("lapisCenterHeight", Integer.valueOf(customworldsettingsfinal_customworldsettings.az));
            jsonobject.addProperty("lapisSpread", Integer.valueOf(customworldsettingsfinal_customworldsettings.aA));
            return jsonobject;
        }

        public Object deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            return this.a(jsonelement, type, jsondeserializationcontext);
        }

        public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonserializationcontext) {
            return this.a((CustomWorldSettingsFinal.CustomWorldSettings) object, type, jsonserializationcontext);
        }
    }

    public static class CustomWorldSettings {

        static final Gson a = (new GsonBuilder()).registerTypeAdapter(CustomWorldSettingsFinal.CustomWorldSettings.class, new CustomWorldSettingsFinal.CustomWorldSettingsSerializer()).create();
        public float b = 684.412F;
        public float c = 684.412F;
        public float d = 512.0F;
        public float e = 512.0F;
        public float f = 200.0F;
        public float g = 200.0F;
        public float h = 0.5F;
        public float i = 80.0F;
        public float j = 160.0F;
        public float k = 80.0F;
        public float l = 8.5F;
        public float m = 12.0F;
        public float n = 1.0F;
        public float o = 0.0F;
        public float p = 1.0F;
        public float q = 0.0F;
        public int r = 63;
        public boolean s = true;
        public boolean t = true;
        public int u = 8;
        public boolean v = true;
        public boolean w = true;
        public boolean x = true;
        public boolean y = true;
        public boolean z = true;
        public boolean A = true;
        public boolean B = true;
        public int C = 4;
        public boolean D = true;
        public int E = 80;
        public boolean F = false;
        public int G = -1;
        public int H = 4;
        public int I = 4;
        public int J = 33;
        public int K = 10;
        public int L = 0;
        public int M = 256;
        public int N = 33;
        public int O = 8;
        public int P = 0;
        public int Q = 256;
        public int R = 33;
        public int S = 10;
        public int T = 0;
        public int U = 80;
        public int V = 33;
        public int W = 10;
        public int X = 0;
        public int Y = 80;
        public int Z = 33;
        public int aa = 10;
        public int ab = 0;
        public int ac = 80;
        public int ad = 17;
        public int ae = 20;
        public int af = 0;
        public int ag = 128;
        public int ah = 9;
        public int ai = 20;
        public int aj = 0;
        public int ak = 64;
        public int al = 9;
        public int am = 2;
        public int an = 0;
        public int ao = 32;
        public int ap = 8;
        public int aq = 8;
        public int ar = 0;
        public int as = 16;
        public int at = 8;
        public int au = 1;
        public int av = 0;
        public int aw = 16;
        public int ax = 7;
        public int ay = 1;
        public int az = 16;
        public int aA = 16;

        public static CustomWorldSettingsFinal.CustomWorldSettings a(String s) {
            if (s.length() == 0) {
                return new CustomWorldSettingsFinal.CustomWorldSettings();
            } else {
                try {
                    return (CustomWorldSettingsFinal.CustomWorldSettings) CustomWorldSettingsFinal.CustomWorldSettings.a.fromJson(s, CustomWorldSettingsFinal.CustomWorldSettings.class);
                } catch (Exception exception) {
                    return new CustomWorldSettingsFinal.CustomWorldSettings();
                }
            }
        }

        public String toString() {
            return CustomWorldSettingsFinal.CustomWorldSettings.a.toJson(this);
        }

        public CustomWorldSettings() {
            this.a();
        }

        public void a() {
            this.b = 684.412F;
            this.c = 684.412F;
            this.d = 512.0F;
            this.e = 512.0F;
            this.f = 200.0F;
            this.g = 200.0F;
            this.h = 0.5F;
            this.i = 80.0F;
            this.j = 160.0F;
            this.k = 80.0F;
            this.l = 8.5F;
            this.m = 12.0F;
            this.n = 1.0F;
            this.o = 0.0F;
            this.p = 1.0F;
            this.q = 0.0F;
            this.r = 63;
            this.s = true;
            this.t = true;
            this.u = 8;
            this.v = true;
            this.w = true;
            this.x = true;
            this.y = true;
            this.z = true;
            this.A = true;
            this.B = true;
            this.C = 4;
            this.D = true;
            this.E = 80;
            this.F = false;
            this.G = -1;
            this.H = 4;
            this.I = 4;
            this.J = 33;
            this.K = 10;
            this.L = 0;
            this.M = 256;
            this.N = 33;
            this.O = 8;
            this.P = 0;
            this.Q = 256;
            this.R = 33;
            this.S = 10;
            this.T = 0;
            this.U = 80;
            this.V = 33;
            this.W = 10;
            this.X = 0;
            this.Y = 80;
            this.Z = 33;
            this.aa = 10;
            this.ab = 0;
            this.ac = 80;
            this.ad = 17;
            this.ae = 20;
            this.af = 0;
            this.ag = 128;
            this.ah = 9;
            this.ai = 20;
            this.aj = 0;
            this.ak = 64;
            this.al = 9;
            this.am = 2;
            this.an = 0;
            this.ao = 32;
            this.ap = 8;
            this.aq = 8;
            this.ar = 0;
            this.as = 16;
            this.at = 8;
            this.au = 1;
            this.av = 0;
            this.aw = 16;
            this.ax = 7;
            this.ay = 1;
            this.az = 16;
            this.aA = 16;
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            } else if (object != null && this.getClass() == object.getClass()) {
                CustomWorldSettingsFinal.CustomWorldSettings customworldsettingsfinal_customworldsettings = (CustomWorldSettingsFinal.CustomWorldSettings) object;

                return this.aa != customworldsettingsfinal_customworldsettings.aa ? false : (this.ac != customworldsettingsfinal_customworldsettings.ac ? false : (this.ab != customworldsettingsfinal_customworldsettings.ab ? false : (this.Z != customworldsettingsfinal_customworldsettings.Z ? false : (Float.compare(customworldsettingsfinal_customworldsettings.l, this.l) != 0 ? false : (Float.compare(customworldsettingsfinal_customworldsettings.o, this.o) != 0 ? false : (Float.compare(customworldsettingsfinal_customworldsettings.n, this.n) != 0 ? false : (Float.compare(customworldsettingsfinal_customworldsettings.q, this.q) != 0 ? false : (Float.compare(customworldsettingsfinal_customworldsettings.p, this.p) != 0 ? false : (this.H != customworldsettingsfinal_customworldsettings.H ? false : (this.ae != customworldsettingsfinal_customworldsettings.ae ? false : (this.ag != customworldsettingsfinal_customworldsettings.ag ? false : (this.af != customworldsettingsfinal_customworldsettings.af ? false : (this.ad != customworldsettingsfinal_customworldsettings.ad ? false : (Float.compare(customworldsettingsfinal_customworldsettings.b, this.b) != 0 ? false : (Float.compare(customworldsettingsfinal_customworldsettings.h, this.h) != 0 ? false : (Float.compare(customworldsettingsfinal_customworldsettings.f, this.f) != 0 ? false : (Float.compare(customworldsettingsfinal_customworldsettings.g, this.g) != 0 ? false : (this.au != customworldsettingsfinal_customworldsettings.au ? false : (this.aw != customworldsettingsfinal_customworldsettings.aw ? false : (this.av != customworldsettingsfinal_customworldsettings.av ? false : (this.at != customworldsettingsfinal_customworldsettings.at ? false : (this.W != customworldsettingsfinal_customworldsettings.W ? false : (this.Y != customworldsettingsfinal_customworldsettings.Y ? false : (this.X != customworldsettingsfinal_customworldsettings.X ? false : (this.V != customworldsettingsfinal_customworldsettings.V ? false : (this.K != customworldsettingsfinal_customworldsettings.K ? false : (this.M != customworldsettingsfinal_customworldsettings.M ? false : (this.L != customworldsettingsfinal_customworldsettings.L ? false : (this.J != customworldsettingsfinal_customworldsettings.J ? false : (this.u != customworldsettingsfinal_customworldsettings.u ? false : (this.G != customworldsettingsfinal_customworldsettings.G ? false : (this.am != customworldsettingsfinal_customworldsettings.am ? false : (this.ao != customworldsettingsfinal_customworldsettings.ao ? false : (this.an != customworldsettingsfinal_customworldsettings.an ? false : (this.al != customworldsettingsfinal_customworldsettings.al ? false : (this.S != customworldsettingsfinal_customworldsettings.S ? false : (this.U != customworldsettingsfinal_customworldsettings.U ? false : (this.T != customworldsettingsfinal_customworldsettings.T ? false : (this.R != customworldsettingsfinal_customworldsettings.R ? false : (this.O != customworldsettingsfinal_customworldsettings.O ? false : (this.Q != customworldsettingsfinal_customworldsettings.Q ? false : (this.P != customworldsettingsfinal_customworldsettings.P ? false : (this.N != customworldsettingsfinal_customworldsettings.N ? false : (Float.compare(customworldsettingsfinal_customworldsettings.c, this.c) != 0 ? false : (this.ai != customworldsettingsfinal_customworldsettings.ai ? false : (this.ak != customworldsettingsfinal_customworldsettings.ak ? false : (this.aj != customworldsettingsfinal_customworldsettings.aj ? false : (this.ah != customworldsettingsfinal_customworldsettings.ah ? false : (this.az != customworldsettingsfinal_customworldsettings.az ? false : (this.ay != customworldsettingsfinal_customworldsettings.ay ? false : (this.ax != customworldsettingsfinal_customworldsettings.ax ? false : (this.aA != customworldsettingsfinal_customworldsettings.aA ? false : (this.E != customworldsettingsfinal_customworldsettings.E ? false : (Float.compare(customworldsettingsfinal_customworldsettings.e, this.e) != 0 ? false : (Float.compare(customworldsettingsfinal_customworldsettings.i, this.i) != 0 ? false : (Float.compare(customworldsettingsfinal_customworldsettings.j, this.j) != 0 ? false : (Float.compare(customworldsettingsfinal_customworldsettings.k, this.k) != 0 ? false : (this.aq != customworldsettingsfinal_customworldsettings.aq ? false : (this.as != customworldsettingsfinal_customworldsettings.as ? false : (this.ar != customworldsettingsfinal_customworldsettings.ar ? false : (this.ap != customworldsettingsfinal_customworldsettings.ap ? false : (this.I != customworldsettingsfinal_customworldsettings.I ? false : (this.r != customworldsettingsfinal_customworldsettings.r ? false : (Float.compare(customworldsettingsfinal_customworldsettings.m, this.m) != 0 ? false : (Float.compare(customworldsettingsfinal_customworldsettings.d, this.d) != 0 ? false : (this.s != customworldsettingsfinal_customworldsettings.s ? false : (this.t != customworldsettingsfinal_customworldsettings.t ? false : (this.D != customworldsettingsfinal_customworldsettings.D ? false : (this.F != customworldsettingsfinal_customworldsettings.F ? false : (this.x != customworldsettingsfinal_customworldsettings.x ? false : (this.A != customworldsettingsfinal_customworldsettings.A ? false : (this.v != customworldsettingsfinal_customworldsettings.v ? false : (this.y != customworldsettingsfinal_customworldsettings.y ? false : (this.z != customworldsettingsfinal_customworldsettings.z ? false : (this.w != customworldsettingsfinal_customworldsettings.w ? false : (this.B != customworldsettingsfinal_customworldsettings.B ? false : this.C == customworldsettingsfinal_customworldsettings.C))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))));
            } else {
                return false;
            }
        }

        public int hashCode() {
            int i = this.b != 0.0F ? Float.floatToIntBits(this.b) : 0;

            i = 31 * i + (this.c != 0.0F ? Float.floatToIntBits(this.c) : 0);
            i = 31 * i + (this.d != 0.0F ? Float.floatToIntBits(this.d) : 0);
            i = 31 * i + (this.e != 0.0F ? Float.floatToIntBits(this.e) : 0);
            i = 31 * i + (this.f != 0.0F ? Float.floatToIntBits(this.f) : 0);
            i = 31 * i + (this.g != 0.0F ? Float.floatToIntBits(this.g) : 0);
            i = 31 * i + (this.h != 0.0F ? Float.floatToIntBits(this.h) : 0);
            i = 31 * i + (this.i != 0.0F ? Float.floatToIntBits(this.i) : 0);
            i = 31 * i + (this.j != 0.0F ? Float.floatToIntBits(this.j) : 0);
            i = 31 * i + (this.k != 0.0F ? Float.floatToIntBits(this.k) : 0);
            i = 31 * i + (this.l != 0.0F ? Float.floatToIntBits(this.l) : 0);
            i = 31 * i + (this.m != 0.0F ? Float.floatToIntBits(this.m) : 0);
            i = 31 * i + (this.n != 0.0F ? Float.floatToIntBits(this.n) : 0);
            i = 31 * i + (this.o != 0.0F ? Float.floatToIntBits(this.o) : 0);
            i = 31 * i + (this.p != 0.0F ? Float.floatToIntBits(this.p) : 0);
            i = 31 * i + (this.q != 0.0F ? Float.floatToIntBits(this.q) : 0);
            i = 31 * i + this.r;
            i = 31 * i + (this.s ? 1 : 0);
            i = 31 * i + (this.t ? 1 : 0);
            i = 31 * i + this.u;
            i = 31 * i + (this.v ? 1 : 0);
            i = 31 * i + (this.w ? 1 : 0);
            i = 31 * i + (this.x ? 1 : 0);
            i = 31 * i + (this.y ? 1 : 0);
            i = 31 * i + (this.z ? 1 : 0);
            i = 31 * i + (this.A ? 1 : 0);
            i = 31 * i + (this.B ? 1 : 0);
            i = 31 * i + this.C;
            i = 31 * i + (this.D ? 1 : 0);
            i = 31 * i + this.E;
            i = 31 * i + (this.F ? 1 : 0);
            i = 31 * i + this.G;
            i = 31 * i + this.H;
            i = 31 * i + this.I;
            i = 31 * i + this.J;
            i = 31 * i + this.K;
            i = 31 * i + this.L;
            i = 31 * i + this.M;
            i = 31 * i + this.N;
            i = 31 * i + this.O;
            i = 31 * i + this.P;
            i = 31 * i + this.Q;
            i = 31 * i + this.R;
            i = 31 * i + this.S;
            i = 31 * i + this.T;
            i = 31 * i + this.U;
            i = 31 * i + this.V;
            i = 31 * i + this.W;
            i = 31 * i + this.X;
            i = 31 * i + this.Y;
            i = 31 * i + this.Z;
            i = 31 * i + this.aa;
            i = 31 * i + this.ab;
            i = 31 * i + this.ac;
            i = 31 * i + this.ad;
            i = 31 * i + this.ae;
            i = 31 * i + this.af;
            i = 31 * i + this.ag;
            i = 31 * i + this.ah;
            i = 31 * i + this.ai;
            i = 31 * i + this.aj;
            i = 31 * i + this.ak;
            i = 31 * i + this.al;
            i = 31 * i + this.am;
            i = 31 * i + this.an;
            i = 31 * i + this.ao;
            i = 31 * i + this.ap;
            i = 31 * i + this.aq;
            i = 31 * i + this.ar;
            i = 31 * i + this.as;
            i = 31 * i + this.at;
            i = 31 * i + this.au;
            i = 31 * i + this.av;
            i = 31 * i + this.aw;
            i = 31 * i + this.ax;
            i = 31 * i + this.ay;
            i = 31 * i + this.az;
            i = 31 * i + this.aA;
            return i;
        }

        public CustomWorldSettingsFinal b() {
            return new CustomWorldSettingsFinal(this, (CustomWorldSettingsFinal.SyntheticClass_1) null);
        }
    }
}
