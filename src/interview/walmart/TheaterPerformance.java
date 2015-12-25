package interview.walmart;

import interview.walmart.theater.AbstractTheaterLevel;
import interview.walmart.theater.Theater;

import java.util.Map;

import org.apache.mina.util.ExpirationListener;
import org.apache.mina.util.ExpiringMap;

public class TheaterPerformance implements TicketService {
   private static final int SEAT_HOLD_TIME_TO_LIVE_IN_SECS = 900;
   private static final int SEAT_HOLD_EXPIRATION_INTERVAL_SECS = 15;

   //performance name
   //performance date and time
   //performance artist
   private Map<Integer,TheaterLevelPerformance> performanceSeating;
   private ExpiringMap<Integer,SeatHold> seatHoldExpirer;
   private int largestLevelIndex = -1;

   public TheaterPerformance(final Theater theater) {
      seatHoldExpirer =
         new ExpiringMap<Integer,SeatHold>(
               SEAT_HOLD_TIME_TO_LIVE_IN_SECS,
               SEAT_HOLD_EXPIRATION_INTERVAL_SECS);

      seatHoldExpirer.addExpirationListener(new ExpirationListener<SeatHold>() {
         @Override
         public void expired(SeatHold seatHold) {
            seatHold.returnSeatsToAvailable(performanceSeating.values());
         }
      });

      for (AbstractTheaterLevel level : theater.getLevels()) {
         performanceSeating.put(level.getLevelId(),
                                new TheaterLevelPerformance(level));
         largestLevelIndex =
            Math.max(largestLevelIndex, level.getLevelId().intValue());
      }
   }

   @Override
   public int numSeatsAvailable() {
      int availableSeats = 0;

      for (TheaterLevelPerformance level : performanceSeating.values()) {
         availableSeats += level.numSeatsAvailable();
      }
      return availableSeats;
   }

   private boolean validVenueLevel(final Integer venueLevel) {
      int venueLevelInt = venueLevel.intValue();

      return (venueLevelInt > 0) && (venueLevelInt <= largestLevelIndex);
   }

   @Override
   public int numSeatsAvailable(final Integer venueLevel) {
      return validVenueLevel(venueLevel)
                ? performanceSeating.get(venueLevel).numSeatsAvailable()
                : 0;
   }

   @Override
   public SeatHold findAndHoldSeats(final int numSeats,
                                    final String customerEmail) {
      SeatHold seatHold = new SeatHold(performanceSeating.values());
      int seatsHeld = 0;

      for (TheaterLevelPerformance level : performanceSeating.values()) {
         seatsHeld +=
            seatHold.findSeatsToHold(level, seatsHeld, numSeats);

         if (seatsHeld == numSeats) {
            break;
         }
      }
      //if seatsHeld < numSeats ???
      mockSendMail(customerEmail, "Your seats are temporarily reserved");
      return seatHold;
   }

   @Override
   public SeatHold findAndHoldSeats(final int numSeats,
                                    final Integer minLevel,
                                    final Integer maxLevel,
                                    final String customerEmail) {
      SeatHold seatHold = new SeatHold(performanceSeating.values());
      int seatsHeld = 0;
      int maxLevelInt = maxLevel.intValue();

      for (int i = minLevel.intValue(); i < maxLevelInt; i++) {
         seatsHeld +=
            seatHold.findSeatsToHold(performanceSeating.get(Integer.valueOf(i)),
                                     seatsHeld, numSeats);

         if (seatsHeld == numSeats) {
            break;
         }
      }
      //if seatsHeld < numSeats ???
      mockSendMail(customerEmail, seatHold.generateHoldEmail());
      return seatHold;
   }

   @Override
   public String reserveSeats(final int seatHoldId,
                              final String customerEmail) {
      SeatHold seatHold = seatHoldExpirer.get(Integer.valueOf(seatHoldId));

      seatHoldExpirer.remove(seatHold);
      seatHold.convertHeldSeatsToReserveSeats(performanceSeating.values());
      return mockSendMail(customerEmail, "Your seats are reserved");
   }

   //~----------------------------------------------------------------
   //~----------------------------------------------------------------
   //~----------------------------------------------------------------
   private static String mockSendMail(final String customerEmail,
                                      final String message) {
      System.out.println(
         new StringBuilder("To: ").append(customerEmail)
                   .append(" Message: ").append(message)
                   .toString());
      return "";
   }
}
