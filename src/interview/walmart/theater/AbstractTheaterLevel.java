package interview.walmart.theater;

import java.util.Comparator;

public abstract class AbstractTheaterLevel {
   public static final Comparator<AbstractTheaterLevel> LEVEL_COMPARATOR =
      new Comparator<AbstractTheaterLevel>() {
         public int compare(AbstractTheaterLevel level1,
                            AbstractTheaterLevel level2) {
            return level1.getLevelId().compareTo(level2.getLevelId());
         }
   };

   private Integer levelId;
   private String name;
   private float seatPrice;
   private int rows;
   private int seatsInRow;

   public AbstractTheaterLevel(final Integer levelId,
                               final String name,
                               final float seatPrice,
                               final int rows,
                               final int seatsInRow) {
      this.levelId = levelId;
      this.name = name;
      this.seatPrice = seatPrice;
      this.rows = rows;
      this.seatsInRow = seatsInRow;
   }

   public Integer getLevelId() {
      return levelId;
   }
   public String getName() {
      return name;
   }
   public float getSeatPrice() {
      return seatPrice;
   }
   public int getRows() {
      return rows;
   }
   public int getSeatsInRow() {
      return seatsInRow;
   }
}
