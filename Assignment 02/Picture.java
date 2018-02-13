/*
 * Picture.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
import java.util.Scanner;

/**
 * This program finds the number of swaps required to arrange
 * the students and teacher for a photograph.
 *
 * @author      Sagar Kukreja (sk3126@rit.edu)
 * @author      Rohan Shiroor (rss1103@rit.edu)
 */

public class Picture {

    /*We create a Person class object for each input we receive
    * to store the age and height of a person
    * */
    class Person {
        int age;
        double height;

        Person(int age, double height) {
            this.age = age;
            this.height = height;
        }
    }

    static int n; // number of person in picture
    static Person[] persons;
    static int swapCount; // number of swaps required

    public static void main(String args[]) {
        Picture p = new Picture();
        p.initialize();
        Person[] temp = new Person[n];

        //sorting based on age of persons. Takes O(n log n) and returns the swap.
        swapCount = p.sortAndCount(persons, temp, 0, n - 1);

        //sorts 7 years old only in ascending order based on height and returns the count of swaps. Takes O(n log n)
        swapCount += p.sortAndCountHeight(persons, temp, 0, (n / 2) - 1);

        //sorts 8 years old only in descending order based on height and returns the count of swaps. Takes O(n log n)
        swapCount += p.sortAndCountHeight(persons, temp, (n / 2) + 1, n - 1);
        System.out.println(swapCount);
    }

    /*Take input from the command line and initialize each input as a person object */
    public void initialize() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        persons = new Person[n];
        for (int i = 0; i < n; i++) {
            persons[i] = new Person(sc.nextInt(), sc.nextDouble());
        }
    }

    /*This function basically performs merge sort on all the persons based on their age.
     * After this function, we have all 7 years on one side of teacher and all 8 years on
      * other side of teacher with teacher in middle. It takes O(n log n) and returns the
      * swaps required to sort and merge the array. */

    public int sortAndCount(Person[] persons, Person[] temp, int left, int right) {
        int count = 0;
        if (left < right) {

            int mid = (left + right) / 2;
            count = sortAndCount(persons, temp, left, mid);                     //sort one half
            count += sortAndCount(persons, temp, mid + 1, right);          //sort other half
            count += mergeAndCount(persons, temp, left, mid + 1, right);  //merge both halves

        }
        return count;
    }

    int mergeAndCount(Person[] persons, Person[] temp, int left, int mid, int right) {

        int i = left;
        int j = mid;
        int k = left;
        int count = 0; // variable that count swaps required to sort the array

        while (i <= mid - 1 && j <= right) {
            if (persons[i].age > 8 || persons[j].age > 8) {
                if (persons[i].age > 8) {

                    if (persons[j].age < 8) {
                        temp[k++] = persons[j++];
                        count = count + (mid - i);
                    } else if (persons[j].age == 8) {
                        temp[k++] = persons[i++];
                    }
                } else if (persons[i].age <= 8) {
                    if (persons[i].age < 8) {
                        temp[k++] = persons[i++];
                    } else {
                        temp[k++] = persons[j++];
                        count = count + (mid - i);
                    }
                }
            } else {

                if (persons[i].age <= persons[j].age) {
                    temp[k++] = persons[i++];
                } else {
                    temp[k++] = persons[j++];
                    count = count + (mid - i);
                }
            }
        }
        while (i <= mid - 1) {
            temp[k++] = persons[i++];
        }
        while (j <= right) {
            temp[k++] = persons[j++];
        }
        for (int m = left; m <= right; m++) {
            persons[m] = temp[m];
        }
        return count;
    }

    /*After the input is sorted based on age , we now divide the input into two arrays, 1)The array with 7 years old,
    * 2) the array with 8 years old and apply merge sort on them independently to arrange 7 years in ascending and
    * 8 years in descending order based on their heights ,around the teacher. It also takes O(n log n)and returns
     * number of swaps required to do so. */

    public int sortAndCountHeight(Person[] persons, Person[] temp, int left, int right) {
        int count = 0;
        if (left < right) {

            int mid = (left + right) / 2;
            count = sortAndCountHeight(persons, temp, left, mid);                   //sort one half
            count += sortAndCountHeight(persons, temp, mid + 1, right);         //sort other half
            count += mergeAndCountHeight(persons, temp, left, mid + 1, right);  //merge both halves

        }
        return count;
    }



    int mergeAndCountHeight(Person[] persons, Person[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid;
        int k = left;
        int count = 0;

        while (i <= mid - 1 && j <= right) {
            if (persons[i].age == 7) {
                if (persons[i].height < persons[j].height) {
                    temp[k++] = persons[i++];
                } else {
                    temp[k++] = persons[j++];
                    count = count + (mid - i);
                }
            } else {
                if (persons[i].height > persons[j].height) {
                    temp[k++] = persons[i++];
                } else {
                    temp[k++] = persons[j++];
                    count = count + (mid - i);
                }
            }
        }
        while (i <= mid - 1) {
            temp[k++] = persons[i++];
        }
        while (j <= right) {
            temp[k++] = persons[j++];
        }
        for (int m = left; m <= right; m++) {
            persons[m] = temp[m];
        }
        return count;
    }

}