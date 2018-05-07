/*
 * Spies.java
 * Version: 1
 * Revisions: 3
 */
import java.util.Scanner;
/**
 * Algorithm that finds a minimum cost network to connect all of your spies with the
 additional requirement that a message can be passed from any spy to any other spy without
 ever having to go through an unreliable spy as an intermediate step along the path.
 *
 * @author: Rohan Shiroor (rss1103)
 * @author: Sagar Kukreja (sk3126)
 */
public class Spies {
    public static Scanner sc = new Scanner(System.in);
    Node[] N;

    /**
     * Constructor for the class
     * @param n
     */
    public Spies(int n){
        this.N = new Node[n];
        for(int i=0;i<n;i++){
            N[i] = new Node(n);
        }
    }

    /**
     * The main function to construct MST using the Kruskal's algorithm.
     * @param G1
     * @param n
     * @param Gx
     * @return EdgeMST
     */
    public int[][] Kruskal(int[][] G1,int n,int[][] Gx){
        // Minimum spanning tree following the restrictions.
        int [][] EdgeMST = new int[n-1][3];
        int m = G1.length;
        int k = Gx.length;
        int x,y;
        int j=0;
        boolean flag;
        // Sort the edges in increasing order of weight
        G1 = this.MergeSort(G1);
        // initialize union-find arrays
        this.Init(n);
        for(int i=0;i<m;i++){
            // verify new edge added does not create cycle.
            x = this.Find(G1[i][0]);
            y = this.Find(G1[i][1]);
            // Validate whether edge satisfies the constraints.
            flag = this.validateEdge(G1[i][0],G1[i][1],Gx);
            if (x != y && flag){
                EdgeMST[j] = G1[i];
                j++;
                // union of sets containing u, v
                this.Union(G1[i][0],G1[i][1]);
            }
        }
        return EdgeMST;
    }

    /**
     * verify new edge added does not create cycle.
     * @param e
     * @return
     */
    public int Find(int e){
        return this.N[e].boss;
    }

    /**
     * initialize union-find arrays
     * @param n
     */
    public void Init(int n){
        for(int i=0;i<n;i++){
            // boss of current node.
            this.N[i].boss = i;
            // size of current node.
            this.N[i].size = 1;
            // Vertices in this set.
            this.N[i].V[0] = i;
        }
    }

    /**
     * Perform the union operations.
     * @param u
     * @param v
     */
    public void Union(int u,int v){
        int j=0;
        int e1,e2,e;
        e1 = this.N[u].boss;
        e2 = this.N[v].boss;
        // Attach smaller size tree to root of higher size tree.
        if(this.N[e1].size > this.N[e2].size){
            for(int i= this.N[e1].size;i<this.N[e1].size+this.N[e2].size;i++){
                this.N[e1].V[i] = this.N[e2].V[j];
                e = this.N[e2].V[j];
                this.N[e].boss = e1;
                j++;
            }
            this.N[e1].size+=this.N[e2].size;
        }else {
            for(int i= this.N[e2].size;i<this.N[e1].size+this.N[e2].size;i++){
                this.N[e2].V[i] = this.N[e1].V[j];
                e = this.N[e1].V[j];
                this.N[e].boss = e2;
                j++;
            }
            this.N[e2].size+=this.N[e1].size;
        }
    }

    /**
     * Validate if the edge can be added to the MST
     * @param u
     * @param v
     * @param Gx
     * @return
     */
    public boolean validateEdge(int u, int v,int[][] Gx) {
        boolean flag1 = true, flag2 = true;
        int idxu = -1, idxv = -1;
        // Check if any of the 2 vertices are unreliable. Set appropriate flag.
        for (int i = 0; i < Gx.length; i++) {
            if (Gx[i][0] == u) {
                flag1 = false;
                idxu = i;
            } else if (Gx[i][0] == v) {
                flag2 = false;
                idxv = i;
            }
        }   // If both are unreliable edge cannot be added
            if (!flag1 && !flag2)
                return false;
            // If both are reliable ok to add.
            else if(flag1 && flag2)
                return true;
            // If first node unreliable check if there exists a previous edge to it.
            else if (!flag1 && Gx[idxu][1] != 1) {
                Gx[idxu][1] = 1;
                return true;
            // If second node unreliable check if there exists a previous edge to it.
            }else if(!flag2 && Gx[idxv][1] != 1){
                Gx[idxv][1] = 1;
                return true;
            }
        return false;
        }

    /**
     *  Merge Sort to sort according to edge weights.
     * @param arr2
     * @return
     */
    public int[][] MergeSort(int[][] arr2){
        int[][] A,B;
        int len = arr2.length;
        if(len==1){
            return arr2;
        }
        int middle = (len)/2;
        A = new int[middle][3];
        B = new int[len-middle][3];
        // Create subarray which stores elements from start to middle.
        for (int i=0;i<A.length;i++){
            A[i][0] = arr2[i][0];
            A[i][1] = arr2[i][1];
            A[i][2] = arr2[i][2];
        }
        // Create subarray which stores elements from middle to end.
        for (int i =0;i<B.length;i++){
            B[i][0] = arr2[middle+i][0];
            B[i][1] = arr2[middle+i][1];
            B[i][2] = arr2[middle+i][2];
        }
        // Recursively call merge sort with sub-arrays.
        MergeSort(A);
        MergeSort(B);
        // Call the merge function to merge the sub-arrays
        this.merge(A,B,arr2);
        return arr2;
    }

    /**
     * Perform merge part of merge sort.
     * @param As
     * @param Bs
     * @param arr
     */
    public void merge(int[][] As,int[][] Bs,int[][] arr){
        int AsLength = As.length;
        int BsLength = Bs.length;
        int i=0,j=0,k=0;
        // Check whether As has the lower element or Bs.
        while (i < AsLength && j < BsLength) {
            if (As[i][2] < Bs[j][2]) {
                arr[k][0] = As[i][0];
                arr[k][1] = As[i][1];
                arr[k][2] = As[i][2];
                i++;
            } else {
                arr[k][0] = Bs[j][0];
                arr[k][1] = Bs[j][1];
                arr[k][2] = Bs[j][2];
                j++;
            }
            k++;
        }

        // Insert the remaining elements into the array.
        while(i<AsLength){
            arr[k][0]= As[i][0];
            arr[k][1] = As[i][1];
            arr[k][2] = As[i][2];
            i++;
            k++;
        }
        while (j<BsLength){
            arr[k][0]=Bs[j][0];
            arr[k][1] = Bs[j][1];
            arr[k][2] = Bs[j][2];
            j++;
            k++;
        }
    }

    /**
     * Main function of the program.
     * @param args
     */
    public static void main(String[] args){
        // Take in the inputs
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int [][] unrelVert = new int[k][2];
        int [][] G = new int[m][3];
        int[][] SpTree;
        int tot = 0;
        Spies sp = new Spies(n);
        for(int i=0;i<k;i++){
            unrelVert[i][0] = sc.nextInt();
            unrelVert[i][1] = 0;
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<3;j++){
                G[i][j] = sc.nextInt();
            }
        }
        SpTree = sp.Kruskal(G,n,unrelVert);
        //System.out.println(G.length);
        for(int i=0;i<n-1;i++){
            //System.out.println(SpTree[i][0]+" "+SpTree[i][1]+" "+SpTree[i][2]);
            if(SpTree[i][0]==0 && SpTree[i][1]==0 && SpTree[i][2]==0)
                tot = 0;
            else
                tot += SpTree[i][2];
        }
        // Print the output.
        if(tot==0)
            System.out.println("NONE");
        else
            System.out.println(tot);
    }
}
