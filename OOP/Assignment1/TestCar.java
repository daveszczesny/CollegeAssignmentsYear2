
/*
 * TestCar Class
 * Contains the Main Method
 * Creates Car, Engine, and Wheel objects and assigns values
 * Performs 3 trips, each with different fuel levels
 * The Car file has a printState method that is ran in this class.
 *  it prints out currents levels of the variables as well as totals
 */

public class TestCar {
    public static void main(String[] args){
        // create objects and add to car object
        Car car = new Car("X7");
        Engine engine = new Engine("DX7", 43);
        car.add(engine);
        Wheel wheel = new Wheel("Wichelin15", 15);
        car.add(wheel);

        // the setFuel method sets the amount of fuel in the engine
        // the drive method "drives" the car aka performs all the calculations
        // printState method prints out all the information about the trip

        // trip 1
        car.setFuel(100);
        car.drive();
        car.printState();


        // trip 2
        car.setFuel(80);
        car.drive();
        car.printState();


        // trip 4, testing negative number
        car.setFuel(-20);
        car.drive();
        car.printState();

        // trip 5, testing for float number
        car.setFuel(56.1F);
        car.drive();
        car.printState();

        // trip 6, testing with irrational number
        car.setFuel((float)Math.PI);
        car.drive();
        car.printState();
    }
}
