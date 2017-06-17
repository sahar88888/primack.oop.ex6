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

    static Pattern p = Pattern.compile(VAR_TYPE+"[\\s]+("+VAR_NAME+WHITESPACE+","+WHITESPACE+")*"+VAR_NAME+WHITESPACE);


    private ArrayList<String> vars;
    private String Type;

    static VarDeclaration createFromLine(String line){
        // TODO wild sex
        Matcher m  = p.matcher(line);
        if (m.matches())
        {
            ArrayList<String> var_names = new ArrayList<>();
            m = p.matcher(VAR_NAME);//setting m to find all variable names.

            m.find();
            String type = Cut(line,m);
            while (m.find()){
                var_names.add(Cut(line,m));//adding all names to the array.
            }
        }
        else
            return null;
    }
}
