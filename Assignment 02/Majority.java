/*
 * Majority.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
import java.util.Scanner;

/**
 * This program finds there is a number in an array that occurs
 *  n/2 and n/3 times respectively in O(n) time complexity.
 *
 * @author      Sagar Kukreja (sk3126@rit.edu)
 * @author      Rohan Shiroor (rss1103@rit.edu)
 */


public class Majority {

    static int n;
    int[] inputArray ;

    public static void main(String args[]){
        int pivot;
        int proportion = 2;
        Majority s = new Majority();
        s.initialize();
        //selects pivot element
        pivot = s.selectPivotAlgo(s.inputArray);

        //checks if any element is present n/2 times
        String s1 = s.findElement(s.inputArray,pivot,proportion);
        System.out.println(s1);
        proportion = 3;

        //checks if any element is present n/3 times
        String s2 = s.findElement(s.inputArray,pivot,proportion);
        System.out.println(s2);

    }

    /*Creates array by reading elements from command line*/
    public void initialize(){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        inputArray = new int[n];

        for(int i = 0;i<n;i++){
            inputArray[i] = sc.nextInt();
        }
    }

    /*This block selects the pivot by brute forcing method if array length is <=5
    * otherwise divide the array in groups of n/5 and then find a pivot using median of medians algo*/

    public int selectPivotAlgo(int[] temp){
        int pivot,medianArray[],start,end;
        if(temp.length <=5)
            pivot = bruteForceSelect(temp,0,temp.length-1);
        else {
            medianArray = new int[(int) Math.ceil(temp.length / 5.0)];
            start = 0;
            end = 4;
            for (int i = 0; i < medianArray.length; i++) {
                if (i != medianArray.length - 1) {
                    medianArray[i] = bruteForceSelect(temp, start, end);
                    start = end + 1;
                    end = end + 5;
                } else {
                    medianArray[i] = bruteForceSelect(temp, start, temp.length - 1);
                }
            }
            pivot = selectPivotAlgo(medianArray);
        }
        return pivot;

    }

    /*brute force method to find pivot/median*/

    public int bruteForceSelect(int[] temp,int start,int end){
        int x, medianValue,input[];
        input = new int[end-start+1];
        for(int i =0;i<input.length;i++){
            input[i] = temp[i+start];
        }
        for (int i = 1;i<input.length;i++){
            for(int j =i;j>0;j--){
                if(input[j]<input[j-1]){
                    x = input[j];
                    input[j] = input[j-1];
                    input[j-1] = x;
                }

            }
        }
        medianValue = input[(input.length-1)/2];
        return medianValue;
    }

    /* After selecting a pivot element , we divide the array into 3 parts,
    * large array contains elements that are larger than pivot, smaller array contains
    * elements smaller than pivot, and equal array contains elements equal to pivot.
     * We then check if equal array has n/2 and n/3 elements and outputs yes
     * else we recurse on larger and smaller array and apply the same algo.
     * If nothing is found we output NO */


    /*divides the array in three parts*/
    public String findElement(int temp[], int pivot,int proportion) {
        int largerThanPivotArray[], smallerThanPivotArray[], equalToPivotArray[];
        int largeCount = 0, smallCount = 0, equalCount = 0, l = 0, s = 0, e = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] > pivot)
                largeCount++;
            else if (temp[i] < pivot)
                smallCount++;
            else
                equalCount++;
        }
        largerThanPivotArray = new int[largeCount];
        smallerThanPivotArray = new int[smallCount];
        equalToPivotArray = new int[equalCount];

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] > pivot)
                largerThanPivotArray[l++] = temp[i];
            else if (temp[i] < pivot)
                smallerThanPivotArray[s++] = temp[i];
            else
                equalToPivotArray[e++] = temp[i];
        }
        return checkElement(temp,equalToPivotArray, largerThanPivotArray, smallerThanPivotArray,proportion);
    }

    /*recurse and compares with equal array*/

     public String checkElement(int[] input, int[] equal, int[] large, int[] small, int proportion) {
         if (equal.length > n/ proportion) {
             return "YES";
         }
         else if (large.length > n / proportion) {
             int p = selectPivotAlgo(large);
             return findElement(large, p,proportion);
         } else if (small.length > n / proportion) {
             int p = selectPivotAlgo(small);
             return findElement(small, p,proportion);
         } else {
             return "NO";
         }
    }
}
