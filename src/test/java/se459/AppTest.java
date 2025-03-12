package se459;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import se459.rogue_data.Dungeon;
import se459.rogue_data.Gold;
import se459.rogue_data.Item;
import se459.rogue_data.Monster;
import se459.rogue_data.Player;
import se459.rogue_logic.DungeonGenerator;
import se459.rogue_ui.GameRenderer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    @Test
    public void testPlayerNameInput() {
        String input = "Hero\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Rogue's Name: ");
        String playerName = scanner.nextLine();

        assertEquals("Hero", playerName);
    }

    @Test
    public void testGameLoopQuit() {
        String input = "q\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Dungeon dungeon = DungeonGenerator.generate(50, 20);
        Player player = new Player(5, 5);
        Monster monster = new Monster(4, 4, "Emu");

        Scanner scanner = new Scanner(System.in);
        String gameMessages = "Welcome to the Dungeons of Doom!";
        String playerStats = "Health: 20 | Level: 1 | Experience: 0";

        while (true) {
            GameRenderer.render(dungeon, player, monster, gameMessages, playerStats);
            System.out.println("Move (WASD), P to pick up item, Q to quit:");

            if (!scanner.hasNext()) break;
            char inputChar = scanner.next().toLowerCase().charAt(0);
            if (inputChar == 'q') {
                break;
            }
        }

        assertTrue(outContent.toString().contains("Move (WASD), P to pick up item, Q to quit:"));
    }

    @Test
    public void testGameInitialization() {
        Dungeon dungeon = new Dungeon(50, 20);
        Player player = new Player(9, 10);
        Monster monster = new Monster(13, 10, "Emu");

        String gameMessages = "Welcome to the Dungeons of Doom!";
        String playerStats = "Health: 20 | Level: 1 | Experience: 0";

        GameRenderer.render(dungeon, player, monster, gameMessages, playerStats);
        String output = outContent.toString();

        assertTrue(output.contains("#"));  // Dungeon walls
        assertTrue(output.contains("@"));  // Player
        assertTrue(output.contains("E"));  // Monster
        assertTrue(output.contains("Welcome to the Dungeons of Doom!"));
        assertTrue(output.contains("Health: 20 | Level: 1 | Experience: 0"));
    }

    @Test
    public void testItemPickup() {
        Dungeon dungeon = new Dungeon(10, 5);
        Player player = new Player(2, 2);
        
        List<Item> items = dungeon.getItems();
        assertFalse(items.isEmpty());  

        Item item = items.get(0); 
        player.pickUpItem(item);

        assertNotNull(item);
    }

    @Test
    public void testStarvationDeath() {
        Player player = new Player(2, 2);

        while (player.isAlive()) {
            player.takeDamage(1);
        }

        assertFalse(player.isAlive()); 
    }

    @Test
    public void testGoldCollection() {
        Dungeon dungeon = new Dungeon(10, 5);
        Player player = new Player(2, 2);
        
        List<Item> items = dungeon.getItems();
        assertFalse(items.isEmpty()); 

        Item gold = null;
        for (Item item : items) {
            if (item instanceof Gold) {
                gold = item;
                break;
            }
        }

        assertNotNull(gold); 
        player.pickUpItem(gold);

        assertNotNull(gold);
    }
}