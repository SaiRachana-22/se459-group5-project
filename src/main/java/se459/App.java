package se459;
 
import se459.rogue_data.Dungeon;
import se459.rogue_data.Item;
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
 
        Dungeon dungeon = DungeonGenerator.generate(50, 20);
        Player player = new Player(9, 10);
        Monster monster = new Monster(13, 10, "Emu"); // Create the monster
        dungeon.addMonster(monster); // Add the monster to the dungeon
         
        CombatManager combatManager = new CombatManager(scanner);
        String gameMessages = "Welcome to the Dungeons of Doom, " + playerName + "!";
 
        while (player.isAlive()) {
            // Render the game state
            GameRenderer.render(dungeon, player, monster, gameMessages, player.getStats());
            System.out.println("Move (WASD), P to pick up item, Q to quit:");
 
            char input = scanner.next().toLowerCase().charAt(0);
            if (input == 'q') break;
 
            gameMessages = player.move(input, dungeon); // Move the player

            Item itemToPick = null;
            for (Item item : dungeon.getItems()) {
                if (player.getX() == item.getX() && player.getY() == item.getY()) {
                    itemToPick = item;
                    break;
                }
            }

            // If the player presses 'P' and there is an item, pick it up
            if (input == 'p' && itemToPick != null) {
                player.pickUpItem(itemToPick);
                dungeon.getItems().remove(itemToPick); // Remove from dungeon
                gameMessages = "You picked up " + itemToPick.getName() + "!";
            }

            if(player.isStarving()) {
                gameMessages = "Game Over!! You starved and died!";
                break;
            }
 
            // Check if the player and monster are in the same position to initiate combat
            if (player.getX() == monster.getX() && player.getY() == monster.getY() && monster.isAlive()) {
                combatManager.startCombat(player, dungeon);
               
                if (!monster.isAlive()) {
                    gameMessages = "You defeated the monster Emu! Gained 2 XP";
                    player.gainExperience(2);
                    dungeon.removeMonster();
                }
            }
           
            if (!player.isAlive()) {
                gameMessages = "Game Over!! You have been defeated by Emu!";
                break;
            }
        }
 
        scanner.close();
    }
}

