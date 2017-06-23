package code_elements;

import code_elements.variables.Variable;
import oop.Custom_Regexes;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static oop.Custom_Regexes.*;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class Condition extends Block {

    static public String BOOLEAN_UNIT = WHITESPACE +"("+VAR_NAME+"|"+BOOLEAN_VAL+")";
    static public String BOOLEAN_EXPRESSION = "("+BOOLEAN_UNIT+BOOLEAN_OPERATOR+")*"+BOOLEAN_UNIT;
    static public String CREATE_REGEX = CONDITION_NAME+"\\("+BOOLEAN_EXPRESSION+"\\)"+WHITESPACE;


    protected Condition(BufferedReader f, String def_line) throws
            IOException, BadElementException{
        super(f,def_line);
    }

    static Condition createFromLine(BufferedReader f, String line) throws
            BadElementException, IOException{
        Condition elem = null;
        if(CheckMatch(line, CREATE_REGEX)) {
            if ((elem = IfCondition.createFromLine(f, line))  ==  null) {
                elem = WhileCondition.createFromLine(f, line);
            }
        }
        return elem;
    }

    @Override
    protected void checkElementType(CodeElement e) throws BadElementException {
        if (e instanceof Method){
            throw new BadElementException();
        }
    }

    @Override
    public void is_legal(ArrayList<Variable> scope_vars) throws BadElementException {
        for(String statement : GetSubStrings(def_line, VAL)) {
            if (statement.matches(VAR_NAME))//there can only be a problem if the value is a variable name..
            {
                Variable var = find_var_by_string(scope_vars,statement);
                if(!var.getValue().matches(BOOLEAN_VAL))
                    throw new BadElementException("variable in condition isn't boolean.");
            }
        }
        super.is_legal(scope_vars);
    }
}
