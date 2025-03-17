package se459.rogue_data;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int x, y;
    private int health;
    private int level;
    private int experience;
    private int hunger;
    private List<Item> pack;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int gold;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.health = 20; // Default health
        this.level = 1;
        this.experience = 0;
        this.hunger = 100;
        this.pack = new ArrayList<>();
        this.equippedWeapon = null;
        this.equippedArmor = null;
        this.gold = 0;
    }

    public String move(char direction, Dungeon dungeon) {
        int newX = x, newY = y;

        switch (direction) {
            case 'w':
                newY--;
                break; // Move up
            case 's':
                newY++;
                break; // Move down
            case 'a':
                newX--;
                break; // Move left
            case 'd':
                newX++;
                break; // Move right
            default:
                return "Invalid move!";
        }

        char tile = dungeon.getTile(newX, newY);

        // Check if the tile is walkable (floor or passage)
        if (tile == '.' || tile == ' ') {
            x = newX;
            y = newY;
            hunger--; // Decrease hunger on move
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
        return equippedWeapon != null ? equippedWeapon.getAttackPower() : 3;
    }

    public void takeDamage(int damage) {
        int reducedDamage = equippedArmor != null ? Math.max(damage - equippedArmor.getDefense(), 1) : damage;
        health -= reducedDamage;
    }

    public void pickUpItem(Item item) {
        if (item instanceof Gold) {
            gold += ((Gold) item).getAmount();
        } 
        else {
            pack.add(item);
            if (item instanceof Weapon) {
                equipWeapon((Weapon) item);
            }
            else if (item instanceof Armor) {
                equipArmor((Armor) item);
            }
        }
    }

    public void equipWeapon(Weapon weapon) {
        equippedWeapon = weapon;
    }

    public void equipArmor(Armor armor) {
        equippedArmor = armor;
    }

    public Food getFirstFood() {
        for (Item item : pack) {
            if (item instanceof Food) {
                return (Food) item;
            }
        }
        return null;
    }

    public void eatFood(Food food) {
        hunger = Math.min(hunger + food.getHungerRestore(), 100);
    }

    public boolean isStarving() {
        return hunger <= 0;
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
        return "HP: " + health + " | LVL: " + level + " | XP: " + experience + " | Hunger: " + hunger + " | Gold: "
                + gold;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

