package code_elements;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Empty extends NoCode {

    static String CREATE_REGEX = "\\s";
    static CodeElement createFromLine(String line){
        if(CodeElement.check_match(line,CREATE_REGEX)){
            return new Empty();
        }
        else return null;
    }
}
