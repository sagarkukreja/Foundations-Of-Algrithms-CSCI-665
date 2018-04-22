/*
 * ShovelGraph.java
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

import java.util.LinkedList;

class ShovelGraph{

    int vertices;
    LinkedList<Node> adjListArray[];
    ShovelGraph(int vertices){
        this.vertices = vertices;
        this.adjListArray = new LinkedList[vertices];
        for(int i = 0;i<vertices;i++){
            adjListArray[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination , int weight){
        Node node = new Node(destination,weight);
        this.adjListArray[source].add(node);
    }
}