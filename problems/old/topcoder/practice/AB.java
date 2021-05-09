package topcoder.practice;

/*
Problem Statement
You are given two s: N and K. Lun the dog is interested in strings that satisfy the following conditions:

The string has exactly N characters, each of which is either 'A' or 'B'.
The string s has exactly K pairs (i, j) (0 <= i < j <= N-1) such that s[i] = 'A' and s[j] = 'B'.
If there exists a string that satisfies the conditions, find and return any such string. Otherwise, return an empty string.

Definition
Class: AB
Method: createString
Parameters: int, int
Returns: String
Method signature: String createString(int N, int K)
(be sure your method is public)
Limits
Time limit (s): 2.000
Memory limit (MB): 256
Constraints
- N will be between 2 and 50, inclusive.
- K will be between 0 and N(N-1)/2, inclusive.
Examples
0)
3
2
Returns: "ABB"
This string has exactly two pairs (i, j) mentioned in the statement: (0, 1) and (0, 2).
1)
2
0
Returns: "BA"
Please note that there are valid test cases with K = 0.
2)
5
8
Returns: ""
Five characters is too short for this value of K.
3)
10
12
Returns: "BAABBABAAB"
Please note that this is an example of a solution; other valid solutions will also be accepted.
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/

import java.util.ArrayList;

/**
 * Created by Dennis on 10/11/2017.
 */
public class AB {
    public String createString(int N, int K) {
        if(K > N && K > Math.pow((N/2), 2)) return "";
        if(K == 0) {
            String result = "";
            for(int i = 0; i < N; i++) result += "B";
            return result;
        }
        String result = recurse(0, N, 0, K, new ArrayList<Tuple>());
        return result;
    }

    public String recurse(int n, int N, int k, int K, ArrayList<Tuple> list) {
        if(n > N || k > K) {
            return "";
        }
        if(k == K) {
            return genString(list, n, N);
        }

        for(int x=K-k; x >= Math.floor((K-k)/2); x--) {
            for(int i = 1; i <= Math.sqrt(x); i++) {
                if(x % i == 0) {
                    int j = x / i;
                    ArrayList<Tuple> newList = new ArrayList<Tuple>(list);
                    newList.add(new Tuple(i,j));
                    String tryS = recurse(getLength(newList), N, k+x, K, newList);
                    // String tryS = recurse(n+i+j, N, k+i*j, K, ((s != "") ? s+"+" : "") + i+"*"+j);
                    if(!tryS.isEmpty()) return tryS;
                }
            }
        }
        return "";
    }

    public String genString(ArrayList<Tuple> list, int n, int N) {
        String result = "";
        
        int num = 0, length = 0;
        for(int i = list.size()-1; i >= 0; i--) {
            Tuple t = list.get(i);
            int x = Math.min(t.x, t.y);
            int y = Math.max(t.x, t.y);
            for (int j = 0; j < (y-num); j++)  result = "B" + result;
            for (int j = 0; j < x; j++)  result = "A" + result;
            
            length += x + (y-num);
            num += (y-num);
        }

        for (int i = length; i < N; i++) result = "B" + result;
        return result;
    }

    public int getLength(ArrayList<Tuple> list) {
        int length = 0;
        int num = 0;
        for(int i = list.size()-1; i >= 0; i--) {
            Tuple t = list.get(i);
            int x = Math.min(t.x, t.y);
            int y = Math.max(t.x, t.y);
            
            length += x + (y-num);
            num += (y-num);
        }
        return length;
    }

    public class Tuple {
        public int x;
        public int y;

        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}