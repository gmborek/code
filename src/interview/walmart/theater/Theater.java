package interview.walmart.theater;

import java.util.Set;
import java.util.TreeSet;

public class Theater {
   private Set<AbstractTheaterLevel> levels;

   public Theater(final AbstractTheaterLevel... levels) {
      this.levels =
         new TreeSet<AbstractTheaterLevel>(AbstractTheaterLevel.LEVEL_COMPARATOR);

      for (int i = 0; i < levels.length; i++) {
         this.levels.add(levels[i]);
      }
   }

   public Set<AbstractTheaterLevel> getLevels() {
      return levels;
   }
}
