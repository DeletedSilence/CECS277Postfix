import java.util.Stack;
public class PostFixEvaluator {
	/**
	 * private attributes
	 */
	private String postfix;
	private Stack<Double> calculate = new Stack<Double>();
	 /**
	  * default constructor
	  */
	public PostFixEvaluator(){
		postfix = "";
	}
	/**
	 * Overloaded constructor
	 * @param post  string variable to hold an expression 
	 */
	public PostFixEvaluator(String post){
		postfix = post;
	}
	
	/**
	 * setter for the postfix attribute
	 * @param ex  variable to hold an expression
	 */
	public void setPostfix(String ex){
		postfix = ex;
	}
	/**
	 * evaluates a postfix expression 
	 * @return the calculated result from evaluating the postfix expression
	 */
	public double evaluatePostfixExpression(){
		String whitespace = "[ ]+"; 
		String[] splitPostfix = postfix.split(whitespace);
		double op1;
		double op2;
		for(int i=0;i<splitPostfix.length;i++){
			if (splitPostfix[i].matches("[0123456789]+")){
				calculate.push(Double.parseDouble(splitPostfix[i]));
			}
			else if(splitPostfix[i].matches("[()^+*-/]")){
				op2 = calculate.pop();
				op1 = calculate.pop();
				calculate.push(Calculate(op1,op2,splitPostfix[i]));
			}
		}
		return calculate.pop();
	}
	
	/**
	 * calculates a single operation
	 * @param op1 operand
	 * @param op2 operand
	 * @param operation operation
	 * @return the calculated result from performing the operation
	 */
	public double Calculate(double op1, double op2, String operation){
		double answer=0;
		switch(operation){
		case "+":
			answer = op1+op2;
			break;
		case "-":
			answer = op1-op2;
			break;
		case "*":
			answer = op1*op2;
			break;
		case "/":
			answer = op1/op2;
			break;
		case "^":
			answer = Math.pow(op1, op2);
			break;
		case "%":
			answer = op1%op2;
			break;
			
		}
		return answer;
	}

}
