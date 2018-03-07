/*
 * DoubleKnapsack.java
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

public class DoubleKnapsack {

    //class to hold items that we input
    class Items{
        int id;
        int weight;
        int cost;

        Items(int id, int weight , int cost){
            this.id = id;
            this.weight = weight;
            this.cost = cost;
        }
    }

    static int n;
    static int w1;//weight of knapsack 1
    static int w2; // weight of knapsack 2
    static int[][][] dpArray; // dynamic programming array that holds the solutions to sub-problems as well
    static  Items[] items; // array of Items class objects


    public static void main(String args[]){
        DoubleKnapsack d = new DoubleKnapsack();
        d.initialize();
        d.fillKnapsack();
    }

    //intialize the array and takes input from user
    public void initialize(){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        w1 = sc.nextInt();
        w2 = sc.nextInt();

        items = new Items[n+1];
        items[0] = new Items(0,0,0);
        for(int i = 1; i <= n;i = i+1){
            items[i] = new Items(i, sc.nextInt(),sc.nextInt());
        }

        dpArray = new int[n+1][w1+1][w2+1];
        for(int i = 0; i <= n ; i++){
            dpArray[i][0][0] = 0;
        }

        for(int i = 0; i <= w1 ; i++){
            dpArray[0][i][0] = 0;
        }

        for(int i = 0; i <= w2 ; i++){
            dpArray[0][0][i] = 0;
        }

    }

    //code to fill knapsack
    public void fillKnapsack(){
        for (int i = 1;i<=n;i++){
            for(int k1 = 0; k1 <= w1;k1++){ //base case we start from 0 not from 1
                for(int k2 = 0;k2 <=w2;k2++){ //base case we start from 0 not from 1
                    dpArray[i][k1][k2] = dpArray[i-1][k1][k2]; // value when item i is not included

                    //item i is included in knapsack 1
                    if(items[i].weight <= k1 && items[i].cost+dpArray[i-1][k1 - items[i].weight][k2] > dpArray[i][k1][k2]){
                        dpArray[i][k1][k2] = items[i].cost+dpArray[i-1][k1 - items[i].weight][k2];
                    }

                    //item i is included in knapsack 2
                    if(items[i].weight <= k2 && items[i].cost+dpArray[i-1][k1][k2-items[i].weight] > dpArray[i][k1][k2]){
                        dpArray[i][k1][k2] = items[i].cost+dpArray[i-1][k1][k2-items[i].weight];
                    }
                }
            }
        }
        System.out.println(dpArray[n][w1][w2]);
    }
}
