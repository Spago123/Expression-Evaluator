package ba.unsa.etf.rpr.Mathematics.TwoArgumentOperations;

import ba.unsa.etf.rpr.Mathematics.Operation;

/**
 * Abstract class that represents every two argument operations
 */
public abstract class TwoArgumentOperations implements Operation {

    public abstract double doCalculation(double num1, double num2);
}
