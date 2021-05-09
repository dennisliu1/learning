import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int Q = in.nextInt();
        for(int a0 = 0; a0 < Q; a0++){
            int n = in.nextInt();
            String b = in.next();

            // do a check of the possibility of an answer
            // do a count of each ladybug type and blank
			char[] ladybugs = b.toCharArray();
			if(isExistPossibleSolution(ladybugs) || isAllLadybugsHappy(ladybugs)) System.out.println("YES");
			else System.out.println("NO");
        }
    }
	
	public static boolean isAllLadybugsHappy(char[] ladybugs) {
		boolean result = true;
		char current = ladybugs[0];
		int adjacentCount = 0;
		for (int i = 1; i < ladybugs.length; i++) {
			char nextLadybug = ladybugs[i];
			if(current == nextLadybug) adjacentCount++;
			else {
				if(nextLadybug == '_');
				else if(adjacentCount == 0) {
					result = false;
					break;
				}
				else {
					adjacentCount = 0;
				}
			}
			
			current = nextLadybug;
		}
		return result;
    }

    public static boolean isExistPossibleSolution(char[] ladybugs) {
    	int[] ladybugsCount = new int[27];
		for (int i = 0; i < ladybugs.length; i++) {
			int type = Math.min(ladybugs[i] - 'A', 26);
			ladybugsCount[type]++;
		}

		// need at least one blank to move ladubugs around
		boolean hasAtLeastOneBlank = ladybugsCount[26] >= 1;
		boolean isPossible = true;
		for (int i = 0; i < ladybugsCount.length-1; i++) {// each ladybug type needs more than 1
			if(ladybugsCount[i] == 1) {
				isPossible = false;
				break;
			}
		}
		return (isPossible && hasAtLeastOneBlank);
    }
}
