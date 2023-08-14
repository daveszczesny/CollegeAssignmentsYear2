/*
 * Class that a bus company would have to submit to work with our program
 */

public class BusEireann extends BusCompany {
    public BusEireann() {
        super();
        // sets the name of the company to the class Name
        this.name = getClass().getSimpleName();

        loadTrips("busEireann.csv");  // loads the provided csv file from vendor
    }


}
