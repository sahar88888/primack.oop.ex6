package code_elements;

import code_elements.variables.Variable;

import java.io.Reader;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Block extends CodeElement {

    Block(Reader f) {
        super(f);
    }

    ArrayList<Variable> scope_vars;
    ArrayList<CodeElement> elements;

    static CodeElement createFromLine(String line){
        // TODO wild sex
        return new Method();
    }

    @Override
    public boolean is_legal() {
        return false;
    }
}
