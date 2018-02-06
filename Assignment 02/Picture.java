import java.util.Scanner;

public class Picture {

    class Person {
        int age;
        double height;

        Person(int age, double height) {
            this.age = age;
            this.height = height;
        }
    }

    static int n;
    static Person[] persons;
    static int swapCount;

    public static void main(String args[]) {
        Picture p = new Picture();
        p.initialize();
        //persons = new Person[n];
        Person[] temp = new Person[n];
        swapCount = p.sortAndCount(persons, temp, 0, n - 1);
        swapCount += p.sortAndCountHeight(persons, temp, 0, (n / 2) - 1);
        swapCount += p.sortAndCountHeight(persons, temp, (n / 2) + 1, n - 1);
        System.out.println(swapCount);
    }

    public void initialize() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        persons = new Person[n];
        for (int i = 0; i < n; i++) {
            persons[i] = new Person(sc.nextInt(), sc.nextDouble());
        }
    }

    public int sortAndCount(Person[] persons, Person[] temp, int left, int right) {
        int count = 0;
        if (left < right) {

            int mid = (left + right) / 2;
            count = sortAndCount(persons, temp, left, mid);
            count += sortAndCount(persons, temp, mid + 1, right);
            count += mergeAndCount(persons, temp, left, mid + 1, right);

        }
        return count;
    }

    int mergeAndCount(Person[] persons, Person[] temp, int left, int mid, int right) {

        int i = left;
        int j = mid;
        int k = left;
        int count = 0;

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

    public int sortAndCountHeight(Person[] persons, Person[] temp, int left, int right) {
        int count = 0;
        if (left < right) {

            int mid = (left + right) / 2;
            count = sortAndCountHeight(persons, temp, left, mid);
            count += sortAndCountHeight(persons, temp, mid + 1, right);
            count += mergeAndCountHeight(persons, temp, left, mid + 1, right);

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