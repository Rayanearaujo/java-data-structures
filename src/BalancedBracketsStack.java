import java.util.Stack;

public class BalancedBracketsStack {
    private static char[][] TOKENS = {{'{', '}'}, {'[', ']'}, {'(', ')'}};

    private static Boolean isOpenBracket(char c){
        for(char[] element : TOKENS){
            if(c == element[0]){
                return true;
            }
        }

        return false;
    }

    private static Boolean matches(char openBracket, char closeBracket){
        for(char[] element : TOKENS){
            if(element[0] == openBracket){
                return (element[1] == closeBracket);
            }
        }

        return false;
    }

    private static Boolean isBalanced(String expression){
        Stack<Character> stack = new Stack<>();

        for(char c : expression.toCharArray()){
            if(isOpenBracket(c)){
                stack.push(c);
            }else{
                if(stack.isEmpty() || !matches(stack.pop(), c)){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args){
        String expression = "{([(({}))])}";
        System.out.println(isBalanced(expression));
    }
}
