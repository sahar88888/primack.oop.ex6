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
    private String name;
    private String value;
    private VarType type;
    boolean is_final = false;
    boolean initialized = false;

    public Variable(String name, String type_String) throws BadElementException {
        this.name = name;

        Pattern p = Pattern.compile(VAR_NAME);
        Matcher m = p.matcher(type_String);
        m.matches();
        if (m.group(1) != null)//first group contains possibly 'final' modifier
            this.is_final = true;
        this.type = GetValueTypeFromName(type_String);
    }

    /**
     *creating a variable from a given one.
     * @param other creating a variable
     * @throws BadElementException
     */
    public Variable( Variable other) throws BadElementException
    {
        this.name = other.getName();
        this.type = other.getType();
        this.value = other.getValue();
        this.is_final = other.is_final();
        this.initialized = other.isInitialized();

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

    /**
     * generates a copy of the
     * @return
     * @throws BadElementException
     */
    public Variable getCopy() throws BadElementException
    {
        return new Variable(this);
    }

    public boolean isBoolean(){
        if(type==VarType.BOOLEAN || type==VarType.DOUBLE || type==VarType.INT){
            return true;
        }
        return false;
    }
}
