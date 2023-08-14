import java.io.FileWriter;
import java.io.IOException;

public class Tests {
    
    // variables to define max numbers and interval to check at
    static final int INTERVAL = 50000;
    static final int MAX = 1000000;

    PalindromeCheck palindrome = new PalindromeCheck();
    
    // checks how many matching palindromatic numbers there are in both decimal and binary
    public void test_binaryPallinedromCheck() {
        int increments = 0;
        int doublePalindrome = 0;
        int binaryPalindromes = 0;
        int decimalPalindromes = 0;

        while (increments < MAX){
            if(palindrome.elementByElementPalindrome(String.valueOf(increments)) == true)
                decimalPalindromes++;
            if(palindrome.elementByElementPalindrome(String.valueOf(Integer.toBinaryString(increments))) == true)
                binaryPalindromes++;
            if(palindrome.elementByElementPalindrome(String.valueOf(increments)) == true &&
                 palindrome.elementByElementPalindrome(String.valueOf(Integer.toBinaryString(increments))) == true){
                    doublePalindrome++;
            }
            increments++;
        }

        System.out.println("\nBinary Double Palindrome Check\n============================");
        System.out.println("Decimal Palindromes: " + decimalPalindromes);
        System.out.println("Binary Palindromes: " + binaryPalindromes);
        System.out.println("Double Palindromes  " + doublePalindrome);
        System.out.println("============================");
    }

    // Checks the operations performed for decimal
    public void test_OperationsPerformed() {
        int amount_of_algos = 4;

        String[][] output = new String[MAX / INTERVAL][amount_of_algos + 1];
        for (int i = 0; i < amount_of_algos; i++) {
            // we will increment by 25,000 until we reach a million. We will use the values
            // to plot on a graph.
            int operations = 0;
            int increments = 0;
            while (increments < MAX) {
                if (increments % INTERVAL == 0) {
                    output[(int) increments / INTERVAL][0] = String.valueOf(increments);
                    output[(int) increments / INTERVAL][i + 1] = String.valueOf(operations);
                }
                switch (i) {
                    case 0:
                        palindrome.loopReversePalindrome(String.valueOf(increments));
                        operations += 3 + (2 * String.valueOf(increments).length()); // number of primitive operations
                                                                                     // per call
                        break;
                    case 1:
                        palindrome.elementByElementPalindrome(String.valueOf(increments));
                        // 3 primitve operations * input length / 2
                        operations += 3 + ( String.valueOf(increments).length() / 2); // number of primitive operations
                                                                                       // per call
                        break;
                    case 2:
                        palindrome.arrayStack_QueuePalindrome(String.valueOf(increments));
                        // 4 + 6n
                        operations += 7 + (8 * String.valueOf(increments).length()); // number of primitive operations
                                                                                     // per call

                        break;
                    case 3:
                        palindrome.recursiveMethodPalindrome(String.valueOf(increments));
                        // recursiveMethodPalindrome_operations += 4 + (2 * input.length()**2);
                        // number of primitive operations
                        // per call
                        operations += 7 + Math.pow(String.valueOf(increments).length(), 2);
                        break;

                }
                increments++;
            }

        }

        // exporting to file
        try {
            FileWriter file = new FileWriter("operation_test.csv");
            file.write("Operations Test\n\n");
            file.write("Increments, Method1, Method2, Method3, Method4\n");
            for (int col = 0; col < MAX / INTERVAL; col++) {
                for (int row = 0; row < amount_of_algos + 1; row++) {
                    file.write(output[col][row] + ",");
                }
                file.write("\n");
            }
            file.close();
            System.out.println("\nOperation check for Palindrome Methods\n============================");
            System.out.println("Export to file 'operation_test.csv'");
            System.out.println("============================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // checks the amount of operations performed in binary
    public void test_OperationsPerformedBinary() {
        int amount_of_algos = 4;

        String[][] output = new String[MAX / INTERVAL][amount_of_algos + 1];
        for (int i = 0; i < amount_of_algos; i++) {
            // we will increment by 25,000 until we reach a million. We will use the values
            // to plot on a graph.
            int operations = 0;
            int increments = 0;
            while (increments < MAX) {
                if (increments % INTERVAL == 0) {
                    output[(int) increments / INTERVAL][0] = String.valueOf(increments);
                    output[(int) increments / INTERVAL][i + 1] = String.valueOf(operations);
                }
                switch (i) {
                    case 0:
                        palindrome.loopReversePalindrome(Integer.toBinaryString(increments));
                        operations += 3 + (2 * Integer.toBinaryString(increments).length()); // number of primitive operations
                                                                                     // per call
                        break;
                    case 1:
                        palindrome.elementByElementPalindrome(Integer.toBinaryString(increments));
                        // 3 primitve operations * input length / 2
                        operations += 3 + ( Integer.toBinaryString(increments).length() / 2); // number of primitive operations
                                                                                       // per call
                        break;
                    case 2:
                        palindrome.arrayStack_QueuePalindrome(Integer.toBinaryString(increments));
                        // 4 + 6n
                        operations += 7 + (8 * Integer.toBinaryString(increments).length()); // number of primitive operations
                                                                                     // per call

                        break;
                    case 3:
                        palindrome.recursiveMethodPalindrome(Integer.toBinaryString(increments));
                        // recursiveMethodPalindrome_operations += 4 + (2 * input.length()**2);
                        // number of primitive operations
                        // per call
                        operations += 7 + Math.pow(Integer.toBinaryString(increments).length(), 2);
                        break;

                }
                increments++;
            }

        }

        // exporting to file
        try {
            FileWriter file = new FileWriter("operation_test_binary.csv");
            file.write("Operations Test Binary\n\n");
            file.write("Increments, Method1, Method2, Method3, Method4\n");
            for (int col = 0; col < MAX / INTERVAL; col++) {
                for (int row = 0; row < amount_of_algos + 1; row++) {
                    file.write(output[col][row] + ",");
                }
                file.write("\n");
            }
            file.close();
            System.out.println("\nOperation check in binary for Palindrome Methods\n============================");
            System.out.println("Export to file 'operation_test_binary.csv'");
            System.out.println("============================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // checks for time taken in decimal
    public void test_TimeTaken() {
        int amount_of_algos = 4;

        String[][] output = new String[MAX / INTERVAL][amount_of_algos + 1];

        for (int i = 0; i < amount_of_algos; i++) {
            // we will increment by 50,000 until we reach a million. We will use the values
            // to plot on a graph.
            int increments = 0;
            long start = 0;
            long end = 0;
            start = System.currentTimeMillis();
            while (increments < MAX) {

                if (increments % INTERVAL == 0) {
                    end = System.currentTimeMillis() - start;
                    output[(int) increments / INTERVAL][0] = String.valueOf(increments);
                    output[(int) increments / INTERVAL][i + 1] = String.valueOf(end);
                }
                switch (i) {
                    case 0:
                        palindrome.loopReversePalindrome(String.valueOf(increments));
                        break;
                    case 1:
                        palindrome.elementByElementPalindrome(String.valueOf(increments));
                        break;
                    case 2:
                        palindrome.arrayStack_QueuePalindrome(String.valueOf(increments));
                        break;
                    case 3:
                        palindrome.recursiveMethodPalindrome(String.valueOf(increments));
                        break;

                }
                increments++;
            }

        }

        // exporting to file
        try {
            FileWriter file = new FileWriter("time_test.csv");
            file.write("Time taken Test\n\n");
            file.write("Increments, Method1, Method2, Method3, Method4\n");
            for (int col = 0; col < MAX / INTERVAL; col++) {
                for (int row = 0; row < amount_of_algos + 1; row++) {
                    file.write(output[col][row] + ",");
                }
                file.write("\n");
            }
            file.close();
            System.out.println("\nTime taken check for Palindrome Methods\n============================");
            System.out.println("Export to file 'time_test.csv'");
            System.out.println("============================");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // check amount taken in binary
    public void test_TimeTakenBinary() {
        int amount_of_algos = 4;
        String[][] output = new String[MAX / INTERVAL][amount_of_algos + 1];

        for (int i = 0; i < amount_of_algos; i++) {
            // we will increment by 50,000 until we reach a million. We will use the values
            // to plot on a graph.
            int increments = 0;
            long start = 0;
            long end = 0;
            start = System.currentTimeMillis();
            while (increments < MAX) {
                if (increments % INTERVAL == 0) {
                    end = System.currentTimeMillis() - start;
                    output[(int) increments / INTERVAL][0] = String.valueOf(increments);
                    output[(int) increments / INTERVAL][i + 1] = String.valueOf(end);
                }
                switch (i) {
                    case 0:
                        palindrome.loopReversePalindrome(Integer.toBinaryString(increments));
                        break;
                    case 1:
                        palindrome.elementByElementPalindrome(Integer.toBinaryString(increments));
                        break;
                    case 2:
                        palindrome.arrayStack_QueuePalindrome(Integer.toBinaryString(increments));
                        break;
                    case 3:
                        palindrome.recursiveMethodPalindrome(Integer.toBinaryString(increments));
                        break;

                }
                increments++;
            }

        }

        // exporting to file
        try {
            FileWriter file = new FileWriter("time_test_binary.csv");
            file.write("Time taken in Binary Test\n\n");
            file.write("Increments, Method1, Method2, Method3, Method4\n");
            for (int col = 0; col < MAX / INTERVAL; col++) {
                for (int row = 0; row < amount_of_algos + 1; row++) {
                    file.write(output[col][row] + ",");
                }
                file.write("\n");
            }
            file.close();
            System.out.println("\nTime taken in Binart check for Palindrome Methods\n============================");
            System.out.println("Export to file 'time_test_binary.csv'");
            System.out.println("============================");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
