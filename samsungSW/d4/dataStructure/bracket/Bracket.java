package samsungSW.d4.dataStructure.bracket;

import java.util.Stack;

public class Bracket {
    private static final char[] parenthesis = {'(','[','{','<',')',']','}','>'};
    public static int isParenthesis(String brackets){
        Stack<Character> stack = new Stack<>();
        int n = brackets.length();
        for (int i = 0; i <n ; i++) {
            char here = brackets.charAt(i);
            if (here==parenthesis[0]||here==parenthesis[1]
                    ||here==parenthesis[2]||here==parenthesis[3]){
                stack.push(here);
            }
            //닫는 괄호라면,
            else {
                if (stack.isEmpty()) return 0;
                //exception.
                if ((here==parenthesis[4]&&parenthesis[0]==stack.peek())||
                        (here==parenthesis[5]&&parenthesis[1]==stack.peek())||
                        (here==parenthesis[6]&&parenthesis[2]==stack.peek())||
                        (here==parenthesis[7]&&parenthesis[3]==stack.peek())){
                    stack.pop();
                }
                //만약에 위에 경우가 존재하지 않다면, 잘못된 괄호.
                else{
                    return 0;
                }
            }
        }
        return 1;
    }
}
