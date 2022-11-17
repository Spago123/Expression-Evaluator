package ba.unsa.etf.rpr.Mathematics.OneArgumentOperations;

/**
 * Square root is a class that represents the one argument operation called square root
 */
public class SquareRoot extends OneArgumentOperations{
    /**
     * Calculates the square root of a numbers
     * @param num double
     * @return double
     * @throws IllegalArgumentException if num is negative
     */
    @Override
    public double doCalculation(double num) throws IllegalArgumentException{
        if ( num < 0 )
            throw new IllegalArgumentException(num + " is negative, but it cannot be negative!");
        return Math.sqrt(num);
    }
}