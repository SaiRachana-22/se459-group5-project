package se459;

import se459.rogue_data.Dungeon;
import se459.rogue_data.Player;
import se459.rogue_logic.DungeonGenerator;
import se459.rogue_ui.GameRenderer;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for the player's name
        System.out.print("Rogue's Name: ");
        String playerName = scanner.nextLine();

        Dungeon dungeon = DungeonGenerator.generate(20, 10);
        Player player = new Player(5, 5); // Ensure this starts inside a room

        String gameMessages = "Welcome to the Dungeons of Doom, " + playerName + "!";
        String playerStats = playerName + " | Level: 1 | Score: 0";

        while (true) {
            GameRenderer.render(dungeon, player, gameMessages, playerStats);
            System.out.println("Move (WASD), Q to quit:");

            char input = scanner.next().toLowerCase().charAt(0);
            if (input == 'q')
                break; // Quit game

            gameMessages = player.move(input, dungeon);
        }
        scanner.close();
    }
}
