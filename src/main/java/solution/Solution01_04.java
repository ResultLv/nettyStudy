package solution;

import java.util.*;

public class Solution01_04 {

    public static boolean canPermutePalindrome(String s) {
//        char[] chars = s.toCharArray();
//        Map<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < chars.length; i++) {
//            if (!map.keySet().contains(chars[i])){
//                map.put(chars[i], 1);
//            }else {
//                map.put(chars[i], map.get(chars[i]) + 1);
//            }
//        }
//        int flag = 0;
//        for (Map.Entry<Character, Integer> entry : map.entrySet()){
//            if (chars.length % 2 == 0 && entry.getValue() % 2 != 0){
//                return false;
//            }
//            if (chars.length % 2 != 0 && entry.getValue() % 2 != 0) {
//                flag++;
//            }
//        }
//        if (flag == 0 || flag == 1){
//            return true;
//        }
//        return false;

        //利用哈希表 每个字符出现的次数为偶数, 或者有且只有一个字符出现的次数为奇数时, 是回文的排列; 否则不是.
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!set.add(c)) {
                set.remove(c);
            }
        }
        return set.size() <= 1;
    }

    public static void main(String[] args) {
        System.out.println(Solution01_04.canPermutePalindrome("tactcoa"));
        System.out.println(Solution01_04.canPermutePalindrome("abc"));
        System.out.println(Solution01_04.canPermutePalindrome("aa"));
        System.out.println(Solution01_04.canPermutePalindrome("civic"));
    }
}
