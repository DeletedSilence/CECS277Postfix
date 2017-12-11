import java.util.Stack;
public class InfixToPostfixConverter {
	/**
	 * private attributes
	 */
	private String expression;
	private Stack<String> stack = new Stack<String>();
	
	/**
	 * default constructor
	 */
	public InfixToPostfixConverter(){
		expression = "";
	}
	/**
	 * overloaded constructor
	 * @param ex variable to hold an expression
	 */
	public InfixToPostfixConverter(String ex){
		expression = expand(ex);

	}
	/**
	 * setter for the expression attribute
	 * @param ex variable to hold an expression
	 */
	public void setExpression(String ex){
		expression = expand(ex);
	}
	/**
	 * converts the expression attribute into postfix
	 * @return the converted postfix expression
	 */
	public String infixToPostfix() {
        String postfixString = "";
        String whitespace = "[ ]+"; 
		String[] splitPost = expression.split(whitespace);

		for(int i = 0; i<splitPost.length;i++){
			if(splitPost[i].matches("[0123456789]+")){
				postfixString+=splitPost[i]+" ";
			}
			else if(splitPost[i].equals("(")){
				stack.push(splitPost[i]);
			}
			else if(splitPost[i].equals("*")||splitPost[i].equals("/")||splitPost[i].equals("+")||splitPost[i].equals("-")||splitPost[i].equals("^")||splitPost[i].equals("%")){
				while((!stack.isEmpty())&&(!stack.peek().equals("("))){
					if(orderOfPrecedence(stack.peek(),splitPost[i])){
						postfixString+=stack.pop()+" ";
					}
					else{
						break;
					}
				}
				stack.push(splitPost[i]);
			}
			else if(splitPost[i].equals(")")){
				while((!stack.isEmpty())&&(!stack.peek().equals("("))){
					postfixString+=stack.pop()+" ";
				}
				if(!stack.isEmpty()){
					stack.pop();
				}
			}
		}
		while(!stack.isEmpty()){
			postfixString+=stack.pop()+" ";
		}
		return postfixString;
        
    }
	/**
	 * puts a space between every operation
	 * @param eq expression needed to expand
	 * @return the expanded expression
	 */
	public String expand(String eq){
		eq = eq.replaceAll("\\+", " + "); 
		eq = eq.replaceAll("\\-", " - ");
		eq = eq.replaceAll("\\/", " / ");
		eq = eq.replaceAll("\\*", " * ");
		eq = eq.replaceAll("\\^", " ^ ");
		eq = eq.replaceAll("\\%", " % ");
		eq = eq.replaceAll("\\)", " ) ");
		eq = eq.replaceAll("\\(", " ( ");
		return eq;
	}

	/**
	 * determines which operations have a higher precedence
	 * @param OpCurr first operation
	 * @param OpPrev second operation
	 * @return true if OpCurr has a higher precedence than OpPrev
	 */
	public boolean orderOfPrecedence(String OpCurr, String OpPrev) {
		int CurrValue = 0, PrevValue = 0;

		if (OpCurr.equals("+") || OpCurr.equals("-")) {
			CurrValue = 1;
		} else if (OpCurr.equals("/") || OpCurr.equals("*")||OpCurr.equals("%")) {
			CurrValue = 2;
		} else if (OpCurr.equals("^")) {
			CurrValue = 3;
		}

		if (OpPrev.equals("+") || OpPrev.equals("-")) {
			PrevValue = 1;
		} else if (OpPrev.equals("/") || OpPrev.equals("*")||OpCurr.equals("%")) {
			PrevValue = 2;
		} else if (OpPrev.equals("^")) {
			PrevValue = 3;
		}
		
		if(CurrValue == PrevValue)
			return true;

		return CurrValue > PrevValue;
	}

}
