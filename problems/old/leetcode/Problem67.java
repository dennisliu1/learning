package leetcode;

/**
 * Created by Dennis on 11/18/2017.
 *
 * Given two binary strings, return their sum (also a binary string).

 For example,
 a = "11"
 b = "1"
 Return "100".

 16 minutes, mostly just got hung up on leading zeros
 */
public class Problem67 {
    public String addBinary(String a, String b) {
        int endPosA = 0;
        int endPosB = 0;

        int posA = a.length()-1;
        int posB = b.length()-1;
        char digitA;
        char digitB;
        String result = "";
        int count = 0;
        boolean carryOver = false;
        int digit = 0;

        while(posA >= endPosA || posB >= endPosB) {
            digitA = (posA >= 0) ? a.charAt(posA) : '0';
            digitB = (posB >= 0) ? b.charAt(posB) : '0';

            count = 0;
            if(digitA == '1') count++;
            if(digitB == '1') count++;
            if(carryOver) {
                count++;
                carryOver = false;
            }

            if(count == 3) {
                digit = 1;
                carryOver = true;
            }
            else if(count == 2) {
                digit = 0;
                carryOver = true;
            }
            else if(count == 1) digit = 1;
            else digit = 0;

            result = digit + result;

            if(posA >= 0) posA--;
            if(posB >= 0) posB--;

        }
        if(carryOver) result = '1' + result;
        return result;
    }
}
