# Assignment 4
# Auther: Dave
# 02 . 11 . 22

## Displaying Bus trips
- startingLocation, destination, dateOfDeparture, timeOfDeparture, dateOfArrival, timeOfArrival, fare
-- from available vendors : 'GoBus', 'BusEireann', City-link'

## Placing Orders
- noOfPassengers, startingLocation, destination, dateOfDeparture, timeofDeparture, dateOfArrival, timeOfArrival, fare



## Classes all vendors should have
Interfaces for vendors to implement
- getAllAvailableTrips() // should print all availble trips from vendors
- bookingTrips(obj) // where obj is the booking details,


Each trip should have a available seats variable that will be decremented by noOfPassengers

We could make a table print out of the available trips

Company          Trip ID     Origin      Destination     Departure Date      Departure Time      Arrival Date        Arrival Time        Fare    Free Seats
Bus Eireann         0       Galway      Limerick            02/11/22            09:00               02/11/22            11:00              7           60       
