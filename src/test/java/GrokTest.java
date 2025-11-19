import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for testing Grok's power pill management methods.
 * Updated to reflect that takePowerPill() now ADDS to power level rather than setting it.
 */
public class GrokTest {

    private Grok grok;
    private PowerPill pill1;
    private PowerPill pill2;
    private PowerPill pill3;

    @BeforeEach
    public void setUp() {
        grok = new Grok();
        pill1 = new PowerPill("RedPill", 100);
        pill2 = new PowerPill("BluePill", 75);
        pill3 = new PowerPill("GreenPill", 120);
    }

    // Tests for pickupPowerPill()

    @Test
    public void testPickupSinglePowerPill() {
        int initialPower = grok.getPowerLevel();
        grok.pickupPowerPill(pill1);
        // Power level shouldn't change until pill is taken
        assertEquals(initialPower, grok.getPowerLevel());
    }

    @Test
    public void testPickupMultiplePowerPills() {
        grok.pickupPowerPill(pill1);
        grok.pickupPowerPill(pill2);
        grok.pickupPowerPill(pill3);

        // Power level shouldn't change until pills are taken
        assertEquals(50, grok.getPowerLevel());
    }

    @Test
    public void testPickupMaximumPowerPills() {
        PowerPill[] pills = new PowerPill[6];
        for (int i = 0; i < 6; i++) {
            pills[i] = new PowerPill("Pill" + i, 10);
            grok.pickupPowerPill(pills[i]);
        }

        // Should only store 5 pills (max capacity)
        // Test by consuming all pills - should add 50 total (5 pills * 10 power each)
        int initialPower = grok.getPowerLevel();

        for (int i = 0; i < 6; i++) {
            grok.takePowerPill();
        }

        // Should have added 50 power (5 pills * 10), starting from 50
        assertEquals(initialPower + 50, grok.getPowerLevel());
    }

    // Tests for takePowerPill() - no parameters (LIFO)

    @Test
    public void testTakePowerPillLastInFirstOut() {
        grok.pickupPowerPill(pill1); // 100 power
        grok.pickupPowerPill(pill2); // 75 power

        int initialPower = grok.getPowerLevel(); // 50

        grok.takePowerPill(); // Should take pill2 (last one added) and ADD 75
        assertEquals(initialPower + 75, grok.getPowerLevel());

        grok.takePowerPill(); // Should take pill1 and ADD 100
        assertEquals(initialPower + 75 + 100, grok.getPowerLevel());
    }

    @Test
    public void testTakePowerPillWhenNoPillsAvailable() {
        int initialPower = grok.getPowerLevel();
        grok.takePowerPill(); // No pills stored

        // Power level should remain unchanged
        assertEquals(initialPower, grok.getPowerLevel());
    }

    @Test
    public void testTakePowerPillRemovesPillFromStorage() {
        grok.pickupPowerPill(pill1);
        int powerBefore = grok.getPowerLevel();
        grok.takePowerPill();
        int powerAfterFirst = grok.getPowerLevel();

        // Try taking another pill - should have no effect
        grok.takePowerPill();
        assertEquals(powerAfterFirst, grok.getPowerLevel());

        // Verify that first pill actually added power
        assertEquals(powerBefore + 100, powerAfterFirst);
    }

    @Test
    public void testTakePowerPillAddsToExistingPower() {
        grok.pickupPowerPill(pill1); // 100 power

        assertEquals(50, grok.getPowerLevel()); // Initial power

        grok.takePowerPill();
        assertEquals(150, grok.getPowerLevel()); // 50 + 100 = 150
    }

    // Tests for takePowerPill(String name) - by name

    @Test
    public void testTakePowerPillByName() {
        grok.pickupPowerPill(pill1); // RedPill, 100
        grok.pickupPowerPill(pill2); // BluePill, 75
        grok.pickupPowerPill(pill3); // GreenPill, 120

        int initialPower = grok.getPowerLevel(); // 50

        grok.takePowerPill("BluePill");
        assertEquals(initialPower + 75, grok.getPowerLevel());
    }

    @Test
    public void testTakePowerPillByNameFirstPill() {
        grok.pickupPowerPill(pill1); // RedPill, 100
        grok.pickupPowerPill(pill2); // BluePill, 75
        grok.pickupPowerPill(pill3); // GreenPill, 120

        int initialPower = grok.getPowerLevel();

        grok.takePowerPill("RedPill");
        assertEquals(initialPower + 100, grok.getPowerLevel());
    }

    @Test
    public void testTakePowerPillByNameLastPill() {
        grok.pickupPowerPill(pill1); // RedPill, 100
        grok.pickupPowerPill(pill2); // BluePill, 75
        grok.pickupPowerPill(pill3); // GreenPill, 120

        int initialPower = grok.getPowerLevel();

        grok.takePowerPill("GreenPill");
        assertEquals(initialPower + 120, grok.getPowerLevel());
    }

    @Test
    public void testTakePowerPillByNameNotFound() {
        grok.pickupPowerPill(pill1);
        int initialPower = grok.getPowerLevel();

        grok.takePowerPill("NonExistentPill");
        // Power level should remain unchanged
        assertEquals(initialPower, grok.getPowerLevel());
    }

    @Test
    public void testTakePowerPillByNullName() {
        grok.pickupPowerPill(pill1);
        int initialPower = grok.getPowerLevel();

        grok.takePowerPill(null);
        // Power level should remain unchanged
        assertEquals(initialPower, grok.getPowerLevel());
    }

    @Test
    public void testTakePowerPillByNameRemovesGaps() {
        grok.pickupPowerPill(pill1); // RedPill, 100
        grok.pickupPowerPill(pill2); // BluePill, 75
        grok.pickupPowerPill(pill3); // GreenPill, 120

        int powerAfterPickup = grok.getPowerLevel(); // 50

        // Remove middle pill
        grok.takePowerPill("BluePill");
        int powerAfterBlue = grok.getPowerLevel(); // 50 + 75 = 125

        // Now take pills in LIFO order to verify no gaps
        grok.takePowerPill(); // Should get GreenPill (was at index 2, now at 1)
        assertEquals(powerAfterBlue + 120, grok.getPowerLevel()); // 125 + 120 = 245

        grok.takePowerPill(); // Should get RedPill (was at index 0, still at 0)
        assertEquals(powerAfterBlue + 120 + 100, grok.getPowerLevel()); // 245 + 100 = 345
    }

    // Integration tests

    @Test
    public void testMixedPowerPillOperations() {
        grok.pickupPowerPill(pill1); // RedPill, 100
        grok.pickupPowerPill(pill2); // BluePill, 75

        int initialPower = grok.getPowerLevel(); // 50

        grok.takePowerPill(); // Take BluePill (LIFO) - adds 75
        assertEquals(initialPower + 75, grok.getPowerLevel()); // 125

        grok.pickupPowerPill(pill3); // GreenPill, 120
        grok.takePowerPill("RedPill"); // Take by name - adds 100
        assertEquals(initialPower + 75 + 100, grok.getPowerLevel()); // 225

        grok.takePowerPill(); // Take GreenPill (only one left) - adds 120
        assertEquals(initialPower + 75 + 100 + 120, grok.getPowerLevel()); // 345
    }

    @Test
    public void testPowerPillAfterTakingHit() {
        grok.pickupPowerPill(pill1); // 100 power
        grok.tookHit(); // Power drops to 45

        assertEquals(45, grok.getPowerLevel());

        grok.takePowerPill(); // Should ADD 100, not set to 100
        assertEquals(145, grok.getPowerLevel()); // 45 + 100 = 145
    }

    @Test
    public void testMultiplePowerPillsAdditive() {
        grok.pickupPowerPill(pill1); // 100
        grok.pickupPowerPill(pill2); // 75
        grok.pickupPowerPill(pill3); // 120

        assertEquals(50, grok.getPowerLevel());

        grok.takePowerPill(); // +120
        assertEquals(170, grok.getPowerLevel());

        grok.takePowerPill(); // +75
        assertEquals(245, grok.getPowerLevel());

        grok.takePowerPill(); // +100
        assertEquals(345, grok.getPowerLevel());
    }

    @Test
    public void testTakePowerPillByNameIsAdditive() {
        grok.pickupPowerPill(pill1); // 100
        grok.pickupPowerPill(pill2); // 75

        assertEquals(50, grok.getPowerLevel());

        grok.takePowerPill("RedPill"); // +100
        assertEquals(150, grok.getPowerLevel());

        grok.takePowerPill("BluePill"); // +75
        assertEquals(225, grok.getPowerLevel());
    }
}