package code_elements;

import code_elements.variables.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class WhileCondition extends Condition {

    protected WhileCondition(BufferedReader f, String line,
             ArrayList<Variable> scope_vars ) throws
            IOException, BadElementException{
        super(f, line, scope_vars);
    }

    static WhileCondition createFromLine(BufferedReader f, String line,
             ArrayList<Variable> scope_vars) throws
            IOException, BadElementException {
        if (CodeElement.check_match(line, CREATE_REGEX)) {
            return new WhileCondition(f, line, scope_vars);
        } else return null;
    }
}
