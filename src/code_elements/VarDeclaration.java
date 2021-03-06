package code_elements;

import code_elements.Variable.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import  static oop.Custom_Regexes.*;




/**
 * Created by t8417719 on 12/06/2017.
 */
public class VarDeclaration extends Statement {
    static String Split_Str = ",";
    static String CREATE_REGEX =  P_WHITESPACE +
            "("+P_WHITESPACE+VAR_TYPE+WHITESPACE+
            "("+NAME_OR_ASSIGNMENT+WHITESPACE+","+WHITESPACE+")*"
            +NAME_OR_ASSIGNMENT+P_WHITESPACE +")"+LINE_END;
    private ArrayList<Variable> vars;//list of variables declared
    private ArrayList<VarAssignment> assignments;//a list of assignments made during declaration.
    private VarType type; //

    protected VarDeclaration(String line, ArrayList<Variable> vars,VarType
            type,
                     ArrayList<VarAssignment> assignments) {
        super(line);
        this.vars = vars;
        this.type = type;
        this.assignments = assignments;
    }

    static VarDeclaration createFromLine(String line) throws BadElementException{
        Pattern p = Pattern.compile(CREATE_REGEX);
        Matcher m = p.matcher(line);

        if (m.matches())
        {
            ArrayList<Variable> vars = new ArrayList<>();//list of variables.
            ArrayList<VarAssignment> assignments = new ArrayList<>();//list of assignments

            //TAKE CARE OF - DEAL WITH THE CASE OF ASSIGNMENT!

            p = Pattern.compile(VAR_TYPE);
            m = p.matcher(line);//searching for the type
            m.find();

            String type_str = Cut(line,m);
            VarType type = GetValueTypeFromTypeName(m.group(5));

            //cutting the line to a smaller one containing only the variables and assignments
            line = line.substring(m.end(),line.length());

            String[] line_parts = line.split(Split_Str);

            for(String part:line_parts)
            {
                String[] var_parts = part.split("=");//splitting a part to a var name and perhaps assigned value.
                String var_string = Find(VAR_NAME,var_parts[0]);
                vars.add(new Variable(var_string,type_str));

                if(var_parts.length>1) {//if there's a value assignment:
                    String val_string = Find(VAL,var_parts[1]);
                    VarAssignment assignment = new VarAssignment
                            (line,var_string,val_string);
                    assignments.add(assignment);
                }
            }

            return new VarDeclaration(line,vars,type,assignments);
        }
        else
            return null;
    }

    public ArrayList<Variable> getVars() {
        return vars;
    }

    @Override
    public void is_legal(ArrayList<Variable> scope_vars) throws BadElementException {

        for(Variable var: getVars())
        {
            for (Variable scope_var: scope_vars)
                if(var.getName().equals(scope_var.getName()))
                    throw new BadElementException("two elements with the same name in the same scope");
        }

        for(VarAssignment assign:getAssignments())//making all assignments.
            assign.is_legal(scope_vars);

        for (Variable v:getVars()) //locking all variables in here
            v.Lock();
    }

    public VarType getType() {
        return type;
    }

    public ArrayList<VarAssignment> getAssignments() {
        return assignments;
    }
}
