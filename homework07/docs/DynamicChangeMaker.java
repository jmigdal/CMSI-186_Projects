/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  To make change give denominations of coins an a target
 * @author    :  James Migdal
 * Date       :  2018-05-01
 * Description:  @see <a href='https://bjohnson.lmu.build/cmsi186web/homework07.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2018-05-01  James Migdal  Initial writing and begin coding
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DynamicChangeMaker {
   
   static int[] denomsIn;
   static int targetIn;
   
   public static void main( String[]args ) {
      validateArgs( args );
      //System.out.println( denoms[1] );
      //System.out.println( target );
      
      Tuple solution = makeChangeWithDynamicProgramming( denomsIn, targetIn );
      if( solution.isImpossible() ) {
         System.out.println( "IMPOSSIBLE" );
      } else {
         System.out.println( targetIn + " can be made with " + solution.length() + " coins as follows:" );
         for( int i = 0; i < denomsIn.length; i++ ) {
            System.out.println( solution.getElement(i) + " x " + denomsIn[i] + " coin(s)" );
         }
      }
   }
   
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that the initial arguements are valid
   *  @param  args        initial arguements
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */   
   public static void validateArgs( String[] args ) {
      if( args.length != 2 ) {
         System.out.println("BAD DATA");
         System.exit( 1 );
      }
      
      String[] denomStr = args[0].split(",");
      denomsIn = new int[ denomStr.length ];
      targetIn = Integer.parseInt(args[1]);
      for( int i = 0; i < denomStr.length; i++ ) {
         denomsIn[i] = Integer.parseInt( denomStr[i] );
      }
      
      boolean duplicates = false;
      boolean negative = false;
      if( targetIn <= 0 ) {
         System.out.println("BAD DATA");
         System.exit( 1 );
      }
      for( int i = 0; i < denomsIn.length; i++ ) {
         for( int j = 0; j < denomsIn.length; j++ ) {
         if( (denomsIn[i] == denomsIn[j]) && (i != j) ) {
               duplicates = true;
            }
         }
         if( denomsIn[i] <= 0 ) {
            negative = true;
         }
      }
      
      if( duplicates || negative ) {
         System.out.println("BAD DATA");
         System.exit( 1 );
      }
   }      

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to make change using the fewest coins
   *  @param  denoms         the denominations of coins
   *  @param  target         the target value to reach
   *  @return Tuple representing the best way to make change
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */   
   public static Tuple makeChangeWithDynamicProgramming( int[] denoms, int target ) {
      int rowCount = denoms.length;
      int columnCount = target+1;
      Tuple onesTupe = new Tuple(rowCount);
      Tuple[] currentRow = new Tuple[columnCount];
      Tuple[] previousRow = new Tuple[columnCount];
      
      for( int i = 0; i < rowCount; i++ ) {
         for( int j = 0; j < columnCount; j++ ) {
            if( j == 0 ) {
               currentRow[j] = new Tuple(rowCount);
            } else {
               
               if( denoms[i] > j ) {                  
                  currentRow[j] = new Tuple( new int[0] );

                  if( i != 0 ) {
                     if( !(previousRow[j].isImpossible()) ) {
                        currentRow[j] = new Tuple(rowCount).add( previousRow[j] );
                     }
                  }
               } else {
                  onesTupe = new Tuple(rowCount);
                  onesTupe.setElement(i, 1);

                  if( (j - denoms[i]) >= 0 ) {
                     if( currentRow[j-denoms[i]].isImpossible() ) {
                        currentRow[j] = new Tuple( new int[0] );
                     } else {
                        currentRow[j] = new Tuple(rowCount).add( currentRow[j - denoms[i]] ).add(onesTupe);
                     }
                  }

                  if( i != 0 ) {
                     if( !(previousRow[j].isImpossible()) && previousRow[j].total() < currentRow[j].total() ) {
                        currentRow[j] = new Tuple(rowCount).add( previousRow[j] );
                     }
                  }
               }
            }
         }
         System.arraycopy( currentRow, 0, previousRow, 0, currentRow.length );
         currentRow = new Tuple[columnCount];
      }
      return previousRow[target];
   }
}
