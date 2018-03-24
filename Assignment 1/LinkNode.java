/*
 * LinkNode.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * Defines an implementation of node of link list
 *
 * @author      Sagar Kukreja (sk3126@rit.edu)
 * @author      Rohan Shiroor (rss1103@rit.edu)
 */

public class LinkNode {

    double data;
    LinkNode next = null;

    public LinkNode(){
    }

    public LinkNode(double item) {
        data = item;
    }
}
