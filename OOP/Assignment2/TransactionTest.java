/*
Author: Dawid Szczesny
ID: 21300293
*/

public class TransactionTest {
    // Main method
    // Starting point of the program
    public static void main(String[] args) {
        TransactionTest test = new TransactionTest();
        // Test methods, to test different scenarios
        test.transactionTest1();
        test.transactionTest2();
        test.transactionTest3();
        test.transactionTest4();
        test.transactionTest5();
        test.transactionTest6();
    }

    /*
     * Scenario One
     * From the Lecture notes
     * Customer adds 3 items, and orders successfully
     */
    public void transactionTest1() {
        System.out.println(
                " --------------------------------------------New Order --------------------------------------------\n");
        // Creates our customer. We set their name, last name, and email address.
        Customer customer = new Customer("Craig", "Smith", "craig.smith@gmail.com");

        // We create 3 items. We give their name and their price.
        Item item1 = new Item("Gloves", 12.99F);
        Item item2 = new Item("Cucumber", .69F);
        Item item3 = new Item("Butter", 4.69F);

        // We add the 3 items into our cart.
        customer.addToCart(item1, item2, item3);
        Address address = new Address("Street 1", "H91Kc9h");
        customer.setAddress(address);

        // We make a payment method, that takes the cart type either visa or mastercard,
        // the card number and the expiry date.
        Payment payment = new Payment("visa", "4587985698547854", "01/23");

        // We create an order instance
        // We begin the process and then confirm it
        Order order = new Order(customer, payment);
        order.process();
        order.confirm();
    }

    /*
     * Scenario 2
     * From Assignment 2
     * Customer adds 3 items, displays them and the total price
     * The the customer removes one item and continues to the payment method
     * In this scenario, the order fails, as the card is out of date by one month
     */
    public void transactionTest2() {

        System.out.println(
                " --------------------------------------------New Order --------------------------------------------\n");

        Customer customer = new Customer("Craig", "Smith", "craig.smith@gmail.com");

        Item item1 = new Item("Nerf gun", 70.0f);
        Item item2 = new Item("Nerf bullets", 20.f);
        Item item3 = new Item("Lego people", 15.f);

        customer.addToCart(item1, item2, item3);

        customer.displayCartContents();
        customer.displayCartTotal();
        customer.removeFromCart(item2);
        customer.lockCart();
        Address address = new Address("Street 1", "H91Kc9h");
        customer.setAddress(address);
        // create payment
        // The card in this scenario is out of date and hence will fail the order.
        Payment payment = new Payment("visa", "4587985698547854", "08/22");

        Order order = new Order(customer, payment);
        order.process();
        order.confirm();
    }

    /*
     * Scenario 3
     * Custom
     * We test the implementation of seperate billing addresses
     * We also check if mastercard is a valid card type
     */
    public void transactionTest3() {
        System.out.println(
                " --------------------------------------------New Order --------------------------------------------\n");
        // The address constructor allows for us to omit the billing addresses which
        // entails that it is the same as the shipping address

        Customer customer = new Customer("Dave", "Thompson", "d.thompson@nuigalway.ie");

        Item item1 = new Item("Keyboard", 155.0F);
        Item item2 = new Item("Almond Milk", 2.55F);
        Item item3 = new Item("Dental Floss", 1.67F);

        customer.addToCart(item1, item2);
        customer.lockCart();
        customer.addToCart(item3);
        customer.displayCartContents();
        Address address = new Address("41 new road, New Ross, Co.Down", "F45K289", "Eyre Square, Galway", "H91ED63");
        customer.setAddress(address);
        Payment payment = new Payment("mastercard", "4587985698547854", "01/23");
        Order order = new Order(customer, payment);
        order.process();
        order.confirm();
    }

    /*
     * Scenario 4
     * Custom
     * We test what would happen if we gave an invalid email address. (There is no
     * '@' symbol in it)
     * We also give an invalid address, as the shippingAddress can't be one number
     * and the Eircode most be valid
     * We also set the card expiry date to a negative number.
     */
    public void transactionTest4() {
        System.out.println(
                " --------------------------------------------New Order --------------------------------------------\n");

        Customer customer = new Customer("Dave", "Thompson", "d.thompsonnuigalway.ie");

        Item item1 = new Item("Keyboard", 155.0F);
        Item item2 = new Item("Almond Milk", 2.55F);

        customer.addToCart(item1, item2);
        customer.displayCartContents();
        customer.displayCartTotal();
        customer.lockCart();
        Address address = new Address("1", "8291", "Eyre Square, Galway", "123");
        customer.setAddress(address);
        customer.setAddress(address);
        Payment payment = new Payment("MyMasfer", "12311121", "-01/23");
        Order order = new Order(customer, payment);
        order.process();
        order.confirm();
    }

    /*
     * Scenario 5
     * Custom
     * Testing wrong card details
     */
    public void transactionTest5() {
        System.out.println(
                " --------------------------------------------New Order --------------------------------------------\n");

        Customer customer = new Customer("Dave", "Thompson", "d.thompson@nuigalway.ie");

        Item item1 = new Item("Typewriter", -5.0F);
        Item item2 = new Item("Baked Potatoes", 2.55F);
        Item item3 = new Item("Toilet Paper", 1.67F);

        customer.addToCart(item1, item2, item3);
        customer.displayCartContents();
        customer.displayCartTotal();
        customer.lockCart();
        Address address = new Address("The Occupant, House Master", "H92 89XQ");
        customer.setAddress(address);
        Payment payment = new Payment("MyMasfer", "12311121", "12/23");
        Order order = new Order(customer, payment);
        order.process();
        order.confirm();
    }

    /*
     * Scenario 6
     * Custom
     * Testing if there were no items in the cart
     * We then add one item to ensure its working
     */
    public void transactionTest6() {
        System.out.println(
                " --------------------------------------------New Order --------------------------------------------\n");

        Customer customer = new Customer("Dave", "Thompson", "d.thompson@nuigalway.ie");

        customer.displayCartContents();
        customer.displayCartTotal();
        Address address = new Address("The Occupant, House Master", "H92 89XQ");
        customer.setAddress(address);

        customer.lockCart();

        Payment payment = new Payment("visa", "4587452145632145", "12/23");
        Order order = new Order(customer, payment);
        order.process();
        order.confirm();

        Item item1 = new Item("Banana", 0.5F);
        customer.addToCart(item1);
        order.process();
        order.confirm();
    }
}