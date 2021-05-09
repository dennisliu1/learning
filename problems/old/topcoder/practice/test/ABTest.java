package topcoder.practice.test;

import org.junit.Test;

import topcoder.practice.AB;

/**
 * Created by Dennis on 10/11/2017.
 * 4 pomodoros
 * 3
 */
public class ABTest {
    @Test
    public void test1() {
        AB ab = new AB();
        runTest(ab, 3, 2);
        runTest(ab, 2, 0);
        runTest(ab, 5, 8);
        runTest(ab, 10, 12);
        runTest(ab, 10, 1);

        for(int i = 0; i <= 40; i++) runTest(ab, 10, i);
    }

    public boolean isValid(String s, int k) {
        int pairs = 0;

        int num = 0;
        char[] arr = s.toCharArray();
        for(int i = arr.length-1; i >= 0; i--) {
            char c = arr[i];
            if(c == 'A') pairs += num;
            else if(c == 'B') num++;
        }

        return (pairs == k);
    }

    public void runTest(AB ab, int n, int k) {
        String result = ab.createString(n,k);
        boolean b = isValid(result, k);
        System.out.printf("n=%d k=%d:%s:%s\n", n, k, result, b);
    }
}