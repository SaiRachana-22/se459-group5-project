package se459.rogue_logic;
 
import se459.rogue_data.Dungeon;
import se459.rogue_data.Room;
 
import java.util.Random;
 
public class DungeonGenerator {
 
    private static final Random random = new Random();
 
    // Generate the dungeon
    public static Dungeon generate(int width, int height) {
        Dungeon dungeon = new Dungeon(width, height);
 
        // Create the first room
        Room firstRoom = createRoom(dungeon, width, height);
        createRoomInDungeon(dungeon, firstRoom);
 
        // Return dungeon for further modifications
        return dungeon;
    }
 
    // Creates a random room inside the dungeon
    private static Room createRoom(Dungeon dungeon, int width, int height) {
        int roomWidth = 15;  // Fixed width for simplicity
        int roomHeight = 12;  // Fixed height for simplicity
        int x = 7;  // Room x position (starting point)
        int y = 6;  // Room y position (starting point)
 
        return new Room(x, y, roomWidth, roomHeight);
    }
 
    // Create the room in the dungeon grid
    private static void createRoomInDungeon(Dungeon dungeon, Room room) {
        // Create floor inside the room
        for (int i = room.getY(); i < room.getY() + room.getHeight(); i++) {
            for (int j = room.getX(); j < room.getX() + room.getWidth(); j++) {
                dungeon.setTile(j, i, '.'); // Floor inside the room
            }
        }
 
        // Add walls around the room
        addWalls(dungeon, room);
 
        // Add passage out
        addPassageOut(dungeon, room);
    }
 
    // Add walls around a room (horizontal and vertical)
    private static void addWalls(Dungeon dungeon, Room room) {
        // Top and Bottom walls
        for (int i = room.getX(); i < room.getX() + room.getWidth(); i++) {
            dungeon.setTile(i, room.getY(), '-'); // Top wall
            dungeon.setTile(i, room.getY() + room.getHeight() - 1, '-'); // Bottom wall
        }
 
        // Left and Right walls
        for (int i = room.getY(); i < room.getY() + room.getHeight(); i++) {
            dungeon.setTile(room.getX(), i, '|'); // Left wall
            dungeon.setTile(room.getX() + room.getWidth() - 1, i, '|'); // Right wall
        }
    }
 
    // Add a passage out from the room (on the right side)
    private static void addPassageOut(Dungeon dungeon, Room room) {
        // Create a passage on the right side of the room
        int passageX = room.getX() + room.getWidth() - 1; // Right side of the room
        int passageY = room.getY() + room.getHeight() / 2; // Middle of the room vertically
 
        dungeon.setTile(passageX, passageY, ' '); // Empty space for the passage out
    }
}
 
