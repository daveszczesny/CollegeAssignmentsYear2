import java.time.LocalDate; // to get current date

/*
Payment class
Takes card type, card number, expiry date
*/

public class Payment {
    private String cardType;
    private String cardNumber;
    private String expiryDate;
    private String inValidExcuse = "";

    // constructor
    public Payment(String cardType, String cardNumber, String expiryDate) {
        this.cardType = cardType.toLowerCase(); // sets card type to lowercase for simplicity
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    // checks all payment details
    // ensures card number is 16 digits
    // that the card is either visa or mastercard
    // and that the current date is before the expiry datte
    public boolean checkPaymentDetails() {
        if (cardNumber.length() != 16) {
            inValidExcuse += "Card number not 16 digits\n";
        }
        if ((cardType != "visa" && cardType != "mastercard")) {
            inValidExcuse += "Card type invalid\n";
        }

        // Check Expiry Date against current date
        // gets the current date and converts it to a string array of its year, month,
        // and day
        // format progress
        // YYYY-MM-DD as Date data type -> YYYY-MM-DD as String data type -> "YYYY",
        // "MM","DD" as String array
        String[] currentDate = LocalDate.now().toString().split("-");
        // we parse and split our string array taking the year and month and
        // reorganising it to MMYY as a number
        int cd = Integer.valueOf(currentDate[0].substring(2) + currentDate[1]);

        // we make sure there are no negative dates by deleting the minus sign
        // we parse and split our string into the desired format
        expiryDate = expiryDate.replace("-", "");
        int exDate = Integer.valueOf(expiryDate.substring(3) + expiryDate.substring(0, 2));

        // we compare the dates
        if (cd > exDate) {
            inValidExcuse += "Card is out of date\n";
        }

        // checks if the explaination for failure is more than 25 characters
        if (inValidExcuse.length() > 0) {
            System.out.println("Process failed!");
            return false;
        }
        return true;

    }

    // getter
    public String getInvalidationExcuse() {
        return inValidExcuse;
    }
}
