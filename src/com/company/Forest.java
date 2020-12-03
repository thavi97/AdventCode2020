package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Forest {
    
    private static int numberOfRows;
    private static int numberOfColumns;
    private static int numberOfRepeats;
    private static final int right = 3;
    private static final int down = 1;

    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println(Arrays.deepToString(readFile()));
        seekThroughMap(duplicateForest(readFile()));
    }

    public static void seekThroughMap(int[][] duplicateMapOfForest){
        int x=0;
        int y=0;
        int counter = 0;
        System.out.println(numberOfRows);
        System.out.println((numberOfColumns*numberOfRepeats)+1);
        while(y<numberOfRows-1){
            x += right;
            y += down;
            if(duplicateMapOfForest[y][x] == 1 ){
                counter++;
                System.out.println(counter);
            }
        }
    }

    public static int[][] duplicateForest(int[][] mapOfForest) throws FileNotFoundException {
        Scanner forest = new Scanner(new File("forest.txt"));
        numberOfRepeats = ((numberOfRows/numberOfColumns)*(right+1));
        int[][] duplicateMapOfForest = new int[numberOfRows][numberOfColumns*numberOfRepeats];
        for (int j = 0; j < numberOfRows; j++) {
            int u=0;
            if (forest.hasNextLine()) {
                char[] nextLine = forest.nextLine().toCharArray();
                for(int i=0; i<numberOfRepeats; i++){
                    for (char location : nextLine) {
                        if(location == ".".charAt(0)){
                            duplicateMapOfForest[j][u] = 0;
                        }else{
                            duplicateMapOfForest[j][u] = 1;
                        }
                        u++;
                        //System.out.print(location);
                    }
                }
                //System.out.println();
            }
            //System.out.print(Arrays.deepToString(duplicateMapOfForest));
        }
        return duplicateMapOfForest;
    }

    public static int[][] readFile() throws FileNotFoundException {
        Scanner forest = new Scanner(new File("forest.txt"));
        Scanner forestCheck = new Scanner(new File("forest.txt"));
        String currentLine = forestCheck.nextLine();
        numberOfColumns = currentLine.length();

        int i=1;
        while(forestCheck.hasNext()){
            forestCheck.nextLine();
            i++;
        }
        numberOfRows = i;

        int[][] mapOfForest = new int[numberOfRows][numberOfColumns];

        for (int j = 0; j < numberOfRows; j++) {
            int u=0;
            if (forest.hasNextLine()) {
                for (char location : forest.nextLine().toCharArray()) {
                    if(location == ".".charAt(0)){
                        mapOfForest[j][u] = 0;
                    }else{
                        mapOfForest[j][u] = 1;
                    }
                    u++;
                    //System.out.print(location);
                }
            }
            //System.out.println();
        }

        if(forest.hasNextLine()) {
            forest.nextLine();
        }

        //System.out.print(Arrays.deepToString(mapOfForest));

        forest.close();
        forestCheck.close();
        return mapOfForest;
    }
}
