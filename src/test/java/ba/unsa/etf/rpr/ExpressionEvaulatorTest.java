package ba.unsa.etf.rpr;


import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class ExpressionEvaulatorTest {
    /**
     * Test that checks some mathematical expressions that need two arguments
     */
    @Test
    public void checkSomeExpressions() {
        assertAll("Check some expressions",
                () ->assertEquals(-9, new ExpressionEvaluator().evaluate("( ( 2 - 3 ) * ( 4 + 5 ) )")),
                () ->assertEquals(90, new ExpressionEvaluator().evaluate("( ( 2 + 3 ) * ( ( 4 + 5 ) * 2 ) )")),
                () ->assertEquals(17, new ExpressionEvaluator().evaluate("( 2 + ( 3 * 5 ) )")),
                () ->assertEquals(5, new ExpressionEvaluator().evaluate("( 1 + ( 1 + ( 1 + ( 1 + ( 1 / 1 ) ) ) ) )")),
                () ->assertEquals(15, new ExpressionEvaluator().evaluate("( 1 + ( 2 * ( 3 + 4 ) ) )")),
                () ->assertEquals(-3, new ExpressionEvaluator().evaluate("( -3 )"))
        );
    }

    /**
     * Test that checks more mathematical expressions that need two and one arguments
     */
    @Test
    public void checkSomeMoreExpressions(){
        assertAll("Check some more expressions",
                () -> assertEquals(3, new ExpressionEvaluator().evaluate("( 1 + sqrt ( ( 1 + 3 ) ) )")),
                () -> assertEquals(-5, new ExpressionEvaluator().evaluate("( sqrt ( 1 ) - ( sqrt ( 9 ) * 2 ) )")),
                () -> assertEquals(2, new ExpressionEvaluator().evaluate("( sqrt ( 4 ) )"))
        );
    }

    /**
     * Test to see if evaluate throws an error if given expression is not valid missing space
     * @throws RuntimeException is thrown if expression is not valid
     */
    @Test
    public void invalidExpression1() throws RuntimeException {
        assertThrows(RuntimeException.class, () ->{
            new ExpressionEvaluator().evaluate("( 1 +2 )");
        });
    }

    /**
     * Test to see if evaluate throws an error if given exception is not valid missing space
     * @throws RuntimeException is thrown if expression is not valid
     */
    @Test
    public void invalidExpression2() throws RuntimeException {
        assertThrows(RuntimeException.class, () ->{
            new ExpressionEvaluator().evaluate("( 1 + ( 2 * ( 3+ 4 ) ) )");
        });
    }

    /**
     * Test to see if evaluate throws an error if given exception is not valid
     * invalid number
     * @throws RuntimeException is thrown if expression is not valid
     */
    @Test
    public void invalidExpression3() throws RuntimeException {
        assertThrows(RuntimeException.class, () ->{
            new ExpressionEvaluator().evaluate("( 1 + ( 2 * ( 3 + k ) ) )");
        });
    }
    /**
     * Test to see if evaluate throws an error if given exception is not valid
     * missing number
     * @throws RuntimeException is thrown if expression is not valid
     */
    @Test
    public void checkExpressionOrder1() throws RuntimeException{
        assertThrows(RuntimeException.class, ()->{
            new ExpressionEvaluator().evaluate("( + 1 )");
        });
    }
    /**
     * Test to see if evaluate throws an error if given exception is not valid
     * missing operator
     * @throws RuntimeException is thrown if expression is not valid
     */
    @Test
    public void checkExpressionOrder2() throws RuntimeException{
        assertThrows(RuntimeException.class, ()->{
            new ExpressionEvaluator().evaluate("( 1 ( 2 + 3 ) )");
        });
    }
    /**
     * Test to see if given exception is valid
     * extra operator
     */
    @Test
    public void checkExpressionOrder3(){
        assertFalse(new ExpressionValidator().checkIfOrderIsCorrect("( 1 + * 3 )"));
    }

    /**
     * Test to see if given exception is valid
     * missing operator
     */
    @Test
    public void checkExpressionOrder4() {
        assertFalse(new ExpressionValidator().checkIfOrderIsCorrect("( 1 + ( 1 + 4 ) ( 2 + 3 ) )"));
    }


    /**
     * Test to see if expression is valid
     * missing numbers
     * @throws RuntimeException if expression is not valid
     */
    @Test
    public void checkExpressionOrder5() throws RuntimeException{
        assertThrows(RuntimeException.class, ()->{
            new ExpressionEvaluator().evaluate("( ( 1 + 2 ) + )");
        });
    }

    /**
     * Test to see if expression is valid
     * missing colon
     */
    @Test
    public void missingColon1(){
        assertFalse(new ExpressionValidator().checkIfEveryLeftSemicolonIsClosed("( ( ) ) ( )"));
    }

    /**
     * Test to see if expression is valid
     * missing colon
     */
    @Test
    public void missingColon2(){
        assertFalse(new ExpressionValidator().checkIfEveryLeftSemicolonIsClosed("( ( ( ) )"));
    }

    /**
     * Test to see if expression is valid
     * missing colon
     */
    @Test
    public void missingColon3(){
        assertFalse(new ExpressionValidator().numberOfColonsEquals2TimesNumberOfOps("( 1 + 2 + 3 )".split(" "),false));
    }

    /**
     * Test to see if expression is valid
     * missing colon
     */
    @Test
    public void     missingColon4(){
        assertFalse(new ExpressionValidator().numberOfColonsEquals2TimesNumberOfOps("( ( 1 + 2 ) + sqrt ( 4 ) + 0 )".split(" "),false));
    }
}
