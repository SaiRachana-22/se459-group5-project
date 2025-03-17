package se459.rogue_data;

public class Scroll extends Item {
    private String effect;

    public Scroll(String name, int x, int y, String effect) {
        super(name, x, y);
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }
}