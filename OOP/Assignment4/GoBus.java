/*
 * Class that a bus company would have to submit to work with our program
 */

public class GoBus extends BusCompany {
    public GoBus() {
        super();
        // sets the name of the company to the class Name
        this.name = getClass().getSimpleName() + "\t";
        loadTrips("goBus.csv");// loads the provided csv file from vendor
    }

}
