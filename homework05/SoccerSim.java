public class SoccerSim {
   
   private static final double DEFAULT_TIMESLICE_SECONDS = 1.0;
   private final double MAXIMUM_X_VALUE = 1000.0;
   private final double MINIMUM_X_VALUE = -1000.0;
   private final double MAXIMUM_Y_VALUE = 1000.0;
   private final double MINIMUM_Y_VALUE = -1000.0;
   
   private static double timeslice;
   private static int numOfBalls;
   
   private static Ball[] bs = null;
   
   public SoccerSim() {
      super();
   }
   
   public static void handleInitialArguments( String args[] ) {
      if( 8 > args.length ) {
         System.out.println( "You must enter at least 8 arguments. Please try again." );
         System.exit( 1 );
      }
      if( 2 <= args.length % 4 ) {
         System.out.println( "You must enter an x coordinate, y coordinate, x velocity and y velocity for every ball. Please try again.");
         System.exit( 1 );
      }
      
      if( 1 == args.length % 4 ) {
         timeslice = Double.parseDouble(args[ args.length - 1 ]);
      } else {
         timeslice = DEFAULT_TIMESLICE_SECONDS;
      }
      numOfBalls = (int)(args.length/4);
      bs = new Ball[ numOfBalls ];
      
      for ( int i = 0; i < numOfBalls; i++ ) {
         bs[i] = new Ball( Integer.parseInt(args[4*i]), Integer.parseInt(args[4*i + 1]), Integer.parseInt(args[4*i + 2]), Integer.parseInt(args[4*i + 3]) );
      }
   }
   
   public static boolean checkCollision() {
      double[] firstPos;
      double[] secondPos;
      double distance;
      for ( int i = 0; i < numOfBalls; i++ ) {
         for ( int j = 1; j < numOfBalls; j++ ) {
            firstPos = bs[i].getPosition();
            secondPos = bs[j].getPosition();
            distance = Math.sqrt( Math.pow( firstPos[0] - secondPos[0], 2 ) + Math.pow( firstPos[1] - secondPos[1], 2 ) );
            if ( j <= i ) {
               break;
            } else if ( distance <= 8.9 ) {
               System.out.println("\nCONTACT ball " + i + " & " + j + ";");
               return true;
            }
         }
      }
      return false;
   }
   
   public static boolean checkAllStopped() {
      for ( int i = 0; i < numOfBalls; i++ ) {
         if ( .0835 < bs[i].getSpeed() ) {
            return false;
         }
      }
      System.out.println("\nNO COLLISION IS POSSIBLE");
      return true;
   }
   
   
   public static void main (String[]args) {
      
      handleInitialArguments( args );
      
      Timer timer = new Timer( timeslice );
      
      System.out.println("INITIAL REPORT at " + timer.toString());
      for ( int i = 0; i < numOfBalls; i++ ) {
         System.out.println( i + ":\t" + bs[i].toString() );
      }
      
      while ( true ) {
         timer.tick();
         
         System.out.println("\nPROGRESS REPORT at " + timer.toString());
         
         for ( int i = 0; i < numOfBalls; i++ ) {
            bs[i].move();
            bs[i].friction( timeslice );
            System.out.println( i + ":\t" + bs[i].toString() );
         }
         
         if ( checkAllStopped() ) {
            break;
         }
         if ( checkCollision() ) {
            break;
         }
      }
   }
}
