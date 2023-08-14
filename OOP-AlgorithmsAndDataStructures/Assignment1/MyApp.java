import java.util.Scanner;

/*
 * @Author: Dawid Szczesny
 * @ID: 21300293
 * @Date: 13/01/2023
 */

class MyApp {

    // creating alphabet array
    private char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z' };

    // @Constructor
    public MyApp() {
        // Instructions for user
        System.out.println("Type the alphabet in order (hit enter between each letter)");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Forwards or backwards? (f / b): ");
        // takes in a character and converts it to lower case
        char alphaOrder = scanner.next().toLowerCase().charAt(0);

        // switch case syntax to determine whether the user goes forwards or backwards

        switch (alphaOrder) {
            case 'f':
                // user chose to type the alphabet forwards
                System.out.println("You are typing the alphabet forwards!");
                break;
            case 'b':
                // We reverse in alphabet array as we are going backwards
                char[] temp = new char[26]; // creates new temp array
                // loop through each letter in alphabet array from back to front
                // appending each one to temp
                for (int i = 0; i < alphabet.length; i++) {
                    temp[i] = alphabet[alphabet.length - 1 - i];
                }
                // set alphabet to temp
                alphabet = temp;

                System.out.println("You are typing the alphabet backwards!");
                break;
            default:
                System.out.println("Character must be either a f or b");
                alphaOrder = scanner.next().toLowerCase().charAt(0);
                break;
        }

        // ------------------ Game Part ------------------------------

        // this is where our time will begin
        long begin = System.currentTimeMillis();

        for (int i = 0; i < alphabet.length; i++) {
            System.out.println("Type \'" + alphabet[i] + "\' now");
            // Accepts both lower case and upper case letters, by coverting them to
            // lowercase
            char input = scanner.next().toLowerCase().charAt(0);

            // checking if character is incorrect
            // if it is we cancel out the increment of i in the for loop
            // let the user know that it was incorrect
            // continue on with the for loop
            if (input != alphabet[i]) {
                i--;
                System.out.println("incorrect");
                continue;
            }

        }

        // congratulates the user and calculates time
        System.out.println("Well done!");
        long end = System.currentTimeMillis();
        float time = (float) (end - begin) / 1000; // using float to not lose decimal
        System.out.printf("Time taken: %.3f seconds\n", time); // cutting off at 3 decimal places

        scanner.close(); // closes scanner once we are done

    }

    // Main method
    // Creates instance of app and runs constructor
    public static void main(String[] args) {
        MyApp app = new MyApp();
    }
}