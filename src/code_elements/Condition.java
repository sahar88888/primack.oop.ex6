package code_elements;

import code_elements.variables.Variable;
import oop.Custom_Regexes;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static oop.Custom_Regexes.*;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class Condition extends Block {

    static public String CREATE_REGEX = "blablabla"; //TODO fix
    static Variable.VarType[] boolTypes = new Variable.VarType[]{Variable.VarType.INT,
            Variable.VarType.BOOLEAN, Variable.VarType.DOUBLE};

    protected Condition(BufferedReader f, String def_line) throws
            IOException, BadElementException{
        super(f,def_line);
    }

    static Condition createFromLine(BufferedReader f, String line) throws
            BadElementException, IOException{
        Condition elem = null;
        if(CheckMatch(line, CREATE_REGEX)) {
            if ((elem = IfCondition.createFromLine(f, line))  ==
                    null) {
                elem = WhileCondition.createFromLine(f, line);
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
    public void is_legal(ArrayList<Variable> scope_vars) throws BadElementException {
        for(String statement : GetSubStrings(def_line, VAL)){
            boolean valid = false;
            if(CheckMatch(statement,LOGICAL_VALUE)){
                valid = true;
            }
            else if(CheckMatch(statement, Custom_Regexes
                    .VAR_NAME)){
                //this is a variable name, and we need to check if it exists
                // within scope.
                Variable v = find_var_by_string(scope_vars, statement);
                if(v.isBoolean()){
                    valid = true;
                }
            }
            if(!valid){
                throw new BadElementException();
            }
        }
        super.is_legal(scope_vars);
    }
}
