public class Trout extends Fish{
    
    // Class fields
    boolean hasSpikes;
    boolean isEdible;
    String layingGround;

    // Constructor
    public Trout(){
        super();

        hasSpikes=true;
        isEdible=true;
        layingGround="Upriver";
        colour = "brown";
    }



    // field getters
    public boolean hasSpikes(){
        return hasSpikes;
    }
    public boolean isEdible(){
        return isEdible;
    }
    public String layingGround(){
        return layingGround;
    }

    // Overrides toString method

    @Override
    public String toString(){
        String strng = "\n----- Trout -----\n";
        strng += "Has skins: " + hasSkin();
        strng += "\nBreaths: "+breathes;
        strng+="\nColour: "+getColour();
        strng+="\nHas fins: "+hasFins();
        strng+="\nSwims: "+swims;
        strng+="\nHas gills: " + hasGills();
        strng+="\nHas spikes: " + hasSpikes();
        strng+="\nIs edible: " + isEdible();
        strng+="\nLaying ground: " + layingGround();
        return strng;
    }

    // Overrides equals method

    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if(!(obj instanceof Trout) || obj == null)
            return false;
        Trout trout = (Trout)obj;
        return trout.hasSkin() == this.hasSkin() &&
                trout.breathes == this.breathes &&
                trout.getColour() == this.getColour() &&
                trout.hasFins() == this.hasFins() &&
                trout.swims == this.swims &&
                trout.hasGills() == this.hasGills() &&
                trout.hasSpikes() == this.hasSpikes() &&
                trout.isEdible() == this.isEdible() &&
                trout.layingGround().equals(this.layingGround());
    }

    
}