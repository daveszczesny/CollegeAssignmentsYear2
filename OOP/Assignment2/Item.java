/*
 * Item class
 * Just has the item name and its price
 */
public class Item{
    private String name;
    private float price;
    public Item(String name, float price){
        this.name = name;
        // We check to see if the price is a positive number or non-zero, if it isn't we set it to one cent.
        this.price = price > 0 ? price : 0.01F;
    }
   
    // getters
    public String getName(){
        return name;
    }
    public float getPrice(){
        return price;
    }
    
    // Override print method so when we print we'll get the item name and cost
    @Override
    public String toString(){
        return getName() + " costs " + getPrice() + " euro.";
    }
}