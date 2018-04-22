/*
 * Shovel.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * Design an O((mn)^2) algorithm to figure
 * out the fewest blocks of the sidewalk you will
 * need to shovel to forge a path to the doctor’s office.
 *
 * @author      Sagar Kukreja (sk3126@rit.edu)
 * @author      Rohan Shiroor (rss1103@rit.edu)
 */

import java.util.Iterator;
import java.util.Scanner;

public class Shovel{

    static int rows;
    static int columns;
    static int vertices;
    static ShovelGraph graph;
    static int[] distance;
    static int[] parent;
    static Boolean[] finalized;

    public static void main(String args[]){
        Shovel sh = new Shovel();
        sh.initialize();
        sh.computeDistance();
        //prints value for mth street and nth avenue
        System.out.println(distance[((rows-1)*(columns+1))+(columns-1)]);
    }

    //initialize
    public void initialize(){
        Scanner sc = new Scanner(System.in);
        rows = sc.nextInt();
        columns = sc.nextInt();
        vertices = (rows+1)*(columns+1);
        distance = new int[vertices];
        finalized = new Boolean[vertices];

        graph = new ShovelGraph(vertices);
        int k = 0;
        for(int i = 0;i <= rows;i++){
            int j = 0;
            while(j<columns){
                int weight = sc.nextInt();
                int source = k + j;
                int destination = source + 1;
                graph.addEdge(source,destination,weight);
                j++;
            }
            k = k + columns + 1;
        }

        for(int i = 0;i <= columns ;i++){
            int j = 0;
            int l = i;
            while(j < rows){
                int weight = sc.nextInt();
                int source = l ;
                int destination = l + columns + 1;
                graph.addEdge(source,destination,weight);
                j++;
                l = l + columns + 1;
            }
        }

    }

    //applies dijkstra algo
    public void computeDistance(){

        for(int i = 0;i < vertices;i++){
            distance[i] = Integer.MAX_VALUE;
            finalized[i] = false;
        }
        distance[0] = 0;
        for(int i = 0; i < vertices-1 ; i++){
            int u = this.minDistance();
            finalized[u] = true;
            Iterator<Node> it = graph.adjListArray[u].iterator();
            while(it.hasNext()){
                Node j = it.next();
                if(!finalized[j.vertex] && distance[u] != Integer.MAX_VALUE && distance[u] + j.weight < distance[j.vertex]){
                    distance[j.vertex] = distance[u] + j.weight;
                }
            }
        }
    }

    //computes minimum edge weight
    public int minDistance(){
        int min = Integer.MAX_VALUE, min_index = -1;
        for(int i = 0 ; i<vertices;i++){
                 if(!finalized[i] && distance[i] <= min ) {
                min = distance[i];
                min_index = i;
            }
        }
        return min_index;
    }
}
