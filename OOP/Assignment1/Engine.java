/*
 * Engine class
 * Creates wheel object
 * setters and getters for private variables
 */

public class Engine{
    // initilize variables
    private String name = "";
    private int tpl = 0; // turns per litre (engine efficiceny)
    private int totalNumTurns = 0; // total number of engine turns
    private float fuelLevel = 0;
    private float initialFuelLevel = 0;  // keep track of how much fuel we had at the beginning

    private Wheel wheel; // creating an undeclared variable of the wheel class later to be added

    // Constructor
    public Engine(String name, int tpl)
    {
        this.name = name;
        this.tpl = tpl;
    }

    //   ####################
    //  #### setters  ######
    // ####################
    
    public void setFuel(float fuelLevel){
        this.fuelLevel = fuelLevel;
        initialFuelLevel = fuelLevel;
    }
    // decreases fuelLevel by the amount
    public void useFuel(float amount){
        this.fuelLevel -= amount;
    }

    // The wheel class is the parameter for this method
    public void add(Wheel wheel){
        this.wheel = wheel;
    }

    //   ####################
    //  #### getters  ######
    // ####################

    public float getFuel(){
        return fuelLevel;
    }
    public float getInitialFuelLevel(){
        return initialFuelLevel;
    }
    public int getTurns(){
        return totalNumTurns;
    }
    public int getTpl(){
        return tpl;
    }

    public String getName(){
        return name;
    }

    public Wheel getWheel(){
        return wheel;
    }


    // increments the amount of turns of the engine
    public void incrementTurns(float turns){
        totalNumTurns += (int)turns;
    }

}