import java.util.Random; // imports random

public class AnimalTest {

    // main method
    public static void main(String[] args) {

        // creates instance of main class
        AnimalTest test = new AnimalTest();

        // two testing methods
        test.testString();
        test.testEquals();
    }

    /*
     * This method will test the toString overrides over the classes
     * Canary, Ostrich, Shark and Trout
     */
    public void testString() {
        // Create animal array of size 4
        Animal[] animals = new Animal[4];
        Random random = new Random();
        // Populate array with animals
        animals[0] = new Canary("Bob");
        animals[1] = new Ostrich();
        animals[2] = new Shark();
        animals[3] = new Trout();

        // For loop to print out the array elements
        for (Animal animal : animals) {
            System.out.println(animal);
            animal.move(random.nextInt(100));
        }

    }

    /*
     * Method to test equals override
     */
    public void testEquals() {
        // Create animals array of size 8
        Animal[] animals = new Animal[8];
        Random random = new Random(); // create instance of random


        // populate array with animals, two of each
        animals[0] = new Canary("Bobby");
        animals[1] = new Canary("Dave");
        animals[2] = new Ostrich();
        animals[3] = new Ostrich();
        animals[4] = new Shark();
        animals[5] = new Shark();
        animals[6] = new Trout();
        animals[7] = new Trout();

        // for loop, we check 15 different times
        for (int i = 0; i < 15; i++) {
            // create two random integers of max array length
            int m = random.nextInt(animals.length - 1);
            int n = random.nextInt(animals.length - 1);

            // print out the class name that we are comparing
            // note the canary class won't always be equal to true when comparing to another canary class
            //      this is because we have two canary instances in our array both with different names
            System.out.print("Animals : " + animals[m].getClass().getSimpleName() + " equals " + animals[n].getClass().getSimpleName() + ": ");
            System.out.println(animals[m].equals(animals[n])); // print out their equals
        }

    }

}
