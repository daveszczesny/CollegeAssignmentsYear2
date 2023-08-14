
/**
 * @author Dawid Szczesny
 */

import java.util.Random;

public class Hash {

    public static void main(String[] args) {
        int res = 0;
        if (args != null && args.length > 0) { // Check for <input> value
            res = hashF1(args[0]); // call hash function with <input>

            if (res < 0) { // Error
                System.out.println("Error: <input> must be 1 to 64 characters long.");
            } else {
                System.out.println("input = " + args[0] + " : Hash = " + res);
                System.out.println("Start searching for collisions");
                // Your code to look for a hash collision starts here!
                genCollision(res);
            }
        } else { // No <input>
            System.out.println("Use: CT255_HashFunction1 <Input>");
        }
    }

    // Shifts the filler by the character numeric value of the key
    public static String caesarShift(String key, String filler) {

        int numericShift = 0;

        // for loop loops through the key (in this case Bamb0)
        for (int i = 0; i < key.length(); i++) {
            // gets the numeric character value of each character in the string and adds it to numericShift
            numericShift += Character.getNumericValue((char) key.charAt(i));
        }


        String newFiller = "";

        // runs through the filler length (in this case 64)
        for (int i = 0; i < filler.length(); i++) {
            // preform caesar shift
            // we find the shifted value of our filler string
            // and add it to our newFiller string
            newFiller += filler.charAt((numericShift + i) % (filler.length() - 1));
        }
        // return new filler string
        return newFiller;

    }

    /*
     * Collision finder method
     */
    private static void genCollision(int r) {
        Random random = new Random(); // create random instance

        // create string of all upper case, lower case, and numeric values
        String upp_Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String chars = upp_Chars + upp_Chars.toLowerCase() + "0123456789";
        // String word will contain our randomized word
        String word = "";
        // tries variable to keep track of how many attempts it took to find collisions
        int tries = 0;
        // fixed int to limit word length
        final int MAX_WORD_LENGTH = 5;
        // fixed int to limit found words to 10;
        final int WORDS = 10;

        /*
         * For loop
         * to generate words that collide with hash function
         */
        for (int n = 0; n < WORDS; n++) {
            // continues loop until a word is found
            while (r != hashF1(word)) {
                word = ""; // reset word after each attempt
                for (int i = 0; i < MAX_WORD_LENGTH; i++) {
                    // randomly pick characters and numbers from chars string
                    word += chars.charAt(random.nextInt(chars.length() - 1));
                }
                tries++; // incremenet tries
            }

            // only runs when a word was found
            // prints out what the word was (input), that has equivalent (the same as original word), and the tries it took to find
            System.out.println("input = " + word + " : Hash = " + hashF1(word) + " : tries = " + tries);
            // resets word
            word = "";
        }
    }

    private static int hashF1(String s) {
        int ret = -1, i;
        int[] hashA = new int[] { 1, 1, 1, 1 };

        String filler, sIn;

        filler = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890az");
        filler = caesarShift(s, filler);
        if ((s.length() > 64) || (s.length() < 1)) { // String does not have required length
            ret = -1;
        } else {
            sIn = s + filler; // Add characters, now have "<input>HABCDEF..."
            sIn = sIn.substring(0, 64); // // Limit string to first 64 characters
            // System.out.println(sIn); // FYI
            for (i = 0; i < sIn.length(); i++) {
                char byPos = sIn.charAt(i); // get i'th character
                hashA[0] += (byPos * 17);
                hashA[1] += (byPos * 31);
                hashA[2] += (byPos * 101);
                hashA[3] += (byPos * 79);
            }

            hashA[0] %= 255; // % is the modulus operation, i.e. division with rest
            hashA[1] %= 255;
            hashA[2] %= 255;
            hashA[3] %= 255;

            ret = hashA[0] + (hashA[1] * 256) + (hashA[2] * 256 * 256) + (hashA[3] * 256 * 256 * 256);
            if (ret < 0)
                ret *= -1;
        }
        return ret;
    }
}