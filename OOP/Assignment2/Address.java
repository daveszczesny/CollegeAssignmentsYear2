
/*
Address Class
Responsible for holding and validating the address of the customer
*/
public class Address {
    private boolean addressValid = false;
    private String shippingAddress;
    private String shippingEircode;
    private String billingAddress;
    private String billingEircode;
    // An error log string. This will be sent to the customer via email
    private String invalidAddressExcuse = "";

    // Address constructor for same shipping and Billing addresses
    public Address(String shippingAddress, String shippingEircode) {

        this.shippingAddress = billingAddress = shippingAddress;
        this.shippingEircode = billingEircode = shippingEircode;
        // checks if the address is valid
        isValid();
        // Printing to customer that we are using the same address for both the shipping
        // and billing
        System.out.println("Shipping address same as billing!");
    }

    // Address constructor for different shipping and billing addresses
    public Address(String shippingAddress, String shippingEircode, String billingAddress, String billingEircode) {
        this.shippingAddress = shippingAddress;
        this.shippingEircode = shippingEircode;
        this.billingAddress = billingAddress;
        this.billingEircode = billingEircode;
        // checks if the address is valid
        isValid();

    }

    // getters
    public String getInvalidationExcuse() {
        return invalidAddressExcuse;
    }

    public boolean getValidation() {
        return addressValid;
    }


    // validation method. This method is set to private to ensure no external class can run this method as there is no need to
    private void isValid() {
        // checks if the eircode is composed of 7 characters; ignoring the spaces, and
        // check if the address itself is greater than 5 characters
        addressValid = shippingEircode.replace(" ", "").length() == 7
                    && billingEircode.replace(" ", "").length() == 7
                    && shippingAddress.length() > 5 && this.billingAddress.length() > 5;
        if (!addressValid) {
            invalidAddressExcuse += "Invalid Address";
        }
    }

    // Override method, used for when printing address
    @Override
    public String toString() {
        return shippingAddress + ", " + shippingEircode;
    }
}