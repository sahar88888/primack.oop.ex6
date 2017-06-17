package code_elements;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class IfCondition extends Condition {

    static String CREATE_REGEX = "if.*";

    static CodeElement createFromLine(String line){
        if(CodeElement.check_match(line,CREATE_REGEX)){
            return new IfCondition();
        }
        else return null;
    }
}
