/**
 * Car class
 * Car class will have an engine object (which will be then connected to the wheel object)
 * Support to add Engine object and add wheel object to the engine (abstraction)
 * accessor/mutator methods are called getters / setters
 * Will have a printState() method that will print out the desired information about the car and trip
 */

public class Car{
    // declare and initilize all variables we need for class Car
    private String name ="";
    private float distance = 0;
    private float totalKm = 0;
    private Engine engine;

    // constructor
    public Car(String name)
    {
        this.name = name;
    }

    // add method has ability to add both the engine and engine.engine.wheel
    public void add(Engine engine){
        this.engine = engine;
    }

    // uses the method add() inside of engine class to add the wheel to the engine from the Car class 
    public void add(Wheel wheel){
        engine.add(wheel);
    }

    // setters
    // sets the fuel of the engine by running engine's method setFuel()
    // if it detects that we entered a negative fuel level, it will treat it as a mistake and reverse it
    // -x = |x|
    public void setFuel(float floatLevel){
        if(floatLevel<0){
            System.out.println("Negative fuel!, Absoluting value.");
            floatLevel *= -1;
        }
        engine.setFuel(floatLevel);
    }

    // getters
    public float getFuel(){
        return engine.getFuel();
    }

    // run the car method
    // calculates distance based on circumference of the weel * engine.wheel turns per litre * litres in engine
    // calculates the total distance travelled by summing up all previous distances travelled
    public void drive(){
        distance = engine.getWheel().getCircumference() * engine.getTpl() * engine.getFuel();
        totalKm += distance;
        engine.incrementTurns(engine.getTpl()*engine.getFuel());
        engine.useFuel(engine.getInitialFuelLevel());
    }

    // print method
    // prints out the info about the car, engine, and engine.wheel.
    // prints out engine.wheel circumference, distance travelled on trip, total distanced travelled and the remaining fuel after trip.
    public void printState(){
        System.out.println("Current fuel: " + engine.getInitialFuelLevel());
        System.out.printf("Confiuration: Car Body %s\n", name);
        System.out.println("Engine name: " + engine.getName());
        System.out.println("Engine turns per litre: " + engine.getTpl());
        System.out.println("Engine's total turn count: " + engine.getTurns());
        
        System.out.println("Wheel name: " + engine.getWheel().getName());
        System.out.printf("Wheel radius: %.2f\n", engine.getWheel().getRadius());
        System.out.printf("Wheel circumference (distance travelled per turn): %.2f\n", engine.getWheel().getCircumference());
        
        System.out.printf("Distance this trip: %.2f\n", distance);
        System.out.printf("total km travelled: %.2f\n", totalKm);
        System.out.printf("Current fuel status: %.2f\n\n\n", getFuel());    
        
    }
}