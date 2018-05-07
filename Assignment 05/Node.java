/*
 * Node.java
 * Version: 1
 * Revisions: 1
 */
/**
 * Node in Union-Find algorithm.
 *
 * @author: Rohan Shiroor (rss1103)
 * @author: Sagar Kukreja (sk3126)
 */
public class Node {
    public int boss;
    public int size;
    public int[] V;
    public Node(int n){
        this.boss = -1;
        this.size = -1;
        this.V = new int[n];
        for(int i=0;i<n;i++){
            V[i] = -1;
        }
    }
}
