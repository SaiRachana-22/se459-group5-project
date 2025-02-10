package se459.rogue_data;

public class Dungeon {

    private final int width;
    private final int height;
    private char[][] grid;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];

        // Initialize the dungeon with walls and empty spaces
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    grid[i][j] = '#'; // Walls
                } else {
                    grid[i][j] = ' '; // Empty space
                }
            }
        }
    }

    public void setTile(int x, int y, char tile) {
        grid[y][x] = tile;
    }

    public char getTile(int x, int y) {
        return grid[y][x];
    }

    public char[][] getGrid() {
        return grid;
    }
}