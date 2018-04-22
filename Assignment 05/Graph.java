import java.util.LinkedList;

class Graph{

    int vertices;
    LinkedList<Integer> adjListArray[]; //array of linkedlist containing nodes
    Graph(int vertices){
        this.vertices = vertices;
        this.adjListArray = new LinkedList[vertices];
        for(int i = 0;i<vertices;i++){
            adjListArray[i] = new LinkedList<>();
        }
    }

    //function to add edge to nodes
    public void addEdge(Graph graph, int source, int destination){
        graph.adjListArray[source].add(destination);
    }
}