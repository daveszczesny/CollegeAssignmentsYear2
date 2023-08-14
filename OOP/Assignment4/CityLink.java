/*
 * Class that a bus company would have to submit to work with our program
 */

public class CityLink extends BusCompany {
    public CityLink() {
        super();
        // sets the name of the company to the class Name
        this.name = getClass().getSimpleName();
        loadTrips("cityLink.csv");// loads the provided csv file from vendor

    }
}
