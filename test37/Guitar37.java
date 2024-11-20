/** Guitar37 class
* @author Jessica Li
* @version 1.0 (05/29/2023)
*/
public class Guitar37 implements Guitar {
    //create an array of 37 GuitarString objects.
    private GuitarString[] strings = new GuitarString[37];
    /**create a String constant called KEYBOARD to represents 
      *all the positions of the black and white keys on piano.
    */
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/'";  // keyboard layout
    
    /**Guitar37() constructor initalizes Guitar37 objects by 
      *caculating the frequency to the i-th character of the 
      *corresponds string.
    */
    public Guitar37() {
    //iterate the whole strings array that has 37 strings
        for (int i = 0; i < strings.length; i++) {
        /**define a varaible called frequency and saved the string's
          *frequency into it. We calculates the frequency of each string
          *based on its index i, and use the i-th character of the string 
          *corresponds to a frequency formula.
          */
            double frequency = 440 * Math.pow(2, (i - 24) / 12.0);
         //we store the frequency of the corresponds string into the strings array.
            strings[i] = new GuitarString(frequency);
        }
    }
    /**playNote(int pitch) will find the string based on its give index 
      *and vibrate it.
      */
    public void playNote(int pitch) {
    //convert a pitch value to an index of the string
        int idx = pitch + 24;
        //if the position of the give string is not out of bound
        if (idx >= 0 && idx < strings.length) {
        //we find the string based on the given index to pluck it and make it vibrate.
            strings[idx].pluck();
        }
    }
    
    /**hasString method lets a client verify that a particular character has a 
      *corresponding string for this guitar      
      *@return return true if the given character matched up with 
      *a character in the KEYBBOARD string   
      *@return false if it's not one of the letters in the KEYBBOARD string. 
      */
    public boolean hasString(char string) {
      char[] charArray = KEYBOARD.toCharArray();
      for (int i = 0; i < charArray.length; i++) {
        if (charArray[i] == string) {
            return true;
        }
      }
    return false;
    }
    
    /**pluck() method makes a precondition that if the key is legal for 
      *this guitar. It will throw an IllegalArgumentException if the given
      *character's index is less than 0 or out of the length of KEYBOARD string.
      */
    public void pluck(char string) {
      //the index of this give character will be its location on the KEYBOARD map.
      int idx = KEYBOARD.indexOf(string);
      if (idx < 0 || idx >= KEYBOARD.length()) {
        throw new IllegalArgumentException();
       }
      //vibrate the giving string if it's idex is not out of bound.
      strings[idx].pluck();
    }

    /**sample() method returns the sum of the current samples.
      *@return return the sum of all 37 samples.
      */
    
    public double sample() {
        double sum = 0.0;
        //uses a loop to find the sum of all 37 samples.
        for (GuitarString str : strings) {
            sum += str.sample();
        }
        return sum;
    }
    
    //tic() method is to btain the current sound sample.

    public void tic() {
        //iterate all the strings in the stirng method.
        for (GuitarString str : strings) {
            str.tic();//use a recursion to get its current sound sample.
        }
    }
   
   /**time() method is to determine the current time
     *@return -1 if the time method is not implemented.
     */
    public int time() {
        return -1;  // not implemented
    }
}
