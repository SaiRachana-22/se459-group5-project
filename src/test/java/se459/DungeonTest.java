package se459;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import se459.rogue_data.Dungeon;

public class DungeonTest {

    private Dungeon dungeon;

    @Before
    public void setUp() {
        dungeon = new Dungeon(10, 10);
    }

    @Test
    public void testDungeonInitialization() {
        assertEquals('#', dungeon.getTile(0, 0)); // Check walls
        assertEquals('#', dungeon.getTile(9, 9)); // Check walls
        assertEquals(' ', dungeon.getTile(5, 5)); // Check empty space
    }

    @Test
    public void testSetAndGetTile() {
        dungeon.setTile(5, 5, '.');
        assertEquals('.', dungeon.getTile(5, 5));
    }

}