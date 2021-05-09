package topcoder.practice;

/*
Problem Statement
One day, Jamie noticed that many English words only use the letters A and B. Examples of such words include "AB" (short for abdominal), "BAA" (the noise a sheep makes), "AA" (a type of lava), and "ABBA" (a Swedish pop sensation).


Inspired by this observation, Jamie created a simple game. You are given two s: initial and target. The goal of the game is to find a sequence of valid moves that will change initial into target. There are two types of valid moves:

Add the letter A to the end of the string.
Add the letter B to the end of the string and then reverse the entire string. (After the reversal the newly-added B becomes the first character of the string).
Return "Possible" (quotes for clarity) if there is a sequence of valid moves that will change initial into target. Otherwise, return "Impossible".

Definition
Class: ABBADiv1
Method: canObtain
Parameters: String, String
Returns: String
Method signature: String canObtain(String initial, String target)
(be sure your method is public)
Limits
Time limit (s): 2.000
Memory limit (MB): 256
Constraints
- The length of initial will be between 1 and 49, inclusive.
- The length of target will be between 2 and 50, inclusive.
- target will be longer than initial.
- Each character in initial and each character in target will be either 'A' or 'B'.
Examples
0)
"A"
"BABA"
Returns: "Possible"
Jamie can perform the following moves:
Initially, the string is "A".
Jamie adds a 'B' to the end of the string and then reverses the string. Now the string is "BA".
Jamie adds a 'B' to the end of the string and then reverses the string. Now the string is "BAB".
Jamie adds an 'A' to the end of the string. Now the string is "BABA".
Since there is a sequence of moves which starts with "A" and creates the string "BABA", the answer is "Possible".
1)
"BAAAAABAA"
"BAABAAAAAB"
Returns: "Possible"
Jamie can add a 'B' to the end of the string and then reverse the string.
2)
"A"
"ABBA"
Returns: "Impossible"
3)
"AAABBAABB"
"BAABAAABAABAABBBAAAAAABBAABBBBBBBABB"
Returns: "Possible"
4)
"AAABAAABB"
"BAABAAABAABAABBBAAAAAABBAABBBBBBBABB"
Returns: "Impossible"
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/

import java.util.LinkedList;

/**
 * Created by Dennis on 10/12/2017.
 */
public class ABBADiv1 {
	public static String POSSIBLE = "Possible";
	public static String IMPOSSIBLE = "Impossible";

	public String canObtain(String initial, String target) {
		int start = -1, end = -1;
		int pos = 0, prevPos = -1;
		while(pos >= 0 && pos != prevPos) {
			pos = target.indexOf(initial, pos);
			if(pos >= 0) {
				start = pos;
				end = pos+initial.length()-1;
				boolean possible = trace(initial, target, start, end, false);
				if(possible) return POSSIBLE;
                prevPos = pos+initial.length()-1;
			}
			pos = target.indexOf( new StringBuffer(initial).reverse().toString(), pos );
			if(pos >= 0) {
				start = pos;
				end = pos+initial.length()-1;
				boolean possible = trace(initial, target, start, end, true);
				if(possible) return POSSIBLE;
                prevPos = pos+initial.length()-1;
			}
		}
		
		return IMPOSSIBLE;
	}

	public boolean trace(String initial, String target, int start, int end, boolean reversed) {
		char[] arr = target.toCharArray();
		int i = start, j = end;
		boolean dirRight = !reversed;
		while(i >= 0 && j < target.length()) {
			if(dirRight && j+1 < target.length()) {
				if(arr[j+1] == 'A') j++;
				else if(arr[j+1] == 'B') {
					j++;
					dirRight = false;
				}
			}
			else if(!dirRight && i-1 >= 0) {
				if(arr[i-1] == 'A') i--;
				else if(arr[i-1] == 'B') {
					i--;
					dirRight = true;
				}
			}
			else break;
		}
		return (i == 0 && j == target.length()-1);
	}

	public void printSteps() {

	}

	public void clearSteps() {

	}
}
