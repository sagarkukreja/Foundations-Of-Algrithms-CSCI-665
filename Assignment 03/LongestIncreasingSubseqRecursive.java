/*
 * LongestIncreasingSubseqRecursive.java
 * Version: 1
 * Revisions: 4
 */
import java.util.Scanner;
/**
 * Algorithm that finds longest increasing subsequence using the recusrsive approach.
 *
 * @author: Rohan Shiroor (rss1103)
 * @author: Sagar Kukreja (sk3126)
 */
public class LongestIncreasingSubseqRecursive {
    static int max;

    /**
     *  Recursively get all LIS ending with arr[0], arr[1] ...
        arr[n-2]. If   arr[i-1] is smaller than arr[n-1], and
        max ending with arr[n-1] then update it.
     * @param arr
     * @param n
     * @return
     */
    public static int LIS(int[] arr,int n){

        // base case of the algorihm.
        if (n==1)
            return 1;
        // maxCount is length of LIS ending with arr[n-1]
        int res,maxCount =1;
        for(int i=1;i<n;i++){
            res = LIS(arr,i);
            if(arr[i-1]<arr[n-1] && res + 1 > maxCount)
                maxCount = res + 1;
        }
        // Compare maxCount with Max.
        if (max < maxCount)
         max = maxCount;
        return maxCount;
    }

    /**
     * This is the driver function, which calls the recursive function.
     * @param arr
     * @param n
     * @return
     */
    public static int Lis_driver(int[] arr,int n) {
        max = 1;
        LIS(arr,n);
        return max;
    }

    /**
     * The main function
     * @param args
     */
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        int[] A;
        int max;
        // Take number of inputs.
        int n = sc.nextInt();
        A = new int[n];
        // Take inputs.
        for(int i=0;i<n;i++){
            A[i] = sc.nextInt();
        }
     //long startTimeInsertion = System.nanoTime();
     max = Lis_driver(A,n);
     //long stopTimeInsertion = System.nanoTime();
     System.out.println(max);
     //long timeTakenInsertion = stopTimeInsertion - startTimeInsertion;
     //System.out.println(timeTakenInsertion + "  ns for insertion sort with size " + n);
    }
}
