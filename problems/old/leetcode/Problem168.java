package leetcode;

/**
 * Created by Dennis on 12/1/2017.
 *
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.

 For example:

 1 -> A
 2 -> B
 3 -> C
 ...
 26 -> Z
 27 -> AA
 28 -> AB

 20 +
 */
public class Problem168 {
    public String convertToTitle(int n) {
        int curr = n;
        String result = "";
        while(curr > 0) {
            curr--;
            int value = (curr) % 26;
            result = getLetter(value) + result;
            curr = (curr) / 26;
        }
        return result;
    }

    public String getLetter(int value) {
        char c = (char) ('A' + value);
        return c + "";
    }
}
