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

    protected IfCondition(BufferedReader f, String def_line) throws
            IOException, BadElementException{
        super(f,def_line);
    }

    static IfCondition createFromLine(BufferedReader f, String line)  throws
            IOException, BadElementException {
        if(CodeElement.check_match(line,CREATE_REGEX)){
            return new IfCondition(f, line);
        }
        else return null;
    }
}
