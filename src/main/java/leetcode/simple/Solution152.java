package leetcode.simple;

/**
 * 验证回文串
 * https://leetcode-cn.com/problems/valid-palindrome/
 * @author Result Lv
 * @date 2021/4/16 10:08 下午
 */
public class Solution152 {

//    输入: "A man, a plan, a canal: Panama"
//    输出: true
    public static boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-z|A-Z|0-9]", "");
        int start = 0, end = s.length() - 1;
        while (start < end){
            if (s.charAt(start) != s.charAt(end)
                    && !String.valueOf(s.charAt(start)).toLowerCase().equals(String.valueOf(s.charAt(end)).toLowerCase())){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

//    基于ASCII码判断字符是否是整数或数字
//    ASCII码范围：0-9:40-57   A-Z:65-90   a-z:97-122
    public static boolean isPalindrome1(String s) {
        s = s.replaceAll("[^a-z|A-Z|0-9]", "");
        int start = 0, end = s.length() - 1;
        while (start < end){
            char c1 = s.charAt(start);
            char c2 = s.charAt(end);
            if ((c1 < '0' || c1 > '9') && (c2 < '0' || c2 > '9')){
                int abs = Math.abs(c1 - c2);
                if (abs != 0 && abs != 32){
                    return false;
                }
            }else if (c1 != c2){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args){
        String str = "A man, a plan, a canal: Panama";
        String str1 = "0P0";

        System.out.println(isPalindrome1(str));
        System.out.println(isPalindrome1(str1));
    }
}
