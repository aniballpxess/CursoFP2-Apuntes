package edu.dam2.ad.ejercicios.emojiapi;

public class Emoji {
    private String character;
    private String name;
    private String group;
    private String subGroup;

    public Emoji(String character, String name, String group, String subGroup) {
        this.character = character;
        this.name = name;
        this.group = group;
        this.subGroup = subGroup;
    }

    public String getCharacter() { return character; }
    public String getName() { return name; }
    public String getGroup() { return group; }
    public String getSubGroup() { return subGroup; }

    @Override
    public String toString() {
        return character;
    }
}
