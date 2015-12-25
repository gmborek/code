package interview.walmart;

public interface TicketService {
   public int numSeatsAvailable();
   public int numSeatsAvailable(Integer venueLevel);

   public SeatHold findAndHoldSeats(int numSeats,
                                    String customerEmail);
   public SeatHold findAndHoldSeats(int numSeats,
                                    Integer minLevel,
                                    Integer maxLevel,
                                    String customerEmail);

   public String reserveSeats(int seatHoldId, String customerEmail);
}
