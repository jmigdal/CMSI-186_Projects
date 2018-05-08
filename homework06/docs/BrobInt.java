/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByte, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   private String internalValue = "";        // internal String representation of this BrobInt
   private byte   sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string

  /**
  ab
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
      if ( value.charAt(0) == '-' ) {
         this.sign = -1;
         value = value.substring( 1, value.length() );
      } else if ( value.charAt(0) == '+' ) {
         this.sign = 1;
         value = value.substring( 1, value.length() );
      } else {
         this.sign = 1;
      }
      while ( value.charAt(0) == '0' && value.length() > 1 ) {
         value = value.substring( 1, value.length() );
      }
      this.internalValue = value;
      validateDigits();
      this.reversed = reverseString( this.internalValue );
      this.byteVersion = stringToBytes( this.internalValue );
      
      
      //System.out.println( this.reversed );
      //System.out.println( this.internalValue );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() {
      char c;
      for ( int i = 0; i < this.internalValue.length(); i++ ) {
         c = this.internalValue.charAt(i);
         //System.out.println( c );
         //System.out.println( Character.getNumericValue(c) );
         if ( !(Character.getNumericValue(c) >= 0 && Character.getNumericValue(c) <= 9) ) {
            System.out.println("Only include a negative or positive sign before the number and after only include digits 0-9");
            System.exit( 1 );
         }
      }
      return true;
      //throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using byte array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt addByte( BrobInt gint ) {
      byte carry = 0;
      String sign = "";
      String topNum = this.internalValue;
      String botNum = gint.toBaseString();
      BrobInt addBrob;
      
      
      if ( this.getSign() == gint.getSign() ) {
         if ( this.getSign() == -1 ) {
            sign = "-";
         } else {
            sign = "+";
         }
      } else {
         if ( this.getSign() == -1 ) {
            addBrob = new BrobInt( botNum ).subtractByte( new BrobInt( topNum ) );
         } else {
            addBrob = new BrobInt( topNum ).subtractByte( new BrobInt( botNum ) );
         }
         return addBrob;
      }
           
      if ( botNum.length() > topNum.length() ) {
         while ( botNum.length() != topNum.length() ) {
            topNum = "0" + topNum;
         }
      } else if ( botNum.length() < topNum.length() ) {
         while ( botNum.length() != topNum.length() ) {
            botNum = "0" + botNum;
         }
      }
      //System.out.println( topNum + " + " + botNum );
      byte[] topArr = stringToBytes( reverseString(topNum) );
      byte[] botArr = stringToBytes( reverseString(botNum) );
      //toArray( topArr );
      //toArray( botArr );
      byte[] addArr = new byte[ topNum.length() + 1 ];
      for ( int i = 0; i < topNum.length(); i++ ) {
         if ( topArr[i] + botArr[i] + carry >= 10 ) {
            addArr[i] = (byte)(topArr[i] + botArr[i] + carry - 10);
            carry = 1;            
         } else {
            addArr[i] = (byte)(topArr[i] + botArr[i] + carry);
            carry = 0;
         }
      }
      addArr[ topNum.length() ] = carry;
      String add = bytesToString( addArr );
      //System.out.println(sign + reverseString(add) + " rip");
      addBrob = new BrobInt( sign + reverseString(add) );
      return addBrob;
      //throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt addInt( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using bytes
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtractByte( BrobInt gint ) {
      byte carry = 0;
      boolean swap = false;
      String sign;
      String bigNum;
      String smlNum;
      BrobInt subBrob;
      if ( compareTo( gint ) == 0 && gint.getSign() == this.getSign() ) {
         subBrob = new BrobInt( "0" );
         return subBrob;
      } else if ( compareTo( gint ) > 0 ) {
         //System.out.println("itwerk" + compareTo( gint ));
         bigNum = this.internalValue;
         smlNum = gint.toBaseString();
      } else {
         bigNum = gint.toBaseString();
         smlNum = this.internalValue;
         swap = true;
      }
      
      if ( gint.getSign() != this.getSign() ) {
         if ( gint.getSign() == -1 ) {
            subBrob = new BrobInt( smlNum ).addByte( new BrobInt( bigNum ) );
         } else {
            subBrob = new BrobInt( "-" + smlNum ).addByte( new BrobInt( "-" + bigNum ) );
         }
         return subBrob;
      } else {
         if ( (this.getSign() == 1 && swap) || (this.getSign() == -1 && !swap) ) {
            sign = "-";
         } else {
            sign = "+";
         }
      }
      
      if ( bigNum.length() > smlNum.length() ) {
         while ( bigNum.length() != smlNum.length() ) {
            smlNum = "0" + smlNum;
         }
      } else if ( bigNum.length() < smlNum.length() ) {
         while ( bigNum.length() != smlNum.length() ) {
            bigNum = "0" + bigNum;
         }
      }      
      
      byte[] bigArr = stringToBytes( reverseString(bigNum) );
      byte[] smlArr = stringToBytes( reverseString(smlNum) );
      //toArray( bigArr );
      //toArray( smlArr );
      byte[] subArr = new byte[ bigNum.length() ];
      for ( int i = 0; i < bigNum.length(); i++ ) {
         if ( bigArr[i] - smlArr[i] - carry < 0 ) {
            subArr[i] = (byte)(10 + bigArr[i] - smlArr[i] - carry);
            carry = 1;
         } else {
            subArr[i] = (byte)(bigArr[i] - smlArr[i] - carry);
            carry = 0;
         }
      }
      String sub = bytesToString( subArr );
      //System.out.println(sign + reverseString(sub) + "a dip");
      subBrob = new BrobInt( sign + reverseString(sub) );
      return subBrob;
      //throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtractInt( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
      String sign;
      String bigNum;
      String smlNum;
      char end;
      BrobInt multBrob = new BrobInt("0");
      Halver hav = new Halver();
      
      if ( gint.getSign() == this.getSign() ) {
         sign = "+";
      } else {
         sign = "-";
      }
      
      if ( compareTo( gint ) > 0 ) {
         bigNum = this.internalValue;
         smlNum = gint.toBaseString();
      } else {
         bigNum = gint.toBaseString();
         smlNum = this.internalValue;
      }
      
      if ( bigNum.equals( "1" ) ) {
         multBrob = new BrobInt ( sign + smlNum );
         return multBrob;
      } else if ( smlNum.equals( "1" ) ) {
         multBrob = new BrobInt ( sign + bigNum );
         return multBrob;
      }            
      
      while ( !smlNum.equals("") ) {
         end = smlNum.charAt( smlNum.length() - 1 );
         if ( end == '1' || end == '3' || end == '5' || end == '7' || end == '9' ) {
            multBrob = multBrob.addByte( new BrobInt( bigNum ) );
         }
         smlNum = hav.halve( smlNum );
         bigNum = new BrobInt( bigNum ).addByte( new BrobInt( bigNum ) ).toBaseString();
      }
      multBrob = new BrobInt( sign + multBrob.toBaseString() );
      return multBrob;
      //throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
      String sign;
      String firstNum = this.internalValue;
      String seconNum = gint.toBaseString();
      BrobInt firstBrob = new BrobInt( firstNum );
      BrobInt seconBrob = new BrobInt( seconNum );
      BrobInt helperBrob = new BrobInt( "1" );
      int divnum = 0;
      int lengthdiff;
      BrobInt divBrob = new BrobInt ( "0" );
      
      if ( compareTo( gint ) < 0 ) {
         divBrob = new BrobInt( "0" );
         return divBrob;
      }
      
      if ( gint.getSign() == this.getSign() ) {
         sign = "+";
      } else {
         sign = "-";
      }
      while ( firstBrob.compareTo( seconBrob ) > 0 ) {
         lengthdiff = firstBrob.toBaseString().length() - seconBrob.toBaseString().length();
         helperBrob = new BrobInt( "1" );
         for ( int i = 0; i < lengthdiff-1; i++ ) {
            //helperBrob = helperBrob.multiply( new BrobInt ( "10" ) );
            //System.out.println(lengthdiff);
         }
         firstBrob = firstBrob.subtractByte( helperBrob.multiply( seconBrob ) );
         divBrob = divBrob.addByte( helperBrob );
      }
      //divBrob = divBrob.subtractByte( helperBrob );
      divBrob = new BrobInt( sign + divBrob.toBaseString() );
      return divBrob;
      //throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      BrobInt remBrob = this.divide( gint );
      remBrob = this.subtractByte( gint.multiply(remBrob) );
      return remBrob;
      //throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {
      if( internalValue.length() > gint.internalValue.length() ) {
         return 1;
      } else if( internalValue.length() < gint.internalValue.length() ) {
         return (-1);
      } else {
         for( int i = 0; i < internalValue.length(); i++ ) {
            Character a = new Character( internalValue.charAt(i) );
            Character b = new Character( gint.internalValue.charAt(i) );
            if( new Character(a).compareTo( new Character(b) ) > 0 ) {
               return 1;
            } else if( new Character(a).compareTo( new Character(b) ) < 0 ) {
               return (-1);
            }
         }
      }
      return 0;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toBaseString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( new Long( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toBaseString() {
      return internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      String result;
      if ( this.getSign() == -1 ) {
         result = "-" + this.internalValue;
      } else {
         result = "" + this.internalValue;      //could put '+' in the "" if wanted
      }
      return result;
   }
   
   
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( byte[] d ) {
      System.out.println( Arrays.toString( d ) );
   }
   
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to convert a string representation of this BrobInt to an array of bytes
   
   
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public byte[] stringToBytes( String s ) {
      byte[] bytes = new byte[ s.length() ];
      for ( int i = 0; i < s.length(); i++ ) {
         bytes[i] = (byte)(s.charAt(i) - 48);
      }
      //toArray( bytes );
      return bytes;
   }   

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to convert a byte array representation of this BrobInt to a string
   
   
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String bytesToString( byte[] b ) {
      String result = "";
      for ( int i = 0; i < b.length; i++ ) {
         result = result + Byte.toString(b[i]);
      }
      return result;
   }   
      
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the String passed as argument
   *  @param  s         String to reverse its value
   *  @return String that is the reverse of the String passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String reverseString( String s ) {
      String result = new StringBuilder( s ).reverse().toString();
      return result;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to the sign of this BrobInt
   *  @return byte  which is the byte representation of the sign of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public byte getSign() {
      return this.sign;
   }
   
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

      System.exit( 0 );
   }
}
