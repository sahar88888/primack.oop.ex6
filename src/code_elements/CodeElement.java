package code_elements;

import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class CodeElement {
    CodeElement parent;
    String src_str;

    public abstract boolean is_legal();

    static CodeElement createFromLine(BufferedReader f, String line) throws
            IOException, BadElementException{
        CodeElement elem;
        if((elem = Block.createFromLine(f,line))==null){
            if((elem=NoCode.createFromLine(f,line))==null){
                elem = Statement.createFromLine(f,line);
            }
        }
        return elem;
    }

    static boolean check_match(String line, String regex){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        return m.matches();
    }
}
