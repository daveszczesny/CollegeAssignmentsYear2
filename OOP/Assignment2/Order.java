import java.util.ArrayList;

/*
Order class
Check customer details
*/
public class Order {
    private static int orderNumber = 0; // static variable to make each order number unique
    private Customer customer;
    private Payment payment;
    private ArrayList<Item> orderArray;
    private float totalPrice = 0;

    public Order(Customer customer, Payment payment) {
        this.customer = customer;
        this.payment = payment;

        // for each new order we increment the order number to make it a unique order
        // orderNumber is a static variable which allows it to retain its value.
        orderNumber++;
    }

    public void process() {
        // process order code
        // copying the cart to our order cart array
        orderArray = new ArrayList<Item>(customer.getCart().getItemArray());
        // can also be done using an iterative array and using orderArray.add(item)
        // we clear the customer's cart
        customer.getCart().getItemArray().clear();
        // keep the total price and then reset it for the customer
        totalPrice = customer.getCart().getTotalPrice();
        customer.getCart().setTotal(0);
    }

    // confirm order method
    public void confirm() {
        // ensures that there is something in our cart
        if (orderArray.size() <= 0) {
            System.out.println("Your cart is currently empty! Consider adding a few items and try again!\n");
            return;
        }

        /*
         * This method will confirm the items that the customer is about to purchase.
         */
        // prints out the items
        System.out.println("----------Items in cart----------");

        orderArray.forEach((item) -> System.out.println(item)); // prints out each item in the array

        // prints the total
        System.out.printf("---------- Total ----------\n%.2f\n", totalPrice);
        System.out.println("-------------------------------");
        System.out.println("Order processing ... ");

        // We check that the customer has a valid email address before progressing
        if (customer.getValidEmail()) {
            // If they do we send them on a confirmation email, on whether their order went
            // through or not
            new Email(getStatus(), this);
            return;
        }

        // We should only run this code when the email is invalid
        System.out.println("Order processing failed due to invalid email\n\n");
        return;

    }

    // getters
    public int getOrderNumber() {
        return orderNumber;
    }

    public ArrayList<Item> getOrderItems() {
        return orderArray;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public boolean getStatus() {
        // this will check if all the details are correct.
        return payment.checkPaymentDetails() && customer.getAddress().getValidation();

    }

    public Customer getCustomer() {
        return customer;
    }

    public Payment getPaymentDetails() {
        return payment;
    }

}