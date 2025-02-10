package se459.rogue_data;
 
public class Player {
 
    private int x, y;
 
    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }
 
    public String move(char direction, Dungeon dungeon) {
        int newX = x, newY = y;
 
        switch (direction) {
            case 'w': newY--; break;
            case 's': newY++; break;
            case 'a': newX--; break;
            case 'd': newX++; break;
            default: return "Invalid move!";
        }
 
        char tile = dungeon.getTile(newX, newY);
        if (tile == '.' || tile == ' ') { // Walkable floor or passage
            x = newX;
            y = newY;
            return switch (direction) {
                case 'w' -> "You move up.";
                case 's' -> "You move down.";
                case 'a' -> "You move left.";
                case 'd' -> "You move right.";
                default -> "";
            };
        } else {
            return "You hit a wall!";
        }
    }
 
    public int getX() { return x; }
    public int getY() { return y; }
}
 