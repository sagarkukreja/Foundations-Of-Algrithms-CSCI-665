/*
 * Register.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Consider the problem of students registering for courses. For each student, you are given a set of
 * courses that the student is willing and able to take. No student may take more than 3 courses. Each
 * course also has a maximum number of students than can enroll, though this may be different for
 * different courses. Create an efficient algorithm that will compute the maximum sum of registered
 * students over all courses.
 *
 * @author      Sagar Kukreja (sk3126@rit.edu)
 * @author      Rohan Shiroor (rss1103@rit.edu)
 *
**/

public class Register {

    static int numStudents;
    static int numCourses;
    static int vertices;
    static int[][] adjMatrix; // to represent graph as adjacency matrix

    public static void main(String args[]){
        Register r = new Register();
        r.initialize(); //initializes the graph
        r.applyFordFulkerson(); // applies ford-fulkerson
    }

    public void initialize() {
        Scanner sc = new Scanner(System.in);
        numStudents = sc.nextInt();
        numCourses = sc.nextInt();
        vertices = numCourses + numStudents + 2; //+2 to make graph single source single sink
        adjMatrix = new int[vertices][vertices];

        //capacity of students will be 3. so we add weight from source to all students to be 3
        for(int i = 1;i<= numStudents;i++){
            adjMatrix[0][i] = 3;
        }
        String s = sc.nextLine();

        //edge from students to source has a flow of 1 unit
        for(int i = 1;i<=numStudents;i++){
            String input = sc.nextLine();
            String[] numbers = input.split(" ");
            for(int j = 0;j< numbers.length;j++){
                adjMatrix[i][Integer.parseInt(numbers[j])+numStudents] = 1;
            }
        }

        //edge from courses to sink has a given flow
        for(int i = 1;i<= numCourses;i++){
            adjMatrix[i+numStudents][vertices-1] = sc.nextInt();
        }
    }


    public void applyFordFulkerson(){
        int sourceVertex = 0;
        int sinkVertex = vertices-1;
        int u,v;

        //initially residual graph values are  equal to original graph values
        int[][] residualGraph = new int[vertices][vertices];
        for(u = 0;u<vertices;u++) {
            for (v = 0; v < vertices; v++) {
                residualGraph[u][v] = adjMatrix[u][v];
            }
        }
                int parent[] = new int[vertices];
                int max_flow = 0;

                while(bfs(residualGraph,sourceVertex,sinkVertex, parent)){
                    int path_flow =  Integer.MAX_VALUE;

                    //find minimum value edge in path returned
                    for(v = sinkVertex; v != sourceVertex ; v = parent[v]){
                        u = parent[v];
                        path_flow = Math.min(path_flow,residualGraph[u][v]);
                    }

                    //updates residual graph
                    for(v = sinkVertex; v != sourceVertex ; v = parent[v]){
                        u = parent[v];
                        residualGraph[u][v] -= path_flow;
                        residualGraph[v][u] += path_flow;
                    }

                    max_flow += path_flow;//adds min edge to max flow , every time  there is a path
                }
        System.out.println(max_flow);
    }

    //computes path from source node to sink and returns true if it exists
    public boolean bfs(int[][] rGraph, int source , int sink, int[] parent){
        boolean[] visited  = new boolean[vertices];
        for(int v = 0;v < vertices;v++){
            visited[v]= false;
        }
        LinkedList<Integer> bfsQueue = new LinkedList<Integer>();
        bfsQueue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while(bfsQueue.size()!=0){
            int u = bfsQueue.poll();
            for(int v = 0; v < vertices ; v++){
                if(!visited[v] && rGraph[u][v] > 0){
                    bfsQueue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return (visited[sink]==true);
    }
}