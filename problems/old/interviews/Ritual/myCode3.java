package interviews.Ritual;/* package whatever; // don't place package name! */
/*
YOU MAY NOT CONVERT THE ARRAY INTO A NUMBER AND THEN BACK INTO AN ARRAY.

THERE MAY BE NO VALUE STORED IN ANY ARRAY GREATER THAN NINE (9).

Implement a method that, given an "ArrayInteger", multiplies another "ArrayInteger". You may use whatever array class you like.

Please use the following method signature

public static int[] multiplyArrayInt(int[] arrayInt, int[] otherInt);
An integer value is represented in an array such that the integer may be any size. For example, the value 123 (one hundred twenty three) would be stored as follows:

arrayInt[0] = 3;

arrayInt[1] = 2;

arrayInt[2] = 1;
Given the inputs 123 and 3 the result would be:

arrayInt[0] = 9;

arrayInt[1] = 6;

arrayInt[2] = 3;
YOU MAY NOT CONVERT AN ARRAY INTO A NUMBER AND THEN BACK INTO AN ARRAY. THERE MAY BE NO VALUE STORED IN ANY ARRAY GREATER THAN NINE (9).

 */

import java.io.*;
import java.util.*;

//////////////////////////////////////////////////////////////////////
//////// READ THE ENTIRE QUESTION BEFORE STARTING             ////////
////////                                                      ////////
//////// Ensure that your code does NOT print out debug data  ////////
//////// or it will interfere with the automatic scoring.     ////////
//////////////////////////////////////////////////////////////////////

class myCode {
    public static ArrayList<Integer> multArrayInt(ArrayList<Integer> array, ArrayList<Integer> other) {
        ////////////////////////
        //// YOUR CODE HERE ////
        ////////////////////////
        // Implement me.

        // 1. multiply per digit
        ArrayList<ArrayList<Integer>> digitResults = new ArrayList<ArrayList<Integer>>();
        for(int j = 0; j < other.size(); j++) {
            ArrayList<Integer> digitResult = new ArrayList<Integer>();
            for(int p = 0; p < j; p++) {
                digitResult.add(0);
            }

            int multiplyDigit = other.get(j);
            int carryOver = 0;
            for(int i = 0; i < array.size(); i++) {
                int digit = array.get(i);

                int result = digit * multiplyDigit;
                result += carryOver;
                carryOver = 0;

                int newDigit = result;
                if(result > 9) {
                    newDigit = result % 10;
                    carryOver = (result - newDigit) / 10;
                }
                digitResult.add(newDigit);
            }
            if(carryOver > 0) digitResult.add(carryOver);
//            System.out.printf("%d: ", j);
//            printAnswer(digitResult);



            digitResults.add(digitResult);
        }

        // 2. add digitwise results
        ArrayList<Integer> resultList = digitResults.get(0);
        for(int i = 1; i < digitResults.size(); i++) {
            resultList = addArrayInt(resultList, digitResults.get(i));
        }
        return resultList;
    }

    public static ArrayList<Integer> addArrayInt(ArrayList<Integer> array, ArrayList<Integer> other) {
        ////////////////////////
        //// YOUR CODE HERE ////
        ////////////////////////
        // Implement me.
        ArrayList<Integer> result = new ArrayList<Integer>();
        int digitA, digitB;
        boolean carryOver = false;
        int i = 0, j = 0;
        int addition, newDigit;
        while(i < array.size() || j < other.size()) {
            digitA = getDigit(array, i);
            digitB = getDigit(other, j);

            addition = digitA + digitB;
            if(carryOver) {
                addition = addition + 1;
                carryOver = false;
            }

            if(addition > 9) {
                newDigit = addition % 10;
                carryOver = true;
            }
            else {
                newDigit = addition;
            }

            result.add(newDigit);

            i++;
            j++;
        }

        if(carryOver) result.add(1);

        return result;
    }

    public static int getDigit(ArrayList<Integer> array, int index) {
        if(index >= array.size()) return 0;
        else return array.get(index);
    }

    /*************************************************************************
     * DO NOT EDIT - this is used in our automatic assessment.                *
     * Changing or altering this in any way will void your application.       *
     *************************************************************************/
    public static void printAnswer(ArrayList<Integer> answer) {
        // MSD is at the end. Print backwards.
        for (int i = answer.size() - 1; i >= 0; i--) {
            System.out.print(answer.get(i));
        }
        System.out.println("");
    }

    /*********************
     * It is highly recommended you
     * DO NOT change the main function.
     *********************/
    public static void main (String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while (true) {
            input = br.readLine();
            if (input == null) {
                break;
            }
            ArrayList<Integer> array = new ArrayList<Integer>();
            ArrayList<Integer> other = new ArrayList<Integer>();
            boolean doneFirst = false;
            for(int i = 0; i < input.length(); i++){
                String sub = input.substring(i, i + 1);
                if (!sub.equals(";") && !doneFirst) {
                    array.add(0, new Integer(sub));
                    continue;
                } else if (sub.equals(";")) {
                    doneFirst = true;
                    continue;
                }
                other.add(0, new Integer(sub));
            }
            // DO NOT EDIT
            ArrayList<Integer> answer = multArrayInt(array, other);
            printAnswer(answer);
            // END - DO NOT EDIT
        }
    }
}
