package solution;

class Solution01_01 {
    public static void main(String[] args) {
        String str = "abc";
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length-1; i++) {
            for (int j = i+1; j < chars.length; j++) {
                if (chars[i] == chars[j]){
                    System.out.println("false");
                    break;
                }
            }
        }
        System.out.println("true");
    }
}