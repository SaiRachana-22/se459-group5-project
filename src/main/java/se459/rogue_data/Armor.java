package se459.rogue_data;
 
public class Armor extends Item {
    private int defense;
 
    public Armor(String name, int x, int y, int defense) {
        super(name, x, y);
        this.defense = defense;
    }
 
    public int getDefense() {
        return defense;
    }
}
