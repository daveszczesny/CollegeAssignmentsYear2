import java.util.ArrayList;

/*
ShoppingCart class
Holds the items, has the ability to add, remove, and display each item
Method to display the contents of the cart and the total price of it

*/
public class ShoppingCart {
    private ArrayList<Item> itemArray = new ArrayList<Item>();
    private float totalPrice = 0.F;
    private boolean locked = false;

    // Adds items to the cart
    // Has the ability to add mulitply Items add a time
    public void add(Item... items) {
        if(locked){
            System.out.println("Cart is locked, cannot add items");
            return;
        }
        for (Item item : items) {
            System.out.println("Adding item - " + item.getName());
            itemArray.add(item);
            totalPrice += item.getPrice(); // Adds up price as we add items
        }
    }

    // locks the cart so no more items can be added or removed
    public void lock(){
        locked = true;
    }

    // removes items
    public void remove(Item item) {
        if(locked){
            System.out.println("Cart is locked, cannot remove items");
            return;
        }
        System.out.println("removing item - " + item.getName());
        itemArray.remove(item);
        totalPrice -= item.getPrice(); // keeps track of price even when removing items
    }

    // displays the contents of the cart
    public void displayContents() {
        System.out.println("-----Cart contents-----");
        for (Item item : itemArray) {
            System.out.printf("%s\n", item);
        }
        System.out.println("---------------");
    }

    // display total price
    public void displayTotal() {
        System.out.printf("Total price for items in the cart is %.2f euro.\n\n", getTotalPrice());
    }

    //setters
    public void setTotal(int total) {
        totalPrice = total;
    }

    //getters
    public float getTotalPrice() {
        return totalPrice;
    }

    
    public ArrayList<Item> getItemArray() {
        return itemArray;
    }

}