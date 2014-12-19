package net.minecraft.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CustomWorldSettings {

    static final Gson a = (new GsonBuilder()).registerTypeAdapter(CustomWorldSettings.class, new CustomWorldSettingsSerializer()).create();
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

    public static CustomWorldSettings a(String s) {
        if (s.length() == 0) {
            return new CustomWorldSettings();
        } else {
            try {
                return (CustomWorldSettings) CustomWorldSettings.a.fromJson(s, CustomWorldSettings.class);
            } catch (Exception exception) {
                return new CustomWorldSettings();
            }
        }
    }

    public String toString() {
        return CustomWorldSettings.a.toJson(this);
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
            CustomWorldSettings customworldsettings = (CustomWorldSettings) object;

            return this.aa != customworldsettings.aa ? false : (this.ac != customworldsettings.ac ? false : (this.ab != customworldsettings.ab ? false : (this.Z != customworldsettings.Z ? false : (Float.compare(customworldsettings.l, this.l) != 0 ? false : (Float.compare(customworldsettings.o, this.o) != 0 ? false : (Float.compare(customworldsettings.n, this.n) != 0 ? false : (Float.compare(customworldsettings.q, this.q) != 0 ? false : (Float.compare(customworldsettings.p, this.p) != 0 ? false : (this.H != customworldsettings.H ? false : (this.ae != customworldsettings.ae ? false : (this.ag != customworldsettings.ag ? false : (this.af != customworldsettings.af ? false : (this.ad != customworldsettings.ad ? false : (Float.compare(customworldsettings.b, this.b) != 0 ? false : (Float.compare(customworldsettings.h, this.h) != 0 ? false : (Float.compare(customworldsettings.f, this.f) != 0 ? false : (Float.compare(customworldsettings.g, this.g) != 0 ? false : (this.au != customworldsettings.au ? false : (this.aw != customworldsettings.aw ? false : (this.av != customworldsettings.av ? false : (this.at != customworldsettings.at ? false : (this.W != customworldsettings.W ? false : (this.Y != customworldsettings.Y ? false : (this.X != customworldsettings.X ? false : (this.V != customworldsettings.V ? false : (this.K != customworldsettings.K ? false : (this.M != customworldsettings.M ? false : (this.L != customworldsettings.L ? false : (this.J != customworldsettings.J ? false : (this.u != customworldsettings.u ? false : (this.G != customworldsettings.G ? false : (this.am != customworldsettings.am ? false : (this.ao != customworldsettings.ao ? false : (this.an != customworldsettings.an ? false : (this.al != customworldsettings.al ? false : (this.S != customworldsettings.S ? false : (this.U != customworldsettings.U ? false : (this.T != customworldsettings.T ? false : (this.R != customworldsettings.R ? false : (this.O != customworldsettings.O ? false : (this.Q != customworldsettings.Q ? false : (this.P != customworldsettings.P ? false : (this.N != customworldsettings.N ? false : (Float.compare(customworldsettings.c, this.c) != 0 ? false : (this.ai != customworldsettings.ai ? false : (this.ak != customworldsettings.ak ? false : (this.aj != customworldsettings.aj ? false : (this.ah != customworldsettings.ah ? false : (this.az != customworldsettings.az ? false : (this.ay != customworldsettings.ay ? false : (this.ax != customworldsettings.ax ? false : (this.aA != customworldsettings.aA ? false : (this.E != customworldsettings.E ? false : (Float.compare(customworldsettings.e, this.e) != 0 ? false : (Float.compare(customworldsettings.i, this.i) != 0 ? false : (Float.compare(customworldsettings.j, this.j) != 0 ? false : (Float.compare(customworldsettings.k, this.k) != 0 ? false : (this.aq != customworldsettings.aq ? false : (this.as != customworldsettings.as ? false : (this.ar != customworldsettings.ar ? false : (this.ap != customworldsettings.ap ? false : (this.I != customworldsettings.I ? false : (this.r != customworldsettings.r ? false : (Float.compare(customworldsettings.m, this.m) != 0 ? false : (Float.compare(customworldsettings.d, this.d) != 0 ? false : (this.s != customworldsettings.s ? false : (this.t != customworldsettings.t ? false : (this.D != customworldsettings.D ? false : (this.F != customworldsettings.F ? false : (this.x != customworldsettings.x ? false : (this.A != customworldsettings.A ? false : (this.v != customworldsettings.v ? false : (this.y != customworldsettings.y ? false : (this.z != customworldsettings.z ? false : (this.w != customworldsettings.w ? false : (this.B != customworldsettings.B ? false : this.C == customworldsettings.C))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))));
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
        return new CustomWorldSettingsFinal(this, (EmptyClass4) null);
    }
}
