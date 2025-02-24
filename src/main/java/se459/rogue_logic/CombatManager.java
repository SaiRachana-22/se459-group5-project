package se459.rogue_logic;

import se459.rogue_data.Player;
import se459.rogue_data.Monster;
import se459.rogue_ui.GameRenderer;
import se459.rogue_data.Dungeon;
import java.util.Scanner;

public class CombatManager {
    private final Scanner scanner;

    public CombatManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startCombat(Player player, Dungeon dungeon) {
        Monster monster = dungeon.getMonster(); // Retrieve the single monster from the dungeon

        // Check if the monster is valid and alive
        if (monster == null || !monster.isAlive()) {
            System.out.println("No monster available for combat!");
            return;
        }

        System.out.println("A " + monster.getName() + " attacks you!");

        while (player.isAlive() && monster.isAlive()) {
            GameRenderer.render(dungeon, player, monster, "In combat with Emu", player.getStats());

            System.out.println("Press 'A' to attack...");
            char action = scanner.next().toLowerCase().charAt(0);

            if (action == 'a') {
                int playerDamage = player.attack();
                monster.takeDamage(playerDamage);
                
            } 
        }
    }

}