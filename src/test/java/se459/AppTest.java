package se459;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import se459.rogue_data.Dungeon;
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

        Scanner scanner = new Scanner(System.in);
        String gameMessages = "Welcome to the Dungeons of Doom!";
        String playerStats = "Player | Level: 1 | Score: 0";

        while (true) {
            GameRenderer.render(dungeon, player, gameMessages, playerStats);
            System.out.println("Move (WASD), Q to quit:");

            char inputChar = scanner.next().toLowerCase().charAt(0);
            if (inputChar == 'q') {
                break;
            }
        }

        assertTrue(outContent.toString().contains("Move (WASD), Q to quit:"));
    }
}