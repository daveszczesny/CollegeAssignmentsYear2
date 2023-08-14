/*
 * BusCompany abstract class
 * Describes everything that a bus company can do and should have
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public abstract class BusCompany{
    // name of bus company and an array for all trips
    String name;
    ArrayList<Trip> trips = new ArrayList<>();

    // loads provided trips from csv file
    public void loadTrips(String csvFile) {
        // try catch to handle any exceptions
        try{
            // reads in the csvFile one by one
            Scanner scanner = new Scanner(new File(csvFile));
            String tripDetails = scanner.nextLine();

            while(tripDetails!=null){
                tripDetails = tripDetails.replaceAll(" ", ""); // deletes spaces from file
                String arr[] = tripDetails.split(","); // splits trip details into segments to be then parsed into trip class
                Trip trip = new Trip(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8]);
                trips.add(trip); // adds trip to trips array
                tripDetails = scanner.nextLine();
            }
        }catch(Exception e){
            // ignores all exceptions that we run into.
        }
        
    }

    // Makes a booking
    public void makeBooking(Booking booking) {
        /*
         * Checks if trip exists and if there are enough seats for all the passengers
         */
        if (booking.getTrip() == null || booking.getPassengers() > booking.getTrip().capacity) {
            System.out.println("\n<---   Booking Failed!   --->\n\n");
            return;
        }

        // decrements available seats by the amount of passengers that bought a ticket.
        booking.getTrip().capacity -= booking.getPassengers();
        
        // prints out booking success message to user      
        System.out.println("\nBooking succesful!");
        System.out.println("+-----------------------------------------------+");
        System.out.println("| Numbers of passengers: " + booking.getPassengers());
        System.out.println("| Trip details: " + booking.getTrip().origin + " to " + booking.getTrip().destination);
        System.out.println("| Trip id: " + booking.getTrip().tripId);
        System.out.println("| Total cost: €" + booking.getTrip().fare * booking.getPassengers());
        System.out.println("+-----------------------------------------------+\n\n\n");
    }

    // getters
    // gets name of company
    public String getName() {
        return name;
    }

    // prints out all trips offered by bus company
    public void getTrips() {
        System.out.println(
                "Company\t\tTrip ID\t     Origin\t  Destination\t\bDept. Date  Dept. Time  Arr. Date\tArr. Time\tFare\t  Seats Available");
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------------------");
        trips.forEach(x -> {
            System.out.println(name + "\t" + x);
        });
        System.out.println();
    }

    // gets trip given its id
    public Trip getTrip(int id) {
        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).tripId == id) {
                return trips.get(i);
            }
        }

        System.err.println("<---   Trip doesn't exist  --->");
        return null;
    }

}