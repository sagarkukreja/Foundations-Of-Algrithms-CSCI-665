/*
 * LongestIncreasingSubseqDP.java
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

public class LongestIncreasingSubseqDP {

    public static void main(String args[]){
        LongestIncreasingSubseqDP d = new LongestIncreasingSubseqDP();
        int[] input = d.initialize();
        long startTime = System.currentTimeMillis();
        d.findSequence(input);
        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println("Time taken :: " + timeTaken + "ms");

    }

    //block for taking input from user
    public int[] initialize(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] input = new int[n];
        for(int i = 0;i<n ;i++){
            input[i] = sc.nextInt();
        }
        return input;
    }

    //implements dynamic programing algorithm to find longest increasing subsequence
    public void findSequence(int[] input){
        int n = input.length;
        int[] solutionArray = new int[n+1]; // array that keep tracks of subseqence
        solutionArray[0] = 0;
        int max = solutionArray[0]; // to find the max among all values of solutionArray
        for(int i = 1; i <= n;i++){
            solutionArray[i] = 1;
            for(int j = 1; j<=i-1;j++){
                if(input[j-1] < input[i-1] && solutionArray[i] < solutionArray[j] + 1){
                    solutionArray[i] = solutionArray[j] + 1;
                }
                if(max < solutionArray[i])
                    max = solutionArray[i];
            }
        }
        System.out.println(max);
    }
}
