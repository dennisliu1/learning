import java.util.*;
import java.lang.*;

/**
http://www.spoj.com/problems/TEST/

Your program is to use the brute-force approach in order to find the Answer to Life, 
the Universe, and Everything. 
More precisely... rewrite small numbers from input to output. 
Stop processing input after reading in the number 42. 
All numbers at input are integers of one or two digits.


Example

Input:
1
2
88
42
99

Output:
1
2
88
Information

If you have some troubles with your code, 
you can take a look in the forum, 
you'll find the answer, only for this problem, in various languages.
*/

class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// for (int i = 0; i < args.length; i++) {
		// 	System.out.println(args[i]);
		// }

		Scanner in = new Scanner(System.in);
		while(in.hasNextLine()) {
			String line = in.nextLine();
			if(line.equals("42")) break;
			else System.out.println(line);
		}
		
	}
}