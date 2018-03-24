/*
 * Planters.java
 * Version: 1
 * Revisions: 4
 */
import java.util.Scanner;

/**
 * Algorithm that determines whether it is possible to
 * replant the plants so that each gets a larger planter than it had initially.
 *
 * @author: Rohan Shiroor (rss1103)
 * @author: Sagar Kukreja (sk3126)
 */
public class Planters {

    /**
     * Takes the input from the command line.
     * @param planter
     * @param sc
     * @param lim
     */
    public static void input(int[] planter,Scanner sc,int lim){
        for(int i=0;i<lim;i++){
            planter[i] = sc.nextInt();
        }
    }

    /**
     * Merge the two sorted arrays of size m and n.
     * @param planter1
     * @param planter2
     * @param planter_merge
     * @return
     */

    public int[][] mergeArrays(int[] planter1,int[] planter2,int[][] planter_merge){
        int i=0,j=0,k=0;
        int n = planter1.length; // Planter1 are the n planters which contain the initial plants.
        int m = planter2.length; // Planter2 are the m new planters which are bought.
        int len = m+n;
        // Check whether planter1 has the lower element or planter2.
        while(i<n && j<m){
            if(planter1[i]<planter2[j]){
                planter_merge[0][k]=planter1[i];
                planter_merge[1][k]=1;
                i++;
            }
            else {
                planter_merge[0][k] = planter2[j];
                planter_merge[1][k] = 2;
                j++;
            }
            k++;
        }
        // Insert the remaining elements into the array.
        while(i<n){
            planter_merge[0][k]=planter1[i];
            planter_merge[1][k]=1;
            i++;
            k++;
        }
        while (j<m){
            planter_merge[0][k]=planter2[j];
            planter_merge[1][k]=2;
            j++;
            k++;
        }
        return planter_merge;
    }

    /**
     * Perform the merge sort on an array.
     * This is a recursive function.
     * @param planter
     * @return
     */
    public int[] MergeSort(int[] planter){
        int[] A,B;
        int len = planter.length;
        if(len==1){
            return planter;
        }
        int middle = (len)/2;
        A = new int[middle];
        B = new int[len-middle];
        // Create subarray which stores elements from start to middle.
        for (int i=0;i<A.length;i++){
            A[i] = planter[i];
        }
        // Create subarray which stores elements from middle to end.
        for (int i =0;i<B.length;i++){
            B[i] = planter[middle+i];
        }
        // Recursively call merge sort with sub-arrays.
        MergeSort(A);
        MergeSort(B);
        // Call the merge function to merge the sub-arrays
        this.merge(A,B,planter);
        return planter;
    }

    /**
     * Merge the two sub-arrays for the merge sort algorithm.
     * @param As
     * @param Bs
     * @param arr
     */
    public void merge(int[] As,int[] Bs,int[] arr){
        int AsLength = As.length;
        int BsLength = Bs.length;
        int i=0,j=0,k=0;
        // Check whether As has the lower element or Bs.
        while (i<AsLength && j<BsLength){
            if(As[i]<Bs[j]){
               arr[k]=As[i];
               i++;
            }
            else {
                arr[k] = Bs[j];
                j++;
            }
            k++;
        }
        // Insert the remaining elements into the array.
        while(i<AsLength){
            arr[k]=As[i];
            i++;
            k++;
        }
        while (j<BsLength){
            arr[k]=Bs[j];
            j++;
            k++;
        }
    }

    /**
     * Function that determines whether it is possible to replant the plants
     * so that each gets a larger planter than it had initially.
     * Prints YES if possible and NO if not possible.
     * @param planter_merge
     * @param n
     * @param m
     */
    public static void output(int[][] planter_merge,int n,int m){
        int len = m+n,count=0,i=1;
        int pointer = 0;
        // Check if the last element of the combined array belongs to list m or list n.
        if (planter_merge[1][len-1]==1)
            System.out.println("NO");
        // Check if possible to replant the plants so that each gets a larger planter than it had initially.
        else {
            // Check if the current planter smaller than next planter and increment count if yes.
            while(i<len) {
                if (planter_merge[1][pointer] == 1 && planter_merge[0][pointer] < planter_merge[0][i]) {
                    count++;
                    i++;
                    // Check if the current planter equal to the next planter and keep on incrementing until next higher planter found.
                } else if (planter_merge[1][pointer] == 1 && planter_merge[0][pointer] == planter_merge[0][i]) {
                    pointer--;
                    i++;
                }
                pointer++;
            }
            // Print YES if all plants placed in a higher planter NO otherwise.
            if (count == n)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    /**
     * The main function of the program.
     * @param args
     */
    public static void main(String args[]){
        int [] planter1,planter2;
        int[][] planter;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Array containing the first n number of planters.
        planter1 = new int[n];
        int m = sc.nextInt();
        // Array containing the next m number of planters
        planter2 = new int[m];
        sc.useDelimiter("\\D");
        // Read input for the first n planters which contain plants
        input(planter1,sc,n);
        // Read input for the next m planters with no plants.
        input(planter2,sc,m);
        Planters plt = new Planters();
        planter = new int[2][m+n];
        // Perform Merge sort on array containing n planters
        plt.MergeSort(planter1);
        // Perform Merge sort on array containing m planters
        plt.MergeSort(planter2);
        // Merge the two sorted arrays.
        planter= plt.mergeArrays(planter1,planter2,planter);
        // Print the output YES or NO.
        output(planter,n,m);
        /*
        for (int i=0;i<n;i++){
            System.out.println(planter1[i]);
        }
        System.out.println("Set2:");
        for (int i=0;i<m;i++){
            System.out.println(planter2[i]);
        }
        for (int i =0;i<(m+n);i++){
            System.out.println(planter[0][i]+"\t"+planter[1][i]);
        }
        */
    }
}
