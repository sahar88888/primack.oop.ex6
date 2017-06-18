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
    CodeElement parent;
    String src_str;

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
     * @param line string to check.
     * @param regex regex to match.
     * @return whether the whole string matches the regex.
     */
    static boolean check_match(String line, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        return m.matches();
    }


    /**
     * Checks whether all variable/method names exists in the currect
     * context, in this codeElement.
     * @return
     */
    public abstract void is_legal(ArrayList<Variable> scope_vars) throws
            BadElementException;

}
