/*
 * SortCards.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
import java.util.Scanner;

/**
 * Consider the following problem related to ordering playing cards in your hand. You hold
 * n cards in your hand (you have a big hand; n can be an arbitrarily large number). The n
 * cards have distinct integer values in the range 1 2 : : :  n. The cards have been shuffled, so
 * you initially hold them in a random ordering.
 * Design an O(n^2) algorithm that determines the minimum number of cards that need
 * to be moved in order to get the whole set of n cards in sorted, ascending order. One move
 * corresponds to extracting one card from its current location, and moving it to a different
 * location. In the process, the remaining cards shift as necessary in your hand to make room
 * (this shifting does not count as any movement).
 * For example, if you hold the cards 2 3 1 5 4 in your hand, it will take 2 moves to
 * arrange the cards in sorted, ascending order. The 1 card can be moved to the leftmost
 * position. The 4 card can be moved left of the 5.
 *
 * @author      Sagar Kukreja (sk3126@rit.edu)
 * @author      Rohan Shiroor (rss1103@rit.edu)
 */

public class SortCards {

    static int n;
    static int[] input ;
    static int[] solutionArray;

    public static void main(String args[]){
        SortCards sc = new SortCards();
        sc.initialize();
        sc.sortCard();
    }

    public void initialize(){
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        solutionArray = new int[n+1];
        input = new int[n];
        for(int i = 0;i<n;i++){
            input[i] = s.nextInt();
        }
    }

    /*We compute the longest increasing sequence in the deck of cards . Number of cards hat rae out of place or needs
   * to be moved in order will be the ones that are not part of sequence. Hence n - longest sequence is the answer.
   */
    public void sortCard(){
        solutionArray[0] = 0;
        int max = solutionArray[0];
        for (int i = 1;i<=n;i++){
            solutionArray[i] = 1;
            for(int j = 1 ;j<=i-1;j++){
                if(input[j-1]<input[i-1] && solutionArray[i]<solutionArray[j]+1){
                    solutionArray[i] =solutionArray[j]+1;
                    if(solutionArray[i]>max){
                        max = solutionArray[i];
                    }
                }
            }
        }System.out.println(n-max);//number of cards to be moved
    }
}