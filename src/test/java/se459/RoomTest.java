package se459;

import static org.junit.Assert.*;
import org.junit.Test;
import se459.rogue_data.Room;

public class RoomTest {

    @Test
    public void testRoomInitialization() {
        Room room = new Room(3, 2, 7, 4);
        assertEquals(3, room.getX());
        assertEquals(2, room.getY());
        assertEquals(7, room.getWidth());
        assertEquals(4, room.getHeight());
    }
    
}