/*
 * Email class
 * Ran from the order class
 * Primarily a print class
 */

public class Email {
    public Email(boolean success, Order order) {

        // We check if all the details are correct
        if (!success) {
            // order unsuccessful email
            // this code runs if there is some error with either the address or the payment details

            System.out.println("\n============================");
            System.out.println("From: confirm@davetop.ie\n");
            System.out.printf("Sorry %s, but there was an issue with the processing of your order\n",
                    order.getCustomer().getName());
            System.out.println(
                    "Check the following error log and amend any issues.\nIf your problem continues please reach out to us at help@davetop.ie");
            System.out.println("The issues we found:\n" + order.getPaymentDetails().getInvalidationExcuse()
                    + order.getCustomer().getAddress().getInvalidationExcuse() + "\n");
            System.out.println("============================\n\n\n");
            return;
        }

        // order sucessful email
        // this code runs when everything is fine and the code works perfectly.
        System.out.println("Payment and address validation succesful\n");
        System.out.println("============================");
        System.out.println("From: confirm@davetop.ie\n");
        System.out.printf("Hey there %s, quickly wanted to let you know that your order will be with you soon!\n",
                order.getCustomer().getName());
        System.out.println("Customer ID: " + order.getCustomer().getID() + ", Order ID: " + order.getOrderNumber());
        System.out.println("Thanks for ordering from us");
        System.out.println("Items bought: ");
        for (Item item : order.getOrderItems()) {
            System.out.println(item);
        }
        System.out.printf("Total price %.2f euro.\n", order.getTotalPrice());
        System.out.println("Your order will be with you shortly at " + order.getCustomer().getAddress());
        System.out.println("DaveTop Ltd.");
        System.out.println("============================\n\n\n");

    }
}
