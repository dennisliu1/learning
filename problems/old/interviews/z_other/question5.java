//static String[] matchParentheses(String[] values) {
//    String[] results = new String[values.length];
//    int i = 0;
//    for(String v : values) {
//        String result = isCorrectForm(v);
//        results[i] = result;
//        i++;
//    }
//    return results;
//}
//static String isCorrectForm(String v) {
//    LinkedList<Character> stack = new LinkedList<Character>();
//    char[] arr = v.toCharArray();
//    boolean isValid = true;
//    for(char c : arr) {
//        char prev = (stack.size() > 0) ? stack.get(0) : 0;
//        isValid &= validChar(prev, c);
//        if(isValid) {
//            if(c == '{' || c == '[' || c == '(' ) {
//                stack.push(c);
//            }
//        }
//        if(!isValid) return "NO";
//    }
//    return "YES";
//}
//static boolean validChar(char prev, char c) {
//    if(prev == '{' && c == '}') return true;
//    else if(prev == '[' && c == ']') return true;
//    else if(prev == '(' && c == ')') return true;
//    else if(prev == 0 && c == '{') return true;
//    else if(prev == 0 && c == '[') return true;
//    else if(prev == 0 && c == '(') return true;
//    else return false;
//}