public class Timer {
   
   private static double elapstime = 0.0;
   private static double ticklength;
   
   public Timer( double ticklength ) {
      this.ticklength = ticklength;
   }
   
   public double tick() {
      this.elapstime += this.ticklength;
      return this.elapstime;
   }
   
   public String toString () {
      int hour = (int)(this.elapstime / 3600);
      int minute = (int)(this.elapstime / 60) - hour * 60;
      double second = this.elapstime % 60;
      String s = hour + ":" + minute + ":" + second;
      return s;
   }
   
   public static void main (String[]args) {
      
   }
}