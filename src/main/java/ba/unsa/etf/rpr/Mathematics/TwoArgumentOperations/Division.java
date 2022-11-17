package ba.unsa.etf.rpr.Mathematics.TwoArgumentOperations;

/**
 * Class that does division between two numbers
 */
public class Division extends TwoArgumentOperations {
    /**
     * Method that calculates the division between two numbers
     * @param num1 double nominator
     * @param num2 double denominator
     * @return double
     * @throws IllegalArgumentException if num2 is zero
     */
    @Override
    public double doCalculation(double num1, double num2) throws IllegalArgumentException {
        if(num2==0)throw new IllegalArgumentException("The second parameter must be a non - zero value!");
        return num1/num2;
    }

}
