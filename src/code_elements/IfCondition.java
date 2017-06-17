package code_elements;

import code_elements.variables.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class IfCondition extends Condition {

    static String CREATE_REGEX = "if.*";

    protected IfCondition(BufferedReader f, String def_line,
              ArrayList<Variable> scope_vars ) throws
            IOException, BadElementException{
        super(f,def_line, scope_vars);
    }

    static IfCondition createFromLine(BufferedReader f, String line,
            ArrayList<Variable> scope_vars)  throws
            IOException, BadElementException {
        if(CodeElement.check_match(line,CREATE_REGEX)){
            return new IfCondition(f, line,scope_vars);
        }
        else return null;
    }
}
