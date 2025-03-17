package se459.rogue_data;

public class Potion extends Item {
    private int healthRestore;

    public Potion(String name, int x, int y, int healthRestore) {
        super(name, x, y);
        this.healthRestore = healthRestore;
    }

    public int getHealthRestore() {
        return healthRestore;
    }
}
