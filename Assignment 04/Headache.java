/*
 * Headache.java
 * Version: 1
 * Revisions: 3
 */
import java.util.Scanner;
/**
 * Algorithm that computes the minimum units of headache you will incur
 * getting all of the company employees on the ride.
 *
 * @author: Rohan Shiroor (rss1103)
 * @author: Sagar Kukreja (sk3126)
 */
public class Headache {
    public static Scanner sc = new Scanner(System.in);

    /**
     * Read the input from the command Line.
     * @param lim
     * @param arr
     * @return
     */
    public static String[] Input(int lim, String[] arr){
        for(int i=0;i<lim;i++){
            arr[i] = sc.next();
        }
        return arr;
    }

    /**
     * Find the minimum amongst the 5 values.
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @return
     */
    public int findMin(int a,int b,int c,int d,int e){
        int arr[] = new int[]{a,b,c,d,e};
        int smallest=999999;
        for (int k=0;k<5;k++){
            if(arr[k]<=smallest)
                smallest = arr[k];
        }
        return smallest;
    }

    /**
     * Perform Sequence Alignment for the 2 Lines.
     * @param m
     * @param n
     * @param mArr
     * @param nArr
     * @return
     */
    public int Seq_Align(int m,int n,String[] mArr,String[] nArr){
        int[][] S;
        S = new int[m+1][n+1];
        // a1-mismatch penalty for i and j
        // a2-mismatch penalty for i and i-1
        // a3-mismatch penalty for j and j-1
        // g1-gap penalty
        // g2-penalty for selecting 2 elements from the same line.
        int a1=0,a2=0,a3=0,g1=4,g2=3;
        // Total Penalty for selecting employees from same line
        int ag1 = 999999,ag2 = 999999;
        S[0][0] = S[1][0] = S[0][1] = 0;
        // Fill in the first row of the DP array.
        // Only two cases mismatch for i and i-1 and gap.
        for (int i=2;i<=m;i++){
            if((mArr[i-1].equals("E") && mArr[i-2].equals("N"))||(mArr[i-1].equals("N") && mArr[i-2].equals("E")))
                a2 = 5;
            else
                a2 = 0;
            S[i][0] = Math.min((a2+S[i-2][0]),(g1+S[i-1][0]));
        }
        g1 = 4;
        // Fill in the first column of the DP array.
        // Only two cases mismatch for i and i-1 and gap.
        for (int j=2;j<=n;j++){
            if((nArr[j-1].equals("E") && nArr[j-2].equals("N"))||(nArr[j-1].equals("N") && nArr[j-2].equals("E")))
                a3 = 5;
            else
                a3 = 0;
            S[0][j] = Math.min((a3+S[0][j-2]),(g1+S[0][j-1]));
        }
        a3=0;
        a2=0;
        g1=4;
        // When m and n both contain employees.
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++) {
                //Mismatch penalty
                if((mArr[i-1].equals("E") && nArr[j-1].equals("N"))||(mArr[i-1].equals("N") && nArr[j-1].equals("E")))
                    a1 = 5;
                else
                    a1=0;
                if (i>1) {
                    // Mimsatch penalty.
                    if ((mArr[i - 1].equals("E") && mArr[i - 2].equals("N")) || (mArr[i - 1].equals("N") && mArr[i - 2].equals("E")))
                        a2 = 5;
                    else
                        a2 = 0;
                    // Penalty for selecting both employees from line 1.
                   ag1 =  (g2+a2+S[i-2][j]);
                }
                if(j>1) {
                    //Mismatch penalty
                    if ((nArr[j - 1].equals("E") && nArr[j - 2].equals("N")) || (nArr[j - 1].equals("N") && nArr[j - 2].equals("E")))
                        a3 = 5;
                    else
                        a3 = 0;
                    // Penalty for selecting both employees from line 2.
                    ag2 = (g2+a3+S[i][j-2]);
                }
                // 5 penalty cases
                // Gap penalty for 1st line.
                // Gap penalty for 2nd line.
                // Mismatch penalty
                // Select both employees from 1st line + mismatch penalty.
                // Select both employees from 2nd line + mismatch penalty.
                S[i][j] = this.findMin((a1+S[i-1][j-1]),(g1+S[i-1][j]),(g1+S[i][j-1]),ag1,ag2);
            }
            ag2 = 999999;
        }
        // Return the minimum value for the headache.
        return S[m][n];

    }

    /**
     * The main function of the program.
     * @param args
     */
    public static void main(String[] args){
        // Read m and n.
        int m = sc.nextInt();
        int n = sc.nextInt();
        String[] mLine = new String[m];
        String[] nLine = new String[n];
        // Read the characters for the 2 Lines.
        mLine = Input(m,mLine);
        nLine = Input(n,nLine);
        Headache hd = new Headache();
        int cost;
        // Find the minimum cost for headache.
        cost = hd.Seq_Align(m,n,mLine,nLine);
        // Print the cost
        System.out.println(cost);
        /*
        int [][] dp;
        dp= hd.Seq_Align(m,n,mLine,nLine);
        for(int i=m;i>=0;i--){
            for(int j=0;j<=n;j++){
                System.out.print(dp[i][j]+"\t");
            }
            System.out.println("\n");
        }
        */
    }

}
