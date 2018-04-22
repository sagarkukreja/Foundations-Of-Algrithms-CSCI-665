/*
 * LongestPathDAG.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * Give an O(m+n)
 * algorithm that computes the length of the longest path in a given directed
 * acyclic graph.
 *
 * @author      Sagar Kukreja (sk3126@rit.edu)
 * @author      Rohan Shiroor (rss1103@rit.edu)
 */

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class LongestPathDAG {

    static Stack<Integer> stack;
    static int vertices;
    static Graph graph;
    static boolean[] seen;
    static int[] finish;
    static int[] dist; //dynamic array

    public static void main(String args[]){
        LongestPathDAG lp = new LongestPathDAG();
        lp.initialize();
        lp.topologicalOrder();
    }

    //Initialize
    public void initialize(){
        Scanner sc = new Scanner(System.in);
        vertices = sc.nextInt();
        graph = new Graph(vertices);
        seen = new boolean[vertices];
        finish = new int[vertices];
        dist = new int[vertices];
        int edges = sc.nextInt();
        for(int i = 0;i<edges;i++){
            int src = sc.nextInt();
            int dest = sc.nextInt();
            graph.addEdge(graph,src,dest);
        }
        for(int i = 0;i<vertices;i++){
            seen[i] = false;
            finish[i] = Integer.MAX_VALUE;
            dist[i] = 0;
        }
    }

    //computes the topological ordering
    public void topologicalOrder(){
        stack = new Stack<>();
        for(int i = 0;i<graph.vertices;i++){
            if(!seen[i]){
                this.DFS(i);
            }
        }
        Integer source = stack.peek();
        dist[source] = 0;
        int max = 0;
        while(!stack.empty()){
            Integer u  = stack.peek();
            stack.pop();
            Iterator<Integer> it = graph.adjListArray[u].iterator();
            while(it.hasNext()){
                Integer j = it.next();
                if(dist[j] < dist[u]+1){
                    dist[j] = dist[u]+1;
                    if(max<dist[j])
                        max = dist[j];
                }
            }
        }
        System.out.print(max);
    }

    //Runs DFS on Graph
    public void DFS(int i){
        seen[i] = true;
        Iterator<Integer> it = graph.adjListArray[i].iterator();
        while(it.hasNext()){
            Integer j = it.next();
            if(!seen[j]){
                DFS(j);
            }
        }
        stack.push(i);
    }
}
