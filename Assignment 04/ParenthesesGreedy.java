/*
 * ParenthesesGreedy.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
import java.util.Scanner;
import java.util.Stack;

/**
* Given is an algebraic expression involving only positive integers and the operators +
* and *. Design a greedy O(n) algorithm that determines the maximum possible value
* that can be obtained from the expression by fully parenthesizing it.
* For example, given the expression 3 * 2 + 4 * 5, the maximum possible value of the
* expression is 90. One way of achieving this value is by parenthesizing as follows: ((3 * (2 + 4)) * 5).
*
* @author      Sagar Kukreja (sk3126@rit.edu)
* @author      Rohan Shiroor (rss1103@rit.edu)
*/

public class ParenthesesGreedy {

    static Stack<String>  expression = new Stack<>();

    public static void main(String args[]){
        ParenthesesGreedy p = new ParenthesesGreedy();
        p.initialize();
        p.solution();
    }
//take input and calculate operands with + operation
    public void initialize(){

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        Scanner lineTokenizer = new Scanner(line);
        while(lineTokenizer.hasNext()){
            String token = lineTokenizer.next();
            //expression.push(lineTokenizer.next());
            if(token.equals("+")){
                String op1 = expression.pop();
                String op2 = lineTokenizer.next();
                int result = Integer.parseInt(op1) + Integer.parseInt(op2);
                token = Integer.toString(result);
            }
            expression.push(token);
        }
    }
    //evaluate the expression
    public void solution(){
        int result = 0;
        while(!(expression.size()==1)){
            String op1 = expression.pop();
            expression.pop(); // to pop the operator
            String op2 = expression.pop();
            result = Integer.parseInt(op1)*Integer.parseInt(op2);
            expression.push(Integer.toString(result));
        }
        System.out.print(expression.peek());
    }
}
