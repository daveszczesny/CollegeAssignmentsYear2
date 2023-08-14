public class PalindromeCheck {
    // Method One
    public boolean loopReversePalindrome(String input) {
        // We are linearly tracking each element, hence
        // O(n) is the big O notation for this method
        // Reversing a string using a loop
        String reversedInput = new String(""); // 1 primitive operation

        // loops through string from end to start appending to new string
        // 1 + 2 * input.length()
        for (int i = input.length() - 1; i >= 0; i--) {
            reversedInput += input.charAt(i); // method call, and variable assignment (2 operations)
        }
        return checkPalindrome(input, reversedInput); // 1 primitive operation
        // 3 + 2*input length primitive operations

    }

    // method two
    public boolean elementByElementPalindrome(String input) {

        /*
         * For loop iterates through every character in input
         * Checking that the character at i matches the character at length - i
         * If they all match then string is a palindrome
         * if not then not a palindrome.
         */

        // each method call inside of the if statement is an operation times input
        // length
        // 3 * input length
        // for loop statement is one operation
        // Therefore the big o notation is O(n/2) -> O(n)

        for (int i = 0; i < input.length(); i++) { // 1 primitive operation
            if (input.charAt(i) != input.charAt(input.length() - 1 - i)) { // 3 primitve operations * input length / 2
                return false;
            }
        }
        return true;

    }

    // method three
    public boolean arrayStack_QueuePalindrome(String input) {
        ArrayStack array = new ArrayStack(input.length()); // 1 operation
        ArrayQueue queue = new ArrayQueue(input.length()); // 1 operation

        for (char ch : input.toCharArray()) { // one operations
            array.push(ch); // 1 * input length
            queue.enqueue(ch); // 1 * input length
        }
        for (int i = 0; i < input.length(); i++) { // 1 operation
            if ((char) array.pop() != (char) queue.dequeue()) { // 2 * input length
                return false;
            }
        }

        while (!array.isEmpty() && !queue.isEmpty()) {
            if ((char) array.pop() != (char) queue.dequeue()) { 
                return false;
            }
        }

        return true;
    }

    /*
     * recursive method would run quadratically as it would split the string after
     * each iteration.
     * hence big o notation of O(n^2)
     */
    public boolean recursiveMethodPalindrome(String input) {
        if (input.equals(reverse(input))) { // 2 operation
            return true;
        }
        return false;
    }

    private String reverse(String input) {
        if (input.length() <= 1) { // 1 operation
            return input;
        }
        return reverse(input.substring(1)) + input.charAt(0);
    }

    // checks the palindrome
    private boolean checkPalindrome(String string1, String string2) {
        if (string1.equals(string2)) {
            return true;
        }
        return false;
    }
}
