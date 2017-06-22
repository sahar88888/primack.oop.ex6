package code_elements;

import code_elements.variables.Variable;
import oop.Custom_Regexes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Method extends Block {

    String line;

    static String PARAM_REGEX = Custom_Regexes.VAR_TYPE+Custom_Regexes
            .WHITESPACE+Custom_Regexes.VAR_NAME+Custom_Regexes.WHITESPACE;

    protected Method(BufferedReader f, String line) throws IOException,
            BadElementException{
        super(f,line);
        this.line = line;
    }

    static Method createFromLine(BufferedReader f, String line)  throws
            IOException, BadElementException{
        if(CodeElement.check_match(line,Custom_Regexes.METHOD_DECLARATION)){
            return new Method(f, line);
        }
        else return null;
    }


    @Override
    protected void checkElementType(CodeElement e) throws BadElementException {
        if (e instanceof Method){
            throw new BadElementException();
        }
    }

    @Override
    public void is_legal(ArrayList<Variable> scope_vars) throws BadElementException {
        super.is_legal(scope_vars);
        String parameters = ((this.definition_line.split("\\)")[0]).split
                ("\\(")[1]);
        Pattern p = Pattern.compile(PARAM_REGEX);
        Matcher m = p.matcher(parameters);
        while(m.find()){
            String sub = parameters.substring(m.start(),m.end());
            String varname = sub.split(Custom_Regexes.WHITESPACE)[1];
            overrideVarByName(varname,scope_vars);
            // add parameters as variables
            VarDeclaration var_dec = VarDeclaration.createFromLine(sub);
            for(Variable v: var_dec.getVars()) {
                local_vars.add(v);
            }
        }
    }
}
