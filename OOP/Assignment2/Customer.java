/*
Customer class, responsible for holding info about the customer
*/

public class Customer {
    private String firstName;
    private String surName;
    private String email;
    private boolean validEmail = false;
    private Address address;
    private ShoppingCart cart;

    // we create a static variable to ensure that it does not restart count after
    // every new customer, to ensure no two customers have the same customerID
    private static int customerID = 0;

    public Customer(String firstName, String surName, String email) {
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        validEmail = this.email.contains("@");
        customerID++;
        cart = new ShoppingCart();
    }

    // setters
    // sets the address to customer
    public void setAddress(Address address) {
        this.address = address;
    }

    public void addToCart(Item... items) {

        cart.add(items);
    }

    public void removeFromCart(Item item) {
        cart.remove(item);
    }

    // getters
    // checks if the customer's email has the correct format.
    public boolean getValidEmail() {
        return validEmail;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return firstName;
    }

    public String getSureName() {
        return surName;
    }

    public int getID() {
        return customerID;
    }

    // getters for cart info
    public void displayCartContents() {
        cart.displayContents();
    }

    public void displayCartTotal() {
        cart.displayTotal();
    }

    public void lockCart() {
        cart.lock();
    }

}