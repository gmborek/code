package interview.walmart;

public class Seat {
   private String levelName;
   private int levelRow;
   private int seatInRow;
   private float price;

   public Seat(final String levelName, final int levelRow,
               final int seatInRow, final float price) {
      this.levelName = levelName;
      this.levelRow = levelRow;
      this.seatInRow = seatInRow;
      this.price = price;
   }

   public String getLevelName() {
      return levelName;
   }
   public int getLevelRow() {
      return levelRow;
   }
   public int getSeatInRow() {
      return seatInRow;
   }
   public float getPrice() {
      return price;
   }

   public String asString() {
      return new StringBuilder("Level: ").append(levelName)
                       .append(" Row: ").append(levelRow)
                       .append(" Seat: ").append(seatInRow)
                       .toString();
   }
}
