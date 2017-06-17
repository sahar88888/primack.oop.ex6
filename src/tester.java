/**
 * Created by t8417719 on 13/06/2017.
 */

import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class tester {

    public static void main(String[] args) {
        Pattern p = Pattern.compile("[\\s]");
        Matcher m = p.matcher("bla");
        System.out.println(m.matches());
    }





}
