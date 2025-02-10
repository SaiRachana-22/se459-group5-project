package se459;

import se459.rogue_data.Dungeon;
import se459.rogue_logic.DungeonGenerator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        

        Dungeon dungeon = DungeonGenerator.generate(20, 10);
        
        scanner.close();
    }
}
