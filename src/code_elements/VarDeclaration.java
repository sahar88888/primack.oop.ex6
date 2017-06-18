package code_elements;

import code_elements.variables.Variable;
import code_elements.variables.Variable.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import  static oop.Custom_Regexes.*;




/**
 * Created by t8417719 on 12/06/2017.
 */
public class VarDeclaration extends Statement {

    static String Line_Regex= VAR_TYPE+"[\\s]+("+VAR_NAME+WHITESPACE+","+WHITESPACE+")*"+VAR_NAME+WHITESPACE +
            "("+ASSIGNMENT+")?"+LINE_END;

    private ArrayList<Variable> vars;//String of variables declared
    private String type;//

    public VarDeclaration(ArrayList<Variable> vars, String type) {
        this.vars = vars;
        this.type = type;
    }

    static VarDeclaration createFromLine(String line) throws BadElementException{
        // TODO wild sex

        if (line.matches(Line_Regex))
        {
            String[] LineParts = line.split("=",2); //splitting the line to a max of 2 parts: vars and assignment.

            ArrayList<VarType> var_names = new ArrayList<>();

            //TAKE CARE OF - DEAL WITH THE CASE OF ASSIGNMENT!
            Pattern p = Pattern.compile(VAR_NAME);
            Matcher m = p.matcher(LineParts[0]);//searching for names in the first part.

            m.find();//to skip the variable type, that surely could be a var name.
            String type_str = Cut(line,m);
            VarType type = GetValueType(type_str);

            while (m.find()) //getting all variable names.
            {
                String var_name=Cut(line,m);//adding all names to the array.
                Variable var = new Variable(var_name,type);
            }
            if(LineParts.length>1){ //if there is also an assignment

            }

            return new VarDeclaration(var_names,type);
        }
        else
            return null;
    }

    public ArrayList<String> getVars() {
        return vars;
    }

    public String getType() {
        return type;
    }
}
