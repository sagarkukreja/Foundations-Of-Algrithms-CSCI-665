import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

 class Graph{

    int vertices;
    LinkedList<Integer> adjListArray[];
    Graph(int vertices){
        this.vertices = vertices;
        this.adjListArray = new LinkedList[vertices];
        for(int i = 0;i<vertices;i++){
            adjListArray[i] = new LinkedList<>();
        }
    }

    public void addEdge(Graph graph, int source, int destination){
        graph.adjListArray[source].add(destination);
    }
    /*public  void printGraph(Graph graph)
    {
        for(int v = 0; v < graph.vertices; v++)
        {
            System.out.println("Adjacency list of vertex "+ v);
            System.out.print("head");
            for(Integer pCrawl: graph.adjListArray[v]){
                System.out.print(" -> "+pCrawl);
            }
            System.out.println("\n");
        }
    }*/
}
public class LongestPathDAG {

    static Stack<Integer> stack;
    static int vertices;
    static Graph graph;
    static boolean[] seen;
    static int[] finish;
    static int[] dist;
    public static void main(String args[]){
        LongestPathDAG lp = new LongestPathDAG();
        lp.initialize();
        lp.topologicalOrder();
        //System.out.print("hi");
    }

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
       // graph.printGraph(graph);
        for(int i = 0;i<vertices;i++){
            seen[i] = false;
            finish[i] = Integer.MAX_VALUE;
            dist[i] = 0;
        }
    }

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
            //System.out.print(stack.pop() +" ");
        }
        System.out.print(max);
    }

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
