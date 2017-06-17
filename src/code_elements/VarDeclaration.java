package code_elements;

import code_elements.variables.Variable;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import  static oop.Custom_Regexes.*;



/**
 * Created by t8417719 on 12/06/2017.
 */
public class VarDeclaration extends Statement {

    static Pattern p = Pattern.compile(VAR_TYPE+"[\\s]+("+VAR_NAME+"\\ [\\s]*,[\\s]*)*");


    ArrayList<Variable> vars;

    static VarDeclaration createFromLine(String line) throws BadElementException{
        // TODO wild sex
        Matcher m  = p.matcher(line);
        if (m.matches())
        {

        }
        else
            return null;
    }
}
