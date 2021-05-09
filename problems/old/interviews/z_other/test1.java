package interviews;/* package whatever; // don't place package name! */
/*

YOU MAY NOT CONVERT THE ARRAY INTO A NUMBER AND THEN BACK INTO AN ARRAY. 

THERE MAY BE NO VALUE STORED IN ANY ARRAY GREATER THAN NINE (9).

An "Array Integer" is an integer represented by an array, where each digit of the integer is an element in the array. For example, the value 123 (one hundred twenty three) would be stored as follows, note the least significant digit is in the zero-th position.

arrayInt[0] = 3;

arrayInt[1] = 2;

arrayInt[2] = 1;
Implement a method that, given an "ArrayInteger", increments the value by one.

Please use the following method signature

public static int[] incrementArrayInt(int[] arrayInt);
YOU MAY NOT CONVERT THE ARRAY INTO A NUMBER AND THEN BACK INTO AN ARRAY. THERE MAY BE NO VALUE STORED IN ANY ARRAY GREATER THAN NINE (9).

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
  public static ArrayList<Integer> incrementArrayInt(ArrayList<Integer> array) {
    ////////////////////////
    //// YOUR CODE HERE ////
    ////////////////////////
    // Implement me.

    

    return array;
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
      for(int i = 0; i < input.length(); i++){
        array.add(0, new Integer(input.substring(i, i + 1)));
      }
      // DO NOT EDIT
      ArrayList<Integer> answer = incrementArrayInt(array);
      printAnswer(answer);
      // END - DO NOT EDIT
    }
  }
}
