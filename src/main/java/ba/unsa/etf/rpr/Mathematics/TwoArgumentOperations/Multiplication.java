package ba.unsa.etf.rpr.Mathematics.TwoArgumentOperations;

/**
 * Class that does multiplication of two numbers
 */
public class Multiplication extends TwoArgumentOperations{
    /**
     * Method that return the product of two numbers
     * @param num1 double
     * @param num2 double
     * @return double
     */
    @Override
    public double doCalculation(double num1, double num2) {
        return num1*num2;
    }

}
