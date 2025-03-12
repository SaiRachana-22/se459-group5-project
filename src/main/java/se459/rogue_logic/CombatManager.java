package se459.rogue_logic;

import se459.rogue_data.Player;
import se459.rogue_data.Monster;
import se459.rogue_ui.GameRenderer;
import se459.rogue_data.Dungeon;
import se459.rogue_data.Food;

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

            System.out.println("Actions: Press 'A' to attack..., 'E' to eat food");
            char action = scanner.next().toLowerCase().charAt(0);

            switch (action) {
                case 'a':
                    attack(player, monster);
                    break;
                case 'e':
                    eatFood(player);
                    break;
                default:
                    System.out.println("Invalid action!");
            }

            if (monster.isAlive()) {
                monsterAttack(player, monster);
            }

        }
    }

    private void attack(Player player, Monster monster) {
        int damage = player.attack();
        monster.takeDamage(damage);
    }

    private void eatFood(Player player) {
        Food food = player.getFirstFood();
        if (food == null) {
            System.out.println("No food in your pack!");
        } else {
            player.eatFood(food);
            System.out.println("You ate " + food.getName() + " and restored hunger.");
        }
    }

    private void monsterAttack(Player player, Monster monster) {
        int damage = monster.attack();
        player.takeDamage(damage);
    }

}
