import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class App extends JFrame {

    // Constructor, runs the window
    public App() {
        createGUI();
    }

    // methods translates infix notation to postfix notation
    public String convertInfix(String input) {
        // Creates a stack of capacity 20
        ArrayStack arr = new ArrayStack(20);

        // A character array of the input string,
        // regex expression to remove all spaces
        // toCharArray converts the string to characters
        char[] chars = input.replaceAll("\\s+", "").toCharArray();
        String output = new String("");

        // if the input string is longer than 20, display message to user
        if (chars.length >= 20) {
            JOptionPane.showMessageDialog(null, "Infix input too long!");
            return "";
        }

        // regex to check for double digtis
        String[] checkDoubleDigits = input.split("([\\+\\-\\*\\^\\(\\)])");
        for (String digit : checkDoubleDigits) {
            if(digit.length() == 0){
                continue;
            }
            if (Double.parseDouble(digit) > 9) {
                JOptionPane.showMessageDialog(null, "Infix input contains digitis greater than 9!");
                return "";
            }
        }

        // regex to checks for double operators        
        String[] checkDoubleOperators = input.split("([0-9])");
        for (String operator : checkDoubleOperators) {
            if (operator.length() > 1) {
                JOptionPane.showMessageDialog(null, "Infix input contains double operators! Negative numbers and others are not supported");
                return "";
            }
        }

        // looping through all the characters
        for (char ch : chars) {

            // if the character is a letter, return lack of support for algebraic
            // expressions.
            if (Character.isAlphabetic(ch)) {
                JOptionPane.showMessageDialog(null, "Infix notation convertor doesn't support algebraic expressions");
                return "";
            }

            // character is an operand
            if (Character.isDigit(ch)) {
                output += ch;

                // character is an operator
            } else if (ch == '(') {
                arr.push(ch);
            } else if (ch == ')') {
                // if character is ')' look through the array until we find the opening bracket
                // and append the result in the output string
                while (!arr.isEmpty() && (char) arr.top() != '(') {
                    output += (char) arr.top();
                    arr.pop();
                }
                arr.pop();
            } else {
                // if the percendence of our scanned operator is less than or equal to the
                // operator at the top of the stack
                while (!arr.isEmpty() &&
                        percedence(ch) <= percedence((char) arr.top())) {
                    output += (char) arr.top();
                    arr.pop();
                }
                arr.push(ch);
            }

        }

        // moves all the operators from the stack to the output string
        while (!arr.isEmpty()) {
            if ((char) arr.top() != '(') {
                output += (char) arr.top();
                arr.pop();
            }
        }
        return output;


    }

    // percedence method, to find the percedence of an operator
    public int percedence(char ch) {
        switch (ch) {
            case '-':
            case '+':
                return 1;
            case '/':
            case '*':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }


    /*
     * Method to solve postfix notational maths
     * Loops through the characters, operands are pushed to the stack
     * If an operator is encountered we take the top 2 operands from the stack
     * and perform the operation on them through the compute method
     * the result is pushed back onto the stack
     * After all characters are read through we return the top of the stack
     */
    private String solveInput(String postfix) {
        ArrayStack stack = new ArrayStack(20);
        char[] chars = postfix.toCharArray();
        for (char ch : chars) {

            if (Character.isDigit(ch)) {
                stack.push(ch);
            } else {
                if (!stack.isEmpty()) {
                    double num1 = Double.parseDouble(String.valueOf(stack.top()));
                    stack.pop();
                    if (!stack.isEmpty()) {
                        double num2 = Double.parseDouble(String.valueOf(stack.top()));
                        stack.pop();
                        stack.push(compute(num1, num2, ch));
                    }
                }

            }
        }

        // if the stack isn't empty we return the answer
        if (!stack.isEmpty()) {
            return stack.top().toString();
        }
        // something has gone wrong and the stack is empty
        return "Nan";
    }

    // compute method, takes in two operands and an oeprator and returns the
    // resulting double
    private double compute(double num1, double num2, char operator) {

        switch (operator) {
            case '-':
                return num2 - num1;
            case '+':
                return num2 + num1;
            case '*':
                return num2 * num1;
            case '/':
                return num2 / num1;
            case '^':
                return Math.pow(num2, num1);
            default:
                return 0;

        }
    }

    /*
     * createGUI
     * sets up window.
     */
    public void createGUI() {
        this.setTitle("ArrayStack Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creating all labels, text fields, buttons
        JLabel infixLabel = new JLabel("Infix");
        JTextField textField = new JTextField(20);
        JButton button = new JButton("Convert");

        JLabel postLabel1 = new JLabel("Post fix:");
        JLabel postLabel2 = new JLabel("");
        JLabel resultLabel1 = new JLabel("Result");
        JLabel resultLabel2 = new JLabel("");

        // adding them all to frame
        add(infixLabel);
        add(textField);
        add(button);
        add(postLabel1);
        add(postLabel2);
        add(resultLabel1);
        add(resultLabel2);
        pack();

        // creating a springlayout
        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        // setting constraints to making gui appealing to user
        layout.putConstraint(SpringLayout.WEST, infixLabel, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, infixLabel, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, textField, 5, SpringLayout.EAST, infixLabel);
        layout.putConstraint(SpringLayout.NORTH, textField, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, button, 5, SpringLayout.EAST, textField);
        layout.putConstraint(SpringLayout.NORTH, button, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, postLabel1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, postLabel1, 9, SpringLayout.SOUTH, infixLabel);

        layout.putConstraint(SpringLayout.WEST, postLabel2, 5, SpringLayout.EAST, postLabel1);
        layout.putConstraint(SpringLayout.NORTH, postLabel2, 5, SpringLayout.SOUTH, textField);

        layout.putConstraint(SpringLayout.WEST, resultLabel1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, resultLabel1, 9, SpringLayout.SOUTH, postLabel1);

        layout.putConstraint(SpringLayout.WEST, resultLabel2, 5, SpringLayout.EAST, resultLabel1);
        layout.putConstraint(SpringLayout.NORTH, resultLabel2, 5, SpringLayout.SOUTH, postLabel1);

        // adding action listener to button
        // When pressed the text from the text field is taken into the convertInfix()
        // method
        // which the result of is then passed to the solveInput method
        // the results of both are then displayed to the labels
        button.addActionListener(e -> {
            postLabel2.setText(convertInfix(textField.getText()));
            resultLabel2.setText(solveInput(postLabel2.getText()));

            // prints the results to console
            System.out.println(
                    "Infix Notation: " + textField.getText() +
                            "\nPostfix Notation: " + postLabel2.getText() +
                            "\nResult: " + resultLabel2.getText());
        });

        // setting minimum size, and visibility of frame
        setMinimumSize(new Dimension(400, 150));
        setVisible(true);
    }

    public static void main(String[] args) {
        App app = new App();
    }
}
