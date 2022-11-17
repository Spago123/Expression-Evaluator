package ba.unsa.etf.rpr;


import ba.unsa.etf.rpr.Mathematics.*;
import ba.unsa.etf.rpr.Mathematics.OneArgumentOperations.OneArgumentOperations;
import ba.unsa.etf.rpr.Mathematics.OneArgumentOperations.SquareRoot;
import ba.unsa.etf.rpr.Mathematics.TwoArgumentOperations.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Operation doer is a class that calculates values of expressions,
 * and checks ifa given string is a valid operation
 */
public class OperationDoer {
    Map<String, TwoArgumentOperations> allTwoOps;
    Map<String, OneArgumentOperations> allOneOps;
    public OperationDoer(){
        allTwoOps = new HashMap<String, TwoArgumentOperations>();
        allOneOps = new HashMap<String, OneArgumentOperations>();
        allTwoOps.put("+",new Sumation());
        allTwoOps.put("-", new Subtraction());
        allTwoOps.put("*", new Multiplication());
        allTwoOps.put("/", new Division());
        allOneOps.put("sqrt", new SquareRoot());
    }

    /**
     * A method that is called to evaluate the value of the expression num1 op num2
     * when called with 3 parameters it is refered to two argument operations
     * @param op String
     * @param num1 double
     * @param num2 double
     * @return double
     * @throws IllegalArgumentException
     */
    public double doOperation(String op, double num1, double num2) throws IllegalArgumentException{
        return allTwoOps.get(op).doCalculation(num1, num2);
    }

    /**
     * Method that evaluates the value of the expression op ( num )
     * when called with two parameters it is refered to one argument operations
     * @param op String
     * @param num double
     * @return double
     */
    public double doOperation(String op, double num){
        return allOneOps.get(op).doCalculation(num);
    }

    /**
     * Method that checks if the given string op is a valid two argument operation
     * @param op String
     * @return boolean
     */
    public boolean isTwoArgumentOperation(String op) {
        return allTwoOps.containsKey(op);
    }

    /**
     * Method that check if the given string op is a valid one argument operation
     * @param op String
     * @return boolean
     */
    public boolean isOneArgumentOperation(String op) { return allOneOps.containsKey(op);}

    /**
     * Method that checks if the given string op is a valid operation
     * @param op String
     * @return boolean
     */
    public boolean isOperation(String op) { return isOneArgumentOperation(op) || isTwoArgumentOperation(op);}
}
