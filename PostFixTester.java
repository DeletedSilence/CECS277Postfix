import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Jose Ramirez
 * CECS 277
 * 12/11/17
 * Postfix Calculator
 */
public class PostFixTester {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		InfixToPostfixConverter postfix = new InfixToPostfixConverter();
		PostFixEvaluator evaluate = new PostFixEvaluator();
		double result=0;
		String express = "";//"((15 / (7-(1 + 1)))*3)-(2+(1+1))";//"15 / 7 - 1 + 1 * 3 - 2 + 1+1";
		boolean check = false;
		int choose = 0;
		do{
			choose = menu();
			switch(choose){
			case 1:
				express = expression();
				postfix.setExpression(express);
				express = postfix.infixToPostfix();
				System.out.println("Postfix: "+express);
				break;
			case 2:
				express = expression();
				postfix.setExpression(express);
				express = postfix.infixToPostfix();
				evaluate.setPostfix(express);
				result = evaluate.evaluatePostfixExpression();
				System.out.println("Equals: "+result);
				break;
			case 3:
				check = true;
				break;
			}
		}while(!check);
		System.out.println("You have exited the program");
		
		
	}
	public static void validate(String infix){
		int parenthesis = 0;
		
		for(int i = 0; i<infix.length();i++){
			if(infix.charAt(i)=='+'||infix.charAt(i)=='-'||infix.charAt(i)=='*'||infix.charAt(i)=='/'||infix.charAt(i)=='^'||infix.charAt(i)=='%'){
				if(i==0){
					throw new IllegalArgumentException();
				}
				else{
					for(int n = i-1;n>=0;n--){
						if(Character.isDigit(infix.charAt(n))||infix.charAt(n)==')'){
							break;
						}
						else if(infix.charAt(n)==' '){
							if(n==0){
								throw new IllegalArgumentException();
							}
						}
						else{
							throw new IllegalArgumentException();
						}
					}
					if(i==infix.length()-1){
						throw new IllegalArgumentException();
					}
					else{
						for(int n = i+1;n<infix.length();n++){
							if(Character.isDigit(infix.charAt(n))||infix.charAt(n)=='('){
								break;
							}
							else if(infix.charAt(n)==' '){
								if(n==infix.length()-1){
									throw new IllegalArgumentException();
								}
							}
							else{
								throw new IllegalArgumentException();
							}
						}
					}
				}
			}
			else if(infix.charAt(i)=='('){
				parenthesis+=1;
				
				if(i==infix.length()-1){
					throw new IllegalArgumentException();
				}
				else if(i==0){
					
				}
				else{
					for(int n = i-1;n>=0;n--){
						if(infix.charAt(n)=='+'||infix.charAt(n)=='-'||infix.charAt(n)=='*'||infix.charAt(n)=='/'||infix.charAt(n)=='^'||infix.charAt(n)=='%'||infix.charAt(n)=='('){
							break;
						}
						else if(infix.charAt(n)==' '){
							/*if(n==0){
								throw new IllegalArgumentException();
							}*/
						}
						else{
							throw new IllegalArgumentException();
						}
					}
					for(int n = i+1;n<infix.length();n++){
						if(Character.isDigit(infix.charAt(n))||infix.charAt(n)=='('){
							break;
						}
						else if(infix.charAt(n)==' '){
							if(n==infix.length()-1){
								throw new IllegalArgumentException();
							}
						}
						else{
							throw new IllegalArgumentException();
						}
					}
					
				}
			}
			else if(infix.charAt(i)==')'){
				parenthesis-=1;
				
				if (i == 0) {
					throw new IllegalArgumentException();
				/*if(i == infix.length()-1){
					
				}
				else{
					for(int n = i+1;n<infix.length();n++){
						if(infix.charAt(n)=='+'||infix.charAt(n)=='-'||infix.charAt(n)=='*'||infix.charAt(n)=='/'||infix.charAt(n)=='^'||infix.charAt(n)=='%'||infix.charAt(n)==')'){
							break;
						}
						else if(infix.charAt(n)==' '){
						}
						else{
							throw new IllegalArgumentException();
						}
					}
				}*/
				}
				else if(i==infix.length()-1){
					
				}
				else if(i<infix.length()-1){
					for (int n = i - 1; n >= 0; n--) {
						if (Character.isDigit(infix.charAt(n))||infix.charAt(n)==')') {
							break;
						} else if (infix.charAt(n) == ' ') {
							if (n == 0) {
								throw new IllegalArgumentException();
							}
						} else {
							throw new IllegalArgumentException();
						}

					}
					for(int n = i+1;n<infix.length();n++){
						if(infix.charAt(n)=='+'||infix.charAt(n)=='-'||infix.charAt(n)=='*'||infix.charAt(n)=='/'||infix.charAt(n)=='^'||infix.charAt(n)=='%'||infix.charAt(n)==')'){
							break;
						}
						else if(infix.charAt(n)==' '){
						}
						else{
							throw new IllegalArgumentException();
						}
					}
				}
				else{
					throw new IllegalArgumentException();
				}
			}
			else if(Character.isDigit(infix.charAt(i))){
				if(i==0){
				}
				else{
					if(i>0){
						for(int n = i-1;n>=0;n--){
							if(infix.charAt(n)=='+'||infix.charAt(n)=='-'||infix.charAt(n)=='*'||infix.charAt(n)=='/'||infix.charAt(n)=='^'||infix.charAt(n)=='%'||infix.charAt(n)=='('){
								break;
							}
							else if(Character.isDigit(infix.charAt(n))){
								
							}
							else if(infix.charAt(n)==' '){
								if(n==0){
									break;
								}
							}
							else{
								throw new IllegalArgumentException();
							}
						}
					}
					
					if(i<infix.length()-1){
						for(int n = i+1;n<infix.length();n++){
							if(infix.charAt(n)=='+'||infix.charAt(n)=='-'||infix.charAt(n)=='*'||infix.charAt(n)=='/'||infix.charAt(n)=='^'||infix.charAt(n)=='%'||infix.charAt(n)==')'){
								break;
							}
							else if(Character.isDigit(infix.charAt(n))){
								
							}
							else if(infix.charAt(n)==' '){
								
							}
							else{
								throw new IllegalArgumentException();
							}
						}
					}
				}
			}
		}
		
		if(parenthesis != 0){
			throw new IllegalArgumentException();
		}
	}
	
	public static int menu(){
		int choice = 0;
		boolean check = false;
		Scanner in = new Scanner(System.in);
		System.out.println("Please choose one of the folllowing options:");
		System.out.println("(1) Convert expression from infix to postfix");
		System.out.println("(2) Evaluate a postfix expression");
		System.out.println("(3) Exit the program");
		
		do{
			try {
				choice = in.nextInt();
				if(choice<1||choice > 3){
					throw new InputMismatchException();
				}
				else
					check = true;

			} catch (InputMismatchException e) {
				System.out.println("Error! Please choose one of the options");
				in.nextLine();
			}
		}while(!check);
		return choice;
	}
	
	public static String expression(){
		Scanner in = new Scanner(System.in);
		String express = "";
		boolean check = false;
		do{
			try{
				System.out.println("Please enter a valid expression:");
				express = in.nextLine();
				validate(express);
				check = true;
			}
			catch(IllegalArgumentException e){
				System.out.println("Error! An invalid expression was entered");
			}
			
		}while(!check);
		return express;
	}
	
	
	
	
	

}
