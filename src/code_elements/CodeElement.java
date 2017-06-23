package code_elements;

import code_elements.variables.Variable;

import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    protected boolean varExists(String name, ArrayList<Variable> vars){
        for(Variable v : vars){
            if(v.getName().equals(name))
                return true;
        }
        return false;
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
}
