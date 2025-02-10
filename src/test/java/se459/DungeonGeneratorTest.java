package se459;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import se459.rogue_data.Dungeon;
import se459.rogue_logic.DungeonGenerator;

public class DungeonGeneratorTest {
    private Dungeon dungeon;

    @Before
    public void setUp() {
        dungeon = DungeonGenerator.generate(20, 10);
    }

    @Test
    public void testFirstRoomGeneration() {
        boolean hasFloor = false;
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 20; x++) {
                if (dungeon.getTile(x, y) == '.') {
                    hasFloor = true;
                    break;
                }
            }
        }
        assertTrue("Dungeon room should have at least one floor tile", hasFloor);
    }

    @Test
    public void testPassageExists() {
        boolean hasPassage = false;
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 20; x++) {
                if (dungeon.getTile(x, y) == ' ') {
                    hasPassage = true;
                    break;
                }
            }
        }
        assertTrue("Dungeon room should have at least one passage tile", hasPassage);
    }
}