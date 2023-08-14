public class Ostrich extends Bird{

    // Class fields
    boolean isTall;
    boolean hasLongThinLegs;

    // Constructor
    public Ostrich(){
        super();
        isTall = true;
        hasLongThinLegs = true;
        flies = false;
    }

    // getters
    public boolean isTall(){
        return isTall;
    }

    public boolean hasLongThinLegs(){
        return hasLongThinLegs;
    }


    // Overrides toString method
    @Override
    public String toString(){
        String strng ="";
        strng+= "\n----- Ostrich -----\n";
        strng+= "\nHas feathers: " + hasFeathers();
        strng+= "\nHas wings: " + hasWings();
        strng+= "\nColour: " + getColour();
        strng+= "\nFlies: " + flies;
        strng+="\nIs tall: " + isTall();
        strng+="\nHas long thin legs: " + hasLongThinLegs();
        return strng;
    }

    // Overrides equals method
    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        
        if(!(obj instanceof Ostrich) || obj == null)
            return false;
        

        Ostrich ostrich = (Ostrich)obj;
        return ostrich.colour.equals(this.colour) &&
                ostrich.hasFeathers() == this.hasFeathers() &&
                ostrich.hasWings() == this.hasWings() &&
                ostrich.flies == this.flies &&
                ostrich.isTall() == this.isTall() &&
                ostrich.getColour().equals(this.getColour()) &&
                ostrich.hasLongThinLegs() == this.hasLongThinLegs();
    }

}