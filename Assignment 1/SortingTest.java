/*
 * SortingTest.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

import java.util.Random;


/**
 * This program compares merge sort , bucket sort
 * and insertion sort on different inputs and size of inputs.
 *
 * @author      Sagar Kukreja (sk3126@rit.edu)
 * @author      Rohan Shiroor (rss1103@rit.edu)
 */

public class SortingTest {

    public double[] inputUniform ; //array for uniform input
    public double[] inputGaussian; //array for gaussian input
    public int size ;
    public static final double MEAN = 0.5;
    public static final double VARIANCE = 0.0001;

    public SortingTest(int size){
        this.size = size;
        this.inputUniform = new double[size];
        this.inputGaussian = new double[size];
    }

    public static void main(String args[]) {

        //change here to set different size
        SortingTest s = new SortingTest(100); //default size 100

        //to initialize uniform and gaussian input
        s.initialize(s.size);


        System.out.println("Running for Gaussian input");

        //copy of input array
        double[] inputArray = s.copyArray(s.inputGaussian);

        long startTimeInsertion = System.nanoTime();

        //calling insertion sort
        double[] resultInsertion = s.insertionSort(inputArray);
        long stopTimeInsertion = System.nanoTime();
        long timeTakenInsertion = stopTimeInsertion - startTimeInsertion;

        System.out.println(timeTakenInsertion + "  ns for insertion sort with size " + s.size);
        //System.out.println("------------------------------------insertion sort------------------------");
        //s.printResult(s.inputUniform);
        //s.printResult(resultInsertion);

        //System.out.println("-------------bucket sort starts---------");

        //calling bucket sort
        long startTimeBucket = System.nanoTime();
        double[] resultBucket = s.bucketSort(s.inputGaussian);
        long stopTimeBucket= System.nanoTime();
        long timeTakenBucket = stopTimeBucket - startTimeBucket;
        System.out.println(timeTakenBucket+"  ns for Bucket sort with size " + s.size);
        //s.printResult(resultBucket);
        //System.out.println("-------------input after sort--------");
       // s.printResult(s.inputUniform);


        double[] resultMerge = s.copyArray(s.inputGaussian);
        long startTimeMerge = System.nanoTime();

        //calling merge sort
        s.mergeSort(resultMerge,0,s.size-1);
        long stopTimeMerge= System.nanoTime();
        long timeTakenMerge = stopTimeMerge - startTimeMerge;
        System.out.println(timeTakenMerge+"  ns for merge sort with size " + s.size);
        //s.printResult(resultMerge);
        //System.out.println("-------------input after sort--------");
        //s.printResult(s.inputUniform);
    }

    /*function to create copy of input array*/
    public double[] copyArray(double[] input){
        double[] newArray = new double[input.length];
        for(int i = 0;i<input.length;i++){
            newArray[i] = input[i];
        }
        return newArray;
    }

    /*function to initialize gaussian and uniform input */
    public void initialize(int size){
        Random randomGenerator = new Random();
        Random gaussianRandom = new Random();
        for(int i = 0;i<size;i++){
            this.inputUniform[i] = randomGenerator.nextDouble();
            this.inputGaussian[i] = MEAN + gaussianRandom.nextGaussian() * VARIANCE;

            //System.out.println(this.inputUniform[i]);

        }
    }

    /*function to print result*/
    public void printResult(double[] inputArray){
        for ( int i = 0; i<inputArray.length;i++){
            System.out.println(inputArray[i]);
        }
    }

    /*function for insertion sort*/
    public double[] insertionSort(double[] input){
        int n = this.size;
        for (int i = 1; i<n;i++){
            int j = i;
            while(j > 0 && input[j-1] > input[j]){
                double temp = input[j-1];
                input[j-1] = input[j];
                input[j] = temp;
                j = j - 1;
            }
        }
        return input;
    }


    /*fucntion for bucket sort*/

    public double[] bucketSort(double[] input){
        int n = this.size;

        double[] result = new double[n];

        //initialize array of link list O(n)
        LinkedList[] listArray = new LinkedList[n];
        for(int i = 0;i<n;i++){
            listArray[i] = new LinkedList();
        }

        //put the input into respective buckets O(n)
        for(int i = 0 ;i<n;i++){
            int bucketNumber = (int)Math.floor(n*input[i]);
                listArray[bucketNumber].addNode(input[i]);
        }

        //sorting the link list
        for(int i = 0;i < n; i++){
            if(listArray[i].head.next != null) {
                listArray[i].sort();
            }
        }

        //int count = 0;
        /*for(int i = 0;i < n;i++){
            if(listArray[i].head.next != null) {
            LinkNode temp = listArray[i].head.next;
            while(temp != null){
                    result[count++] = temp.data;
                    temp = temp.next;
                    //System.out.println(result[count-1]);
            }
        }
        }*/

        //combining all the buckets
        LinkedList finalList = new LinkedList();
        int count = 0;
        for(int i = 0; i<n ;i++){

            if(listArray[i].head.next != null){
                if(count == 0){
                    finalList.head.next = listArray[i].head.next;
                    finalList.tail = listArray[i].tail;
                    count = 1;
                }
                else{
                    finalList.tail.next = listArray[i].head.next;
                    finalList.tail = listArray[i].tail;
                }

        }}

        //copying buckets to array and returning the array
        LinkNode temp = finalList.head.next;
        int p = 0;
        double[] arrayResult = new double[this.size];
        while(temp != null){
            //System.out.println(temp.data);
            arrayResult[p] = temp.data;
            temp = temp.next;
            p++;
        }
        return arrayResult;

    }

    /*performing merge sort*/
    public void mergeSort(double[] arr,int leftPointer , int rightPointer){

        if(leftPointer < rightPointer){
            int middle = (leftPointer+rightPointer)/2;
            mergeSort(arr,leftPointer,middle);
            mergeSort(arr,middle+1,rightPointer);
            merge(arr,leftPointer,middle,rightPointer);
        }

    }

    /*combining two arrays in sorted order called from fucntion mergeSort*/
    public void merge(double[] arr, int left,int middle, int right){

        //find sizes of two subarrays to be merged
        int n1 = middle-left+1;
        int n2 = right - middle;

        //create two temp arrays
        double leftArray[] = new double[n1+1];
        double rightArray[] = new double[n2+1];

        for(int i = 0;i<n1;i++){
            leftArray[i] = arr[left+i];
        }
        for(int j = 0;j<n2;j++){
            rightArray[j] = arr[middle+1+j];
        }

        //merge left and right arrays
        //initial index of subarrays i = 0 , j = 0
        int i = 0, j=0;
        int k = left;//index of merged subarray
        while(i<n1 && j<n2){
            if(leftArray[i]<rightArray[j]){
                arr[k] = leftArray[i];
                i++;
            }
            else{
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while(i<n1){
            arr[k] = leftArray[i];
            i++;
            k++;
        }
        while(j<n2){
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
//SortingTest.java

