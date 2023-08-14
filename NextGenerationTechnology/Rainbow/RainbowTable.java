/*
 +-----------------------------------------------+
 |  @Author: Dave
 |  Date: 28/10/2022
 +-----------------------------------------------+
 */

// imports
import java.util.ArrayList;

/*  CT255 Assignment 2
 *  This class provides functionality to build rainbow tables (with a different reduction function per round) for 8 character long strings, which
    consist of the symbols "a .. z", "A .. Z", "0 .. 9", "!" and "#" (64 symbols in total).
    Properly used, it creates the following value pairs (start value - end value) after 10,000 iterations of hashFunction() and reductionFunction():
          start value  -  end value
          Kermit12        lsXcRAuN
          Modulus!        L2rEsY8h
          Pigtail1        R0NoLf0w
          GalwayNo        9PZjwF5c
          Trumpets        !oeHRZpK
          HelloPat        dkMPG7!U
          pinky##!        eDx58HRq
          01!19!56        vJ90ePjV
          aaaaaaaa        rLtVvpQS
          036abgH#        klQ6IeQJ
          
          
 *
 * @author Michael Schukat
 * @version 1.0
 */
public class RainbowTable {

    public static void main(String[] args) {
        long res = 0;
        int i;
        String start;

        // Creating two arrays. One for the start values and the other for the end values

        ArrayList<String> sValues = new ArrayList<String>();
        // Populating array
        sValues.add("Kermit12");
        sValues.add("Modulus!");
        sValues.add("Pigtail1");
        sValues.add("GalwayNo");
        sValues.add("Trumpets");
        sValues.add("HelloPat");
        sValues.add("pinky##!");
        sValues.add("01!19!56");
        sValues.add("aaaaaaaa");
        sValues.add("036abgH");

        ArrayList<String> eValues = new ArrayList<String>();
        // populating array
        eValues.add("lsXcRAuN");
        eValues.add("L2rEsY8h");
        eValues.add("R0NoLf0w");
        eValues.add("9PZjwF5c");
        eValues.add("!oeHRZpK");
        eValues.add("dkMPG7!U");
        eValues.add("eDx58HRq");
        eValues.add("vJ90ePjV");
        eValues.add("rLtVvpQS");
        eValues.add("klQ6IeQJ");


        if (args != null && args.length > 0) { // Check for <input> value
            start = args[0];

            if (start.length() != 8) {
                System.out.println("Input " + start + " must be 8 characters long - Exit");
            } else {
                // hash code // problem 1


                System.out.println("+--------------------+\n|\tProblem 1\n+--------------------+");
                String str = sValues.get(0);
                long hash = 0;
                for(int k = 0;k<10000;k++){
                    hash = hashFunction(str);
                    str = reductionFunction(hash, k);
                }

                System.out.println(sValues.get(0) + " -> " + str);

                // adding the hash values for problem 2 into an array
                ArrayList<String> hashValues = new ArrayList<>();
                hashValues.add("895210601874431214");
                hashValues.add("750105908431234638");
                hashValues.add("111111111115664932");
                hashValues.add("977984261343652499");

                System.out.println("+--------------------+\n|\tProblem 2\n+--------------------+");

                // Searching through each starting element
                sValues.forEach((element) -> {
                    String str_ = element;  // keeping track of the original string \\
                    long hash_ = 0;
                    // for loop to iterate over hash and reduction function 10,000 times
                    for(int k = 0; k<10000; k++){
                        // hashFunction returns long from starting value
                        hash_ = hashFunction(str_);
                        // reductionFunction converts it to a string value
                        str_ = reductionFunction(hash_, k);

                        // We check if any of the iterations collide with our 4 hash values
                        if(hashValues.contains(Long.toString(hash_))){
                            System.out.println("Hash Collision found! " + "Starting value: " + element + 
                            ", current value: "  + str_ +  " and its hash: " + hash_);
                        }
                    }
                    //System.out.println("Starting value: " + element + " -> End value: " + str);
                });


            }
        } else { // No <input>
            System.out.println("Use: RainbowTable <Input>");
        }
    }

    private static long hashFunction(String s) {
        long ret = 0;
        int i;
        long[] hashA = new long[] { 1, 1, 1, 1 };

        String filler, sIn;

        int DIV = 65536;

        filler = new String("ABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGHABCDEFGH");

        sIn = s + filler; // Add characters, now have "<input>HABCDEF..."
        sIn = sIn.substring(0, 64); // // Limit string to first 64 characters

        for (i = 0; i < sIn.length(); i++) {
            char byPos = sIn.charAt(i); // get i'th character
            hashA[0] += (byPos * 17111); // Note: A += B means A = A + B
            hashA[1] += (hashA[0] + byPos * 31349);
            hashA[2] += (hashA[1] - byPos * 101302);
            hashA[3] += (byPos * 79001);
        }

        ret = (hashA[0] + hashA[2]) + (hashA[1] * hashA[3]);
        if (ret < 0)
            ret *= -1;
        return ret;
    }

    private static String reductionFunction(long val, int round) { // Note that for the first function call "round" has
                                                                   // to be 0,
        String car, out;                                           // and has to be incremented by one with every subsequent call.
        int i;                                                     // I.e. "round" created variations of the reduction function.
        char dat;

        car = new String("0123456789ABCDEFGHIJKLMNOPQRSTUNVXYZabcdefghijklmnopqrstuvwxyz!#");
        out = new String("");

        for (i = 0; i < 8; i++) {
            val -= round;
            dat = (char) (val % 63);
            val = val / 83;
            out = out + car.charAt(dat);
        }

        return out;
    }
}
