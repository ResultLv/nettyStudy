package solution;

public class Solution01_09 {

    public static boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()){
            return false;
        }
        if (s1.equals(s2)){
            return true;
        }
        for (int i = 0; i < s1.length(); i++) {
            String rotate = s1.substring(i + 1) + s1.substring(0, i + 1);
            if (rotate.equals(s2)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Solution01_09.isFlipedString("", ""));
        System.out.println(Solution01_09.isFlipedString("waterbottle", "erbottlewat"));
    }
}
