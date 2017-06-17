package code_elements;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class CodeElement {
    CodeElement parent;
    String src_str;
    CodeElement(Reader f){

    }
    public abstract boolean is_legal();

    static CodeElement createFromLine(File f){
        CodeElement elem;
        String line = f.read
        if((elem = Block.createFromLine(line))==null){
            if((elem=NoCode.createFromLine(line))==null){
                elem = Statement.createFromLine(line);
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
