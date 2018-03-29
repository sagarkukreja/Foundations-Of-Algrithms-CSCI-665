/*
* ParenthesesDP.java
*
* Version:
*     $Id$
*
* Revisions:
*     $Log$
*/

import java.util.Scanner;
import java.util.StringTokenizer;

/**
* Given is an algebraic expression involving only positive integers and the operators + and -.
* Design an O(n^3) dynamic programming algorithm that determines the maximum
* possible value that can be obtained from the expression by fully parenthesizing it.
* For example, given the expression 3 + 2 - 4 - 5, the maximum possible value of the
* expression is 6. One way of achieving this value is by parenthesizing as follows: (3 + (2 - (4 - 5))).
*
* @author      Sagar Kukreja (sk3126@rit.edu)
* @author      Rohan Shiroor (rss1103@rit.edu)
*/

public class ParenthesesDP {

    static String[] operator; // container for operators in expression
    static String[] operands; // container for operands in expression
    public static void main(String args[]) {
        ParenthesesDP p = new ParenthesesDP();
        p.initialize();
        p.dynamicSolution();
    }

    public void initialize(){

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int size = new StringTokenizer(line).countTokens();
        operator = new String[size/2];
        operands = new String[(size-operator.length)];
        Scanner lineTokenizer = new Scanner(line);
        int indexOperator = 0, indexOperand = 0;
        while(lineTokenizer.hasNext()){
            String token = lineTokenizer.next();
            if(token.equals("+")||token.equals("-")){
                operator[indexOperator] = token;
                indexOperator++;
            }
            else{
                operands[indexOperand] = token;
                indexOperand++;
            }
        }
    }

    public void dynamicSolution(){
        int n = operands.length;
        int minDPArray[][] = new int[n][n];
        int maxDPArray[][] = new int[n][n];

        if(n == 1){
            System.out.println(operands[0]);
        }

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                minDPArray[i][j] = Integer.MAX_VALUE;
                maxDPArray[i][j] = 0;

                //  initializing main diagonal by respective operands
                if (i == j){
                    maxDPArray[i][j] = Integer.parseInt(operands[i]);
                    minDPArray[i][j] = Integer.parseInt(operands[i]);
                }
            }
        }
    //calculate min and max values for all combinations of parenthesizing
            for (int L = 2; L <= n; L++)
            {
                for (int i = 0; i < n - L + 1; i++)
                {
                    int j = i + L - 1;
                    for (int k = i; k < j; k++)
                    {
                        int minTmp = 0, maxTmp = 0;

                        if(operator[k].equals("+"))
                        {
                            minTmp = minDPArray[i][k] + minDPArray[k + 1][j];
                            maxTmp = maxDPArray[i][k] + maxDPArray[k + 1][j];
                        }
                        else if(operator[k].equals("-"))
                        {
                            minTmp = minDPArray[i][k] - maxDPArray[k + 1][j];
                            maxTmp = maxDPArray[i][k] - minDPArray[k + 1][j];
                        }

                        //update values
                        if (minTmp < minDPArray[i][j])
                            minDPArray[i][j] = minTmp;
                        if (maxTmp > maxDPArray[i][j])
                            maxDPArray[i][j] = maxTmp;
                    }
                }

            }
            //output
            System.out.print(maxDPArray[0][n-1]);
    }
}