package se459.rogue_ui;

import se459.rogue_data.Dungeon;
import se459.rogue_data.Player;

public class GameRenderer {

    public static void render(Dungeon dungeon, Player player, String gameMessages, String playerStats) {
        clearScreen(); // Clear the screen before rendering

        // Display game messages at the top
        System.out.println(gameMessages);

        // Display the dungeon grid
        char[][] grid = dungeon.getGrid();

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (x == player.getX() && y == player.getY()) {
                    System.out.print("@"); // Player
                } else {
                    System.out.print(grid[y][x]);
                }
            }
            System.out.println();
        }

        // Display player stats at the bottom
        System.out.println("Player Stats: " + playerStats);
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J"); // ANSI escape code for clearing console
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Failed to clear screen");
        }
    }
}
