My approach to the ticket service homework solution involved the following ideas:

- The theater described in the homework instructions only describes the resouce
 used during an iteration.  An object implementing the TicketService interface
 would represent a performance, or an instance of the theater in time.  The
 theater performance contains a datastructure for the theater level performance
 objects.
 
 - Instantiated theater performance levels are the objects that contain the Seats.
 The Seats are in two lists for available seats and reserved seats.  Seats move
 only between these two lists or on a SeatHolder and are not allocated after the
 performance level instantiation.
 
 - A TheaterPerformance class is the class implementing the TicketService
 interface.  It contains a datastructure for the theater performance levels and
 a data structure for the SeatHolder objects, described below.  If the SeatHolder
 objects are pooled, no further memory needs to be allocated.
 
 - Given that the SeatHolders expire after a given period of time, I chose to
 use the Apache Mina ExpiringMap class:
 
 https://mina.apache.org/mina-project/apidocs/org/apache/mina/util/ExpiringMap.html
 
 This solution performs all of the timing and expiration behavior I needed
 without me having to implements Threads myself.
 
 The conditions for the homework are vague and my development environment only
 uses Java 1.7, so I had to modify the implementation of the TicketService
 interface slightly.
 