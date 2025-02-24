package se459;

import se459.rogue_data.Dungeon;
import se459.rogue_data.Player;
import se459.rogue_data.Monster;
import se459.rogue_logic.DungeonGenerator;
import se459.rogue_logic.CombatManager;
import se459.rogue_ui.GameRenderer;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for the player's name
        System.out.print("Rogue's Name: ");
        String playerName = scanner.nextLine();

        Dungeon dungeon = DungeonGenerator.generate(20, 10);
        Player player = new Player(5, 4);
        Monster monster = new Monster(7, 4, "Emu"); // Create the monster
        dungeon.addMonster(monster); // Add the monster to the dungeon

        CombatManager combatManager = new CombatManager(scanner);
        String gameMessages = "Welcome to the Dungeons of Doom, " + playerName + "!";

        while (player.isAlive()) {
            // Render the game state
            GameRenderer.render(dungeon, player, monster, gameMessages, player.getStats());
            System.out.println("Move (WASD), Q to quit:");

            char input = scanner.next().toLowerCase().charAt(0);
            if (input == 'q') break;

            gameMessages = player.move(input, dungeon); // Move the player

            // Check if the player and monster are in the same position to initiate combat
            if (player.getX() == monster.getX() && player.getY() == monster.getY() && monster.isAlive()) {
                combatManager.startCombat(player, dungeon);
                
                if (!monster.isAlive()) {
                    gameMessages = "You defeated the monster Emu! Gained 2 XP";
                    player.gainExperience(2);
                    dungeon.removeMonster();
                }
            }
            
        }

        scanner.close();
    }
}