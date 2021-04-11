package leetcode.middle;

/**
 * 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * @author Result Lv
 * @date 2021/4/8 11:02 下午
 */
public class Solution5 {

    /**
     * dp[i][j]表示从字符串从i到j位是否是回文子串
     * DP递推公式：
     *      1.dp[i][j] = true, i = j
     *      2.dp[i][j] = dp[i+1][j-1] && s[i] == s[j], i <= j+1
     */
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len <= 1){
            return s;
        }
        String longestStr = s.substring(0, 1);
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)){
                    if (j - i < 3){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                if (dp[i][j] && s.substring(i, j+1).length() > longestStr.length()){
                    longestStr = s.substring(i, j+1);
                }
            }
        }
        return longestStr;
    }

    public static void main(String[] args){
//        String s = "babad";
        String s = "acd";
        System.out.println(longestPalindrome(s));
    }
}
