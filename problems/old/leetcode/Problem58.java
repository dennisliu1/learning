package leetcode;

/**
 * Created by Dennis on 11/18/2017.
 *
 Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

 If the last word does not exist, return 0.

 Note: A word is defined as a character sequence consists of non-space characters only.

 Example:

 Input: "Hello World"
 Output: 5

 8 minutes
 */
public class Problem58 {
    public int lengthOfLastWord(String s) {
        if(s.length() <= 0) return 0;

        int len = 0;
        int i = s.length()-1;
        char c = s.charAt(i);
        while(c == ' ' && i > 0) {// skip whitespace
            i--;
            c = (i >= 0) ? s.charAt(i) : ' ';
        }

        while(c != ' ' && i > 0) {
            len++;
            i--;
            c = (i >= 0) ? s.charAt(i) : ' ';
        }
        return len;
    }
}
