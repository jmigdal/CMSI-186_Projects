/**
 *  File name     :  CountTheDays.java
 *  Purpose       :  Calls CalendarStuff.daysBetween() to find the number of days between 2 dates.
 *  Author        :  James Migdal (prototype)
 *  Date          :  2018-01-25 (prototype)
 *  Description   :  See purpose.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-01-25  James Migdal  Initial writing and release
 */
public class CountTheDays {
    public static void main (String[] args) {
      long[] dates = new long[6];                                                     //new array to hold dates as longs
      if ( args.length != 6 ) {
         System.out.println("Sorry, you must give 2 dates, Ex. 1 2 2018 2 2 1998");   //Catch if user doesn't input enough dates
         System.exit(-1);
      }
      for ( int i = 0; i < 6; i++ ) {
         dates[i] = Long.parseLong(args[i]);
      }
      System.out.println(CalendarStuff.daysBetween(dates[0], dates[1], dates[2], dates[3], dates[4], dates[5]));
    }
}
