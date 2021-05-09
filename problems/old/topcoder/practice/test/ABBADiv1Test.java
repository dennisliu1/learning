package topcoder.practice.test;

import org.junit.Test;
import topcoder.practice.ABBADiv1;

import static org.junit.Assert.*;

/**
 * Created by Dennis on 10/12/2017.
 * 4 pomodoros
 */
public class ABBADiv1Test {

    @Test
    public void test() {
        runTest("A", "BABA", true);
        runTest("B", "BB", true);
        runTest("A", "BABA", true);
        runTest("BAAAAABAA", "BAABAAAAAB", true);
        runTest("A", "ABBA", false);
        runTest("AAABBAABB", "BAABAAABAABAABBBAAAAAABBAABBBBBBBABB", true);
        runTest("AAABAAABB", "BAABAAABAABAABBBAAAAAABBAABBBBBBBABB", false);
    }

    public void runTest(String initial, String target, boolean expected) {
        ABBADiv1 abba = new ABBADiv1();
        String result = abba.canObtain(initial, target);
        System.out.printf("%s,%s: %s %s\n", initial, target, result, expected);
        if(result.equals(ABBADiv1.POSSIBLE)) {
            abba.printSteps();
            abba.clearSteps();
        }
    }

//    public class ABBADiv1 {
//        public static String POSSIBLE = "Possible";
//        public static String IMPOSSIBLE = "Impossible";
//
//        public String canObtain(String initial, String target) {
//
//        }

//     // still too slow
//    public String canObtain(String initial, String target) {
//        LinkedList<Path> queue = new LinkedList<Path>();
//        queue.add(new Path(initial, false));
//        while(!queue.isEmpty()) {
//            Path path = queue.remove();
//            String s = path.s;
//
//            if(s.length() > target.length()) continue;
//            else if(s.length() == target.length()) {
//                String check = s;
//                if(path.isFlipped) {
//                    check = new StringBuffer(s).reverse().toString();
//                }
//                if(check.equals(target)) return POSSIBLE;
//            }
//            else {
//                queue.add( new Path(s+"A", false) );
//                queue.add( new Path(s+"B", true) );
//            }
//        }
//        return IMPOSSIBLE;
//    }
//
//    public class Path {
//        String s;
//        boolean isFlipped;
//
//        public Path(String s, boolean isFlipped) {
//            this.s = s;
//            this.isFlipped = isFlipped;
//        }
//    }
//        // iteration
//        // public String canObtain(String initial, String target) {
//        // 	LinkedList<String> queue = new LinkedList<String>();
//        // 	queue.add(initial);
//        // 	while(!queue.isEmpty()) {
//        // 		String s = queue.remove();
//        // 		if(s.length() > target.length()) continue;
//        // 		else if(s.length() == target.length() && initial.equals(target)) return POSSIBLE;
//        // 		else {
//        // 			queue.add( initial+"A" );
//        // 			queue.add( new StringBuffer(initial+"B").reverse().toString() );
//        // 		}
//        // 	}
//        // 	return IMPOSSIBLE;
//        // }
//
//        // recursion:
//        // public String canObtain(String initial, String target) {
//        // 	if(initial.length() > target.length()) return IMPOSSIBLE;
//        // 	return (recurse(initial, target)) ? POSSIBLE : IMPOSSIBLE;
//        // }
//
//        // public boolean recurse(String initial, String target) {
//        // 	if(initial.length() > target.length()) return false;
//        // 	else if(initial.length() == target.length()) return initial.equals(target);
//        // 	else {
//        // 		return recurse(initial+"A", target)
//        // 		|| recurse( new StringBuffer(initial+"B").reverse().toString(), target );
//        // 	}
//        // }
//    }
}