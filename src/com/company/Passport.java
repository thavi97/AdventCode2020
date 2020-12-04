package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Passport {

    public static void main(String[] args) throws FileNotFoundException {
        readFile();
    }

    public static void readFile() throws FileNotFoundException {
        String listOfPassports = "";
        Scanner passports = new Scanner(new File("passports.txt"));
        while(passports.hasNext()){
            listOfPassports += passports.nextLine() + "\n";
        }
        listOfPassports += "\n ";

        String onePassport = "";
        String[] singleLine= listOfPassports.split("\n");
        ArrayList<String> arrayListOfPassports = new ArrayList();
        int i=0;
        for (String line: singleLine) {
            if(line != ""){
                onePassport += line + " ";
            }else {

                arrayListOfPassports.add(onePassport);
                onePassport = "";
                i++;
            }
        }

        int validPassports = 0;
        for (String passport: arrayListOfPassports) {
            int counter = 0;
            String[] keyValuePairs = passport.split(" ");
            for (String keyValuePair:keyValuePairs) {
                String key = keyValuePair.split(":")[0];
                String value = keyValuePair.split(":")[1];
                switch (key){
                    case "byr":
                        if(Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002) {
                            counter++;
                        }
                        break;
                    case "iyr":
                        if(Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020) {
                            counter++;
                        }
                        break;
                    case "eyr":
                        if(Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030) {
                            counter++;
                        }
                        break;
                    case "hgt":
                        if(value.contains("cm")) {
                            int height = Integer.parseInt(value.split("cm")[0]);
                            if(height >= 150 && height <= 193) {
                                counter++;
                            }
                        } else if (value.contains("in")) {
                            int height = Integer.parseInt(value.split("in")[0]);
                            if(height >= 59 && height <= 76) {
                                counter++;
                            }
                        }
                        break;
                    case "hcl":
                        if(value.matches("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$")) {
                            counter++;
                        }
                        break;
                    case "ecl":
                        if(value.contains("amb")||value.contains("blu")||value.contains("brn")||value.contains("gry")||value.contains("grn")||value.contains("hzl")||value.contains("oth")) {
                            counter++;
                        }
                        break;
                    case "pid":
                        if(value.length()==9){
                            counter++;
                        }
                        break;
                }
            }
            if(counter>=7){
                validPassports++;
            }
        }
        System.out.println(validPassports);
    }

}
