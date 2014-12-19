package net.minecraft.server;

final class ChatStyleRoot extends ChatModifier {

    ChatStyleRoot() {}

    public EnumChatFormat getColor() {
        return null;
    }

    public boolean isBold() {
        return false;
    }

    public boolean isItalic() {
        return false;
    }

    public boolean isStrikethrough() {
        return false;
    }

    public boolean isUnderlined() {
        return false;
    }

    public boolean isRandom() {
        return false;
    }

    public ChatClickable h() {
        return null;
    }

    public ChatHoverable i() {
        return null;
    }

    public String j() {
        return null;
    }

    public ChatModifier setColor(EnumChatFormat enumchatformat) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier setBold(Boolean obool) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier setItalic(Boolean obool) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier setStrikethrough(Boolean obool) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier setUnderline(Boolean obool) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier setRandom(Boolean obool) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier setChatClickable(ChatClickable chatclickable) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier setChatHoverable(ChatHoverable chathoverable) {
        throw new UnsupportedOperationException();
    }

    public ChatModifier setChatModifier(ChatModifier chatmodifier) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return "Style.ROOT";
    }

    public ChatModifier clone() {
        return this;
    }

    public ChatModifier n() {
        return this;
    }
}
