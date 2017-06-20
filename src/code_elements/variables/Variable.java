package code_elements.variables;

import code_elements.BadElementException;
import code_elements.CodeElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static oop.Custom_Regexes.*;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Variable {

    public enum VarType{BOOLEAN,STRING,INT,DOUBLE,CHAR}
    String name;
    String value;
    VarType type;
    boolean is_final = false;
    boolean initialized;

    public Variable(String name, String type_String) throws BadElementException {
        this.name = name;

        Pattern p = Pattern.compile(VAR_NAME);
        Matcher m = p.matcher(type_String);
        m.matches();
        if (m.group(1) != null)//first group contains possibly 'final' modifier
            this.is_final = true;
        this.type = GetValueTypeFromName(type_String);
    }

    public String getName() {
        return name;
    }

    public VarType getType() {
        return type;
    }

    public boolean is_final() {
        return is_final;
    }

    public boolean isInitialized() {
        return initialized;
    }

    /**
     * assigning a value to the variable.
     *
     */
    public void assign(String value) throws BadElementException
    {
        this.initialized=true;

        if(is_final())
            throw new BadElementException();//final variables can't be assigned!

        if (!value.matches(GetRegexFromVarType(type)))
            throw new BadElementException();// type not matching.

        this.value = value;

    }

    public String getValue() throws BadElementException {
        if(!initialized) // if the element has no value.
            throw new BadElementException();

        return value;
    }
}
