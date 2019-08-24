package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {
	
	private LinkedStack<String> operators = new LinkedStack<String>();
	private LinkedStack<Integer> operands  = new LinkedStack<Integer>();
	
	/** return stack object (for testing purpose) */
	public LinkedStack<String> getOperatorStack() { return operators; }
	public LinkedStack<Integer> getOperandStack()  { return operands;  }
	
	
	/** This method performs one step of evaluation of a infix expression.
	 *  The input is a token. Follow the infix evaluation algorithm
	 *  to implement this method. If the expression is invalid, throw an
	 *  exception with the corresponding exception message.
	 */	
	public void evaluate_step(String token) throws Exception {
		if(isOperand(token)) {
			// TODO: What do we do if the token is an operand?
			operands.push(Integer.parseInt(token));
		} else {
			/* TODO: What do we do if the token is an operator?
			   If the expression is invalid, make sure you throw
			   an exception with the correct message.
			   
			   You can call precedence(token) to get the precedence
			   value of an operator. It's already defined in 
			   the Evaluator class.
			 */ 
			if (token.equals(")")) {
				//Fixing here
				/*while (!operators.top().equals("(")) {
					if (operators.isEmpty()) throw new Exception("missing (");
					process_operator(operators.pop());
					
				}
				operators.pop();
				*/
				while (!operators.isEmpty()) {
					
					if (operators.top().equals("(")) {operators.pop(); break;}
					process_operator(operators.pop());
					if (operators.isEmpty()) throw new Exception("missing (");
				}
				
				
				
			}
				
				
			
			else if (token.equals("(") || operators.isEmpty() || (precedence(token) > precedence(operators.top()))) 
				operators.push(token);
			
			//FIXING
			else {
				while (!operators.isEmpty()) {
				
				if ((precedence(token)<=precedence(operators.top())))
						process_operator(operators.pop());			
				else {operators.push(token); break;}
			}
				if (operators.isEmpty())operators.push(token);
				
			}
			
			/*else while (!(precedence(token)>precedence(operators.top())) ) 
				process_operator(operators.pop());	
				if (operators.isEmpty()) 
					operators.push(token);
				else if ((precedence(token)>precedence(operators.top())))
					operators.push(token);
					*/
			
		}
		
	}
	
	public void process_operator(String token) throws Exception {
		
		if (token.equals("!")) {
			Integer tokenInt1 = operands.pop();
			if(tokenInt1 == null ) throw new Exception("too few operands");
			tokenInt1 = tokenInt1*(-1);
			operands.push(tokenInt1);
			}
		else {
		Integer tokenInt1 = operands.pop();
		if (tokenInt1 == null) throw new Exception("too few operands");
		Integer tokenInt2 = operands.pop();
		if (tokenInt2 == null) throw new Exception("too few operands");
		if (token.equals("+")) {
			Integer sum = tokenInt1 + tokenInt2;
			operands.push(sum);
			}
		else if (token.equals("-")) {
			Integer sub = tokenInt2 - tokenInt1;
			operands.push(sub);
			}
		else if (token.equals("*")) {
			Integer mult = tokenInt1 * tokenInt2;
			operands.push(mult);
			}
		else if (token.equals("/")) {
			if (tokenInt1 == 0) throw new Exception("division by zero");
			Integer div = tokenInt2 / tokenInt1;
			operands.push(div);
			}
		else throw new Exception ("invalid operator");
		}
	}
	/** This method evaluates an infix expression (defined by expr)
	 *  and returns the evaluation result. It throws an Exception object
	 *  if the infix expression is invalid.
	 */
	public Integer evaluate(String expr) throws Exception {
		
		for(String token : ArithParser.parse(expr)) {
			evaluate_step(token);
		}
		
		/* TODO: what do we do after all tokens have been processed? */
		while (!operators.isEmpty())
			process_operator(operators.pop());
		// The operand stack should have exactly one operand after the evaluation is done
		if(operands.size()>1)
			throw new Exception("too many operands");
		else if(operands.size()<1)
			throw new Exception("too few operands");
		
		return operands.pop();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new InfixEvaluator().evaluate("5+(5+2*(5+9))/!8"));
	}
}
