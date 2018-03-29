/*
 * PathInMaze.java
 * Version: 1
 * Revisions: 2
 */
import java.util.Scanner;
/**
 * Algorithm that finds the length of the shortest
 * path from position 2 to position 3.
 *
 * @author: Rohan Shiroor (rss1103)
 * @author: Sagar Kukreja (sk3126)
 */
public class PathInMaze {
    public static Scanner sc = new Scanner(System.in);
    int[][] Maze;
    Node src,dest;
    /**
     *  Constructor for the class
     * @param m
     * @param n
     */
    public PathInMaze(int m,int n){
        this.Maze = new int[m][n];
        this.src = new Node();
        this.dest = new Node();
    }

    /**
     * Take the input maze from the console
     * and store in an array.
     * @param m
     * @param n
     */
    public void Input(int m,int n){
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                this.Maze[i][j] = sc.nextInt();
            }
        }
    }

    /**
     * Find the source and destination row and column locations
     * of the Maze.
     * @param m
     * @param n
     */
    public void SrcDest(int m,int n){
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(this.Maze[i][j]==2){
                    this.src.x = i;
                    this.src.y = j;
                    this.src.dist = 0;
                }
                else if(this.Maze[i][j]==3){
                    this.dest.x = i;
                    this.dest.y = j;
                }
            }
        }
    }

    /**
     * Check if the current row and columns are greater than 0
     * and less than the matrix length.
     * @param row
     * @param col
     * @param m
     * @param n
     * @return True if row and column valid else false
     */
    public boolean isValid(int row,int col,int m,int n){
        if(row>=0 && row< m && col>=0 && col<n)
            return true;
        else
            return false;
    }

    /**
     * BFS to find the shortest path from source to destination.
     * @param m
     * @param n
     * @return
     */
    public int BFS(int m,int n){
        int [][] seen = new int[m][n];
        // Mark source as visited.
        seen[src.x][src.y] = 1;
        // To find the rows and columns of neighbor.
        // It indicates left,bottom, top and right neighbors.
        int[] Xn = new int[]{-1,0,0,1};
        int[] Yn = new int[]{0,-1,1,0};
        // Create a new Queue.
        Queue queue = new Queue(m+n);
        // Add source to Queue.
        queue.Enqueue(src);
        // Until queue is not empty dequeue element and find neighbors
        // Enqueue the neighbors.
        while(queue.isEmpty(queue)!=1){
            // Dequeue the front of queue.
            Node curr = queue.Dequeue();
            // Check if destination
            if (curr.x == dest.x && curr.y==dest.y)
                return curr.dist;

            for(int i=0;i<4;i++){
                // Get neighbors (4 way).
                int row = curr.x+Xn[i];
                int col = curr.y+Yn[i];
                // For each neighbor check if valid and not visited and not a obstacle.
                if(this.isValid(row,col,m,n) && Maze[row][col]!=1 && seen[row][col]!=1){
                    seen[row][col]= 1;
                    // create adjacent node
                    Node Adjacent = new Node();
                    Adjacent.x = row;
                    Adjacent.y = col;
                    // Distance is current + 1.
                    Adjacent.dist = curr.dist+1;
                    // Enqueue.
                    queue.Enqueue(Adjacent);
                }
            }
        }
        return -1;
    }

    /**
     * Main function of the program.
     * @param args
     */
    public static void main(String[] args){
        int m = sc.nextInt();
        int n = sc.nextInt();
        PathInMaze pth = new PathInMaze(m,n);
        pth.Input(m,n);
        pth.SrcDest(m,n);
        int dist = pth.BFS(m,n);
        System.out.println(dist);
    }
}
