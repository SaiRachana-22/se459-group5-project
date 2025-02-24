package se459.rogue_data;

public class Player {
    private int x, y;
    private int health;
    private int level;
    private int experience;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.health = 20; // Default health
        this.level = 1;
        this.experience = 0;
    }

    public String move(char direction, Dungeon dungeon) {
        int newX = x, newY = y;

        switch (direction) {
            case 'w': newY--; break; // Move up
            case 's': newY++; break; // Move down
            case 'a': newX--; break; // Move left
            case 'd': newX++; break; // Move right
            default: return "Invalid move!";
        }

        char tile = dungeon.getTile(newX, newY);
        
        // Check if the tile is walkable (floor or passage)
        if (tile == '.' || tile == ' ') {
            x = newX;
            y = newY;
            return switch (direction) {
                case 'w' -> "You move up.";
                case 's' -> "You move down.";
                case 'a' -> "You move left.";
                case 'd' -> "You move right.";
                default -> "";
            };
        } 
        // Allow movement into monster's position without hitting a wall
        else if (tile == 'E') { // Assuming 'E' is the monster symbol
            x = newX;
            y = newY;
            return "You moved into a monster's position!";
        }
        // If it's a wall, return hit wall message
        else if (tile == '#' || tile == '-' || tile == '|') {
            return "You hit a wall!";
        }
        
        return "You cannot move there!";
    }

    public int attack() {
        return 3; // Fixed damage for simplicity
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void gainExperience(int xp) {
        experience += xp;
        if (experience >= 10) {
            level++;
            experience = 0;
            System.out.println("You leveled up! Level: " + level);
        }
    }

    public String getStats() {
        return "Health: " + health + " | Level: " + level + " | Experience: " + experience;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}