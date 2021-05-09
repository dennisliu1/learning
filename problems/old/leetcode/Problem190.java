package leetcode;

/**
 * Created by Dennis on 12/4/2017.
 *
 * Reverse bits of a given 32 bits unsigned integer.

 For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

 Follow up:
 If this function is called many times, how would you optimize it?

 Related problem: Reverse Integer

 15 minutes, couldn't do the sign bit
 */
public class Problem190 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for(int i = 0; i < 32; i++) {
            result += n & 1;
            n >>>= 1; // right shift, don't carry MSB
            if(i < 31) result <<= 1; // left shift result, but not on last bit
        }
        return  result;
    }

    public static void main(String[] args) {
        Problem190 p = new Problem190();
        System.out.println(p.reverseBits(1));
    }
}
