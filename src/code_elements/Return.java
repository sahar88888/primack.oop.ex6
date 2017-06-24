package code_elements;

import java.util.ArrayList;

import static oop.Custom_Regexes.P_WHITESPACE;

/**
 * Created by t8431103 on 6/23/2017.
 */
public class Return extends Statement {
    static String CREATE_REGEX = P_WHITESPACE + "return" + P_WHITESPACE + ";" +
            "" + P_WHITESPACE;

    protected Return(String line){
        super(line);
    }

    protected static Return createFromLine(String line){
        if(line.matches(CREATE_REGEX)){
            return new Return(line);
        }
        return null;
    }

    @Override
    public void is_legal(ArrayList<Variable> scope_vars) throws BadElementException {
        return; //Oh, the irony.
    }
}
