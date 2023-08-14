
public class Canary extends Bird
{
    
    String name; // the name of this Canary

    /**
     * Constructor for objects of class Canary
     */
    public Canary(String name)
    {
        super(); // call the constructor of the superclass Bird
        this.name = name;
        colour = "yellow"; // this overrides the value inherited from Bird
        
    }
    
    /**
     * Sing method overrides the sing method
     * inherited from superclass Bird
     */
    @Override // good programming practice to use @Override to denote overridden methods
    public void sing(){
        System.out.println("tweet tweet tweet");
    }
    
    /**
     * toString method returns a String representation of the bird
     * What superclass has Canary inherited this method from? 
     */

    @Override
    public String toString(){
        String strng ="";
        strng+= "\n----- Canary -----\n";
        strng+= "name: " + name;
        strng+= "\nColour: " + colour;
        strng+= "\nHas feathers: " + hasFeathers();
        strng+= "\nHas wings: " + hasWings();
        strng+= "\nFlies: " + flies;
        return strng;
    }

    
    /** 
     * equals method defines how equality is defined between 
     * the instances of the Canary class
     * param Object
     * return true or false depending on whether the input object is 
     * equal to this Canary object
     */
    
    @Override
    public boolean equals(Object obj){

        // checks if obj is equal to itself
        if(obj == this){
            return true;
        }

        // checks if the obj is a null pointer or not an instance of Canary
        if(!(obj instanceof Canary) || obj == null){
            return false;
        }

        // Casts obj to canary and checks its fields
        Canary canary = (Canary)obj;
        return canary.name.equals(this.name) &&
                canary.getColour().equals(this.getColour()) &&
                canary.hasFeathers() == this.hasFeathers() &&
                canary.hasWings() == this.hasWings() &&
                canary.flies == this.flies;
    }
}
