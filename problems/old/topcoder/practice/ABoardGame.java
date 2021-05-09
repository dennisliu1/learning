package topcoder.practice;

/*

Problem Statement
Your friends Alice and Bob are playing a board game. They have asked you to help them to determine the winner. The game is played on a square board with 2N rows and 2N columns. The exact rules of the game itself are not important for this problem. Once the game is over, each cell of the board is either empty or contains a single piece that belongs to either Alice or Bob. You are given board, where the j-th character in i-th element (0-based indices) describes the contents of the cell in row i, column j: '.' represents an empty cell, 'A' a cell with Alice's piece and 'B' a cell with Bob's piece.

The entire board is divided into N regions. Region 1 occupies the 4 central cells of the board. Each next region contains all cells that are horizontally, vertically or diagonally adjacent to cells of the immediately previous region and do not belong to any of the previous regions. For example, when N = 4, here is how the regions look:
44444444
43333334
43222234
43211234
43211234
43222234
43333334
44444444
The winner is determined as follows. Consider the lowest numbered region that contains a different number of Alice's and Bob's pieces. The player who has more pieces in this region is the winner. If all regions contain the same number of Alice's and Bob's pieces, the game ends in a draw.

Return "Alice" if Alice wins the given game, "Bob" if Bob wins and "Draw" if the game ends in a draw. Note that return values are case-sensitive.
Definition
Class: ABoardGame
Method: whoWins
Parameters: String[]
Returns: String
Method signature: String whoWins(String[] board)
(be sure your method is public)
Limits
Time limit (s): 2.000
Memory limit (MB): 256
Constraints
- board will contain between 2 and 50 elements, inclusive.
- The number of elements in board will be even.
- Each element of board will contain the same number of characters as the number of elements in board.
- Each character in board will be 'A', 'B' or '.'.
Examples
0)
{".....A", "......", "..A...", "...B..", "......", "......"}
Returns: "Alice"
Both Alice and Bob have 1 piece in region 1, so they are tied there. In region 2, they have no pieces at all, so a tie again. Finally, in region 3 Alice has 1 piece, while Bob has none. So Alice is the winner of this game.
1)
{"AAAA", "A.BA", "A..A", "AAAA"}
Returns: "Bob"
Even though Alice has 12 pieces and Bob just one, this one piece is enough for him to win.
2)
{"..", ".."}
Returns: "Draw"
The board can be entirely empty.
3)
{"BBB..BAB...B.B", ".AAAAAAAAAAAA.", "AA.AA.AB..A.AB", "..........B.AB", ".A..BBAB.A.BAB", ".AB.B.......A.", ".A..A.AB.A..AB", ".ABAA.BA...BA.", "BAAAB.....ABA.", ".A....B..A..B.", "B...B....B..A.", "BA.B..A.ABA.A.", "BAAAA.AAAAA.A.", "B.B.B.BB.B...."}
Returns: "Alice"
4)
{"..A..AAA..AA", "ABABB..AAAAA", "ABBBBBBBBBA.", "AABBBABABBAA", "...BABABABBA", "B.BA..A.BBA.", "AA.A..B.AB.B", "..BA.B.AABAA", "..ABABBBABA.", ".ABB.BBBBBAA", "ABAAA.AA.A.A", "A..AAA.AAA.A"}
Returns: "Bob"
5)
{"B..ABAABBB", "B.........", "A..A.AA..B", "A.BBBAA..A", "B.AAAAB...", "A..BBBBB.A", "B..ABAABBA", "A......B.B", "B......A.A", "BA.AABBB.A"}
Returns: "Draw"
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.


 */

/**
 * Created by Dennis on 10/12/2017.
 */
public class ABoardGame {
    public static String ALICE = "Alice";
    public static String BOB = "Bob";
    public static String DRAW = "Draw";

	public String whoWins(String[] board) {
		if(board[0].length() != board.length) return "Draw";
        int n = board.length/2;

		int[] alice = new int[n];
        int[] bob = new int[n];

		int i = 0;
		int j = 0;
		int dir = 1;
		for(int l = 0; l < n; l++) {
			j = 0;
			for(int k = 0; k < n; k++) {
				char c = board[i].charAt(j);
				if(c == 'A') alice[j]++;
				if(c == 'B') bob[j]++;
				char c2 = board[i].charAt(board.length-1-j);
				if(c2 == 'A') alice[j]++;
				if(c2 == 'B') bob[j]++;

				if(j < i) j++;
			}

            if(l == n-1) dir = -1;
            else i += dir;
		}
        int alicePts = 0;
		int bobPts = 0;
		for(int x = 0; x < n; x++) {
		    if(alice[x] > bob[x]) alicePts++;
		    else if(alice[x] < bob[x]) bobPts++;
        }

		if(alicePts > bobPts) return ALICE;
		else if(alicePts < bobPts) return BOB;
		else return DRAW;
	}
}
