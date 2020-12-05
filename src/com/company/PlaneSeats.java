package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlaneSeats {

    public static void main(String[] args) {
        //System.out.println(findHighestSeatId(readFile()));
        System.out.println(findMySeatId(readFile()));
    }

    public static int findMySeatId(ArrayList planeSeats){
        int mySeat = 0;
        int[] seatIds = new int[planeSeats.size()];
        int i=0;
        for (Object planeSeat: planeSeats) {
            String stringPlaneSeat = "";
            int rowNumber = findRowNumber(planeSeat);
            int columnNumber = findColumnNumber(planeSeat);
            seatIds[i]=(rowNumber*8)+columnNumber;
            i++;
        }
        Arrays.sort(seatIds);
        for (int j=0; j<seatIds.length-1; j++) {
            if(seatIds[j]+1!=seatIds[j+1]){
                mySeat = seatIds[j]+1;
            }
        }
        System.out.println(Arrays.toString(seatIds));
        return mySeat;
    }

    public static int findHighestSeatId(ArrayList planeSeats){
        int highestSeatId = 0;
        for (Object planeSeat: planeSeats) {
            int rowNumber = findRowNumber(planeSeat);
            int columnNumber = findColumnNumber(planeSeat);
            int seatId = (rowNumber*8)+columnNumber;
            if(seatId>highestSeatId){
                highestSeatId=seatId;
            }
        }

        return highestSeatId;
    }

    public static int findRowNumber(Object planeSeat){
        int rowNumber = findBinary((String)planeSeat, 0, 7, "F");
        return rowNumber;
    }

    public static int findColumnNumber(Object planeSeat){
        int columnNumber = findBinary((String)planeSeat, 7, 10, "L");
        return columnNumber;
    }

    public static int findBinary(String stringPlaneSeat, int lowerIValue, int upperIValue, String charValue){
        String seatNumberInBinary = "";
        for (int i=lowerIValue; i<upperIValue; i++){
            if(stringPlaneSeat.toCharArray()[i]==charValue.charAt(0)){
                seatNumberInBinary += "0";
            }else{
                seatNumberInBinary += "1";
            }
        }
        return convertBinary(seatNumberInBinary);
    }

    public static int convertBinary(String rowNumberInBinary){
        int decimal = Integer.parseInt(rowNumberInBinary,2);
        return decimal;
    }

    public static ArrayList readFile(){
        List<String> planeSeats = Collections.emptyList();
        try {
            planeSeats = Files.readAllLines(Paths.get("planeseats.txt"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (ArrayList)planeSeats;
    }
}