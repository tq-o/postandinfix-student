package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class PostfixEvaluator extends Evaluator {
	
	private LinkedStack<Integer> stack = new LinkedStack<Integer>();
	
	/** return stack object (for testing purpose) */
	public LinkedStack<Integer> getStack() { return stack; }
	
	/** This method performs one step of evaluation of a postfix expression.
	 *  The input is a token. Follow the postfix evaluation algorithm
	 *  to implement this method. If the expression is invalid, throw an
	 *  exception with the corresponding exception message.
	 */
	public void evaluate_step(String token) throws Exception {
		if(isOperand(token)) {
			// TODO: What do we do if the token is an operand?
		Integer token_int = Integer.parseInt(token);
		stack.push(token_int);
			
		} else {
			/* TODO: What do we do if the token is an operator?
			   If the expression is invalid, make sure you throw
			   an exception with the correct message
			 */ 
			if (token.equals("!")) {
				Integer tokenInt1 = stack.pop();
				if(tokenInt1 == null ) throw new Exception("too few operands");
				tokenInt1 = tokenInt1*(-1);
				stack.push(tokenInt1);
				}
			else {
			Integer tokenInt1 = stack.pop();
			if (tokenInt1 == null) throw new Exception("too few operands");
			Integer tokenInt2 = stack.pop();
			if (tokenInt2 == null) throw new Exception("too few operands");
			if (token.equals("+")) {
				Integer sum = tokenInt1 + tokenInt2;
				stack.push(sum);
				}
			else if (token.equals("-")) {
				Integer sub = tokenInt2 - tokenInt1;
				stack.push(sub);
				}
			else if (token.equals("*")) {
				Integer mult = tokenInt1 * tokenInt2;
				stack.push(mult);
				}
			else if (token.equals("/")) {
				if (tokenInt1 == 0) throw new Exception("division by zero");
				Integer div = tokenInt2 / tokenInt1;
				stack.push(div);
				}
			else throw new Exception ("invalid operator");
			}
		}		
	}
	/** This method evaluates a postfix expression (defined by expr)
	 *  and returns the evaluation result. It throws an Exception
	 *  if the postfix expression is invalid.
	 */
	public Integer evaluate(String expr) throws Exception {
		
		for(String token : ArithParser.parse(expr)) {
			evaluate_step(token);
		}
		// The stack should have exactly one operand after evaluation is done
		if(stack.size()>1) {
			throw new Exception("too many operands");
		} else if (stack.size()<1) {
			throw new Exception("too few operands");
		}
		return stack.pop(); 
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new PostfixEvaluator().evaluate("50 25 ! / 3 +"));
	}
}