package se459;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se459.rogue_data.Dungeon;
import se459.rogue_data.Player;
import se459.rogue_data.Monster;
import se459.rogue_ui.GameRenderer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class GameRendererTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream)); // Capture console output
    }

    @After
    public void tearDown() {
        System.setOut(originalOut); // Restore original output stream
    }

    @Test
    public void testRenderDisplaysDungeonAndPlayer() {
        Dungeon dungeon = new Dungeon(50, 20);
        Player player = new Player(2, 2);
        Monster monster = new Monster(4, 4, "Emu");
        String gameMessages = "Welcome to the Dungeons of Doom!";
        String playerStats = "HP: 20 | LVL: 1 | XP: 0";

        GameRenderer.render(dungeon, player, monster, gameMessages, playerStats);
        String output = outputStream.toString();

        // Check if dungeon contains walls, player, and monster
        assertTrue(output.contains("#"));  // Walls should be present
        assertTrue(output.contains("@")); 
        assertTrue(output.contains("E"));     }

    @Test
    public void testRenderDisplaysGameMessagesAndStats() {
        Dungeon dungeon = new Dungeon(50, 20);
        Player player = new Player(2, 2);
        Monster monster = new Monster(4, 2, "Emu");
        String gameMessages = "You move up!";
        String playerStats = "HP: 20 | LVL: 1 | XP: 0";

        GameRenderer.render(dungeon, player, monster, gameMessages, playerStats);
        String output = outputStream.toString();

        // Check if messages and stats are displayed
        assertTrue(output.contains(gameMessages));
        assertTrue(output.contains(playerStats));
    }
}