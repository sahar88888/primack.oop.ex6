package code_elements;

import code_elements.variables.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class Condition extends Block {

    static public String CREATE_REGEX = "blablabla"; //TODO fix

    protected Condition(BufferedReader f, String def_line,
            ArrayList<Variable> scope_vars)  throws
            IOException, BadElementException{
        super(f,def_line, scope_vars);
    }

    static Condition createFromLine(BufferedReader f, String line,
            ArrayList<Variable> scope_vars ) throws
            BadElementException, IOException{
        Condition elem = null;
        if(CodeElement.check_match(line, CREATE_REGEX)) {
            if ((elem = IfCondition.createFromLine(f, line, scope_vars))  ==
                    null) {
                elem = WhileCondition.createFromLine(f, line, scope_vars);
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
    public void is_legal() throws BadElementException {
        super.is_legal();
        String[] statements = (this.definition_line.split("\\(")[1]).split
                ("logical operators");
        for(String statement : statements){
            
        }

    }
}
