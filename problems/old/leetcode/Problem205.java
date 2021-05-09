package leetcode;

import java.util.*;

/**
 * Created by Dennis on 12/6/2017.
 *
 * Given two strings s and t, determine if they are isomorphic.

 Two strings are isomorphic if the characters in s can be replaced to get t.

 All occurrences of a character must be replaced with another character while preserving the order of characters.
 No two characters may map to the same character but a character may map to itself.

 For example,
 Given "egg", "add", return true.

 Given "foo", "bar", return false.

 Given "paper", "title", return true.

 30 minutes, going wrong direction
 4 minutes, understanding solution
 */
public class Problem205 {
    public boolean isIsomorphic(String s, String t) {
        int[] m1 = new int[256]; // stores most recent position seeing character
        int[] m2 = new int[256];

        for(int i = 0; i < s.length(); i++) {
            if(m1[s.charAt(i)] != m2[t.charAt(i)]) return false;
            m1[s.charAt(i)] = i+1;
            m2[t.charAt(i)] = i+1;
        }
        return true;
    }

}
