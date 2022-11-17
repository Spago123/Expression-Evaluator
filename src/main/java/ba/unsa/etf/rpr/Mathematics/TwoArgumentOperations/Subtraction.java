package ba.unsa.etf.rpr.Mathematics.TwoArgumentOperations;

/**
 * Class that does the subtraction of two numbers
 */
public class Subtraction extends TwoArgumentOperations{
    /**
     * Method that subtract two numbers
     * @param num1 double
     * @param num2 double
     * @return double
     */
    @Override
    public double doCalculation(double num1, double num2) {
        return num1 - num2;
    }

}
