package ba.unsa.etf.rpr;

import java.util.Stack;

/**
 * Class that calculates the value of an expression that is given via a String
 */
public class ExpressionEvaluator {
    private Stack<String> ops;
    private Stack<Double> numbers;
    private OperationDoer doer;
    private ExpressionValidator validator;

    public ExpressionEvaluator(){
        ops = new Stack<>();
        numbers = new Stack<>();
        doer = new OperationDoer();
        validator = new ExpressionValidator();
    }

    /**
     * Method that return the value of the given expression
     * @param expression String
     * @return Double
     * @throws RuntimeException if the second parameter of the division operator is zero
     * @throws IllegalArgumentException if the parameter of sqrt is a negative number, or if denominator is zero
     */
    public Double evaluate(String expression) throws RuntimeException, IllegalArgumentException{
        validator.expressionNotValid(expression);
        return dijkstraAlgorithm(expression.split(" "));
    }

    /**
     * Algorithm that evaluates the expression
     * @param list a String[] of operations and numbers that are in the expression
     * @return Double
     */
    private Double dijkstraAlgorithm(String[] list) {
        boolean skip = false;
        for(String s : list){
            if(s.equals(")")){
                double num2 = numbers.pop();
                if(ops.empty()){
                    return num2;
                }
                String op = ops.pop();
                if (doer.isTwoArgumentOperation(op)) {
                    numbers.push(doer.doOperation(op, numbers.pop(), num2));
                }else if (doer.isOneArgumentOperation(op)){
                    skip = true;
                    numbers.push(doer.doOperation(op, num2));
                }
            }else if(doer.isOperation(s)){
                skip = false;
                ops.push(s);
            } else if(validator.checkIfNumber(s)){
                numbers.push(Double.parseDouble(s));
            } else if (skip) {
                skip = false;
            }
        }
        return numbers.pop();
    }
}
