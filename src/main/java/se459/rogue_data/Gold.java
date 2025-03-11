package se459.rogue_data;

public class Gold extends Item {
    private int amount;

    public Gold(int amount, int x, int y) {
        super("Gold", x, y);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
