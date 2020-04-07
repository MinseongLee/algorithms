package strategiesOfAlgorithms.dataStructure.queueAndStack.brackets;

import java.util.Stack;

public class Brackets {
    private static final String YES = "YES";
    private static final String NO = "NO"; //"({["
    private static final char[] OPEN = {'(','{','['};
//    private static final char[] CLOSE = {')','}',']'};
    public static void main(String[] args) {
//        String bracket = "({[}])";
        String bracket = "({}[(){}])";
        System.out.println(rightBrackets(bracket));
    }
    public static String rightBrackets(String bracket){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < bracket.length(); i++) {
            //이렇게 refactoring 하는 것이 좋다고 생각.
            if (findOpen(bracket.charAt(i))){
                stack.push(bracket.charAt(i));
            }

            /*if (bracket.charAt(i)=='(' || bracket.charAt(i)=='{' ||
            bracket.charAt(i)=='['){
                stack.push(bracket.charAt(i));
            }*/
            //pop
            else {
                //아래 있는 내용을 이렇게 refactoring
                if (findClose(bracket.charAt(i),stack.peek())){
                    stack.pop();
                }else{
                    return NO;
                }
                /*char top = stack.peek();
                if ((bracket.charAt(i)==')' && top=='(') ||
                        (bracket.charAt(i)=='}' && top=='{')||
                        (bracket.charAt(i)==']'&&top=='[')){
                    stack.pop();
                }else {
                    return NO;
                }*/
            }
        }
        if (stack.isEmpty()) return YES;
        return NO;
    }

    private static boolean findClose(char close, char top) {
        if ((close==')' && top=='(') ||
                (close=='}' && top=='{')||
                (close==']'&&top=='[')){
            return true;
        }
        return false;
    }

    private static boolean findOpen(char open) {
        for (int i = 0; i < OPEN.length; i++) {
            if (OPEN[i]==open) return true;
        }
        return false;
    }
}
