package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public abstract class Questionnaire implements Set{

    private static ArrayList<String> listOfDuplicates;

    public static void main(String[] args) throws FileNotFoundException {
        listOfDuplicates = new ArrayList<String>();
        System.out.println(countAnswers(cleanUpUniqueListOfAnswers(createUniqueListOfAnswers(cleanUpAnswerList(readFile())))));
        //countAnswers(cleanUpUniqueListOfAnswers(createUniqueListOfAnswers(cleanUpAnswerList(readFile()))));
        //System.out.println(listOfDuplicates);
        //System.out.println(countAnswers(cleanUpDuplicateListOfAnswers(listOfDuplicates)));
    }

    public static ArrayList<String> readFile() throws FileNotFoundException {
        String listOfAnswers = "";
        Scanner answers = new Scanner(new File("question_answers.txt"));
        while (answers.hasNext()) {
            listOfAnswers += answers.nextLine() + "\n";
        }
        listOfAnswers += "\n ";

        String oneAnswerSet = "";
        String[] singleLine = listOfAnswers.split("\n");
        ArrayList<String> arrayListOfAnswers = new ArrayList();
        int i = 0;
        int j = 0;
        for (String line : singleLine) {
            if (line != "") {
                oneAnswerSet += line + " ";
                j++;
            } else {
                if(j==1){
                    oneAnswerSet += "! ";
                }
                arrayListOfAnswers.add(oneAnswerSet);
                oneAnswerSet = "";
                j=0;
                i++;
            }
        }
        //System.out.println(arrayListOfAnswers);
        return arrayListOfAnswers;
    }

    public static ArrayList<String> cleanUpAnswerList(ArrayList<String> arrayListOfAnswers){
        ArrayList<String> cleanedArrayListOfAnswers = new ArrayList<String>();
        for (String answerSet:arrayListOfAnswers) {
            cleanedArrayListOfAnswers.add(answerSet.replace(" ",""));
        }
        //System.out.println(cleanedArrayListOfAnswers);
        return cleanedArrayListOfAnswers;
    }

    public static ArrayList<String> createUniqueListOfAnswers(ArrayList<String> cleanedArrayListOfAnswers){
        ArrayList<String> uniqueAnswerList = new ArrayList<String>();
        for (String cleanedAnswerList:cleanedArrayListOfAnswers) {
            String duplicateAnswer = "";
            Set uniqueSetOfAnswerLists = new HashSet();
            char[] answerListChar = cleanedAnswerList.toCharArray();
            for (char answerChar:answerListChar) {
                String answerListToString = new String(answerListChar);
                if(answerListToString.contains("!") && answerListChar.toString() != answerListToString){
                    //System.out.println(answerListToString);
                    duplicateAnswer=answerListToString;
                }else if(!uniqueSetOfAnswerLists.add(answerChar)){
                    duplicateAnswer += answerChar;
                }
                uniqueSetOfAnswerLists.add(answerChar);
            }
            if(duplicateAnswer!=""){
                listOfDuplicates.add(duplicateAnswer);
                //System.out.println(duplicateAnswer);
            }
            uniqueAnswerList.add(uniqueSetOfAnswerLists.toString());
        }

        return uniqueAnswerList;
    }

    public static ArrayList<String> cleanUpUniqueListOfAnswers(ArrayList<String> uniqueAnswerList){
        ArrayList<String> cleanedUniqueAnswerList = new ArrayList<String>();
        for (String answerSet:uniqueAnswerList) {
            cleanedUniqueAnswerList.add(answerSet.replaceAll("[^a-z]", ""));
        }
        return cleanedUniqueAnswerList;
    }

    public static ArrayList<String> cleanUpDuplicateListOfAnswers(ArrayList<String> listOfDuplicates){
        ArrayList<String> uniqueDuplicateList = new ArrayList<String>();
        ArrayList<String> cleanedDuplicateList = new ArrayList<String>();
        for (String duplicate:listOfDuplicates) {
            Set uniqueSetOfDuplicateLists = new HashSet();
            if(duplicate.contains("!")){
                System.out.println("OMG HI");
                String replacedDuplicate = duplicate.replace("!", "");
                cleanedDuplicateList.add(replacedDuplicate);
            }else {
                char[] duplicateListChar = duplicate.toCharArray();
                for (char duplicateChar : duplicateListChar) {
                    uniqueSetOfDuplicateLists.add(duplicateChar);
                }
                uniqueDuplicateList.add(uniqueSetOfDuplicateLists.toString());
                //System.out.println(uniqueSetOfDuplicateLists);
            }
        }

        ArrayList<String> cleanedUpList = cleanUpUniqueListOfAnswers(uniqueDuplicateList);
        for (String list:cleanedUpList) {
            cleanedDuplicateList.add(list);
        }
        System.out.println(cleanedDuplicateList);
        for (String bob:cleanedDuplicateList
             ) {
            System.out.println(bob);
        }
        System.out.println("END");

        return cleanedDuplicateList;
    }

    public static int countAnswers(ArrayList<String> cleanedUniqueAnswerList){
        int sumOfAnswers = 0;
        for (String answerSet:cleanedUniqueAnswerList) {
            int sizeOfAnswerSet = answerSet.length();
            sumOfAnswers += sizeOfAnswerSet;
        }
        return sumOfAnswers;
    }
}
