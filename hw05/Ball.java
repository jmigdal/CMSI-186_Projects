public class Ball {
   
   private double x;
   private double y;
   private double velx;
   private double vely;
   private double[] position = new double[2];
   
   public Ball( double x, double y, double velx, double vely) {
      this.x = x;
      this.y = y;
      this.velx = velx;
      this.vely = vely;
   }
   
   public void move() {
      this.x += this.velx;
      this.y += this.vely;
   }
   
   public void friction( double timeSlice ) {
      this.velx = this.velx - ((this.velx * 0.01) * timeSlice);
      this.vely = this.vely - ((this.vely * 0.01) * timeSlice);
   }
   
   public double[] getPosition() {
      this.position[0] = this.x;
      this.position[1] = this.y;
      return this.position;
   }
   
   public double getSpeed() {
      return Math.sqrt( this.velx*this.velx + this.vely*this.vely );
   }
   
   public String toString() {
      String s;
      if ( this.getSpeed() >= .0835 ) {
         s = String.format("position <%.1f,%.1f>\t velocity <%.4f,%.4f>", this.x, this.y, this.velx, this.vely);
      } else {
         s = String.format("position <%.1f,%.1f>\t at rest", this.x, this.y);
      }
      return s;
   }
   
   public static void main(String[]args) {
      System.out.println("sup");
   }
}