package code_elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static oop.Custom_Regexes.GetValueTypeFromName;
import static oop.Custom_Regexes.VAL;
import static oop.Custom_Regexes.VAR_NAME;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class CodeElement {
    protected String def_line;

    protected CodeElement(String line){
        this.def_line=  line;
    }
    /**
     *
     * @param f
     * @param line
     * @return
     * @throws IOException
     * @throws BadElementException
     */
    static CodeElement createFromLine(BufferedReader f, String line) throws
            IOException, BadElementException{
        CodeElement elem;
        if((elem = Block.createFromLine(f,line))==null){
            if((elem=NoCode.createFromLine(line))==null){
                elem = Statement.createFromLine(f,line);
            }
        }
        return elem;
    }




    /**
     * Checks whether all variable/method names exists in the currect
     * context, in this codeElement.
     * @return
     */
    public abstract void is_legal(ArrayList<Variable> scope_vars) throws
            BadElementException;

    protected static Variable findVarByName(String name, ArrayList<Variable>
            vars){
        for(Variable v : vars){
            if(v.getName().equals(name))
                return v;
        }
        return null;
    }

    /**
     * finding a variable with a given variable name, in a list of variables.
     * @param variables
     * @param varName
     * @return
     * @throws BadElementException if the variable was not found.
     */
    protected static Variable find_var_by_string(ArrayList<Variable> variables,
             String varName) throws BadElementException {
        for (Variable var : variables) {
            if (var.getName().equals(varName))
                return var;
        }
        throw new BadElementException();//if the variable wasn't found..
    }

    protected static Variable.VarType getExpressionType(String expr,
                    ArrayList<Variable> scope_vars) throws BadElementException{
        if(expr.matches(VAR_NAME)){
            Variable v = findVarByName(expr,scope_vars);
            if(v!=null && v.isInitialized()){
                return v.getType();
            }
        }
        else if(expr.matches(VAL)){
            return GetValueTypeFromName(expr);
        }
        throw new BadElementException();
    }
}
