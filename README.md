# Recitation 8 - Grok Coalition With Arrays!

## Learning Outcomes

After completing this Skill Builder, a student will be able to

1. Use arrays to store items
2. Add items to an array
3. Delete items from an array

## The Grok Coalition
Our `Grok` is now sporting a pocket that can store up to *N* power pills.  In this recitation, our Grok can store 5 power pills.  In the `Grok` class, a named constant will be defined.

```
private static final int MAX_NUMBER_OF_POWERPILLS = 5;
```

To store the power pills, an array will be used.

```
private PowerPill[] powerPill;
```

The array, <span style="font-family:'courier',courier new; font-weight:bold;">powerPill</span>, will be initialized in the <span style="font-family:'courier',courier new; font-weight:bold;">init</span> method as follows.

```
private void init(int powerLevel)
{
    setPowerLevel(powerLevel);
    powerPill = new PowerPill[MAX_NUMBER_OF_POWERPILLS];
    numOfPowerPills = 0;
}
```


## The Grok Class

The `Grok` class must implement methods that support the storing and ingestion power pills.  In this recitation, a `Grok` will be able to pick up a power pill.  This includes storing the power pill. 

```
/*
 * Store the power pill for this Grok so that it may be used later.
 * @param pill The PowerPill to store for this Grok.
 */
public void pickupPowerPill(PowerPill pill)
{
    // TODO: replace this line with your code
}
```

The `Grok` will also be able to take the last power pill the `Grok` picked up (i.e. ingest it) 

```
/*
 * Add the power level of this Grok to the power level of
 * a stored pill.  Power pills are consumed starting with the
 * last pill stored.
 */
public void takePowerPill()
{
    // TODO: replace this line with your code
}
```

In addition, the `Grok` can take a power pill with a specific name. 

```
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
```


## Assignment

Complete the three methods listed in the previous section and test them against the unit test provided in the Gradel project.  Submit the recitation by the deadline at the **CodeGrade** link on Blackboard. 
