/*
 * LinkedList.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * Defines a linkList implementation
 *
 * @author      Sagar Kukreja (sk3126@rit.edu)
 * @author      Rohan Shiroor (rss1103@rit.edu)
 */

public class LinkedList {

    public LinkNode head;
    public LinkNode tail;
    public int listCount;
    LinkNode sortedList ; //points to head of sortedList

    public LinkedList(){
        this.head = new LinkNode();
        this.tail = head;
        this.listCount = 0;
        this.sortedList = new LinkNode();
    }

    public boolean addNode(double d){
        LinkNode newNode = new LinkNode(d);
        LinkNode temp = this.head;
        if(temp.next == null) {
            temp.next = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }this.listCount++;
        return true;
    }

    public void sort(){

        LinkNode curr = this.head, next,pre;

        while(curr != null){
            next = curr.next;
            pre = this.sortedList;
            while(pre.next != null && pre.next.data <= curr.data){
                pre = pre.next;
            }
            curr.next = pre.next;
            pre.next = curr;

            curr = next;
        }
        pre = this.sortedList;
            while(pre.next!=null){
            pre = pre.next;
        }
        this.tail = pre;
    }
}
