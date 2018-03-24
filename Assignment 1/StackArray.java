/*
 * StackClass.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * This program implements the stack functionality
 *
 * @author      Sagar Kukreja (sk3126@rit.edu)
 * @author      Rohan Shiroor (rss1103@rit.edu)
 */
public class StackArray {
        int arr[];
        int size=0;

    /**
     * Constructor for Stack class
     * @param n
     */
    public StackArray(int n){
            this.arr = new int[n];
        }

    /**
     * Push the element on to the stack
     * @param ele
     */
    public void push(int ele){
            this.arr[this.size++]=ele;
        }

    /**
     * Pop the element from top of stack.
     * Reduce the size of the array after pop.
     * @return
     */
    public int pop(){
            if(this.size==0)
                return -1;
            else {
                int ele = this.arr[--this.size];
                return ele;
            }
        }

    /**
     * Check if the stack is empty.
     * @return
     */
    public boolean isempty(){
            if (this.size == 0)
                return true;
            else
                return false;
        }
}
