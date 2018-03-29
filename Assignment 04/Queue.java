/*
 * Queue.java
 * Version: 1
 * Revisions: 3
 * Reference: Geeks for Geeks https://www.geeksforgeeks.org/queue-set-1introduction-and-array-implementation/
 */
/**
 * Queue data structure using arrays.
 *
 * @author: Rohan Shiroor (rss1103)
 * @author: Sagar Kukreja (sk3126)
 */

public class Queue {
    int front,back,size, capacity;
    Node[] queue;

    /**
     * Constructor for the class.
     * @param capacity
     */
    public Queue(int capacity){
        this.capacity = capacity;
        this.front = 0;
        this.size = 0;
        this.back = this.capacity - 1;
        queue = new Node[this.capacity];
    }

    /**
     * Check if the Queue is empty if size is 0.
     * @param q
     * @return 1 if empty 0 if not
     */
    public int isEmpty(Queue q){
        if(q.size==0)
            return 1;
        else
            return 0;
    }

    /**
     * Remove from Queue. Changes front and the size.
     * @return: Item dequeued from the Queue.
     */
    public Node Dequeue(){
        Node item = new Node();
        if (isEmpty(this)==1) {
            item.x = -1;
            item.y = -1;
            item.dist = -1;
            return item;
        }
        item = this.queue[this.front];
        //this.front = this.queue[this.front];
        this.front = (this.front + 1)%capacity;
        this.size = this.size - 1;
        return item;
    }

    /**
     * Check if Queue is Full if size = capacity.
     * @param q
     * @return 1 if full and 0 if not.
     */
    public int isFull(Queue q){
        if(q.size == q.capacity)
            return 1;
        else
            return 0;
    }

    /**
     * Add element to Queue. Changes rear and size.
     * @param item
     */
    public void Enqueue(Node item){
        if (isFull(this)==1)
            return;
        this.back = (this.back+1)%this.capacity;
        this.queue[this.back] = item;
        this.size +=1;
    }
}
