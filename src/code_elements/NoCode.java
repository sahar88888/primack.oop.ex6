package code_elements;

import code_elements.variables.Variable;

import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class NoCode extends CodeElement {

    @Override
    public void is_legal(ArrayList<Variable> scope_vars) throws BadElementException {
        return; //always legal.
    }

    static public NoCode createFromLine(String line) {
        NoCode elem;
        if ((elem = Empty.createFromLine(line)) == null) {
            elem = Comment.createFromLine(line);
        }
        return elem;


    }

}
