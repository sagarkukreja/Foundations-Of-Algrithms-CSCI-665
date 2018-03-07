/*
 * Jobs.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
import java.util.Scanner;

/**
 * This program finds there is a number in an array that occurs
 *  n/2 and n/3 times respectively in O(n) time complexity.
 *
 * @author      Sagar Kukreja (sk3126@rit.edu)
 * @author      Rohan Shiroor (rss1103@rit.edu)
 */

class Interval {

        int startTime;
        int finishTime;
        int employer;

        public Interval(int startTime, int finishTime, int employer) {
            this.startTime = startTime;
            this.finishTime = finishTime;
            this.employer = employer;
        }
    }

    class Node {
        Interval data;
        Node next;

        public Node(Interval data){
            this.data = data;
            this.next = null;
        }
    }

    class LinkList {
    Node head;
    Node tail;

    LinkList() {
        this.head = null;
        this.tail = head;
    }

    public void add(Interval item){
        Node n = new Node(item);
        Node temp = this.head;
        if(temp == null){
            head = n;
            //temp = temp.next;
            this.tail = n;
        }
        else{
            this.tail.next = n;
            tail = tail.next;
        }
    }
    }

 public class Jobs {

    static int countEmpZero = 0;
    static int countEmpOne = 0;
    static Interval[] intervalEmpZero ;
    static Interval[] intervalEmpOne;

    public static void main(String args[]) {
        Jobs j = new Jobs();
        Interval[] intervals, intervalsEmpZero, intervalsEmpOne;
        intervals = j.initialize();
        intervalEmpZero = new Interval[countEmpZero];
        intervalEmpOne = new Interval[countEmpOne];
        j.seperateIntervals(intervals);  //make separate array for jobs from employer 1 and employer 0
        j.sortIntervals(intervalEmpZero,0,countEmpZero-1); //sort the individual arrays
        j.sortIntervals(intervalEmpOne,0,countEmpOne-1);  //sort the individual arrays
        int resultCount = j.assignJobs();
        System.out.println(resultCount);
    }

    //initialize and count number of jobs from employer 1 and employer 0
    public Interval[] initialize(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Interval[] interval = new Interval[n];
        int startTime, finishTime, employer;
        for ( int i = 0; i<n ;i++){
            startTime = sc.nextInt();
            finishTime = sc.nextInt();
            employer = sc.nextInt();
            if (employer == 0)
                    countEmpZero++;
            else
                countEmpOne++;
            interval[i]= new Interval(startTime, finishTime, employer);
        }
        return interval;
    }

    //make separate array for jobs from employer 1 and employer 0
    public void seperateIntervals(Interval intervals[]){
        int pointerForEmpZero = 0, pointerForEmpOne = 0;
        for (int i = 0;i<intervals.length;i++){
            if(intervals[i].employer == 0){
                intervalEmpZero[pointerForEmpZero] = intervals[i];
                pointerForEmpZero++;
            }
            else{
                intervalEmpOne[pointerForEmpOne] = intervals[i];
                pointerForEmpOne++;
            }
        }
    }

    //sort the individual arrays
    public void sortIntervals(Interval[] intervals,int l , int r){
        if(l<r){
            int middle = (l+r)/2;

            sortIntervals(intervals, l, middle);
            sortIntervals(intervals,middle+1,r);
            mergeIntervals(intervals, l , middle, r);
        }
    }
    //merge sort
    public void mergeIntervals(Interval[] intervals, int l , int m , int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        Interval[] leftTemp = new Interval[n1];
        Interval[] rightTemp = new Interval[n2];

        for (int i = 0; i < n1; i++) {
            leftTemp[i] = intervals[i + l];
        }
        for (int i = 0; i < n2; i++) {
            rightTemp[i] = intervals[m + 1 + i];
        }
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (leftTemp[i].finishTime < rightTemp[j].finishTime) {
                intervals[k] = leftTemp[i];
                i++;
            } else {
                intervals[k] = rightTemp[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            intervals[k] = leftTemp[i];
            i++;
            k++;
        }
        while (j < n2) {
            intervals[k] = rightTemp[j];
            k++;
            j++;
        }
    }

    //assign jobs
    public int assignJobs(){
        int i = 0, j = 0;
        LinkList result = new LinkList();
        int currentFinishTime = 0;
        int lastEmployerAdded = -1;
        int resultCount = 0;

        while(i < intervalEmpZero.length || j < intervalEmpOne.length){

            //for the first job select the one with the least finish time for first job
            if(intervalEmpZero[0].finishTime > intervalEmpOne[0].finishTime && lastEmployerAdded == -1){
                result.add(intervalEmpOne[0]);
                resultCount++;
                currentFinishTime = intervalEmpOne[0].finishTime;
                lastEmployerAdded = intervalEmpOne[0].employer;
                j++;
            }

            //check if the new job has start time greater than finish time of the job that we added last and both jobs come from different employers

            if(i < intervalEmpZero.length && intervalEmpZero[i].startTime >= currentFinishTime && intervalEmpZero[i].employer != lastEmployerAdded ){
                result.add(intervalEmpZero[i]);
                resultCount++;
                currentFinishTime = intervalEmpZero[i].finishTime;
                lastEmployerAdded = intervalEmpZero[i].employer;
            }

            if(j < intervalEmpOne.length && intervalEmpOne[j].startTime >= currentFinishTime && intervalEmpOne[j].employer != lastEmployerAdded){
                result.add(intervalEmpOne[j]);
                resultCount++;
                currentFinishTime = intervalEmpOne[j].finishTime;
                lastEmployerAdded = intervalEmpOne[j].employer;

            }
            i++;
            j++;

        }return resultCount;
    }
}