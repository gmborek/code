package interview.walmart;

import interview.walmart.theater.Balcony1Level;
import interview.walmart.theater.Balcony2Level;
import interview.walmart.theater.MainLevel;
import interview.walmart.theater.OrchestraLevel;
import interview.walmart.theater.Theater;

public class TicketServiceRunner {
   public void run(final TheaterPerformance theaterPerformance) {
      
   }

   public static void main(String[] args) {
      TicketServiceRunner ticketServiceRunner = new TicketServiceRunner();

      ticketServiceRunner.run(
         new TheaterPerformance(
            //name
            //date and time
            //artist
            new Theater(new OrchestraLevel(), new MainLevel(),
                        new Balcony1Level(), new Balcony2Level())));
   }
}
