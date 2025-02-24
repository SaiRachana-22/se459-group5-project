package se459;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import se459.rogue_data.Dungeon;
import se459.rogue_data.Monster;
import se459.rogue_data.Player;
import se459.rogue_logic.DungeonGenerator;
import se459.rogue_ui.GameRenderer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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

        Dungeon dungeon = DungeonGenerator.generate(20, 10);
        Player player = new Player(5, 5);
        Monster monster = new Monster(4, 4, "Emu");

        Scanner scanner = new Scanner(System.in);
        String gameMessages = "Welcome to the Dungeons of Doom!";
        String playerStats = "Health: 20 | Level: 1 | Experience: 0";

        while (true) {
            GameRenderer.render(dungeon, player, monster, gameMessages, playerStats);
            System.out.println("Move (WASD), Q to quit:");

            char inputChar = scanner.next().toLowerCase().charAt(0);
            if (inputChar == 'q') {
                break;
            }
        }

        assertTrue(outContent.toString().contains("Move (WASD), Q to quit:"));
    }

    @Test
    public void testGameInitialization() {
        // Simulate game initialization
        Dungeon dungeon = new Dungeon(10, 5);
        Player player = new Player(2, 2);
        Monster monster = new Monster(4, 4, "Emu");

        String gameMessages = "Welcome to the Dungeons of Doom!";
        String playerStats = "Health: 20 | Level: 1 | Experience: 0";

        // Render the initial game state
        GameRenderer.render(dungeon, player, monster, gameMessages, playerStats);
        String output = outContent.toString();

        // Validate game components appear in output
        assertTrue(output.contains("#"));  // Dungeon walls should be present
        assertTrue(output.contains("@"));  // Player should be rendered
        assertTrue(output.contains("E"));  // Monster should be rendered
        assertTrue(output.contains("Welcome to the Dungeons of Doom!"));  // Game message should appear
        assertTrue(output.contains("Health: 20 | Level: 1 | Experience: 0"));  // Player stats should appear
    }

}