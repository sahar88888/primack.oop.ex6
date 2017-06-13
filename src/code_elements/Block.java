package code_elements;

import code_elements.variables.Variable;

import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Block extends CodeElement {

    ArrayList<Variable> scope_vars;
    ArrayList<CodeElement> elements;

    static CodeElement createFromLine(String line){
        // TODO wild sex
    }
}
