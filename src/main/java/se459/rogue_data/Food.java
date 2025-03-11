package se459.rogue_data;
 
public class Food extends Item {
    private int hungerRestore;
 
    public Food(String name, int x, int y, int hungerRestore) {
        super(name, x, y);
        this.hungerRestore = hungerRestore;
    }
 
    public int getHungerRestore() {
        return hungerRestore;
    }
}
