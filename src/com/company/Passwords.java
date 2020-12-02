package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passwords {

    private static ArrayList<String> validPasswords;

    public static void main(String[] args) {
        System.out.println(splitObject(readFile()));
    }

    public static String splitObject(ArrayList passwords){
        String regex = "";
        String letter = "";
        String password = "";
        for (int i=0; i<passwords.size(); i++){
            String singlePassword = (String)passwords.get(i);
            String[] strArray =  singlePassword.split(" ");
            for(int j=0; j<strArray.length; j++){
                if (j == 0) {
                    regex = strArray[j].replace("-", ",");
                    //System.out.println("Password " + i + " " + regex);
                }else if(j == 1){
                    letter = strArray[j].replace(":", "");
                    //System.out.println("Password " + i + " " + letter);
                }else{
                    password = strArray[j].replace(":", "");
                    //System.out.println("Password " + i + " " + password);
                }
            }
            password = password.replace(":", "");
            Pattern p = Pattern.compile(letter+"{"+regex+"}");
            Matcher m = p.matcher(password);
            boolean b = m.matches();
            if(b){
                System.out.println(password);
            }
        }


        return (String)passwords.get(0);
    }

    public static ArrayList readFile(){
        List<String> passwords = Collections.emptyList();
        try {
            passwords = Files.readAllLines(Paths.get("passwords.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (ArrayList)passwords;
    }
}
