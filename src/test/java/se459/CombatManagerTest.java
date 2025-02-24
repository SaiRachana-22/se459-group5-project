package se459;

import org.junit.Test;
import se459.rogue_logic.CombatManager;

import java.util.Scanner;
import static org.junit.Assert.*;

public class CombatManagerTest {
   
    @Test
    public void testCombatInitialization() {
        Scanner scanner = new Scanner(System.in);
        CombatManager combatManager = new CombatManager(scanner);
        assertNotNull(combatManager);
    }
}