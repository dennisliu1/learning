package leetcode;

/**
 * Created by Dennis on 12/1/2017.
 *
 * Related to question Excel Sheet Column Title

 Given a column title as appear in an Excel sheet, return its corresponding column number.

 For example:

 A -> 1
 B -> 2
 C -> 3
 ...
 Z -> 26
 AA -> 27
 AB -> 28

 5 minutes - clearly learning from doing these exercises
 */
public class Problem171 {
    public int titleToNumber(String s) {
        if(s == null || s.isEmpty()) return 0;

        int result = 0;
        char[] letters = s.toCharArray();
        int exp = 0;
        for(int i = letters.length-1; i >= 0; i--) {
            char letter = letters[i];
            int digit = letter - 'A' + 1;
            int value = (int) (digit * Math.pow(26, exp));
            result += value;
            exp++;
        }
        return result;
    }
}
