package ba.unsa.etf.rpr;

/**
 * Console application that evaluates the value of an expression
 * the symbols that are used
 * ( summation + )
 * ( subtraction - )
 * ( multiplication x )
 * ( division / )
 * ( square root sqrt )
 * @author Harun Spago
 * @version 1.0.0
 */
public class App {
    /**
     * Main method which takes one string as the input and evaluates its content
     * @param args is the expression that is being evaluated, every single element of args represents one
     * operation, number or colon in the expression
     */
    public static void main( String[] args ) {
        try {
            ExpressionEvaluator ex = new ExpressionEvaluator();
            StringBuilder expression = new StringBuilder();
            for (String s : args) {
                if (s.equals("x"))
                    s = "*";
                expression.append(s);
                expression.append(" ");
            }
            System.out.println(expression + " = " + ex.evaluate(expression.toString()));
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}