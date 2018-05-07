/*
 * Wizard.java
 * Version: 1
 * Revisions: 15
 */
import java.util.Scanner;
/**
 * Algorithm that determines whether it is possible for you to safely reach the
 * exit before the evil wizard turns you into a paperclip.
 *
 * @author: Rohan Shiroor (rss1103)
 * @author: Sagar Kukreja (sk3126)
 */
public class Wizard {
    public static Scanner sc = new Scanner(System.in);
    long[] D;
    int ans = -1;

    /**
     * Constructor for the class
     * @param n
     */
    public Wizard(int n){
        this.D = new long[n];
    }

    /**
     * The Bellman Ford Algorithm to find the Shortest path upto a certain k value.
     * @param G
     * @param n
     * @param k
     * @return ans
     */
    public int BellmanFord(int[][] G,int n,int k){
        int nEdges = G.length;
        long[] undirD = new long[n];
        int[][] undirG = new int[2*nEdges][3];
        // Create a new graph which is undirected that is add a reverse edge for every edge.
        for(int i=0;i<nEdges;i++){
            undirG[i][0] = G[i][0];
            undirG[i][1] = G[i][1];
            undirG[i][2] = G[i][2];
        }
        for(int i=0;i<nEdges;i++){
            undirG[nEdges+i][0] = G[i][1];
            undirG[nEdges+i][1] = G[i][0];
            undirG[nEdges+i][2] = G[i][2];
        }
        /*
        for(int i=0;i<(2*nEdges);i++){
            System.out.println(undirG[i][0]+" "+undirG[i][1]+" "+undirG[i][2]);
        }
        */
        // Initialize the Array.
        for(int i=1;i<n;i++){
            D[i] = Integer.MAX_VALUE;
            undirD[i] = Integer.MAX_VALUE;
        }
        // Perform the actual calculation on a copy of the array and then put it in the original array.
        for(int i=0;i<=k;i++) {
            for (int j = 0; j < (2*nEdges); j++) {
                if (D[undirG[j][1]] > (D[undirG[j][0]] + undirG[j][2])) {
                    if(undirD[undirG[j][1]] > (undirD[undirG[j][0]] + undirG[j][2]))
                    undirD[undirG[j][1]] = D[undirG[j][0]] + undirG[j][2];
                }
            }
            for(int j=0;j<n;j++){
                D[j] = undirD[j];
            }
        }
        // Check if there are negative cycles.
        for(int i=0;i<n;i++){
            if(D[G[i][1]]>(D[G[i][0]]+G[i][2]))
                ans = -1;
            else
                ans = (int)D[n-1];
        }
    return ans;
    }

    /**
     * Perform validation checking the condition if the Wizard can get out.
     * @param ans
     * @param t
     */
    public void Validation(int ans,int t){
        if(ans==-1)
            System.out.println("NO");
        else if(ans > t)
            System.out.println("NO");
        else
            System.out.println("YES");
    }

    /**
     * The main function of the program.
     * @param args
     */
    public static void main(String[] args){
        int n = sc.nextInt();
        int k = sc.nextInt();
        int t = sc.nextInt();
        int m = sc.nextInt();
        int [][] ptOfInterest = new int[m][3];
        int ans;
        for(int i=0;i<m;i++){
            ptOfInterest[i][0] = sc.nextInt();
            ptOfInterest[i][1] = sc.nextInt();
            ptOfInterest[i][2] = sc.nextInt();
        }
        Wizard wiz = new Wizard(n);
        ans = wiz.BellmanFord(ptOfInterest,n,k);
        wiz.Validation(ans,t);
        //System.out.println(ans);
        /*
        for(int i=0;i<m;i++){
            System.out.println(ptOfInterest[i][0]+" "+ptOfInterest[i][1]+" "+ptOfInterest[i][2]);
        }
        */

    }

}
