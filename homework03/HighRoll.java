/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  A game using the Die and DiceSet classes
 *  @author       :  James Migdal
 *  Date          :  2018-02-20
 *  Description   :  A game where the user decides the amount of dice he/she would like to roll and how
 *                   many side per die. They can then keep rolling to get the maximum possible score.
 *
 *  Notes         :  The user must choose at least one die and the dice must be at least 4 sided.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-20  James Migdal  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

 public class HighRoll {
    
   private static int dienum;
   private static int sidenum;
   private static int hiscore;
   
   public static void main( String args[] ) {
      BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
      hiscore = 0;
      
      //
      System.out.println("Enter the number of dice you would like to use.");
      System.out.print( ">>" );
      String inputLine = null;
      try {
         inputLine = input.readLine();
         if( 0 == inputLine.length() ) {
            System.out.println("enter some text!:");
         }
         //System.out.println( "   You typed: " + inputLine );
      }
      catch( IOException ioe ) {
         System.out.println( "Caught IOException" );
      }
      dienum = Integer.parseInt(inputLine);
      
      System.out.println("Enter the number of sides per dice");
      System.out.print( ">>" );
      inputLine = null;
      try {
         inputLine = input.readLine();
         if( 0 == inputLine.length() ) {
            System.out.println("enter some text!:");
         }
         //System.out.println( "   You typed: " + inputLine );
      }
      catch( IOException ioe ) {
         System.out.println( "Caught IOException" );
      }
      sidenum = Integer.parseInt(inputLine);      
      DiceSet ds = new DiceSet(dienum, sidenum);  

      while( true ) {
         System.out.println("-=Enter one of the following numbers to do its action=-");
         System.out.println("1: Roll all the dice");
         System.out.println("2: Roll a single die");
         System.out.println("3: Calculate the score for this set");
         System.out.println("4: Save this score as high score");
         System.out.println("5: Display the high score");
         System.out.println("OR enter 'q' to quit");         
         System.out.print( ">>" );
         inputLine = null;
         try {
            inputLine = input.readLine();
            if( 0 == inputLine.length() ) {
               System.out.println("enter some text!:");
            }
            //System.out.println( "   You typed: " + inputLine );
            if( 'q' == inputLine.charAt(0) ) {
               break;
            }
         }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
         
         switch (inputLine) {
            case "1": ds.roll();
                      break;
            case "2": System.out.println("Which die would you like to roll?");
                      System.out.print( ">>" );
                      inputLine = null;
                      try {
                         inputLine = input.readLine();
                         if( 0 == inputLine.length() ) {
                            System.out.println("enter some text!:");
                         }
                         //System.out.println( "   You typed: " + inputLine );
                      }
                      catch( IOException ioe ) {
                         System.out.println( "Caught IOException" );
                      }
                      break;
            case "3": System.out.println(ds.sum());
                      break;
            case "4": hiscore = ds.sum();
                      break;
            case "5": System.out.print(hiscore);
                      break;
            default : System.out.println("Please choose a valid option");
                      break;
         }
         System.out.println("\n");
      }
   }
 }  