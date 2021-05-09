package leetcode;

/**
 * Created by Dennis on 11/18/2017.
 * The count-and-say sequence is the sequence of integers with the first five terms as following:

 1.     1
 2.     11
 3.     21
 4.     1211
 5.     111221
 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.
 Given an integer n, generate the nth term of the count-and-say sequence.

 Note: Each term of the sequence of integers will be represented as a string.

 Example 1:

 Input: 1
 Output: "1"
 Example 2:

 Input: 4
 Output: "1211"

 37 minutes

 */
public class Problem38 {
    public String countAndSay(int n) {
        String result;
        String term = "1";
        for(int i = 0; i < n-1; i++) {
            term = generate(term);
            System.out.println(term);
        }
        result = term;
        return result;
    }

    public String generate(String term) {
        String result = "";

        int i = 0;
        int digit = term.charAt(i)-'0';
        int prevDigit = -1;
        int countDigits = -1;
        while(digit != 0 && i < term.length()) {
            digit = term.charAt(i)-'0';

            if(prevDigit == -1) {
                prevDigit = digit;
                countDigits = 1;
            }
            else if(prevDigit == digit) {
                countDigits++;
            }
            else {
                result = buildResult(result, prevDigit, countDigits);
                countDigits = 1;
                prevDigit = digit;
            }

            i++;
        }
        result = buildResult(result, prevDigit, countDigits);
        return result;
    }

    public String buildResult(String result, int prevDigit, int countDigits) {
        return result + Integer.toString(countDigits)+ Integer.toString(prevDigit);
    }

    public static void main(String[] args) {
        Problem38 p = new Problem38();
        System.out.println("result: "+ p.countAndSay(3));
    }
}
