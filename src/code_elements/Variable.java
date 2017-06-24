package code_elements;

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
    private boolean is_final = false;
    private boolean initialized = false;

    /**
     * an attribute tightly tied to the final attribute: a final variable will be locked after the line in which it was
     * declared was fully processed.
     */
    private boolean locked = false;

    public Variable(String name, String type_String) throws BadElementException {
        this.name = name;

        Pattern p = Pattern.compile(VAR_TYPE);
        Matcher m = p.matcher(type_String);
        m.matches();
        if (m.group(2) != null)//first group contains possibly 'final' modifier
            this.is_final = true;
        this.type = GetValueTypeFromTypeName(m.group(5));
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

        if(locked)
            throw new BadElementException("assigning value to final var");

        if (!value.matches(GetRegexFromVarType(type)))
            throw new BadElementException("value assigned doesn't match variable type.");// type not matching.

        this.value = value;

    }

    public void assign(Variable other_var) throws BadElementException
    {
        this.initialized=true;

        if(locked)
            throw new BadElementException("assigning value to final var");

        String other_type_example = ExampleString(other_var.getType());
        if (!other_type_example.matches(GetRegexFromVarType(this.type)))
            throw new BadElementException("value assigned doesn't match variable type.");// type not matching.

        this.value = other_var.getValue();
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

    /**
     * 'locking' a variable if it's final - will be effective after the variable is declared.
     * @throws BadElementException
     */
    public void Lock() throws BadElementException
    {
        if (is_final())
        {
            if (!initialized)
                throw new BadElementException("Uninitialized final element");

            this.locked=true;
        }
    }



}
