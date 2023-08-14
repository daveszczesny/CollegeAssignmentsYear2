/*
 * @Authour: Dave
 * ID: 21300293
 * Date: 04/11/21
 */

public class TravelIreland {
    public static void main(String[] args) {

        // Testing
        TravelIreland travelIreland = new TravelIreland();
        travelIreland.scenerioOne();
        travelIreland.scenerioTwo();
        travelIreland.scenerioThree();
        travelIreland.scenerioFour();

    }

    /*
     * Working example of booking
     * User makes booking for 5 passengers from busEireann for the Galway Limerick bus
     * Prints out confirmation message
     */
    public void scenerioOne(){
        System.out.println("Scenerio One ------------------------------\n\n\n");
        BusCompany busEireann = new BusEireann();
        busEireann.getTrips();

        Trip trip = busEireann.getTrip(10105);
        Booking myBooking = new Booking(trip, 5);
        busEireann.makeBooking(myBooking);

        busEireann.getTrips();
    }

    /*
     * Booking failure example
     * Booking failed due to the lack of seats available onboard
     */
    public void scenerioTwo(){
        System.out.println("Scenerio Two  ------------------------------\n\n\n");
        BusCompany cityLink = new CityLink();
        cityLink.getTrips();

        Trip trip = cityLink.getTrip(12112);
        Booking myBooking = new Booking(trip, 100);
        cityLink.makeBooking(myBooking);

        cityLink.getTrips();
    }

    /*
     * Booking failed example 2
     * Booking failed as the trip ID given was wrong
     * No trip with trip id of 99102 exists.
     */
    public void scenerioThree(){
        System.out.println("Scenerio Three ------------------------------\n\n\n");
        BusCompany goBus = new GoBus();
        goBus.getTrips();

        Trip trip = goBus.getTrip(99102);
        Booking myBooking = new Booking(trip, 5);
        goBus.makeBooking(myBooking);
        goBus.getTrips();
    }

    /*
     * Multiple bookings made in the one session
     */
    public void scenerioFour(){
        System.out.println("Scenerio Four ------------------------------\n\n\n");
        BusCompany cityLink = new CityLink();
        cityLink.getTrips();

        Trip trip1 = cityLink.getTrip(12112);
        Booking myBooking1 = new Booking(trip1, 20);
        cityLink.makeBooking(myBooking1);

        cityLink.getTrips();

        Trip trip2 = cityLink.getTrip(12112);
        Booking myBooking2 = new Booking(trip2, 10);
        cityLink.makeBooking(myBooking2);

        Trip trip3 = cityLink.getTrip(90901);
        Booking myBooking3 = new Booking(trip3, 15);
        cityLink.makeBooking(myBooking3);

        cityLink.getTrips();
    }
}   