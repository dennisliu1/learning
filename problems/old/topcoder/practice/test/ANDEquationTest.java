package topcoder.practice.test;

import org.junit.Test;
import topcoder.practice.ANDEquation;

import static org.junit.Assert.*;

/**
 * Created by Dennis on 10/12/2017.
 */
public class ANDEquationTest {
    @Test
    public void test1() {
        runTest(new int[]{1, 3, 5}, 1);
        runTest(new int[]{31, 7}, -1);
        runTest(new int[]{31, 7, 7}, 7);
        runTest(new int[]{1,0,0,1,0,1,0,1,0,0,0,0,0,0,0,1,0,0, 0,0,0,0,0,0,1,0,1,0,1,1,0,0,0,1}, 0);
        runTest(new int[]{191411,256951,191411,191411,191411,256951,195507,191411,192435,191411, 191411,195511,191419,191411,256947,191415,191475,195579,191415,191411, 191483,191411,191419,191475,256947,191411,191411,191411,191419,256947, 191411,191411,191411}, 191411);
        runTest(new int[]{1362,1066,1659,2010,1912,1720,1851,1593,1799,1805,1139,1493,1141,1163,1211}, -1);
        runTest(new int[]{2, 3, 7, 19}, -1);
    }

    public void runTest(int[] nums, int expected) {
        ANDEquation and = new ANDEquation();
        int y = and.restoreY(nums);
        for(int num : nums) System.out.print(num+", ");
        System.out.printf("\n%d == %d\n\n", y, expected);
//        assert(y == expected);
    }
}