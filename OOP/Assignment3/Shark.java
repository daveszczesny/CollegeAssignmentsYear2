public class Shark extends Fish{
    
    // Class fields
    boolean canBite;
    boolean isDangerous;

    // Constructor
    public Shark(){
        super(); // Call inherited constructor method
        canBite = true;
        isDangerous = true; 
        colour = "grey";
    }


    // getters
    public boolean canBite(){
        return canBite;
    }

    public boolean isDangerous(){
        return isDangerous;
    }

    // Overrides strings method

    @Override
    public String toString(){
        String strng = "\n----- Shark -----\n";
        strng += "Has skin: " + hasSkin();
        strng +="\nBreathes: " + breathes;
        strng +="\nColour: " + getColour();
        strng += "\nHas fins: " + hasFins();
        strng += "\nSwims: " + swims;
        strng += "\nHas gills: " + hasGills();
        strng += "\nCan bite: " + canBite();
        strng += "\nIs dangerous: " + isDangerous();
        return strng;
    }

    // Overrides equals method

    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if(!(obj instanceof Shark) || obj == null)
            return false;
        
        Shark shark = (Shark)obj;
        return shark.hasSkin() == this.hasSkin() &&
                shark.breathes == this.breathes &&
                shark.getColour().equals(this.getColour()) &&
                shark.hasFins() == this.hasFins() &&
                shark.swims == this.swims &&
                shark.hasGills() == this.hasGills() &&
                shark.canBite() == this.canBite() &&
                shark.isDangerous() == this.isDangerous();
    }

}
