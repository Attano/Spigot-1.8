package net.minecraft.server;

public class ChatModifier {

    private ChatModifier a;
    private EnumChatFormat b;
    private Boolean c;
    private Boolean d;
    private Boolean e;
    private Boolean f;
    private Boolean g;
    private ChatClickable h;
    private ChatHoverable i;
    private String j;
    private static final ChatModifier k = new ChatStyleRoot();

    public ChatModifier() {}

    public EnumChatFormat getColor() {
        return this.b == null ? this.o().getColor() : this.b;
    }

    public boolean isBold() {
        return this.c == null ? this.o().isBold() : this.c.booleanValue();
    }

    public boolean isItalic() {
        return this.d == null ? this.o().isItalic() : this.d.booleanValue();
    }

    public boolean isStrikethrough() {
        return this.f == null ? this.o().isStrikethrough() : this.f.booleanValue();
    }

    public boolean isUnderlined() {
        return this.e == null ? this.o().isUnderlined() : this.e.booleanValue();
    }

    public boolean isRandom() {
        return this.g == null ? this.o().isRandom() : this.g.booleanValue();
    }

    public boolean g() {
        return this.c == null && this.d == null && this.f == null && this.e == null && this.g == null && this.b == null && this.h == null && this.i == null;
    }

    public ChatClickable h() {
        return this.h == null ? this.o().h() : this.h;
    }

    public ChatHoverable i() {
        return this.i == null ? this.o().i() : this.i;
    }

    public String j() {
        return this.j == null ? this.o().j() : this.j;
    }

    public ChatModifier setColor(EnumChatFormat enumchatformat) {
        this.b = enumchatformat;
        return this;
    }

    public ChatModifier setBold(Boolean obool) {
        this.c = obool;
        return this;
    }

    public ChatModifier setItalic(Boolean obool) {
        this.d = obool;
        return this;
    }

    public ChatModifier setStrikethrough(Boolean obool) {
        this.f = obool;
        return this;
    }

    public ChatModifier setUnderline(Boolean obool) {
        this.e = obool;
        return this;
    }

    public ChatModifier setRandom(Boolean obool) {
        this.g = obool;
        return this;
    }

    public ChatModifier setChatClickable(ChatClickable chatclickable) {
        this.h = chatclickable;
        return this;
    }

    public ChatModifier setChatHoverable(ChatHoverable chathoverable) {
        this.i = chathoverable;
        return this;
    }

    public ChatModifier setInsertion(String s) {
        this.j = s;
        return this;
    }

    public ChatModifier setChatModifier(ChatModifier chatmodifier) {
        this.a = chatmodifier;
        return this;
    }

    private ChatModifier o() {
        return this.a == null ? ChatModifier.k : this.a;
    }

    public String toString() {
        return "Style{hasParent=" + (this.a != null) + ", color=" + this.b + ", bold=" + this.c + ", italic=" + this.d + ", underlined=" + this.e + ", obfuscated=" + this.g + ", clickEvent=" + this.h() + ", hoverEvent=" + this.i() + ", insertion=" + this.j() + '}';
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ChatModifier)) {
            return false;
        } else {
            ChatModifier chatmodifier = (ChatModifier) object;
            boolean flag;

            if (this.isBold() == chatmodifier.isBold() && this.getColor() == chatmodifier.getColor() && this.isItalic() == chatmodifier.isItalic() && this.isRandom() == chatmodifier.isRandom() && this.isStrikethrough() == chatmodifier.isStrikethrough() && this.isUnderlined() == chatmodifier.isUnderlined()) {
                label65: {
                    if (this.h() != null) {
                        if (!this.h().equals(chatmodifier.h())) {
                            break label65;
                        }
                    } else if (chatmodifier.h() != null) {
                        break label65;
                    }

                    if (this.i() != null) {
                        if (!this.i().equals(chatmodifier.i())) {
                            break label65;
                        }
                    } else if (chatmodifier.i() != null) {
                        break label65;
                    }

                    if (this.j() != null) {
                        if (!this.j().equals(chatmodifier.j())) {
                            break label65;
                        }
                    } else if (chatmodifier.j() != null) {
                        break label65;
                    }

                    flag = true;
                    return flag;
                }
            }

            flag = false;
            return flag;
        }
    }

    public int hashCode() {
        int i = this.b.hashCode();

        i = 31 * i + this.c.hashCode();
        i = 31 * i + this.d.hashCode();
        i = 31 * i + this.e.hashCode();
        i = 31 * i + this.f.hashCode();
        i = 31 * i + this.g.hashCode();
        i = 31 * i + this.h.hashCode();
        i = 31 * i + this.i.hashCode();
        i = 31 * i + this.j.hashCode();
        return i;
    }

    public ChatModifier clone() {
        ChatModifier chatmodifier = new ChatModifier();

        chatmodifier.c = this.c;
        chatmodifier.d = this.d;
        chatmodifier.f = this.f;
        chatmodifier.e = this.e;
        chatmodifier.g = this.g;
        chatmodifier.b = this.b;
        chatmodifier.h = this.h;
        chatmodifier.i = this.i;
        chatmodifier.a = this.a;
        chatmodifier.j = this.j;
        return chatmodifier;
    }

    public ChatModifier n() {
        ChatModifier chatmodifier = new ChatModifier();

        chatmodifier.setBold(Boolean.valueOf(this.isBold()));
        chatmodifier.setItalic(Boolean.valueOf(this.isItalic()));
        chatmodifier.setStrikethrough(Boolean.valueOf(this.isStrikethrough()));
        chatmodifier.setUnderline(Boolean.valueOf(this.isUnderlined()));
        chatmodifier.setRandom(Boolean.valueOf(this.isRandom()));
        chatmodifier.setColor(this.getColor());
        chatmodifier.setChatClickable(this.h());
        chatmodifier.setChatHoverable(this.i());
        chatmodifier.setInsertion(this.j());
        return chatmodifier;
    }

    static Boolean a(ChatModifier chatmodifier, Boolean obool) {
        return chatmodifier.c = obool;
    }

    static Boolean b(ChatModifier chatmodifier, Boolean obool) {
        return chatmodifier.d = obool;
    }

    static Boolean c(ChatModifier chatmodifier, Boolean obool) {
        return chatmodifier.e = obool;
    }

    static Boolean d(ChatModifier chatmodifier, Boolean obool) {
        return chatmodifier.f = obool;
    }

    static Boolean e(ChatModifier chatmodifier, Boolean obool) {
        return chatmodifier.g = obool;
    }

    static EnumChatFormat a(ChatModifier chatmodifier, EnumChatFormat enumchatformat) {
        return chatmodifier.b = enumchatformat;
    }

    static String a(ChatModifier chatmodifier, String s) {
        return chatmodifier.j = s;
    }

    static ChatClickable a(ChatModifier chatmodifier, ChatClickable chatclickable) {
        return chatmodifier.h = chatclickable;
    }

    static ChatHoverable a(ChatModifier chatmodifier, ChatHoverable chathoverable) {
        return chatmodifier.i = chathoverable;
    }

    static Boolean b(ChatModifier chatmodifier) {
        return chatmodifier.c;
    }

    static Boolean c(ChatModifier chatmodifier) {
        return chatmodifier.d;
    }

    static Boolean d(ChatModifier chatmodifier) {
        return chatmodifier.e;
    }

    static Boolean e(ChatModifier chatmodifier) {
        return chatmodifier.f;
    }

    static Boolean f(ChatModifier chatmodifier) {
        return chatmodifier.g;
    }

    static EnumChatFormat g(ChatModifier chatmodifier) {
        return chatmodifier.b;
    }

    static String h(ChatModifier chatmodifier) {
        return chatmodifier.j;
    }

    static ChatClickable i(ChatModifier chatmodifier) {
        return chatmodifier.h;
    }

    static ChatHoverable j(ChatModifier chatmodifier) {
        return chatmodifier.i;
    }
}
