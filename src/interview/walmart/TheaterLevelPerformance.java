package interview.walmart;

import interview.walmart.theater.AbstractTheaterLevel;

import java.util.ArrayList;
import java.util.List;

public class TheaterLevelPerformance {
   private Integer levelId;
   private List<Seat> availableSeats;
   private List<Seat> reservedSeats;

   public TheaterLevelPerformance(final AbstractTheaterLevel level) {
      levelId = level.getLevelId();
      reservedSeats = new ArrayList<Seat>();
      int levelRows = level.getRows();
      int levelSeatsInRow = level.getSeatsInRow();
      String levelName = level.getName();
      float levelSeatPrice = level.getSeatPrice();

      for (int i = 0; i < levelRows; i++) {
         for (int j = 0; j < levelSeatsInRow; j++) {
            availableSeats.add(new Seat(levelName, i, j, levelSeatPrice));
         }
      }
   }

   public Integer getLevelId() {
      return levelId;
   }

   public int numSeatsAvailable() {
      return availableSeats.size();
   }

   public int findSeatsToHold(final List<Seat> levelSeats,
                              final int seatsHeld, final int numSeats) {
      while (((seatsHeld + levelSeats.size()) < numSeats) &&
             !availableSeats.isEmpty()) {
         levelSeats.add(availableSeats.remove(0));
      }
      return levelSeats.size();
   }

   public void convertHeldSeatsToAvailableSeats(final List<Seat> heldSeats) {
      availableSeats.addAll(heldSeats);
   }

   public void convertHeldSeatsToReserveSeats(final List<Seat> heldSeats) {
      reservedSeats.addAll(heldSeats);
   }
}
