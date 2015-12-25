package interview.walmart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SeatHold {
   private static int lastSeatId = 0;

   private int seatHoldId;
   private Map<Integer,List<Seat>> levelSeats;

   public SeatHold(final Collection<TheaterLevelPerformance> performanceSeating) {
      this.seatHoldId = lastSeatId++;

      for (TheaterLevelPerformance levelPerformance : performanceSeating) {
         levelSeats.put(levelPerformance.getLevelId(),
                        new ArrayList<Seat>());
      }
   }

   public int getSeatHoldId() {
      return seatHoldId;
   }

   public int findSeatsToHold(final TheaterLevelPerformance levelPerformance,
                              final int seatsHeld, final int numSeats) {
      return levelPerformance.findSeatsToHold(
                levelSeats.get(levelPerformance.getLevelId()),
                seatsHeld, numSeats);
   }

   public void returnSeatsToAvailable(
         final Collection<TheaterLevelPerformance> performanceSeating) {
      for (TheaterLevelPerformance levelPerformance : performanceSeating) {
         levelPerformance.convertHeldSeatsToAvailableSeats(
            levelSeats.get(levelPerformance.getLevelId()));
      }
   }

   public void convertHeldSeatsToReserveSeats(
         final Collection<TheaterLevelPerformance> performanceSeating) {
      for (TheaterLevelPerformance levelPerformance : performanceSeating) {
         levelPerformance.convertHeldSeatsToReserveSeats(
            levelSeats.get(levelPerformance.getLevelId()));
      }
   }

   public String generateHoldEmail() {
      StringBuilder sb =
         new StringBuilder("Your seats are temporarily reserved");

      for (List<Seat> eachLevelSeats : levelSeats.values()) {
         for (Seat seat : eachLevelSeats) {
            sb.append(" ").append(seat.asString());
         }
      }
      return sb.toString();
   }
}
