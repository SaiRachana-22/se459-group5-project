package se459;

import org.junit.Test;
import static org.junit.Assert.*;
import se459.rogue_data.Monster;


public class MonsterTest {

    @Test
    public void testMonsterInitialization() {
        Monster monster = new Monster(3, 4, "Emu");
        assertEquals(3, monster.getX());
        assertEquals(4, monster.getY());
        assertEquals("Emu", monster.getName());
    }

    @Test
    public void testMonsterAttack() {
        Monster monster = new Monster(2, 3, "Emu");
        assertEquals(2, monster.attack());
    }

    @Test
    public void testMonsterTakesDamage() {
        Monster monster = new Monster(2, 3, "Emu");
        monster.takeDamage(5);
        assertTrue(monster.isAlive());
    }

    @Test
    public void testMonsterDeath() {
        Monster monster = new Monster(2, 3, "Emu");
        monster.takeDamage(10);
        assertFalse(monster.isAlive());
    }
}
