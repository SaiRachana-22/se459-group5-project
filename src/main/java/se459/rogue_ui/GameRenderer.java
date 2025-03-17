package se459.rogue_ui;
 
import java.util.List;
 
import se459.rogue_data.Armor;
import se459.rogue_data.Dungeon;
import se459.rogue_data.Food;
import se459.rogue_data.Gold;
import se459.rogue_data.Item;
import se459.rogue_data.Player;
import se459.rogue_data.Potion;
import se459.rogue_data.Scroll;
import se459.rogue_data.Weapon;
import se459.rogue_data.Monster;
 
public class GameRenderer {
 
    public static void render(Dungeon dungeon, Player player, Monster monster, String gameMessages, String playerStats) {
        clearScreen();
        System.out.println(gameMessages);
 
        char[][] grid = dungeon.getGrid();
        char[][] displayGrid = new char[grid.length][grid[0].length];
 
        // Copy the base dungeon grid
        for (int y = 0; y < grid.length; y++) {
            System.arraycopy(grid[y], 0, displayGrid[y], 0, grid[y].length);
        }
 
        // Render items (Food, Weapons, Armor, Gold)
        List<Item> items = dungeon.getItems();
        for (Item item : items) {
            if (item instanceof Food) {
                displayGrid[item.getY()][item.getX()] = '*';
            } else if (item instanceof Weapon) {
                displayGrid[item.getY()][item.getX()] = '%';
            } else if (item instanceof Armor) {
                displayGrid[item.getY()][item.getX()] = '^';
            } else if (item instanceof Gold) {
                displayGrid[item.getY()][item.getX()] = '$';
            } else if (item instanceof Scroll) {
                displayGrid[item.getY()][item.getX()] = '&';
            } else if (item instanceof Potion) {
                displayGrid[item.getY()][item.getX()] = '!';
            }
 
 
        }
 
        // Place the monster on the display grid if it's alive
        if (monster != null && monster.isAlive()) {
            displayGrid[monster.getY()][monster.getX()] = 'E';
        }
        else {
            displayGrid[monster.getY()][monster.getX()] = '.';
            dungeon.setTile(monster.getX(), monster.getY(), '.');
        }
 
        // Place the player, overriding any tile
        displayGrid[player.getY()][player.getX()] = '@';
 
        // Print the dungeon with the updated display grid
        for (int y = 0; y < displayGrid.length; y++) {
            for (int x = 0; x < displayGrid[y].length; x++) {
                System.out.print(displayGrid[y][x]);
            }
            System.out.println();
        }
 
        System.out.println("Player Stats: " + playerStats);
    }
 
    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Failed to clear screen");
        }
    }
   
}