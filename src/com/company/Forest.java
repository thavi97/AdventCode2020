package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Forest {

    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println(Arrays.deepToString(readFile()));
        readFile();
    }

    public static int[][] readFile() throws FileNotFoundException {
        Scanner forest = new Scanner(new File("forest.txt"));
        Scanner forestCheck = new Scanner(new File("forest.txt"));
        String currentLine = forestCheck.nextLine();
        int numberOfColumns = currentLine.length();

        int i=1;
        while(forestCheck.hasNext()){
            forestCheck.nextLine();
            i++;
        }
        int numberOfRows = i;

        System.out.println(numberOfColumns);
        System.out.println(numberOfRows);
        System.out.println(numberOfRows/numberOfColumns);

        int[][] mapOfForest = new int[numberOfRows][numberOfColumns];

        System.out.println("test");
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

        System.out.print(Arrays.deepToString(mapOfForest));

        forest.close();
        forestCheck.close();
        return mapOfForest;
    }
}
