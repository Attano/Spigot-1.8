package net.minecraft.server;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import java.util.regex.Pattern;

class MojangsonPrimitiveParser extends MojangsonTypeParser {

    private static final Pattern c = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[d|D]");
    private static final Pattern d = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[f|F]");
    private static final Pattern e = Pattern.compile("[-+]?[0-9]+[b|B]");
    private static final Pattern f = Pattern.compile("[-+]?[0-9]+[l|L]");
    private static final Pattern g = Pattern.compile("[-+]?[0-9]+[s|S]");
    private static final Pattern h = Pattern.compile("[-+]?[0-9]+");
    private static final Pattern i = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
    private static final Splitter j = Splitter.on(',').omitEmptyStrings();
    protected String b;

    public MojangsonPrimitiveParser(String s, String s1) {
        this.a = s;
        this.b = s1;
    }

    public NBTBase a() {
        try {
            if (MojangsonPrimitiveParser.c.matcher(this.b).matches()) {
                return new NBTTagDouble(Double.parseDouble(this.b.substring(0, this.b.length() - 1)));
            }

            if (MojangsonPrimitiveParser.d.matcher(this.b).matches()) {
                return new NBTTagFloat(Float.parseFloat(this.b.substring(0, this.b.length() - 1)));
            }

            if (MojangsonPrimitiveParser.e.matcher(this.b).matches()) {
                return new NBTTagByte(Byte.parseByte(this.b.substring(0, this.b.length() - 1)));
            }

            if (MojangsonPrimitiveParser.f.matcher(this.b).matches()) {
                return new NBTTagLong(Long.parseLong(this.b.substring(0, this.b.length() - 1)));
            }

            if (MojangsonPrimitiveParser.g.matcher(this.b).matches()) {
                return new NBTTagShort(Short.parseShort(this.b.substring(0, this.b.length() - 1)));
            }

            if (MojangsonPrimitiveParser.h.matcher(this.b).matches()) {
                return new NBTTagInt(Integer.parseInt(this.b));
            }

            if (MojangsonPrimitiveParser.i.matcher(this.b).matches()) {
                return new NBTTagDouble(Double.parseDouble(this.b));
            }

            if (this.b.equalsIgnoreCase("true") || this.b.equalsIgnoreCase("false")) {
                return new NBTTagByte((byte) (Boolean.parseBoolean(this.b) ? 1 : 0));
            }
        } catch (NumberFormatException numberformatexception) {
            this.b = this.b.replaceAll("\\\\\"", "\"");
            return new NBTTagString(this.b);
        }

        if (this.b.startsWith("[") && this.b.endsWith("]")) {
            String s = this.b.substring(1, this.b.length() - 1);
            String[] astring = (String[]) Iterables.toArray(MojangsonPrimitiveParser.j.split(s), String.class);

            try {
                int[] aint = new int[astring.length];

                for (int i = 0; i < astring.length; ++i) {
                    aint[i] = Integer.parseInt(astring[i].trim());
                }

                return new NBTTagIntArray(aint);
            } catch (NumberFormatException numberformatexception1) {
                return new NBTTagString(this.b);
            }
        } else {
            if (this.b.startsWith("\"") && this.b.endsWith("\"")) {
                this.b = this.b.substring(1, this.b.length() - 1);
            }

            this.b = this.b.replaceAll("\\\\\"", "\"");
            StringBuilder stringbuilder = new StringBuilder();

            for (int j = 0; j < this.b.length(); ++j) {
                if (j < this.b.length() - 1 && this.b.charAt(j) == 92 && this.b.charAt(j + 1) == 92) {
                    stringbuilder.append('\\');
                    ++j;
                } else {
                    stringbuilder.append(this.b.charAt(j));
                }
            }

            return new NBTTagString(stringbuilder.toString());
        }
    }
}
