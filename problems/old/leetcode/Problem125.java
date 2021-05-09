package leetcode;

import java.util.ArrayList;

/**
 * Created by Dennis on 12/1/2017.
 *
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 For example,
 "A man, a plan, a canal: Panama" is a palindrome.
 "race a car" is not a palindrome.

 Note:
 Have you consider that the string might be empty? This is a good question to ask during an interview.

 For the purpose of this problem, we define empty string as valid palindrome.

 9 minutes - easy
 */
public class Problem125 {
    public boolean isPalindrome(String s) {
        if(s == null || s.isEmpty()) return true;
        ArrayList<Character> characters = new ArrayList<Character>();
        for(char c : s.toLowerCase().toCharArray()) {
            if(Character.isAlphabetic(c) || Character.isDigit(c)) {
                characters.add(c);
            }
        }
        boolean result = true;
        int leftPos = 0;
        int rightPos = characters.size()-1;
        while(leftPos <= rightPos) {
            char leftC = characters.get(leftPos);
            char rightC = characters.get(rightPos);
            if(leftC != rightC) {
                result = false;
                break;
            }

            leftPos++;
            rightPos--;
        }

        return result;
    }
}
