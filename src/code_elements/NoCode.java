package code_elements;

import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class NoCode extends CodeElement {

    protected NoCode(String line){
        super(line);
    }

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
