/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  James Migdal
 *  Date          :  2018-01-25
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 */

 
public class CalendarStuff {

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = FEBRUARY  + 1;
   private static final int APRIL      = MARCH     + 1;
   private static final int MAY        = APRIL     + 1;
   private static final int JUNE       = MAY       + 1;
   private static final int JULY       = JUNE      + 1;
   private static final int AUGUST     = JULY      + 1;
   private static final int SEPTEMBER  = AUGUST    + 1;
   private static final int OCTOBER    = SEPTEMBER + 1;
   private static final int NOVEMBER   = OCTOBER   + 1;
   private static final int DECEMBER   = NOVEMBER  + 1;
  
  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor called..." );  // replace this with the actual code
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
	   if ( year % 4 == 0 ) {
         if ( year % 100 == 0 ) {
            if ( year % 400 == 0 ) {
               return true;
            } else {
               return false;
            }
         } else {
            return true;
         }
      }
      return false;
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
      long leap = 0;
      if ( isLeapYear(year) ) {
         leap = 1;                                             //1 if it was  leap year, 0 otherwise
      }
      switch ( Math.toIntExact(month)-1 ) {
         case JANUARY  : return 31;
         case FEBRUARY : return 28 + leap;
         case MARCH    : return 31;
         case APRIL    : return 30;
         case MAY      : return 31;
         case JUNE     : return 30;
         case JULY     : return 31;
         case AUGUST   : return 31;
         case SEPTEMBER: return 30;
         case OCTOBER  : return 31;
         case NOVEMBER : return 30;
         case DECEMBER : return 31;
         default: throw new IllegalArgumentException( "Illegal month value given to 'daysInMonth()'." );
      }
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if ( month1 == month2 && year1 == year2 && day1 == day2 ) {
         return true;
      } else {
         return false;
      }
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if ( year1 == year2 ) {
         if ( month1 == month2 ) {
            if ( day1 == day2 ) {
               return 0;
            } else if ( day1 > day2 ) {
               return 1;
            } else {               
               return -1;
            }
         } else if ( month1 > month2 ) {
            return 1;
         } else {
            return -1;
         }
      } else if ( year1 > year2 ) {
         return 1;
      } else {
         return -1;
      }
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
      if ( year >= 1000 && year <= 9999 ) {
         if ( month >= 1 && month <= 12 ) {
            if  ( day >= 1 && day <= daysInMonth(month, year) ) {
               return true;
            }
         }
      }
      return false;
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( long month ) {
      switch(Math.toIntExact(month - 1)) {
         case JANUARY  : return "January";
         case FEBRUARY : return "February";
         case MARCH    : return "March";
         case APRIL    : return "April";
         case MAY      : return "May";
         case JUNE     : return "June";
         case JULY     : return "July";
         case AUGUST   : return "August";
         case SEPTEMBER: return "September";
         case OCTOBER  : return "October";
         case NOVEMBER : return "November";
         case DECEMBER : return "December";
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      long dayCount = 0;
      long dateCompare = compareDate(month1, day1, year1, month2, day2, year2);
      if ( dateCompare == 0 )  {
         return 0;
      } else if ( dateCompare == -1 ) {
         while ( day1 < day2 || month1 < month2 || year1 < year2 ) {
            dayCount++;
            day1++;
            if ( day1 > daysInMonth(month1, year1) ) {
               day1 = 1;
               month1++;
            }
            if ( month1 > 12 ) {
               month1 = 1;
               year1++;
            }
         }
      } else if ( dateCompare == 1 ) {
         while ( day2 < day1 || month2 < month1 || year2 < year1 ) {
            dayCount++;
            day2++;
            if ( day2 > daysInMonth(month2, year2) ) {
               day2 = 1;
               month2++;
            }
            if ( month2 > 12 ) {
               month2 = 1;
               year2++;
            }
         }
      }
      return dayCount;
   }

}