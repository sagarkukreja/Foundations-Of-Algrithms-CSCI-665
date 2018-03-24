/*
 * Match.java
 *
 * Version: 1
 *
 * Revisions: 3
 *
 */

import java.util.*;
/**
 * This program implements the Gayle-shapley algorithm and
 * finds all the stable matching pairs.
 *
 * @author      Rohan Shiroor (rss1103@rit.edu)
 * @author      Sagar Kukreja (sk3126@rit.edu)
 *
 */

public class Match {

    int [][] setA; //Contains the elements belonging to group 1
    int [][] setB; //Contains the elements belonging to group 2
    int [] count; //Keeps track of the number of ‘asks’ made by group 1.
    int [] current; // The current assignment for group 1.

    /**
     * Constructor for the Match class.
     * @param n
     */
    public Match(int n){
        count = new int[n];
        current = new int[n];
        Arrays.fill(current,-1);
        Arrays.fill(count, 0);
    }

    /**
     * Read the input from the command line.
     * @param arr
     * @param n
     * @param sc
     */
    public static void input(int [][] arr,int n,Scanner sc){
        int i,j;
       for(i=0;i<n;i++){
          for(j=0;j<n;j++){
              arr[i][j]= sc.nextInt();
          }
          sc.nextLine();
       }
    }

    /**
     * Preprocessing the two arrays. Involves reversing
     * the array for Group which is asked.
     * @param arr1
     * @param arr2
     * @param n
     * @param flag
     */
    public void preprocess(int [][] arr1,int[][] arr2,int n,int flag){
        int i,j;
        // Check if 1st round of GS
        // If 1st round Group 1 asks Group 2
        // Inverse Group 2 elements
        if (flag==0){
            this.setA = new int[n][n];
            this.setB = new int[n][n];
            for(i=0;i<n;i++){
                for(j=0;j<n;j++){
                    this.setB[i][arr2[i][j]] = j;
                    this.setA[i][j] = arr1[i][j];
                }
            }
        }
        // If 2nd round Group 2 asks Group 1
        // Inverse Group 1 elements
        else {
            this.setA = new int[n][n];
            this.setB = new int[n][n];
            for(i=0;i<n;i++){
                for(j=0;j<n;j++){
                    this.setB[i][arr1[i][j]] = j;
                    this.setA[i][j]=arr2[i][j];
                }
            }
        }
    }

    /**
     * Initialize the stack with given list
     * in the ascending order starting with 0.
     * @param stk
     * @param n
     */
    public void initializeStack(StackArray stk,int n){
        for(int i=n-1;i>=0;i--){
            stk.push(i);
        }
    }

    /**
     * Perform the GS Stable Matching Algorithm
     * @param stk
     * @param n
     * @return
     */
    public int[] stableMatch(StackArray stk,int n){
        int p,i,s;
        // some prof is free and hasn't asked every student
        while (stk.isempty()==false){
            // Choose such a prof p
            p = stk.pop();
            // 1st student on p's list whom p has not yet asked.
            s = this.setA[p][this.count[p]];
            for(i=0;i<n;i++){
                if(this.current[i]==s)
                    break;
            }
            // s is free. assign p and s to be partners
            if (i==n){
                this.count[p]++;
                this.current[p]=s;
            }
            // s prefers p to current partner p'. assign p and s to be partners, and p' to be free
            else if(this.setB[s][p]<setB[s][i]){
                count[p]++;
                current[p]=s;
                current[i] = -1;
                stk.push(i);
            }
            // s rejects p
            else {
                count[p]++;
                stk.push(p);
            }
        }
        // Return the list of assignments.
        return current;
    }

    /**
     * Print the count of stable matching pairs.
     * @param n
     * @param matchings1
     * @param matchings2
     */
    public static void output(int n,int[] matchings1,int[] matchings2){
        int ele,count=0;
        // Check the pair which does not change for matchings.
        for (int i=0;i<n;i++){
            ele = matchings1[i];
            if(matchings2[ele]==i){
                count++;
            }
        }
        // Print the count
        System.out.println(count);
    }

    /**
     * The main function of the algorithm
     * @param args
     */
    public static void main(String args[]){
        int [][] array1;
        int [][] array2;
        int [] matchings1,matchings2;
        int flag;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.useDelimiter("\\D");
        array1 = new int[n][n];
        array2 = new int[n][n];
        matchings1 = new int[n];
        matchings2 = new int[n];
        // Input for Set A
        input(array1,n,sc);
        // Input for Set B
        input(array2,n,sc);
        // 1st iteration of GS. Set A asks set B
        flag = 0;
        Match mth1 = new Match(n);
        StackArray stk1 = new StackArray(n);
        mth1.preprocess(array1,array2,n,flag);
        mth1.initializeStack(stk1,n);
        // Pairings returned after 1st iteration.
        matchings1 = mth1.stableMatch(stk1,n);
        // 2nd iteration of GS.Set B asks set A.
        flag = 1;
        Match mth2 = new Match(n);
        StackArray stk2 = new StackArray(n);
        mth2.preprocess(array1,array2,n,flag);
        mth2.initializeStack(stk2,n);
        // Pairings returned after 2nd iteration.
        matchings2 = mth2.stableMatch(stk2,n);
        output(n,matchings1,matchings2);
        /*
        for(int i=0;i<n;i++){
            System.out.println("i:"+i+" val:"+matchings1[i]);
        }
        for(int i=0;i<n;i++){
            System.out.println("i:"+i+" val:"+matchings2[i]);
        }
        */


    }
}
