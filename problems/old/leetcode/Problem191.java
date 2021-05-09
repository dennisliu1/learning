package leetcode;

/**
 * Created by Dennis on 12/4/2017.
 *
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

 For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.

 5 minutes, should have just done bit shifting
 */
public class Problem191 {
    public int hammingWeight(int n) {
        int count = 0;
        int x = n;
        for(int i = 32; i >= 0; i--) {
            int bit = x & 1;
            if(bit > 0) {
                count++;
            }
            x >>>= 1;
        }

        return count;
    }

    public static void main(String[] args) {
        Problem191 p = new Problem191();
        System.out.println(p.hammingWeight(  Integer.MIN_VALUE));
    }
}
