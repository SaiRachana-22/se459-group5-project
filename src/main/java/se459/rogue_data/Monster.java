package se459.rogue_data;

public class Monster {
    private int x;
    private int y;
    private String name;
    private int health;
    private int attackPower;

    public Monster(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name; // Set the name
        this.health = 10; // Example health value
        this.attackPower = 2; // Example attack power value
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }
}