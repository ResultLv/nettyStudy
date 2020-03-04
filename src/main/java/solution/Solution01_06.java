package solution;

public class Solution01_06 {

    public static String compressString(String S) {
        if (S.length() == 1) {
            return S;
        }
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < S.length()-1; i++) {
            char c = S.charAt(i);
            if (c == S.charAt(i+1)){
                count++;
            }else{
                sb.append(c).append(count);
                count = 1;
            }
            if (i == S.length()-2){
                sb.append(S.charAt(i+1)).append(count);
            }
        }
        String compactStr = sb.toString();
        return compactStr.length() < S.length() ? compactStr : S;
    }

    public static void main(String[] args) {
        System.out.println(Solution01_06.compressString("aabcccccaaa"));
        System.out.println(Solution01_06.compressString("abbccd"));
        System.out.println(Solution01_06.compressString("qwerrrtyuiop"));
        System.out.println(Solution01_06.compressString("a"));
    }
}
