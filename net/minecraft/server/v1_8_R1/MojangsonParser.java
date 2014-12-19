package net.minecraft.server;

import java.util.Stack;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MojangsonParser {

    private static final Logger a = LogManager.getLogger();
    private static final Pattern b = Pattern.compile("\\[[-+\\d|,\\s]+\\]");

    public static NBTTagCompound parse(String s) {
        s = s.trim();
        if (!s.startsWith("{")) {
            throw new MojangsonParseException("Invalid tag encountered, expected \'{\' as first char.");
        } else if (b(s) != 1) {
            throw new MojangsonParseException("Encountered multiple top tags, only one expected");
        } else {
            return (NBTTagCompound) a("tag", s).a();
        }
    }

    static int b(String s) {
        int i = 0;
        boolean flag = false;
        Stack stack = new Stack();

        for (int j = 0; j < s.length(); ++j) {
            char c0 = s.charAt(j);

            if (c0 == 34) {
                if (b(s, j)) {
                    if (!flag) {
                        throw new MojangsonParseException("Illegal use of \\\": " + s);
                    }
                } else {
                    flag = !flag;
                }
            } else if (!flag) {
                if (c0 != 123 && c0 != 91) {
                    if (c0 == 125 && (stack.isEmpty() || ((Character) stack.pop()).charValue() != 123)) {
                        throw new MojangsonParseException("Unbalanced curly brackets {}: " + s);
                    }

                    if (c0 == 93 && (stack.isEmpty() || ((Character) stack.pop()).charValue() != 91)) {
                        throw new MojangsonParseException("Unbalanced square brackets []: " + s);
                    }
                } else {
                    if (stack.isEmpty()) {
                        ++i;
                    }

                    stack.push(Character.valueOf(c0));
                }
            }
        }

        if (flag) {
            throw new MojangsonParseException("Unbalanced quotation: " + s);
        } else if (!stack.isEmpty()) {
            throw new MojangsonParseException("Unbalanced brackets: " + s);
        } else {
            if (i == 0 && !s.isEmpty()) {
                i = 1;
            }

            return i;
        }
    }

    static MojangsonTypeParser a(String... astring) {
        return a(astring[0], astring[1]);
    }

    static MojangsonTypeParser a(String s, String s1) {
        s1 = s1.trim();
        String s2;
        boolean flag;
        char c0;

        if (s1.startsWith("{")) {
            s1 = s1.substring(1, s1.length() - 1);

            MojangsonCompoundParser mojangsoncompoundparser;

            for (mojangsoncompoundparser = new MojangsonCompoundParser(s); s1.length() > 0; s1 = s1.substring(s2.length() + 1)) {
                s2 = b(s1, true);
                if (s2.length() > 0) {
                    flag = false;
                    mojangsoncompoundparser.b.add(a(s2, flag));
                }

                if (s1.length() < s2.length() + 1) {
                    break;
                }

                c0 = s1.charAt(s2.length());
                if (c0 != 44 && c0 != 123 && c0 != 125 && c0 != 91 && c0 != 93) {
                    throw new MojangsonParseException("Unexpected token \'" + c0 + "\' at: " + s1.substring(s2.length()));
                }
            }

            return mojangsoncompoundparser;
        } else if (s1.startsWith("[") && !MojangsonParser.b.matcher(s1).matches()) {
            s1 = s1.substring(1, s1.length() - 1);

            MojangsonListParser mojangsonlistparser;

            for (mojangsonlistparser = new MojangsonListParser(s); s1.length() > 0; s1 = s1.substring(s2.length() + 1)) {
                s2 = b(s1, false);
                if (s2.length() > 0) {
                    flag = true;
                    mojangsonlistparser.b.add(a(s2, flag));
                }

                if (s1.length() < s2.length() + 1) {
                    break;
                }

                c0 = s1.charAt(s2.length());
                if (c0 != 44 && c0 != 123 && c0 != 125 && c0 != 91 && c0 != 93) {
                    throw new MojangsonParseException("Unexpected token \'" + c0 + "\' at: " + s1.substring(s2.length()));
                }
            }

            return mojangsonlistparser;
        } else {
            return new MojangsonPrimitiveParser(s, s1);
        }
    }

    private static MojangsonTypeParser a(String s, boolean flag) {
        String s1 = c(s, flag);
        String s2 = d(s, flag);

        return a(new String[] { s1, s2});
    }

    private static String b(String s, boolean flag) {
        int i = a(s, ':');
        int j = a(s, ',');

        if (flag) {
            if (i == -1) {
                throw new MojangsonParseException("Unable to locate name/value separator for string: " + s);
            }

            if (j != -1 && j < i) {
                throw new MojangsonParseException("Name error at: " + s);
            }
        } else if (i == -1 || i > j) {
            i = -1;
        }

        return a(s, i);
    }

    private static String a(String s, int i) {
        Stack stack = new Stack();
        int j = i + 1;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;

        for (int k = 0; j < s.length(); ++j) {
            char c0 = s.charAt(j);

            if (c0 == 34) {
                if (b(s, j)) {
                    if (!flag) {
                        throw new MojangsonParseException("Illegal use of \\\": " + s);
                    }
                } else {
                    flag = !flag;
                    if (flag && !flag2) {
                        flag1 = true;
                    }

                    if (!flag) {
                        k = j;
                    }
                }
            } else if (!flag) {
                if (c0 != 123 && c0 != 91) {
                    if (c0 == 125 && (stack.isEmpty() || ((Character) stack.pop()).charValue() != 123)) {
                        throw new MojangsonParseException("Unbalanced curly brackets {}: " + s);
                    }

                    if (c0 == 93 && (stack.isEmpty() || ((Character) stack.pop()).charValue() != 91)) {
                        throw new MojangsonParseException("Unbalanced square brackets []: " + s);
                    }

                    if (c0 == 44 && stack.isEmpty()) {
                        return s.substring(0, j);
                    }
                } else {
                    stack.push(Character.valueOf(c0));
                }
            }

            if (!Character.isWhitespace(c0)) {
                if (!flag && flag1 && k != j) {
                    return s.substring(0, k + 1);
                }

                flag2 = true;
            }
        }

        return s.substring(0, j);
    }

    private static String c(String s, boolean flag) {
        if (flag) {
            s = s.trim();
            if (s.startsWith("{") || s.startsWith("[")) {
                return "";
            }
        }

        int i = a(s, ':');

        if (i == -1) {
            if (flag) {
                return "";
            } else {
                throw new MojangsonParseException("Unable to locate name/value separator for string: " + s);
            }
        } else {
            return s.substring(0, i).trim();
        }
    }

    private static String d(String s, boolean flag) {
        if (flag) {
            s = s.trim();
            if (s.startsWith("{") || s.startsWith("[")) {
                return s;
            }
        }

        int i = a(s, ':');

        if (i == -1) {
            if (flag) {
                return s;
            } else {
                throw new MojangsonParseException("Unable to locate name/value separator for string: " + s);
            }
        } else {
            return s.substring(i + 1).trim();
        }
    }

    private static int a(String s, char c0) {
        int i = 0;

        for (boolean flag = true; i < s.length(); ++i) {
            char c1 = s.charAt(i);

            if (c1 == 34) {
                if (!b(s, i)) {
                    flag = !flag;
                }
            } else if (flag) {
                if (c1 == c0) {
                    return i;
                }

                if (c1 == 123 || c1 == 91) {
                    return -1;
                }
            }
        }

        return -1;
    }

    private static boolean b(String s, int i) {
        return i > 0 && s.charAt(i - 1) == 92 && !b(s, i - 1);
    }
}
