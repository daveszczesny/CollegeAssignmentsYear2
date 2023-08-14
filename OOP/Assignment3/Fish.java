public class Fish extends Animal {
    // Class fields
    boolean hasFins;
    boolean swims;
    boolean hasGills;

    // Constructor
    public Fish(){
        super();
        hasFins = true;
        swims = true;
        hasGills = true;
    }

    // overriding move from animal class
    // move and swim are technically the same thing
    @Override
    public void move(int distance){
        String msg = (swims ? "I swim " : "I move ") + distance + " metres";
        System.out.println(msg);
    }

    // getters
    public boolean hasFins(){
        return hasFins;
    }

    public boolean hasGills(){
        return hasGills;
    }


}
