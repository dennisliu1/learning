package topcoder.practice.test;

import org.junit.Test;
import topcoder.practice.ABoardGame;

import static org.junit.Assert.*;

/**
 * Created by Dennis on 10/12/2017.
 */
public class ABoardGameTest {
    @Test
    public void test() {
        runTest(new String[]{".....A", "......", "..A...", "...B..", "......", "......"}, ABoardGame.ALICE);
        runTest(new String[]{"AAAA","A.BA","A..A","AAAA"}, ABoardGame.DRAW);
        runTest(new String[]{"..",".."}, ABoardGame.DRAW);
        runTest(new String[]{"BBB..BAB...B.B",".AAAAAAAAAAAA.","AA.AA.AB..A.AB","..........B.AB",
                ".A..BBAB.A.BAB",".AB.B.......A.",".A..A.AB.A..AB",".ABAA.BA...BA.",
                "BAAAB.....ABA.",".A....B..A..B.","B...B....B..A.","BA.B..A.ABA.A.",
                "BAAAA.AAAAA.A.","B.B.B.BB.B...."}, ABoardGame.BOB);
        runTest(new String[]{"..A..AAA..AA","ABABB..AAAAA","ABBBBBBBBBA.","AABBBABABBAA",
                "...BABABABBA","B.BA..A.BBA.","AA.A..B.AB.B","..BA.B.AABAA",
                "..ABABBBABA.",".ABB.BBBBBAA","ABAAA.AA.A.A","A..AAA.AAA.A"}, ABoardGame.BOB);
        runTest(new String[]{"B..ABAABBB","B.........","A..A.AA..B","A.BBBAA..A","B.AAAAB...","A..BBBBB.A","B..ABAABBA","A......B.B","B......A.A","BA.AABBB.A"}, ABoardGame.BOB);
    }

    public void runTest(String[] board, String expected) {
        ABoardGame abg = new ABoardGame();
        String result = abg.whoWins(board);
        printRegions(board.length/2);
        for(String row : board) {
            System.out.println(row);
        }
        printRegions(board.length/2);
        System.out.printf("%s = %s\n\n", result, expected);
    }

    public void printRegions(int n) {
        int dir = 1;
        int i = 0;
        for(int l = 0; l < n*2; l++) {
            System.out.print(""+i);
            if(l == n-1) dir = -1;
            else i += dir;
        }
        System.out.println();
    }
}