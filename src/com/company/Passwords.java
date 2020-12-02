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
        System.out.println("Size of list: " + splitObject(readFile()).size());
    }

    /*
        Split the object to separate the number range, the letter, and the password.
        Then check using Regex whether the password is valid.
     */
    public static ArrayList<String> splitObject(ArrayList passwords){
        String regex = "";
        String letter = "";
        String password = "";
        validPasswords = new ArrayList<String>();
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
            String strippedPassword = stripPassword(password, letter);
            Pattern p = Pattern.compile(letter+"{"+regex+"}");
            Matcher m = p.matcher(strippedPassword);
            boolean b = m.matches();
            if(b){
                //System.out.println(password);
                validPasswords.add(password);
            }

        }

        return validPasswords;
    }

    /*
        Strips the password so it only leaves behind the letters required.
        This is because regex in Java searches for multiple occurrences of a letter in a sequence.
     */
    public static String stripPassword(String password, String letter){
        String matchedLetters = "";
        for (char character: password.toCharArray()) {
            if(character==letter.charAt(0)){
                matchedLetters += character;
            }
        }
        return matchedLetters;
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
