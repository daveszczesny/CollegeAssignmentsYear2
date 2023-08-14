/*
 * Basic trip format class
 */

import java.util.Formatter;
import java.util.Locale;

public class Trip {
    // every variable that a trip needs to have
    // variable declaration
    int tripId;
    String origin;
    String destination;
    String deptDate;
    String deptTime;
    String arrTime;
    String arrDate;
    float fare;
    int capacity;

    // constructor
    public Trip(String tripId, String origin, String dest, String deptDate,
            String deptTime, String arrDate, String arrTime,
            String fare, String capacity) {
        // we parse some Strings to Integers and Floats
        this.tripId = Integer.parseInt(tripId);
        this.origin = origin;
        this.destination = dest;
        this.deptDate = deptDate;
        this.deptTime = deptTime;
        this.arrDate = arrDate;
        this.arrTime = arrTime;
        this.fare = Float.parseFloat(fare);
        this.capacity = Integer.parseInt(capacity);

    }

    /*
     * Override toString method to allow us to print out the trips
     * in a nice layout
     */

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();
        Formatter formatter = new Formatter(buffer, Locale.US);
        formatter.format("%d\t%12s\t%12s\t%8s\t%5s\t%8s\t%5s\t\t€%.2f\t\t%d", tripId, origin, destination, deptDate,
                deptTime, arrDate, arrTime, fare, capacity);
        String strng = formatter.toString();
        formatter.close();
        return strng;
    }

}
