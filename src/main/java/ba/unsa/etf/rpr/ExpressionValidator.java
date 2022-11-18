package ba.unsa.etf.rpr;

import java.util.Arrays;

/**
 * Expression Validator is a class that tests if the expression that
 * is being evaluated fits the criteria
 */
public class ExpressionValidator {
    OperationDoer doer;
    public ExpressionValidator(){
        doer = new OperationDoer();
    }

    /**
     * A method which throws an exception if the given expression does not fit the criteria
     * @param expression String
     * @throws RuntimeException if expression is invalid
     */
    public void expressionNotValid(String expression) throws RuntimeException {
        if(!checkForCharacterValidity(expression) || !checkIfOrderIsCorrect(expression)
            || !checkIfEveryLeftBracketIsClosed(expression) || !numberOfBracketsEquals2TimesNumberOfOps(expression.split(" "),false))
            throw new RuntimeException("The expression " + expression + "cannot be evaluated");
    }
    /**
     * Method that check if any character in the expression is invalid
     *
     * @param expression String
     * @return true or false
     */
    public boolean checkForCharacterValidity(String expression) {
        String[] list = expression.split(" ");
        for(String s : list){
            if(!checkIfValidCharacter(s))
                return false;
        }
        return true;
    }
    /**
     * Metod that check if the character belong to any of the knows valid operations
     * @param character String
     * @return true or false
     */
    private boolean checkIfValidCharacter(String character){
        return new OperationDoer().isOperation(character) || checkIfNumber(character)
                || character.equals("(") || character.equals(")");
    }

    /**
     * Metod that checks if a string is parsable to a number
     * @param character String
     * @return true or false
     */
    public boolean checkIfNumber(String character){
        try{
            Double.parseDouble(character);
        }catch (Exception e){
            return false;
        }
        return true;
    }


    /**
     * Method that checks if the order of operations, numbers and brackets in the expression is correct
     * @param expression String
     * @return true or false
     */
    public boolean checkIfOrderIsCorrect(String expression) {
        String[] list = expression.split(" ");
        String nextCharacter = "(";
        for(int i = 0; i < list.length - 1; i++){
            if(list[i].equals("(") && !afterLeftBracket(list[i+1])){
                return false;
            }else if(list[i].equals(")") && !afterRightBracket(list[i+1])){
                return false;
            }else if(new OperationDoer().isOperation(list[i]) && !afterOperation(list[i+1])){
                return false;
            }else if(checkIfNumber(list[i]) && !afterNumber(list[i+1])){
                return false;
            }
        }
        return true;
    }

    /**
     * Method that check if the character after the left bracket is a number or op, or a bracket
     * @param s String
     * @return true or false
     */
    private boolean afterLeftBracket(String s){
        return checkIfNumber(s) || s.equals("(") || new OperationDoer().isOneArgumentOperation(s);
    }

    /**
     * Method that check if the character after an operation is a number or a left semicolon
     * @param s String
     * @return true or false
     */
    private boolean afterOperation(String s){
        return checkIfNumber(s) || s.equals("(") || new OperationDoer().isOneArgumentOperation(s);
    }

    /**
     * Method that check if the character after a number is an operation or a right semicolon
     * @param s String
     * @return true or false
     */
    private boolean afterNumber(String s){
        return new OperationDoer().isOperation(s) || s.equals(")");
    }

    /**
     * Metthod that check if the character after a right bracket is an op or a right bracket
     * @param s String
     * @return true or false
     */
    private boolean afterRightBracket(String s){
        return s.equals(")") || new OperationDoer().isOperation(s);
    }

    /**
     * Method that checks if the expression had every left bracket closed
     * @param expression String
     * @return true or false
     */
    public boolean checkIfEveryLeftBracketIsClosed(String expression){
        String[] list = expression.split(" ");
        int openClosed = 0;
        for(int i = 1; i < list.length - 1; i++){
            if(list[i].equals("(")){
                openClosed++;
            }else if(list[i].equals(")")) {
                openClosed--;
            }

            if(openClosed < 0)
                return false;
        }
        return openClosed == 0 && list[0].equals("(") && list[list.length-1].equals(")");
    }

    /**
     * Method that check if the number of brackets and operations matches
     * @param list String[] array
     * @param isOneArgument if true we are checking the statement in a one argument op, if not it's an expression
     * @return true or false
     */
    public boolean numberOfBracketsEquals2TimesNumberOfOps(String[] list, boolean isOneArgument){
        int multiplier = isOneArgument ? 2 : 0;
        int ops2Counter = 0, bracketCounter = 0;
       //String[] list = expression.split(" ");
        for ( int i = 0; i < list.length; i++){
            if(doer.isTwoArgumentOperation(list[i])){
                ops2Counter++;
            } else if(doer.isOneArgumentOperation(list[i])){
                ////now we are going to evaluate the expression between the one argument colons
                ///(finding the substring)
                int lefts = 1, rights = 0, j = i + 2;
                for(; j < list.length && lefts != rights; j++){
                    if(list[j].equals("(")){
                        lefts++;
                    }else if(list[j].equals(")")){
                        rights++;
                    }
                }
                if(!numberOfBracketsEquals2TimesNumberOfOps(Arrays.copyOfRange(list,i+1,j),true)){
                    return false;
                }
                i = j - 1;
            } else if(list[i].equals("(") || list[i].equals(")")){
                bracketCounter++;
            }
        }

        return (bracketCounter-multiplier==2 * ( ops2Counter ) )
                || (bracketCounter == 2 && ops2Counter == 0);
    }
}

