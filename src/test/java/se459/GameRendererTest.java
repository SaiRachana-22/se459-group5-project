package se459;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se459.rogue_data.Dungeon;
import se459.rogue_data.Player;
import se459.rogue_ui.GameRenderer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class GameRendererTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream)); // Redirect System.out
    }

    @After
    public void tearDown() {
        System.setOut(originalOut); // Restore original System.out
    }

    @Test
    public void testRenderDisplaysDungeonAndPlayer() {
        Dungeon dungeon = new Dungeon(10, 5);
        Player player = new Player(2, 2);
        String gameMessages = "Welcome!";
        String playerStats = "Hero | Level: 1 | Score: 0";

        GameRenderer.render(dungeon, player, gameMessages, playerStats);
        String output = outputStream.toString();

        // Check if dungeon contains walls and player
        assertTrue(output.contains("#")); // Walls should be present
        assertTrue(output.contains("@")); // Player should be rendered
    }

    @Test
    public void testRenderDisplaysGameMessagesAndStats() {
        Dungeon dungeon = new Dungeon(10, 5);
        Player player = new Player(3, 3);
        String gameMessages = "You move up!";
        String playerStats = "Rogue | Level: 1 | Score: 0";

        GameRenderer.render(dungeon, player, gameMessages, playerStats);
        String output = outputStream.toString();

        // Check if messages and stats are displayed
        assertTrue(output.contains("You move up!"));
        assertTrue(output.contains("Rogue | Level: 1 | Score: 0"));
    }
}