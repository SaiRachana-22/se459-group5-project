package se459.rogue_data;
 
public class Weapon extends Item {
    private int attackPower;
 
    public Weapon(String name, int x, int y, int attackPower) {
        super(name, x, y);
        this.attackPower = attackPower;
    }
 
    public int getAttackPower() {
        return attackPower;
    }
}
