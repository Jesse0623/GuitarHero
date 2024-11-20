/** GuitarString class
* @author Jessica Li
* @version 1.0 (05/29/2023)
*/

import java.util.*;

public class GuitarString {
   /**implements the ring buffer as a queue using the Queue<E> 
     *interface and the LinkedList<E> implementation. 
     */
   private Queue<Double> q = new LinkedList<Double>();
   /**creates a random generator to generate N random values to 
     *replace N elements in ring buffer.
     */
   private Random r = new Random();
   //creates the desired capacity of the ring buffer.
   private int capacityN;
   //creates the sampling rate.
   public static final double ENERGY_DECAY_FACTOR = 0.996;
   
   /**GuitarString(double frequency) method will throw an 
     *IllegalArgumentException if the capacity of the ring 
     *buffer (StdAudio.SAMPLE_RATE / frequency) is less 
     * than 2 or frequency is less and equal to 0.   
   */
   public GuitarString(double frequency) {
      capacityN = (int) Math.round(StdAudio.SAMPLE_RATE / frequency);
      if (frequency <= 0 || capacityN < 2) {
         throw new IllegalArgumentException();
      }
    }
   
   /**GuitarString(double[] init) adds the value in the init 
     *array into the ring buffer (initzlizes the content in 
     *the ring buffer) and throw an exception if the array
     *has less than 2 elements.
     */
   public GuitarString(double[] init) {
      if (init.length < 2) {
         throw new IllegalArgumentException();
      }
      for (double contents : init) {
         q.add(contents);
      }
   }
   
   /**pluck() method use the random generator r to generate 
     *values between -0.5 and +0.5 and use them to replace 
     *the elements in the ring buffer by adding them into it.
     */
   public void pluck() {
      for (int i = 0; i < capacityN; i++) {
         double randVal = r.nextDouble() - 0.5;
         q.add(randVal);
      }   
   }
   
   /**tic()method will calculate the average of the first two samples
     *in the ring buffer (remove the first one and get access of
     *the second one without removing it), and multiply the result by
     *ENERGY_DECAY_FACTOR. Finally, it will add this final sample at
     *the end of the ring buffer Queue.
   
   */
   public void tic() {
   //q.remove() remove the first sample in the ring buffer.
   //q.peek() get the value of second sample without removing it.
      double sample = (q.remove() + q.peek()) * 0.5 * ENERGY_DECAY_FACTOR;
      q.add(sample);
   }
   
   /**sample() method will return the current sample 
     *@return return the value at the front of the ring buffer.
     */
   public double sample() {
      return q.peek();
   }
}
