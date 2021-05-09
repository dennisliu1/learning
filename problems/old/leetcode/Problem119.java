package leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 11/28/2017.
 *
 * Given an index k, return the kth row of the Pascal's triangle.

 For example, given k = 3,
 Return [1,3,3,1].

 Note:
 Could you optimize your algorithm to use only O(k) extra space?

 8 minutes
 */
public class Problem119 {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> currRow = new ArrayList<>();
        ArrayList<Integer> prevRow = null;
        int index = 0;
        while(index <= rowIndex) {
            if(index == 0) {
                currRow.add(1);
            }
            else {
                currRow.add(1);
                for(int i = 0; i < prevRow.size()-1; i++) {
                    int left = prevRow.get(i);
                    int right = prevRow.get(i+1);
                    int sum = left + right;
                    currRow.add(sum);
                }
                currRow.add(1);
            }
            prevRow = currRow;
            currRow = new ArrayList<>();
            index++;
        }
        return prevRow;
    }
}
