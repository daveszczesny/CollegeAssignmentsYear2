/*
 * Booking class
 */

public class Booking {
    Trip trip;
    int passengers;

    // constructor
    public Booking(Trip trip, int passengers){
        this.trip = trip;
        this.passengers = passengers;
    }


    // getters
    public Trip getTrip(){
        return trip;
    }

    public int getPassengers(){
        return passengers;
    }

}
