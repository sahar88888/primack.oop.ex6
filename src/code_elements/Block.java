package code_elements;

import code_elements.variables.Variable;

import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Block extends CodeElement {

    static String CREATE_REGEX = ".*\\{";

    ArrayList<Variable> scope_vars;
    ArrayList<CodeElement> elements;

    static CodeElement createFromLine(String line){
        CodeElement elem = null;
        if(CodeElement.check_match(line,CREATE_REGEX)) {
            if ((elem = Method.createFromLine(line)) == null) {
                elem = Condition.createFromLine(line);
            }
        }
        return elem;
    }
}
