package oop.ex6.main;

import code_elements.BadElementException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by t8417719 on 12/06/2017.
 */
public abstract class Sjavac {
    final static String LEGAL = "0";
    final static String ILLEGAL = "1";
    final static String ERROR = "2";
    public static void main(String[] args) {

        if(args.length!=1){
            System.out.print('2');
            System.err.println("incorrect number of parameters.");
            return;
        }

        try {
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);
            Program program = new Program(br);
            program.is_legal(new ArrayList<>());
            System.out.print(LEGAL);
        }
        catch(BadElementException e){
            System.err.println(e.getMessage());
            System.out.print(ILLEGAL);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            System.out.print(ERROR);
        }
    }
}
