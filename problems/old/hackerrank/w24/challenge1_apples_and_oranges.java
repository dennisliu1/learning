import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
https://www.hackerrank.com/contests/w24/challenges/apple-and-orange

1 <= s,t,a,b,m,n <= 10^5
-10^5 <= d <= 10^5
a < s < t < b

Input:
1. s, t
2. a, b
3. m, n
4. m # of apple distances
5. n # of orange distances

Output:
1. # apples on Sam's House
2. # oranges on Sam's House

*/
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int t = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int m = in.nextInt();
        int n = in.nextInt();
        int[] apple = new int[m];
        for(int apple_i=0; apple_i < m; apple_i++){
            apple[apple_i] = in.nextInt();
        }
        int[] orange = new int[n];
        for(int orange_i=0; orange_i < n; orange_i++){
            orange[orange_i] = in.nextInt();
        }

        // count apples on Sam's House
        int numApplesHouse = 0;
        for(int apple_i=0; apple_i < m; apple_i++){
            int position = a + apple[apple_i];
            if(s <= position && position <= t) 
                numApplesHouse++;
        }
        System.out.println(numApplesHouse);

        // count oranges on Sam's House
        int numOrangesHouse = 0;
        for(int orange_i=0; orange_i < n; orange_i++){
            int position = b + orange[orange_i];
            if(s <= position && position <= t) 
                numOrangesHouse++;
        }
        System.out.println(numOrangesHouse);
    }
}