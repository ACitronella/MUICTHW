//Name: Phuriwat Angkoondittaphong
//ID: 6388003
//Section: 1
import java.util.ArrayList;

import org.graalvm.compiler.hotspot.nodes.FastAcquireBiasedLockNode;


public class Kalculator extends ArrayList<Double>{

    //******INSERT YOUR CODE HERE***********

    //Class attributes go here
    //**************************************

    /**
     * Constructor is the fist method to be call at instantiation of a Kalculator object.
     * If you need to initialize your object, do so here. 
     */
    Kalculator()
    {
        //******INSERT YOUR CODE HERE***********
        // super();
        //**************************************
    }
    
    /**
     * Add number to the list of numbers. 
     * @param number
     */
    public void addNumber(double number)
    {	//******INSERT YOUR CODE HERE***********
        Double value = Double.valueOf(number);
        this.add(value);
        //**************************************
    }
    
    /**
     * Remove the least recently added number from the list. If the list is empty, do nothing.
     */
    public void deleteFirstNumber()
    {
        //******INSERT YOUR CODE HERE***********
        if(this.size() != 0){
            this.remove(0);
        }
        //**************************************
    }
    
    /**
     * Remove the most recently added number from the list. If the list is empty, do nothing.
     */
    public void deleteLastNumber()
    {
        //******INSERT YOUR CODE HERE***********
        if(this.size() != 0){
            this.remove(this.size() - 1);
        }
        //**************************************
    }
    
    /**
     * Calculate the summation of all the numbers in the list, then returns the sum. 
     * If the list is empty, return 0.
     * @return
     */
    public double getSum()
    {
        //******INSERT YOUR CODE HERE***********
        double sum = 0.0;
        for(double e: this){
            sum += e;
        }
        return sum;
        //**************************************
    }
    
    /**
     * Calculate and return the average of all the numbers in the list.
     * If the list is empty, return 0.
     * @return
     */
    public double getAvg()
    {
        //******INSERT YOUR CODE HERE***********
        if(this.size() == 0){
            return 0;
        }
        return this.getSum()/this.size();
        //**************************************
    }
    
    /**
     * Calculate and return the sample standard deviation of all the numbers in the list.
     * If the list has fewer than 2 numbers, return 0.
     * @return
     */
    public double getStd()
    {
        //******INSERT YOUR CODE HERE***********
        if(this.size() < 2){
            return 0;
        }
        double e_bar = this.getAvg();
        double sum = 0;
        for(double e: this){
            sum = sum + (e - e_bar) * (e - e_bar);
        }
        return  Math.sqrt(sum/(this.size() - 1));
        //**************************************
    }
    
    /**
     * 
     * @param mode for indicate select min or max index, true is max, false is min
     * @apiNote Iter over list, memorise min/max in index variable
     * @return index of min or max selected
     */
    private int maxMinIndex(boolean mode){
        if(this.size() == 0){
            return -1;
        }
        int index = 0;
        for(int i = 1; i < this.size(); i++){
            if((mode && this.get(i) > this.get(index)) || (!mode && this.get(i) < this.get(index))){
                index = i;
            }
        }
        return index;
    }



    /**
     * Find and return the maximum of all the numbers in the list.
     * If the list is empty, return 0.
     * @return
     */
    public double getMax()
    {
        //******INSERT YOUR CODE HERE***********
        int index = this.maxMinIndex(true);
        if(index == -1){
            return 0;
        }
        return this.get(index);
        //**************************************
    }

    /**
     * Find and return the minimum of all the numbers in the list.
     * If the list is empty, return 0.
     */
    public double getMin()
    {
        //******INSERT YOUR CODE HERE***********
        int index = this.maxMinIndex(false);
        if(index == -1){
            return 0;
        }
        return this.get(index);
        //**************************************
    }
    
    /**
     * 
     * @param mode, select either min or max
     * @return
     */
    private double[] getMinMaxK(int k, boolean mode){
        if(this.size() < k){
            return null;
        }
        if(this.size() == k){
            double temp[] = new double[k];
            int c = 0;
            for (double d : this) {
                temp[c++] = d;
            }
            return temp;

        }
        double temp[] = new double[k];
        Kalculator dup = (Kalculator) this.clone();
        int len = this.size(), c = 0, indexMax;
        
        for(int i = len - 1; i > len - k - 1; i--){
            indexMax = dup.maxMinIndex(mode);
            temp[c] = dup.remove(indexMax);
            c++;
        }
        return temp;
    }


    /**
     * Find and return the maximum k numbers of all the numbers in the list as an array of k double number.
     * The order of the returned k numbers does not matter. (We only care if you can get the max k elements)
     * If the list has fewer than k numbers, return null.
     */
    public double[] getMaxK(int k)
    {
        //******INSERT YOUR CODE HERE***********
        return this.getMinMaxK(k, true);
        //**************************************
    }
    
    /**
     * Find and return the minimum k numbers of all the numbers in the list as an array of k double number.
     * The order of the returned k numbers does not matter. (We only care if you can get the min k elements)
     * If the list has fewer than k numbers, return null.
     */
    public double[] getMinK(int k)
    {
        //******INSERT YOUR CODE HERE***********
        return this.getMinMaxK(k, false);
        //**************************************
    }
    
    /**
     * Print (via System.out.println()) the numbers in the list in format of:
     * DATA[<N>]:[<n1>, <n2>, <n3>, ...]
     * Where N is the size of the list. <ni> is the ith number in the list.
     * E.g., "DATA[4]:[1.0, 2.0, 3.0, 4.0]"
     */
    public void printData()
    {
        //******INSERT YOUR CODE HERE***********
        int len = this.size();
        System.out.printf("DATA[%d]:[", len);
        for(int i = 0; i < len; i++){
            System.out.printf("%.2f", this.get(i));
            if(i != len - 1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
        //**************************************
    }
}
