package se459;
 
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import se459.rogue_data.Dungeon;
import se459.rogue_data.Player;
 
 
public class PlayerTest {
 
    private Player player;
    private Dungeon dungeon;
 
    @Before
    public void setUp() {
        dungeon = new Dungeon(50, 20);
        player = new Player(5, 5);
        dungeon.setTile(5, 5, '.'); // Ensure the player starts on a walkable tile
    }
 
    @Test
    public void testValidMoves() {
        dungeon.setTile(5, 4, '.'); // Ensure up is walkable
        assertEquals("You move up.", player.move('w', dungeon));
        assertEquals(5, player.getX());
        assertEquals(4, player.getY());
    }
 
    @Test
    public void testWallCollision() {
        dungeon.setTile(5, 4, '#'); // Wall ahead
        assertEquals("You hit a wall!", player.move('w', dungeon));
        assertEquals(5, player.getX());
        assertEquals(5, player.getY()); // Position should remain the same
    }
 
    @Test
    public void testPlayerAttack() {
        Player player = new Player(2, 2);
        assertEquals(3, player.attack());
    }
 
    @Test
    public void testTakeDamage() {
        Player player = new Player(2, 2);
        player.takeDamage(5);
        assertTrue(player.isAlive());
    }
 
}