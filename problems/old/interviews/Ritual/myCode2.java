package interviews.Ritual;/* package whatever; // don't place package name! */

/*
YOU MAY NOT CONVERT THE ARRAY INTO A NUMBER AND THEN BACK INTO AN ARRAY.

THERE MAY BE NO VALUE STORED IN ANY ARRAY GREATER THAN NINE (9).

Implement a method that, given an "ArrayInteger", adds another "ArrayInteger". You may use whatever array class you like.

Please use the following method signature

public static int[] addArrayInt(int[] arrayInt, int[] otherInt);
An integer value is represented in an array such that the integer may be any size. For example, the value 123 (one hundred twenty three) would be stored as follows:

arrayInt[0] = 3;

arrayInt[1] = 2;

arrayInt[2] = 1;
Given the inputs 123 and 456 the result would be:

arrayInt[0] = 9;

arrayInt[1] = 7;

arrayInt[2] = 5;
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

class myCode2 {
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
            ArrayList<Integer> answer = addArrayInt(array, other);
            printAnswer(answer);
            // END - DO NOT EDIT
        }
    }
}
