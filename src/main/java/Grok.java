
/**
 * Groks are bad actors in a game.  Groks have the ability to ingest
 * a PowerPill to replenish their energy.  This makes them difficult
 * to kill.  This implementation allows a Grok to store upto a maximum
 * number of power pills (5 by default).  
 *
 * @author (You Again)
 * @version (0.1)
 */
public class Grok
{
    private static final int DEFAULT_POWER_LEVEL = 50;
    private static final int MAX_NUMBER_OF_POWERPILLS = 5;

    // instance variables
    private int powerLevel;
    private PowerPill[] powerPill;
    private int numOfPowerPills;

    // accessor variables

    /*
     * Initializes a Grok object to the default power level of 50.
     */
    private void init(int powerLevel)
    {
        setPowerLevel(powerLevel);
        powerPill = new PowerPill[MAX_NUMBER_OF_POWERPILLS];
        numOfPowerPills = 0;
    }

    public Grok()
    {
        init(DEFAULT_POWER_LEVEL);
    }

    /*
     * Initializes a Grok object to power powerLevel
     */
    public Grok(int powerLevel)
    {

        init(powerLevel);
    }

    // accessor methods

    /*
     * Returns the power level of this Grok.
     * @return returns the power level of this Grok
     */
    public int getPowerLevel()
    {
        return powerLevel;
    }

    // mutator methods

    /*
     * Sets the power level of this Grok.
     * @param powerLevel the power value to set for this Grok.
     */
    public void setPowerLevel(int powerLevel)
    {
        this.powerLevel = powerLevel;
    }

    /*
     * Store the power pill for this Grok so that it may be used later.
     * @param pill The PowerPill to store for this Grok.
     */
    public void pickupPowerPill(PowerPill pill)
    {
        // TODO: replace this line with your code
    }

    /*
     * Add the power level of this Grok to the power level of
     * a stored pill.  Power pills are consumed starting with the
     * last pill stored.
     */
    public void takePowerPill()
    {
        // TODO: replace this line with your code
    }

    /*
     * Add the power level of this Grok to the power level of
     * a stored pill with the name given by the parameter name.
     */
    public void takePowerPill(String name)
    {
        /*
         * Search for name in array and if found consume powerpill.
         * Copy all powerpills over so that the consumed power pill
         * is removed from the array and there are no gaps in the 
         * array.
         */ 
        
        // TODO: replace this line with your code
    }

    /*
     * Invoked when this Grok takes a hit.  The power level of
     * this Grok is reduced by 5.
     */
    public void tookHit()
    {
        powerLevel = powerLevel - 5;
    }
}
