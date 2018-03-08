/*
 * Donut.java
 * Version: 1
 * Revisions: 4
 */
import java.util.Scanner;
/**
 * Algorithm that finds that minimizes the sum of the distances that
   the traffic police have to travel to reach the donut store
 *
 * @author: Rohan Shiroor (rss1103)
 * @author: Sagar Kukreja (sk3126)
 */
public class Donut {

    /**
     * This block selects the pivot by brute forcing method if array length is <=5
     * otherwise divide the array in groups of n/5 and then find a pivot using median of medians algorithm.
     * @param arr
     * @param k
     * @return
     */
    public int Select(int[] arr,int k){
        int start,end,medianB;
        int i,largeCount=0,smallCount=0,equalCount=0;
        int[] B;
        int[] L,E,G;
        if (arr.length<=5){
            medianB = this.Median(arr,0,arr.length-1);
            return medianB;
        }
        int grp = (int)Math.floor(arr.length/5.0);
        start = 0;
        end = 4;
        B =new int[grp];
        for (i=0;i<grp;i++){
            if(i != (grp-1)) {
                B[i] = Median(arr, start, end);
                start = end + 1;
                end = end + 5;
            }
            else {
                B[i] = Median(arr, start, arr.length-1);
            }
        }
        medianB = Select(B,(int)Math.floor((B.length-1)/2.0));
        for (i = 0; i < arr.length; i++) {
            if (arr[i] > medianB)
                largeCount++;
            else if (arr[i] < medianB)
                smallCount++;
            else
                equalCount++;
        }
        G = new int[largeCount];
        L = new int[smallCount];
        E= new int[equalCount];
        int l=0,g=0,e=0;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] > medianB)
                G[g++] = arr[i];
            else if (arr[i] < medianB)
                L[l++] = arr[i];
            else
                E[e++] = arr[i];
        }
        if(k<L.length) return Select(L,k);
        else if (k>=L.length && k<(L.length+E.length)) return medianB;
        else return Select(G,(k-L.length-E.length));
    }

    /**
     * Brute Force to find the median
     * @param arr2
     * @param start
     * @param end
     * @return
     */
    public int Median (int [] arr2,int start,int end){
        int input[],median;
        input = new int[end-start+1];
        for(int j=0;j<input.length;j++){
            input[j]= arr2[start+j];
        }
        input = MergeSort(input);
        int k1 = (int)Math.floor((input.length-1)/2.0);
        median = input[k1];
        return median;
    }

    /**
     * Perform the Merge Sort on a list of 5 numbers.
     * @param arr
     * @return
     */
    public int[] MergeSort(int[] arr){
        int len = arr.length;
        int [] A,B;
        if (len==1)
            return arr;
        int middle = len/2;
        A = new int[middle];
        B = new int[len-middle];
        for(int i=0;i<A.length;i++){
            A[i] = arr[i];
        }
        for(int i=0;i<B.length;i++){
            B[i] = arr[middle+i];
        }
        MergeSort(A);
        MergeSort(B);
        this.merge(A,B,arr);
        return arr;
    }

    /**
     * Perform the Merge operation for the Merge Sort Algorithm.
     * @param As
     * @param Bs
     * @param arr2
     */
    public void merge(int[] As,int[] Bs,int[] arr2){
        int AsLength = As.length;
        int BsLength = Bs.length;
        int i=0,j=0,k=0;
        // Check whether As has the lower element or Bs.
        while (i < AsLength && j < BsLength) {
            if (As[i] < Bs[j]) {
                arr2[k] = As[i];
                i++;
                // If As and Bs have an equal element check the 2nd element of the 2d array.
            }
            // Choose Bs
            else {
                arr2[k] = Bs[j];
                j++;
            }
            k++;
        }
        while(i<AsLength){
            arr2[k]= As[i];
            i++;
            k++;
        }
        while (j<BsLength){
            arr2[k]=Bs[j];
            j++;
            k++;
        }
    }

    /**
     * Calculate the distance between the median point to the location of the traffic police
     * such that the distance is minimum.
     * @param x
     * @param y
     * @param arr1
     * @param arr2
     * @param n
     * @return
     */
    public int calcDist(int x,int y,int[] arr1,int[] arr2,int n){
        int dist = 0;
        for(int i=0;i<n;i++){
            dist += Math.abs(x-arr1[i])+Math.abs(y-arr2[i]);
        }
        return dist;
    }

    /**
     * The main program.
     * @param args
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] X = new int [n];
        int [] Y = new int [n];
        for(int i=0;i<n;i++){
            X[i] = sc.nextInt();
            Y[i] = sc.nextInt();
        }
       //output(X,Y,n);
        Donut D = new Donut();
        int k = (int)Math.floor((n-1)/2.0);
        int X_med,Y_med,dist;
        X_med = D.Select(X,k);
        //System.out.println(X_med);
        Y_med = D.Select(Y,k);
        //System.out.println(Y_med);
        dist = D.calcDist(X_med,Y_med,X,Y,n);
        System.out.println(dist);
    }
}
