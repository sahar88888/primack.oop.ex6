package code_elements;

import static oop.Custom_Regexes.CheckMatch;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Empty extends NoCode {

    static String CREATE_REGEX = "\\s*";

    protected Empty(String line){
        super(line);
    }

    static public Empty createFromLine(String line){
        if(CheckMatch(line,CREATE_REGEX)){
            return new Empty(line);
        }
        else return null;
    }
}
