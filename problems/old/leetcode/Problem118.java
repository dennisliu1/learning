package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 11/28/2017.
 * Given numRows, generate the first numRows of Pascal's triangle.

 For example, given numRows = 5,
 Return

 [
 [1],
 [1,1],
 [1,2,1],
 [1,3,3,1],
 [1,4,6,4,1]
 ]

 15 minutes - thought incorrectly, took easy way out
 */
public class Problem118 {
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> prevRow = null;
        for(int i = 0; i < numRows; i++) {
            ArrayList<Integer> rowResult = new ArrayList<>();
            if(i == 0) {
                rowResult.add(1);
            }
            else {
                rowResult.add(1);
                for(int j = 0; j < prevRow.size()-1; j++) {
                    int left = prevRow.get(j);
                    int right = prevRow.get(j+1);
                    int sum = left + right;
                    rowResult.add(sum);
                }
                rowResult.add(1);
            }
            result.add(rowResult);
            prevRow = rowResult;
        }
        return result;
    }
}
