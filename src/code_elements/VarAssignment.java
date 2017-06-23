package code_elements;

import code_elements.variables.Variable;
import code_elements.variables.Variable.VarType;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static oop.Custom_Regexes.*;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class VarAssignment extends Statement {

    private String varName;//name of the variable.
    private String valName;//name of the value.

    static String LineRegex = WHITESPACE+ASSIGNMENT+LINE_END;
    public VarAssignment(String varName, String valName) {
        this.varName = varName;
        this.valName = valName;
    }

    static VarAssignment createFromLine(String line){
        Pattern p = Pattern.compile(LineRegex);
        Matcher m = p.matcher(line);
        if(m.matches())
        {
            //the ASSIGNMENT regex is devided readily to 2 groups.
            String varName = m.group(1);
            String valName = m.group(2);
            return new VarAssignment(varName,valName);
        }

        return null;//if there was no match..

    }

    @Override
    /**
     * checking legality while assigning matching values
     */
    public void is_legal(ArrayList<Variable> scope_vars) throws BadElementException
    {
        Variable var =find_var_by_string(scope_vars,varName); //getting the matching variable from the scope.

        String value;// the value to be assigned.
        if(valName.matches(VAL))//if a value is trying to be assigned.
            value = valName;

        else //we're trying to assign a variable to the variable. valName is a name of a variable.
        {
            Variable assigned_val = find_var_by_string(scope_vars,valName);
            value = assigned_val.getValue();
        }

        var.assign(value);//assigning the value to the variable.

    }


}
