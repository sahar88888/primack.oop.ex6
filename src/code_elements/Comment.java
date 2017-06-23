package code_elements;

import static oop.Custom_Regexes.CheckMatch;

/**
 * Created by t8417719 on 12/06/2017.
 */
public class Comment extends NoCode {
    static String CREATE_REGEX = "^//.*";

    protected Comment(String line){
        super(line);
    }
    static public Comment createFromLine(String line) {
        if (CheckMatch(line, CREATE_REGEX)) {
            return new Comment(line);
        }
        else return null;
    }
}
