package se459.rogue_data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dungeon {
    private final int width;
    private final int height;
    private char[][] grid;
    private Monster monster;
    private List<Item> items;
    private Random random;


    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];
        this.monster = null; // Initialize to null
        this.items = new ArrayList<>();
        this.random = new Random();
        generateRandomItems();

        // Initialize the dungeon with walls and empty spaces
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    grid[i][j] = '#'; // Walls
                } else {
                    grid[i][j] = ' '; // Empty space
                }
            }
        }
    }

    private void generateRandomItems() {
        items.add(new Weapon("Sword", 10, 12,5));
        items.add(new Armor("Leather Armor", 15, 13, 2));
        items.add(new Food("Bread", 11, 7, 20));
        items.add(new Gold(10, 17, 8));
    }

    // Add a monster at a specified position
    public void addMonster(Monster monster) {
        // Check if the position is valid (not a wall and within bounds)
        if (isValidPosition(monster.getX(), monster.getY())) {
            this.monster = monster;
            grid[monster.getY()][monster.getX()] = 'E'; // Monster symbol
        } else {
            System.out.println("Invalid position for monster!");
        }
    }

    // Get the single monster
    public Monster getMonster() {
        return monster; // Return the monster instance
    }

    public List<Item> getItems() {
        return items;
    }

    // Check if a position is valid (not a wall and within bounds)
    private boolean isValidPosition(int x, int y) {
        return x > 0 && x < width - 1 && y > 0 && y < height - 1 && grid[y][x] != '#';
    }

    public void removeMonster() {
        this.monster = null; // Remove the monster from the dungeon
    }

    public char getTile(int x, int y) {
        return grid[y][x];
    }

    public void setTile(int x, int y, char tile) {
        grid[y][x] = tile;
    }

    public char[][] getGrid() {
        return grid;
    }
}
