package edu.dam2.ad.ejercicios.emojiapi;

public class Emoji {
    private String character;
    private String name;
    private String slug;

    public Emoji(String character, String name, String slug) {
        this.character = character;
        this.name = name;
        this.slug = slug;
    }

    public String getCharacter() { return character; }
    public String getName() { return name; }
    public String getSlug() { return slug; }

    @Override
    public String toString() {
        return character + " - " + name;
    }
}
