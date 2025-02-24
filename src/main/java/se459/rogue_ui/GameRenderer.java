package se459.rogue_ui;

import se459.rogue_data.Dungeon;
import se459.rogue_data.Player;
import se459.rogue_data.Monster;

public class GameRenderer {

    public static void render(Dungeon dungeon, Player player, Monster monster, String gameMessages,
            String playerStats) {
        clearScreen();
        System.out.println(gameMessages);

        char[][] grid = dungeon.getGrid();
        char[][] displayGrid = new char[grid.length][grid[0].length];

        // Copy the base dungeon grid
        for (int y = 0; y < grid.length; y++) {
            System.arraycopy(grid[y], 0, displayGrid[y], 0, grid[y].length);
        }

        // Place the monster on the display grid if it's alive
        if (monster != null) {
            displayGrid[monster.getY()][monster.getX()] = 'E';
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
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            System.out.println("Failed to clear screen");
        }
    }
}