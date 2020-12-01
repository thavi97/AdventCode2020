package com.company;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        find2020FromTwoNumbers(readFile());
        find2020FromThreeNumbers(readFile());
    }

    /*
        A nested for loop that sifts through the ArrayList to find the 2 numbers that add up to make 2020.
        It then calls the multiplication function to multiply the numbers together.
     */
    public static int find2020FromTwoNumbers(ArrayList numbers){
        int firstNumber = 0;
        int secondNumber = 0;
        for (int i=0; i<numbers.size()-1; i++){
            for (int u=0; u<numbers.size()-1; u++){
                if(Integer.parseInt((String)numbers.get(i))+Integer.parseInt((String)numbers.get(u)) == 2020){
                    firstNumber = Integer.parseInt((String)numbers.get(i));
                    secondNumber = Integer.parseInt((String)numbers.get(u));
                }
            }
        }
        System.out.println(firstNumber+"x"+secondNumber+"="+multiplyTwoNumbers(firstNumber, secondNumber));
        int finalAnswer=multiplyTwoNumbers(firstNumber, secondNumber);
        return finalAnswer;
    }

    /*
        A nested for loop that sifts through the ArrayList to find the 3 numbers that add up to make 2020.
        It then calls the multiplication function to multiply the numbers together.
     */
    public static int find2020FromThreeNumbers(ArrayList numbers){
        int firstNumber = 0;
        int secondNumber = 0;
        int thirdNumber = 0;
        for (int i=0; i<numbers.size()-1; i++){
            for (int u=0; u<numbers.size()-1; u++){
                for(int j=0; j<numbers.size()-1; j++) {
                    if(Integer.parseInt((String)numbers.get(i))+Integer.parseInt((String)numbers.get(u))+Integer.parseInt((String)numbers.get(j)) == 2020){
                        firstNumber = Integer.parseInt((String)numbers.get(i));
                        secondNumber = Integer.parseInt((String)numbers.get(u));
                        thirdNumber = Integer.parseInt((String)numbers.get(j));
                    }
                }
            }
        }
        System.out.println(firstNumber+"x"+secondNumber+"x"+thirdNumber+"="+multiplyThreeNumbers(firstNumber, secondNumber, thirdNumber));
        int finalAnswer=multiplyThreeNumbers(firstNumber, secondNumber, thirdNumber);
        return finalAnswer;
    }

    /*
        This method multiplies the two numbers that add up to 2020
     */
    public static int multiplyTwoNumbers(int firstNumber, int secondNumber){
        int finalAnswer = firstNumber * secondNumber;
        return finalAnswer;
    }

    /*
        This method multiplies the three numbers that add up to 2020
    */
    public static int multiplyThreeNumbers(int firstNumber, int secondNumber, int thirdNumber){
        int finalAnswer = firstNumber * secondNumber * thirdNumber;
        return finalAnswer;
    }

    /*
        Reads a txt file that contains a list of numbers.
        Adds these numbers into an ArrayList.
    */
    public static ArrayList readFile(){
        List<String> numbers = Collections.emptyList();
        try {
            numbers = Files.readAllLines(Paths.get("numbers.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (ArrayList)numbers;
    }
}